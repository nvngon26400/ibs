package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPriceViewLookupForeignStockBrandListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPriceViewLookupForeignStockBrandListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPriceViewLookupForeignStockBrandListSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPriceViewLookupForeignStockBrandListSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPriceViewLookupForeignStockBrandListSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPriceViewLookupForeignStockBrandListSql003ResponseModel;

/**
 * 画面ID：SUB0202_0302-01
 * 画面名：単価表照会（外国株式銘柄一覧）
 * 2024/03/27 新規作成
 *
 * @author SCSK今井
 */
public interface IfaPriceViewLookupForeignStockBrandListDao {
    
    /**
     * SQLID：Sql001
     * SQL名：全体メッセージ取得
     * SQLタイプ：select
     * リクエストクラス：IfaPriceViewLookupForeignStockBrandListSql001RequestModel
     * レスポンスクラス：IfaPriceViewLookupForeignStockBrandListSql001ResponseModel
     *
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaPriceViewLookupForeignStockBrandListSql001ResponseModel> selectIfaPriceViewLookupForeignStockBrandListSql001(
            IfaPriceViewLookupForeignStockBrandListSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：単価表照会情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaPriceViewLookupForeignStockBrandListSql002RequestModel
     * レスポンスクラス：IfaPriceViewLookupForeignStockBrandListSql002ResponseModel
     *
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaPriceViewLookupForeignStockBrandListSql002ResponseModel> selectIfaPriceViewLookupForeignStockBrandListSql002(
            IfaPriceViewLookupForeignStockBrandListSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：外部リンクURL取得
     * SQLタイプ：select
     * リクエストクラス：IfaPriceViewLookupForeignStockBrandListSql003RequestModel
     * レスポンスクラス：IfaPriceViewLookupForeignStockBrandListSql003ResponseModel
     *
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaPriceViewLookupForeignStockBrandListSql003ResponseModel> selectIfaPriceViewLookupForeignStockBrandListSql003(
            IfaPriceViewLookupForeignStockBrandListSql003RequestModel req) throws Exception;
    
}
