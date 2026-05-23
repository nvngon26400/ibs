package com.sbisec.helios.ap.ccsApi.interceptor;

import java.io.IOException;
import java.net.SocketTimeoutException;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * ソケット接続がタイムアウトした場合にリトライ処理を行うインターセプタ
 */
public class CcsApiRetryInterceptor implements Interceptor {

    /** ロガーインスタンス */
    private static final Logger LOG = LoggerFactory.getLogger(CcsApiRetryInterceptor.class);

    /** リトライ回数 */
    private int g_retryCount;

    /**
     * コンストラクタ
     * @param retryCount リトライ回数を指定
     */
    public CcsApiRetryInterceptor(int retryCount) {
        this.g_retryCount = retryCount;
    }

    /**
     * リトライ処理を実行するメソッド
     * @param x_chain Chainオブジェクト
     * @return Response サーバーからの応答
     * @throws IOException 入出力エラーが発生した場合
     */
    @NotNull
    @Override
    public Response intercept(@NotNull Chain x_chain) throws IOException {
        return this.retry(x_chain);
    }

    /**
     * リトライ処理を実行するメソッド
     * @param x_chain Chainオブジェクト
     * @return Response サーバーからの応答
     * @throws IOException 入出力エラーが発生した場合
     */
    public Response retry(Chain x_chain) throws IOException {
        Request p_req = x_chain.request();
        Response p_res = null;
        try {
            p_res = x_chain.proceed(p_req);
        } catch (SocketTimeoutException p_ste) {
            int p_retryCount = 0;

            while (null == p_res && this.g_retryCount > p_retryCount) {
                LOG.info("The request failed and will be retried soon. Request:" + p_req.toString());
                p_retryCount++;
                try {
                    p_res = x_chain.proceed(p_req);
                } catch (SocketTimeoutException ste_) {
                    continue;
                }
                LOG.info("Retrying request completed: " + p_retryCount);
            }
            if (null == p_res)
                throw p_ste;
        }
        return p_res;
    }
}