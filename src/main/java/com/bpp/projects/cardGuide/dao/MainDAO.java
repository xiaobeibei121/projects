package com.bpp.projects.cardGuide.dao;

import com.bpp.projects.cardGuide.entity.Detail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainDAO {

    List<Detail> selectMain(@Param("date") String date, @Param("tag")Integer tag);

}
