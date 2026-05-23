package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDepositBalanceDetailSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDepositBalanceDetailSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDepositBalanceDetailSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDepositBalanceDetailSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDepositBalanceDetailSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDepositBalanceDetailSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDepositBalanceDetailSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDepositBalanceDetailSql004ResponseModel;

/**
 * 画面ID：SUB0202_010201-04
 * 画面名：預り残高詳細

 * @author 秋山
 */
public interface IfaDepositBalanceDetailDao {

    /**
     * SQLID：Sql001
     * SQL名：基準価額単位取得
     * SQLタイプ：select
     * リクエストクラス：IfaDepositBalanceDetailSql001RequestModel
     * レスポンスクラス：IfaDepositBalanceDetailSql001ResponseModel

     * @param req リクエスト情報
     * @return IfaDepositBalanceDetailSql001ResponseModel レスポンス情報
     * @exception Exception 例外発生時
     */
    public DataList<IfaDepositBalanceDetailSql001ResponseModel>
            selectIfaDepositBalanceDetailSql001(IfaDepositBalanceDetailSql001RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：優先市場取得
     * SQLタイプ：select
     * リクエストクラス：IfaDepositBalanceDetailSql002RequestModel
     * レスポンスクラス：IfaDepositBalanceDetailSql002ResponseModel

     * @param req リクエスト情報
     * @return IfaDepositBalanceDetailSql002ResponseModel レスポンス情報
     * @exception Exception 例外発生時
     */
    public DataList<IfaDepositBalanceDetailSql002ResponseModel>
            selectIfaDepositBalanceDetailSql002(IfaDepositBalanceDetailSql002RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql003
     * SQL名：外国債券（外貨建）銘柄情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaDepositBalanceDetailSql003RequestModel
     * レスポンスクラス：IfaDepositBalanceDetailSql003ResponseModel

     * @param req リクエスト情報
     * @return IfaDepositBalanceDetailSql003ResponseModel レスポンス情報
     * @exception Exception 例外発生時
     */
    public DataList<IfaDepositBalanceDetailSql003ResponseModel>
            selectIfaDepositBalanceDetailSql003(IfaDepositBalanceDetailSql003RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql004
     * SQL名：基準価額取得
     * SQLタイプ：select
     * リクエストクラス：IfaDepositBalanceDetailSql004RequestModel
     * レスポンスクラス：IfaDepositBalanceDetailSql004ResponseModel

     * @param req リクエスト情報
     * @return IfaDepositBalanceDetailSql004ResponseModel レスポンス情報
     * @exception Exception 例外発生時
     */
    public DataList<IfaDepositBalanceDetailSql004ResponseModel>
            selectIfaDepositBalanceDetailSql004(IfaDepositBalanceDetailSql004RequestModel req)
            throws Exception;
}
