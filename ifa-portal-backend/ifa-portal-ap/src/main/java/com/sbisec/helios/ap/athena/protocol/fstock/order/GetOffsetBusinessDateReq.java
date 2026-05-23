package com.sbisec.helios.ap.athena.protocol.fstock.order;

import com.sbisec.helios.ap.athena.annotation.UrlParm;
import com.sbisec.helios.ap.athena.protocol.BaseRequest;

/**
 * Description 外国株式取引サービス - 外国株式現地営業日取得API（日数指定版） Request.
 * 
 * @author yunhui.zhao
 * @date 02/14/2022
 */
public class GetOffsetBusinessDateReq implements BaseRequest {
    
    public GetOffsetBusinessDateReq() {
        
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
        
        public Header() {
            
        }
    }
    
    public class Parameter {
        
        public Parameter() {
            
        }
        
        // カレンダー対象国
        @UrlParm(name = "country_code")
        private String countryCode;
        
        // 基準日(yyyy-MM-dd)
        @UrlParm(name = "base_date")
        private String baseDate;
        
        // 基準日から何営業日数で計算
        private Integer businessDateOffset;
        
        public String getCountryCode() {
            
            return countryCode;
        }
        
        /**
         * @param countryCode 必須「カレンダー対象国」
         */
        public void setCountryCode(String countryCode) {
            
            this.countryCode = countryCode;
        }
        
        public String getBaseDate() {
            
            return baseDate;
        }
        
        /**
         * @param baseDate 必須「基準日(yyyy-MM-dd)」
         */
        public void setBaseDate(String baseDate) {
            
            this.baseDate = baseDate;
        }
        
        public Integer getBusinessDateOffset() {
            
            return businessDateOffset;
        }
        
        /**
         * @param businessDateOffset 「基準日から何営業日数で計算」
         */
        public void setBusinessDateOffset(Integer businessDateOffset) {
            
            this.businessDateOffset = businessDateOffset;
        }
    }
}
