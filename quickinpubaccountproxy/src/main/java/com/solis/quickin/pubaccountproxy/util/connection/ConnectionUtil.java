package com.solis.quickin.pubaccountproxy.util.connection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by monis on 2018/6/10.
 */
public class ConnectionUtil {

    /**
     * http/https get请求
     * @param servletPath
     * @param params
     * @return
     * @throws Exception
     */
    public static String sendGetHttpRequest(String servletPath, List<NameValuePair> params) throws Exception {
        URIBuilder uriBuilder = new URIBuilder(servletPath).setCharset(Charset.forName("UTF-8"));
        if (params != null && !params.isEmpty()) {
            uriBuilder.setParameters(params);
        }
        URI uri = uriBuilder.build();
        HttpGet httpGet = new HttpGet(uri);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        InputStream inputStream = entity.getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,  Charset.forName("UTF-8")));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        return builder.toString();
    }

    /**
     * 发送Post数据
     * @param servletPath
     * @param contentType
     * @param data
     * @return
     * @throws Exception
     */
    public static String sendPostHttpRequest(String servletPath, String contentType, String data) throws Exception {
        URIBuilder uriBuilder = new URIBuilder(servletPath).setCharset(Charset.forName("UTF-8"));
        URI uri = uriBuilder.build();
        HttpPost httpPost = new HttpPost(uri);
        StringEntity postEntity = new StringEntity(data, "UTF-8");
        postEntity.setContentType(contentType);
        httpPost.setEntity(postEntity);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        InputStream inputStream = entity.getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,  Charset.forName("UTF-8")));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        return builder.toString();
    }
}
