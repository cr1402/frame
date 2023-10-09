package com.kejin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kejin.controller.param.user.LoginParam;
import com.kejin.controller.param.user.UserDeptParam;
import com.kejin.controller.param.user.UserListParam;
import com.kejin.controller.param.user.UserRoleParam;
import com.kejin.entity.LoginUser;
import com.kejin.entity.User;
import com.kejin.mapper.UserMapper;
import com.kejin.service.IUserService;
import com.kejin.utils.CommonUtils;
import com.kejin.utils.JWTUtils;
import com.kejin.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chen
 * @since 2023-05-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DeptServiceImpl deptService;

    @Override
    public Result login(LoginParam loginParam){
        Result result =new Result();
        Map<String,Object> map =new HashMap();

        //使用Authentication的实现类
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginParam.getUserCode(), loginParam.getPassword());

        //手动调用方法去认证 他会自动调用UserDetailsService查 然后对比啥的
        Authentication authenticate = authenticationManager.authenticate(authentication);
        //说明输入错误
        if(Objects.isNull(authenticate)){
            throw new  RuntimeException("用户名或密码错误");
        }
        //拿到用户信息 然后生成jwt返回给前端，并且将用户的信息存入redis

        LoginUser loginUser = (LoginUser)authenticate.getPrincipal();

        String token = jwtUtils.getToken(loginUser.getUser().getUserId().toString());


        map.put("data",loginUser);
        map.put("token",token);

        result.setCode(200).setMessage("用户"+loginUser.getUser().getUserName()+"登录成功").setResult(map);

        return result;
    }

    @Override
    public Result addUser(User user){
        Result result =new Result();
        String password =user.getPassword();
        long count = count(Wrappers.lambdaQuery(User.class).
                select(User::getUserId).
                eq(User::getUserCode, user.getUserCode()));

        if (count>0){
            result.setCode(-1).setMessage("该用户账号已存在，请修改");
        }else {
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            save(user);
            result.setCode(200).setMessage("用户"+user.getUserName()+"注册成功");
        }

        return result;
    }

    @Override
    public Result selectUser(UserListParam param){

        Result result =new Result();

        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.isNull("pauseDate")
        .like(!CommonUtils.strIsNull(param.getUserCode()),"userCode",param.getUserCode())
        .like(!CommonUtils.strIsNull(param.getUserName()),"userName",param.getUserName());

        String deptId = param.getDeptId();

        if(!CommonUtils.strIsNull(deptId) && deptId.length()==1){
            Integer deptNum = Integer.valueOf(deptId);

            List<String> deptIds = deptService.getChildDeptIds(deptNum);
            deptIds.add(deptId);

            wrapper.in("d.deptId",deptIds);
        }
        if (!CommonUtils.strIsNull(deptId) && deptId.length()>1){
            int i = deptId.indexOf("-");
            String substring = deptId.substring(i+1);
            Integer deptNum = Integer.valueOf(substring);

            wrapper.eq("d.deptId",deptNum);
        }


        Page page = (Page) userMapper.getUserVoList(new Page(param.getPage(),param.getPageSize()), wrapper);


        result.setCode(200).setMessage("查询成功").setResult(page);

        return result;
    }

    @Override
    public Result updateUser(User user){
        Result result =new Result();
        String password =user.getPassword();
        if (!CommonUtils.strIsNull(password)){
            user.setPassword(new BCryptPasswordEncoder().encode(password));
        }

        update(user, Wrappers.lambdaUpdate(User.class)
                .eq(User::getUserId, user.getUserId()).or()
                .eq(User::getUserCode, user.getUserCode()));

        result.setCode(200).setMessage("修改成功");

        return result;
    }

    @Override
    public Result addUserDept(UserDeptParam param) {
        Result result =new Result();

        userMapper.addUserDept(param);
        result.setCode(200).setMessage("添加成功");

        return result;
    }

    @Override
    public Result deleteUserDept(UserDeptParam param) {
        Result result =new Result();

        userMapper.deleteUserDept(param);

        result.setCode(200).setMessage("删除成功");

        return result;
    }

    @Override
    public Result addUserRole(UserRoleParam param) {
        Result result =new Result();

        userMapper.addUserRole(param);

        result.setCode(200).setMessage("新增成功");

        return result;
    }

    @Override
    public Result deleteUserRole(UserRoleParam param) {
        Result result =new Result();

        userMapper.deleteUserRole(param);

        result.setCode(200).setMessage("删除成功");

        return result;
    }

    @Override
    public Result geuUserInfo(HttpServletRequest request){
        Result result =new Result();

        String authorization = request.getHeader("authorization");

        String tokenId = jwtUtils.getTokenId(authorization);

        User user = getById(tokenId);

        result.setCode(200).setMessage("已获取用户"+user.getUserName()+"的信息").setResult(user);

        return result;
    }

    @Override
    public Result userCodeExist(String userCode) {
        Result result =new Result();

        LambdaUpdateWrapper<User> eq = Wrappers.lambdaUpdate(User.class).eq(User::getUserCode, userCode);

        long count = count(eq);

        if (count>=1){
            throw new RuntimeException("该用户编号已存在！");
        }

        result.setCode(200).setMessage("该用户编号可以正常使用");

        return result;
    }


}
