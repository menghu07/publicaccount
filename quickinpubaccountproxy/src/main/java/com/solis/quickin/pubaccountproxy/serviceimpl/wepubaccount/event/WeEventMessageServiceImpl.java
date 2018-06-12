package com.solis.quickin.pubaccountproxy.serviceimpl.wepubaccount.event;

import com.solis.quickin.pubaccountproxy.service.wepubaccount.WeMessageService;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

/**
 * 事件消息处理服务实现
 * Created by monis on 2018/6/11.
 */
@Service
public class WeEventMessageServiceImpl implements WeMessageService {

    @Override
    public String processMessage(Document weMessage) {
        return null;
    }
}
