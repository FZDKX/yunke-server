package com.fzdkx.yunke.service.impl;

import com.fzdkx.yunke.bean.dao.TDicType;
import com.fzdkx.yunke.bean.vo.DicTypeVO;
import com.fzdkx.yunke.mapper.TDicTypeMapper;
import com.fzdkx.yunke.mapper.TDicValueMapper;
import com.fzdkx.yunke.service.DicTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/20
 */
@Service
public class DicTypeServiceImpl implements DicTypeService {

    @Resource
    private TDicTypeMapper tDicTypeMapper;
    @Resource
    private TDicValueMapper tDicValueMapper;

    @Override
    public List<DicTypeVO> loadAllDicDataByCache() {
        return tDicTypeMapper.selectAllDicDataByCache();
    }

    @Override
    public PageInfo<TDicType> pageList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TDicType> tDicTypes = tDicTypeMapper.selectList();
        return new PageInfo<>(tDicTypes);
    }

    @Override
    public TDicType getDicType(Integer id) {
        return tDicTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int editDicType(TDicType tDicType) {
        if (tDicType.getId() == null || tDicType.getTypeCode() == null || tDicType.getTypeName() == null) {
            return 0;
        }
        return tDicTypeMapper.updateByPrimaryKeySelective(tDicType);
    }

    @Override
    public int addDicType(TDicType tDicType) {
        if (tDicType.getTypeCode() == null || tDicType.getTypeName() == null) {
            return 0;
        }
        return tDicTypeMapper.insertSelective(tDicType);
    }

    @Override
    public int removeDicType(Integer id) {
        TDicType dicType = tDicTypeMapper.selectByPrimaryKey(id);
        if (dicType == null || dicType.getTypeCode() == null){
            return 0;
        }
        // 删除字典值
        tDicValueMapper.deleteByDicType(dicType.getTypeCode());
        // 删除字典类型
        return tDicTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<TDicType> getAll() {
        return tDicTypeMapper.selectList();
    }
}
