package com.sbisec.helios.ap.athena.utils;

import com.sbibits.earth.util.StringUtil;

/**
 * Requestツールクラス
 * 
 * @author shuchen.xin
 * @date: 02/21/2022
 */
public class RequestUtil {
    
    /** 接続文字: '-' */
    private static final String CONNECTOR_SYMBOL = "-";
    
    /** 左側に追加する文字: '0' */
    private static final char CHAR_ZERO = '0';
    
    /** チャネル: 電話 PHONE */
    private static final String CHANNEL_PHONE = "PHONE";
    
    /**
     * 部店口座を利用し、tokenを取得する。
     * 
     * @param butenCode 部店
     * @param accountNo 口座
     * @return {部店}3桁 + "-" + {口座}7桁
     */
    public static String getToken(String butenCode, String accountNo) {
        
        // 口座：指定の7桁文字数になるまで左側に文字'0'を追加する
        String accountNoFill = StringUtil.fillLeft(accountNo, CHAR_ZERO, 7);
        
        return String.join(CONNECTOR_SYMBOL, butenCode, accountNoFill);
    }
    
    /**
     * チャネルを取得する。
     * 
     * @return 固定値: 電話 ： PHONE
     */
    public static String getChannel() {
        
        return CHANNEL_PHONE;
    }
    
}
