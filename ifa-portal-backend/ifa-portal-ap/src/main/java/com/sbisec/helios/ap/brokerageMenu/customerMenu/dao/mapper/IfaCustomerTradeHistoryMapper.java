package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerTradeHistorySql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerTradeHistorySql001ResponseModel;


/**
 * 画面ID：SUB0202_0109-01
 * 画面名：取引履歴（顧客別）
 * 2025/07/24 新規作成
 *
 * @author SCSK
 */
@Mapper
public interface IfaCustomerTradeHistoryMapper {
    
    /**
     * SQLID：SQL001
     * SQL名：取引履歴（顧客別） 一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerTradeHistorySql001RequestModel
     * レスポンスクラス：IfaCustomerTradeHistorySql001ResponseModel
     *
     * @param req パラメータ
     * @return 取引履歴（顧客別） 一覧
     * @exception Exception システムエラー
     */
    public List<IfaCustomerTradeHistorySql001ResponseModel> selectIfaCustomerTradeHistorySql001(
            @Param("req") IfaCustomerTradeHistorySql001RequestModel req
    ) throws Exception;
}
