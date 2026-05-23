package com.sbisec.helios.ap.athena.protocol.account;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;

/**
 * Description:余力サービス 外貨信用保証金残高スケジュール取得API Request

 * @author SCSK 矢口
    2023/12/1 新規作成
 */
public class ListForeignMarginScheduleCashBalancesReq implements BaseRequest {
    
    public ListForeignMarginScheduleCashBalancesReq() {
        
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
    
    /**
     * @author SCSK
     *
     */
    public class Header {
        
        // token
        private String token;
        
        public String getToken() {
            
            return token;
        }
        
        /**
         * @param token 「必須」 部店コード(3桁) + '-' + 口座コード(7桁)を設定
         */
        public void setToken(String token) {
            
            this.token = token;
        }
        
    }
    
    /**
     * @author SCSK
     *
     */
    public class Parameter {
        
        public Parameter() {
            
        }
        
        // 通貨コード
        private String currencyCode;
        
        // 取得日数
        private Integer days;
        
        public String getCurrencyCode() {
            
            return currencyCode;
        }
        
        /**
         * @param currencyCode 通貨コード
         */
        public void setCurrencyCode(String currencyCode) {
            
            this.currencyCode = currencyCode;
        }
        
        public Integer getDays() {
            
            return days;
        }
        
        /**
         * @param days 取得日数
         */
        public void setDays(Integer days) {
            
            this.days = days;
        }
    }
}
