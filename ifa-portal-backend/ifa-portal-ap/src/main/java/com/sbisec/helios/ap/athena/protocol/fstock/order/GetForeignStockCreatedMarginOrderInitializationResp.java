package com.sbisec.helios.ap.athena.protocol.fstock.order;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.common.Market;
import com.sbisec.helios.ap.athena.protocol.common.Securities;
import com.sbisec.helios.ap.athena.protocol.common.TickSize;

import lombok.Data;

/**
 * 外国株式取引サービス - 外国株式信用注文初期情報取得API
 * 
 * 2023/11/22移植
 */
@Data
public class GetForeignStockCreatedMarginOrderInitializationResp {
    
    public GetForeignStockCreatedMarginOrderInitializationResp() {
    
    }
    
    /** 銘柄情報 */
    private Securities securities;
    
    /** 銘柄種別 */
    private String securitiesType;
    
    /** 取引通貨 */
    private String tradeCurrencyCode;
    
    /** 市場情報 */
    private Market market;
    
    /** 値幅制限なし。 true：なし/false：あり */
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
    
    /** 選択可能信用期日リスト */
    private List<String> marginCloseLimitTypes;
    
    /** 選択可能返済建玉指定方法リスト */
    private List<String> closePositionKinds;
    
    /** 選択可能返済選択順序リスト */
    private List<String> closeSelectionSorts;
    
    /** 選択可能期間条件リスト */
    private List<String> orderLimitCodes;
    
    /** 有効期間一覧 "yyyy-MM-dd"形式 */
    private List<String> orderTerms;
    
}
