package com.fzdkx.yunke.bean.vo;

import com.fzdkx.yunke.bean.dao.TClueRemark;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 发着呆看星
 * @create 2024/6/22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ClueRemarkVO extends TClueRemark {
    private String createName;
    private String editName;
    private String noteWayName;
}
