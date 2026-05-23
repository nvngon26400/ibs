package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundBuyAbleListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundBuyAbleListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundBuyAbleListSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundBuyAbleListSql002ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 画面ID：SUB0202_0401-01
 * 画面名：国内投信購入積立可能一覧
 *
 * @author WJL
 *
 *     2025/03/28 新規作成
 */
@Mapper
public interface IfaDomesticMutualFundBuyAbleListMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：協会コード取得
     * SQLタイプ：select
     * リクエストクラス：IfaDomesticMutualFundBuyAbleListSql001RequestModel
     * レスポンスクラス：IfaDomesticMutualFundBuyAbleListSql001ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 実行時例外
     */
    public List<IfaDomesticMutualFundBuyAbleListSql001ResponseModel> selectIfaDomesticMutualFundBuyAbleListSql001(
            @Param("req") IfaDomesticMutualFundBuyAbleListSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：銘柄情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaDomesticMutualFundBuyAbleListSql002RequestModel
     * レスポンスクラス：IfaDomesticMutualFundBuyAbleListSql002ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 実行時例外
     */
    public List<IfaDomesticMutualFundBuyAbleListSql002ResponseModel> selectIfaDomesticMutualFundBuyAbleListSql002(
            @Param("req") IfaDomesticMutualFundBuyAbleListSql002RequestModel req) throws Exception;
    
}
