package com.jt.web.threadLocal;

import com.jt.web.pojo.User;

/**
 * 线程本地化
 * @author zain
 * 17/01/23
 */
public class UserThreadLocal {
    private static final ThreadLocal<User> USER = new ThreadLocal<>();
    
    public static void set(User user) {
        USER.set(user);
    }
    public static User get() {
        return USER.get();
    }
    public static Long getUserId() {
        return USER.get().getId();
    }
}
