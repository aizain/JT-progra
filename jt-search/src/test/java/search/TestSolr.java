package search;

import com.jt.search.service.Item;

public class TestSolr {
    public static void main(String[] args) {
        String url = "http://localhost:8983/solr/jt";
        HttpSolrServer httpSolrServer = new HttpSolrServer(url);
        
        httpSolrServer.setPaeser(new XMLResponseParser()); // 设置响应解析器
        httpSolrServer.setMaxRetries(1); // 设置重试次数，推荐设置为1
        httpSolrServer.setConnectionTimeout(500); // 建立连接的最长时间
        
        Item item = new Item();
        item.setId(123L);
        item.setTitle("中华人民共和国");
        
        httpSolrServer.addBean(item); // 将java对象写入solr，同时创建索引
        httpSolrServer.commit(); // 提交事务
        System.out.println("ok");
    }
    
    
    public void del() {
        String url = "http://localhost:8983/solr/jt"; // 指定访问solr中的具体的core
        HttpSolrServer httpSolrServer = new HttpSolrServer(url);
        
        httpSolrServer.setPaeser(new XMLResponseParser()); // 设置响应解析器
        httpSolrServer.setMaxRetries(1); // 设置重试次数，推荐设置为1
        httpSolrServer.setConnectionTimeout(500); // 建立连接的最长时间
        
        httpSolrServer.deleteById("123");
        httpSolrServer.commit();
    }
}
