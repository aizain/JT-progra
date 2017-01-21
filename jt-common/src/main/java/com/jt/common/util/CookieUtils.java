package com.jt.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Cookie 工具类
 * @author zain
 * 17/01/20
 */
public final class CookieUtils {
    protected static final Logger logger = LoggerFactory.getLogger(CookieUtils.class);
    
    /**
     * 得到Cookie的值，不编码
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        return getCookieValue(request, cookieName, false);
    }

    /**
     * 
     * @param request
     * @param cookieName
     * @param b
     * @return
     */
    private static String getCookieValue(HttpServletRequest request, String cookieName, boolean b) {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * 
     * @param request
     * @param cookieName
     * @param b
     * @return
     */
    private static String getCookieValue(HttpServletRequest request, String cookieName, String b) {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * 设置Cookie的值，不设置生效时间，默认浏览器关闭失效，也不编码
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue) {
        setCookie(request, response, cookieName, cookieValue, -1);
    }
    /**
     * 设置Cookie的值，在指定时间内，但不编码
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param cookieMaxage
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage) {
        setCookie(request, response, cookieName, cookieValue, cookieMaxage, false);
    }
    
    /**
     * 设置Cookie的值，不设置生效时间，但编码
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param isEncode
     */
    private static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
            String cookieValue, boolean isEncode) {
        setCookie(request, response, cookieName, cookieValue, -1, isEncode);
    }

    /**
     * 
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param cookieMaxage
     * @param s
     */
    private static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
            String cookieValue, int cookieMaxage, boolean isEncode) {
        // TODO Auto-generated method stub
    }
    
    /**
     * 
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param cookieMaxage
     * @param s
     */
    private static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
            String cookieValue, int cookieMaxage, String s) {
        // TODO Auto-generated method stub
    }

    /**
     * 
     * @param request
     * @param response
     * @param cookieName
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
        
    }
    
    /**
     * 
     * @param request
     * @param response
     * @param cookieName
     */
    public static void doSetCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
        
    }
    
    /**
     * 
     * @param request
     * @return
     */
    public static String getDomainName(HttpServletRequest request) {
        return "";
    }
}
