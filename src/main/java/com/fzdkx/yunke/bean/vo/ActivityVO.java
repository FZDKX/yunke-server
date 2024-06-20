package com.fzdkx.yunke.bean.vo;

import com.fzdkx.yunke.bean.dao.TActivity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 发着呆看星
 * @create 2024/6/18
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ActivityVO extends TActivity {
    // 负责人
    private String ownerName;
    // 创建人
    private String createName;
    // 编辑人
    private String editName;
}
