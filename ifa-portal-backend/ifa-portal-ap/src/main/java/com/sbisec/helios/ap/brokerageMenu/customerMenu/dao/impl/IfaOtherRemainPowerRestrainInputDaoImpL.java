package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaOtherRemainPowerRestrainInputDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaOtherRemainPowerRestrainInputMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOtherRemainPowerRestrainInputSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOtherRemainPowerRestrainInputSql001ResponseModel;

/**
 * DB処理インターフェースの実装クラス
 * 画面ID：SUB0202_0110-01_1
 * 画面名：その他余力拘束注文入力
 * 2025/03/10 新規作成
 *
 * @author 大連 王永宝
 */
@Component
public class IfaOtherRemainPowerRestrainInputDaoImpL extends RowSelectableDao 
    implements IfaOtherRemainPowerRestrainInputDao {

    @Autowired
    private IfaOtherRemainPowerRestrainInputMapper g_mapper;

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
            IfaOtherRemainPowerRestrainInputSql001RequestModel x_req) throws Exception {

        // 結果の格納先を準備
        DataList<IfaOtherRemainPowerRestrainInputSql001ResponseModel> p_res = new DataList<IfaOtherRemainPowerRestrainInputSql001ResponseModel>();
        // SQLを実行し結果を格納    
        p_res.setDataList(g_mapper.selectIfaOtherRemainPowerRestrainInputSql001(x_req));

        return p_res;
    }

}
