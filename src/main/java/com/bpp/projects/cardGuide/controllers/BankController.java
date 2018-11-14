package com.bpp.projects.cardGuide.controllers;

import com.bpp.projects.cardGuide.dao.BankDAO;
import com.bpp.projects.cardGuide.entity.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.ArrayList;

@RestController
public class BankController{

    @Autowired
    private BankDAO bankDAO;

    @GetMapping("/getBanks")
    public List<Bank> getBanks () {
        List<Bank> banks = new ArrayList<Bank>();
        banks = bankDAO.getAllBank();
        return banks;
    }
}
