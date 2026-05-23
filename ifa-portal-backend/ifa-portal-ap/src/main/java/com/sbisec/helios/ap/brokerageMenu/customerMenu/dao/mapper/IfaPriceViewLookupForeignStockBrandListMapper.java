package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
@Mapper
public interface IfaPriceViewLookupForeignStockBrandListMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：全体メッセージ取得
     * SQLタイプ：select
     * リクエストクラス：IfaPriceViewLookupForeignStockBrandListSql001RequestModel
     * レスポンスクラス：IfaPriceViewLookupForeignStockBrandListSql001ResponseModel
     *
     * @param req リクエスト
     * @return 全体メッセージのリスト
     * @exception exception システムエラー
     */
    public List<IfaPriceViewLookupForeignStockBrandListSql001ResponseModel> selectIfaPriceViewLookupForeignStockBrandListSql001(
            @Param("req") IfaPriceViewLookupForeignStockBrandListSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：単価表照会情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaPriceViewLookupForeignStockBrandListSql002RequestModel
     * レスポンスクラス：IfaPriceViewLookupForeignStockBrandListSql002ResponseModel
     *
     *　@param req リクエスト
     * @return 単価表照会情報のリスト
     * @exception exception システムエラー
     */
    public List<IfaPriceViewLookupForeignStockBrandListSql002ResponseModel> selectIfaPriceViewLookupForeignStockBrandListSql002(
            @Param("req") IfaPriceViewLookupForeignStockBrandListSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：外部リンクURL取得
     * SQLタイプ：select
     * リクエストクラス：IfaPriceViewLookupForeignStockBrandListSql003RequestModel
     * レスポンスクラス：IfaPriceViewLookupForeignStockBrandListSql003ResponseModel
     *
     * @param req リクエスト
     * @return 外部リンクURLのリスト
     * @throws Exception SQL実行時に発生した例外
     */
    public List<IfaPriceViewLookupForeignStockBrandListSql003ResponseModel> selectIfaPriceViewLookupForeignStockBrandListSql003(
            @Param("req") IfaPriceViewLookupForeignStockBrandListSql003RequestModel req) throws Exception;
    
}
