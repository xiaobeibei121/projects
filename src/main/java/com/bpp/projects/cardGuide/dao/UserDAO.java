package com.bpp.projects.cardGuide.dao;

import com.bpp.projects.cardGuide.Service.UserService;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Repository
@FeignClient(value = "api.weixin.qq.com", fallback = UserService.class)
public interface UserDAO {

    @RequestMapping(value = "/sns/jscode2session",method = RequestMethod.GET)
    Map<String,String> getOpenID(@RequestParam(name="appid") String appid,@RequestParam(name="secret") String secret,@RequestParam(name="js_code") String js_code,@RequestParam(name="grant_type") String grant_type);
}
