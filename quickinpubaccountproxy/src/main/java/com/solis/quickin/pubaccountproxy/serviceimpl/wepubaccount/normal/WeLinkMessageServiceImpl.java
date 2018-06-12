package com.solis.quickin.pubaccountproxy.serviceimpl.wepubaccount.normal;

import com.solis.quickin.pubaccountproxy.service.wepubaccount.WeMessageService;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

/**
 * 链接消息处理服务实现
 * Created by monis on 2018/6/11.
 */
@Service
public class WeLinkMessageServiceImpl implements WeMessageService {

    @Override
    public String processMessage(Document weMessage) {
        return "";
    }
}
