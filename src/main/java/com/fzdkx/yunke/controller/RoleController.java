package com.fzdkx.yunke.controller;

import com.fzdkx.yunke.bean.dao.TRole;
import com.fzdkx.yunke.bean.vo.AllPermAndRoleVO;
import com.fzdkx.yunke.bean.vo.IdListVO;
import com.fzdkx.yunke.bean.vo.PermAllVO;
import com.fzdkx.yunke.bean.vo.RoleInfoVO;
import com.fzdkx.yunke.common.Result;
import com.fzdkx.yunke.service.RoleService;
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
@RequestMapping("/api/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @PreAuthorize("hasAnyAuthority('role:list')")
    @GetMapping("/list")
    public Result<PageInfo<TRole>> pageList(Integer pageSize, Integer pageNum) {
        return roleService.getListByPage(pageSize, pageNum);
    }

    @PreAuthorize("hasAnyAuthority('role:view')")
    @GetMapping("/perm/{id}")
    // 查询指定角色所有权限
    public Result<List<PermAllVO>> queryRolePerm(@PathVariable("id") Integer id) {
        return roleService.getRolePerm(id);
    }

    @PreAuthorize("hasAnyAuthority('role:edit')")
    @GetMapping("/detail/{id}")
    // 编辑页面，查询角色所有信息
    public Result<AllPermAndRoleVO> queryRoleDetail(@PathVariable("id") Integer id) {
        return roleService.queryUserDetail(id);
    }

    @PreAuthorize("hasAnyAuthority('role:edit')")
    @PutMapping()
    public Result<String> editRole(@RequestBody RoleInfoVO roleInfo) {
        return roleService.editRole(roleInfo.getRole(), roleInfo.getIds());
    }

    @PreAuthorize("hasAnyAuthority('role:add')")
    @PostMapping()
    public Result<String> addRole(@RequestBody RoleInfoVO roleInfo) {
        return roleService.addRole(roleInfo.getRole(), roleInfo.getIds());
    }

    @PreAuthorize("hasAnyAuthority('role:delete')")
    @DeleteMapping("/{id}")
    public Result<String> deleteRole(@PathVariable("id") Integer id) {
        return roleService.deleteRole(id);
    }

    @PreAuthorize("hasAnyAuthority('role:delete')")
    @DeleteMapping("/batch")
    public Result<String> batchDelete(@RequestBody IdListVO idListVO) {
        return roleService.batchDeleteRole(idListVO);
    }

    @PreAuthorize("hasAnyAuthority('role:list')")
    @GetMapping("/all")
    // 用户赋予权限，需要加载所有角色
    public Result<List<TRole>> queryAllRole() {
        return roleService.queryAllRoles();
    }
}
