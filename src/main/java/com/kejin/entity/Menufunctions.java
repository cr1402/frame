package com.kejin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 菜单功能表
 * </p>
 *
 * @author chen
 * @since 2023-06-16
 */
@Getter
@Setter
public class Menufunctions implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "mfId", type = IdType.AUTO)
    private Integer mfId;

    /**
     * 菜单功能名称
     */
    private String mfName;

    /**
     * 上级菜单
     */
    private Integer submenuId;

    /**
     * 请求路径
     */
    private String requestUrl;

    private LocalDateTime createDate;

    private Integer state;

    private Integer seqNo;
}
