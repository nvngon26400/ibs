package com.sbisec.helios.ap.bizcommon.dao.impl;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.dao.Fct008Dao;
import com.sbisec.helios.ap.bizcommon.dao.mapper.Fct008Mapper;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct008Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct008Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct008Sql002ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct008Sql003RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct008Sql003ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 共通関数DAO：FCT008
 *
 * @author SCSK
 */
@Component
public class Fct008DaoImpl implements Fct008Dao {
    
    @Autowired
    private Fct008Mapper mapper;

    @Override
    public List<String> getBusinessDayList(Fct008Sql001RequestModel requestModel) {
        return mapper.getBusinessDayList();
    }

    @Override
    public Fct008Sql002ResponseModel getMarket(Fct008Sql002RequestModel requestModel) {
        return mapper.getMarket(requestModel.getBrandCode());
    }

    public Fct008Sql003ResponseModel getOverTime(Fct008Sql003RequestModel requestModel) {
        return mapper.getOverTime(requestModel);
    }
}
