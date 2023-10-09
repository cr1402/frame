package com.kejin.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptVo {

    private Integer deptId;

    private String deptName;

    private String key;

    private List<DeptVo> children;

}
