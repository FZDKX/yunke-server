package com.fzdkx.yunke.controller;

import com.fzdkx.yunke.bean.dao.TClueRemark;
import com.fzdkx.yunke.bean.vo.ClueRemarkVO;
import com.fzdkx.yunke.common.Result;
import com.fzdkx.yunke.service.ClueRemarkService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @author 发着呆看星
 * @create 2024/6/22
 */
@RestController
@RequestMapping("/api/clueRemark")
public class ClueRemarkController {

    @Resource
    private ClueRemarkService clueRemarkService;

    @GetMapping("/list/{clueId}")
    public Result<PageInfo<ClueRemarkVO>> remarkPage(@PathVariable("clueId") Integer clueId, Integer pageNum, Integer pageSize) {
        return clueRemarkService.remarkPage(clueId, pageNum, pageSize);
    }

    @GetMapping("/{id}")
    public Result<TClueRemark> getRemark(@PathVariable("id") Integer id) {
        return clueRemarkService.getRemark(id);
    }

    @PutMapping()
    public Result<String> editRemark(@RequestBody TClueRemark tClueRemark) {
        return clueRemarkService.editRemark(tClueRemark);
    }

    @PostMapping()
    public Result<String> addRemark(@RequestBody TClueRemark tClueRemark) {
        return clueRemarkService.addRemark(tClueRemark);
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteRemark(@PathVariable("id") Integer id) {
        return clueRemarkService.deleteRemark(id);
    }
}
