package com.fzdkx.yunke.service;

import com.fzdkx.yunke.bean.dao.TTran;
import com.fzdkx.yunke.bean.vo.TranHistoryVO;
import com.fzdkx.yunke.bean.vo.TranVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/25
 */
public interface TranService {
    int sava(TTran tTran);

    PageInfo<TranVO> pageQuery(Integer pageNum, Integer pageSize);

    List<TranHistoryVO> getHistoryList(Integer tranId);

    TTran getTran(Integer id);

    int editStage(TTran tTran);

    int edit(TTran tTran);

    int remove(Integer id);
}
