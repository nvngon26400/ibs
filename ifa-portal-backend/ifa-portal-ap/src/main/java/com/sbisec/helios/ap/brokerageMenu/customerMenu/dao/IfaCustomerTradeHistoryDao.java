package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerTradeHistorySql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerTradeHistorySql001ResponseModel;


/**
 * 画面ID：SUB0202_0109-01
 * 画面名：取引履歴（顧客別）
 * 2025/07/24 新規作成
 *
 * @author SCSK
 */
public interface IfaCustomerTradeHistoryDao {

    /**
     * SQLID：SQL001
     * SQL名：取引履歴（顧客別） 一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerTradeHistorySql001RequestModel
     * レスポンスクラス：IfaCustomerTradeHistorySql001ResponseModel
     *
     * @param req パラメータ
     * @return 取引履歴（顧客別） 一覧
     * @exception Exception システムエラー
     */
    public DataList<IfaCustomerTradeHistorySql001ResponseModel> selectIfaCustomerTradeHistorySql001(
            IfaCustomerTradeHistorySql001RequestModel req
    ) throws Exception;

}
