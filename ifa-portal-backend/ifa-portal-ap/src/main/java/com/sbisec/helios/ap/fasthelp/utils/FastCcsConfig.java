package com.sbisec.helios.ap.fasthelp.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sbisec.helios.ap.athena.utils.AthenaException;
import com.sbisec.helios.ap.fasthelp.exception.FastCcsException;

/**
 * @Description: Fasthelp api properties
 * @author dalian
 * @date: 04/29/2025
 */
@Component
public class FastCcsConfig {

    public FastCcsConfig() throws FastCcsException {
        init();
    }

    private final String FILE_PATH = "fasthelp-config.properties";

    private static Properties properties;
    private static final String PRO_HOST_PROTOCOL = "host.protocol";
    private static final String PRO_HOST_CCS_IP = "host.ccs.ip";
    private static final String PRO_HOST_FASTHELP_IP = "host.fasthelp.ip";
    private static final String PRO_HOST_PORT = "host.port";
    private static final String PRO_HTTP_CLIENT_CONNECTTIMEOUT = "http.client.connectTimeout";
    private static final String PRO_HTTP_CLIENT_READTIMEOUT = "http.client.readTimeout";
    private static final String PRO_HTTP_CLIENT_WRITETIMEOUT = "http.client.writeTimeout";
    private static final String PRO_HTTP_CLIENT_CONNPOOLTIMEOUT = "http.client.connPoolTimeout";
    private static final String PRO_HTTP_CLIENT_CONNPOOLCACHEDMAX = "http.client.connPoolCachedMax";
    private static final String PRO_HTTP_CLIENT_RETRYCOUNT = "http.client.retryCount";

    private static final Logger LOG = LoggerFactory.getLogger(FastCcsConfig.class);

    /**
     * 
     * @return
     */
    public static String getHostProtocol() {
        return properties.getProperty(PRO_HOST_PROTOCOL);
    }

    /**
     * 
     * @return
     */
    public static String getHostCcsIp() {
        return properties.getProperty(PRO_HOST_CCS_IP);
    }
   /**
     * 
     * @return
     */
    public static String getHostFasthelpIp() {
        return properties.getProperty(PRO_HOST_FASTHELP_IP);
    }

    /**
     * 
     * @return
     */
    public static String getHostPort() {
        return properties.getProperty(PRO_HOST_PORT);
    }

    /**
     * 
     * @return
     */
    public static Long getHttpClientConnectTimeout() {
        return Long.valueOf(properties.getProperty(PRO_HTTP_CLIENT_CONNECTTIMEOUT));
    }

    /**
     * 
     * @return
     */
    public static Long getHttpClientReadTimeout() {
        return Long.valueOf(properties.getProperty(PRO_HTTP_CLIENT_READTIMEOUT));
    }

    /**
     * 
     * @return
     */
    public static Long getHttpClientWriteTimeout() {
        return Long.valueOf(properties.getProperty(PRO_HTTP_CLIENT_WRITETIMEOUT));
    }

    /**
     * 
     * @return
     */
    public static Integer getHttpClientConnPoolTimeout() {
        return Integer.valueOf(properties.getProperty(PRO_HTTP_CLIENT_CONNPOOLTIMEOUT));
    }

    /**
     * 
     * @return
     */
    public static Integer getHttpClientConnPoolCachedMax() {
        return Integer.valueOf(properties.getProperty(PRO_HTTP_CLIENT_CONNPOOLCACHEDMAX));
    }

    /**
     * 
     * @return
     */
    public static Integer getHttpClientRetryCount() {
        return Integer.valueOf(properties.getProperty(PRO_HTTP_CLIENT_RETRYCOUNT));
    }

    /**
     * 
     * @throws Exception
     */
    private void init() throws FastCcsException {
        properties = loadProperties();
    }

    /**
     * 
     * @return
     * @throws AthenaException
     */
    private Properties loadProperties() throws FastCcsException {

        Properties pro = new Properties();

        InputStream in = null;
        try {
            in = new BufferedInputStream(FastCcsConfig.class.getClassLoader().getResourceAsStream(FILE_PATH));
            pro.load(in);
        } catch (Exception e) {
            LOG.error("Fasthelp Exception occured.", e);
            pro = null;
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException ie) {
                    LOG.error("Fasthelp Exception occured.", ie);
                    throw new FastCcsException(ie);
                }
            }
        }

        return pro;
    }
}