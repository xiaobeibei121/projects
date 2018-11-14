package com.bpp.projects.cardGuide.controllers;

import com.bpp.projects.cardGuide.dao.MainDAO;
import com.bpp.projects.cardGuide.entity.Detail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("card")
public class MainController {

    @Autowired
    private MainDAO mainDAO;

    @GetMapping("/mains")
    public Map<String, Object> getMainList(@RequestParam(required = false) String date) {

        String temp_str = date;

        // 判断date是否传过来，若为 null ，则选择当前的日期
        if (temp_str == null) {
            Date dt = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            temp_str = sdf.format(dt);
        }

        // 创建map
        Map<String, Object> mainObj = new HashMap<String, Object>();
        mainObj.put("date", temp_str);

        List<Detail> details = mainDAO.selectMain(temp_str);

        Map<Integer, List<Detail>> result = details.stream().collect(Collectors.groupingBy(Detail::getTag));

        mainObj.put("details", result);
        return mainObj;
    }
}
