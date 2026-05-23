package com.sbisec.helios.ap.bizcommon.dao.impl;

import com.sbisec.helios.ap.bizcommon.dao.Fct023Dao;
import com.sbisec.helios.ap.bizcommon.dao.mapper.Fct023Mapper;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct023Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct023Sql001ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 共通関数DAOImpl： FCT023
 *
 * @author 陳
 */
@Component
public class Fct023DaoImpl implements Fct023Dao {
    
    @Autowired
    private Fct023Mapper mapper;
    
    @Override
    public Fct023Sql001ResponseModel getFct023Sql001(Fct023Sql001RequestModel sql001Req) {
        
        return mapper.getFct023Sql001(sql001Req);
    }
    
}
