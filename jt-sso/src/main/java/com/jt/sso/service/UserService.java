package com.jt.sso.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.vo.SysResult;
import com.jt.sso.mapper.UserMapper;
import com.jt.sso.pojo.User;

/**
 * 处理用户相关服务
 * @author zain
 * 17/01/08
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    
    /**
     * 检查用户是否存在
     * @param map
     * @return
     */
    public SysResult check(Map<String, String> map) {
        Integer count = userMapper.queryUserCheck(map);
        if(count > 0) {
            return SysResult.ok(true);
        } else {
            return SysResult.build(201, "用户没查到", false);
        }
    }
    
    /**
     * 用户注册
     * @param user
     * @return
     */
    public SysResult register(User user) {
        user.setCreated(new Date());
        user.setUpdated(user.getCreated());
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        try {
            userMapper.insert(user);
            return SysResult.ok(true);
        } catch(Exception e) {
            return SysResult.build(201, "用户注册失败", false);
        }
    }
    
    /**
     * 登录操作
     * @param username
     * @return
     */
    public User login(String username) {
        User param = new User();
        param.setUsername(username);
        List<User> userList = userMapper.select(param);
        if(userList != null && userList.size() > 0) {
            User _user = userList.get(0);
            return _user;
        }
        return null;
    }
}
