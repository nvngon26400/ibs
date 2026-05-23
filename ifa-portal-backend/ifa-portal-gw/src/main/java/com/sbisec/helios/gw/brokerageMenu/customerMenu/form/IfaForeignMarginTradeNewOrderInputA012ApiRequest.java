package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * 米株信用取引新規注文入力 A012 リクエストパラメータ
 *
 * @author SCSK池田
 * 
 */
@Data
public class IfaForeignMarginTradeNewOrderInputA012ApiRequest {
    
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
    private String tradeCd;
    
    /** 注文数量. */
    @NotEmpty(message = "注文数量")
    private String foreignQuantity;
    
    /** 価格条件. */
    @NotEmpty(message = "価格条件")
    private String orderPriceKindList;
    
    /** 価格条件（逆指値）. */
    @NotEmpty(message = "価格条件（逆指値）")
    private String orderPriceKindListReversePriceLimit;
    
    /** 注文単価（指値）. */
    @Digits(integer = 10, fraction = 2, message = "注文単価（指値）")
    @NotEmpty(message = "注文単価（指値）")
    @Size(max = 14, message = "注文単価（指値）")
    private String hiddenOrderPrice;
    
    /** 注文単価（逆指値）. */
    @Digits(integer = 10, fraction = 2, message = "注文単価（逆指値）")
    @NotEmpty(message = "注文単価（逆指値）")
    @Size(max = 14, message = "注文単価（逆指値）")
    private String hiddenOrderPriceReversePriceLimit;
    
    /** 発火条件価格. */
    @NotEmpty(message = "発火条件価格")
    private String orderInputAreaPriceTermsreversePriceLimitStopOrderPrice;
    
    /** 期間条件. */
    @NotEmpty(message = "期間条件")
    private String periodRadio;
    
    /** 期間 "yyyy-MM-dd"形式. */
    @NotEmpty(message = "期間:yyyy-MM-dd形式")
    private String period;
    
    /** 預り区分（全角半角）. */
    @NotEmpty(message = "預り区分")
    @Size(max = 20, message = "預り区分")
    private String depositType;
    
    /** 信用期日. */
    @NotEmpty(message = "信用期日")
    private String marginDueDate;
    
    /** 決済方法（全角半角）. */
    @NotEmpty(message = "決済方法")
    private String kessaiHoho;
    
    /** 勧誘区分（全角半角）. */
    @NotEmpty(message = "勧誘区分")
    @Size(max = 2, message = "勧誘区分")
    private String kanyuKbn;
    
    /** 受注方法. */
    @NotEmpty(message = "受注方法")
    private String receiveOrderType;
    
    /** 重要事項の説明. */
    @NotEmpty(message = "重要事項の説明")
    private String importantMatterType;
    
    /** 英文開示銘柄. */
    @NotEmpty(message = "英文開示銘柄")
    private String engPubText;
    
    /** 確認項目.インサイダー確認（全角半角）. */
    @NotEmpty(message = "確認項目.インサイダー確認")
    private String checkInsider;

    /** 本日の注意銘柄URL. */
    @NotEmpty(message = "本日の注意銘柄URL")
    private String tradeLimit;
    
    /** 休場日URL. */
    @NotEmpty(message = "休場日URL")
    private String closedDay;
    
    /** 円貨決済停止日URL. */
    @NotEmpty(message = "円貨決済停止日URL")
    private String yenClosed;
    
    /** 取扱銘柄一覧URL. */
    @NotEmpty(message = "取扱銘柄一覧URL")
    private String usequityList;
    
    /** お取引注意事項URL. */
    @NotEmpty(message = "お取引注意事項URL")
    private String tradingAttention;
    
}
