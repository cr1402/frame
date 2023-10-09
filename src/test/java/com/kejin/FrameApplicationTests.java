package com.kejin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kejin.controller.param.user.LoginParam;
import com.kejin.controller.param.user.UserDeptParam;
import com.kejin.controller.param.user.UserListParam;
import com.kejin.entity.User;
import com.kejin.service.impl.UserServiceImpl;
import com.kejin.utils.JWTUtils;
import com.kejin.utils.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;

@SpringBootTest
class FrameApplicationTests {

    @Autowired
    UserServiceImpl service;

    @Autowired
    private JWTUtils jwtUtils;

    @Test
    void contextLoads() {
        Result result = service.login(new LoginParam("admin", "123456"));
        System.out.println(result);
    }

    @Test
    void test1() {
        User user = new User(null,"TestUser","测试用户","123456", null,null,null,"","","",null,"","","");
        Result result = service.addUser(user);
        System.out.println(result);
    }

    @Test
    void test2() {
        Result result = service.selectUser(new UserListParam(1,20,"4","ad",null));
        Page<User> data = (Page<User>) result.getResult();
        System.out.println(data.getRecords());
    }

    @Test
    void test3() {
        User user = new User(1,"admin","管理员","123456", null,null,1.0,"","","",null,"","","");
        Result result = service.updateUser(user);
        System.out.println(result);
    }

    @Test
    void test4(){
        UserDeptParam param =new UserDeptParam(1,1);
        Result result = service.addUserDept(param);
        System.out.println(result);
    }

    @Test
    void test5(){
        UserDeptParam param =new UserDeptParam(1,1);
        Result result = service.deleteUserDept(param);
        System.out.println(result);
    }

    @Test
    void test6(){
        Calendar old = Calendar.getInstance();
        old.set(2023,5,10,21,0,00);
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        System.out.println(now.get(Calendar.MINUTE) - old.get(Calendar.MINUTE));
    }

    @Test
    void test7(){
        String token = jwtUtils.getToken("1");
        System.out.println(token);
        boolean verify = jwtUtils.verify(token);

        System.out.println(verify);
    }



}
