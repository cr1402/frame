package com.kejin.controller;

import com.kejin.service.impl.RoleServiceImpl;
import com.kejin.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chen
 * @since 2023-05-04
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleServiceImpl roleService;

    @GetMapping("/getAllRoleList")
    public Result getAllRoleList(){
        return roleService.getAllRoleList();
    }

}
