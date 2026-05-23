package com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：SUB0504_02-01
 * 画面名：電子交付同意データ登録
 */
@Data
public class IfaEdelivAgreementDataRegisterEdelivAgreementDataDto {

    private String buten;

    private String accountNumber;

    private String edelivAgreementDate;

    private String edelivAgreementKbn;

    private String edelivAgreementKbnKanji;

    private String checkResult;

    private String displayMessageFlag;

    private List<IfaEdelivAgreementDataRegisterCheckResultSetDto> checkResultSetList;
}
