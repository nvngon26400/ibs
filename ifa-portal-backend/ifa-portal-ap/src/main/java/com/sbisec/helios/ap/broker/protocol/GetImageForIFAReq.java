package com.sbisec.helios.ap.broker.protocol;

/**
 * 
 * 顧客帳票取得API Request
 * 
 * @author katsuhiko.kagoshima
 * @date: 12/23/2022
 */
public class GetImageForIFAReq implements PapyBaseRequest {
    
    public GetImageForIFAReq() {
        
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
        
        // IFA識別子
        private String ifaIdentifier;
        
        // 部店
        private String departId;
        
        // 口座番号
        private String accountNo;
        
        /**
         * @return ifaIdentifier
         */
        public String getIfaIdentifier() {
            
            return ifaIdentifier;
        }
        
        /**
         * @param ifaIdentifier
         */
        public void setIfaIdentifier(String ifaIdentifier) {
            
            this.ifaIdentifier = ifaIdentifier;
        }
        
        /**
         * @return departId
         */
        public String getDepartId() {
            
            return departId;
        }
        
        /**
         * @param departId
         */
        public void setDepartId(String departId) {
            
            this.departId = departId;
        }
        
        /**
         * @return accountNo
         */
        public String getAccountNo() {
            
            return accountNo;
        }
        
        /**
         * @param accountNo
         */
        public void setAccountNo(String accountNo) {
            
            this.accountNo = accountNo;
        }
    }
}
