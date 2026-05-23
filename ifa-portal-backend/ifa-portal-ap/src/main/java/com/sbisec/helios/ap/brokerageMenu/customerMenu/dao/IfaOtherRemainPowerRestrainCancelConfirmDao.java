package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOtherRemainPowerRestrainCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOtherRemainPowerRestrainCancelConfirmSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOtherRemainPowerRestrainCancelConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOtherRemainPowerRestrainCancelConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOtherRemainPowerRestrainCancelConfirmSql004RequestModel;

/**
 * 画面ID：SUB0202_0110-02_1
 * 画面名：その他余力拘束注文取消確認
 * 2025/03/10 新規作成
 *
 * @author 大連 王永宝
 */
public interface IfaOtherRemainPowerRestrainCancelConfirmDao {

    /**
     * SQLID：Sql001
     * SQL名：その他注文履歴情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaOtherRemainPowerRestrainCancelConfirmSql001RequestModel
     * レスポンスクラス：IfaOtherRemainPowerRestrainCancelConfirmSql001ResponseModel
     *
     * @param x_req prepared statement
     * @return p_res レスポンス
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaOtherRemainPowerRestrainCancelConfirmSql001ResponseModel> selectIfaOtherRemainPowerRestrainCancelConfirmSql001(
            IfaOtherRemainPowerRestrainCancelConfirmSql001RequestModel x_req) throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：その他注文履歴情報(取消発注)の件数を取得
     * SQLタイプ：select
     * リクエストクラス：IfaOtherRemainPowerRestrainCancelConfirmSql002RequestModel
     * 
     * @param x_req リクエスト
     * @return int 件数
     * @exception Exception SQLExceptionなど
     */
    public int selectIfaOtherRemainPowerRestrainCancelConfirmSql002(
            IfaOtherRemainPowerRestrainCancelConfirmSql002RequestModel x_req) throws Exception;

    /**
     * SQLID：Sql003
     * SQL名：発注前の注文登録
     * SQLタイプ：insert
     * リクエストクラス：IfaOtherRemainPowerRestrainCancelConfirmSql003RequestModel
     * 
     * @param x_req リクエスト
     * @return int 新規登録件数
     * @exception Exception SQLExceptionなど
     */
    public int insertIfaOtherRemainPowerRestrainCancelConfirmSql003(
            IfaOtherRemainPowerRestrainCancelConfirmSql003RequestModel x_req) throws Exception;

    /**
     * SQLID：Sql004
     * SQL名：発注後の注文更新
     * SQLタイプ：update
     * リクエストクラス：IfaOtherRemainPowerRestrainCancelConfirmSql004RequestModel
     * 
     * @param x_req リクエスト
     * @return int 更新件数
     * @exception Exception SQLExceptionなど
     */
    public int updateIfaOtherRemainPowerRestrainCancelConfirmSql004(
            IfaOtherRemainPowerRestrainCancelConfirmSql004RequestModel x_req) throws Exception;

}
