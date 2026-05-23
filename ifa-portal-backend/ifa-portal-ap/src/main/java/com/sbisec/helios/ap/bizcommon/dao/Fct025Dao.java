package com.sbisec.helios.ap.bizcommon.dao;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct025Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct025Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct025Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct025Sql002ResponseModel;

/**
 * @author 鄒
 *
 */
public interface Fct025Dao {
    
    public List<Fct025Sql001ResponseModel> getFct025Sql001(Fct025Sql001RequestModel fct025Sql001RequestModel);
    
    public List<Fct025Sql002ResponseModel> getFct025Sql002(Fct025Sql002RequestModel fct025Sql002RequestModel);
    
}
