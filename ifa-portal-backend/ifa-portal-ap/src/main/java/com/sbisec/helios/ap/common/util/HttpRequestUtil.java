package com.sbisec.helios.ap.common.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.ContentCachingRequestWrapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbisec.helios.ap.common.filter.wrapper.RepeatableReadHttpServletRequestWrapper;

import lombok.extern.slf4j.Slf4j;

/**
 * HTTPリクエストユーティリティクラス。
 *
 * @author SCSK宮坂
 */
@Slf4j
public class HttpRequestUtil {
    
    /** HTTPヘッダ名（リモートホスト優先度１） */
    private static final String HTTP_HEADER_NM_REMOTE_HOST_1 = "True-Client-IP";
    
    /** HTTPヘッダ名（リモートホスト優先度２） */
    private static final String HTTP_HEADER_NM_REMOTE_HOST_2 = "X-Forwarded-For";
    
    /** リモートホスト不明時文字列 */
    private static final String UNKNOWN_REMOTE_HOST_TEXT = "unknown";
    
    /** ヘッダ値セパレータ */
    private static final String HTTP_HEADER_VALUE_SEP = ",";
    
    /** ログフォーマット（サポート外リクエスト） */
    private static final String LOG_FORMAT_UNSUPPORTED_REQUEST = "HttpRequestUtil.getBodyParamText() Unsupported request type. Return value will be null.";
    
    /**
     * ALBを考慮してリモートホストを取得する。
     *
     * @param httpServletRequest HTTPリクエストサーブレット。
     * @return リモートホスト。
     */
    public static String getRemoteHost(HttpServletRequest httpServletRequest) {
        
        String remoteHost = null;
        
        // まずは優先度１のヘッダから取得
        remoteHost = httpServletRequest.getHeader(HTTP_HEADER_NM_REMOTE_HOST_1);
        
        if (StringUtils.isEmpty(remoteHost) || UNKNOWN_REMOTE_HOST_TEXT.equals(remoteHost)) {
            // 取得できない場合は優先度２のヘッダから取得
            remoteHost = httpServletRequest.getHeader(HTTP_HEADER_NM_REMOTE_HOST_2);
            
            if (StringUtils.isEmpty(remoteHost) || UNKNOWN_REMOTE_HOST_TEXT.equals(remoteHost)) {
                // いずれからも取得できない場合はシンプルに取得
                remoteHost = httpServletRequest.getRemoteHost();
            } else {
                // 複数プロキシを通過する場合を考慮
                remoteHost = remoteHost.split(HTTP_HEADER_VALUE_SEP)[0];
            }
        }
        
        return remoteHost;
    }
    
    /**
     * ALBを考慮してリモートアドレスを取得する。<br>
     * ホスト名をIPアドレスに変換できない場合はホスト名を返す。
     *
     * @param httpServletRequest HTTPリクエストサーブレット。
     * @return リモートアドレス。
     */
    public static String getRemoteAddress(HttpServletRequest httpServletRequest) {
        
        String remoteAddress = null;
        
        // リモートホストを取得
        String remoteHost = getRemoteHost(httpServletRequest);
        
        try {
            // リモートアドレスに変換
            remoteAddress = InetAddress.getByName(remoteHost).getHostAddress();
        } catch (UnknownHostException e) {
            log.debug(e.getMessage(), e);
            
            // リモートアドレスに変換できない場合は、そのままリモートホストを設定
            remoteAddress = remoteHost;
        }
        
        return remoteAddress;
    }
    
    /**
     * フォームパラメータを連結した文字列として取得する。<br>
     * 主にログ出力向けの機能であるため、業務ロジックとしては使用しないことを推奨する。
     *
     * @param httpServletRequest HTTPサーブレットリクエスト。
     * @param format             パラメータ名と値の２つのプレースホルダーを設定した文字列化フォーマット。
     * @param delimiter          パラメータ名と値を示す文字列を連結する際の区切り文字。
     * @return フォームパラメータ文字列。パラメータなしの場合はnull。
     */
    public static String getFormParamText(HttpServletRequest httpServletRequest, String format, String delimiter) {
        
        return getFormParamText(httpServletRequest, format, delimiter, null, null);
    }
    
    /**
     * フォームパラメータを連結した文字列として取得する。<br>
     * 主にログ出力向けの機能であるため、業務ロジックとしては使用しないことを推奨する。
     *
     * @param httpServletRequest HTTPサーブレットリクエスト。
     * @param format             パラメータ名と値の２つのプレースホルダーを設定した文字列化フォーマット。
     * @param delimiter          パラメータ名と値を示す文字列を連結する際の区切り文字。
     * @param maskingParamNmList マスキング対象パラメータ名リスト。完全一致したものがマスキング代替文字列に置き換わる。
     * @param maskingText        マスキング代替文字列。
     * @return フォームパラメータ文字列。パラメータなしの場合はnull。
     */
    public static String getFormParamText(HttpServletRequest httpServletRequest, String format, String delimiter,
            List<String> maskingParamNmList, String maskingText) {
        
        String formParamText = null;
        
        // パラメータキーと値のログを構築
        StringBuilder strBuf = new StringBuilder();
        for (String name : httpServletRequest.getParameterMap().keySet()) {
            if (!maskingParamNmList.contains(name)) {
                strBuf.append(MessageFormat.format(format, name, httpServletRequest.getParameter(name)));
            } else {
                // マスキング対象の場合は代替文字列を採用
                strBuf.append(MessageFormat.format(format, name, maskingText));
            }
        }
        
        // 末尾の不要なデリミターを削除
        if (strBuf.length() != 0) {
            strBuf.delete(strBuf.length() - delimiter.length(), strBuf.length());
            
            formParamText = strBuf.toString();
        }
        
        return formParamText;
    }
    
    /**
     * ボディパラメータを文字列として取得する。<br>
     * 主にログ出力向けの機能であるため、業務ロジックとしては使用しないことを推奨する。
     *
     * @param httpServletRequest HTTPサーブレットリクエスト。
     * @return ボディパラメータ文字列。ボディが空の場合はnull。
     */
    public static String getBodyParamText(HttpServletRequest httpServletRequest) {
        
        return getBodyParamText(httpServletRequest, null, null);
    }
    
    /**
     * ボディパラメータを文字列として取得する。<br>
     * 主にログ出力向けの機能であるため、業務ロジックとしては使用しないことを推奨する。
     *
     * @param httpServletRequest HTTPサーブレットリクエスト。
     * @param maskingParamNmList マスキング対象パラメータ名リスト。完全一致したものがマスキング代替文字列に置き換わる。
     * @param maskingText        マスキング代替文字列。
     * @return ボディパラメータ文字列。ボディが空の場合はnull。
     */
    public static String getBodyParamText(HttpServletRequest httpServletRequest, List<String> maskingParamNmList,
            String maskingText) {
        
        String bodyParamText = null;
        
        try {
            // リクエストボディをバイト配列で取得
            byte[] bodyBytes = null;
            if (httpServletRequest instanceof ContentCachingRequestWrapper) {
                bodyBytes = ((ContentCachingRequestWrapper) httpServletRequest).getContentAsByteArray();
            } else if (httpServletRequest instanceof RepeatableReadHttpServletRequestWrapper) {
                bodyBytes = ((RepeatableReadHttpServletRequestWrapper) httpServletRequest).getInputStream()
                        .readAllBytes();
            } else {
                // デバッグログを出力
                log.debug(LOG_FORMAT_UNSUPPORTED_REQUEST);
            }
            
            if (!ArrayUtils.isEmpty(bodyBytes)) {
                try {
                    // 値タイプオブジェクトを構築
                    TypeReference<Map<String, Object>> valueTypeRef = new TypeReference<Map<String, Object>>() {
                    };
                    
                    // ボディが存在する場合、文字コード固定で文字列化してマップ形式に変換
                    Map<String, Object> jsonParamMap = new ObjectMapper()
                            .readValue(new String(bodyBytes, StandardCharsets.UTF_8), valueTypeRef);
                    
                    // JSONパラメータマップをマスキング
                    if (maskingParamNmList != null) {
                        jsonParamMap = getMaskedJsonParamMap(jsonParamMap, maskingParamNmList, maskingText);
                    }
                    
                    // マスキング済みのJSONパラメータマップを文字列化
                    bodyParamText = new ObjectMapper().writeValueAsString(jsonParamMap);
                } catch (JsonProcessingException e) {
                    // デバッグログを出力
                    log.debug(e.getMessage(), e);
                    
                    // JSONとしてパースできない場合はJSON形式以外とみなし、リクエストのエンコーディングを採用してそのままセット
                    bodyParamText = new String(bodyBytes, httpServletRequest.getCharacterEncoding());
                }
            }
        } catch (IOException e) {
            // 通常運用では発生しえないため無視して便宜上nullをセット
            log.debug(e.getMessage(), e);
            
            bodyParamText = null;
        }
        
        return bodyParamText;
    }
    
    /**
     * マスキング済みJSONパラメータマップを取得する。<br>
     *
     * @param jsonParamMap       JSONパラメータ名とその値を示すマップ。
     * @param maskingParamNmList マスキング対象パラメータ名リスト。完全一致したものがマスキング代替文字列に置き換わる。
     * @param maskingText        マスキング代替文字列。
     * @return マスキング済みJSONパラメータ文字列。
     */
    @SuppressWarnings("unchecked")
    private static LinkedHashMap<String, Object> getMaskedJsonParamMap(Map<String, Object> jsonParamMap,
            List<String> maskingParamNmList, String maskingText) {
        
        LinkedHashMap<String, Object> maskedJsonParamMap = new LinkedHashMap<String, Object>();
        
        for (String name : jsonParamMap.keySet()) {
            Object paramObject = jsonParamMap.get(name);
            
            // Mapの場合は項目名を再検査するためメソッド再帰呼び出し
            if (paramObject instanceof Map) {
                maskedJsonParamMap.put(name, 
                        getMaskedJsonParamMap((Map<String, Object>) paramObject, maskingParamNmList, maskingText));
            } else if (paramObject instanceof ArrayList) {
                Iterator <Object> iterator = ((ArrayList)paramObject).iterator();

                if (!iterator.hasNext()) {
                    maskedJsonParamMap.put(name, paramObject);
                    continue;
                }

                List<Object> maskedList = new ArrayList<>();
 
                while (iterator.hasNext()) {
                    Object object = iterator.next();
 
                    if (object instanceof Map) {
                        Map<String, Object> masked = getMaskedJsonParamMap((Map<String, Object>) object, maskingParamNmList, maskingText);
                        maskedList.add(masked);
                    } else {
                        if (!maskingParamNmList.contains(name)) {
                            maskedJsonParamMap.put(name, paramObject);
                        } else {
                            maskedJsonParamMap.put(name, maskingText);
                        }
                        break;
                    }
                }
 
                if (!maskedList.isEmpty()) {
                    maskedJsonParamMap.put(name, maskedList);
                }
            } else {
                // パラメータキーと値のログを構築
                if (!maskingParamNmList.contains(name)) {
                    maskedJsonParamMap.put(name, paramObject);
                } else {
                    // マスキング対象の場合は代替文字列を採用
                    maskedJsonParamMap.put(name, maskingText);
                }
            }
        }
        
        return maskedJsonParamMap;
    }
}
