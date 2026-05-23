package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginCollateralTransferConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginCollateralTransferConfirmSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginCollateralTransferConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginCollateralTransferConfirmSql003RequestModel;

/**
 * 画面ID：SUB0202_0305-01_2
 * 画面名：米株信用代用振替確認
 *
 * @author SCSK川崎
 *
   2024/03/19 新規作成
 */
@Mapper
public interface IfaForeignMarginCollateralTransferConfirmMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：店頭取引で売却注文されている預り情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaForeignMarginCollateralTransferConfirmSql001RequestModel
     * レスポンスクラス：IfaForeignMarginCollateralTransferConfirmSql001ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 例外が発生した場合
     */
    public List<IfaForeignMarginCollateralTransferConfirmSql001ResponseModel> 
            selectIfaForeignMarginCollateralTransferConfirmSql001(
            @Param("req") IfaForeignMarginCollateralTransferConfirmSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：振替指示前の指示登録
     * SQLタイプ：insert
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 例外が発生した場合
     */
    public int insertIfaForeignMarginCollateralTransferConfirmSql002(
            @Param("req") IfaForeignMarginCollateralTransferConfirmSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：振替指示後の指示更新
     * SQLタイプ：update
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 例外が発生した場合
     */
    public int updateIfaForeignMarginCollateralTransferConfirmSql003(
            @Param("req") IfaForeignMarginCollateralTransferConfirmSql003RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：シーケンスオブジェクトの取得
     * SQLタイプ：select
     *
     * @return レスポンス
     * @exception Exception 例外が発生した場合
     */
    public String selectIfaForeignMarginCollateralTransferConfirmSql004() throws Exception;
    
}
