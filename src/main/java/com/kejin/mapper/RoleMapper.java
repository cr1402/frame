package com.kejin.mapper;

import com.kejin.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chen
 * @since 2023-05-04
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 删除角色用户关联表中的用户
     * @param roleId
     */
    void deleteRole(@Param("roleId") Integer roleId );

    /**
     * 根据用户编号获取角色id
     * @param userCode
     * @return
     */
    Integer getRoleIdByUserCode(@Param("userCode") String userCode);

    /**
     * 根据用户编号获取角色列表
     * @param userCode
     * @return
     */
    List<String> getRoleListByUserCode(@Param("userCode") String userCode);

    /**
     * 根据请求路径获取角色列表
     * @param url
     * @return
     */
    List<String> getRoleCodeByUrl(String url);
}
