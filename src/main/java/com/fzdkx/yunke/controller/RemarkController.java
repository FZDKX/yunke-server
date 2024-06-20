package com.fzdkx.yunke.controller;

import com.fzdkx.yunke.bean.dao.TActivityRemark;
import com.fzdkx.yunke.bean.vo.RemarkVO;
import com.fzdkx.yunke.common.Result;
import com.fzdkx.yunke.service.RemarkService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/19
 */
@RestController
@RequestMapping("/api/remark")
public class RemarkController {

    @Resource
    private RemarkService remarkService;

    @GetMapping("/list/{activityId}")
    public Result<PageInfo<RemarkVO>> remarkPage(@PathVariable("activityId") Integer activityId, Integer pageNum, Integer pageSize) {
        return remarkService.remarkPage(activityId, pageNum, pageSize);
    }

    @GetMapping("/{id}")
    public Result<TActivityRemark> getRemark(@PathVariable("id") Integer id) {
        return remarkService.getRemark(id);
    }

    @PutMapping()
    public Result<TActivityRemark> editRemark(@RequestBody TActivityRemark tActivityRemark) {
        return remarkService.editRemark(tActivityRemark);
    }

    @PostMapping()
    public Result<TActivityRemark> addRemark(@RequestBody TActivityRemark tActivityRemark) {
        return remarkService.addRemark(tActivityRemark);
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteRemark(@PathVariable("id") Integer id){
        return remarkService.deleteRemark(id);
    }
}
