package test.httpclient;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TestHttpClient {

    @Test
    public void test() throws ClientProtocolException, IOException {
        /**
         * 创建一个httpClient实例
         * 调用实例execute方法
         * 得到响应
         */
        HttpClient hc = HttpClients.createDefault();
        //HttpGet get = new HttpGet("http://localhost:8081/page/index");
        HttpGet get = new HttpGet("http://baike.baidu.com/item/httpclient");
        HttpResponse res = hc.execute(get);
        if(res.getStatusLine().getStatusCode() == 200) { // 成功返回
            String s1 = res.getEntity().toString();
            System.err.println(s1);
            
            // 通过EntityUtils.toString返回页面信息
            String s = EntityUtils.toString(res.getEntity(), "utf-8");
            System.err.println(s);
        } else {
            System.err.println("请求失败！");
        }
        
    }
}
