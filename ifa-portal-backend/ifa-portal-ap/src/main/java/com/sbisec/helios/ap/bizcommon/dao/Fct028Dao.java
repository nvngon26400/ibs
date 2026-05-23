package com.sbisec.helios.ap.bizcommon.dao;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct028Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct028Sql001ResponseModel;

/**
 * 店頭取引注文情報の取得

 * @author  鄒
 *
 */
public interface Fct028Dao {
    
    public List<Fct028Sql001ResponseModel> getFct028Sql001(Fct028Sql001RequestModel fct028Sql001RequestModel);
    
}
