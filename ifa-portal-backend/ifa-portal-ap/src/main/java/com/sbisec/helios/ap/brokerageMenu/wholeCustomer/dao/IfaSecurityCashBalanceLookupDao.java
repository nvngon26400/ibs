package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaSecurityCashBalanceLookupSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaSecurityCashBalanceLookupSql001ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaSecurityCashBalanceLookupSql004ResponseModel;





/**
 * 画面ID：SUB020302_0301-01
 * 画面名：証券・金銭・残高照会
 *
 * @author <author-name>
 2024/05/07 新規作成
 *
 */
public interface IfaSecurityCashBalanceLookupDao {
    
	
    /**
     * SQLID：Sql001
     * SQL名：証券・金銭・残高情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaSecurityCashBalanceLookupSql001RequestModel
     * レスポンスクラス：IfaSecurityCashBalanceLookupSql001ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSecurityCashBalanceLookupSql001ResponseModel> selectIfaSecurityCashBalanceLookupSql001(IfaSecurityCashBalanceLookupSql001RequestModel req)
            throws Exception;
    
	
    /**
     * SQLID：Sql004
     * SQL名：証券・金銭・残高照会画面コメント取得
     * SQLタイプ：select
     * リクエストクラス：IfaSecurityCashBalanceLookupSql004RequestModel
     * レスポンスクラス：IfaSecurityCashBalanceLookupSql004ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSecurityCashBalanceLookupSql004ResponseModel> selectIfaSecurityCashBalanceLookupSql004()
            throws Exception;
    
    
    
    
}
