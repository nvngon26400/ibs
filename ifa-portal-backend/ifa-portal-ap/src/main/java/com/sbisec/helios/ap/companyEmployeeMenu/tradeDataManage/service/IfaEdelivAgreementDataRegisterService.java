package com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaEdelivAgreementDataRegisterA002RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaEdelivAgreementDataRegisterA002ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaEdelivAgreementDataRegisterA003RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaEdelivAgreementDataRegisterA003ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0504_02-01
 * 画面名：電子交付同意データ登録
 */
public interface IfaEdelivAgreementDataRegisterService extends Service {

    /**
     * アクションID：A002
     * アクション名：確認
     */
    DataList<IfaEdelivAgreementDataRegisterA002ResponseDto> confirmA002(
            IfaEdelivAgreementDataRegisterA002RequestDto dtoReq) throws Exception;

    /**
     * アクションID：A003
     * アクション名：登録
     */
    DataList<IfaEdelivAgreementDataRegisterA003ResponseDto> registerA003(
            IfaEdelivAgreementDataRegisterA003RequestDto dtoReq) throws Exception;
}
