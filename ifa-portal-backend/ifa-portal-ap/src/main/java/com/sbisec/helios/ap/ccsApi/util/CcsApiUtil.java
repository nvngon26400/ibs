package com.sbisec.helios.ap.ccsApi.util;

/**
 * CCS API URL
 */
public class CcsApiUtil {

    /** バイト配列を文字列に変換する際の文字コード */
    public static String CHARSET_SJIS = "Shift_JIS";

    /** バイト配列を文字列に変換する際の文字コード */
    public static String CHARSET_MS932 = "MS932";

    /**
     * @return 接触履歴参照API
     */
    public static String getShowSubContractHistoryIFA() {
        return "/CCS/api/ShowSubContractHistoryIFA.do";
    }

    /**
     * @return 接触履歴詳細参照API
     */
    public static String getShowDealingDetailIFA() {
        return "/CCS/api/ShowDealingDetailIFA.do";
    }
}
