package com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0504_02-01
 * 画面名：電子交付同意データ登録
 */
@Data
public class IfaEdelivAgreementDataRegisterSql002RequestModel {

    private String butenCode;

    private String accountNumber;

    private String edelivAgreementDate;

    private String edelivAgreementKbn;

    private String userId;
}
