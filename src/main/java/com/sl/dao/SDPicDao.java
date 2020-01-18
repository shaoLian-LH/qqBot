package com.sl.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author：邵莲
 * @Date：2020/1/18 20:14
 */
@Repository("sdPicDao")
@Mapper
public interface SDPicDao {
    @Select("SELECT getSDPicName()")
    public String getPicName();

    @Insert("INSERT INTO sd_pic VALUES(#{fileName})")
    public Boolean addPic(String fileName);
}
