package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginCollateralTransferInputSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginCollateralTransferInputSql001ResponseModel;

/**
 * 画面ID：SUB0202_0305-01_1
 * 画面名：米株信用代用振替入力
 *
 * @author SCSK川崎
 *
   2024/03/19 新規作成
 */
@Mapper
public interface IfaForeignMarginCollateralTransferInputMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：店頭取引で売却注文されている預り情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaForeignMarginCollateralTransferInputSql001RequestModel
     * レスポンスクラス：IfaForeignMarginCollateralTransferInputSql001ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 例外が発生した場合
     */
    public List<IfaForeignMarginCollateralTransferInputSql001ResponseModel> 
            selectIfaForeignMarginCollateralTransferInputSql001(
                    @Param("req") IfaForeignMarginCollateralTransferInputSql001RequestModel req) throws Exception;
    
}
