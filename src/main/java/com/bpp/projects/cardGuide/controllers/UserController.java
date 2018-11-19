package com.bpp.projects.cardGuide.controllers;

import com.bpp.projects.cardGuide.Service.UserService;
import com.bpp.projects.cardGuide.commons.ResponseData;
import com.bpp.projects.cardGuide.dao.UserDAO;
import com.bpp.projects.cardGuide.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/openid")
    public ResponseData getOpenID(@RequestParam(required = true) String code) {
        ResponseData resultDate = null;
        try {
            resultDate = userService.getOpenID(code);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultDate;
    }

    @PutMapping("/user")
    public ResponseData insertUser(@RequestBody User user) {
        ResponseData resultDate = null;
        resultDate = userService.insertUser(user);
        return resultDate;
    }
}
