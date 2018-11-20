package com.bpp.projects.cardGuide.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(value = {"id", "parentID"})
public class Detail {

    private int id;

    private int parentID;

    private int tag;

    private String name;

    private String messages;
}
