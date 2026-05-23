package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 書類請求入力sql12レスポンスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestInputSql012ResponseModel {

    /** 書類分類名 */
    private String shoruiBunruiMei;

    /** 書類名 */
    private String shoruimei;

    /** 銘柄コード */
    private String meigaraCd;

    /** 銘柄名 */
    private String meigaraMei;

    /** BM配信予定日時 */
    private String bmDeliveryTimeSchedule;

    /** ユーザー名 */
    private String userNm;

    /** 種別 */
    private String shubetsu;
    
    /** BM交付番号 */
    private String bmDeliveryNo;
}
