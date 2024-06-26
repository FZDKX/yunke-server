package com.fzdkx.yunke.service;

import com.fzdkx.yunke.bean.dao.TTranRemark;
import com.fzdkx.yunke.bean.vo.TranRemarkVO;
import com.fzdkx.yunke.common.Result;
import com.github.pagehelper.PageInfo;

/**
 * @author 发着呆看星
 * @create 2024/6/25
 */
public interface TranRemarkService {
    Result<PageInfo<TranRemarkVO>> remarkPage(Integer tranId, Integer pageNum, Integer pageSize);

    Result<TTranRemark> getRemark(Integer id);

    Result<String> editRemark(TTranRemark tTranRemark);

    Result<String> addRemark(TTranRemark tTranRemark);

    Result<String> deleteRemark(Integer id);
}
