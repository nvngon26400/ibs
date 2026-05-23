package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaForeignMarginCollateralDeficientAlertListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaForeignMarginCollateralDeficientAlertListSql001ResponseModel;





/**
 * 画面ID：SUB020301_01-04
 * 画面名：米株信用担保不足アラート一覧
 *
 * @author <author-name>
 2024/06/17 新規作成
 *
 */
public interface IfaForeignMarginCollateralDeficientAlertListDao {
    
	
    /**
     * SQLID：Sql001
     * SQL名：追証請求顧客情報取得（SBI証券本店）
     * SQLタイプ：select
     * リクエストクラス：IfaForeignMarginCollateralDeficientAlertListSql001RequestModel
     * レスポンスクラス：IfaForeignMarginCollateralDeficientAlertListSql001ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaForeignMarginCollateralDeficientAlertListSql001ResponseModel> selectIfaForeignMarginCollateralDeficientAlertListSql001(IfaForeignMarginCollateralDeficientAlertListSql001RequestModel req)
            throws Exception;
    
    
    
    
}
