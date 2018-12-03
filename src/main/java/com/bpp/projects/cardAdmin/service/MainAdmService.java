package com.bpp.projects.cardAdmin.service;

import com.bpp.projects.cardAdmin.dao.MainAdmDAO;
import com.bpp.projects.cardAdmin.entity.MainAdm;
import com.bpp.projects.cardGuide.commons.ResponseData;
import com.bpp.projects.cardGuide.entity.Detail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MainAdmService {
    @Autowired
    private MainAdmDAO mainAdmDAO;

    /**
     * 列表数据
     * @param startDate
     * @param endDate
     * @return
     */
    public ResponseData getDateLists(String startDate,String endDate) {
        List<MainAdm> mains = new ArrayList<MainAdm>();
        mains = mainAdmDAO.getDateLists(startDate, endDate);
        for (int i = 0; i<mains.size();i++){
            MainAdm mainAdm = mains.get(i);
            mainAdm.setStatusText(mainAdm.getStatus() == 0 ? "未发布": "已发布");
        }

        // 数据返回处理
        ResponseData result = new ResponseData();
        int errorCode = 0;
        String errorMsg = null;
        if (mains == null) {
            errorCode = -1;
            errorMsg = "获取数据失败";
        } else {
            errorMsg = "获取数据成功";
        }
        result.setErrorCode(errorCode);
        result.setErrorMsg(errorMsg);
        result.setData(mains);
        return result;
    }

    /**
     * 新增数据
     * @param obj
     * @return
     */
    public ResponseData insertMains(Object obj) {
//        {date:"", data:[{tag:'0', name: '推荐', messages:['']},{tag:'', name: '', messages:['']}]}
        Map<String, Object> objMap= (Map<String, Object>) obj;
        MainAdm mainAdm = new MainAdm();
        mainAdm.setStatus(0);
        mainAdm.setDate((String) objMap.get("date"));
        mainAdm.setCreateTime(new Date());
        int isSuccess = mainAdmDAO.insertMain(mainAdm);

        List<Object> detailList = (List<Object>) objMap.get("data");
        for (int i = 0; i<detailList.size();i++){
            Map<String,Object> detailObj= (Map<String,Object>) detailList.get(i);
            List<String> messages = (List<String>) detailObj.get("messages");
            for(int j = 0; j< messages.size(); j++) {
                Detail detail = new Detail();
                detail.setTag(Integer.parseInt(detailObj.get("tag").toString()));
                detail.setMessages(messages.get(j));
                detail.setParentID(mainAdm.getId());
                mainAdmDAO.insertDetail(detail);
            }
        }
        // 数据返回处理
        ResponseData result = new ResponseData();
        int errorCode = 0;
        String errorMsg = null;
        if (isSuccess == 0) {
            errorCode = -1;
            errorMsg = "新增数据失败";
        } else {
            errorMsg = "新增数据成功";
        }
        result.setErrorCode(errorCode);
        result.setErrorMsg(errorMsg);
        result.setData(mainAdm.getId());
        return result;
    }

    /**
     * 更新状态
     * @param id
     * @param status
     * @return
     */
    public ResponseData upMains(Integer id, Integer status){
        int isSuccess = mainAdmDAO.upMains(id, status);
        // 数据返回处理
        ResponseData result = new ResponseData();
        int errorCode = 0;
        String errorMsg = null;
        if (isSuccess == 0) {
            errorCode = -1;
            errorMsg = "发布失败";
        } else {
            errorMsg = "发布成功";
        }
        result.setErrorCode(errorCode);
        result.setErrorMsg(errorMsg);
        result.setData(isSuccess);
        return result;
    }

    /**
     * 获取详细数据
     * @param id
     * @return
     */
    public ResponseData selectMainAdm(Integer id) {
        // 创建map
        Map<String, Object> mainObj = new HashMap<String, Object>();

        List<Detail> details = mainAdmDAO.selectMainAdm(id);

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

    public ResponseData updateMains(Object obj) {
//        {id:"", data:[{tag:'0', name: '推荐', messages:['']},{tag:'', name: '', messages:['']}]}
        Map<String, Object> objMap= (Map<String, Object>) obj;

        int deleteSuccess = mainAdmDAO.deleteDetail(Integer.parseInt(objMap.get("id").toString()));

        if (deleteSuccess>0) {
            List<Object> detailList = (List<Object>) objMap.get("data");
            for (int i = 0; i<detailList.size();i++){
                Map<String,Object> detailObj= (Map<String,Object>) detailList.get(i);
                List<String> messages = (List<String>) detailObj.get("messages");
                for(int j = 0; j< messages.size(); j++) {
                    Detail detail = new Detail();
                    detail.setTag(Integer.parseInt(detailObj.get("tag").toString()));
                    detail.setMessages(messages.get(j));
                    detail.setParentID(Integer.parseInt(objMap.get("id").toString()));
                    mainAdmDAO.insertDetail(detail);
                }
            }
        }
        // 数据返回处理
        ResponseData result = new ResponseData();
        int errorCode = 0;
        String errorMsg = null;
        if (deleteSuccess == 0) {
            errorCode = -1;
            errorMsg = "新增数据失败";
        } else {
            errorMsg = "新增数据成功";
        }
        result.setErrorCode(errorCode);
        result.setErrorMsg(errorMsg);
        result.setData(null);
        return result;
    }
}
