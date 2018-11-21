package com.bpp.projects.cardGuide.Service;

import com.bpp.projects.cardGuide.commons.ResponseData;
import com.bpp.projects.cardGuide.dao.MainDAO;
import com.bpp.projects.cardGuide.entity.Detail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MainService {

    @Autowired
    private MainDAO mainDAO;

    /**
     * 获取主页数据
     * @param date
     * @return
     */
    public ResponseData getMainList(String date, Integer tag) {
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

        List<Detail> details = mainDAO.selectMain(temp_str, tag);

        Map<Integer, List<Detail>> groupByResult = details.stream().collect(Collectors.groupingBy(Detail::getTag));

        List<Map<String, Object>> results= new ArrayList<>();
        for (List<Detail> value : groupByResult.values()) {
            Map<String, Object> resultDetail= new HashMap<String, Object>();
            resultDetail.put("name", ((Detail)value.get(0)).getName());
            resultDetail.put("tag", ((Detail)value.get(0)).getTag());
            List<String> messages = new ArrayList<String>();
            for (int i = 0; i<value.size();i++){
                messages.add(((Detail)value.get(i)).getMessages());
            }
            resultDetail.put("messages",messages);
            results.add(resultDetail);
        }

        mainObj.put("details", results);

        int errorCode = 0;
        String errorMsg = "请求成功";

        // 数据返回处理
        ResponseData result = new ResponseData();
        result.setErrorCode(errorCode);
        result.setErrorMsg(errorMsg);
        result.setData(mainObj);
        return result;
    }

}
