package com.bpp.projects.cardGuide.Service;

import com.bpp.projects.cardGuide.commons.ResponseData;
import com.bpp.projects.cardGuide.dao.UserDAO;
import com.bpp.projects.cardGuide.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private OkHttpClient client;

    @Autowired
    private UserDAO userDAO;

    /**
     * 通过code获取openid
     * @param code
     * @return
     * @throws IOException
     */
    public ResponseData getOpenID(String code) throws IOException {

        // 用OkHttpClient获取微信数据
        Request request = new Request.Builder()
                .url("https://api.weixin.qq.com/sns/jscode2session?appid=wxa89701c001079b53&secret=4b8721037ceb8a57a72c42c8a43020a9&js_code=" + code + "&grant_type=authorization_code")
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("服务器端错误: " + response);
        }

        // 获取返回值
        String res =response.body().string();
        // jackson
        ObjectMapper mapper = new ObjectMapper();
        Map<String,?> resMap = mapper.readValue(res, HashMap.class);
        // 获取openid
        String openid = (String) resMap.get("openid");

        // 数据返回处理
        ResponseData result = new ResponseData();
        Map<String,Object> data = new HashMap<>();
        int errorCode = 0;
        String errorMsg = null;
        if (openid == null) {
            errorCode = Integer.parseInt((String)resMap.get("errcode"));
            errorMsg = (String)resMap.get("errmsg");
        } else {
            errorMsg = "获取成功";
            // 判断用户是否存在
            int isExistUser = userDAO.isExistUser(openid);
            if (isExistUser <= 0) { // 若不存在，则插入数据
                userDAO.inserUserOpenid(openid, new Date(), new Date());
            } else {
                userDAO.updateUser(openid, new Date());
            }
            data.put("isExistUser", 1);
        }
        data.put("openid", openid);
        result.setErrorCode(errorCode);
        result.setErrorMsg(errorMsg);
        result.setData(data);

        return result;
    }

    /**
     * 插入用户数据
     * @param user
     * @return
     */
    public ResponseData insertUser(User user) {

        // 插入数据
        user.setCreateTime(new Date());
        user.setLastVisitTime(new Date());
        int resultNum = userDAO.insertUser(user);

        int errorCode = 0;
        String errorMsg = null;
        if (resultNum <= 0) {
            errorCode = -1;
            errorMsg = "用户数据新增失败";
        } else {
            errorMsg = "用户数据新增成功";
        }

        // 数据返回处理
        ResponseData result = new ResponseData();
        result.setErrorCode(errorCode);
        result.setErrorMsg(errorMsg);
        result.setData(resultNum);

        return result;
    }

    /**
     * 获取用户信息
     * @param openid
     * @return
     */
    public ResponseData getUser(String openid) {
        User user = userDAO.getUser(openid);

        // 数据返回处理
        ResponseData result = new ResponseData();
        int errorCode = 0;
        String errorMsg = null;
        if (user == null) {
            errorCode = -1;
            errorMsg = "获取用户数据失败";
        } else {
            errorMsg = "获取用户数据成功";
        }
        result.setErrorCode(errorCode);
        result.setErrorMsg(errorMsg);
        result.setData(user);
        return result;
    }

    /**
     * 更新用户数据
     * @param openid
     * @return
     */
    public ResponseData updateUser(String openid) {
        // 更新用户数据
        int resultNum = userDAO.updateUser(openid, new Date());

        int errorCode = 0;
        String errorMsg = null;
        if (resultNum <= 0) {
            errorCode = -1;
            errorMsg = "用户数据更新失败";
        } else {
            errorMsg = "用户数据更新成功";
        }

        // 数据返回处理
        ResponseData result = new ResponseData();
        result.setErrorCode(errorCode);
        result.setErrorMsg(errorMsg);
        result.setData(resultNum);

        return result;
    }
}
