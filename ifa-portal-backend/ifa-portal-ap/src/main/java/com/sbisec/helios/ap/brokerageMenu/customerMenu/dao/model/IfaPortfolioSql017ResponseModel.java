package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;


import lombok.Data;

/**
 * 画面ID：SUB0202_0101-01
 * 画面名：資産状況
 * SQL017 SBIラップ口座分の現金情報取得
 * 2023/12/27 新規作成
 *
 * @author SCSK 江口
 *
 */
@Data
public class IfaPortfolioSql017ResponseModel {

    /** 証券種別コード（商品分類コード） */
    private String securityClassCode;

    /** 評価額（円貨）（数値(整数)） */
    private String valuation;

}
