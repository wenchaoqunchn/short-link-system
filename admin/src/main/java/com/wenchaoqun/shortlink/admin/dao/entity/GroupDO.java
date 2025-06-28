package com.wenchaoqun.shortlink.admin.dao.entity;

/**
 * 短链接分组实体
 */

import com.baomidou.mybatisplus.annotation.TableName;
import com.wenchaoqun.shortlink.admin.common.database.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("t_group")
public class GroupDO extends BaseDO {

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
    private String groupName;

    /**
     * 创建分组用户
     */
    private String username;


}
