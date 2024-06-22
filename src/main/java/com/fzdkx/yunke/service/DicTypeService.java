package com.fzdkx.yunke.service;

import com.fzdkx.yunke.bean.vo.DicTypeVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/20
 */
@Service
public interface DicTypeService {
    List<DicTypeVO> loadAllDicDataByCache();
}
