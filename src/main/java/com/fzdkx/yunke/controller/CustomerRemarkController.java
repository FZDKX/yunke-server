package com.fzdkx.yunke.controller;

import com.fzdkx.yunke.bean.dao.TCustomerRemark;
import com.fzdkx.yunke.bean.vo.CustomerRemarkVO;
import com.fzdkx.yunke.common.Result;
import com.fzdkx.yunke.service.CustomerRemarkService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @author 发着呆看星
 * @create 2024/6/22
 */
@RestController
@RequestMapping("/api/customerRemark")
public class CustomerRemarkController {

    @Resource
    private CustomerRemarkService customerRemarkService;

    @GetMapping("/list/{customerId}")
    public Result<PageInfo<CustomerRemarkVO>> remarkPage(@PathVariable("customerId") Integer customerId, Integer pageNum, Integer pageSize) {
        PageInfo<CustomerRemarkVO> pageInfo = customerRemarkService.remarkPage(customerId, pageNum, pageSize);
        return Result.success(pageInfo);
    }

    @GetMapping("/{id}")
    public Result<TCustomerRemark> getRemark(@PathVariable("id") Integer id) {
        TCustomerRemark tCustomerRemark = customerRemarkService.getRemark(id);
        return Result.success(tCustomerRemark);
    }

    @PutMapping()
    public Result<String> editRemark(@RequestBody TCustomerRemark tCustomerRemark) {
        customerRemarkService.editRemark(tCustomerRemark);
        return Result.success();
    }

    @PostMapping()
    public Result<String> addRemark(@RequestBody TCustomerRemark tCustomerRemark) {
        customerRemarkService.addRemark(tCustomerRemark);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteRemark(@PathVariable("id") Integer id) {
        int count = customerRemarkService.deleteRemark(id);
        return count >= 1 ? Result.success() : Result.fail();
    }
}
