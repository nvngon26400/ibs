package com.sbisec.helios.ap.bizcommon.dao.impl;

import com.sbisec.helios.ap.bizcommon.dao.Fct009Dao;
import com.sbisec.helios.ap.bizcommon.dao.mapper.Fct009Mapper;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct009Sql001RequestModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 共通関数Dao：FCT009

 * @author SCSK
 */

@Component
public class Fct009DaoImpl implements Fct009Dao {
    
    @Autowired
    private Fct009Mapper mapper;

    /**
     * 共同募集顧客(共募顧客)チェック
     *
     * @param model リクエストモデル
     * @return Integer　件数
     */
    @Override
    public int jointSubscriptCustomerCheck(Fct009Sql001RequestModel model) {
        return mapper.jointSubscriptCustomerCheck(model.getButenCode(), model.getAccountNumber());
    }

}
