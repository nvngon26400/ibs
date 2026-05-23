package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterDetailDisplaySql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterDetailDisplaySql001ResponseModel;

/**
 * 画面ID：SUB0202_0104-03
 * 画面名：外国株式店頭詳細表示

 * @author 大崎
　　　2024/03/19 新規作成
 */

@Mapper
public interface IfaForeignStockCounterDetailDisplayMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：外国株式店頭注文詳細表示情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaForeignStockCounterDetailDisplaySql001RequestModel
     * レスポンスクラス：IfaForeignStockCounterDetailDisplaySql001ResponseModel

     * @param req リクエスト
     * @return IfaForeignStockCounterDetailDisplaySql001ResponseModel
     * @exception Exception SQLExceptionなど
     */
    public List<IfaForeignStockCounterDetailDisplaySql001ResponseModel> selectIfaForeignStockCounterDetailDisplaySql001(
            @Param("req") IfaForeignStockCounterDetailDisplaySql001RequestModel req
        ) throws Exception;   
}
