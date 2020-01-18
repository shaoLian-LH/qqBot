package com.sl.service.impl;

import com.sl.constant.URLConst;
import com.sl.dao.PicDao;
import com.sl.dao.SDPicDao;
import com.sl.enums.OperationTag;
import com.sl.model.GroupMsg;
import com.sl.model.ReceiveMsg;
import com.sl.model.ReplyMsg;
import com.sl.service.GroupMsgService;
import com.sl.service.ZprService;

import com.sl.util.RightsUtil;
import com.sl.util.UpLoadUtil;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


/**
 * @author：邵莲
 * @Date：2020/1/9 20:30
 */
@Log
@Service
@Transactional
public class ZprServiceImpl implements ZprService {

    @Autowired
    GroupMsgService groupMsgService;

    @Autowired
    PicDao picDao;

    @Autowired
    SDPicDao sdPicDao;

    @Override
    public ReplyMsg sendZprByMsg(ReceiveMsg receiveMsg,HttpServletRequest request, String targetDir) {
        String key = null;
        String message = "?";
        switch (targetDir){
            case "zpr":{
                key = picDao.getPicName();
                message = "[CQ:image,file=zpr/"  + key + "]";
            }break;
            case "sd":{
                key = sdPicDao.getPicName();
                message = "[CQ:image,file=sd/"  + key + "]";
            }break;
        }
        GroupMsg groupMsg = new GroupMsg();
        groupMsg.setMessage(message);
        groupMsg.setGroup_id(receiveMsg.getGroup_id());
        groupMsgService.sendGroupMsg(groupMsg,request);
        return null;
    }

    @Override
    public OperationTag uploadZprPic(MultipartFile upload, HttpServletRequest request, String targetDir) {
       try {
           String name = UpLoadUtil.uploadFile(upload,request,URLConst.DATA_IMG_PATH + targetDir +"/");
           Boolean result = false;
           switch (targetDir){
               case "zpr":{
                   result = picDao.addPic(name);
               }break;
               case "sd":{
                   result = sdPicDao.addPic(name);
               }break;
           }
           if(result == Boolean.TRUE){
               String newFilePath = URLConst.DATA_IMG_PATH + targetDir +"/" + name;
               RightsUtil.givePrevilege(newFilePath);
               return OperationTag.UPLOAD_SUCCESS;
           }else{
               return OperationTag.UPLOAD_FAILED;
           }
       }catch (RuntimeException e){
           return OperationTag.UPLOAD_FAILED;
       }
    }
}
