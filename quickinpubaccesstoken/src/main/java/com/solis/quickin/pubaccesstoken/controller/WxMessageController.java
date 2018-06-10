package com.solis.quickin.pubaccesstoken.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by apeny on 2018/6/9.
 */
@Controller
public class WxMessageController {

    @ResponseBody
    @RequestMapping(path = "/wxmessage/checkwxserver.do")

    public String checkWxServer(String signature, String timestamp, String nonce, String echostr) {
        System.out.println("i do nothing....");
        return echostr;
    }
}
