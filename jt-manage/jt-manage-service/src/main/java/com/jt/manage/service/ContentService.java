package com.jt.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jt.common.vo.EasyUIResult;
import com.jt.manage.mapper.ContentMapper;
import com.jt.manage.pojo.Content;

/**
 * 内容管理
 * @author zain
 * 16/11/25
 */
@Service
public class ContentService {

    @Autowired
    private ContentMapper contentMapper;
    
    public EasyUIResult queryListByCategoryId(Long categoryId, Integer page, Integer rows) {
        Content param = new Content();
        param.setCategoryId(categoryId);
        
        PageHelper.startPage(page, rows);
        List<Content> contentList = contentMapper.select(param);
        PageInfo info = new PageInfo(contentList);
        
        return new EasyUIResult(info.getTotal(), info.getList());
    }
}
