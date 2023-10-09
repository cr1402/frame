package com.kejin.controller.param.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListParam {

    private Integer page =1;

    private Integer pageSize =10;

    private String deptId;

    private String userCode;

    private String userName;
}
