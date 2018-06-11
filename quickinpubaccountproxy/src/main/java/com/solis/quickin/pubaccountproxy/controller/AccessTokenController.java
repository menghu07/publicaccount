package com.solis.quickin.pubaccountproxy.controller;

import com.solis.quickin.pubaccountproxy.constant.SystemConstant;
import com.solis.quickin.pubaccountproxy.system.SystemProperties;
import com.solis.quickin.pubaccountproxy.util.connection.ConnectionUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by monis on 2018/6/10.
 */
@Controller
public class AccessTokenController {

    private static final Object ACCESS_LOCK = new Object();

    @RequestMapping(path = "/getaccesstoken.do")
    @ResponseBody
    public String getAccessToken() {
        List<NameValuePair> pairs = new ArrayList<>();
        pairs.add(new BasicNameValuePair(SystemConstant.GRANT_TYPE, "client_credential"));
        pairs.add(new BasicNameValuePair(SystemConstant.APPID, SystemProperties.appID));
        pairs.add(new BasicNameValuePair(SystemConstant.APPSECRET, SystemProperties.appsecret));
        String response = "";
        try {
            response = ConnectionUtil.sendGetHttpRequest("https://api.weixin.qq.com/cgi-bin/token", pairs);
            System.out.println("response=" + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
