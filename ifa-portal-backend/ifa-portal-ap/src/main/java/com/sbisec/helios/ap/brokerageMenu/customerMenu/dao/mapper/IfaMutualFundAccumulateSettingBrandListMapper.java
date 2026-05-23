package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingBrandListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingBrandListSql001ResponseModel;

/**
 * 画面ID：SUB0202_0403-01
 * 画面名：投信積立設定済銘柄一覧
 *
 * @author nicksen.li
 */
@Mapper
public interface IfaMutualFundAccumulateSettingBrandListMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：銘柄コード取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundAccumulateSettingBrandListSql001RequestModel
     * レスポンスクラス：IfaMutualFundAccumulateSettingBrandListSql001ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @throws Exception 銘柄名取得の際、例外が発生した場合
     */
    public List<IfaMutualFundAccumulateSettingBrandListSql001ResponseModel> selectIfaMutualFundAccumulateSettingBrandListSql001(
            @Param("req") IfaMutualFundAccumulateSettingBrandListSql001RequestModel req) throws Exception;
    
}
