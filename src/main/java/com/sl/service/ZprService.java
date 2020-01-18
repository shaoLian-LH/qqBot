package com.sl.service;

import com.sl.enums.OperationTag;
import com.sl.model.ReceiveMsg;
import com.sl.model.ReplyMsg;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author：邵莲
 * @Date：2020/1/9 20:28
 */
public interface ZprService {
    ReplyMsg sendZprByMsg(ReceiveMsg receiveMsg,HttpServletRequest request, String targetDir);

    public OperationTag uploadZprPic(MultipartFile upload, HttpServletRequest request, String targetDir);
}
