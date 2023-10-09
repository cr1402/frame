package com.kejin.controller.param.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleParam {

    private Integer userId;

    private Integer roleId;
}
