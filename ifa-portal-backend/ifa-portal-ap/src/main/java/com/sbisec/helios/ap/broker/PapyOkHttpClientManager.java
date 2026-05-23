package com.sbisec.helios.ap.broker;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import com.sbisec.helios.ap.broker.intercepter.PapyNetworkIntercepter;
import com.sbisec.helios.ap.broker.intercepter.PapyRetryInterceptor;
import com.sbisec.helios.ap.broker.util.PapyConfig;
import com.sbisec.helios.ap.broker.util.PapyConnectionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

/**
 * <p>
 * Description:OkHttpClientManager
 * </p>
 *
 * @author katsuhiko.kagoshima
 * @date 12/23/2022
 */
public class PapyOkHttpClientManager implements Cloneable {
    
    private static class OkHttpClientInternal {
        
        private static final PapyOkHttpClientManager INSTANCE = new PapyOkHttpClientManager();
    }
    
    private static final Logger LOG = LoggerFactory.getLogger(PapyOkHttpClientManager.class);
    
    private static volatile boolean isCreate = false;
    
    private OkHttpClient client;
    
    private X509TrustManager trustManager;
    
    private SSLSocketFactory sslSocketFactory;
    
    private long connTimeout;
    
    private long readTimeout;
    
    private long writeTimeout;
    
    private int connPoolTimeout;
    
    private int connPoolCachedMax;
    
    private int retryCount;
    
    private PapyOkHttpClientManager() {
        
        if (isCreate)
            throw new RuntimeException("Cannot instantiate repeatedly!");
        isCreate = true;
        
        this.connTimeout = 3L;
        this.readTimeout = 10L;
        this.writeTimeout = 5L;
        this.connPoolTimeout = 10;
        this.connPoolCachedMax = 5;
        this.retryCount = 1;
    }
    
    private synchronized void create() throws PapyConnectionException {
        
        try {
            ConnectionPool pool = new ConnectionPool(this.connPoolTimeout, this.connPoolCachedMax, TimeUnit.MINUTES);
            // サーバー証明書の認証用トラストストアの取得
            trustManager = trustManagerForCertificates();
            //　通信形式を指定
            SSLContext sslContext = SSLContext.getInstance("TLS");
            // クライアント側の認証は行わない想定のため、第一引数はnull, 第二引数はトラストストアを設定
            sslContext.init(null, new TrustManager[] { trustManager }, new java.security.SecureRandom());
            sslSocketFactory = sslContext.getSocketFactory();
            
            // クライアントの生成
            this.client = new OkHttpClient().newBuilder().sslSocketFactory(sslSocketFactory, trustManager)
                    .connectTimeout(this.connTimeout, TimeUnit.SECONDS).readTimeout(this.readTimeout, TimeUnit.SECONDS)
                    .writeTimeout(this.writeTimeout, TimeUnit.SECONDS).retryOnConnectionFailure(false)
                    .addInterceptor(new PapyRetryInterceptor(this.retryCount))
                    .addNetworkInterceptor(new PapyNetworkIntercepter()).connectionPool(pool).build();
        } catch (Exception e) {
            LOG.info("Papy Exception occured.: ", e);
            throw new PapyConnectionException();
        }
    }
    
    public PapyOkHttpClientManager setReadTimeout(long readTimeout) {
        
        this.readTimeout = readTimeout;
        return this;
    }
    
    public PapyOkHttpClientManager setWriteTimeout(long writeTimeout) {
        
        this.writeTimeout = writeTimeout;
        return this;
    }
    
    public PapyOkHttpClientManager setConnTimeout(long connTimeout) {
        
        this.connTimeout = connTimeout;
        return this;
    }
    
    public PapyOkHttpClientManager setConnPoolTimeout(int connPoolTimeout) {
        
        this.connPoolTimeout = connPoolTimeout;
        return this;
    }
    
    public PapyOkHttpClientManager setConnPoolCachedMax(int connPoolCachedMax) {
        
        this.connPoolCachedMax = connPoolCachedMax;
        return this;
    }
    
    public PapyOkHttpClientManager setRetryCount(int retryCount) {
        
        this.retryCount = retryCount;
        return this;
    }
    
    public OkHttpClient getClient() throws PapyConnectionException {
        
        if (null == this.client) {
            synchronized (this) {
                if (null == this.client) {
                    this.create();
                }
            }
        }
        return this.client;
    }
    
    protected static final PapyOkHttpClientManager getInstance() {
        
        return OkHttpClientInternal.INSTANCE;
    }
    
    /** 
     * サーバーの認証情報をファイルから取得し、キーストアを生成する。
     * 生成したキーストアを用いて、トラストストアを取得する。
     * @return trustManager
     * @throws GeneralSecurityException
     * @throws IOException
     */
    private X509TrustManager trustManagerForCertificates() throws GeneralSecurityException, IOException {
        
        // トラストストアの生成に用いるキーストア、キーマネージャファクトリーのロードを行う。
        char[] password = PapyConfig.getKeystorePassword().toCharArray();
        KeyStore keyStore = loadKeyStore(password);
        
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, password);
        
        // トラストストアのロードを行う。
        TrustManagerFactory trustManagerFactory = TrustManagerFactory
                .getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
            throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
        }
        return (X509TrustManager) trustManagers[0];
    }
    
    /**
     * サーバー証明書の認証情報を格納したキーストアのロードを行う。
     * @param password　キーストアのパスワード
     * @return　keyStore　キーストア
     * @throws GeneralSecurityException
     */
    private static KeyStore loadKeyStore(char[] password) throws GeneralSecurityException, IOException {
        
        //　ファイル(cacert)からサーバー認証用の情報を取得する。
        InputStream in = null;
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            in = new FileInputStream(PapyConfig.getKeystorePath());
            // 取得情報とパスワードをもとに、キーストアのロードを行う。
            keyStore.load(in, password);
            return keyStore;
        } catch (IOException e) {
            throw new IOException(e);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }
    
    /**
     * Creates and returns a copy of this object. The precise meaning of "copy" may
     * depend on the class of the object. The general intent is that, for any object
     * {@code x}, the expression: <blockquote>
     * 
     * <pre>
     * x.clone() != x
     * </pre>
     * 
     * </blockquote> will be true, and that the expression: <blockquote>
     * 
     * <pre>
     * x.clone().getClass() == x.getClass()
     * </pre>
     * 
     * </blockquote> will be {@code true}, but these are not absolute requirements.
     * While it is typically the case that: <blockquote>
     * 
     * <pre>
     * x.clone().equals(x)
     * </pre>
     * 
     * </blockquote> will be {@code true}, this is not an absolute requirement.
     * <p>
     * By convention, the returned object should be obtained by calling
     * {@code super.clone}. If a class and all of its superclasses (except
     * {@code Object}) obey this convention, it will be the case that
     * {@code x.clone().getClass() == x.getClass()}.
     * <p>
     * By convention, the object returned by this method should be independent of
     * this object (which is being cloned). To achieve this independence, it may be
     * necessary to modify one or more fields of the object returned by
     * {@code super.clone} before returning it. Typically, this means copying any
     * mutable objects that comprise the internal "deep structure" of the object
     * being cloned and replacing the references to these objects with references to
     * the copies. If a class contains only primitive fields or references to
     * immutable objects, then it is usually the case that no fields in the object
     * returned by {@code super.clone} need to be modified.
     * <p>
     * The method {@code clone} for class {@code Object} performs a specific cloning
     * operation. First, if the class of this object does not implement the
     * interface {@code Cloneable}, then a {@code CloneNotSupportedException} is
     * thrown. Note that all arrays are considered to implement the interface
     * {@code Cloneable} and that the return type of the {@code clone} method of an
     * array type {@code T[]} is {@code T[]} where T is any reference or primitive
     * type. Otherwise, this method creates a new instance of the class of this
     * object and initializes all its fields with exactly the contents of the
     * corresponding fields of this object, as if by assignment; the contents of the
     * fields are not themselves cloned. Thus, this method performs a "shallow copy"
     * of this object, not a "deep copy" operation.
     * <p>
     * The class {@code Object} does not itself implement the interface
     * {@code Cloneable}, so calling the {@code clone} method on an object whose
     * class is {@code Object} will result in throwing an exception at run time.
     *
     * @return a clone of this instance.
     * @throws CloneNotSupportedException if the object's class does not support the
     * {@code Cloneable} interface. Subclasses that override the {@code clone}
     * method can also throw this exception to indicate that an instance cannot be
     * cloned.
     * @see Cloneable
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        
        return OkHttpClientInternal.INSTANCE;
    }
}
