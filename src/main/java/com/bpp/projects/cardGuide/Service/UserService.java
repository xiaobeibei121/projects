package com.bpp.projects.cardGuide.Service;

import com.bpp.projects.cardGuide.dao.UserDAO;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService implements UserDAO {

    @Override
    public Map<String,String> getOpenID(String appid, String secret, String js_code, String grant_type) {
        return null;
    }
}
