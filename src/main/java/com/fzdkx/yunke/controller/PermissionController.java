package com.fzdkx.yunke.controller;

import com.fzdkx.yunke.bean.dao.TPermission;
import com.fzdkx.yunke.bean.vo.IdListVO;
import com.fzdkx.yunke.bean.vo.PermAllVO;
import com.fzdkx.yunke.bean.vo.PermissionListVo;
import com.fzdkx.yunke.common.Result;
import com.fzdkx.yunke.service.PermissionService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/14
 */
@RestController
@RequestMapping("/api/perm")
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    // 懒加载，分页查询权限信息
    @PreAuthorize("hasAnyAuthority('perm:list')")
    @GetMapping("/{pageNum}/{pageSize}")
    public Result<PageInfo<PermissionListVo>> queryTopMenu(@PathVariable("pageNum") Integer pageNum,
                                                           @PathVariable("pageSize") Integer pageSize) {
        return permissionService.pageQueryTopMenu(pageNum, pageSize);
    }

    // 加载子节点
    @PreAuthorize("hasAnyAuthority('perm:list')")
    @GetMapping("/children/{id}")
    public Result<List<PermissionListVo>> queryChildren(@PathVariable("id") Integer id) {
        return permissionService.queryChildren(id);
    }

    // 查询所有权限
    @PreAuthorize("hasAnyAuthority('perm:list')")
    @GetMapping("/all")
    public Result<List<PermAllVO>> queryAll() {
        return permissionService.queryAll();
    }

    // 查询单个节点
    @PreAuthorize("hasAnyAuthority('perm:view')")
    @GetMapping("/{id}")
    public Result<TPermission> query(@PathVariable("id") Integer id) {
        return permissionService.query(id);
    }

    // 修改权限
    @PreAuthorize("hasAnyAuthority('perm:edit')")
    @PutMapping
    public Result<String> edit(@RequestBody TPermission tPermission) {
        return permissionService.edit(tPermission);
    }

    // 新增权限
    @PreAuthorize("hasAnyAuthority('perm:add')")
    @PostMapping
    public Result<String> add(@RequestBody TPermission tPermission) {
        return permissionService.add(tPermission);
    }

    // 删除权限
    @PreAuthorize("hasAnyAuthority('perm:delete')")
    @DeleteMapping("/{id}")
    public Result<String> deleteById(@PathVariable("id") Integer id) {
        return permissionService.deleteById(id);
    }

    // 批量删除
    @PreAuthorize("hasAnyAuthority('perm:delete')")
    @DeleteMapping()
    public Result<String> batchDelete(@RequestBody IdListVO idListVO) {
        return permissionService.batchDelete(idListVO);
    }
}
