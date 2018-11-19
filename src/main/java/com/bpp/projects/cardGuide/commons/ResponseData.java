package com.bpp.projects.cardGuide.commons;

import lombok.Data;

@Data
public class ResponseData<T> {
    private int errorCode;

    private String errorMsg;

    private T data;
}
