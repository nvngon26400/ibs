package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 画面ID：SUB0202_0109-01
 * 画面名：取引履歴（顧客別）
 * 2025/07/24 新規作成
 *
 * @author SCSK
 */
@Data
public class IfaCustomerTradeHistoryA002ApiRequest {

    /** 顧客ID */
    private String customerCode;

    /** 会社コード */
    private String companyCode;

    /** 回数 */
    private String times;

    /** 号 */
    private String issue;

    /** 商品区分 */
    private String securityType;

    /** 取引区分 */
    private String tradeType;

    /** 並び順指定【項目】 */
    private String sortOrderItem;

    /** 並び順指定【属性】 */
    private String sortOrderProfile;

    /**期間指定（From） */
    private String periodFrom;

    /**期間指定（To） */
    private String periodTo;

}
