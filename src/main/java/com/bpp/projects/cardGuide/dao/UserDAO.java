package com.bpp.projects.cardGuide.dao;

import com.bpp.projects.cardGuide.entity.User;
import org.apache.ibatis.annotations.Insert;
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
     * 当有openid的时候就插入openid
     * @param openid
     * @return
     */
    @Insert("INSERT INTO usersTable(openid,createTime,lastVisitTime) VALUES(#{openid},#{createTime},#{lastVisitTime})")
    int inserUserOpenid(@Param("openid") String openid, @Param("createTime") Date createTime, @Param("lastVisitTime") Date lastVisitTime);

    /**
     * 更新用户
     * @param user
     * @return
     */
    @Update("UPDATE usersTable SET lastVisitTime=#{user.lastVisitTime},nickName=#{user.nickName},avatarUrl=#{user.avatarUrl},country=#{user.country},province=#{user.province},city=#{user.city},gender=#{user.gender},language=#{user.language} WHERE openid=#{user.openid}")
    int updateUser(@Param("user") User user);

    /**
     * 更新访问时间
     * @param openid
     * @param lastVisitTime
     * @return
     */
    @Update("UPDATE usersTable SET lastVisitTime=#{lastVisitTime} WHERE openid=#{openid}")
    int updateUserVisit(@Param("openid") String openid, @Param("lastVisitTime") Date lastVisitTime);
}
