package com.solis.quickin.pubaccountproxy.util.dispatcher;

import com.solis.quickin.pubaccountproxy.constant.MessageTypeConstant;
import com.solis.quickin.pubaccountproxy.service.wepubaccount.WeMessageService;
import com.solis.quickin.pubaccountproxy.serviceimpl.wepubaccount.normal.WeImageMessageServiceImpl;
import com.solis.quickin.pubaccountproxy.serviceimpl.wepubaccount.normal.WeLinkMessageServiceImpl;
import com.solis.quickin.pubaccountproxy.serviceimpl.wepubaccount.normal.WeLocationMessageServiceImpl;
import com.solis.quickin.pubaccountproxy.serviceimpl.wepubaccount.normal.WeTextMessageServiceImpl;
import com.solis.quickin.pubaccountproxy.system.SystemEnvironment;
import org.w3c.dom.Document;

import javax.servlet.ServletContext;


/**
 * Created by monis on 2018/6/12.
 */
public class Dispatcher {

    /**
     * 分发消息的服务实现
     * @param document
     * @return
     * @throws Exception
     */
    public static WeMessageService dispatchMessage(Document document) throws Exception {
        String msgType = document.getElementsByTagName("MsgType").item(0).getTextContent();
        if (MessageTypeConstant.TEXT.equals(msgType)) {
            return SystemEnvironment.webApplicationContext.getBean("weTextMessageServiceImpl", WeTextMessageServiceImpl.class);
        } else if (MessageTypeConstant.IMAGE.equals(msgType)) {
            return SystemEnvironment.webApplicationContext.getBean("weImageMessageServiceImpl", WeImageMessageServiceImpl.class);
        } else if (MessageTypeConstant.LOCATION.equals(msgType)) {
            return SystemEnvironment.webApplicationContext.getBean("weLocationMessageServiceImpl", WeLocationMessageServiceImpl.class);
        } else if (MessageTypeConstant.LINK.equals(msgType)) {
            return SystemEnvironment.webApplicationContext.getBean("weLinkMessageServiceImpl", WeLinkMessageServiceImpl.class);
        }
        throw new IllegalArgumentException("消息类型错误： " + msgType);
    }
}
