package com.kejin.controller.param.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDeptParam {

    private Integer userId;

    private Integer deptId;
}
