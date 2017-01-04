package com.jt.common.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HttpClientService {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientService.class);
    
    @Autowired
	private CloseableHttpClient httpClient;
    @Autowired
    private RequestConfig requestConfig;
    
    /**
     * 执行get请求
     * 
     * @param url
     * @param params
     * @param encode
     * @return
     * @throws URISyntaxException 
     * @throws IOException 
     * @throws ClientProtocolException 
     */
    public String doGet(String url, Map<String, String> params, String encode) throws URISyntaxException, ClientProtocolException, IOException {
        LOGGER.info("执行GET请求，URL = {}", url);
        if (null != params) {
            URIBuilder builder = new URIBuilder(url);
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.setParameter(entry.getKey(), entry.getValue());
            }
            url = builder.build().toString();
        }
        
        // 创建http GET请求
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                if(encode == null) {
                    encode = "UTF-8";
                }
                return EntityUtils.toString(response.getEntity(), encode);
            }
        } finally {
            if (null != response) {
                response.close();
            }
            // 此处不能关闭httpClient，如果关闭httpClient，连接池也会销毁
        }
        
        return null;
    }
    
    public String doGet(String url, String encode) throws ClientProtocolException, URISyntaxException, IOException {
        return this.doGet(url, null, encode);
    }
    public String doGet(String url) throws ClientProtocolException, URISyntaxException, IOException {
        return this.doGet(url, null, null);
    }
    
    /**
     * 带参数的get请求
     * 
     * @param url
     * @param params
     * @return
     * @throws IOException 
     * @throws URISyntaxException 
     * @throws ClientProtocolException 
     */
    public String doGet(String url, Map<String, String> params) throws ClientProtocolException, URISyntaxException, IOException {
        return this.doGet(url, params, null);
    }
    
    
    /**
     * 执行POST请求
     * 
     * @param url
     * @param params
     * @param encode
     * @return
     * @throws IOException 
     * @throws ParseException 
     */
    public String doPost(String url, Map<String, String> params, String encode) throws ParseException, IOException {
        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        
        if (null != params) {
            // 设置2个post参数，一个是scope，一个是q
            List<NameValuePair> parameters = new ArrayList<>(0);
            for (Map.Entry<String, String> entry : params.entrySet()) {
                parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            
            // 构造一个form表单式的实体
            UrlEncodedFormEntity formEntity = null;
            if (null != encode) {
                formEntity = new UrlEncodedFormEntity(parameters, encode);
            } else {
                formEntity = new UrlEncodedFormEntity(parameters);
            }
            
            // 将请求实体设置到httpPost对象中
            httpPost.setEntity(formEntity);
        }
        
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpPost);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } finally {
            if (null != response) {
                response.close();
            }
        }
        
        return null;
    }
    public String doPost(String url, Map<String, String> params) throws ParseException, IOException {
        return doPost(url, params, null);
    }
    public String doPost(String url) throws ParseException, IOException {
        return doPost(url, null, null);
    }
    
    public String doPostJson(String url, String json) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        
        if (null != json) {
            // 设置请求体为字符串
            StringEntity stringEntity = new StringEntity(json, "UTF-8");
            httpPost.setEntity(stringEntity);
        }
        
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpPost);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } finally {
            if (null != response) {
                response.close();
            }
        }
        return null;
    }
	
}
