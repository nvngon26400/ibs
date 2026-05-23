package com.sbisec.helios.ap.bizcommon.dao;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct039Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct039Sql001ResponseModel;

/**
 * 共通関数：FCT039
 * ポイント関連項目表示可否取得
 *
 * @author SCSK
 */
public interface Fct039Dao {
    
    /**
     * ポイント関連項目表示可否取得
     *
     * @param fct039Sql001RequestModel リクエスト
     * @return レスポンス
     */
    public Fct039Sql001ResponseModel getPointDisplayPermission(Fct039Sql001RequestModel fct039Sql001RequestModel);
    
}
