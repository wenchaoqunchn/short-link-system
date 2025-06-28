package com.wenchaoqun.shortlink.admin.dao.entity;

/**
 * 短链接分组实体
 */

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class GroupDO {

    /**
     * ID
     */
    private Long id;

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 分组名称
     */
    private String name;

    /**
     * 创建分组用户
     */
    private String username;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 删除标识
     */
    private Boolean delFlag;

}
