package com.sbisec.helios.gw.common.form;

import lombok.Data;

/**
 * ログアウト
 *
 */
@Data
public class LogoutResponseForm {
    
    /** CCSログアウトURL */
    private String ccsLogoutUrl;
    
}
