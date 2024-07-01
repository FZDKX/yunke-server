package com.fzdkx.yunke.service;

import com.fzdkx.yunke.bean.dao.TDicType;
import com.fzdkx.yunke.bean.vo.DicTypeVO;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/20
 */
@Service
public interface DicTypeService {
    List<DicTypeVO> loadAllDicDataByCache();

    PageInfo<TDicType> pageList(Integer pageNum, Integer pageSize);

    TDicType getDicType(Integer id);

    int editDicType(TDicType tDicType);

    int addDicType(TDicType tDicType);

    int removeDicType(Integer id);

    List<TDicType> getAll();
}
