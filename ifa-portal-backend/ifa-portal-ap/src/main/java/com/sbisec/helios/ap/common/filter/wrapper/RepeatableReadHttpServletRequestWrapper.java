package com.sbisec.helios.ap.common.filter.wrapper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * ボディの再読み込みに対応したHTTPサーブレットリクエストラッパークラス。<br>
 * springであらかじめ用意されているContentCachingRequestWrapperを使用しない理由としては<br>
 * インスタンス生成時にキャッシュが作成される仕様ではなく読み込み済みの内容を再度取得できるだけであり
 * HttpServletRequestとしてボディの再利用が可能というわけではないためである。
 *
 * @author SCSK宮坂
 */
public class RepeatableReadHttpServletRequestWrapper extends HttpServletRequestWrapper {
    
    /** バイト配列サーブレット入力ストリームクラス */
    private static class ByteArrayServletInputStream extends ServletInputStream {
        
        /** ボディ保持用入力ストリーム */
        private ByteArrayInputStream byteArrayInputStream = null;
        
        /**
         * コンストラクタ。
         *
         * @param bodyBytes ボディバイト配列。
         */
        public ByteArrayServletInputStream(byte[] bodyBytes) {
            
            super();
            
            this.byteArrayInputStream = new ByteArrayInputStream(bodyBytes);
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isFinished() {
            
            return byteArrayInputStream.available() <= 0;
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isReady() {
            
            return true;
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public void setReadListener(ReadListener listener) {
            
            throw new UnsupportedOperationException();
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public int read() throws IOException {
            
            return byteArrayInputStream.read();
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            
            return byteArrayInputStream.read(b, off, len);
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public int read(byte[] b) throws IOException {
            
            return byteArrayInputStream.read(b);
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public long skip(long n) throws IOException {
            
            return byteArrayInputStream.skip(n);
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public int available() throws IOException {
            
            return byteArrayInputStream.available();
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public void close() throws IOException {
            
            byteArrayInputStream.close();
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public synchronized void mark(int readlimit) {
            
            byteArrayInputStream.mark(readlimit);
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public synchronized void reset() throws IOException {
            
            byteArrayInputStream.reset();
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public boolean markSupported() {
            
            return byteArrayInputStream.markSupported();
        }
    }
    
    /** ボディキャッシュ */
    private byte[] bodyCache = null;
    
    /**
     * コンストラクタ。
     *
     * @param httpServletRequest HTTPサーブレットリクエスト。
     * @throws IOException ボディキャッシュ生成に失敗した場合。
     */
    public RepeatableReadHttpServletRequestWrapper(HttpServletRequest httpServletRequest) throws IOException {
        
        super(httpServletRequest);
        
        if (httpServletRequest.getContentLength() > 0) {
            this.bodyCache = Arrays.copyOf(httpServletRequest.getInputStream().readAllBytes(),
                    httpServletRequest.getContentLength());
        } else {
            this.bodyCache = new byte[0];
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public BufferedReader getReader() throws IOException {
        
        // 文字コーディングを取得
        String characterEncoding = getCharacterEncoding();
        if (characterEncoding == null) {
            // 未設定の場合はUTF-8を採用する
            characterEncoding = StandardCharsets.UTF_8.name();
        }
        
        // 文字エンコーディングを指定してリーダーを生成
        return new BufferedReader(new InputStreamReader(getInputStream(), characterEncoding));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
        
        // ボディキャッシュから入力ストリームを生成
        return new ByteArrayServletInputStream(bodyCache);
    }
}
