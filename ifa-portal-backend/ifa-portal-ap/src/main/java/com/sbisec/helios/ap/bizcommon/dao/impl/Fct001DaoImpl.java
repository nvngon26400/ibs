package com.sbisec.helios.ap.bizcommon.dao.impl;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.dao.Fct001Dao;
import com.sbisec.helios.ap.bizcommon.dao.mapper.Fct001Mapper;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct001Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct001Sql001ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * FCT001 利用者顧客参照権限チェックDAO
 *
 * @author SCSK
 *
 */
@Component
public class Fct001DaoImpl implements Fct001Dao {
    
    @Autowired
    private Fct001Mapper fct001Mapper;
    
    /**
     * 利用者顧客参照権限チェック
     *
     * @param fct001Sql001RequestModel SQLパラメータ
     * @return 仲介業者顧客口座属性
     */
    @Override
    public List<Fct001Sql001ResponseModel> getUserCustomerRefAuth(Fct001Sql001RequestModel fct001Sql001RequestModel) {
        
        return fct001Mapper.getUserCustomerRefAuth(fct001Sql001RequestModel);
    }
    
}
