package com.sbisec.helios.ap.broker.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Description: Papy api properties
 * @author katsuhiko.kagoshima
 * @date: 12/23/2022
 */
@Component
public class PapyConfig {
    
    public PapyConfig() throws PapyConnectionException {
        
        init();
    }
    
    private final String FILE_PATH = "papy-config.properties";
    
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
    
    private static final String PRO_KEYSTORE_PATH = "keystore.filepath";
    
    private static final String PRO_KEYSTORE_PASSWORD = "keystore.password";
    
    private static final Logger LOG = LoggerFactory.getLogger(PapyConfig.class);
    
    public static String getHostProtocol() {
        
        return properties.getProperty(PRO_HOST_PROTOCOL);
    }
    
    public static String getHostIp() {
        
        return properties.getProperty(PRO_HOST_IP);
    }
    
    public static String getHostPort() {
        
        return properties.getProperty(PRO_HOST_PORT);
    }
    
    public static Long getHttpClientConnectTimeout() {
        
        return Long.valueOf(properties.getProperty(PRO_HTTP_CLIENT_CONNECTTIMEOUT));
    }
    
    public static Long getHttpClientReadTimeout() {
        
        return Long.valueOf(properties.getProperty(PRO_HTTP_CLIENT_READTIMEOUT));
    }
    
    public static Long getHttpClientWriteTimeout() {
        
        return Long.valueOf(properties.getProperty(PRO_HTTP_CLIENT_WRITETIMEOUT));
    }
    
    public static Integer getHttpClientConnPoolTimeout() {
        
        return Integer.valueOf(properties.getProperty(PRO_HTTP_CLIENT_CONNPOOLTIMEOUT));
    }
    
    public static Integer getHttpClientConnPoolCachedMax() {
        
        return Integer.valueOf(properties.getProperty(PRO_HTTP_CLIENT_CONNPOOLCACHEDMAX));
    }
    
    public static Integer getHttpClientRetryCount() {
        
        return Integer.valueOf(properties.getProperty(PRO_HTTP_CLIENT_RETRYCOUNT));
    }
    
    public static String getKeystorePath() {
        
        return properties.getProperty(PRO_KEYSTORE_PATH);
    }
    
    public static String getKeystorePassword() {
        
        return properties.getProperty(PRO_KEYSTORE_PASSWORD);
    }
    
    private void init() throws PapyConnectionException {
        
        properties = loadProperties();
    }
    
    private Properties loadProperties() throws PapyConnectionException {
        
        Properties pro = new Properties();
        
        InputStream in = null;
        try {
            in = new BufferedInputStream(PapyConfig.class.getClassLoader().getResourceAsStream(FILE_PATH));
            pro.load(in);
        } catch (Exception e) {
            LOG.error("Papy Exception occured.:", e);
            pro = null;
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException ie) {
                    LOG.error("Papy IOException occured.");
                    throw new PapyConnectionException(ie);
                }
            }
        }
        
        return pro;
    }
}
