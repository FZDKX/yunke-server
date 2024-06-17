package com.fzdkx.yunke.service;

import com.fzdkx.yunke.bean.dao.TPermission;
import com.fzdkx.yunke.bean.vo.IdListVO;
import com.fzdkx.yunke.bean.vo.PermAllVO;
import com.fzdkx.yunke.bean.vo.PermissionListVo;
import com.fzdkx.yunke.common.Result;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/14
 */
public interface PermissionService {
    Result<PageInfo<PermissionListVo>> pageQueryTopMenu(Integer pageNum, Integer pageSize);

    Result<List<PermissionListVo>> queryChildren(Integer id);

    Result<List<PermAllVO>> queryAll();

    Result<TPermission> query(Integer id);

    Result<String> edit(TPermission tPermission);

    Result<String> add(TPermission tPermission);

    Result<String> deleteById(Integer id);

    Result<String> batchDelete(IdListVO idListVO);
}
