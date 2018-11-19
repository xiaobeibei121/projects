package com.bpp.projects.cardGuide.dao;

import com.bpp.projects.cardGuide.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {
    int insertUser(User user);
}
