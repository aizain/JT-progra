package com.jt.common.service;

import org.springframework.stereotype.Service;

import com.jt.common.spring.exetend.PropertyConfig;

/**
 * 获取配置文件内容
 * 
 * @author zain
 * 16/10/15
 */
@Service
public class PropertieService {

    @PropertyConfig
    public static String REPOSITORY_PATH;
    @PropertyConfig
    public static String IMAGE_BASE_URL;
    
//    public static final String REPOSITORY_PATH = "d:\\jt-upload";
//    public static final String IMAGE_BASE_URL = "http://image.jt.com";

}
