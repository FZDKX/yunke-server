package com.fzdkx.yunke.service;

import com.fzdkx.yunke.bean.dao.TDicValue;
import com.fzdkx.yunke.bean.vo.DicValueVO;
import com.github.pagehelper.PageInfo;

/**
 * @author 发着呆看星
 * @create 2024/6/26
 */
public interface DicValueService {
    PageInfo<DicValueVO> pageList(Integer pageNum, Integer pageSize);

    DicValueVO getDicValue(Integer id);

    int editDicValue(TDicValue tDicValue);

    int addDicValue(TDicValue tDicValue);

    int removeDicValue(Integer id);
}
