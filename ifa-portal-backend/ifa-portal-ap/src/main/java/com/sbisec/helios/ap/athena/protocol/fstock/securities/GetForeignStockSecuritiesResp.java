package com.sbisec.helios.ap.athena.protocol.fstock.securities;

import com.sbisec.helios.ap.athena.protocol.common.Market;
import com.sbisec.helios.ap.athena.protocol.common.Securities;

import lombok.Data;

/**
 * 外国株式銘柄マスタ取得 Response
 * 
 */
@Data
public class GetForeignStockSecuritiesResp {
    
    public GetForeignStockSecuritiesResp() {
        
    }
    
    /** 銘柄 */
    private Securities securities;
    
    /** 市場 */
    private Market market;
    
    /** 通貨コード */
    private String currencyCode;
    
    /** NISA対象銘柄 */
    private Boolean nisaSecurities;
    
    /** 総合NISA対象銘柄 */
    private Boolean generalNisaSecurities;
    
    /** 銘柄種別 */
    private String securitiesType;
    
    /** 銘柄上場ステータス */
    private String listedSecuritiesStatus;
    
    /** 取引単位 */
    private String tradeUnit;
    
    /** 取引下限数量 */
    private String tradeLimitMin;
    
    /** 取引上限数量 */
    private String tradeLimitMax;
    
    /** ブローカーコード */
    private String brokerCode;
    
    /** 信用新規買建規制 */
    private Boolean openBuyRestrict;
    
    /** 信用新規売建規制 */
    private Boolean openSellRestrict;
    
    /** 代用不適格 */
    private Boolean collateralUnsuitable;
}
