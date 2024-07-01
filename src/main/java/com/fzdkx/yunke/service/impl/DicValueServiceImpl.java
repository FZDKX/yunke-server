package com.fzdkx.yunke.service.impl;

import com.fzdkx.yunke.bean.dao.TDicValue;
import com.fzdkx.yunke.bean.vo.DicValueVO;
import com.fzdkx.yunke.mapper.TDicValueMapper;
import com.fzdkx.yunke.service.DicValueService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/26
 */
@Service
public class DicValueServiceImpl implements DicValueService {
    @Resource
    private TDicValueMapper tDicValueMapper;

    @Override
    public PageInfo<DicValueVO> pageList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<DicValueVO> dicValueVOList = tDicValueMapper.selectList(pageNum, pageSize);
        return new PageInfo<>(dicValueVOList);
    }

    @Override
    public DicValueVO getDicValue(Integer id) {
        return tDicValueMapper.selectById(id);
    }

    @Override
    public int editDicValue(TDicValue tDicValue) {
        if (tDicValue.getId() == null || tDicValue.getTypeCode() == null || tDicValue.getTypeValue() == null) {
            return 0;
        }
        return tDicValueMapper.updateByPrimaryKeySelective(tDicValue);
    }

    @Override
    public int addDicValue(TDicValue tDicValue) {
        if (tDicValue.getTypeCode() == null || tDicValue.getTypeValue() == null) {
            return 0;
        }
        return tDicValueMapper.insertSelective(tDicValue);
    }

    @Override
    public int removeDicValue(Integer id) {
        // 删除字典类型
        return tDicValueMapper.deleteByPrimaryKey(id);
    }
}
