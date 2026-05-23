package com.sbisec.helios.ap.bizcommon.dao;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct022Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct022Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct022Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct022Sql002ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct022Sql003RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct022Sql003ResponseModel;

/**
 * 共通関数：FCT022 DAO
 *
 * @author 陳
 */
public interface Fct022Dao {
    
    /**
     * 国内投信販売手数料取得
     */
    public List<Fct022Sql002ResponseModel> getFct022Sql002(Fct022Sql002RequestModel sql002Req);
    
    /**
     * 国内投信基準価額取得
     */
    public List<Fct022Sql001ResponseModel> getFct022Sql001(Fct022Sql001RequestModel sql001Req);
    
    /**
     * 扱者個別国内投信販売手数料取得
     */
    public List<Fct022Sql003ResponseModel> getFct022Sql003(Fct022Sql003RequestModel sql003Req);
}
