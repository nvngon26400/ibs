package com.sbisec.helios.gw.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.cache.CacheManager;

import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.ServletUtil;
import com.sbisec.helios.gw.common.util.RequestRestrictionHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * リクエスト制限フィルタークラス。<br>
 * RequestRestrictionCheckInterceptorクラスと連動しており、リクエスト状態チェックはインターセプター側の責任となっている。
 *
 * @author SCSK宮坂
 */
@Slf4j
public class RequestRestrictionFilter implements Filter {
    
    /** ログフォーマット（想定外エラー） */
    private static final String LOG_FORMAT_UNEXPECTED_ERROR = "RequestRestrictionFilter unexpected error has occurred.";
    
    /** リクエスト制限ヘルパー */
    private RequestRestrictionHelper requestRestrictionHelper = null;
    
    /**
     * コンストラクタ。
     *
     * @param cacheManager キャッシュマネージャー。
     */
    public RequestRestrictionFilter(CacheManager cacheManager) {
        
        this.requestRestrictionHelper = new RequestRestrictionHelper(cacheManager);
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
                // スキップ対象のリクエストではない場合
                if (!requestRestrictionHelper.isSkippedRestriction(httpServletRequest)) {
                    // リクエスト回数をインクリメント
                    requestRestrictionHelper.incrementRequestCount();
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
