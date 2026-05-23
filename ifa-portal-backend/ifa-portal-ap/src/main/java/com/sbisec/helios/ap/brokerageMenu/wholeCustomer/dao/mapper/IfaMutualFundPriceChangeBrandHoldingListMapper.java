package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaMutualFundPriceChangeBrandHoldingListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaMutualFundPriceChangeBrandHoldingListSql001ResponseModel;





/**
 * 
 * 画面ID：SUB020301_03-01
 * 画面名：投信基準価額変動の銘柄保有一覧
 * @author <author-name>
 *
 * 2024/04/12 新規作成
 */
@Mapper
public interface IfaMutualFundPriceChangeBrandHoldingListMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：投信基準価額変動情報一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundPriceChangeBrandHoldingListSql001RequestModel
     * レスポンスクラス：IfaMutualFundPriceChangeBrandHoldingListSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public List<IfaMutualFundPriceChangeBrandHoldingListSql001ResponseModel> selectIfaMutualFundPriceChangeBrandHoldingListSql001(
        @Param("req") IfaMutualFundPriceChangeBrandHoldingListSql001RequestModel req
        ) throws Exception;
    
    
    
    
}
