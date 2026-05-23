package com.sbisec.helios.ap.bizcommon.dao;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct021Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct021Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct021Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct021Sql002ResponseModel;

/**
 * 取引制限チェックDAO
 *
 * @author SCSK
 *
 */
public interface Fct021Dao {
    
    public List<Fct021Sql001ResponseModel> getFct021Sql001(Fct021Sql001RequestModel fct021Sql001RequestModel);
    
    public List<Fct021Sql002ResponseModel> getFct021Sql002(Fct021Sql002RequestModel fct021Sql002RequestModel);
    
}
