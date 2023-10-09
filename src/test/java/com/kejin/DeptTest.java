package com.kejin;

import com.kejin.entity.Dept;
import com.kejin.mapper.DeptMapper;
import com.kejin.service.impl.DeptServiceImpl;
import com.kejin.utils.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DeptTest {

    @Autowired
    DeptServiceImpl service;

    @Autowired
    DeptMapper deptMapper;

    @Test
    void test1() {
/*        Dept dept =new Dept(3,"Test2","测试部门2",2,1.0,0);
        Result result = service.addDept(dept);
        System.out.println(result);*/
        deptMapper.deleteDept(-1);
    }

    @Test
    void test2() {
        Result result = service.getDeptList();

        System.out.println(result.toString());
    }

    @Test
    void test3() {
        Dept dept =new Dept();
        dept.setDeptId(2);
        dept.setFatherDeptId(0);
        Result result = service.updateDept(dept);
        System.out.println(result);
    }

    @Test
    void test4() {
        Dept dept =new Dept();
        dept.setDeptId(3);

        Result result = service.deleteDept(dept);
        System.out.println(result);
    }

    @Test
    void test5() {
        service.getChildDeptIds(4);
    }
}
