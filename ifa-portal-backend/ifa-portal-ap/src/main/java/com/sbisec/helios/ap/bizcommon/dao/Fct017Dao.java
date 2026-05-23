package com.sbisec.helios.ap.bizcommon.dao;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct017Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct017Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct017Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct017Sql003RequestModel;

/**
 * 共通関数DAO：FCT017
 * 確認書受入および強制注文が必要な国内投信銘柄情報取得

 * @author SCSK　
 */

public interface Fct017Dao {

    /**
     * 受入書類取得
     *
     * @param fct017Sql001RequestModel インプットモデル
     * @return Fct017Sql001ResponseModel 受入書類リスト
     */
    Fct017Sql001ResponseModel acceptanceAcquire(Fct017Sql001RequestModel fct017Sql001RequestModel);
    
    /**
     * 銘柄別書類受入状況取得
     *
     * @param fct017Sql002RequestModel インプットモデル
     * @return Integer　抽出件数
     */
    int brandDocumentAcceptanceAcquire(Fct017Sql002RequestModel fct017Sql002RequestModel);
    
    /**
     * 共通書類受入状況取得
     *
     * @param fct017Sql003RequestModel インプットモデル
     * @return Integer 抽出件数
     */
    int commonDocumentAcceptanceAcquire(Fct017Sql003RequestModel fct017Sql003RequestModel);
    
}
