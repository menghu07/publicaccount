package com.solis.quickin.pubaccountproxy.serviceimpl.wepubaccount.normal;

import com.solis.quickin.pubaccountproxy.service.wepubaccount.WeMessageService;
import com.solis.quickin.pubaccountproxy.util.xmlparse.XMLParser;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;


/**
 * 文本消息处理服务实现
 * Created by monis on 2018/6/11.
 */
@Service
public class WeTextMessageServiceImpl implements WeMessageService {

    @Override
    public String processMessage(Document weMessage) {
        String developer = weMessage.getElementsByTagName("ToUserName").item(0).getTextContent();
        String user = weMessage.getElementsByTagName("FromUserName").item(0).getTextContent();
        String messageType = weMessage.getElementsByTagName("MsgType").item(0).getTextContent();
        String createTime = weMessage.getElementsByTagName("CreateTime").item(0).getTextContent();
        String content = weMessage.getElementsByTagName("Content").item(0).getTextContent();
        String messageID = weMessage.getElementsByTagName("MsgId").item(0).getTextContent();
        LOGGER.info("parsed: developer=" + developer + ", user=" + user + ", messageType=" + messageType + ", createTime=" + createTime
                + ", content=" + content + ", messageID=" + messageID);
        if (content.contains("官网") || content.contains("主页") || content.contains("首页") || content.contains("快印")) {
            return XMLParser.replayToUserPictureAndText(user, developer, System.currentTimeMillis());
        }
        return XMLParser.replyToUser(user, developer, System.currentTimeMillis(), messageType, "hi, i had received yr message.\n" + content);
    }
}
