package com.sbisec.helios.ap.brokerageMenu.jointMarket.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.jointMarket.dao.model.IfaJointMarketTrustFeeSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dao.model.IfaJointMarketTrustFeeSql001ResponseModel;

/**
 * 画面ID：SUB0207_02
 * 画面名：共同店舗　信託報酬
 *
 * @author SBI大連　董
 2024/12/16 新規作成
 */
@Mapper
public interface IfaJointMarketTrustFeeMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：共同店舗　信託報酬一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaTrustFeeSql001RequestModel
     * レスポンスクラス：IfaTrustFeeSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaJointMarketTrustFeeSql001ResponseModel> selectIfaJointMarketTrustFeeSql001(
            @Param("req") IfaJointMarketTrustFeeSql001RequestModel req) throws Exception;
    
    
}
