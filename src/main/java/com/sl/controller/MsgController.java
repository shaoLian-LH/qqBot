package com.sl.controller;

import com.sl.model.PrivateMsg;
import com.sl.model.ReplyMsg;
import com.sl.model.Result;
import com.sl.service.MsgService;
import com.sl.service.PrivateMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/msg")
public class MsgController {

    @Autowired
    MsgService msgService;

    @Autowired
    PrivateMsgService privateMsgService;

    @RequestMapping("/sendPrivateMsg")
    public Result sendPrivateMsg(PrivateMsg msg) {
        return privateMsgService.sendPrivateMsg(msg);
    }

    /**
     * 消息上报接口
     *
     * @param request
     */
    @RequestMapping("/receive")
    public ReplyMsg receive(HttpServletRequest request) {
        return msgService.receive(request);
    }

}
