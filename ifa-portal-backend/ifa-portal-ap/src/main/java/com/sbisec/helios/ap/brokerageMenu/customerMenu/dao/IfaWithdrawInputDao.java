package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaWithdrawInputSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaWithdrawInputSql001ResponseModel;

/**
 * 出金・出金入力
 * 
 * @author xin.huang
 *
 */
public interface IfaWithdrawInputDao {

    /**
     * SQLID：Sql001
     * SQL名：検索
     * SQLタイプ：select 
     * リクエスト：IfaWithdrawInputSql001RequestModel
     * レスポンス：IfaWithdrawInputSql001ResponseModel
     *
     * @param req {@code IfaWithdrawInputSql001RequestModel }
     * @return {@code DataList <IfaWithdrawInputSql001ResponseModel>}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaWithdrawInputSql001ResponseModel> selectIfaWithdrawInputSql001(
            IfaWithdrawInputSql001RequestModel req) throws Exception;

}
