package com.sl.util;

public class MsgUtil {

    //有人加入了群
    //{"group_id":865452446,"notice_type":"group_increase","operator_id":2455279933,"post_type":"notice","self_id":1767638734,"sub_type":"approve","time":1562212021,"user_id":1553060962}

    /**
     * 判断是否是已知的指令
     *
     * @param raw_message
     * @return
     */
    public static boolean isKnownMsg(String raw_message) {
        String[] knownMsg = new String[]{
                "纸片人",
        };
        for (String temp : knownMsg) {
            if (raw_message.contains(temp)) {
                return true;
            }
        }
        return false;
    }

    public static String getMenu(String raw_message) {
        StringBuilder str = new StringBuilder();
        if (!isKnownMsg(raw_message)) {
            str.append("目前支持的指令有:" );
            str.append("纸片人");
        } 

        return str.length() == 0 ? null : str.toString();
    }

}

