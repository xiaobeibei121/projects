package com.bpp.projects.cardGuide.dao;

import com.bpp.projects.cardGuide.entity.Bank;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankDAO {
    @Select("SELECT * FROM banks")
    List<Bank> getAllBank();
}
