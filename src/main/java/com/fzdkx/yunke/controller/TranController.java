package com.fzdkx.yunke.controller;

import com.fzdkx.yunke.bean.dao.TTran;
import com.fzdkx.yunke.bean.vo.TranHistoryVO;
import com.fzdkx.yunke.bean.vo.TranVO;
import com.fzdkx.yunke.common.Result;
import com.fzdkx.yunke.service.TranService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/25
 */
@RestController
@RequestMapping("/api/tran")
public class TranController {

    @Resource
    private TranService tranService;

    @PostMapping
    public Result<String> addTran(@RequestBody TTran tTran) {
        int count = tranService.sava(tTran);
        return count >= 3 ? Result.success() : Result.fail();
    }

    @GetMapping("/list")
    public Result<PageInfo<TranVO>> getList(Integer pageNum, Integer pageSize) {
        PageInfo<TranVO> pageInfo = tranService.pageQuery(pageNum, pageSize);
        return Result.success(pageInfo);
    }

    @GetMapping("/history")
    public Result<List<TranHistoryVO>> getHistory(Integer tranId) {
        List<TranHistoryVO> list = tranService.getHistoryList(tranId);
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result<TTran> getTran(@PathVariable("id") Integer id) {
        TTran tTran = tranService.getTran(id);
        return Result.success(tTran);
    }

    @PutMapping
    public Result<String> editStage(@RequestBody TTran tTran) {
        int count = tranService.editStage(tTran);
        return count >= 2 ? Result.success() : Result.fail();
    }

    @PutMapping("/edit")
    public Result<String> editTran(@RequestBody TTran tTran) {
        int count = tranService.edit(tTran);
        return count >= 1 ? Result.success() : Result.fail();
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteTran(@PathVariable("id") Integer id) {
        int count = tranService.remove(id);
        return count >= 2 ? Result.success() : Result.fail();
    }
}
