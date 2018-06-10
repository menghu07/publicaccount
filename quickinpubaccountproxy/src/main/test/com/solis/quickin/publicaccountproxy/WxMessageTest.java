package com.solis.quickin.publicaccountproxy;

import com.solis.quickin.pubaccountproxy.util.connection.ConnectionUtil;
import com.solis.quickin.pubaccountproxy.util.crypto.AesException;
import com.solis.quickin.pubaccountproxy.util.crypto.WxBizMsgCrypt;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by monis on 2018/6/9.
 */
public class WxMessageTest {
    public static void main(String[] args) {
//        verifyUrl();
//        debugVerifyUrl();
        getAccessToken();
    }

    private static void verifyUrl() {

        WxBizMsgCrypt wxcpt = null;
        try {
            wxcpt = new WxBizMsgCrypt("QDG6eK",
                    "jWmYm7qr5nMoAUwZRjGtBxmz3KA1tkAj3ykkR6q2B2C", "wx5823bf96d3bd56c7");
            String verifyMsgSig = "5c45ff5e21c57e6ad56bac8758b79b1d9ac89fd3";
            String timeStamp = "1409659589";
            String nonce = "263014780";
            String echoStr = "P9nAzCzyDtyTWESHep1vC5X9xho/qYX3Zpb4yKa9SKld1DsH3Iyt3tP3zNdtp+4RPcs8TgAE7OaBO+FZXvnaqQ==";
            wxcpt.verifyUrl(verifyMsgSig, timeStamp, nonce, echoStr);
        } catch (AesException e) {
            e.printStackTrace();
        }
    }

    private static void debugVerifyUrl() {
        WxBizMsgCrypt wxcpt = null;
        try {
            wxcpt = new WxBizMsgCrypt("123456",
                    "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFG", "wx0e2773fbf7707ab8");
            String verifyMsgSig = "8107168c51f154f19a1d11aed4b30f7185f63f0e";
            String timeStamp = "1528558160";
            String nonce = "705521636";
            String echoStr = "3342085780301150814";
            wxcpt.verifyUrl(verifyMsgSig, timeStamp, nonce, echoStr);
        } catch (AesException e) {
            e.printStackTrace();
        }
    }

    private static void getAccessToken() {
        List<NameValuePair> pairs = new ArrayList<>();
        pairs.add(new BasicNameValuePair("grant_type", "client_credential"));
        pairs.add(new BasicNameValuePair("appid", "wx0e2773fbf7707ab8"));
        pairs.add(new BasicNameValuePair("secret", "4e566bc2e4ab484b714f685257badae0"));
        try {
            String response = ConnectionUtil.sendGetHttpRequest("https://api.weixin.qq.com/cgi-bin/token", pairs);
            System.out.println("response=" + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
