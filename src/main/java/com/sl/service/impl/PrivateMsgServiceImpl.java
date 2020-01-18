package com.sl.service.impl;


import com.sl.model.*;
import com.sl.service.PrivateMsgService;
import com.sl.util.MsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.sl.constant.URLConst.SEND_PRIVATE_MSG;
import static com.sl.constant.URLConst.URL;

@Service
public class PrivateMsgServiceImpl implements PrivateMsgService {

    @Autowired
    RestTemplate restTemplate;


    @Override
    public Result sendPrivateMsg(PrivateMsg msg) {
        Result result = restTemplate.postForObject(URL + SEND_PRIVATE_MSG, msg, Result.class);
        return result;
    }


    public ReplyMsg handlePrivateMsg(ReceiveMsg receiveMsg) {
        Long user_id = receiveMsg.getUser_id();
        String raw_message = receiveMsg.getRaw_message();

        //查询指令是否标准，不标准则反馈提示
        if (MsgUtil.getMenu(raw_message) != null) {
            ReplyMsg replyMsg = new ReplyMsg();
            replyMsg.setReply(MsgUtil.getMenu(raw_message));
            return replyMsg;
        }


        return null;
    }


}
