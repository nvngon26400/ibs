package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaStockCertificateDetailSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaStockCertificateDetailSql001ResponseModel;
/**
 * 画面ID：SUB0202_0703-03
 * 画面名：株券詳細
 *
 * @author SBI大連 董
 *2025/03/20 新規作成
 */
public interface IfaStockCertificateDetailDao {
    /**
     * SQLID：Sql001
     * SQL名：株券詳細取得
     * SQLタイプ：select
     * リクエストクラス：IfaStockCertificateDetailSql001RequestModel
     * レスポンスクラス：IfaStockCertificateDetailSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @throws Exception 
     * @exception exception システムエラー
     */
    DataList<IfaStockCertificateDetailSql001ResponseModel> selectIfaStockCertificateDetailSql001(
            IfaStockCertificateDetailSql001RequestModel selSql001Req) throws Exception;

}
