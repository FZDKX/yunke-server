package com.fzdkx.yunke.controller;

import com.fzdkx.yunke.bean.dao.TActivityRemark;
import com.fzdkx.yunke.bean.vo.ActivityRemarkVO;
import com.fzdkx.yunke.common.Result;
import com.fzdkx.yunke.service.ActivityRemarkService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @author 发着呆看星
 * @create 2024/6/19
 */
@RestController
@RequestMapping("/api/activityRemark")
public class ActivityRemarkController {

    @Resource
    private ActivityRemarkService activityRemarkService;

    @GetMapping("/list/{activityId}")
    public Result<PageInfo<ActivityRemarkVO>> remarkPage(@PathVariable("activityId") Integer activityId, Integer pageNum, Integer pageSize) {
        return activityRemarkService.remarkPage(activityId, pageNum, pageSize);
    }

    @GetMapping("/{id}")
    public Result<TActivityRemark> getRemark(@PathVariable("id") Integer id) {
        return activityRemarkService.getRemark(id);
    }

    @PutMapping()
    public Result<TActivityRemark> editRemark(@RequestBody TActivityRemark tActivityRemark) {
        return activityRemarkService.editRemark(tActivityRemark);
    }

    @PostMapping()
    public Result<TActivityRemark> addRemark(@RequestBody TActivityRemark tActivityRemark) {
        return activityRemarkService.addRemark(tActivityRemark);
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteRemark(@PathVariable("id") Integer id){
        return activityRemarkService.deleteRemark(id);
    }
}
