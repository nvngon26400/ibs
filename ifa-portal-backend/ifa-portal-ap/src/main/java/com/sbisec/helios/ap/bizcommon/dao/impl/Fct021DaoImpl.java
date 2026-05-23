package com.sbisec.helios.ap.bizcommon.dao.impl;

import java.util.List;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.bizcommon.dao.Fct021Dao;
import com.sbisec.helios.ap.bizcommon.dao.mapper.Fct021Mapper;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct021Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct021Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct021Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct021Sql002ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 取引制限チェックDAO
 *
 * @author SCSK
 *
 */
@Component
public class Fct021DaoImpl extends RowSelectableDao implements Fct021Dao {
    
    @Autowired
    private Fct021Mapper mapper;
    
    @Override
    public List<Fct021Sql001ResponseModel> getFct021Sql001(Fct021Sql001RequestModel fct021Sql001RequestModel) {
        
        return mapper.getFct021Sql001(fct021Sql001RequestModel);
    }
    
    @Override
    public List<Fct021Sql002ResponseModel> getFct021Sql002(Fct021Sql002RequestModel fct021Sql002RequestModel) {
        
        return mapper.getFct021Sql002(fct021Sql002RequestModel);
    }
    
}
