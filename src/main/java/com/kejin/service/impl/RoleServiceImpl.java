package com.kejin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kejin.entity.Role;
import com.kejin.mapper.RoleMapper;
import com.kejin.service.IRoleService;
import com.kejin.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chen
 * @since 2023-05-04
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    RoleMapper roleMapper;


    @Override
    public Result addRole(Role role) {
        Result result =new Result();

        long count = count(Wrappers.lambdaQuery(Role.class).
                select(Role::getRoleId).
                eq(Role::getRoleCode, role.getRoleCode()));

        if (count>0){
            result.setCode(441).setMessage("该角色编号已存在，请修改");
        }else {
            save(role);
            result.setCode(200).setMessage("新增角色："+role.getRoleName()+"成功");

        }

        return result;
    }


    @Override
    public Result updateRole(Role role) {
        Result result =new Result();

        long count = count(Wrappers.lambdaQuery(Role.class).
                select(Role::getRoleId)
                .eq(Role::getRoleCode, role.getRoleCode())
                .ne(Role::getRoleId, role.getRoleId()));

        if (count>0){
            result.setCode(-1).setMessage("该角色编号已存在，请修改");
        }else {
            updateById(role);
            result.setCode(200).setMessage("修改角色："+role.getRoleName()+"成功");

        }


        return result;
    }


    @Override
    public Result deleteRole(Role role) {
        Result result =new Result();

        removeById(role);

        roleMapper.deleteRole(role.getRoleId());

        result.setCode(200).setMessage("删除成功");

        return result;
    }


    @Override
    public Result selectRole(int current, int size) {
        Result result =new Result();

        Page<Role> page =new Page(current,size);

        page = this.baseMapper.selectPage(page, Wrappers.lambdaQuery(Role.class));

        result.setCode(200).setMessage("查询成功").setResult(page);


        return result;
    }

    @Override
    public Integer getRoleIdByUserCode(String userCode) {
        return roleMapper.getRoleIdByUserCode(userCode);
    }

    @Override
    public List<String> getRoleListByUserCode(String userCode) {
        return roleMapper.getRoleListByUserCode(userCode);
    }

    @Override
    public List<String> getAllRoleCode() {
        LambdaQueryWrapper<Role> wrapper = Wrappers.lambdaQuery(Role.class).select(Role::getRoleCode);

        List<Object> list =listObjs(wrapper);
        List<String> roleList = (List<String>)(List) list;

        return roleList;
    }

    @Override
    public Collection<ConfigAttribute> getRoleCodeByUrl(String url) {

        List<String> roleCodes = roleMapper.getRoleCodeByUrl(url);

        Collection<ConfigAttribute> roles = (Collection<ConfigAttribute>)(Collection)roleCodes;

        return roles;
    }

    @Override
    public Result getAllRoleList() {
        Result result =new Result();

        List<Role> list = list();

        result.setCode(200).setMessage("查询成功").setResult(list);

        return result;
    }
}

