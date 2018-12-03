package com.bpp.projects.cardAdmin.controllers;

import com.bpp.projects.cardAdmin.service.BankService;
import com.bpp.projects.cardGuide.commons.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("cardAdmin")
public class BankController{

    @Autowired
    private BankService bankService;

    @GetMapping("/banks")
    public ResponseData getBanks () {
        ResponseData resultDate = null;
        resultDate = bankService.getBanks();
        return resultDate;
    }
}
