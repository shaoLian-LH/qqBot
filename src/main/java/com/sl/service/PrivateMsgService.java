package com.sl.service;

import com.sl.model.PrivateMsg;
import com.sl.model.ReceiveMsg;
import com.sl.model.ReplyMsg;
import com.sl.model.Result;

/**
 * 私聊消息服务接口
 */
public interface PrivateMsgService {

    /**
     * 发送私聊消息
     *
     * @param msg
     * @return
     */
    Result sendPrivateMsg(PrivateMsg msg);

    /**
     * 由收到的私聊消息的内容转发给特定的方法
     *
     * @param receiveMsg
     * @return
     */
    ReplyMsg handlePrivateMsg(ReceiveMsg receiveMsg);

}
