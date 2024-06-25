package com.fzdkx.yunke.controller;

import com.fzdkx.yunke.bean.dao.TCustomer;
import com.fzdkx.yunke.bean.vo.CustomerExcel;
import com.fzdkx.yunke.bean.vo.CustomerVO;
import com.fzdkx.yunke.common.Result;
import com.fzdkx.yunke.service.CustomerService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/22
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @PostMapping
    public Result<String> addCustomer(@RequestBody TCustomer tCustomer) {
        int count = customerService.save(tCustomer);
        return count >= 1 ? Result.success() : Result.fail();
    }

    @GetMapping("/list")
    public Result<PageInfo<CustomerVO>> customerPage(Integer pageNum, Integer pageSize) {
        PageInfo<CustomerVO> pageInfo = customerService.pageQuery(pageNum, pageSize);
        return Result.success(pageInfo);
    }

    @GetMapping("/{id}")
    public Result<TCustomer> getCustomer(@PathVariable("id") Integer id) {
        TCustomer tCustomer = customerService.getCustomer(id);
        return Result.success(tCustomer);
    }

    @PutMapping
    public Result<String> editCustomer(@RequestBody TCustomer tCustomer) {
        customerService.editCustomer(tCustomer);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteCustomer(@PathVariable("id") Integer id) {
        int count = customerService.remove(id);
        return count >= 1 ? Result.success() : Result.fail();
    }

    @PostMapping("/exportExcel")
    public void exportSelect(@RequestBody List<Integer> ids, HttpServletResponse response) throws IOException, InterruptedException {
        List<CustomerExcel> customerExcels = customerService.exportSelect(ids);
        if (ObjectUtils.isEmpty(customerExcels)) {
            return;
        }
        // export excel
        customerService.exportExcel(response, customerExcels);
    }

}
