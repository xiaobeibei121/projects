package com.bpp.projects.cardAdmin.entity;

import lombok.Data;

import java.util.Date;

@Data
public class MainAdm {

    private int id;

    private String Date;

    private String nickName;

    private Date createTime;

    private Integer status;

    private String statusText;
}
