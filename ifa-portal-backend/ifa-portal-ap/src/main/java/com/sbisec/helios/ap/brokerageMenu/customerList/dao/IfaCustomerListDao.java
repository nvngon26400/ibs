package com.sbisec.helios.ap.brokerageMenu.customerList.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListSql004ResponseModel;

/**
 * 画面ID：SUB0201_01-01
 * 画面名：顧客一覧・基本 Dao
 *
 * @author SCSK池田
 *
   2023/09/13 新規作成
 *
 */
public interface IfaCustomerListDao {
    
    /**
     * SQLID：Sql001
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerListSql001RequestModel
     * レスポンスクラス：IfaCustomerListSql001ResponseModel
     *
     * @param req リクエストモデル
     * @return IfaCustomerListSql001ResponseModel
     * @exception Exception 例外
     */
    public DataList<IfaCustomerListSql001ResponseModel> selectIfaCustomerListSql001(
            IfaCustomerListSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerListSql003RequestModel
     * レスポンスクラス：IfaCustomerListSql003ResponseModel
     *
     * @param req リクエストモデル
     * @return IfaCustomerListSql003ResponseModel
     * @exception Exception 例外
     */
    public DataList<IfaCustomerListSql003ResponseModel> selectIfaCustomerListSql003(
            IfaCustomerListSql003RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerListSql004RequestModel
     * レスポンスクラス：IfaCustomerListSql004ResponseModel
     *
     * @return IfaCustomerListSql004ResponseModel
     * @exception Exception 例外
     */
    public DataList<IfaCustomerListSql004ResponseModel> selectIfaCustomerListSql004() throws Exception;
    
}
