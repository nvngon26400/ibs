package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOtherRemainPowerRestrainInputConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOtherRemainPowerRestrainInputConfirmSql002RequestModel;

/**
 * 画面ID：SUB0202_0110-01_2
 * 画面名：その他余力拘束注文確認
 * 2025/03/10 新規作成
 *
 * @author 大連 王永宝
 */
public interface IfaOtherRemainPowerRestrainInputConfirmDao {

    /**
     * SQLID：Sql001
     * SQL名：発注前の注文登録
     * SQLタイプ：insert
     * リクエストクラス：IfaOtherRemainPowerRestrainInputConfirmSql001RequestModel
     * 
     * @param x_req リクエスト
     * @return int 新規登録件数
     * @exception Exception SQLExceptionなど
     */
    public int insertIfaOtherRemainPowerRestrainInputConfirmSql001(
            IfaOtherRemainPowerRestrainInputConfirmSql001RequestModel x_req) throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新
     * SQLタイプ：update
     * リクエストクラス：IfaOtherRemainPowerRestrainInputConfirmSql002RequestModel
     * 
     * @param x_req リクエスト
     * @return int 更新件数
     * @exception Exception SQLExceptionなど
     */
    public int updateIfaOtherRemainPowerRestrainInputConfirmSql002(
            IfaOtherRemainPowerRestrainInputConfirmSql002RequestModel x_req) throws Exception;

}
