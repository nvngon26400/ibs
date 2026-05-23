package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaForeignMarginPositionDueDateAlertSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaForeignMarginPositionDueDateAlertSql001ResponseModel;

/**
 * 画面ID：SUB020301_02-02
 * 画面名：米株信用建玉期日アラート一覧
 *
 * @author BASE 李
 2024/06/21 新規作成
 *
 */
public interface IfaForeignMarginPositionDueDateAlertDao {
    
    /**
     * SQLID：Sql001
     * SQL名：決済期日顧客情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaForeignMarginPositionDueDateAlertSql001RequestModel
     * レスポンスクラス：IfaForeignMarginPositionDueDateAlertSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaForeignMarginPositionDueDateAlertSql001ResponseModel> selectIfaForeignMarginPositionDueDateAlertSql001(
            IfaForeignMarginPositionDueDateAlertSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql005
     * SQL名：IFA顧客アラート(日数)取得
     * SQLタイプ：select
     *
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public Integer selectIfaForeignMarginPositionDueDateAlertSql005() throws Exception;
    
    /**
     * SQLID：Sql006
     * SQL名：営業日取得
     * SQLタイプ：select
     *
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public String selectIfaForeignMarginPositionDueDateAlertSql006() throws Exception;
}
