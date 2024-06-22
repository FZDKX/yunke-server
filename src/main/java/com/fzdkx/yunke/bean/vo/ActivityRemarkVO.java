package com.fzdkx.yunke.bean.vo;

import com.fzdkx.yunke.bean.dao.TActivityRemark;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 发着呆看星
 * @create 2024/6/19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ActivityRemarkVO extends TActivityRemark {
    // 创建人
    private String createName;
    // 编辑人
    private String editName;
    // 活动名称
    private String activityName;
}
