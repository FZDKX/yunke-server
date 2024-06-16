package com.fzdkx.yunke.bean.dao;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/7
 */
@Data
public class LoginUser implements UserDetails {

    private TUser tUser;

    private List<GrantedAuthority> authorities;

    // 角色集合
    private List<TRole> tRoles;

    // 菜单权限集合
    private List<TPermission> tMenuPermissions;

    // 操作(按钮)权限集合
    private List<TPermission> tButtonPermissions;

    public LoginUser(TUser tUser, List<TRole> tRoles, List<TPermission> tMenuPermissions, List<TPermission> tButtonPermissions) {
        this.tUser = tUser;
        this.tRoles = tRoles;
        this.tMenuPermissions = tMenuPermissions;
        this.tButtonPermissions = tButtonPermissions;
        buildAuthorities();
    }

    private void buildAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        if (!ObjectUtils.isEmpty(tRoles)) {
            tRoles.forEach(role -> list.add(new SimpleGrantedAuthority(role.getRole())));
        }
        if (!ObjectUtils.isEmpty(tButtonPermissions)) {
            tButtonPermissions.forEach(button -> list.add(new SimpleGrantedAuthority(button.getCode())));
        }
        this.authorities = list;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return tUser.getPassword();
    }

    @Override
    public String getUsername() {
        return tUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return tUser.getAccountEnabled() == 1;
    }
}
