package com.kejin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 子菜单表
 * </p>
 *
 * @author chen
 * @since 2023-06-08
 */
@Getter
@Setter
public class Submenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "submenuId", type = IdType.AUTO)
    private Integer submenuId;

    private String submenuName;

    /**
     * 上级目录id
     */
    private Integer directoryId;

    /**
     * 组件路径
     */
    private String submenuAddress;

    private LocalDateTime createDate;

    private Integer state;

    private Integer seqNo;

    /**
     * 是否缓存，0表示否，1表示是
     */
    private Integer isCache;
}
