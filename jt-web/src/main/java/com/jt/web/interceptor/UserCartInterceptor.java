package com.jt.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.common.spring.exetend.PropertyConfig;
import com.jt.common.util.CookieUtils;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;
import com.jt.web.threadLocal.UserThreadLocal;

/**
 * 用户-购物车拦截器
 * @author zain
 * 17/01/23
 */
public class UserCartInterceptor implements HandlerInterceptor {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    @Autowired
    private HttpClientService httpClientService;
    @PropertyConfig
    private String SSO_URL;
    
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        // TODO Auto-generated method stub
    }

    /**
     * false 不放行
     * true 放行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        System.out.println("进入拦截器");
        // 读取当前User对象，到cookie中拿ticket，通过ticket去redis中查询(SSO)
        String cookieName = "JT_TICKET";
        String ticket = CookieUtils.getCookieValue(request, cookieName);
        if (StringUtils.isEmpty(ticket)) {
            // 没有登录
            UserThreadLocal.set(null);
        } else {
            // 把user放入threadLocal中
            String url = SSO_URL + "/user/query/" + ticket;
            String jsonData = httpClientService.doGet(url);
            User user = (User) SysResult.formatToPojo(jsonData, User.class).getData();
            UserThreadLocal.set(user);
        }
        return true;
    }

}
