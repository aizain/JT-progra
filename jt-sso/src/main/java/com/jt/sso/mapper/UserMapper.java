package com.jt.sso.mapper;

import java.util.Map;

import com.jt.sso.mapper.base.mapper.SysMapper;
import com.jt.sso.pojo.User;

public interface UserMapper extends SysMapper<User> {
    public int queryUserCheck(Map<String, String> map);
}
