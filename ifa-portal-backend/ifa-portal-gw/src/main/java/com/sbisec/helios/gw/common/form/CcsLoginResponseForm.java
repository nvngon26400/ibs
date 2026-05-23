package com.sbisec.helios.gw.common.form;

import lombok.Data;

/**
 * CCSログイン
 *
 * @author SCSK 矢口
 */
@Data
public class CcsLoginResponseForm {
    
    /** ログアウトURL */
    private String logoutUrl;
    
    /** ログインURL */
    private String loginUrl;
    
    /** 遷移先URL */
    private String transitionUrl;
    
    /** 一時遷移先URL */
    private String tempUrl;
}
