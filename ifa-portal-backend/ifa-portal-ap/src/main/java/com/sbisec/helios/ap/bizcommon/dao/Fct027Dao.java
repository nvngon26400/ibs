package com.sbisec.helios.ap.bizcommon.dao;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct027Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct027Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct027Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct027Sql002ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct027Sql003RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct027Sql003ResponseModel;

/**
 * 国内株式情報取得 DAO インターフェース

 * @author SCSK

 * @see ORACLE - > ETI -> ETINTRA -> STAR_BRAND_MASTER(銘柄属性情報).xlsx
 * @see ORACLE - > ETI -> CORDYS -> PTS_BRAND_MASTER(PTS銘柄マスタ).xlsx 
 */
public interface Fct027Dao {

    public List<Fct027Sql001ResponseModel> getFct027Sql001(Fct027Sql001RequestModel fct027Sql001RequestModel);

    public List<Fct027Sql002ResponseModel> getFct027Sql002(Fct027Sql002RequestModel fct027Sql002RequestModel);
    
    public List<Fct027Sql003ResponseModel> getFct027Sql003(Fct027Sql003RequestModel fct027Sql003RequestModel);

}

