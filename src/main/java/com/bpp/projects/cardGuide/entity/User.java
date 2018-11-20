package com.bpp.projects.cardGuide.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
public class User {

    private String nickName;

    private String avatarUrl;

    private String country;

    private String province;

    private String city;

    private int gender;

    private String language;

    private Date createTime;

    private String openid;

    private Date lastVisitTime;
}
