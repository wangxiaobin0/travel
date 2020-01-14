package cn.travel.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CZCBHttpAccessService {
    public String commHTTPProxy(String url, String requestParam) throws IOException {
        URL httpURL = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) httpURL.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json;charset");

        OutputStream out = connection.getOutputStream();
        out.write(requestParam.getBytes());

        int code = connection.getResponseCode();
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer(1024);
        if (code != 200) {
            throw new RuntimeException("服务器返回响应码:" + code + "," + connection.getErrorStream());
        }
        InputStream inputStream = connection.getInputStream();
        br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String temp = null;
        while ((temp = br.readLine()) != null) {
            sb.append(temp);
        }
        System.out.println(sb.toString());
        return "0";
    }

    public static void main(String[] args) throws Exception{
        //获取连接地址
        String host = "http://127.0.0.1";
        //获取端口
        int port = 80;


        String api = "/travel/category/queryCategory";
        String url = host + port + api;
        //请求参数
        String requestParam = "username=zhangsan&password=123456&checkCode=7801";
        CZCBHttpAccessService czcbHttpAccessService = new CZCBHttpAccessService();
        //czcbHttpAccessService.commHTTPProxy(url, requestParam);
        Map<String, String> map = new HashMap<>();
        map.put("name", "zhangsan");
        map.put("age", "zhangsan");
    }
}
