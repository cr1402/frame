package com.kejin.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.kejin.controller.param.user.UserDeptParam;
import com.kejin.controller.param.user.UserRoleParam;
import com.kejin.entity.User;
import com.kejin.entity.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chen
 * @since 2023-05-04
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 添加用户部门关联信息
     * @param param
     */
    void addUserDept(UserDeptParam param);

    /**
     * 删除用户部门关联信息
     * @param param
     */
    void deleteUserDept(UserDeptParam param);

    /**
     * 添加用户角色关联信息
     * @param param
     */
    void addUserRole(UserRoleParam param);

    /**
     * 删除用户角色关联信息
     * @param param
     */
    void deleteUserRole(UserRoleParam param);

    /**
     * 根据分页条件查询出用户信息的集合
     * @param page 分页条件
     * @param queryWrapper 其它查询条件
     * return
     */
    IPage<UserVo> getUserVoList(IPage<UserVo> page ,@Param(Constants.WRAPPER) Wrapper<User> queryWrapper);
}
