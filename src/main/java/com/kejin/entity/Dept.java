package com.kejin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author chen
 * @since 2023-05-04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "deptId", type = IdType.AUTO)
    private Integer deptId;

    private String deptCode;

    private String deptName;

    /**
     * 上级部门
     */
    private Integer fatherDeptId;

    private Double seqNo;

//    private Integer childDept;

    private LocalDateTime createDate;

}
