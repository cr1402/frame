package com.kejin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kejin.entity.Role;
import com.kejin.utils.Result;
import org.springframework.security.access.ConfigAttribute;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chen
 * @since 2023-05-04
 */
public interface IRoleService extends IService<Role> {

    /**
     * 新增角色
     * @param role
     * @return
     */
    Result addRole(Role role);

    /**
     * 修改角色
     * @param role
     * @return
     */
    Result updateRole(Role role);

    /**
     * 删除角色
     * @param role
     * @return
     */
    Result deleteRole(Role role);

    /**
     * 根据分页条件查询角色
     * @param current
     * @param size
     * @return
     */
    Result selectRole(int current,int size);

    /**
     * 根据用户编号获取角色id
     * @param userCode
     * @return
     */
    Integer getRoleIdByUserCode(String userCode);

    /**
     * 根据用户编号获取角色列表
     * @param userCode
     * @return
     */
    List<String> getRoleListByUserCode(String userCode);

    /**
     * 获取所有的角色编号
     * @return
     */
    List<String> getAllRoleCode();

    /**
     * 根据请求地址获取角色
     * @param url
     * @return
     */
    Collection<ConfigAttribute> getRoleCodeByUrl(String url);

    /**
     * 获取所有角色信息
     * @return
     */
    Result getAllRoleList();
}
