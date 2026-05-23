package com.sbisec.helios.ap.athena.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Description: Athena api properties
 * @author shuchen.xin
 * @date: 06/02/2021
 */
@Component
public class AthenaConfig {

	public AthenaConfig() throws AthenaException {
		init();
	}

	private final String FILE_PATH = "athena-config.properties";

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
	private static final String DUMMY_TEST_ATHENA_ENV = "dummy.test.athena-env";

	private static final Logger LOG = LoggerFactory.getLogger(AthenaConfig.class);

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
	public static String getHostIp() {
		return properties.getProperty(PRO_HOST_IP);
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
	 * @return
	 */
	public static String getCanary() {
		return properties.getProperty(DUMMY_TEST_CANARY);
	}

	/**
	 * 
	 * @return
	 */
	public static String getAthenaEnv() {
		return properties.getProperty(DUMMY_TEST_ATHENA_ENV);
	}

	/**
	 * 
	 * @throws Exception
	 */
	private void init() throws AthenaException {
		properties = loadProperties();
	}

	/**
	 * 
	 * @return
	 * @throws AthenaException
	 */
	private Properties loadProperties() throws AthenaException {

		Properties pro = new Properties();

		InputStream in = null;
		try {
			in = new BufferedInputStream(AthenaConfig.class.getClassLoader().getResourceAsStream(FILE_PATH));
			pro.load(in);
		} catch (Exception e) {
			LOG.error("Comet Exception occured.", e);
			pro = null;
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException ie) {
					LOG.error("Comet Exception occured.", ie);
					throw new AthenaException(ie);
				}
			}
		}

		return pro;
	}
}