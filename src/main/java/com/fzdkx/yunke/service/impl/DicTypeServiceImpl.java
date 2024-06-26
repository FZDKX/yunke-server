package com.fzdkx.yunke.service.impl;

import com.fzdkx.yunke.bean.vo.DicTypeVO;
import com.fzdkx.yunke.mapper.TDicTypeMapper;
import com.fzdkx.yunke.service.DicTypeService;
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

    @Override
    public List<DicTypeVO> loadAllDicDataByCache() {
        return tDicTypeMapper.selectAllDicDataByCache();
    }
}
