package com.fzdkx.yunke.bean.vo;

import com.fzdkx.yunke.bean.dao.TActivity;
import com.fzdkx.yunke.bean.dao.TDicValue;
import com.fzdkx.yunke.bean.dao.TProduct;
import com.fzdkx.yunke.bean.dao.TUser;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/21
 */
@Data
@Builder
public class DicVO {
    private List<TDicValue> appellationList;
    private List<TDicValue> needLoanList;
    private List<TDicValue> intentionStateList;
    private List<TProduct> intentionProductList;
    private List<TDicValue> stateList;
    private List<TDicValue> sourceList;
    private List<TUser> ownerList;
    private List<TActivity> activityList;
    private List<TDicValue> noteWayList;
    private List<TDicValue> stageList;
}
