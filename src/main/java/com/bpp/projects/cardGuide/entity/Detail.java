package com.bpp.projects.cardGuide.entity;

import lombok.Data;

@Data
public class Detail {

    private int id;

    private int parentID;

    private int tag;

    private String messages;
}
