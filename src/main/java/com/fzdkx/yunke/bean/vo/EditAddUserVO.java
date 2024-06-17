package com.fzdkx.yunke.bean.vo;

import com.fzdkx.yunke.bean.dao.TUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/17
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class EditAddUserVO extends TUser {
    private List<Integer> roleIds;
}
