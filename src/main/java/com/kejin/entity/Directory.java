package com.kejin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author chen
 * @since 2023-06-08
 */
@Getter
@Setter
public class Directory implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "directoryId", type = IdType.AUTO)
    private Integer directoryId;

    /**
     * 目录名称
     */
    private String directoryName;

    /**
     * 目录地址
     */
    private String directoryAddress;

    /**
     * 目录图标
     */
    private String directoryIcon;

    /**
     * 状态
     */
    private Integer state;

    private LocalDateTime createDate;

    private Integer seqNo;
}
