package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaWithdrawExecuteConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaWithdrawExecuteConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaWithdrawExecuteConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaWithdrawExecuteConfirmSql004RequestModel;

/**
 * 出金・出金確認
 * 
 * @author xin.huang
 *
 */
public interface IfaWithdrawExecuteConfirmDao {

    /**
     * SQLID：Sql001
     * SQL名：IFA入出金履歴テーブルに出金履歴を登録する。
     * SQLタイプ：insert
     * リクエスト：IfaWithdrawExecuteConfirmSql001RequestModel
     *
     * @param req {@code IfaWithdrawExecuteConfirmSql001RequestModel }
     * @return int
     * @throws Exception IFA入出金履歴テーブルに出金履歴を登録処理で例外が発生した場合
     */
    public int insertIfaWithdrawExecuteConfirmSql001(IfaWithdrawExecuteConfirmSql001RequestModel req) throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：発注が正常の場合、IFA入出金履歴テーブルに登録した出金履歴を更新する。
     * SQLタイプ：update
     * リクエスト：IfaWithdrawExecuteConfirmSql002RequestModel
     *
     * @param req {@code IfaWithdrawExecuteConfirmSql002RequestModel }
     * @return int
     * @throws Exception IFA入出金履歴テーブルに出金履歴を更新処理で例外が発生した場合
     */
    public int updateIfaWithdrawExecuteConfirmSql002(IfaWithdrawExecuteConfirmSql002RequestModel req) throws Exception;

    /**
     * SQLID：Sql003
     * SQL名：発注がエラーの場合(API応答あり)、IFA入出金履歴テーブルに登録した出金履歴を更新する。
     * SQLタイプ：update
     * リクエスト：IfaWithdrawExecuteConfirmSql003RequestModel
     *
     * @param req {@code IfaWithdrawExecuteConfirmSql003RequestModel }
     * @return int
     * @throws Exception IFA入出金履歴テーブルに出金履歴を更新処理で例外が発生した場合
     */
    public int updateIfaWithdrawExecuteConfirmSql003(IfaWithdrawExecuteConfirmSql003RequestModel req) throws Exception;

    /**
     * SQLID：Sql004
     * SQL名：発注がエラーの場合(API応答なし)、IFA入出金履歴テーブルに登録した出金履歴を更新する。
     * SQLタイプ：update
     * リクエスト：IfaWithdrawExecuteConfirmSql004RequestModel
     *
     * @param req {@code IfaWithdrawExecuteConfirmSql004RequestModel }
     * @return int
     * @throws Exception IFA入出金履歴テーブルに出金履歴を更新処理で例外が発生した場合
     */
    public int updateIfaWithdrawExecuteConfirmSql004(IfaWithdrawExecuteConfirmSql004RequestModel req) throws Exception;

    /**
     * SQLID：selectSeqTbCashPayRecJournalIfa
     * SQL名：SEQUENCE(トランザクションID)を取得する
     * SQLタイプ：select
     * レスポンス：String
     *
     * @return String
     * @throws Exception SEQUENCE(トランザクションID)を取得処理で例外が発生した場合
     */
    public String selectSeqTbCashPayRecJournalIfa() throws Exception;
}
