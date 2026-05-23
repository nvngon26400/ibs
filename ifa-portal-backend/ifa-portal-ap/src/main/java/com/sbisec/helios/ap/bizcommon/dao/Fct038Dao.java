package com.sbisec.helios.ap.bizcommon.dao;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct038Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct038Sql001ResponseModel;

/**
 * 共通関数：FCT038
 * CSVダウンロードMAX件数取得
 *
 * @author SCSK
 */
public interface Fct038Dao {
    
    /**
     * CSVダウンロードMAX件数取得
     *
     * @param fct038Sql001RequestModel リクエスト
     * @return レスポンス
     * @throws Exception CSVダウンロードMAX件数取得に例外が発生した場合
     */
    public Fct038Sql001ResponseModel getCsvMax(Fct038Sql001RequestModel fct038Sql001RequestModel) throws Exception;
    
}
