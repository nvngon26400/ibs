package com.sbisec.helios.ap.bizcommon.dao;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct007Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct007Sql001ResponseModel;

/**
 * 証券営業日付取得

 * @author 鄒
 *
 */
public interface Fct007Dao {

    public Fct007Sql001ResponseModel getDesignatedDate(Fct007Sql001RequestModel fct007Sql001RequestModel);

}
