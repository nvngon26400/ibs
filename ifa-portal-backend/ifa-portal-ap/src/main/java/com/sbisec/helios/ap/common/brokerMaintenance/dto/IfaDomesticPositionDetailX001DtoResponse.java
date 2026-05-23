package com.sbisec.helios.ap.common.brokerMaintenance.dto;

import lombok.Data;

@Data
public class IfaDomesticPositionDetailX001DtoResponse {
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 銘柄（全角半角）. */
    private String brand;
    
    /** 市場（全角）. */
    private String market;
    
    /** 売／買建. */
    private String newMarket;
    
    /** 期限. */
    private String limit;
    
    /** 親株新規約定日. */
    private String positionDetailDeadLine;
    
    /** 建日. */
    private String openTradeDate;
    
    /** 返済期限. */
    private String lastTradeDate;
    
    /** 期日短縮区分フラグ. */
    private String repayPeriodShorterFlag;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 建株数合計（数値(整数)）. */
    private String stockNumTotal;
    
    /** 平均建単価. */
    private String positionPriceLabel;
    
    /** 建代金合計（数値(小数)）. */
    private String constructionPriceTotal;
    
    /** 新規建手数料（税込）. */
    private String domesticNewComm;
    
    /** 管理料（数値(整数)）. */
    private String managePrice;
    
    /** 権利処理等手数料（数値(整数)）. */
    private String rightProcessingCharge;
    
    /** 日歩（数値(整数)）. */
    private String dailyInterest;
    
    /** 逆日歩および貸株料（数値(整数)）. */
    private String reverseDailyInterest;
    
    /** 合計（数値(整数)）. */
    private String total;
    
    /** 新規建保証金率（数値(小数)）. */
    private String newDepositRate;
    
    /** 現金保証金率（数値(小数)）. */
    private String cashDepositRate;
    
    /** 現物買付保証金率（数値(小数)）. */
    private String cashBuyDepositRate;
    
    /** 出金・振替保証金率（数値(小数)）. */
    private String withdrawTransferDepositRate;
    
    /** 増担保規制（全角半角）. */
    private String additionalCollateralRegulations;
    
    /** 一括個別表示フラグ. */
    private String batchIndividualDisplayFlag;
    
}
