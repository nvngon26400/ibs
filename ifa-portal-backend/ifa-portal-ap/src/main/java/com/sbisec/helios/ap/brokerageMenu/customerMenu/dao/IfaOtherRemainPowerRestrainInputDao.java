package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOtherRemainPowerRestrainInputSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOtherRemainPowerRestrainInputSql001ResponseModel;

/**
 * 画面ID：SUB0202_0110-01_1
 * 画面名：その他余力拘束注文入力
 * 2025/03/10 新規作成
 *
 * @author 大連 王永宝
 */
public interface IfaOtherRemainPowerRestrainInputDao {

    /**
     * SQLID：Sql001
     * SQL名：その他注文履歴情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaOtherRemainPowerRestrainInputSql001RequestModel
     * レスポンスクラス：IfaOtherRemainPowerRestrainInputSql001ResponseModel
     *
     * @param x_req prepared statement
     * @return p_res レスポンス
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaOtherRemainPowerRestrainInputSql001ResponseModel> selectIfaOtherRemainPowerRestrainInputSql001(
            IfaOtherRemainPowerRestrainInputSql001RequestModel x_req) throws Exception;

}
