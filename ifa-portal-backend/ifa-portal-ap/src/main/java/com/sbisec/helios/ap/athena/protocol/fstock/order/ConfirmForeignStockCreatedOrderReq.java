package com.sbisec.helios.ap.athena.protocol.fstock.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sbisec.helios.ap.athena.protocol.BaseRequest;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.OrderInput;

import lombok.Data;

/**
 * 外国株式取引サービス - 外国株式現物注文確認API リクエスト.
 *
 * @author SCSK 宇田川達弥
 * @date 2024/02/16
 */
@Data
public class ConfirmForeignStockCreatedOrderReq implements BaseRequest {
    
    public ConfirmForeignStockCreatedOrderReq() {
        
    }
    
    // headerとparameterインスタンス化
    private Header header = new Header();
    private Parameter parameter = new Parameter();
    
    /**
     * 外国株式取引サービス - 外国株式現物注文確認API リクエストヘッダ.
     */
    @Data
    public class Header {
        
        public Header() {
            
        }
        
        /** token ({部店}3桁 + "-" + {口座}7桁) */
        private String token;
        
        /** 取引パスワード */
        @JsonProperty("trade_password")
        private String tradePassword;

        /** チケット */
        private String ticket;

        /** チャネル */
        private String channel;
        
    }
    
    /**
     * 外国株式取引サービス - 外国株式現物注文確認API リクエストパラメータ.
     */
    @Data
    public class Parameter {

        /** 国コード */
        private String countryCode;
        
        /** 市場コード */
        private String marketCode;
        
        /** 銘柄コード */
        private String securitiesCode;
        
        /** 売買区分 */
        private String buySellCode;
        
        /** 注文数量 */
        private String orderQuantity;
        
        /** 価格条件 */
        private String orderPriceKindCode;
        
        /** 注文単価 */
        private String orderPrice;
        
        /** 発火条件価格 */
        private String stopPrice;
        
        /** トレールストップ幅 */
        private String trailingStopAmount;
        
        /** 成行基準価格 */
        private String noLimitPrice;
        
        /** 期間条件 */
        private String orderLimitCode;
        
        /** 期間 "yyyy-MM-dd"形式 */
        private String orderTerm;
        
        /** 預り区分 */
        private String specificAccountCode;
        
        /** 決済方法 */
        private String settlementMethodCode;
        
        /** 余力チェック不要 */
        private Boolean remainingPowerCheckDisabled;
        
        /** NISA枠チェック不要 */
        private Boolean nisaRemainingPowerCheckDisabled;
        
    }
}
