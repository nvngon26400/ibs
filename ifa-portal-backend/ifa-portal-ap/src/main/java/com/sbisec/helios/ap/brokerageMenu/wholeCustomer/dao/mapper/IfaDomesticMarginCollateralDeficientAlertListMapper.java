package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDomesticMarginCollateralDeficientAlertListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDomesticMarginCollateralDeficientAlertListSql001ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;





/**
 * 画面ID：SUB020301_01-02
 * 画面名：国内信用担保不足アラート一覧
 *
 * @author BASE 李
 2024/06/11 新規作成
 */
@Mapper
public interface IfaDomesticMarginCollateralDeficientAlertListMapper {
    
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
    public List<IfaDomesticMarginCollateralDeficientAlertListSql001ResponseModel> selectIfaDomesticMarginCollateralDeficientAlertListSql001(
            @Param("req") IfaDomesticMarginCollateralDeficientAlertListSql001RequestModel req
        ) throws Exception;
    
    
    
    
}
