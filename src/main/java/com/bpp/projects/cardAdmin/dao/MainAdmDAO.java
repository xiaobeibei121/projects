package com.bpp.projects.cardAdmin.dao;

import com.bpp.projects.cardAdmin.entity.MainAdm;
import com.bpp.projects.cardGuide.entity.Detail;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainAdmDAO {
    /**
     * 查找数据
     * @param startDate
     * @param endDate
     * @return
     */
    List<MainAdm> getDateLists(@Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 插入主数据
     * @param mainAdm
     * @return
     */
    int insertMain(MainAdm mainAdm);

    /**
     * 插入详情数据
     * @param detail
     * @return
     */
    int insertDetail(Detail detail);

    /**
     * 更新发布状态
     * @param id
     * @param status
     * @return
     */
    @Update("UPDATE mainTable set status=#{status} where id=#{id}")
    int upMains(@Param("id") Integer id,@Param("status") Integer status);

    /**
     * 获取信息信息
     * @param id
     * @return
     */
    List<Detail> selectMainAdm(@Param("id") Integer id);

    @Delete("DELETE FROM detailTable where parentID=#{id}")
    int deleteDetail(@Param("id") Integer id);
}
