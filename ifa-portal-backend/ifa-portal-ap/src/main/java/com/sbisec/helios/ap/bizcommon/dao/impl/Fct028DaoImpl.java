package com.sbisec.helios.ap.bizcommon.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbisec.helios.ap.bizcommon.dao.Fct028Dao;
import com.sbisec.helios.ap.bizcommon.dao.mapper.Fct028Mapper;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct028Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct028Sql001ResponseModel;

/**
 *
 * @author 鄒
 *
 */
@Component
public class Fct028DaoImpl implements Fct028Dao {
    
    @Autowired
    private Fct028Mapper fct028Mapper;
    
    @Override
    public List<Fct028Sql001ResponseModel> getFct028Sql001(Fct028Sql001RequestModel fct028Sql001RequestModel) {
        
        return fct028Mapper.getFct028Sql001(fct028Sql001RequestModel);
    }
    
}
