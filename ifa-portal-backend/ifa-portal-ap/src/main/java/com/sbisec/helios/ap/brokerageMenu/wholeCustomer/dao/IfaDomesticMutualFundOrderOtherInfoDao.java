package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao;



import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDomesticMutualFundOrderOtherInfoSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDomesticMutualFundOrderOtherInfoSql001ResponseModel;





/**
 * 画面ID：SUB020302_0101-03
 * 画面名：コンプラ項目詳細
 *
 * @author BASE丁
 2024/06/20 新規作成
 *
 */
public interface IfaDomesticMutualFundOrderOtherInfoDao {
    
	
    /**
     * SQLID：Sql001
     * SQL名：国内投信注文その他情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaDomesticMutualFundOrderOtherInfoSql001RequestModel
     * レスポンスクラス：IfaDomesticMutualFundOrderOtherInfoSql001ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaDomesticMutualFundOrderOtherInfoSql001ResponseModel> selectIfaDomesticMutualFundOrderOtherInfoSql001(IfaDomesticMutualFundOrderOtherInfoSql001RequestModel req)
            throws Exception;
    
    
    
    
}
