package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0202_0101-01
 * 画面名：資産状況
 * 2024/01/24 新規作成
 *
 * @author SCSK 江口
 *
 */
@Data
public class IfaPortfolioSql013RequestModel {

    /** 顧客共通情報.部店コード */
    private String butenCode;

    /** 顧客共通情報.口座番号 */
    private String accountNumber;

}
