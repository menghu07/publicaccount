package com.solis.quickin.pubaccountproxy.controller;

import com.solis.quickin.pubaccountproxy.system.SystemProperties;
import com.solis.quickin.pubaccountproxy.util.crypto.WxBizMsgCrypt;
import com.solis.quickin.pubaccountproxy.util.xmlparse.XMLParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Enumeration;

/**
 * Created by monis on 2018/6/9.
 */
@Controller
public class WxMessageController {

    private static Logger LOGGER = LogManager.getLogger("system");

    /**
     * signature= 8107168c51f154f19a1d11aed4b30f7185f63f0e, timestamp=1528558160, nonce=705521636, echostr=3342085780301150814
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/wxmessage.do", params = {"signature", "timestamp", "nonce", "echostr"})
    public String checkToken(String signature, String timestamp, String nonce, String echostr) {
        String success = "success";
        try {
            WxBizMsgCrypt crypt = new WxBizMsgCrypt(SystemProperties.token, "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFG", SystemProperties.appID);
            crypt.verifyUrl(signature, timestamp, nonce);
            success = echostr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("i do nothing...." + ", signature= " + signature + ", timestamp=" + timestamp
                + ", nonce=" + nonce + ", echostr=" + echostr);
        return success;
    }

    /**
     * 接收微信公众号发来的消息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/wxmessage.do")
    public String handleMessage(HttpServletRequest request) {
        String replyMessage = "";
        try {
            Enumeration<String> paramNames = request.getParameterNames();
            String paramName;
            LOGGER.info("wxmessage.do is called, Request.Method=" + request.getMethod());
            while (paramNames.hasMoreElements()) {
                LOGGER.info("name=" + (paramName = paramNames.nextElement()) + ",value=" + request.getParameter(paramName));
            }

            StringBuilder message = new StringBuilder();
            InputStream in = request.getInputStream();
            BufferedInputStream buf = new BufferedInputStream(in);
            byte[] buffer = new byte[1024];
            int iRead;
            while ((iRead = buf.read(buffer)) != -1) {
                message.append(new String(buffer, 0, iRead, "utf-8"));
            }

            LOGGER.info("from inputstream: " + message);
            //"<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName>"
            //+ "<![CDATA[fromUser]]></FromUserName><CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType>  <Content><![CDATA[this is a test]]></Content> <MsgId>1234567890123456</MsgId>  </xml>"
            Document document = XMLParser.convertToDocument(message.toString());
            String developer = document.getElementsByTagName("ToUserName").item(0).getTextContent();
            String user = document.getElementsByTagName("FromUserName").item(0).getTextContent();
            String messageType = document.getElementsByTagName("MsgType").item(0).getTextContent();
            String createTime = document.getElementsByTagName("CreateTime").item(0).getTextContent();
            String content = document.getElementsByTagName("Content").item(0).getTextContent();
            String messageID = document.getElementsByTagName("MsgId").item(0).getTextContent();
            LOGGER.info("parsed: developer=" + developer + ", user=" + user + ", messageType=" + messageType + ", createTime=" + createTime
                    + ", content=" + content + ", messageID=" + messageID);
            replyMessage = XMLParser.replyToUser(user, developer, System.currentTimeMillis(), messageType, content + ", i receive yr data");
        } catch (Exception e) {
            LOGGER.error("wxmessage处理请求异常", e);
        }

        return replyMessage;
    }
}
