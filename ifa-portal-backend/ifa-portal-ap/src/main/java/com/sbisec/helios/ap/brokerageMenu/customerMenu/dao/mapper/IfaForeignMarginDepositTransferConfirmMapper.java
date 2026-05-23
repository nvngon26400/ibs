package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginDepositTransferConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginDepositTransferConfirmSql002RequestModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 画面ID：SUB0202_0304-01_2
 * 画面名：米株信用保証金振替確認
 *
 * @author
 */
@Mapper
public interface IfaForeignMarginDepositTransferConfirmMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：振替指示前の指示登録
     * SQLタイプ：insert
     *
     * @param req リクエスト
     * @return レスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    public int insertIfaForeignMarginDepositTransferConfirmSql001(
            @Param("req") IfaForeignMarginDepositTransferConfirmSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：振替指示後の指示更新
     * SQLタイプ：update
     *
     * @param req リクエスト
     * @return レスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    public int updateIfaForeignMarginDepositTransferConfirmSql002(
            @Param("req") IfaForeignMarginDepositTransferConfirmSql002RequestModel req) throws Exception;
    
}
