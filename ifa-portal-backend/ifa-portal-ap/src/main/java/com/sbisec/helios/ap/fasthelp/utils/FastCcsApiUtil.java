package com.sbisec.helios.ap.fasthelp.utils;

import java.util.HashMap;
import java.util.Map;

import com.sbibits.earth.util.StringUtil;

/**
 * Fasthelp API URL
 * 
 * @author dalian
 * @date 04/29/2025
 */
public class FastCcsApiUtil {

    /**
     * @return FastHelp問合せデータ登録
     */
    public static String getFasthelp_ccs_calls_import() {
        return "/fasthelp5-server/ccsCallsImport";
    }

    /**
     * @return CCS問合せ履歴登録、更新
     */
    public static String getCcs_fastHelp_info_insert_do() {
        return "/CCS/api/FastHelpInfoInsert.do";
    }

    /*--------------------------------- コード変換 ---------------------------------*/
    /* 符号とコードのマッピングを保持するマップ */
    private static final Map<String, String> CODE_MAP = new HashMap<>();
    // マップに符号と対応するコードを追加
    static {
        CODE_MAP.put("\"", "%22");  // javaのエスケープ文字
        CODE_MAP.put("%", "%25");   // 文字コード指示
        CODE_MAP.put("&", "%26");   // パラメータ繋ぎ用
        CODE_MAP.put("=", "%3D");   // パラメータIDとパラメータ内容繋ぎ用
        CODE_MAP.put("\\", "%5C");  // javaのエスケープ文字
        CODE_MAP.put("+", "%2B");   // 変換しないとスペースになる
        CODE_MAP.put("?", "%3F");   // パラメータ指示用
        CODE_MAP.put(" ", "%20");   // 半角スペース
        CODE_MAP.put("\r", "%0D");  // CR
        CODE_MAP.put("\n", "%0A");  // LF
        CODE_MAP.put("\t", "%09");  // TAB
    }

    /**
     * 指定された符号を対応するコードに変換します。
     *
     * @param x_str 変換する内容
     * @return 変換後のコード。符号がマップに存在しない場合は元の符号を返します。
     */
    public static String convertSymbol(String x_str) {
        // 入力文字列がnullまたは空の場合、そのまま返す
        if (StringUtil.isNullOrEmpty(x_str)) {
            return x_str;
        }
        // マップ内の各エントリを順に処理し、符号を対応するコードに置換する
        for (Map.Entry<String, String> entry : CODE_MAP.entrySet()) {
            x_str = x_str.replace(entry.getKey(), entry.getValue());
        }
        // 変換後の文字列を返す
        return x_str;
    }
}
