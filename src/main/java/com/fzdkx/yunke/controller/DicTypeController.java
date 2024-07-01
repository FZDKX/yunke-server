package com.fzdkx.yunke.controller;

import com.fzdkx.yunke.bean.dao.TDicType;
import com.fzdkx.yunke.bean.vo.DicVO;
import com.fzdkx.yunke.common.Result;
import com.fzdkx.yunke.service.DicTypeService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/26
 */
@RestController
@RequestMapping("/api/dictype")
public class DicTypeController {
    @Resource
    private DicTypeService dicTypeService;

    @GetMapping("/list")
    public Result<PageInfo<TDicType>> pageList(Integer pageNum, Integer pageSize) {
        PageInfo<TDicType> pageInfo = dicTypeService.pageList(pageNum, pageSize);
        return Result.success(pageInfo);
    }

    @GetMapping("/{id}")
    public Result<TDicType> getDicType(@PathVariable("id") Integer id) {
        TDicType dicType = dicTypeService.getDicType(id);
        return Result.success(dicType);
    }

    @PutMapping()
    public Result<TDicType> editProduct(@RequestBody TDicType tDicType) {
        int count = dicTypeService.editDicType(tDicType);
        return count >= 1 ? Result.success() : Result.fail();
    }

    @PostMapping
    public Result<TDicType> addProduct(@RequestBody TDicType tDicType) {
        int count = dicTypeService.addDicType(tDicType);
        return count >= 1 ? Result.success() : Result.fail();
    }

    @DeleteMapping("/{id}")
    public Result<TDicType> deleteDicType(@PathVariable("id") Integer id) {
        int count = dicTypeService.removeDicType(id);
        return count >= 1 ? Result.success() : Result.fail();
    }

    @GetMapping("/all")
    public Result<List<TDicType>> getDicType() {
        List<TDicType> dicTypes = dicTypeService.getAll();
        return Result.success(dicTypes);
    }
}
