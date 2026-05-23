package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0202_0101-01
 * 画面名：資産状況
 * SQL010 現金明細取得
 * 2023/12/27 新規作成
 *
 * @author SCSK 江口
 *
 */
@Data
public class IfaPortfolioSql010ResponseModel {

    /** 名称 */
    private String name;

    /** 評価額合計（数値(整数)） */
    private String valuationTotal;

}
