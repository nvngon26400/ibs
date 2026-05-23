package com.sbisec.helios.ap.bizcommon.dao.impl;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.dao.Fct029Dao;
import com.sbisec.helios.ap.bizcommon.dao.mapper.Fct029Mapper;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct029Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct029Sql001ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author 鄒
 *
 */
@Component
public class Fct029DaoImpl implements Fct029Dao {
    
    @Autowired
    private Fct029Mapper fct029Mapper;
    
    @Override
    public Fct029Sql001ResponseModel getFct029Sql001(Fct029Sql001RequestModel fct029Sql001RequestModel) {
        return fct029Mapper.getFct029Sql001(fct029Sql001RequestModel);
    }
    
}
