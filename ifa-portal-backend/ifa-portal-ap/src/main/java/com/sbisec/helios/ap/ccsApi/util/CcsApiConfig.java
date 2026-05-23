package com.sbisec.helios.ap.ccsApi.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sbisec.helios.ap.ccsApi.exception.CcsApiException;

/**
 * CCS API の設定情報を読み込むユーティリティクラス
 */
@Component
public class CcsApiConfig {

    /** ロガーインスタンス */
    private static final Logger LOG = LoggerFactory.getLogger(CcsApiConfig.class);

    /** プロパティファイルのパス "fasthelp-config.properties" を指定 */
    private final String FILE_PATH = "fasthelp-config.properties";

    /** クラス全体で使用するプロパティ情報 */
    private static Properties g_properties;

    /** ホストプロトコル（例：http, https） */
    private static final String PRO_HOST_PROTOCOL = "host.protocol";

    /** CCs サーバーの IP アドレス */
    private static final String PRO_HOST_CCS_IP = "host.ccs.ip";

    /** ポート番号 */
    private static final String PRO_HOST_PORT = "host.port";

    /** HTTP クライアントの接続タイムアウト（ミリ秒） */
    private static final String PRO_HTTP_CLIENT_CONNECTTIMEOUT = "http.client.connectTimeout";

    /** HTTP クライアントの読み取りタイムアウト（ミリ秒） */
    private static final String PRO_HTTP_CLIENT_READTIMEOUT = "http.client.readTimeout";

    /** HTTP クライアントの書き込みタイムアウト（ミリ秒） */
    private static final String PRO_HTTP_CLIENT_WRITETIMEOUT = "http.client.writeTimeout";

    /** HTTP クライアントのコネクションプールタイムアウト（ミリ秒） */
    private static final String PRO_HTTP_CLIENT_CONNPOOLTIMEOUT = "http.client.connPoolTimeout";

    /** HTTP クライアントのコネクションプールキャッシュ最大数 */
    private static final String PRO_HTTP_CLIENT_CONNPOOLCACHEDMAX = "http.client.connPoolCachedMax";

    /** HTTP クライアントのリトライ回数 */
    private static final String PRO_HTTP_CLIENT_RETRYCOUNT = "http.client.retryCount";

    /**
     * コンストラクタ
     * 
     * @throws FastCcsException プロパティの初期化に失敗した場合
     */
    public CcsApiConfig() throws CcsApiException {
        init();
    }

    /**
     * ホストプロトコルを取得する。
     * 
     * @return プロトコル文字列（例：http, https）
     */
    public static String getHostProtocol() {
        return g_properties.getProperty(PRO_HOST_PROTOCOL);
    }

    /**
     * CCs サーバーの IP アドレスを取得する。
     * 
     * @return IP アドレス文字列
     */
    public static String getHostCcsIp() {
        return g_properties.getProperty(PRO_HOST_CCS_IP);
    }

    /**
     * ポート番号を取得する。
     * 
     * @return ポート番号文字列
     */
    public static String getHostPort() {
        return g_properties.getProperty(PRO_HOST_PORT);
    }

    /**
     * HTTP クライアントの接続タイムアウトを取得する。
     * 
     * @return 接続タイムアウト（ミリ秒）
     */
    public static Long getHttpClientConnectTimeout() {
        return Long.valueOf(g_properties.getProperty(PRO_HTTP_CLIENT_CONNECTTIMEOUT));
    }

    /**
     * HTTP クライアントの読み取りタイムアウトを取得する。
     * 
     * @return 読み取りタイムアウト（ミリ秒）
     */
    public static Long getHttpClientReadTimeout() {
        return Long.valueOf(g_properties.getProperty(PRO_HTTP_CLIENT_READTIMEOUT));
    }

    /**
     * HTTP クライアントの書き込みタイムアウトを取得する。
     * 
     * @return 書き込みタイムアウト（ミリ秒）
     */
    public static Long getHttpClientWriteTimeout() {
        return Long.valueOf(g_properties.getProperty(PRO_HTTP_CLIENT_WRITETIMEOUT));
    }

    /**
     * HTTP クライアントのコネクションプールタイムアウトを取得する。
     * 
     * @return コネクションプールタイムアウト（ミリ秒）
     */
    public static Integer getHttpClientConnPoolTimeout() {
        return Integer.valueOf(g_properties.getProperty(PRO_HTTP_CLIENT_CONNPOOLTIMEOUT));
    }

    /**
     * HTTP クライアントのコネクションプールキャッシュ最大数を取得する。
     * 
     * @return コネクションプールキャッシュ最大数
     */
    public static Integer getHttpClientConnPoolCachedMax() {
        return Integer.valueOf(g_properties.getProperty(PRO_HTTP_CLIENT_CONNPOOLCACHEDMAX));
    }

    /**
     * HTTP クライアントのリトライ回数を取得する。
     * 
     * @return リトライ回数
     */
    public static Integer getHttpClientRetryCount() {
        return Integer.valueOf(g_properties.getProperty(PRO_HTTP_CLIENT_RETRYCOUNT));
    }

    /**
     * プロパティ情報を初期化する。
     * 
     * @throws CcsApiException プロパティ読み込みに失敗した場合
     */
    private void init() throws CcsApiException {
        g_properties = loadProperties();
    }

    /**
     * プロパティファイルを読み込み、Properties オブジェクトを返す。
     * 
     * @return Properties オブジェクト
     * @throws CcsApiException プロパティ読み込みに失敗した場合
     */
    private Properties loadProperties() throws CcsApiException {
        Properties p_pro = new Properties();
        InputStream p_ins = null;
        try {
            // プロパティファイルをクラスパスから読み込む
            p_ins = new BufferedInputStream(CcsApiConfig.class.getClassLoader().getResourceAsStream(FILE_PATH));
            p_pro.load(p_ins);
        } catch (Exception e) {
            LOG.error("Fasthelp Exception occured.", e);
            p_pro = null;
        } finally {
            if (null != p_ins) {
                try {
                    p_ins.close();
                } catch (IOException ie) {
                    LOG.error("Fasthelp Exception occured.", ie);
                    throw new CcsApiException(ie);
                }
            }
        }
        return p_pro;
    }
}