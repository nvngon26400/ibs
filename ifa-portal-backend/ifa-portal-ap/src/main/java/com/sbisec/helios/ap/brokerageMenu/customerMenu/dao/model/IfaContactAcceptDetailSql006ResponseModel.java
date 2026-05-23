package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaContactAcceptDetailSql006ResponseModel {

    /** 銘柄コード */
    private String brandCode;

    /** 売買区分 */
    private String tradeType;

    /** 注文数量 */
    private String orderCount;

    /** 決済通貨 */
    private String kessaiTuka;

    /** 預り区分 */
    private String depositType;

    /** 作成日 */
    private String createTime;

    /** ステータス */
    private String orderStatus;

    /** 銘柄名 */
    private String brandName;

    /** ユーザ名 */
    private String userNm;
}
