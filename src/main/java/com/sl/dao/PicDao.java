package com.sl.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import sun.reflect.CallerSensitive;

/**
 * @author：邵莲
 * @Date：2020/1/9 20:50
 */
@Repository("picDao")
@Mapper
public interface PicDao {
    @Select("SELECT getPicName()")
    public String getPicName();

    @Insert("INSERT INTO pic VALUES(#{fileName})")
    public Boolean addPic(String fileName);
}
