package com.fzdkx.yunke.config.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.fzdkx.yunke.bean.dao.LoginUser;
import com.fzdkx.yunke.bean.dao.TClue;
import com.fzdkx.yunke.mapper.TClueMapper;
import com.fzdkx.yunke.utils.JSONUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/20
 * 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
 */
@Slf4j
public class ClueDataListener implements ReadListener<TClue> {

    // 每读取多少条数据，进行一次数据库存储，实际使用中可以100条，然后清理list ，方便内存回收
    private static final int BATCH_COUNT = 100;

    //  缓存的数据 ，固定大小的List
    private List<TClue> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    // 操作数据存储到数据库的bean
    private TClueMapper tClueMapper;

    // 当前用户信息
    private LoginUser loginUser;


    // 如果使用了spring , 请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
    public ClueDataListener(TClueMapper tClueMapper, LoginUser loginUser) {
        this.tClueMapper = tClueMapper;
        this.loginUser = loginUser;
    }

    /**
     * 每解析一行excel数据，就会调用一次
     */
    @Override
    public void invoke(TClue data, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSONUtils.toJSON(data));
        // 设置线索的创建人和创建时间
        data.setCreateBy(loginUser.getTUser().getId());
        data.setCreateTime(new Date());
        // 将数据添加到缓存中
        cachedDataList.add(data);
        // 如果缓存中的数据达到 BATCH_COUNT 条，那么进行一次数据库操作，将缓存中的数据存入数据库
        if (cachedDataList.size() >= BATCH_COUNT) {
            // 将缓存中的数据存入数据库
            saveData();
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    /**
     * 当整个excel解析完成后，调用这个方法
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 将数据存储进入数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", cachedDataList.size());
        // 批量插入进数据库
        tClueMapper.batchInsert(cachedDataList);
        log.info("存储数据库成功！");
    }
}