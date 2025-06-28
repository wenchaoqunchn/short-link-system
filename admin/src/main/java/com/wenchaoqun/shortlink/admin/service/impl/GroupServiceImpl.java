package com.wenchaoqun.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenchaoqun.shortlink.admin.common.convention.exception.ClientException;
import com.wenchaoqun.shortlink.admin.dao.entity.GroupDO;
import com.wenchaoqun.shortlink.admin.dao.mapper.GroupMapper;
import com.wenchaoqun.shortlink.admin.service.GroupService;
import com.wenchaoqun.shortlink.admin.util.RandomGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService  {

    /**
     * 新增短链接分组
     * @param groupName
     */
    @Override
    public void addGroup(String groupName){
        String gid;
        do{
            gid = RandomGenerator.generateRandomId();
        }while(isGroupIdExists(gid));
        GroupDO groupDO = GroupDO.builder()
                .gid(gid)
                .groupName(groupName)
                .build();
        int inserted = baseMapper.insert(groupDO);
        if(inserted < 1){
            throw new ClientException("新增分组失败");
        }
    }

    /**
     * 检查分组ID是否已存在
     *
     * @param gid 分组ID
     * @return true如果分组ID已存在，false如果不存在
     */
    private boolean isGroupIdExists(String gid) {
        Wrapper<GroupDO> wrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGid, gid);
        return baseMapper.selectCount(wrapper) > 0;
    }
}
