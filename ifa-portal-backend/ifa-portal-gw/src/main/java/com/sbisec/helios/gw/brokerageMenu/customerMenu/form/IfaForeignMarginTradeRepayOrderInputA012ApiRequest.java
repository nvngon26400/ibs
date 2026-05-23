package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class IfaForeignMarginTradeRepayOrderInputA012ApiRequest {
    
    /** 市場コード（全角半角）. */
    @NotEmpty(message = "市場コード")
    @Size(max = 12, message = "市場コード")
    private String marketCode;
    
    /** 銘柄コード（半角英数字）. */
    @NotEmpty(message = "銘柄コード")
    @Size(max = 5, message = "銘柄コード")
    private String brandCode;
    
    /** 取引種別（全角半角）. */
    @NotEmpty(message = "取引種別")
    @Size(min = 3, max = 3, message = "取引種別")
    private String tradeCd;
    
    /** 注文数量（返済株数）. */
    @NotEmpty(message = "注文数量（返済株数）")
    private String closeOrderQuantity;
    
    /** 注文数量（合計）. */
    @NotEmpty(message = "注文数量（合計）")
    private String total;
    
    /** 価格条件. */
    @NotEmpty(message = "価格条件")
    private String orderPriceKindList;
    
    /** 注文単価（指値）. */
    @Digits(integer = 10, fraction = 2, message = "注文単価")
    @NotEmpty(message = "注文単価（指値）")
    @Size(max = 16, message = "注文単価（指値）")
    private String limitPrice2;
    
    /** 注文単価（逆指値）. */
    @Digits(integer = 10, fraction = 2, message = "注文単価")
    @NotEmpty(message = "注文単価（逆指値）")
    @Size(max = 16, message = "注文単価（逆指値）")
    private String stopOrderExecutePrice2;
    
    /** 発火条件価格. */
    @NotEmpty(message = "発火条件価格")
    private String stopOrderPrice;
    
    /** 期間条件. */
    @NotEmpty(message = "期間条件")
    private String periodRadio;
    
    /** 期間 . */
    @DateTimeFormat(pattern = "yy/MM/dd")
    @JsonFormat(pattern = "yy/MM/dd")
    @NotEmpty(message = "期間 ")
    @Size(min = 10, max = 10, message = "期間 ")
    private String period;
    
    /** 預り区分（全角半角）. */
    @NotEmpty(message = "預り区分")
    @Size(max = 20, message = "預り区分")
    private String depositType;
    
    /** 信用期日. */
    @NotEmpty(message = "信用期日")
    private String marginDueDate;
    
    /** 決済方法（半角英数字）. */
    @NotEmpty(message = "決済方法")
    @Size(min = 1, max = 1, message = "決済方法")
    private String kessaiHoho;
    
    /** 返済建玉指定方法（全角半角）. */
    @NotEmpty(message = "返済建玉指定方法")
    @Size(max = 8, message = "返済建玉指定方法")
    private String repayPositionDesignateMethod;
    
    /** 返済選択順序（全角半角）. */
    @NotEmpty(message = "返済選択順序")
    @Size(max = 20, message = "返済選択順序")
    private String repaySelectOrder;
    
    /** 建区分. */
    @NotEmpty(message = "建区分")
    private String trade;
    
    /** 預り区分（個別）. */
    @NotEmpty(message = "預り区分（個別）")
    private String depositTypeIndividual;
    
    /** 勧誘区分（全角半角）. */
    @NotEmpty(message = "勧誘区分")
    @Size(max = 2, message = "勧誘区分")
    private String kanyuKbn;
    
    /** 受注方法. */
    @NotEmpty(message = "受注方法")
    private String receiveOrderType;
    
    /** 株価チケット（全角半角）. */
    @NotEmpty(message = "株価チケット")
    @Size(max = 5000, message = "株価チケット")
    private String stockTicket;
    
    /** 確認項目.インサイダー確認（半角英数字）. */
    @NotEmpty(message = "確認項目.インサイダー確認")
    @Size(min = 1, max = 1, message = "確認項目.インサイダー確認")
    private String checkInsider;

    /** 本日の注意銘柄URL. */
    @NotEmpty(message = "本日の注意銘柄URL")
    private String tradeLimitUrl;
    
    /** 休場日URL. */
    @NotEmpty(message = "休場日URL")
    private String closedDayUrl;
    
    /** 円貨決済停止日URL. */
    @NotEmpty(message = "円貨決済停止日URL")
    private String yenClosedUrl;
    
    /** 取扱銘柄一覧URL. */
    @NotEmpty(message = "取扱銘柄一覧URL")
    private String usequityListUrl;
    
    /** お取引注意事項URL. */
    @NotEmpty(message = "お取引注意事項URL")
    private String tradingAttentionUrl;
    
    /** 弁済期限（全角半角）. */
    @NotEmpty(message = "弁済期限")
    @Size(max = 6, message = "弁済期限")
    private String paymentDeadline;
    
    /** 個別建玉情報一覧. */
    private List<IfaForeignMarginTradeRepayOrderInputA012Api_positionDesignationAreaIndividualPositionInfoList> positionDesignationAreaIndividualPositionInfoList;
}
