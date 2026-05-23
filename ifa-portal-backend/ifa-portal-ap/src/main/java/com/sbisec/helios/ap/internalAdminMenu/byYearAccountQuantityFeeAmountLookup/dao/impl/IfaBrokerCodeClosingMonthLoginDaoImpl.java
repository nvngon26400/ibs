package com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.IfaBrokerCodeClosingMonthLoginDao;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.mapper.IfaBrokerCodeClosingMonthLoginMapper;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaBrokerCodeClosingMonthLoginSql001RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaBrokerCodeClosingMonthLoginSql001ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaBrokerCodeClosingMonthLoginSql002RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaBrokerCodeClosingMonthLoginSql002ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaBrokerCodeClosingMonthLoginSql003RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaBrokerCodeClosingMonthLoginSql003ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaBrokerCodeClosingMonthLoginSql004RequestModel;

/**
 * 画面ID：SUB0406-01_1
 * 画面名：仲介業者決算月設定
 *
 * @author SBI大連 夏
 * @date   2025/05/27
 */

@Component
public class IfaBrokerCodeClosingMonthLoginDaoImpl extends RowSelectableDao implements IfaBrokerCodeClosingMonthLoginDao {

    @Autowired
    private IfaBrokerCodeClosingMonthLoginMapper mapper;
    
    @Override
    public IfaBrokerCodeClosingMonthLoginSql001ResponseModel selectIfaBrokerCodeClosingMonthLoginSql001(
        IfaBrokerCodeClosingMonthLoginSql001RequestModel req) throws Exception {
        IfaBrokerCodeClosingMonthLoginSql001ResponseModel res = new IfaBrokerCodeClosingMonthLoginSql001ResponseModel();
        if (mapper.selectIfaBrokerCodeClosingMonthLoginSql001(req) != null) {
         res.setBrokerName(mapper.selectIfaBrokerCodeClosingMonthLoginSql001(req).getBrokerName());   
        }
        return res;
    }

    @Override
    public IfaBrokerCodeClosingMonthLoginSql002ResponseModel selectIfaBrokerCodeClosingMonthLoginSql002(
        IfaBrokerCodeClosingMonthLoginSql002RequestModel req) throws Exception {
        IfaBrokerCodeClosingMonthLoginSql002ResponseModel res = new IfaBrokerCodeClosingMonthLoginSql002ResponseModel();
        if (mapper.selectIfaBrokerCodeClosingMonthLoginSql002(req) != null) {
            res.setClosingMonth(mapper.selectIfaBrokerCodeClosingMonthLoginSql002(req).getClosingMonth());
        }
        return res;
    }

    @Override
    public IfaBrokerCodeClosingMonthLoginSql003ResponseModel selectIfaBrokerCodeClosingMonthLoginSql003(
        IfaBrokerCodeClosingMonthLoginSql003RequestModel req) throws Exception {
        IfaBrokerCodeClosingMonthLoginSql003ResponseModel res = new IfaBrokerCodeClosingMonthLoginSql003ResponseModel();
        if (mapper.selectIfaBrokerCodeClosingMonthLoginSql003(req) != null) {
            res.setBeginYearMonth(mapper.selectIfaBrokerCodeClosingMonthLoginSql003(req).getBeginYearMonth());
        }
        return res;
    }

    @Override
    public int updateIfaBrokerCodeClosingMonthLoginSql004(
        IfaBrokerCodeClosingMonthLoginSql004RequestModel req) throws Exception {
        return mapper.updateIfaBrokerCodeClosingMonthLoginSql004(req);
    }

}
