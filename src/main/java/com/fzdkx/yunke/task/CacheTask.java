package com.fzdkx.yunke.task;

import com.fzdkx.yunke.YunKeApplication;
import com.fzdkx.yunke.bean.dao.TActivity;
import com.fzdkx.yunke.bean.dao.TProduct;
import com.fzdkx.yunke.bean.vo.DicTypeVO;
import com.fzdkx.yunke.common.DictionaryConstant;
import com.fzdkx.yunke.service.ActivityService;
import com.fzdkx.yunke.service.DicTypeService;
import com.fzdkx.yunke.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 发着呆看星
 * @create 2024/6/20
 * 设置定时任务，从缓存中查询字典数据
 */
@Component
@EnableScheduling  // 开始定时任务
public class CacheTask {

    @Resource
    private DicTypeService dicTypeService;

    @Resource
    private ProductService productService;

    @Resource
    private ActivityService activityService;

    // 设置定时任务：两种方式设置定时任务
    // 一种是数字 + 时间单位 fixedDelay
    // 一种是表达式 cron
    //@Scheduled(cron = "${project.task.cron}", zone = "Asia/Shanghai", timeUnit = TimeUnit.MILLISECONDS, initialDelay  = 1000)
    @Scheduled(fixedDelayString = "${yunke.task.delay}", zone = "Asia/Shanghai", timeUnit = TimeUnit.MILLISECONDS, initialDelay = 1000)
    public void setCacheData() {
        // 清空原有缓存
        YunKeApplication.cacheMap.clear();
        // 查询字典中所有数据  类型 + 该类型所有数据
        List<DicTypeVO> dicTypeVOList = dicTypeService.loadAllDicDataByCache();
        for (DicTypeVO dicTypeVO : dicTypeVOList) {
            String code = dicTypeVO.getTypeCode();
            YunKeApplication.cacheMap.put(code, dicTypeVO.getValues());
        }
        // 查询产品表
        List<TProduct> products = productService.loadAllProductByCache();
        YunKeApplication.cacheMap.put(DictionaryConstant.INTENTION_PRODUCT, products);
        // 查询正在进行的市场活动
        List<TActivity> activities = activityService.loadAllOngoingActivity();
        YunKeApplication.cacheMap.put(DictionaryConstant.ACTIVITY, activities);
    }

}
