package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaOtherRemainPowerRestrainInputConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaOtherRemainPowerRestrainInputConfirmMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOtherRemainPowerRestrainInputConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOtherRemainPowerRestrainInputConfirmSql002RequestModel;

/**
 * DB処理インターフェースの実装クラス
 * 画面ID：SUB0202_0110-01_2
 * 画面名：その他余力拘束注文確認
 * 2025/03/10 新規作成
 *
 * @author 大連 王永宝
 */
@Component
public class IfaOtherRemainPowerRestrainInputConfirmDaoImpL extends RowSelectableDao 
    implements IfaOtherRemainPowerRestrainInputConfirmDao {

    @Autowired
    private IfaOtherRemainPowerRestrainInputConfirmMapper g_mapper;

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
            IfaOtherRemainPowerRestrainInputConfirmSql001RequestModel x_req) throws Exception {

        return g_mapper.insertIfaOtherRemainPowerRestrainInputConfirmSql001(x_req);
    }

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
            IfaOtherRemainPowerRestrainInputConfirmSql002RequestModel x_req) throws Exception {

        return g_mapper.updateIfaOtherRemainPowerRestrainInputConfirmSql002(x_req);
    }
}
