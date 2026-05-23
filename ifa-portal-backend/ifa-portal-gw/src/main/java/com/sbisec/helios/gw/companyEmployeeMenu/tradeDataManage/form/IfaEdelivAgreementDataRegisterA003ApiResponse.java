package com.sbisec.helios.gw.companyEmployeeMenu.tradeDataManage.form;

import java.util.List;

import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaEdelivAgreementDataRegisterEdelivAgreementDataDto;

import lombok.Data;

/**
 * 画面ID：SUB0504_02-01
 * 画面名：電子交付同意データ登録
 */
@Data
public class IfaEdelivAgreementDataRegisterA003ApiResponse {

    private List<IfaEdelivAgreementDataRegisterEdelivAgreementDataDto> edelivAgreementDataList;
}
