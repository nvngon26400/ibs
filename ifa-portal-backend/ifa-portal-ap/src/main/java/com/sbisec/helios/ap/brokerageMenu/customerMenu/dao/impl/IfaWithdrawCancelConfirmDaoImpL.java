package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaWithdrawCancelConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaWithdrawCancelConfirmMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaWithdrawCancelConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaWithdrawCancelConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaWithdrawCancelConfirmSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaWithdrawCancelConfirmSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaWithdrawCancelConfirmTrandIdAndEdabanRequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaWithdrawCancelConfirmTrandIdAndEdabanResponseModel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 出金・出金取消
 *
 * @author xin.huang
 *
 */
@Component
public class IfaWithdrawCancelConfirmDaoImpL extends RowSelectableDao implements IfaWithdrawCancelConfirmDao {

    @Autowired
    private IfaWithdrawCancelConfirmMapper mapper;

    /**
     * SQLID：Sql002
     * SQL名：IFA入出金履歴テーブルに出金取消履歴を登録する。
     * SQLタイプ：insert
     * リクエスト：IfaWithdrawHistoryModel
     *
     * @param req {@code IfaWithdrawHistoryModel }
     * @return int
     * @throws Exception IFA入出金履歴テーブルに出金取消履歴を登録処理で例外が発生した場合
     */
    public int insertIfaWithdrawCancelConfirmSql002(IfaWithdrawCancelConfirmSql002RequestModel req) throws Exception {
        return mapper.insertIfaWithdrawCancelConfirmSql002(req);
    }

    /**
     * SQLID：Sql003
     * SQL名：発注が正常の場合、出金履歴テーブルの出金取消履歴データを更新する。
     * SQLタイプ：update
     * リクエスト：IfaWithdrawHistoryModel
     *
     * @param req {@code IfaWithdrawHistoryModel }
     * @return int
     * @throws Exception 出金履歴テーブルの出金取消履歴データを更新処理で例外が発生した場合
     */
    public int updateIfaWithdrawCancelConfirmSql003(IfaWithdrawCancelConfirmSql003RequestModel req) throws Exception {
        return mapper.updateIfaWithdrawCancelConfirmSql003(req);
    }

    /**
     * SQLID：Sql004
     * SQL名：発注がエラーの場合(API応答あり)、出金履歴テーブルの出金取消履歴データを再度更新する。
     * SQLタイプ：update
     * リクエスト：IfaWithdrawHistoryModel
     *
     * @param req {@code IfaWithdrawHistoryModel }
     * @return int
     * @throws Exception 出金履歴テーブルの出金取消履歴データを再度更新処理で例外が発生した場合
     */
    public int updateIfaWithdrawCancelConfirmSql004(IfaWithdrawCancelConfirmSql004RequestModel req) throws Exception {
        return mapper.updateIfaWithdrawCancelConfirmSql004(req);
    }

    /**
     * SQLID：Sql005
     * SQL名：発注がエラーの場合(API応答なし)、出金履歴テーブルの出金取消履歴データを再度更新する。
     * SQLタイプ：update
     * リクエスト：IfaWithdrawHistoryModel
     *
     * @param req {@code IfaWithdrawHistoryModel }
     * @return int
     * @throws Exception 出金履歴テーブルの出金取消履歴データを再度更新処理で例外が発生した場合
     */
    public int updateIfaWithdrawCancelConfirmSql005(IfaWithdrawCancelConfirmSql005RequestModel req) throws Exception {
        return mapper.updateIfaWithdrawCancelConfirmSql005(req);
    }

    /**
     * SQLID：sql006
     * SQL名：既存の出金履歴トランザクションID、枝番を取得する
     * SQLタイプ：select
     * リクエスト：IfaWithdrawCancelConfirmTrandIdAndEdabanRequestModel
     * レスポンス：IfaWithdrawCancelConfirmTrandIdAndEdabanResponseModel
     *
     * @param req {@code IfaWithdrawCancelConfirmTrandIdAndEdabanRequestModel }
     * @return {@code List<IfaWithdrawCancelConfirmTrandIdAndEdabanResponseModel>}
     * @throws Exception 既存の出金履歴トランザクションID、枝番を取得処理で例外が発生した場合
     */
    public List<IfaWithdrawCancelConfirmTrandIdAndEdabanResponseModel> getIfaWithdrawCancelConfirmSql006(
            IfaWithdrawCancelConfirmTrandIdAndEdabanRequestModel req) throws Exception {
        return mapper.getIfaWithdrawCancelConfirmSql006(req);
    }
}
