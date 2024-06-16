package com.fzdkx.yunke.service;

import com.fzdkx.yunke.bean.dao.TUser;
import com.fzdkx.yunke.bean.vo.IdListVO;
import com.fzdkx.yunke.bean.vo.UserDetailsVO;
import com.fzdkx.yunke.common.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author 发着呆看星
 * @create 2024/6/7
 */
public interface UserService extends UserDetailsService {
    Result<PageInfo<TUser>> getListByPage(Integer pageSize, Integer curPage);

    Result<UserDetailsVO> selectUserDetailsById(Integer id);

    Result<TUser> selectUserById(Integer id);

    Result<String> addUser(TUser tUser, Authentication authentication);

    Result<String> editUser(TUser tUser, Authentication authentication);

    Result<String> deleteUserById(Integer id);

    Result<String> batchDelete(IdListVO ids);
}
