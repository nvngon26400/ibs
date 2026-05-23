package com.sbisec.helios.ap.athena.protocol.fstock.order;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.common.BuyPossibleAmount;
import com.sbisec.helios.ap.athena.protocol.common.Market;
import com.sbisec.helios.ap.athena.protocol.common.NisaLimit;
import com.sbisec.helios.ap.athena.protocol.common.Securities;
import com.sbisec.helios.ap.athena.protocol.common.TickSize;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.SellPossibleQuantity;

import lombok.Data;

/**
 * 外国株式取引サービス - 外国株式現物注文初期情報取得取得API レスポンス.
 *
 * @author SCSK 笹倉 秀行
 * @date 02/13/2024
 */
@Data
public class GetForeignStockCreatedOrderInitializationResp {
    
    /** 銘柄情報 */
    private Securities securities;
    
    /** 取引通貨 */
    private String tradeCurrencyCode;
    
    /** 市場情報 */
    private Market market;
    
    /** 値幅制限 */
    private Boolean priceRangeNoLimit;
    
    /** 値幅下限 */
    private String priceRangeLimitMin;
    
    /** 値幅上限 */
    private String priceRangeLimitMax;
    
    /** 呼値 */
    private List<TickSize> tickSizes;
    
    /** 選択可能預り区分リスト */
    private List<String> specificAccountCodes;
    
    /** 選択可能決済方法リスト */
    private List<String> settlementMethodCodes;
    
    /** 選択可能価格条件リスト */
    private List<String> orderPriceKindCodes;
    
    /** 選択可能期間条件リスト */
    private List<String> orderLimitCodes;
    
    /** 有効期間一覧 "yyyy-MM-dd"形式 */
    private List<String> orderTerms;
    
    /** 買付余力（円貨） */
    private BuyPossibleAmount buyPossibleAmount;
    
    /** 買付余力（外貨） */
    private BuyPossibleAmount frnBuyPossibleAmount;
    
    /** NISA投資可能枠 */
    private List<NisaLimit> nisaLimits;
    
    /** 売却可能数 */
    private List<SellPossibleQuantity> sellPossibleQuantities;
    
}
