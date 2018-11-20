package com.bpp.projects.cardGuide.dao;

import com.bpp.projects.cardGuide.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface UserDAO {

    /**
     * 插入用户数据
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 查询用户数据
     * @param openid
     * @return
     */
    @Select("SELECT * FROM usersTable WHERE openid=#{openid}")
    User getUser(@Param("openid") String openid);

    /**
     * 查询某个openid是否存在
     * @param openid
     * @return
     */
    @Select("SELECT count(*) FROM usersTable WHERE openid=#{openid}")
    int isExistUser(@Param("openid") String openid);

    /**
     * 更新用户
     * @param openid
     * @param lastVisitTime
     * @return
     */
    @Update("UPDATE usersTable SET lastVisitTime=#{lastVisitTime}  WHERE openid=#{openid}")
    int updateUser(@Param("openid") String openid, @Param("lastVisitTime") Date lastVisitTime);
}
