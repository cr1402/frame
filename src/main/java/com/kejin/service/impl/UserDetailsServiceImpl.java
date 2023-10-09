package com.kejin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kejin.entity.LoginUser;
import com.kejin.entity.User;
import com.kejin.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getOne(Wrappers.lambdaQuery(User.class).eq(User::getUserCode, username));

        if (CommonUtils.objectIsNull(user)){
            throw new RuntimeException("该用户不存在");
        }

        LoginUser loginUser =new LoginUser(user,roleService.getRoleListByUserCode(username));
        return loginUser;
    }

    public UserDetails loadUserByUsername(Integer userId) throws UsernameNotFoundException {
        User user = userService.getOne(Wrappers.lambdaQuery(User.class).eq(User::getUserId, userId));

        if (CommonUtils.objectIsNull(user)){
            throw new RuntimeException("该用户不存在");
        }

        LoginUser loginUser =new LoginUser(user,roleService.getRoleListByUserCode(user.getUserCode()));
        return loginUser;
    }
}
