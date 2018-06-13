package com.solis.quickin.pubaccountproxy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
 * 自定义菜单
 * Created by monis on 2018/6/13.
 */
@Controller
public class CustomMenuController {

    /**
     * 创建菜单
     * @return
     */
    @RequestMapping(path = "/createmenu.do")
    @ResponseBody
    public String createMenu() {
        //获取AccessToken
        List<NameValuePair> pairs = new ArrayList<>();
        pairs.add(new BasicNameValuePair(SystemConstant.GRANT_TYPE, "client_credential"));
        pairs.add(new BasicNameValuePair(SystemConstant.APPID, SystemProperties.appID));
        pairs.add(new BasicNameValuePair(SystemConstant.APPSECRET, SystemProperties.appsecret));
        String response = "";
        try {
            response = ConnectionUtil.sendGetHttpRequest("https://api.weixin.qq.com/cgi-bin/token", pairs);
            System.out.println("response=" + response);
            JSONObject accessToken = JSONObject.parseObject(response);
            //创建菜单
            String menu = "{\"button\":[{\"type\":\"view\", \"name\":\"商店\", \"url\":\"https://mp.weixin.qq.com/s/XqiB7sLvDjoD9zgX-q3Hxg\"},"
                    + "{\"type\":\"view\", \"name\":\"购物车\", \"url\":\"https://mp.weixin.qq.com/s/dEHmKe4sJ-rmnGtNyzka0Q\"},"
                    + "{\"type\":\"view\", \"name\":\"我的\", \"url\":\"https://mp.weixin.qq.com/s/GcvZAlmXqiv-xnc9XjC4Rw\"}]}";
            response = ConnectionUtil.sendPostHttpRequest("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken.getString("access_token"),
                    "application/json", menu);
            System.out.println("menu reponse=" + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
