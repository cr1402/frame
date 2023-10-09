package com.kejin.controller;

import com.kejin.service.impl.DeptServiceImpl;
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
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    DeptServiceImpl service;

    @GetMapping("/getDeptList")
    public Result getDeptList(){
        Result result = service.getDeptList();
        return result;
    }

}
