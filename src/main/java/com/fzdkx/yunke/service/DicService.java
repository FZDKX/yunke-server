package com.fzdkx.yunke.service;

import com.fzdkx.yunke.bean.vo.DicVO;

/**
 * @author 发着呆看星
 * @create 2024/6/21
 */
public interface DicService {
    DicVO getClueDic();

    DicVO getRemarkDic();

    DicVO getCustomerDic();

    DicVO getStageList();
}
