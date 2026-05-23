package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDepositWithdrawDetailSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDepositWithdrawDetailSql001ResponseModel;

/**
 * 画面ID：SUB020302_0203-01
 * 画面名：入出金明細
 *
 * @author
 */
public interface IfaDepositWithdrawDetailDao {
    
    /**
     * SQLID：Sql001
     * SQL名：入出金明細取得
     * SQLタイプ：select
     * リクエストクラス：IfaDepositWithdrawDetailSql001RequestModel
     * レスポンスクラス：IfaDepositWithdrawDetailSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaDepositWithdrawDetailSql001ResponseModel> selectIfaDepositWithdrawDetailSql001(
            IfaDepositWithdrawDetailSql001RequestModel req) throws Exception;
    
}
