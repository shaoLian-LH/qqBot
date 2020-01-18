package com.sl.util;

import com.sl.constant.URLConst;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

/**
 * @author：邵莲
 * @Date：2020/1/9 20:32
 */
public class ImageDownloadUtil {
    /**
     * 下载指定图片
     *
     * @param key
     * @throws Exception
     */
    public static void downloadImg(String key,HttpServletRequest request) throws Exception {
        //String path = request.getSession().getServletContext().getRealPath(URLConst.DATA_IMG_PATH);
        //代表酷Q的路径
        String path = URLConst.APP_PRE_PATH;
        //System.out.println("downloadImg -- 存放路径："+path);
        //new一个URL对象
        URL url = new URL(URLConst.PIC_PRE_SERVICE + key);
        //System.out.println("获得到的对象："+url);
        //打开链接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置请求方式为"GET"
        conn.setRequestMethod("GET");
        //超时响应时间为7秒
        conn.setConnectTimeout(7 * 1000);
        //通过输入流获取图片数据
        InputStream inStream = conn.getInputStream();
        //得到图片的二进制数据，以二进制封装得到数据，具有通用性
        byte[] data = readInputStream(inStream);
        //new一个文件对象用来保存图片
        //String uuid = UUID.randomUUID().toString().replaceAll("-","").toString();
        //System.out.println("下载的文件："+path + key);
        File imageFile = new File(path + key);

        //创建输出流
        FileOutputStream outStream = new FileOutputStream(imageFile);
        //写入数据
        outStream.write(data);
        //关闭输出流
        outStream.close();

        //创建输出流
        copyFile(path + key,path + key );
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }

    public static boolean isImgExist(String key) {
        File file = new File(URLConst.APP_PRE_PATH  + key);
        return file.exists();
    }

    public static boolean copyFile(String srcFile, String targetFile){
        File in = new File(srcFile);
        File out = new File(targetFile);
        if(!in.exists()){
            return false;
        }
        if(!out.exists()){
            try {
                out.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //File[] file = in.listFiles();
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
        fis = new FileInputStream(in);
        fos = new FileOutputStream(new File(targetFile));
        } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    int c;
    byte[] b = new byte[1024*5];
    try {
            while((c = fis.read(b))!=-1){
            fos.write(b, 0, c);}
            fis.close();
            fos.flush();
            fos.close();
        } catch (IOException e) {

       }
        return false;
    }

}
