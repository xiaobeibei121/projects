package com.bpp.projects.cardAdmin.controllers;

import com.bpp.projects.cardAdmin.service.MainAdmService;
import com.bpp.projects.cardGuide.commons.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("cardAdmin")
public class MainAdmController {
    @Autowired
    private MainAdmService mainAdmService;

    @GetMapping("/dateLists")
    public ResponseData getBanks (@RequestParam(required = false) String startDate,
                                  @RequestParam(required = false) String endDate) {
        ResponseData resultDate = null;
        resultDate = mainAdmService.getDateLists(startDate, endDate);
        return resultDate;
    }

    @PutMapping("/mainData")
    public ResponseData insertMains(@RequestBody Object obj) {
        ResponseData resultDate = null;
        resultDate = mainAdmService.insertMains(obj);
        return resultDate;
    }

    @PostMapping("/mainData")
    public ResponseData updateMains(@RequestBody Object obj) {
        ResponseData resultDate = null;
        resultDate = mainAdmService.updateMains(obj);
        return resultDate;
    }

    @PostMapping("/upMains")
    public ResponseData upMain(@RequestParam(required = true) Integer id,
                                    @RequestParam(required = true) Integer status){
        ResponseData resultDate = null;
        resultDate = mainAdmService.upMains(id, status);
        return resultDate;
    }

    @GetMapping("/selectMainAdm")
    public ResponseData selectMainAdm(@RequestParam(required = true) Integer id){
        ResponseData resultDate = null;
        resultDate = mainAdmService.selectMainAdm(id);
        return resultDate;
    }
}
