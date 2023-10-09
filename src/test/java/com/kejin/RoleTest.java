package com.kejin;

import com.kejin.mapper.RoleMapper;
import com.kejin.service.impl.RoleServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RoleTest {

    @Autowired
    RoleServiceImpl service;

    @Autowired
    RoleMapper Mapper;

    @Test
    void test1() {
        service.getAllRoleCode();
    }

    @Test
    void test2() {

    }

    @Test
    void test3() {

    }

    @Test
    void test4() {

    }
}
