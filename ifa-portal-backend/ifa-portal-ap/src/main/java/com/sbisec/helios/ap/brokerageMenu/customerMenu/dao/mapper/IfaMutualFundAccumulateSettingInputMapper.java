package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingInputSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingInputSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingInputSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingInputSql002ResponseModel;

/**
 * 画面ID：SUB0202_0403-02_1
 * 画面名：投信積立設定入力
 *
 * @author nicksen.li
 */
@Mapper
public interface IfaMutualFundAccumulateSettingInputMapper {

    /**
     * SQLID：Sql001
     * SQL名：投信積立買付設定取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundAccumulateSettingInputSql001RequestModel
     * レスポンスクラス：IfaMutualFundAccumulateSettingInputSql001ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @throws Exception 投信積立買付設定取得の際、例外が発生した場合
     */
    public List<IfaMutualFundAccumulateSettingInputSql001ResponseModel> selectIfaMutualFundAccumulateSettingInputSql001(
            @Param("req") IfaMutualFundAccumulateSettingInputSql001RequestModel req) throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：投信クレカ積立買付設定取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundAccumulateSettingInputSql002RequestModel
     * レスポンスクラス：IfaMutualFundAccumulateSettingInputSql002ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @throws Exception 投信クレカ積立買付設定取得の際、例外が発生した場合
     */
    public List<IfaMutualFundAccumulateSettingInputSql002ResponseModel> selectIfaMutualFundAccumulateSettingInputSql002(
            @Param("req") IfaMutualFundAccumulateSettingInputSql002RequestModel req) throws Exception;

}
