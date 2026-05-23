package com.sbisec.helios.ap.bizcommon.dao.impl;

import com.sbisec.helios.ap.bizcommon.dao.Fct038Dao;
import com.sbisec.helios.ap.bizcommon.dao.mapper.Fct038Mapper;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct038Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct038Sql001ResponseModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 共通関数：FCT038
 * CSVダウンロードMAX件数取得
 *
 * @author SCSK
 */
@Component
public class Fct038DaoImpl implements Fct038Dao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Fct038DaoImpl.class);
    
    @Autowired
    protected Fct038Mapper fct038Mapper;
    
    /**
     * CSVダウンロードMAX件数取得する。
     *
     * @param fct038Sql001RequestModel リクエスト
     * @return レスポンス
     * @throws Exception CSVダウンロードMAX件数取得に例外が発生した場合
     */
    @Override
    public Fct038Sql001ResponseModel getCsvMax(Fct038Sql001RequestModel fct038Sql001RequestModel)
            throws Exception {
        
        return fct038Mapper.getCsvMax(fct038Sql001RequestModel);
    }
    
}
