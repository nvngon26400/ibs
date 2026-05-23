package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundBuyAbleListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundBuyAbleListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundBuyAbleListSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundBuyAbleListSql002ResponseModel;

/**
 * 画面ID：SUB0202_0401-01
 * 画面名：国内投信購入積立可能一覧
 *
 * @author WJL
 *     2025/03/30 新規作成
 *
 */
public interface IfaDomesticMutualFundBuyAbleListDao {
    
    /**
     * SQLID：Sql001
     * SQL名：協会コード取得
     * SQLタイプ：select
     * リクエストクラス：IfaDomesticMutualFundBuyAbleListSql001RequestModel
     * レスポンスクラス：IfaDomesticMutualFundBuyAbleListSql001ResponseModel
     *
     * @param req リクエストモデル
     * @return 応答データリスト
     * @exception Exception システム例外
     */
    public DataList<IfaDomesticMutualFundBuyAbleListSql001ResponseModel> selectIfaDomesticMutualFundBuyAbleListSql001(
    		IfaDomesticMutualFundBuyAbleListSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：銘柄情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaDomesticMutualFundBuyAbleListSql002RequestModel
     * レスポンスクラス：IfaDomesticMutualFundBuyAbleListSql002ResponseModel
     *
     * @param req リクエストモデル
     * @return 応答データリスト
     * @exception Exception システム例外
     */
    public DataList<IfaDomesticMutualFundBuyAbleListSql002ResponseModel> selectIfaDomesticMutualFundBuyAbleListSql002(
    		IfaDomesticMutualFundBuyAbleListSql002RequestModel req) throws Exception;

}
