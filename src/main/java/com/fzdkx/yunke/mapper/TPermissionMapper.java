package com.fzdkx.yunke.mapper;

import com.fzdkx.yunke.bean.dao.TPermission;
import com.fzdkx.yunke.bean.query.PermissionQuery;
import com.fzdkx.yunke.bean.vo.PermissionListVo;
import com.fzdkx.yunke.bean.vo.PermissionVO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TPermission record);

    int insertSelective(TPermission record);

    TPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TPermission record);

    int updateByPrimaryKey(TPermission record);

    List<PermissionVO> selectMenuByUserId(@Param("userId") Integer userId);

    List<PermissionListVo> selectChildren(Integer parentId);

    List<PermissionQuery> selectAll();

    int batchDelete(List<Integer> ids);

    List<TPermission> selectButtonByUserId(@Param("id") Integer id);
}