package com.bpp.projects.cardGuide.controllers;

import com.bpp.projects.cardGuide.Service.MainService;
import com.bpp.projects.cardGuide.commons.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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
}
