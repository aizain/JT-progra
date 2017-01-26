package com.jt.web.controller;

import java.io.IOException;
import java.util.List;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jt.web.pojo.Item;
import com.jt.web.service.SearchService;

/**
 * 前台搜索
 * @author zain
 * 17/01/26
 */
@Controller
public class SearchController {
    
    @Autowired
    private SearchService searchService;
    
    /**
     *  搜索服务 http://www.jt.com/search.html?q=123
     * @param keyWords
     * @param model
     * @return
     * @throws IOException 
     * @throws ParseException 
     */
    @RequestMapping("/search")
    public String search(@RequestParam("q") String keyWords, Model model,
            @RequestParam(defaultValue="1") int page, 
            @RequestParam(defaultValue="20")int rows) throws ParseException, IOException {
        List<Item> dataList = searchService.search(keyWords, page, rows);
        model.addAttribute("itemList", dataList);
        return "search";
    }
}
