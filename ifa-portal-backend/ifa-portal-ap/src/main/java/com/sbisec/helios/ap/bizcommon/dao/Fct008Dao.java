package com.sbisec.helios.ap.bizcommon.dao;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct008Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct008Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct008Sql002ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct008Sql003RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct008Sql003ResponseModel;

/**
 * 共通関数DAO：FCT008

 * @author SCSK
 */

public interface Fct008Dao {
    
    /**
     * 営業日取得
     * 備考：カレンダーマスタ（M_CALENDAR）より、現在日付以降の営業日（16営業日分）を取得する
     *
     * @return List 現在日付以降の営業日リスト(YYYYMMDD)
     */
    List<String> getBusinessDayList(Fct008Sql001RequestModel requestModel);
    
    /**
     * 優先市場の取得
     * 備考：銘柄マスタ（I_PRODUCT_MASTER）より、優先市場を取得する
     *
     * @param brandCode 銘柄コード
     * @return String　優先市場
     */
    Fct008Sql002ResponseModel getMarket(Fct008Sql002RequestModel requestModel);
    
    /**
     * 国内株式取引市場終了時刻取得
     *
     * @param requestModel sql003RequsetModel
     * @return sql003ResponseModel
     */
    Fct008Sql003ResponseModel getOverTime(Fct008Sql003RequestModel requestModel);
}
