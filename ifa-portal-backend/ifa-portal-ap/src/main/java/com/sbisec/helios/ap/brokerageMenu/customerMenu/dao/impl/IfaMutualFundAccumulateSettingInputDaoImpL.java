package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaMutualFundAccumulateSettingInputDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaMutualFundAccumulateSettingInputMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingInputSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingInputSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingInputSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingInputSql002ResponseModel;

/**
 * 画面ID：SUB0202_0403-02_1 
 * 画面名：投信積立設定入力
 *
 * @author nicksen.li
 */
@Component
public class IfaMutualFundAccumulateSettingInputDaoImpL extends RowSelectableDao
        implements IfaMutualFundAccumulateSettingInputDao {

    @Autowired
    private IfaMutualFundAccumulateSettingInputMapper mapper;

    /**
     * SQLID：Sql001 
     * SQL名：投信積立買付設定取得 
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundAccumulateSettingInputSql001RequestModel
     * レスポンスクラス：IfaMutualFundAccumulateSettingInputSql001ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @throws Exception 銘柄コード取得の際、例外が発生した場合
     */
    @Override
    public DataList<IfaMutualFundAccumulateSettingInputSql001ResponseModel> selectIfaMutualFundAccumulateSettingInputSql001(
            IfaMutualFundAccumulateSettingInputSql001RequestModel req) throws Exception {

        DataList<IfaMutualFundAccumulateSettingInputSql001ResponseModel> res = new DataList<IfaMutualFundAccumulateSettingInputSql001ResponseModel>();
        res.setDataList(mapper.selectIfaMutualFundAccumulateSettingInputSql001(req));
        return res;

    }

    /**
     * SQLID：Sql002 
     * SQL名：投信クレカ積立買付設定取得 
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundAccumulateSettingInputSql002RequestModel
     * レスポンスクラス：IfaMutualFundAccumulateSettingInputSql002ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @throws Exception 銘柄コード取得の際、例外が発生した場合
     */
    @Override
    public DataList<IfaMutualFundAccumulateSettingInputSql002ResponseModel> selectIfaMutualFundAccumulateSettingInputSql002(
            IfaMutualFundAccumulateSettingInputSql002RequestModel req) throws Exception {

        DataList<IfaMutualFundAccumulateSettingInputSql002ResponseModel> res = new DataList<IfaMutualFundAccumulateSettingInputSql002ResponseModel>();
        res.setDataList(mapper.selectIfaMutualFundAccumulateSettingInputSql002(req));
        return res;

    }

}
