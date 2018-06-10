package com.solis.quickin.pubaccountproxy.controller;

import com.solis.quickin.pubaccountproxy.util.crypto.WxBizMsgCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by monis on 2018/6/9.
 */
@Controller
public class WxMessageController {

    /**
     * signature= 8107168c51f154f19a1d11aed4b30f7185f63f0e, timestamp=1528558160, nonce=705521636, echostr=3342085780301150814
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/wxmessage", params = {"signature", "timestamp", "nonce", "echostr"})
    public String checkToken(String signature, String timestamp, String nonce, String echostr) {
        String success = "success";
        try {
            WxBizMsgCrypt crypt = new WxBizMsgCrypt("123456", "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFG", "wx0e2773fbf7707ab8");
            success = crypt.verifyUrl(signature, timestamp, nonce, echostr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("i do nothing...." + ", signature= " + signature + ", timestamp=" + timestamp
                + ", nonce=" + nonce + ", echostr=" + echostr);
        return success;
    }


    @ResponseBody
    @RequestMapping(path = "/wxmessage")
    public String handleMessage(HttpServletRequest request) {
        try {
            ServletInputStream inputStream = request.getInputStream();
            byte[] data = new byte[1024];
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            dataInputStream.readFully(data);
            int count = inputStream.read(data);
            System.out.println("i received data=" + new String(data, 0, count) + "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("no param i");
        return "no param name";
    }
}
