package com.fzdkx.yunke.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.fzdkx.yunke.bean.dao.LoginUser;
import com.fzdkx.yunke.bean.dao.TClue;
import com.fzdkx.yunke.bean.dao.TCustomer;
import com.fzdkx.yunke.bean.vo.CustomerExcel;
import com.fzdkx.yunke.bean.vo.CustomerVO;
import com.fzdkx.yunke.common.ExcelConstant;
import com.fzdkx.yunke.mapper.TClueMapper;
import com.fzdkx.yunke.mapper.TCustomerMapper;
import com.fzdkx.yunke.mapper.TCustomerRemarkMapper;
import com.fzdkx.yunke.service.CustomerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/22
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private TCustomerMapper tCustomerMapper;
    @Resource
    private TCustomerRemarkMapper tCustomerRemarkMapper;
    @Resource
    private TClueMapper tClueMapper;

    @Override
    @Transactional
    public int save(TCustomer tCustomer) {
        if (tCustomer.getClueId() == null) {
            return 0;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 修改线索信息
        TClue tClue = new TClue();
        // 设置id
        tClue.setId(tCustomer.getClueId());
        // 设置线索状态
        tClue.setState(-1);
        // 修改信息
        tClueMapper.updateByPrimaryKeySelective(tClue);

        // 新增客户
        tCustomer.setCreateBy(loginUser.getTUser().getId());
        tCustomer.setCreateTime(new Date());
        return tCustomerMapper.insertSelective(tCustomer);
    }

    @Override
    public PageInfo<CustomerVO> pageQuery(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CustomerVO> customerVOList = tCustomerMapper.selectList();
        return new PageInfo<>(customerVOList);
    }

    @Override
    public TCustomer getCustomer(Integer id) {
        return tCustomerMapper.selectByPrimaryKey(id);
    }

    @Override
    public void editCustomer(TCustomer tCustomer) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        tCustomer.setEditBy(loginUser.getTUser().getId());
        tCustomer.setEditTime(new Date());
        tCustomerMapper.updateByPrimaryKeySelective(tCustomer);
    }

    @Override
    public int remove(Integer id) {
        // 删除所有备注
        tCustomerRemarkMapper.deleteByCustomerId(id);
        // 删除客户信息
        return tCustomerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<CustomerExcel> exportAll() {
        // 查出所有客户数据
        return tCustomerMapper.selectExcel(new ArrayList<>());
    }

    @Override
    public List<CustomerExcel> exportSelect(List<Integer> ids) {
        if (ids == null) {
            return null;
        }
        // 查出指定客户数据
        return tCustomerMapper.selectExcel(ids);
    }

    @Override
    public void exportExcel(HttpServletResponse response, List<CustomerExcel> customerExcels) throws IOException {
        // 写出excel
        // 设置内容格式为 二进制流
        response.setContentType("application/octet-stream");
        // 设置编码
        response.setCharacterEncoding("utf-8");
        // 使前端可以获取到 Content-Disposition 中的值
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        // 设置头，告诉浏览器，这是一个文件，不需要解析
        // 设置文件名 + 后缀，如果文件名有中文，需要 URLEncoder.encode 进行编码
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode(ExcelConstant.EXCEL_FILE_NAME, StandardCharsets.UTF_8) + ".xlsx");
        EasyExcel.write(response.getOutputStream(), CustomerExcel.class)
                .sheet("客户信息")
                .doWrite(customerExcels);
    }
}
