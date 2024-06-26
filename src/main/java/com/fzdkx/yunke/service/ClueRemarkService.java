package com.fzdkx.yunke.service;

import com.fzdkx.yunke.bean.dao.TClueRemark;
import com.fzdkx.yunke.bean.vo.ClueRemarkVO;
import com.fzdkx.yunke.common.Result;
import com.github.pagehelper.PageInfo;

/**
 * @author 发着呆看星
 * @create 2024/6/22
 */
public interface ClueRemarkService {
    Result<PageInfo<ClueRemarkVO>> remarkPage(Integer clueId, Integer pageNum, Integer pageSize);

    Result<TClueRemark> getRemark(Integer id);

    Result<String> editRemark(TClueRemark tClueRemark);

    Result<String> addRemark(TClueRemark tClueRemark);

    Result<String> deleteRemark(Integer id);
}
