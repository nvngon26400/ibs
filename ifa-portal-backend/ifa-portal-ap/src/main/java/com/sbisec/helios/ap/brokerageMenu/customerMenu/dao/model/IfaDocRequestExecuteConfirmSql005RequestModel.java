package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import java.util.Date;

import lombok.Data;

/**
 * 書類請求入力sql005リクエスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestExecuteConfirmSql005RequestModel {

    /** BM交付番号 */
    private String bmDeliveryNo;

    /** 種別 */
    private String shubetsu;

    /** 部店コード */
    private String butenCode;

    /** 口座番号 */
    private String accountNumber;

    /** 顧客ID */
    private String kokyakuId;

    /** 書類コード */
    private String shoruiCd;

    /** 書類名 */
    private String shoruimei;

    /** 書類分類コード */
    private String shoruiBunruiCd;

    /** 書類分類名 */
    private String shoruiBunruiMei;

    /** 銘柄コード */
    private String meigaraCd;

    /** 銘柄名 */
    private String meigaraMei;

    /** 協会コード */
    private String kyokaiCd;

    /** BM交付日時 */
    private Date bmDeliveryTime;

    /** BM配信予定日時 */
    private Date bmDeliveryTimeSchedule;

    /** ユーザーID */
    private String userId;

    /** CCSログイン用ID */
    private String ccsUserId;

    /** 仲介業者コード */
    private String brokerCode;

    /** 仲介業者営業員コード */
    private String intermediaryEmpCd;

    /** ステータス */
    private String status;
}
