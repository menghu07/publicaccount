package com.solis.quickin.pubaccountproxy.serviceimpl.wepubaccount;

import com.solis.quickin.pubaccountproxy.service.wepubaccount.WeMessageService;
import org.springframework.stereotype.Service;

/**
 * 普通消息处理服务实现
 * Created by monis on 2018/6/11.
 */
@Service
public class WeNormalMessageServiceImpl implements WeMessageService {

    @Override
    public String processMessage(String weMessage) {
        return null;
    }
}
