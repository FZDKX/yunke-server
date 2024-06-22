package com.fzdkx.yunke.controller;

import com.fzdkx.yunke.bean.dao.TClue;
import com.fzdkx.yunke.bean.vo.ClueOperateVO;
import com.fzdkx.yunke.bean.vo.ClueVO;
import com.fzdkx.yunke.common.Result;
import com.fzdkx.yunke.service.ClueService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author 发着呆看星
 * @create 2024/6/20
 */
@RestController
@RequestMapping("/api/clue")
public class ClueController {

    @Resource
    private ClueService clueService;

    @GetMapping("/list")
    public Result<PageInfo<ClueVO>> cluePage(Integer pageNum, Integer pageSize) {
        return clueService.cluePage(pageNum, pageSize);
    }

    @PostMapping("/upload")
    public Result<String> importExcel(@RequestParam("file") MultipartFile file) throws IOException {
        return clueService.importExcel(file.getInputStream());
    }

    @GetMapping("/{id}")
    public Result<ClueOperateVO> getClueOperateVO(@PathVariable("id") Integer id) {
        return clueService.getClueOperateVO(id);
    }

    @PostMapping
    public Result<String> addClue(@RequestBody TClue tClue) {
        clueService.saceClue(tClue);
        return Result.success();
    }

    @PutMapping
    public Result<String> editClue(@RequestBody TClue clue){
        clueService.editClue(clue);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable("id") Integer id) {
        clueService.remove(id);
        return Result.success();
    }
}
