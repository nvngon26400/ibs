package com.sbisec.helios.ap.bizcommon.dao;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct033Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct033Sql001ResponseModel;

/**
 * 営業日情報チェックDAO
 * 
 * @author SCSK
 *
 */
public interface  Fct033Dao {

    /**
     * カレンダー取得
     * カレンダーマスタ（M_CALENDAR）からパラメータで渡された日付を取得し営業日チェックを行う

     * @param fct033Sql001RequestModel リクエスト
     * @return レスポンス
     * @throws Exception 主要取引所コード取得時に例外が発生した場合
     */
    public List<Fct033Sql001ResponseModel>
            getBusinessDayType(Fct033Sql001RequestModel fct033Sql001RequestModel) throws Exception;
}
