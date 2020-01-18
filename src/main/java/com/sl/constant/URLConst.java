package com.sl.constant;

/**
 * 保存地址的常量类
 */
public class URLConst {

    /**
     * HTTP服务器地址
     */
    public static final String URL = "http://121.199.23.187:5700";
    //public static final String URL = "http://127.0.0.1:5700";
    /**
     * 发送私聊消息
     */
    public static final String SEND_PRIVATE_MSG = "/send_private_msg";

    /**
     * 发送群消息
     */
    public static final String SEND_GROUP_MSG = "/send_group_msg";

    /**
     * 获取群列表
     */
    public static final String GET_GROUP_LIST = "/get_group_list";


    /**
     * 图片服务器所在的前缀
     */
    public static final String PIC_PRE_SERVICE = "http://121.199.23.187:8081/static/images/";
    //public static final String PIC_PRE_SERVICE = "http://localhost:8080/static/images/";
    public static final String APP_PRE_PATH = "/usr/local/tomcat/webapps/botQ/data/image/";
    //public static final String APP_PRE_PATH = "E:\\botQ\\data\\image\\wb\\";
    /**
     * 服务器上图片存储的位置
     */
    //public static final String DATA_IMG_PATH = "WEB-INF/classes/static/images/";
    public static final String DATA_IMG_PATH = "/usr/local/tomcat/webapps/botQ/data/image/";

}
