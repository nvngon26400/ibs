package com.sbisec.helios.ap.ccsApi.service;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.ccsApi.CcsApiOkHttpClientWrapper;
import com.sbisec.helios.ap.ccsApi.CcsApiOkHttpRequest;
import com.sbisec.helios.ap.ccsApi.CcsApiOkHttpResponse;
import com.sbisec.helios.ap.ccsApi.exception.CcsApiException;
import com.sbisec.helios.ap.ccsApi.protocol.CcsApiBaseReq;
import com.sbisec.helios.ap.ccsApi.util.CcsApiConfig;
import com.sbisec.helios.ap.ccsApi.util.CcsApiUtil;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * CCS API に共通の処理を提供するベースクラス。
 * リクエスト送信、レスポンスチェック、パラメータ検証などの基本機能を含む。
 */
public abstract class CcsApiBaseService {

    /** ロガー */
    private static final Logger LOG = LoggerFactory.getLogger(CcsApiBaseService.class);

    /**
     * CCS API の URL を構築する。
     * 
     * @param x_api API エンドポイント
     * @return 完成した URL
     */
    protected final String getCcsUrl(String x_api) {
        return CcsApiConfig.getHostProtocol() + "://" + CcsApiConfig.getHostCcsIp() + ":" + CcsApiConfig.getHostPort() + x_api;
    }

    /**
     * パラメータの有効性を確認する。
     * 
     * @param x_arg 確認対象のパラメータ
     * @throws CcsApiException パラメータが不正な場合
     */
    protected final void checkParameter(Object x_arg) throws CcsApiException {
        // パラメータチェック
        String p_warnMsg = StringUtil.EMPTY_STRING;
        do {
            if (x_arg == null) {
                p_warnMsg = "apiIn is not exists or empty!";
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(p_warnMsg)) {
            LOG.error("CCS Api Exception ： " + p_warnMsg);
            throw new CcsApiException("Parameter verification failed!");
        }
    }

    /**
     * POST リクエストを送信し、レスポンスを取得する。
     * 
     * @param x_url リクエスト先のURL
     * @param x_req リクエストデータ
     * @return 取得したレスポンスオブジェクト
     * @throws Exception リクエスト中にエラーが発生した場合
     */
    protected final CcsApiOkHttpResponse post(String x_url, CcsApiBaseReq x_req) throws Exception {
        CcsApiOkHttpResponse p_res = null;
        CcsApiOkHttpRequest p_req = new CcsApiOkHttpRequest();
        p_req.setUrl(x_url);
        // Set request body
        RequestBody requestBody = toRequestBody(x_req.getParameter());
        // Execute post request with RequestBody
        p_res = CcsApiOkHttpClientWrapper.getInstance().post(p_req, requestBody);
        // Check Response
        checkResponseException(p_res);
        return p_res;
    }

    /**
     * レスポンスの異常をチェックし、エラーがあれば例外を投げる。
     * 
     * @param res チェック対象のレスポンス
     * @throws Exception レスポンスが異常な場合
     */
    protected void checkResponseException(CcsApiOkHttpResponse res) throws Exception {
        if (null == res) {
            LOG.warn("CCS Api Response is null!");
            throw new CcsApiException("Response is null!");
        }
        if (!res.getSuccess()) {
            if (res.getStsCode() >= 500 && res.getStsCode() < 600) {
                LOG.error("CCS Api Exception request failed:{}", res.getResData());
            } else {
                LOG.info("CCS Api request failed:{}", res.getResData());
            }
            // システム異常を投げる
            throw new CcsApiException("CCS Api request failed!");
        }
    }

    /**
     * パラメータをリクエストボディに変換する。
     * <p>
     * パラメータは JSON 形式で渡され、それを Map に変換し、URL エンコードして
     * application/x-www-form-urlencoded 形式のリクエストボディとして返す。
     * </p>
     * 
     * @param parameters 変換対象のパラメータ（通常は CcsApiBaseReq のパラメータ）
     * @return リクエストボディ（application/x-www-form-urlencoded 形式）
     * @throws CcsApiException パラメータの変換中にエラーが発生した場合
     */
    private RequestBody toRequestBody(Object parameters) throws CcsApiException {
        try {
            // フォームデータを構築するための文字列ビルダー
            StringBuilder formDataBuilder = new StringBuilder();
            // Jackson ObjectMapper を使用してパラメータを Map に変換
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> parameterMap = objectMapper.readValue(
                    objectMapper.writeValueAsString(parameters), 
                    new TypeReference<Map<String, Object>>() {});
            // パラメータが null でない場合のみ処理
            if (parameterMap != null) {
                // マップ内の各キーと値をループ
                for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
                    // 値が null でない場合のみ処理
                    if (entry.getValue() != null) {
                        // キーと値を Shift_JIS でエンコード
                        String encodedKey = URLEncoder.encode(entry.getKey(), CcsApiUtil.CHARSET_SJIS);
                        String encodedValue = URLEncoder.encode(entry.getValue().toString(), CcsApiUtil.CHARSET_SJIS);
                        // フォームデータに追加（例: key1=value1&key2=value2）
                        formDataBuilder.append(encodedKey)
                                       .append("=")
                                       .append(encodedValue)
                                       .append("&");
                    }
                }
                // 最後の & を削除（末尾の不要な文字を除去）
                if (formDataBuilder.length() > 0) {
                    formDataBuilder.deleteCharAt(formDataBuilder.length() - 1);
                }
            }
            // バイト配列に変換し、リクエストボディを作成
            return RequestBody.create(
                    formDataBuilder.toString().getBytes(Charset.forName(CcsApiUtil.CHARSET_SJIS)),
                    MediaType.parse("application/x-www-form-urlencoded;charset="+CcsApiUtil.CHARSET_SJIS)
            );
        } catch (Exception e) {
            // エラーが発生した場合は CcsApiException を投げる
            throw new CcsApiException(e);
        }
    }
}