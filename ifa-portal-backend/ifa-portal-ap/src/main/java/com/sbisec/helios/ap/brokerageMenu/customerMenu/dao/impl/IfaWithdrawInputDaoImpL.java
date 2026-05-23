package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaWithdrawInputDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaWithdrawInputMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaWithdrawInputSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaWithdrawInputSql001ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 出金・出金入力
 * 
 * @author xin.huang
 *
 */
@Component
public class IfaWithdrawInputDaoImpL extends RowSelectableDao implements IfaWithdrawInputDao {

    @Autowired
    private IfaWithdrawInputMapper mapper;

    /**
     * SQLID：Sql001
     * SQL名：検索
     * SQLタイプ：select
     * リクエスト：IfaWithdrawInputSql001RequestModel
     * レスポンス：IfaWithdrawInputSql001ResponseModel
     *
     * @param req {@code IfaWithdrawInputSql001RequestModel }
     * @return {@code DataList <IfaWithdrawInputSql001ResponseModel>}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaWithdrawInputSql001ResponseModel> selectIfaWithdrawInputSql001(
            IfaWithdrawInputSql001RequestModel req) throws Exception {

        DataList<IfaWithdrawInputSql001ResponseModel> res = new DataList<IfaWithdrawInputSql001ResponseModel>();
        res.setDataList(mapper.selectIfaWithdrawInputSql001(req));
        return res;
    }
}
