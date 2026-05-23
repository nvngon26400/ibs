package com.sbisec.helios.ap.bizcommon.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbisec.helios.ap.bizcommon.dao.Fct027Dao;
import com.sbisec.helios.ap.bizcommon.dao.mapper.Fct027Mapper;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct027Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct027Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct027Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct027Sql002ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct027Sql003RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct027Sql003ResponseModel;

/**
 * 国内株式情報取得 DAO 実行クラス

 * @author SCSK

 */
@Component
public class Fct027DaoImpl implements Fct027Dao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Fct027DaoImpl.class);
    
    @Autowired
    private Fct027Mapper fct027Mapper;

    @Override
    public List<Fct027Sql001ResponseModel> getFct027Sql001(Fct027Sql001RequestModel fct027Sql001RequestModel) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Fct027Sql001ResponseModel.getFct027Sql001");
        }
        return fct027Mapper.getFct027Sql001(fct027Sql001RequestModel);
    }

    @Override
    public List<Fct027Sql002ResponseModel> getFct027Sql002(Fct027Sql002RequestModel fct027Sql002RequestModel) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Fct027Sql001ResponseModel.getFct027Sql002");
        }
        return fct027Mapper.getFct027Sql002(fct027Sql002RequestModel);
    }
    
    @Override
    public List<Fct027Sql003ResponseModel> getFct027Sql003(Fct027Sql003RequestModel fct027Sql003RequestModel) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Fct027Sql003ResponseModel.getFct027Sql003");
        }
        return fct027Mapper.getFct027Sql003(fct027Sql003RequestModel);
    }

}

