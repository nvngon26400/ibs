package com.sbisec.helios.ap.athena.protocol.fstock.order;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;

/**
 * 口座情報サービス - 信用口座自動振替設定登録API
 * 
 * @author xin.huang
 */
public class CreateMarginAccountAutoTransferSettingReq implements BaseRequest {
    
    public CreateMarginAccountAutoTransferSettingReq() {
    
    }
    
    // headerとparameterインスタンス化
    private Header header = new Header();
    
    private Parameter parameter = new Parameter();
    
    @SuppressWarnings("unchecked")
    @Override
    public Header getHeader() {
        
        return header;
    }
    
    public void setHeader(Header header) {
        
        this.header = header;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Parameter getParameter() {
        
        return parameter;
    }
    
    public void setParameter(Parameter parameter) {
        
        this.parameter = parameter;
    }
    
    public class Header {
        
        private String token;
        
        private String request_id;
        
        public String getToken() {
            
            return token;
        }
        
        /**
         * @param token 「必須」 部店コード(3桁) + '-' + 顧客コード(7桁)を設定
         */
        public void setToken(String token) {
            
            this.token = token;
        }
        
        public String getRequest_id() {
            
            return request_id;
        }
        
        /**
         * @param request_id 「必須」 Request Id
         */
        public void setRequest_id(String request_id) {
            
            this.request_id = request_id;
        }
        
    }
    
    public class Parameter {
        
        public Parameter() {
        
        }
        
        // 建余力不足 自動振替設定(米ドル)
        Boolean marginBuyingPowerShortfallCash;
        
        // 建余力不足 自動振替設定(米国株式)
        Boolean marginBuyingPowerShortfallSecurities;
        
        // 保証金不足 自動振替設定(米ドル)
        Boolean marginShortfallCash;
        
        // 保証金不足 自動振替設定(米国株式)
        Boolean marginShortfallSecurities;
        
        // 現物買付時 株式自動振替先設定
        String depositType;
        
        public Boolean getMarginBuyingPowerShortfallCash() {
            
            return marginBuyingPowerShortfallCash;
        }
        
        public void setMarginBuyingPowerShortfallCash(Boolean marginBuyingPowerShortfallCash) {
            
            this.marginBuyingPowerShortfallCash = marginBuyingPowerShortfallCash;
        }
        
        public Boolean getMarginBuyingPowerShortfallSecurities() {
            
            return marginBuyingPowerShortfallSecurities;
        }
        
        public void setMarginBuyingPowerShortfallSecurities(Boolean marginBuyingPowerShortfallSecurities) {
            
            this.marginBuyingPowerShortfallSecurities = marginBuyingPowerShortfallSecurities;
        }
        
        public Boolean getMarginShortfallCash() {
            
            return marginShortfallCash;
        }
        
        public void setMarginShortfallCash(Boolean marginShortfallCash) {
            
            this.marginShortfallCash = marginShortfallCash;
        }
        
        public Boolean getMarginShortfallSecurities() {
            
            return marginShortfallSecurities;
        }
        
        public void setMarginShortfallSecurities(Boolean marginShortfallSecurities) {
            
            this.marginShortfallSecurities = marginShortfallSecurities;
        }
        
        public String getDepositType() {
            
            return depositType;
        }
        
        public void setDepositType(String depositType) {
            
            this.depositType = depositType;
        }
    }
}
