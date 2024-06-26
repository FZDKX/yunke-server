package com.fzdkx.yunke.service.impl;

import com.fzdkx.yunke.YunKeApplication;
import com.fzdkx.yunke.bean.dao.TActivity;
import com.fzdkx.yunke.bean.dao.TDicValue;
import com.fzdkx.yunke.bean.dao.TProduct;
import com.fzdkx.yunke.bean.dao.TUser;
import com.fzdkx.yunke.bean.vo.DicVO;
import com.fzdkx.yunke.common.DictionaryConstant;
import com.fzdkx.yunke.service.DicService;
import com.fzdkx.yunke.service.UserService;
import com.fzdkx.yunke.task.CacheTask;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author 发着呆看星
 * @create 2024/6/21
 */
@Component
public class DicServiceImpl implements DicService {
    @Resource
    private CacheTask cacheTask;

    @Resource
    private UserService userService;

    @Override
    public DicVO getClueDic() {
        Map<String, Object> map = YunKeApplication.cacheMap;
        DicVO dicVO;
        // 从数据中获取
        if (map.isEmpty()) {
            cacheTask.setCacheData();
        }
        dicVO = DicVO.builder()
                .activityList((List<TActivity>) map.get(DictionaryConstant.ACTIVITY))
                .appellationList((List<TDicValue>) map.get(DictionaryConstant.APPELLATION))
                .intentionStateList((List<TDicValue>) map.get(DictionaryConstant.INTENTION_STATE))
                .needLoanList((List<TDicValue>) map.get(DictionaryConstant.NEED_LOAN))
                .sourceList((List<TDicValue>) map.get(DictionaryConstant.SOURCE))
                .stateList((List<TDicValue>) map.get(DictionaryConstant.CLUE_STATE))
                .intentionProductList((List<TProduct>) map.get(DictionaryConstant.INTENTION_PRODUCT))
                .build();
        List<TUser> listResult = userService.queryAllOwner();
        dicVO.setOwnerList(listResult);
        return dicVO;
    }

    @Override
    public DicVO getRemarkDic() {
        Map<String, Object> map = YunKeApplication.cacheMap;
        DicVO dicVO;
        // 从数据中获取
        if (map.isEmpty()) {
            cacheTask.setCacheData();
        }
        dicVO = DicVO.builder()
                .noteWayList((List<TDicValue>) map.get(DictionaryConstant.NOTE_WAY))
                .build();
        return dicVO;
    }

    @Override
    public DicVO getCustomerDic() {
        Map<String, Object> map = YunKeApplication.cacheMap;
        DicVO dicVO;
        // 从数据中获取
        if (map.isEmpty()) {
            cacheTask.setCacheData();
        }
        dicVO = DicVO.builder()
                .intentionProductList((List<TProduct>) map.get(DictionaryConstant.INTENTION_PRODUCT))
                .intentionStateList((List<TDicValue>) map.get(DictionaryConstant.INTENTION_STATE))
                .build();
        return dicVO;
    }

    @Override
    public DicVO getStageList() {
        Map<String, Object> map = YunKeApplication.cacheMap;
        DicVO dicVO;
        // 从数据中获取
        if (map.isEmpty()) {
            cacheTask.setCacheData();
        }
        dicVO = DicVO.builder()
                .stageList((List<TDicValue>) map.get(DictionaryConstant.STAGE))
                .build();
        return dicVO;
    }


}
