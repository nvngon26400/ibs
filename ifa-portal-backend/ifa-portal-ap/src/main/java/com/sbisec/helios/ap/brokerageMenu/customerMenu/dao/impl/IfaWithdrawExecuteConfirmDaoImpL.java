package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaWithdrawExecuteConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaWithdrawExecuteConfirmMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaWithdrawExecuteConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaWithdrawExecuteConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaWithdrawExecuteConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaWithdrawExecuteConfirmSql004RequestModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 出金・出金確認
 *
 * @author xin.huang
 *
 */
@Component
public class IfaWithdrawExecuteConfirmDaoImpL extends RowSelectableDao implements IfaWithdrawExecuteConfirmDao {

    @Autowired
    private IfaWithdrawExecuteConfirmMapper mapper;

    /**
     * SQLID：Sql001
     * SQL名：IFA入出金履歴テーブルに出金履歴を登録する。
     * SQLタイプ：insert
     * リクエスト：IfaWithdrawHistoryModel
     *
     * @param req {@code IfaWithdrawHistoryModel }
     * @return int
     * @throws Exception IFA入出金履歴テーブルに出金履歴を登録処理で例外が発生した場合
     */
    public int insertIfaWithdrawExecuteConfirmSql001(IfaWithdrawExecuteConfirmSql001RequestModel req) throws Exception {
        return mapper.insertIfaWithdrawExecuteConfirmSql001(req);
    }

    /**
     * SQLID：Sql002
     * SQL名：発注が正常の場合、IFA入出金履歴テーブルに登録した出金履歴を更新する。
     * SQLタイプ：update
     * リクエスト：IfaWithdrawHistoryModel
     *
     * @param req {@code IfaWithdrawHistoryModel }
     * @return int
     * @throws Exception IFA入出金履歴テーブルに出金履歴を更新処理で例外が発生した場合
     */
    public int updateIfaWithdrawExecuteConfirmSql002(IfaWithdrawExecuteConfirmSql002RequestModel req) throws Exception {
        return mapper.updateIfaWithdrawExecuteConfirmSql002(req);
    }

    /**
     * SQLID：Sql003
     * SQL名：発注がエラーの場合(API応答あり)、IFA入出金履歴テーブルに登録した出金履歴を更新する。
     * SQLタイプ：update
     * リクエスト：IfaWithdrawHistoryModel
     *
     * @param req {@code IfaWithdrawHistoryModel }
     * @return int
     * @throws Exception IFA入出金履歴テーブルに出金履歴を更新処理で例外が発生した場合
     */
    public int updateIfaWithdrawExecuteConfirmSql003(IfaWithdrawExecuteConfirmSql003RequestModel req) throws Exception {
        return mapper.updateIfaWithdrawExecuteConfirmSql003(req);
    }

    /**
     * SQLID：Sql004
     * SQL名：発注がエラーの場合(API応答なし)、IFA入出金履歴テーブルに登録した出金履歴を更新する。
     * SQLタイプ：update
     * リクエスト：IfaWithdrawHistoryModel
     *
     * @param req {@code IfaWithdrawHistoryModel }
     * @return int
     * @throws Exception IFA入出金履歴テーブルに登録した出金履歴を更新処理で例外が発生した場合
     */
    public int updateIfaWithdrawExecuteConfirmSql004(IfaWithdrawExecuteConfirmSql004RequestModel req) throws Exception {
        return mapper.updateIfaWithdrawExecuteConfirmSql004(req);
    }

    /**
     * SQLID：selectSeqTbCashPayRecJournalIfa
     * SQL名：SEQUENCE(トランザクションID)を取得する
     * SQLタイプ：select
     * レスポンス：String
     *
     * @return String
     * @throws Exception SEQUENCE(トランザクションID)を取得処理で例外が発生した場合
     */
    public String selectSeqTbCashPayRecJournalIfa() throws Exception {
        return mapper.selectSeqTbCashPayRecJournalIfa();
    }
}
