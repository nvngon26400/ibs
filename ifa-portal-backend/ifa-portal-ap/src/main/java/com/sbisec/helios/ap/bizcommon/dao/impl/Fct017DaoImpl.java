package com.sbisec.helios.ap.bizcommon.dao.impl;

import com.sbisec.helios.ap.bizcommon.dao.Fct017Dao;
import com.sbisec.helios.ap.bizcommon.dao.mapper.Fct017Mapper;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct017Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct017Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct017Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct017Sql003RequestModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 共通関数DAO：FCT017
 * 確認書受入および強制注文が必要な国内投信銘柄情報取得

 * @author SCSK
 */

@Component
public class Fct017DaoImpl implements Fct017Dao {
    
    @Autowired
    private Fct017Mapper mapper;

    @Override
    public Fct017Sql001ResponseModel acceptanceAcquire(Fct017Sql001RequestModel model) {
        return mapper.acceptanceAcquire(model.getNriCd());
    }

    @Override
    public int brandDocumentAcceptanceAcquire(Fct017Sql002RequestModel model) {
        return mapper.brandDocumentAcceptanceAcquire(model.getButenCode(), 
                model.getAccountNumber(), 
                model.getBrandCode(), 
                model.getShoruiCd());
    }

    @Override
    public int commonDocumentAcceptanceAcquire(Fct017Sql003RequestModel model) {
        return mapper.commonDocumentAcceptanceAcquire(model.getButenCode(), 
                model.getAccountNumber(), 
                model.getShoruiCd());
    }
    
}
