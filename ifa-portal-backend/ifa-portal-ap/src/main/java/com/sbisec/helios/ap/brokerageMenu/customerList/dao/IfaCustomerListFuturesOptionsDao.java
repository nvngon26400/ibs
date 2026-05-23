package com.sbisec.helios.ap.brokerageMenu.customerList.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListFuturesOptionsSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListFuturesOptionsSql001ResponseModel;

/**
 * 画面ID：SUB0201_03-01
 * 画面名：顧客一覧・先OP
 *
 * @author SCSK
 2024/05/29 新規作成
 *
 */
public interface IfaCustomerListFuturesOptionsDao {
    
    /**
     * SQLID：Sql001
     * SQL名：顧客検索
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerListFuturesOptionsSql001RequestModel
     * レスポンスクラス：IfaCustomerListFuturesOptionsSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaCustomerListFuturesOptionsSql001ResponseModel> selectIfaCustomerListFuturesOptionsSql001(
            IfaCustomerListFuturesOptionsSql001RequestModel req) throws Exception;
    
}
