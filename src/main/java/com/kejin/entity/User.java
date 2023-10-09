package com.kejin.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "userId", type = IdType.AUTO)
    private Integer userId;

    private String userCode;

    private String userName;

    private String password;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 停用时间
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDateTime pauseDate;

    /**
     * 排序编号
     */
    private Double seqNo =1.0;

    private String sex;

    private String tel;

    private String email;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private LocalDateTime birthday;

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
