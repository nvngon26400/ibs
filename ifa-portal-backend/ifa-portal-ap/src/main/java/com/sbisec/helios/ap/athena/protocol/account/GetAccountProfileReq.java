package com.sbisec.helios.ap.athena.protocol.account;

import com.sbisec.helios.ap.athena.annotation.UrlParm;
import com.sbisec.helios.ap.athena.protocol.BaseRequest;

/**
 * <p>
 * Description: 口座情報サービス - アカウントプロファイル取得 Request
 * </p>
 *
 * @author xiu.yan
 * @version 1.0
 * @date 2/14/2022
 */
public class GetAccountProfileReq implements BaseRequest {
    
    public GetAccountProfileReq() {
    
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
        
        private String token;
        
        /**
         * @return the token
         */
        public String getToken() {
            
            return token;
        }
        
        /**
         * @param token 必須『部店コード(3桁) + '-' + 顧客コード(7桁)を設定)』
         */
        public void setToken(String token) {
            
            this.token = token;
        }
    }
    
    public class Parameter {
        
        public Parameter() {
        
        }
        
        // アカウントプロファイル名
        @UrlParm(name = "profile_name")
        private String profileName;
        
        /**
         * @return profileName
         */
        public String getProfileName() {
            
            return profileName;
        }
        
        /**
         * @param profileName 必須『アカウントプロファイル名』
         */
        public void setProfileName(String profileName) {
            
            this.profileName = profileName;
        }
        
    }
}
