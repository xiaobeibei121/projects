package com.bpp.projects.cardGuide.controllers;

import com.bpp.projects.cardGuide.Service.UserService;
import com.bpp.projects.cardGuide.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/openID")
    public Map<String,String> getOpenID () {

        Map<String,String> userResult = userDAO.getOpenID("a","a","a","a");
        return userResult;
    }
}
