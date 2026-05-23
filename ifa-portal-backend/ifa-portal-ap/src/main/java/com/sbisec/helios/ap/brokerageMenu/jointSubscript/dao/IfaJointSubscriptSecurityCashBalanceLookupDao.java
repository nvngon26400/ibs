package com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptSecurityCashBalanceLookupSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptSecurityCashBalanceLookupSql001ResponseModel;





/**
 * 画面ID：SUB0206_04-01
 * 画面名：共同募集　証券・金銭・残高照会
 *
 * @author SBIえん
 2024/12/10 新規作成
 *
 */
public interface IfaJointSubscriptSecurityCashBalanceLookupDao {
    
    /**
     * SQLID：Sql001
     * SQL名：共同募集　証券・金銭・残高情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaJointSubscriptSecurityCashBalanceLookupSql001RequestModel
     * レスポンスクラス：IfaJointSubscriptSecurityCashBalanceLookupSql001ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointSubscriptSecurityCashBalanceLookupSql001ResponseModel> selectIfaJointSubscriptSecurityCashBalanceLookupSql001(IfaJointSubscriptSecurityCashBalanceLookupSql001RequestModel req)
            throws Exception;
    
}
