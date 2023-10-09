package com.kejin.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {

    private String userName;

    private String userCode;

    private String roleName;

    private String deptName;
}
