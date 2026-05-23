package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingBrandListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingBrandListSql001ResponseModel;

/**
 * 画面ID：SUB0202_0403-01 画面名：投信積立設定済銘柄一覧
 *
 * @author nicksen.li
 */
public interface IfaMutualFundAccumulateSettingBrandListDao {

    /**
     * SQLID：Sql001 SQL名：銘柄コード取得 SQLタイプ：select
     * リクエストクラス：IfaMutualFundAccumulateSettingBrandListSql001RequestModel
     * レスポンスクラス：IfaMutualFundAccumulateSettingBrandListSql001ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @throws Exception 銘柄コード取得の際、例外が発生した場合
     */
    public DataList<IfaMutualFundAccumulateSettingBrandListSql001ResponseModel> selectIfaMutualFundAccumulateSettingBrandListSql001(
            IfaMutualFundAccumulateSettingBrandListSql001RequestModel req) throws Exception;

}
