package com.sbisec.helios.ap.bizcommon.dao;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct023Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct023Sql001ResponseModel;

/**
 * 共通関数DAO：FCT023
 *
 * @author 陳
 */
public interface Fct023Dao {

    /**
     * 国内投信販売手数料取得
     *
     * @param sql001Req NRIコード
     *
     * @return 指定銘柄の各種情報
     */
    Fct023Sql001ResponseModel getFct023Sql001(Fct023Sql001RequestModel sql001Req);
}
