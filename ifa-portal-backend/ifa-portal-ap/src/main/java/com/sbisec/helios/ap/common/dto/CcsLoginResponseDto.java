package com.sbisec.helios.ap.common.dto;

import lombok.Data;

/**
 * CCSログイン
 *
 * @author SCSK 矢口
 */
@Data
public class CcsLoginResponseDto {
    
    /** ログアウトURL */
    private String logoutUrl;
    
    /** ログインURL */
    private String loginUrl;
    
    /** 遷移先URL */
    private String transitionUrl;
    
    /** 一時遷移先URL */
    private String tempUrl;
}
