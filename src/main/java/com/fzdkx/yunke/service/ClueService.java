package com.fzdkx.yunke.service;

import com.fzdkx.yunke.bean.dao.TClue;
import com.fzdkx.yunke.bean.vo.ClueOperateVO;
import com.fzdkx.yunke.bean.vo.ClueVO;
import com.fzdkx.yunke.common.Result;
import com.github.pagehelper.PageInfo;

import java.io.InputStream;

/**
 * @author 发着呆看星
 * @create 2024/6/20
 */
public interface ClueService {
    Result<PageInfo<ClueVO>> cluePage(Integer pageNum, Integer pageSize);

    Result<String> importExcel(InputStream file);


    Result<ClueOperateVO> getClueOperateVO(Integer id);

    void saceClue(TClue tClue);

    void editClue(TClue clue);

    void remove(Integer id);
}
