package com.sbisec.helios.ap.athena.protocol.account;

import com.sbisec.helios.ap.athena.protocol.account.dto.EvaluationProfitLoss;
import com.sbisec.helios.ap.athena.protocol.common.Market;
import com.sbisec.helios.ap.athena.protocol.common.PriceData;
import com.sbisec.helios.ap.athena.protocol.common.Securities;

import lombok.Data;

/**
 * 外貨建商品保有証券 Response
 * 
 * 2023/11/22移植
 */
@Data
public class GetSecuritiesBalanceResp {
    
    public GetSecuritiesBalanceResp() {
        
    }
    
    /** 国コード */
    private String countryCode;
    
    /** 通貨コード */
    private String currencyCode;
    
    /** 預り区分 */
    private String specificAccountCode;
    
    /** 銘柄情報 */
    private Securities securities;
    
    /** 市場情報 */
    private Market market;
    
    /** 保有株数 */
    private String securitiesQuantity;
    
    /** 売却注文中 */
    private String sellFixedOrderQuantity;
    
    /** 取得（参考）単価（外貨） */
    private String frnAcquisitionPrice;
    
    /** 取得（参考）単価（円貨） */
    private String acquisitionPrice;
    
    /** 取得金額（外貨） */
    private String frnAcquisitionAmount;
    
    /** 取得金額（円貨） */
    private String acquisitionAmount;
    
    /** 取得為替レート */
    private String acquisitionExchangeRate;
    
    /** 現在値（円換算額） */
    private String yenLastPrice;
    
    /** 純資産価格 */
    private String principalPrice;
    
    /** 株価情報 */
    private PriceData stockPrice;
    
    /** 評価損益 */
    private EvaluationProfitLoss evaluationProfitLoss;
    
    /** 注意銘柄判定 */
    private Boolean attentionSecurities;
    
    /** 保護区分 */
    private String depositType;
    
    /** 銘柄上場ステータス */
    private String listedSecuritiesStatus;
    
}
