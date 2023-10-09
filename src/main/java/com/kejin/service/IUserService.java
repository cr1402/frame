package com.kejin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kejin.controller.param.user.LoginParam;
import com.kejin.controller.param.user.UserDeptParam;
import com.kejin.controller.param.user.UserListParam;
import com.kejin.controller.param.user.UserRoleParam;
import com.kejin.entity.User;
import com.kejin.utils.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chen
 * @since 2023-05-04
 */
public interface IUserService extends IService<User> {

    /**
     * 登录
     * @param loginParam 登录的参数
     * @return
     */
    Result login(LoginParam loginParam);

    /**
     * 新增用户
     * @param user 新增的用户信息
     * @return
     */
    Result addUser(User user);

    /**
     * 根据条件分页查询用户
     * @param param 页数
     * @return
     */
    Result selectUser(UserListParam param);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    Result updateUser(User user);

    /**
     * 添加用户的部门
     * @param param
     * @return
     */
    Result addUserDept(UserDeptParam param);

    /**
     * 删除用户的部门
     * @param param
     * @return
     */
    Result deleteUserDept(UserDeptParam param);

    /**
     * 添加用户的角色
     * @param param
     * @return
     */
    Result addUserRole(UserRoleParam param);

    /**
     * 删除用户的角色
     * @param param
     * @return
     */
    Result deleteUserRole(UserRoleParam param);

    /**
     * 根据token获取用户信息
     * @param request
     * @return
     */
    Result geuUserInfo(HttpServletRequest request);

    /**
     * 判断userCode是否已经存在
     * @param userCode
     * @return
     */
    Result userCodeExist(String userCode);
}
