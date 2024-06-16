package com.fzdkx.yunke.bean.vo;

import com.fzdkx.yunke.bean.dao.TUser;
import lombok.Data;

/**
 * @author 发着呆看星
 * @create 2024/6/13
 */
@Data
public class UserDetailsVO extends TUser {
    private String createName;
    private String editName;
}
