package com.solis.quickin.pubaccountproxy.serviceimpl.wepubaccount.normal;

import com.solis.quickin.pubaccountproxy.service.wepubaccount.WeMessageService;
import com.solis.quickin.pubaccountproxy.util.xmlparse.XMLParser;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

/**
 * 位置消息处理服务实现
 * Created by monis on 2018/6/11.
 */
@Service
public class WeLocationMessageServiceImpl implements WeMessageService {

    @Override
    public String processMessage(Document weMessage) {

        String developer = weMessage.getElementsByTagName("ToUserName").item(0).getTextContent();
        String user = weMessage.getElementsByTagName("FromUserName").item(0).getTextContent();
        String messageType = weMessage.getElementsByTagName("MsgType").item(0).getTextContent();
        String createTime = weMessage.getElementsByTagName("CreateTime").item(0).getTextContent();
        String locationX = weMessage.getElementsByTagName("Location_X").item(0).getTextContent();
        String locationY = weMessage.getElementsByTagName("Location_Y").item(0).getTextContent();
        String scale = weMessage.getElementsByTagName("Scale").item(0).getTextContent();
        String label = weMessage.getElementsByTagName("Label").item(0).getTextContent();
        String messageID = weMessage.getElementsByTagName("MsgId").item(0).getTextContent();
        LOGGER.info("parsed: developer=" + developer + ", user=" + user + ", messageType=" + messageType + ", createTime=" + createTime
                + ", locationX=" + locationX + ", locationY=" + locationY + ", scale=" + scale + ", label=" + label + ", messageID=" + messageID);
        return XMLParser.replyToUser(user, developer, System.currentTimeMillis(), messageType, "yr location, i just to see");
    }
}
