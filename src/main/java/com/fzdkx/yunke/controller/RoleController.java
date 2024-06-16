package com.fzdkx.yunke.controller;

import com.fzdkx.yunke.bean.dao.TRole;
import com.fzdkx.yunke.bean.vo.AllPermAndRoleVO;
import com.fzdkx.yunke.bean.vo.IdListVO;
import com.fzdkx.yunke.bean.vo.PermVO;
import com.fzdkx.yunke.bean.vo.RoleInfoVO;
import com.fzdkx.yunke.common.Result;
import com.fzdkx.yunke.service.RoleService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
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

    @GetMapping("/list")
    public Result<PageInfo<TRole>> pageList(Integer pageSize, Integer pageNum) {
        return roleService.getListByPage(pageSize, pageNum);
    }

    @GetMapping("/perm/{id}")
    public Result<List<PermVO>> queryRolePerm(@PathVariable("id") Integer id) {
        return roleService.getRolePerm(id);
    }

    @GetMapping("/detail/{id}")
    public Result<AllPermAndRoleVO> queryRoleDetail(@PathVariable("id") Integer id) {
        return roleService.queryUserDetail(id);
    }

    @PutMapping()
    public Result<String> editRole(@RequestBody RoleInfoVO roleInfo) {
        return roleService.editRole(roleInfo.getRole(), roleInfo.getIds());
    }

    @PostMapping()
    public Result<String> addRole(@RequestBody RoleInfoVO roleInfo) {
        return roleService.addRole(roleInfo.getRole(), roleInfo.getIds());
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteRole(@PathVariable("id") Integer id) {
        return roleService.deleteRole(id);
    }
    @DeleteMapping("/batch")
    public Result<String> batchDelete(@RequestBody IdListVO idListVO) {
        return roleService.batchDeleteRole(idListVO);
    }
}
