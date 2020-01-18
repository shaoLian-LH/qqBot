package com.sl.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author：邵莲
 * @Date：2019/11/13 15:24
 */
public class UpLoadUtil {

    //文件上传工具类
    public static String uploadFile(MultipartFile upload, HttpServletRequest request,String customPath){
        //指定位置
        //String path = request.getSession().getServletContext().getRealPath(customPath);
        String path = customPath;
        //System.out.println("目标位置："+path);
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }

        String fileName = upload.getOriginalFilename();
        fileName = fileName.substring(fileName.indexOf("."));
        String uuid = UUID.randomUUID().toString().replace("-","");
        fileName =uuid +"_"+ fileName;
        try {
            upload.transferTo(new File(path,fileName));
            //ImageDownloadUtil.copyFile(path,path+".cqimg");
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    //删除文件
    public static void deleteFile(String fileName, HttpServletRequest request, String customPath){
        //指定位置
        String path = request.getSession().getServletContext().getRealPath(customPath) + fileName;
        File file = new File(path);
        if(file.exists()){
            file.delete();
        }
    }

}
