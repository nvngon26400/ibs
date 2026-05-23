package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaStockCertificateDetailSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaStockCertificateDetailSql001ResponseModel;



/**
 * 画面ID：SUB0202_0703-03
 * 画面名：株券詳細
 *
 * @author SBI大連 董
 *2025/03/20 新規作成
 */
@Mapper
public interface IfaStockCertificateDetailMapper {
    /**
     * SQLID：Sql001
     * SQL名：株券詳細取得
     * SQLタイプ：select
     * リクエストクラス：IfaSendReceiveStatusLookupSql001RequestModel
     * レスポンスクラス：IfaSendReceiveStatusLookupSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaStockCertificateDetailSql001ResponseModel> selectIfaStockCertificateDetailSql001(
            @Param("req") IfaStockCertificateDetailSql001RequestModel req) throws Exception;
    
}
