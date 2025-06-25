package com.wenchaoqun.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenchaoqun.shortlink.admin.common.constant.RedisCacheConstant;
import com.wenchaoqun.shortlink.admin.common.convention.exception.ClientException;
import com.wenchaoqun.shortlink.admin.common.enums.UserErrorCodeEnum;
import com.wenchaoqun.shortlink.admin.dao.entity.UserDO;
import com.wenchaoqun.shortlink.admin.dao.mapper.UserMapper;
import com.wenchaoqun.shortlink.admin.dto.req.UserLoginReqDTO;
import com.wenchaoqun.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.wenchaoqun.shortlink.admin.dto.req.UserUpdateReqDTO;
import com.wenchaoqun.shortlink.admin.dto.resp.UserLoginRespDTO;
import com.wenchaoqun.shortlink.admin.dto.resp.UserRespDTO;
import com.wenchaoqun.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static com.wenchaoqun.shortlink.admin.common.enums.UserErrorCodeEnum.*;

/**
 * 用户接口实现层
 */

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {
    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;
    private final RedissonClient redissonClient;
    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 用户返回实体
     */
    @Override
    public UserRespDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> wrapper = Wrappers.lambdaQuery(UserDO.class).eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(wrapper);
        if (userDO == null) {
            return null;
        }
        UserRespDTO result = new UserRespDTO();
        BeanUtils.copyProperties(userDO, result);
        return result;
    }

    @Override
    public Boolean hasUsername(String username) {
        return userRegisterCachePenetrationBloomFilter.contains(username);
    }

    @Override
    public void register(UserRegisterReqDTO requestParam){
        if(hasUsername(requestParam.getUsername())){
            throw new ClientException(USER_NAME_REPEAT);
        }
        RLock lock = redissonClient.getLock(RedisCacheConstant.LOCK_USER_REGISTER_KEY + requestParam.getUsername());
        try{
            if(lock.tryLock()){
                int inserted = baseMapper.insert(BeanUtil.toBean(requestParam, UserDO.class));
                if(inserted < 1){
                    throw new ClientException(UserErrorCodeEnum.USER_SAVE_ERROR);
                }
                userRegisterCachePenetrationBloomFilter.add(requestParam.getUsername());
            }
            else{
                throw new ClientException(USER_NAME_REPEAT);
            }
        }
        finally {
            lock.unlock();
        }

    }

    @Override
    public void update(String username, UserUpdateReqDTO requestParam) {
        LambdaQueryWrapper wrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(wrapper);
        if (userDO == null) {
            throw new ClientException(USER_NOT_EXIST);
        }
        int updated = baseMapper.update(BeanUtil.toBean(requestParam, UserDO.class), wrapper);
        if(updated < 1){
            throw new ClientException(USER_UPDATE_ERROR);
        }
    }

    @Override
    public UserLoginRespDTO login(UserLoginReqDTO requestParam) {
        String loginKey = "login_" + requestParam.getUsername();
        if (stringRedisTemplate.hasKey(loginKey)) {
            throw new ClientException(USER_LOGIN_ERROR);
        }
        LambdaQueryWrapper<UserDO> loginWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, requestParam.getUsername())
                .eq(UserDO::getPassword, requestParam.getPassword())
                .eq(UserDO::getDelFlag, 0);
        UserDO userDO = baseMapper.selectOne(loginWrapper);
        if (userDO == null) {
            throw new ClientException(USER_LOGIN_ERROR);
        }
        UUID uuid = UUID.randomUUID();
        stringRedisTemplate.opsForHash().put(loginKey,  uuid.toString(), JSON.toJSONString(userDO));
        stringRedisTemplate.expire(loginKey, 30L, TimeUnit.MINUTES);
        return new UserLoginRespDTO(uuid.toString());
    }

    @Override
    public Boolean checkLogin(String username, String token) {
        return stringRedisTemplate.opsForHash().hasKey("login_" + username, token);
    }
}
