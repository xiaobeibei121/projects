package com.bpp.projects.cardGuide.controllers;

import com.bpp.projects.cardGuide.Service.MainService;
import com.bpp.projects.cardGuide.commons.ResponseData;
import com.bpp.projects.cardGuide.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("card")
public class MainController {

    @Autowired
    MainService mainService;

    @GetMapping("/mains")
    public ResponseData getMainList(@RequestParam(required = false) String date,
                                    @RequestParam(required = false) Integer tag) {
        ResponseData resultDate = null;
        resultDate = mainService.getMainList(date,tag);
        return resultDate;
    }

    @PostMapping("/bi")
    public String postBI(@RequestBody String str) {
        System.out.println(str);
        return "true";
    }
}
