package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * 外国現物取引注文入力注文確認APIリクエスト
 *
 * @author SCSK 宇田川達弥
 */
@Data
public class IfaForeignSpotTradeOrderInputA013ApiRequest {
    
    /** 国コード（全角半角）. */
    @NotEmpty(message = "国コード")
    @Size(min = 2, max = 2, message = "国コード")
    private String countryCode;
    
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
    private String buySellTypeName;
    
    /** 注文数量. */
    @NotEmpty(message = "注文数量")
    private String orderQuantity;
    
    /** 価格条件. */
    @NotEmpty(message = "価格条件")
    private String orderPriceKindList;
    
    /** 注文単価（数値(小数)）. */
    @Digits(integer = 10, fraction = 2, message = "注文単価")
    @NotEmpty(message = "注文単価")
    @Size(max = 16, message = "注文単価")
    private String limitOrderPrice;
    
    /** 注文単価（数値(小数)）. */
    @Digits(integer = 10, fraction = 2, message = "注文単価")
    @NotEmpty(message = "注文単価")
    @Size(max = 16, message = "注文単価")
    private String stopOrderExecutePrice;
    
    /** 発火条件価格. */
    @NotEmpty(message = "発火条件価格")
    private String stopOrderPrice;
    
    /** 期間条件. */
    @NotEmpty(message = "期間条件")
    private String periodRadio;
    
    /** 期間. */
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @JsonFormat(pattern = "yyyy/MM/dd")
    @NotEmpty(message = "期間")
    @Size(min = 10, max = 10, message = "期間")
    private String period;
    
    /** 預り区分（全角半角）. */
    @NotEmpty(message = "預り区分")
    @Size(max = 20, message = "預り区分")
    private String depositType;
    
    /** 決済方法（半角英数字）. */
    @NotEmpty(message = "決済方法")
    @Size(min = 1, max = 1, message = "決済方法")
    private String currencyTypeName;
    
    /** 勧誘区分（全角半角）. */
    @NotEmpty(message = "勧誘区分")
    @Size(max = 2, message = "勧誘区分")
    private String solicitTypeList;
    
    /** 受注方法. */
    @NotEmpty(message = "受注方法")
    private String receiveOrderType;
    
    /** 重要事項の説明. */
    @NotEmpty(message = "重要事項の説明")
    private String importantMatterType;
    
    /** 乗換え勧誘(ETF). */
    @NotEmpty(message = "乗換え勧誘(ETF)")
    private String solicitationEtf;
    
    /** 英文開示銘柄. */
    @NotEmpty(message = "英文開示銘柄")
    private String engPubCheckbox;
    
    /** 確認項目.インサイダー確認（半角英数字）. */
    @NotEmpty(message = "確認項目.インサイダー確認")
    @Size(min = 1, max = 1, message = "確認項目.インサイダー確認")
    private String checkInsider;

    /** 本日の注意銘柄URL. */
    @NotEmpty(message = "本日の注意銘柄URL")
    private String todayTradeLimitUrl;
    
    /** 休場日URL. */
    @NotEmpty(message = "休場日URL")
    private String closedDay;
    
    /** 円貨決済停止日URL. */
    @NotEmpty(message = "円貨決済停止日URL")
    private String yenClosedDateUrl;
    
    /** 取扱銘柄一覧URL. */
    @NotEmpty(message = "取扱銘柄一覧URL")
    private String handledStockListUrl;
    
    /** お取引注意事項URL. */
    @NotEmpty(message = "お取引注意事項URL")
    private String noticeofTransactionPrecautionsUrl;

}
