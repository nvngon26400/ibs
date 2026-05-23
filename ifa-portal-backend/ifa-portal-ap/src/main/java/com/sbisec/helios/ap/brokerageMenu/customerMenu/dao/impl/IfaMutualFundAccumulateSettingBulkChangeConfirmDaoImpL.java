package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaMutualFundAccumulateSettingBulkChangeConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaMutualFundAccumulateSettingBulkChangeConfirmMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingBulkChangeConfirmSql001RequestModel;

/**
 * 画面ID：SUB0202_0403-05_2
 * 画面名：投信積立設定一括変更確認
 *
 * @author nicksen.li
 * 2025/07/24 新規作成
 *
 */
@Component
public class IfaMutualFundAccumulateSettingBulkChangeConfirmDaoImpL extends RowSelectableDao
        implements IfaMutualFundAccumulateSettingBulkChangeConfirmDao {

    @Autowired
    private IfaMutualFundAccumulateSettingBulkChangeConfirmMapper mapper;

    /**
     * SQLID：Sql001
     * SQL名：積立設定一括変更登録
     * SQLタイプ：insert
     * 
     * リクエストクラス：IfaMutualFundAccumulateSettingBulkChangeConfirmSql001RequestModel
     * レスポンスクラス：実行結果
     * 
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int insertIfaMutualFundAccumulateSettingBulkChangeConfirmSql001(
            IfaMutualFundAccumulateSettingBulkChangeConfirmSql001RequestModel req) throws Exception {

        return mapper.insertIfaMutualFundAccumulateSettingBulkChangeConfirmSql001(req);
    }

}
