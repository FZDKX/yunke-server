package com.fzdkx.yunke.mapper;

import com.fzdkx.yunke.bean.dao.TPermission;
import com.fzdkx.yunke.bean.query.PermissionQuery;
import com.fzdkx.yunke.bean.vo.PermissionViewVo;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TPermission record);

    int insertSelective(TPermission record);

    TPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TPermission record);

    int updateByPrimaryKey(TPermission record);

    List<TPermission> selectByUserIdAndType(@Param("userId") Integer userId, @Param("type") String type);

    List<PermissionViewVo> selectChildren(Integer parentId);

    List<PermissionQuery> selectAll();

    int batchDelete(List<Integer> ids);

}