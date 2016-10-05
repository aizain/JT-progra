package com.jt.manage.controller;

import java.text.SimpleDateFormat;

import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;

/**
 * 基础controller层
 * 用于处理通用工作
 * 
 * @author zain
 *
 */
@Controller
public class BaseController {
	//日期特殊处理
	@InitBinder
	public void InitBinder (ServletRequestDataBinder binder) {
		binder.registerCustomEditor(
				java.util.Date.class, 
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
	}
}
