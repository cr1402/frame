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
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "roleId", type = IdType.AUTO)
    private Integer roleId;

    private String roleCode;

    private String roleName;

    private String roleDesc;

    private Double seqNo;

    private LocalDateTime createDate;
}
