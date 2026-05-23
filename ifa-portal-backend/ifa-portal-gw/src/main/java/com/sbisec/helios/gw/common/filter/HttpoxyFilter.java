package com.sbisec.helios.gw.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.HttpRequestUtil;
import com.sbisec.helios.ap.common.util.ServletUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * httpoxy脆弱性フィルタークラス。<br>
 * ALBで対応済みの場合も考えられるが、念のため当フィルタを適用しておく。<br>
 * 脆弱性の具体的な内容については、https://httpoxy.orgを参照。
 *
 * @author SCSK宮坂
 */
@Slf4j
public class HttpoxyFilter implements Filter {
    
    /** 脆弱性の原因となるヘッダ名 */
    private static final String HEADER_NM = "proxy";
    
    /** ログフォーマット（該当ヘッダ検知） */
    private static final String LOG_FORMAT_HEADER_DETECTED = "Requested header IP:[{}] proxy:[{}].";
    
    /** ログフォーマット（想定外エラー） */
    private static final String LOG_FORMAT_UNEXPECTED_ERROR = "HttpoxyFilter unexpected error has occurred.";
    
    /** 遷移先パス */
    private static final String FORWARD_PATH = "/error?page=400";
    
    /**
     * コンストラクタ。
     */
    public HttpoxyFilter() {
    
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            
            try {
                // ヘッダ値を取得
                String headerValue = httpServletRequest.getHeader(HEADER_NM);
                
                // 該当ヘッダが存在したらエラー
                if (headerValue != null) {
                    // 警告ログを出力
                    log.warn(LOG_FORMAT_HEADER_DETECTED, HttpRequestUtil.getRemoteAddress(httpServletRequest),
                            headerValue);
                    
                    // エラー画面に遷移
                    request.getRequestDispatcher(FORWARD_PATH).forward(request, response);
                    
                    return;
                }
            } catch (Throwable t) {
                // エラーログを出力
                log.error(LOG_FORMAT_UNEXPECTED_ERROR, t);
                
                // 全ての想定外エラーはシステムエラー
                ServletUtil.instance(httpServletResponse).errorLevel(ErrorLevel.SYSTEM_ERROR)
                        .returnCd("errors.systemError").print();
                
                return;
            }
        }
        
        // 次のフィルタ処理へ連携
        chain.doFilter(request, response);
    }
}
