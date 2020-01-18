package com.sl.service.impl;

import com.sl.constant.URLConst;
import com.sl.model.*;
import com.sl.model.ReceiveMsg;
import com.sl.model.ReplyMsg;
import com.sl.service.*;
import com.sl.util.ChineseToPinyinUtil;
import com.sl.util.MsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Service
public class GroupMsgServiceImpl implements GroupMsgService {

    @Autowired
    PrivateMsgService privateMsgService;

    @Autowired
    ZprService zprService;

    @Autowired
    RestTemplate restTemplate;

    static ArrayList<String> zprMsg = new ArrayList<>();
    static ArrayList<String> sdMsg = new ArrayList<>();
    static {
        String[] zpr = {"纸片人","铜","来点色图"};
        for(String s : zpr){
            zprMsg.add(s);
            zprMsg.add(new StringBuilder(s).reverse().toString());
            zprMsg.add(ChineseToPinyinUtil.getPinyinString(s));
            zprMsg.add(ChineseToPinyinUtil.getFirstLettersLo(s));
            zprMsg.add(ChineseToPinyinUtil.getFirstLettersUp(s));
        }
        String[] sd = {"沙雕图","来点沙雕图"};
        for (String s:sd){
            sdMsg.add(s);
            sdMsg.add(new StringBuilder(s).reverse().toString());
            sdMsg.add(ChineseToPinyinUtil.getPinyinString(s));
            sdMsg.add(ChineseToPinyinUtil.getFirstLettersLo(s));
            sdMsg.add(ChineseToPinyinUtil.getFirstLettersUp(s));
        }
    }

    @Override
    public Result sendGroupMsg(GroupMsg groupMsg, HttpServletRequest request) {
        //groupMsg.setAuto_escape(true);
        Result result = restTemplate.postForObject(URLConst.URL + URLConst.SEND_GROUP_MSG, groupMsg, Result.class);
        return result;
    }


    public ReplyMsg handleGroupMsg(ReceiveMsg receiveMsg, HttpServletRequest request) {
        String raw_message = receiveMsg.getRaw_message().replaceAll(" ","");
        //System.out.println("获得到的消息："+raw_message);

        if(zprMsg.contains(raw_message)){
            zprService.sendZprByMsg(receiveMsg,request,"zpr");
        }

        if(sdMsg.contains(raw_message)){
            zprService.sendZprByMsg(receiveMsg,request,"sd");
        }

        //只有at我的才可以触发
        if (raw_message.contains("CQ:at,qq=198915761")) {
            //查询指令是否标准，不标准则反馈提示
            if (MsgUtil.getMenu(raw_message) != null) {
                ReplyMsg replyMsg = new ReplyMsg();
                replyMsg.setReply(MsgUtil.getMenu(raw_message));
                return replyMsg;
            }
        }
        return null;
    }
}
