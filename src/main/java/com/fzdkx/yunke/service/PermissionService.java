package com.fzdkx.yunke.service;

import com.fzdkx.yunke.bean.dao.TPermission;
import com.fzdkx.yunke.bean.vo.IdListVO;
import com.fzdkx.yunke.bean.vo.PermVO;
import com.fzdkx.yunke.bean.vo.PermissionViewVo;
import com.fzdkx.yunke.common.Result;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/14
 */
public interface PermissionService {
    Result<PageInfo<PermissionViewVo>> pageQueryTopMenu(Integer pageNum, Integer pageSize);

    Result<List<PermissionViewVo>> queryChildren(Integer id);

    Result<List<PermVO>> queryAll();

    Result<TPermission> query(Integer id);

    Result<String> edit(TPermission tPermission);

    Result<String> add(TPermission tPermission);

    Result<String> deleteById(Integer id);

    Result<String> batchDelete(IdListVO idListVO);
}
