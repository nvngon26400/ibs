package com.sbisec.helios.ap.pom.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Description: Pom api properties
 * @author shuchen.xin
 * @date: 06/02/2021
 */
@Component
public class PomConfig {
    
    public PomConfig() throws PomException {
        
        init();
    }
    
    private static final String FILE_PATH = "pom-config.properties";
    
    private static Properties properties;
    
    private static final String PRO_HOST_PROTOCOL = "host.protocol";
    
    private static final String PRO_HOST_IP = "host.ip";
    
    private static final String PRO_HOST_PORT = "host.port";
    
    private static final String PRO_HTTP_CLIENT_CONNECTTIMEOUT = "http.client.connectTimeout";
    
    private static final String PRO_HTTP_CLIENT_READTIMEOUT = "http.client.readTimeout";
    
    private static final String PRO_HTTP_CLIENT_WRITETIMEOUT = "http.client.writeTimeout";
    
    private static final String PRO_HTTP_CLIENT_CONNPOOLTIMEOUT = "http.client.connPoolTimeout";
    
    private static final String PRO_HTTP_CLIENT_CONNPOOLCACHEDMAX = "http.client.connPoolCachedMax";
    
    private static final String PRO_HTTP_CLIENT_RETRYCOUNT = "http.client.retryCount";
    
    private static final String DUMMY_TEST_CANARY = "dummy.test.canary";
    
    private static final String DUMMY_TEST_POM_ENV = "dummy.test.pom-env";
    
    private static final Logger LOG = LoggerFactory.getLogger(PomConfig.class);
    
    /**
     * ホストプロトコルを取得
     *
     * @return ホストプロトコル
     */
    public static String getHostProtocol() {
        
        return properties.getProperty(PRO_HOST_PROTOCOL);
    }
    
    /**
     * ホストIPを取得
     *
     * @return ホストIP
     */
    public static String getHostIp() {
        
        return properties.getProperty(PRO_HOST_IP);
    }
    
    /**
     * ホストポートを取得
     *
     * @return ホストポート
     */
    public static String getHostPort() {
        
        return properties.getProperty(PRO_HOST_PORT);
    }
    
    /**
     * 接続タイムアウトを取得
     *
     * @return 接続タイムアウト
     */
    public static Long getHttpClientConnectTimeout() {
        
        return Long.valueOf(properties.getProperty(PRO_HTTP_CLIENT_CONNECTTIMEOUT));
    }
    
    /**
     * 読み込みタイムアウトを取得
     *
     * @return 読み込みタイムアウト
     */
    public static Long getHttpClientReadTimeout() {
        
        return Long.valueOf(properties.getProperty(PRO_HTTP_CLIENT_READTIMEOUT));
    }
    
    /**
     * 書き出しタイムアウトを取得
     *
     * @return 書き出しタイムアウト
     */
    public static Long getHttpClientWriteTimeout() {
        
        return Long.valueOf(properties.getProperty(PRO_HTTP_CLIENT_WRITETIMEOUT));
    }
    
    /**
     * 接続プールタイムアウトを取得
     *
     * @return 接続プールタイムアウト
     */
    public static Integer getHttpClientConnPoolTimeout() {
        
        return Integer.valueOf(properties.getProperty(PRO_HTTP_CLIENT_CONNPOOLTIMEOUT));
    }
    
    /**
     * 最大接続キャッシュ数を取得
     *
     * @return 最大接続キャッシュ数
     */
    public static Integer getHttpClientConnPoolCachedMax() {
        
        return Integer.valueOf(properties.getProperty(PRO_HTTP_CLIENT_CONNPOOLCACHEDMAX));
    }
    
    /**
     * リトライ数を取得
     *
     * @return リトライ数
     */
    public static Integer getHttpClientRetryCount() {
        
        return Integer.valueOf(properties.getProperty(PRO_HTTP_CLIENT_RETRYCOUNT));
    }
    
    /**
     * Canaryを取得
     *
     * @return Canary
     */
    public static String getCanary() {
        
        return properties.getProperty(DUMMY_TEST_CANARY);
    }
    
    /**
     * POM環境を取得
     *
     * @return POM環境
     */
    public static String getPomEnv() {
        
        return properties.getProperty(DUMMY_TEST_POM_ENV);
    }
    
    private void init() throws PomException {
        
        properties = loadProperties();
    }
    
    private Properties loadProperties() throws PomException {
        
        Properties pro = new Properties();
        
        InputStream in = null;
        try {
            in = new BufferedInputStream(PomConfig.class.getClassLoader().getResourceAsStream(FILE_PATH));
            pro.load(in);
        } catch (Exception e) {
            LOG.error("Pom Exception occured.", e);
            pro = null;
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException ie) {
                    LOG.error("Pom Exception occured.", ie);
                    throw new PomException(ie);
                }
            }
        }
        
        return pro;
    }
}
