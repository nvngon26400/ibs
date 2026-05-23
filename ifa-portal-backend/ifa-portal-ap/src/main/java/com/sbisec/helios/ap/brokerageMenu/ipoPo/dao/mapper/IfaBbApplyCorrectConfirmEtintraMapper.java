package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.mapper;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql015RequestModel;
import com.sbisec.helios.ap.common.annotation.dao.EtintraMapper;

import org.apache.ibatis.annotations.Param;

/**
 * 画面ID：SUB0204_02-02_2
 * 画面名：BB申込訂正確認
 *
 * @author BASE 李
 2024/04/23 新規作成
 */
@EtintraMapper
public interface IfaBbApplyCorrectConfirmEtintraMapper {
    
    /**
     * SQLID：Sql015a
     * SQL名：BB申込情報訂正
     * SQLタイプ：update
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int updateIfaBbApplyCorrectConfirmSql015a(
            @Param("req")  IfaBbApplyCorrectConfirmSql015RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql015b
     * SQL名：BB申込情報訂正
     * SQLタイプ：delete
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int deleteIfaBbApplyCorrectConfirmSql015b(
            @Param("req")  IfaBbApplyCorrectConfirmSql015RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql015c
     * SQL名：BB申込情報訂正
     * SQLタイプ：update
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int updateIfaBbApplyCorrectConfirmSql015c(
            @Param("req")  IfaBbApplyCorrectConfirmSql015RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql015d
     * SQL名：BB申込情報訂正
     * SQLタイプ：insert
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int insertIfaBbApplyCorrectConfirmSql015d(
            @Param("req")  IfaBbApplyCorrectConfirmSql015RequestModel req
        ) throws Exception;
}
