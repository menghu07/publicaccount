package com.solis.quickin.pubaccountproxy.service.wepubaccount;

/**
 * Created by monis on 2018/6/11.
 */
public interface WeMessageService {

    /**
     * 处理微信公众号发来的消息
     * @param weMessage
     * @return
     */
    String processMessage(String weMessage);
}
