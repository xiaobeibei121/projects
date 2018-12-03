package com.bpp.projects.cardAdmin.service;

import com.bpp.projects.cardAdmin.dao.BankDAO;
import com.bpp.projects.cardAdmin.entity.Bank;
import com.bpp.projects.cardGuide.commons.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankService {
    @Autowired
    private BankDAO bankDAO;

    public ResponseData getBanks() {
        List<Bank> banks = new ArrayList<Bank>();
        banks = bankDAO.getAllBank();

        // 数据返回处理
        ResponseData result = new ResponseData();
        int errorCode = 0;
        String errorMsg = null;
        if (banks == null) {
            errorCode = -1;
            errorMsg = "获取数据失败";
        } else {
            errorMsg = "获取数据成功";
        }
        result.setErrorCode(errorCode);
        result.setErrorMsg(errorMsg);
        result.setData(banks);
        return result;
    }
}
