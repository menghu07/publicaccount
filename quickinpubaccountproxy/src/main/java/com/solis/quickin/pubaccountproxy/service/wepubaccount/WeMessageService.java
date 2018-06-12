package com.solis.quickin.pubaccountproxy.service.wepubaccount;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;

/**
 * Created by monis on 2018/6/11.
 */
public interface WeMessageService {

    Logger LOGGER = LogManager.getLogger("system");

    /**
     * 处理微信公众号发来的消息
     * @param weMessage
     * @return
     */
    String processMessage(Document weMessage);
}
