package com.sbisec.helios.ap.bizcommon.dao;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct024Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct024Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct024Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct024Sql002ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct024Sql003RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct024Sql003ResponseModel;

/**
 * 共通関数DAO：FCT024
 *
 * @author SCSK
 */
public interface Fct024Dao {
    
    /**
     * 国内投信販売手数料取得
     */
    public Fct024Sql002ResponseModel getFct024Sql002(Fct024Sql002RequestModel sql002Req);
    
    /**
     * 国内投信基準価額取得
     */
    public Fct024Sql001ResponseModel getFct024Sql001(Fct024Sql001RequestModel sql001Req);
    
    /**
     * 扱者個別国内投信販売手数料取得
     */
    public Fct024Sql003ResponseModel getFct024Sql003(Fct024Sql003RequestModel sql003Req);
    
}
