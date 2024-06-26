package com.fzdkx.yunke.controller;

import com.fzdkx.yunke.bean.dao.TProduct;
import com.fzdkx.yunke.common.Result;
import com.fzdkx.yunke.service.ProductService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @author 发着呆看星
 * @create 2024/6/26
 */
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping("/list")
    public Result<PageInfo<TProduct>> pageList(Integer pageNum, Integer pageSize) {
        PageInfo<TProduct> pageInfo = productService.pageList(pageNum, pageSize);
        return Result.success(pageInfo);
    }

    @GetMapping("/{id}")
    public Result<TProduct> getProduct(@PathVariable("id") Integer id) {
        TProduct product = productService.getProduct(id);
        return Result.success(product);
    }

    @PutMapping()
    public Result<TProduct> editProduct(@RequestBody TProduct tProduct) {
        int count = productService.editProduct(tProduct);
        return count >= 1 ? Result.success() : Result.fail();
    }

    @PostMapping
    public Result<TProduct> addProduct(@RequestBody TProduct tProduct) {
        int count = productService.addProduct(tProduct);
        return count >= 1 ? Result.success() : Result.fail();
    }

    @PutMapping("/down/{id}")
    public Result<TProduct> downProduct(@PathVariable("id") Integer id) {
        int count = productService.updateProductState(id, 1);
        return count >= 1 ? Result.success() : Result.fail();
    }

    @PutMapping("/up/{id}")
    public Result<TProduct> upProduct(@PathVariable("id") Integer id) {
        int count = productService.updateProductState(id, 0);
        return count >= 1 ? Result.success() : Result.fail();
    }
}
