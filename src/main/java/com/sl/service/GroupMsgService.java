package com.sl.service;

import com.sl.model.GroupMsg;
import com.sl.model.ReceiveMsg;
import com.sl.model.ReplyMsg;
import com.sl.model.Result;

import javax.servlet.http.HttpServletRequest;

public interface GroupMsgService {

    /**
     * 发送群消息
     *
     * @param groupMsg
     * @return
     */
    Result sendGroupMsg(GroupMsg groupMsg, HttpServletRequest request);

    /**
     * 由收到的群消息的内容转发给特定的方法
     *
     * @param receiveMsg
     */
    ReplyMsg handleGroupMsg(ReceiveMsg receiveMsg,HttpServletRequest request);

}
