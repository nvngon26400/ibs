package com.sbisec.helios.ap.common.util;

import com.sbibits.earth.util.StringUtil;

/**
 * 口座のデータに関わる共通処理を持ちます.
 *
 * @author SCSK 笹倉 秀行
 */
public class AccountUtil {
    
    /**
     * APIに設定する口座データをフォーマットする処理です.
     *　<p/>
     *　変更管理 #1684を参照。
     *
     * @param account 口座
     * @return 変換後の口座
     */
    public static String formatToApi(String account) {
        
        return StringUtil.fillLeft(account, '0', 7);
    }
}
