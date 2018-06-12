package com.solis.quickin.pubaccountproxy.serviceimpl.wepubaccount.normal;

import com.solis.quickin.pubaccountproxy.service.wepubaccount.WeMessageService;
import com.solis.quickin.pubaccountproxy.util.xmlparse.XMLParser;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

/**
 * 图片消息处理服务实现
 * Created by monis on 2018/6/11.
 */
@Service
public class WeImageMessageServiceImpl implements WeMessageService {

    @Override
    public String processMessage(Document weMessage) {

        String developer = weMessage.getElementsByTagName("ToUserName").item(0).getTextContent();
        String user = weMessage.getElementsByTagName("FromUserName").item(0).getTextContent();
        String messageType = weMessage.getElementsByTagName("MsgType").item(0).getTextContent();
        String createTime = weMessage.getElementsByTagName("CreateTime").item(0).getTextContent();
        String pictureURL = weMessage.getElementsByTagName("PicUrl").item(0).getTextContent();
        String mediaID = weMessage.getElementsByTagName("MediaId").item(0).getTextContent();
        String messageID = weMessage.getElementsByTagName("MsgId").item(0).getTextContent();
        LOGGER.info("parsed: developer=" + developer + ", user=" + user + ", messageType=" + messageType + ", createTime=" + createTime
                + ", pictureURL=" + pictureURL + ", mediaID=" + mediaID + ", messageID=" + messageID);
        return XMLParser.replayToUserPictureAndText(user, developer, System.currentTimeMillis());
    }
}
