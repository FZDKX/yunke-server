package com.fzdkx.yunke.mapper;

import com.fzdkx.yunke.bean.dao.TUser;
import com.fzdkx.yunke.bean.vo.UserDetailsVO;
import com.fzdkx.yunke.bean.vo.UserVO;

import java.util.List;

public interface TUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);

    TUser selectByUsername(String username);

    List<UserVO> selectUserList();

    UserDetailsVO selectUserDetails(Integer id);

    int batchDelete(List<Integer> ids);

    UserVO selectUserAndRole(Integer id);

}