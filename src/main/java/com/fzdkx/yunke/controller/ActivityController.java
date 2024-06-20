package com.fzdkx.yunke.controller;

import com.fzdkx.yunke.bean.dao.TActivity;
import com.fzdkx.yunke.bean.query.ActivitySearchQuery;
import com.fzdkx.yunke.bean.vo.ActivityVO;
import com.fzdkx.yunke.bean.vo.IdListVO;
import com.fzdkx.yunke.common.Result;
import com.fzdkx.yunke.service.ActivityService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author 发着呆看星
 * @create 2024/6/18
 */
@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    @Resource
    private ActivityService activityService;

    // 含条件的活动分页查询
    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('activity:list')")
    public Result<PageInfo<ActivityVO>> activitySearchPage(ActivitySearchQuery activitySearchQuery) {
        return activityService.activitySearchPage(activitySearchQuery);
    }

    // 根据ID查询活动
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('activity:view')")
    public Result<TActivity> queryActivityById(@PathVariable("id") Integer id) {
        return activityService.getActivityById(id);
    }

    // 编辑活动
    @PutMapping()
    @PreAuthorize("hasAnyAuthority('activity:edit')")
    public Result<String> editActivity(@RequestBody TActivity tActivity) {
        return activityService.setActivity(tActivity);
    }

    // 编辑活动
    @PostMapping()
    @PreAuthorize("hasAnyAuthority('activity:add')")
    public Result<String> addActivity(@RequestBody TActivity tActivity) {
        return activityService.addActivity(tActivity);
    }

    @PreAuthorize("hasAnyAuthority('activity:delete')")
    @DeleteMapping("/{id}")
    public Result<String> deleteRole(@PathVariable("id") Integer id) {
        return activityService.deleteActivity(id);
    }

    @PreAuthorize("hasAnyAuthority('activity:delete')")
    @DeleteMapping("/batch")
    public Result<String> batchDelete(@RequestBody IdListVO idListVO) {
        return activityService.batchDeleteActivity(idListVO);
    }

}