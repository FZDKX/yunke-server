package com.fzdkx.yunke.controller;

import com.fzdkx.yunke.bean.dao.TPermission;
import com.fzdkx.yunke.bean.vo.IdListVO;
import com.fzdkx.yunke.bean.vo.PermVO;
import com.fzdkx.yunke.bean.vo.PermissionViewVo;
import com.fzdkx.yunke.common.Result;
import com.fzdkx.yunke.service.PermissionService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
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
    @GetMapping("/{pageNum}/{pageSize}")
    public Result<PageInfo<PermissionViewVo>> queryTopMenu(@PathVariable("pageNum") Integer pageNum,
                                                           @PathVariable("pageSize") Integer pageSize) {
        return permissionService.pageQueryTopMenu(pageNum, pageSize);
    }

    // 加载子节点
    @GetMapping("/children/{id}")
    public Result<List<PermissionViewVo>> queryChildren(@PathVariable("id") Integer id) {
        return permissionService.queryChildren(id);
    }

    // 查询所有权限
    @GetMapping("/all")
    public Result<List<PermVO>> queryAll() {
        return permissionService.queryAll();
    }

    // 查询单个节点
    @GetMapping("/{id}")
    public Result<TPermission> query(@PathVariable("id") Integer id) {
        return permissionService.query(id);
    }

    // 修改权限
    @PutMapping
    public Result<String> edit(@RequestBody TPermission tPermission) {
        return permissionService.edit(tPermission);
    }

    // 新增权限
    @PostMapping
    public Result<String> add(@RequestBody TPermission tPermission) {
        return permissionService.add(tPermission);
    }

    // 删除权限
    @DeleteMapping("/{id}")
    public Result<String> deleteById(@PathVariable("id") Integer id) {
        return permissionService.deleteById(id);
    }

    // 删除权限
    @DeleteMapping()
    public Result<String> deleteById(@RequestBody IdListVO idListVO) {
        return permissionService.batchDelete(idListVO);
    }
}
