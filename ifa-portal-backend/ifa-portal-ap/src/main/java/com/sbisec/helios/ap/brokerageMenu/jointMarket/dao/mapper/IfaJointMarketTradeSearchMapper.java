package com.sbisec.helios.ap.brokerageMenu.jointMarket.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.jointMarket.dao.model.IfaJointMarketTradeSearchSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dao.model.IfaJointMarketTradeSearchSql001ResponseModel;

/**
 * 画面ID：SUB0208_01-01
 * 画面名：共同店舗取引検索
 * 
 * @author lianhua.xia
 * 2024/12/06 新規作成
 */

@Mapper
public interface IfaJointMarketTradeSearchMapper {

    /**
     * SQLID：Sql001
     * SQL名：共同店舗取引検索情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaJointMarketTradeSearchSql001RequestModel
     * レスポンスクラス：IfaJointMarketTradeSearchSql001ResponseModel
     * 
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaJointMarketTradeSearchSql001ResponseModel> selectIfaJointMarketTradeSearchSql001(
        @Param("req") IfaJointMarketTradeSearchSql001RequestModel req) throws Exception;

}
