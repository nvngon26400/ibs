package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 国内投信購入可能一覧 明細 レスポンスパラメタ
 *
 * @author SCSK池田
 */
@Data
public class IfaDomesticMutualFundBuyAbleListDetail {

    /** コード銘柄名. */
    // 手動変換
    private String brandCodeBrandName;

    /** ファンドタイプ. */
    private String fundType;

    /** 目論見書状態. */
    // 手動変換
    private String prospectus;

    /** 目論見書発送日. */
    private String prospectusShippingDate;

    /** 買付可能日. */
    // 手動変換
    private String buyAbledate;

    /** マーケット発注日. */
    private String marketOrderDate;

    /** 基準価額. */
    // 手動変換
    private String price;

    /** 前日比. */
    private String diff;

    /** 買付手数料（税込）（数値(小数)）. */
    private String buyComm;

    /** 優遇適用率（数値(小数)）. */
    private String preferentialApplicationRate;

    /** 注文締切時間. */
    // 手動変換
    private String deadlines;

    /** 通貨選択確認書受入状況. */
    private String currencySelectConfirmDocument;

    /** 複雑投信確認書受入状況. */
    // 手動変換
    private String complexMutualFundConfirmDocument;

    /** 購入可否. */
    // 手動変換
    private String coercionOrderTarget;

    /** 積立可否. */
    // 手動変換
    private String accumulateType;

    /** 協会コード. */
    // 手動変換
    private String kyoukaiCd;
    
    /** ファンドコード（回数）. */
    private String fundCodeTimes;
    
    /** ファンドコード（号）. */
    // 手動変換
    private String fundCodeIssues;
  
}
