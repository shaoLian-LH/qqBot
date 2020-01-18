package com.sl.controller;

import com.sl.service.ZprService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author：邵莲
 * @Date：2020/1/10 9:17
 */
@RestController
public class PicController {

    @Autowired
    ZprService zprService;

    static ArrayList<String> validArrs = new ArrayList<>();
    static {
        validArrs.add("zpr");
        validArrs.add("sd");
    }

    @RequestMapping("/addPic")
    public Map<Object,String> uploadMyPic(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String targetDir = request.getParameter("targetDir");
        Map<Object, String> json = new HashMap<Object, String>();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        if(!validArrs.contains(targetDir)){
            json.put("code","2000");
            return json;
        }

        /** 页面控件的文件流* */
        MultipartFile multipartFile = null;
        Map map =multipartRequest.getFileMap();
        for (Iterator i = map.keySet().iterator(); i.hasNext();) {
            Object obj = i.next();
            multipartFile=(MultipartFile) map.get(obj);
            zprService.uploadZprPic(multipartFile,request,targetDir);
        }
        json.put("code","1000");
        return json;
    }
}
