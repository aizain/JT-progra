package com.jt.search.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.vo.SysResult;
import com.jt.search.pojo.Item;

/**
 * 搜索
 * @author zain
 * 17/01/26
 */
@Service
public class SearchService {
    @Autowired
    private HttpSolrServer httpSolrServer;
    
    /**
     * 查询
     * @param keyWords
     * @param page
     * @param rows
     * @return
     */
    public SysResult search(String keyWords, int page, int rows) {
        // 构造搜索对象
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery(keyWords);
        
        // 分页数据
        solrQuery.setStart((Math.max(1, page) - 1) * rows);
        solrQuery.setRows(rows);
        
        // 设置高亮显示
        solrQuery.setHeighlight(true); // 开启高度
        solrQuery.setHeighlightSimplePre("<span class=\"red\">");
        solrQuery.setHeighlightSimplePre("</span>");
        solrQuery.setHeighlightField("title");
        
        try {
            QueryResponse queryResponse = httpSolrServer.query(solrQuery);
            List<Item> items = queryResponse.getBeans(Item.class);
            if (null == items || items.isEmpty()) {
                return SysResult.build(200, "没有搜索到数据！");
            }
            // 将高亮的标题数据写回数据对象中
            Map<String, Map<String, List<String>>> map = queryResponse.getHighlight();
            for (Map.Entry<String, Map<String, List<String>>> hightlighting: map.entrySet()) {
                for (Item item: items) {
                    if (!hightlighting.getKey().equals(item.getId().toString())) {
                        continue;
                    }
                    item.setTitle(StringUtils.join(hightlighting.getValue().get("title"), ""));
                    break;
                }
            }
            return SysResult.build(200, String.valueOf(queryResponse.getResults().getNumFound()), items);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return SysResult.build(201, "搜索错误！");
    }

    public SysResult update(Item item) {
        // TODO Auto-generated method stub
        return null;
    }

}
