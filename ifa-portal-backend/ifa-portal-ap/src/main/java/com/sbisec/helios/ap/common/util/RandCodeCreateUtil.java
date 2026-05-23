package com.sbisec.helios.ap.common.util;

import java.util.Random;

/**
 * ランダムコード作成共通処理
 *
 * @author SCSK
 *
 */
public class RandCodeCreateUtil {
    
    private static final String str = "ACDEFGHGKLMNPQRTUVWXY3479";
    
    /**
     * 認証コード作成処理
     *
     * @param codeNum コード長
     * @return 認証コード
     */
    public static String createCode(int codeNum) {
        
        Random r = new Random();
        String code = "";
        for (int i = 0; i < codeNum; i++) {
            int n = r.nextInt(25);
            code += str.substring(n, n + 1);
        }
        
        return code;
    }
    
}
