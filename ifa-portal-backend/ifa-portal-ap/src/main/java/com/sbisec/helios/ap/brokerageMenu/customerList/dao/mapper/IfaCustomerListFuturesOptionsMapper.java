package com.sbisec.helios.ap.brokerageMenu.customerList.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListFuturesOptionsSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListFuturesOptionsSql001ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 画面ID：SUB0201_03-01
 * 画面名：顧客一覧・先OP
 *
 * @author SCSK
 2024/05/29 新規作成
 */
@Mapper
public interface IfaCustomerListFuturesOptionsMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：顧客検索
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerListFuturesOptionsSql001RequestModel
     * レスポンスクラス：IfaCustomerListFuturesOptionsSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaCustomerListFuturesOptionsSql001ResponseModel> selectIfaCustomerListFuturesOptionsSql001(
            @Param("req") IfaCustomerListFuturesOptionsSql001RequestModel req) throws Exception;
    
}
