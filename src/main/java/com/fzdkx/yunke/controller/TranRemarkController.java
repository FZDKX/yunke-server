package com.fzdkx.yunke.controller;

import com.fzdkx.yunke.bean.dao.TTranRemark;
import com.fzdkx.yunke.bean.vo.TranRemarkVO;
import com.fzdkx.yunke.common.Result;
import com.fzdkx.yunke.service.TranRemarkService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @author 发着呆看星
 * @create 2024/6/25
 */
@RestController
@RequestMapping("/api/tranRemark")
public class TranRemarkController {

    @Resource
    private TranRemarkService tranRemarkService;

    @GetMapping("/list/{tranId}")
    public Result<PageInfo<TranRemarkVO>> remarkPage(@PathVariable("tranId") Integer tranId, Integer pageNum, Integer pageSize) {
        return tranRemarkService.remarkPage(tranId, pageNum, pageSize);
    }

    @GetMapping("/{id}")
    public Result<TTranRemark> getRemark(@PathVariable("id") Integer id) {
        return tranRemarkService.getRemark(id);
    }

    @PutMapping()
    public Result<String> editRemark(@RequestBody TTranRemark tTranRemark) {
        return tranRemarkService.editRemark(tTranRemark);
    }

    @PostMapping()
    public Result<String> addRemark(@RequestBody TTranRemark tTranRemark) {
        return tranRemarkService.addRemark(tTranRemark);
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteRemark(@PathVariable("id") Integer id) {
        return tranRemarkService.deleteRemark(id);
    }
}
