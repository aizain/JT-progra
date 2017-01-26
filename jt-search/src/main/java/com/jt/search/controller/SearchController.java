package com.jt.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.search.pojo.Item;
import com.jt.search.service.SearchService;

/**
 * 搜索
 * @author zain
 * 17/01/26
 */
@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;
    
    /**
     * 通过关键字检索
     * @param keyWords
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value="search", method=RequestMethod.POST)
    @ResponseBody
    public SysResult search(@RequestParam("keyWords") String keyWords,
            @RequestParam("page") int page, @RequestParam("rows") int rows) {
        return searchService.search(keyWords, page, rows);
    }
    
    /**
     * 更新solr中的数据
     * @param item
     * @return
     */
    @RequestMapping(value="update", method=RequestMethod.POST)
    @ResponseBody
    public SysResult update(@RequestBody Item item) {
        return searchService.update(item);
    }
}
