package com.fzdkx.yunke.bean.vo;

import com.fzdkx.yunke.bean.dao.TClue;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 发着呆看星
 * @create 2024/6/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ClueVO extends TClue {
    private String ownerName;
    private String activityName;
    private String appellationStr;
    private String needLoanStr;
    private String intentionStateStr;
    private String intentionProductStr;
    private String stateStr;
    private String sourceStr;
}
