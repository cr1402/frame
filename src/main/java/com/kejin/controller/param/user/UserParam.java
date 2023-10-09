package com.kejin.controller.param.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserParam {

    private String userCode;

    private String userName;

    private String deptName;

    private String roleName;

    private String sex;

    private String tel;

    private String email;

    private String birthday;

    /**
     * 学历
     */
    private String education;

    /**
     * 毕业学校
     */
    private String graduationSchool;

    /**
     * 职位
     */
    private String position;
}
