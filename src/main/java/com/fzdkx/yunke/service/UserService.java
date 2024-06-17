package com.fzdkx.yunke.service;

import com.fzdkx.yunke.bean.dao.TUser;
import com.fzdkx.yunke.bean.vo.EditAddUserVO;
import com.fzdkx.yunke.bean.vo.IdListVO;
import com.fzdkx.yunke.bean.vo.UserDetailsVO;
import com.fzdkx.yunke.bean.vo.UserVO;
import com.fzdkx.yunke.common.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author 发着呆看星
 * @create 2024/6/7
 */
public interface UserService extends UserDetailsService {
    Result<PageInfo<UserVO>> getListByPage(Integer pageSize, Integer curPage);

    Result<UserDetailsVO> selectUserDetailsById(Integer id);

    Result<UserVO> selectUserById(Integer id);

    Result<String> addUser(EditAddUserVO tUser, Authentication authentication);

    Result<String> editUser(EditAddUserVO tUser, Authentication authentication);

    Result<String> deleteUserById(Integer id);

    Result<String> batchDelete(IdListVO ids);
}
