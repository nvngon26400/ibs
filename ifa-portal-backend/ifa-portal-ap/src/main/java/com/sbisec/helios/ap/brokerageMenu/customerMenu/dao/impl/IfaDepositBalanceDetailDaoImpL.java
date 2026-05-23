package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaDepositBalanceDetailDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaDepositBalanceDetailMapper;
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
@Component
public class IfaDepositBalanceDetailDaoImpL extends RowSelectableDao implements IfaDepositBalanceDetailDao {

    @Autowired
    private IfaDepositBalanceDetailMapper mapper;

    /**
     * SQLID：Sql001
     * SQL名：基準価額単位取得
     * SQLタイプ：select
     * リクエストクラス：IfaDepositBalanceDetailSql001RequestModel
     * レスポンスクラス：IfaDepositBalanceDetailSql001ResponseModel
     */
    public DataList<IfaDepositBalanceDetailSql001ResponseModel>
            selectIfaDepositBalanceDetailSql001(IfaDepositBalanceDetailSql001RequestModel req)
            throws Exception {

        DataList<IfaDepositBalanceDetailSql001ResponseModel> res =
                new DataList<IfaDepositBalanceDetailSql001ResponseModel>();

        res.setDataList(mapper.selectIfaDepositBalanceDetailSql001(req));
        return res;
    }

    /**
     * SQLID：Sql002
     * SQL名：優先市場取得
     * SQLタイプ：select
     * リクエストクラス：IfaDepositBalanceDetailSql002RequestModel
     * レスポンスクラス：IfaDepositBalanceDetailSql002ResponseModel
     */
    public DataList<IfaDepositBalanceDetailSql002ResponseModel>
            selectIfaDepositBalanceDetailSql002(IfaDepositBalanceDetailSql002RequestModel req)
            throws Exception {

        DataList<IfaDepositBalanceDetailSql002ResponseModel> res =
                new DataList<IfaDepositBalanceDetailSql002ResponseModel>();

        res.setDataList(mapper.selectIfaDepositBalanceDetailSql002(req));
        return res;
    }

    /**
     * SQLID：Sql003
     * SQL名：外国債券（外貨建）銘柄情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaDepositBalanceDetailSql003RequestModel
     * レスポンスクラス：IfaDepositBalanceDetailSql003ResponseModel
     */
    public DataList<IfaDepositBalanceDetailSql003ResponseModel>
            selectIfaDepositBalanceDetailSql003(IfaDepositBalanceDetailSql003RequestModel req)
            throws Exception {

        DataList<IfaDepositBalanceDetailSql003ResponseModel> res =
                new DataList<IfaDepositBalanceDetailSql003ResponseModel>();

        res.setDataList(mapper.selectIfaDepositBalanceDetailSql003(req));
        return res;
    }

    /**
     * SQLID：Sql004
     * SQL名：基準価額取得
     * SQLタイプ：select
     * リクエストクラス：IfaDepositBalanceDetailSql004RequestModel
     * レスポンスクラス：IfaDepositBalanceDetailSql004ResponseModel
     */
    public DataList<IfaDepositBalanceDetailSql004ResponseModel>
            selectIfaDepositBalanceDetailSql004(IfaDepositBalanceDetailSql004RequestModel req)
            throws Exception {

        DataList<IfaDepositBalanceDetailSql004ResponseModel> res =
                new DataList<IfaDepositBalanceDetailSql004ResponseModel>();

        res.setDataList(mapper.selectIfaDepositBalanceDetailSql004(req));
        return res;
    }


}
