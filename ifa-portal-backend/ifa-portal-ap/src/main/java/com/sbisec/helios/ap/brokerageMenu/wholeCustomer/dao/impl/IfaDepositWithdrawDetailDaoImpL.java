package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaDepositWithdrawDetailDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper.IfaDepositWithdrawDetailMapper;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDepositWithdrawDetailSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDepositWithdrawDetailSql001ResponseModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB020302_0203-01
 * 画面名：入出金明細
 *
 * @author
 */
@Component
public class IfaDepositWithdrawDetailDaoImpL extends RowSelectableDao implements IfaDepositWithdrawDetailDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaDepositWithdrawDetailDaoImpL.class);
    
    @Autowired
    private IfaDepositWithdrawDetailMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：入出金明細取得
     * SQLタイプ：select
     * リクエストクラス：IfaDepositWithdrawDetailSql001RequestModel
     * レスポンスクラス：IfaDepositWithdrawDetailSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaDepositWithdrawDetailSql001ResponseModel> selectIfaDepositWithdrawDetailSql001(
            IfaDepositWithdrawDetailSql001RequestModel req) throws Exception {
        
        DataList<IfaDepositWithdrawDetailSql001ResponseModel> res = new DataList<IfaDepositWithdrawDetailSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaDepositWithdrawDetailSql001(req));
        return res;
    }
    
}
