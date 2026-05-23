package com.sbisec.helios.ap.bizcommon.dao;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct003Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct003Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct003Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct003Sql002ResponseModel;

/**
 * @author 鄒
 *
 */
public interface Fct003Dao {
    
    public List<Fct003Sql001ResponseModel> getFct003Sql001(Fct003Sql001RequestModel fct003Sql001RequestModel);
    
    public List<Fct003Sql002ResponseModel> getFct003Sql002(Fct003Sql002RequestModel fct003Sql002RequestModel);
    
}
