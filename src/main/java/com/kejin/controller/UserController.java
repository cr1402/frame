package com.kejin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kejin.controller.param.user.*;
import com.kejin.entity.User;
import com.kejin.service.impl.UserServiceImpl;
import com.kejin.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chen
 * @since 2023-05-04
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping ("/login")
    public Result login(@RequestBody LoginParam loginParam){

        Result result = userService.login(loginParam);


        return result;
    }

/*    @PostMapping ("/addUser")
    public Result addUser(@RequestBody UserParam userParam){
        System.out.println(userParam.toString());
//        Result result = userService.addUser(user);

        return null;
    }*/

    @PostMapping ("/addUser")
    public Result addUser(@RequestBody JSONObject userParam){
//        JSONObject userParam1 = JSONObject.parseObject((String) userParam.get("UserParam"));
        HashMap param = (HashMap) userParam.get("UserParam");
        String paramString = JSON.toJSONString(param);
        JSONObject jsonObject = JSONObject.parseObject(paramString);
        UserParam user = JSON.toJavaObject(jsonObject, UserParam.class);
        System.out.println(user.toString());
//        Result result = userService.addUser(user);

        return null;
    }

    @GetMapping("/getUserList")
    public Result getUserList(UserListParam param){
        Result result = userService.selectUser(param);

        return result;
    }

    @PostMapping ("/updateUser")
    public Result updateUser(User user){
        Result result = userService.updateUser(user);

        return result;
    }

    @PostMapping ("/addUserDept")
    public Result updateUser(UserDeptParam param){
        Result result = userService.addUserDept(param);

        return result;
    }

    @PostMapping ("/deleteUserDept")
    public Result deleteUserDept(UserDeptParam param){
        Result result = userService.deleteUserDept(param);

        return result;
    }

    @PostMapping ("/addUserRole")
    public Result addUserRole(UserRoleParam param){
        Result result = userService.addUserRole(param);

        return result;
    }

    @PostMapping ("/deleteUserRole")
    public Result deleteUserRole(UserRoleParam param){
        Result result = userService.deleteUserRole(param);

        return result;
    }

    @GetMapping ("/getUserInfo")
    public Result geuUserInfo(HttpServletRequest request){
        Result result = userService.geuUserInfo(request);

        return result;
    }

    @PostMapping("/userCodeExist")
    public Result userCodeExist(@RequestBody JSONObject userCode){

        String code = userCode.getString("userCode");
        Result result = userService.userCodeExist(code);

        return result;
    }

}
