package com.sl.enums;

/**
 * @author：邵莲
 * @Date：2020/1/10 8:47
 */
public enum OperationTag {

    UPLOAD_SUCCESS(1000,"图片上传成功"),
    UPLOAD_FAILED(1001,"图片上传失败");

    private Integer code;
    private String msg;

    OperationTag(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
