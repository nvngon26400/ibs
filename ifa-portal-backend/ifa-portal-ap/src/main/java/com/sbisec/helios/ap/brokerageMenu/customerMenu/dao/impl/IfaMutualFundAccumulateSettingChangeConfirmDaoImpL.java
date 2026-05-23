package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaMutualFundAccumulateSettingChangeConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaMutualFundAccumulateSettingChangeConfirmMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingChangeConfirmSql001RequestModel;

/**
 * 画面ID：SUB0202_0403-03_2
 * 画面名：投信積立設定変更確認
 * 
 * @author nicksen.li
 *
 * 2025/04/20 新規作成
 */
@Component
public class IfaMutualFundAccumulateSettingChangeConfirmDaoImpL extends RowSelectableDao
        implements IfaMutualFundAccumulateSettingChangeConfirmDao {

    @Autowired
    private IfaMutualFundAccumulateSettingChangeConfirmMapper mapper;

    /**
     * SQLID：Sql001 
     * SQL名：設定変更受付データの格納
     * SQLタイプ：insert
     * リクエストクラス：IfaMutualFundAccumulateSettingChangeConfirmSql001RequestModel
     * レスポンスクラス：IfaMutualFundAccumulateSettingChangeConfirmSql001ResponseModel
     * 
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int insertIfaMutualFundAccumulateSettingChangeConfirmSql001(
            IfaMutualFundAccumulateSettingChangeConfirmSql001RequestModel req) throws Exception {

        return mapper.insertIfaMutualFundAccumulateSettingChangeConfirmSql001(req);
    }

}
