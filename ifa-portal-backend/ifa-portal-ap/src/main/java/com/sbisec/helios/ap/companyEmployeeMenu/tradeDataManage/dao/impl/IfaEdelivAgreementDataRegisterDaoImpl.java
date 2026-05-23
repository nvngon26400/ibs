package com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.IfaEdelivAgreementDataRegisterDao;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.mapper.IfaEdelivAgreementDataRegisterMapper;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.model.IfaEdelivAgreementDataRegisterSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.model.IfaEdelivAgreementDataRegisterSql002RequestModel;

/**
 * 画面ID：SUB0504_02-01
 * 画面名：電子交付同意データ登録
 */
@Component
public class IfaEdelivAgreementDataRegisterDaoImpl extends RowSelectableDao
        implements IfaEdelivAgreementDataRegisterDao {

    @Autowired
    private IfaEdelivAgreementDataRegisterMapper mapper;

    @Override
    public Integer selectIfaEdelivAgreementDataRegisterSql001(IfaEdelivAgreementDataRegisterSql001RequestModel req)
            throws Exception {
        return mapper.selectIfaEdelivAgreementDataRegisterSql001(req);
    }

    @Override
    public int mergeIfaEdelivAgreementDataRegisterSql002(IfaEdelivAgreementDataRegisterSql002RequestModel req)
            throws Exception {
        return mapper.mergeIfaEdelivAgreementDataRegisterSql002(req);
    }
}
