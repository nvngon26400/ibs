package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDomesticMarginCollateralDeficientAlertListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDomesticMarginCollateralDeficientAlertListSql001ResponseModel;





/**
 * 画面ID：SUB020301_01-02
 * 画面名：国内信用担保不足アラート一覧
 *
 * @author <author-name>
 2024/06/11 新規作成
 *
 */
public interface IfaDomesticMarginCollateralDeficientAlertListDao {
    
	
    /**
     * SQLID：Sql001
     * SQL名：追証請求顧客情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaDomesticMarginCollateralDeficientAlertListSql001RequestModel
     * レスポンスクラス：IfaDomesticMarginCollateralDeficientAlertListSql001ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaDomesticMarginCollateralDeficientAlertListSql001ResponseModel> selectIfaDomesticMarginCollateralDeficientAlertListSql001(IfaDomesticMarginCollateralDeficientAlertListSql001RequestModel req)
            throws Exception;
    
    
    
    
}
