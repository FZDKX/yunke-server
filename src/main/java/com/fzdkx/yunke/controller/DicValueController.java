package com.fzdkx.yunke.controller;

import com.fzdkx.yunke.bean.dao.TDicValue;
import com.fzdkx.yunke.bean.vo.DicValueVO;
import com.fzdkx.yunke.common.Result;
import com.fzdkx.yunke.service.DicValueService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @author 发着呆看星
 * @create 2024/6/26
 */
@RestController
@RequestMapping("/api/dicvalue")
public class DicValueController {
    @Resource
    private DicValueService dicValueService;

    @GetMapping("/list")
    public Result<PageInfo<DicValueVO>> pageList(Integer pageNum, Integer pageSize) {
        PageInfo<DicValueVO> pageInfo = dicValueService.pageList(pageNum, pageSize);
        return Result.success(pageInfo);
    }

    @GetMapping("/{id}")
    public Result<DicValueVO> getDicValue(@PathVariable("id") Integer id) {
        DicValueVO dicValue = dicValueService.getDicValue(id);
        return Result.success(dicValue);
    }

    @PutMapping()
    public Result<String> editProduct(@RequestBody TDicValue tDicValue) {
        int count = dicValueService.editDicValue(tDicValue);
        return count >= 1 ? Result.success() : Result.fail();
    }

    @PostMapping
    public Result<String> addProduct(@RequestBody TDicValue tDicValue) {
        int count = dicValueService.addDicValue(tDicValue);
        return count >= 1 ? Result.success() : Result.fail();
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteDicType(@PathVariable("id") Integer id) {
        int count = dicValueService.removeDicValue(id);
        return count >= 1 ? Result.success() : Result.fail();
    }
}
