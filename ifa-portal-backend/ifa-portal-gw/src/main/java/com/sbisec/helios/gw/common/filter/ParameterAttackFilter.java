package com.sbisec.helios.gw.common.filter;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.HttpRequestUtil;
import com.sbisec.helios.ap.common.util.ServletUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * パラメータ攻撃フィルタークラス。
 *
 * @author SCSK宮坂
 */
@Slf4j
public class ParameterAttackFilter implements Filter {
    
    /** 無効パラメータ名パターン */
    private static final Pattern INVALID_PARAM_NM_PETTERN = Pattern.compile("(^|\\W)[cC]lass\\W");
    
    /** ログフォーマット（ログ出力除外パスリスト設定あり） */
    private static final String LOG_FORMAT_PARAM_ATTACK = "INVALID REQUEST : CLIENT IP={}, REQUEST KEY={}, REQUEST VALUE={}";
    
    /** ログフォーマット（マスキング対象パラメータキー設定あり） */
    private static final String LOG_FORMAT_COOKIE_ATTACK = "INVALID COOKIE : CLIENT IP={}, COOKIE NAME={}, COOKIE VALUE={}";
    
    /** ログフォーマット（想定外エラー） */
    private static final String LOG_FORMAT_UNEXPECTED_ERROR = "ParameterAttackFilter unexpected error has occurred.";
    
    /**
     * コンストラクタ。
     */
    public ParameterAttackFilter() {
    
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
                // パラメータ攻撃かどうかを判定
                if (isParameterAttack(httpServletRequest)) {
                    // システムエラー
                    ServletUtil.instance(httpServletResponse).errorLevel(ErrorLevel.SYSTEM_ERROR)
                            .returnCd("errors.systemError").print();
                    
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
    
    /**
     * パラメータ攻撃かどうかを判定する。
     *
     * @param httpServletRequest HTTPサーブレットリクエスト。
     * @return パラメータ攻撃フラグ（true：パラメータ攻撃である、false：パラメータ攻撃でない）
     */
    private boolean isParameterAttack(HttpServletRequest httpServletRequest) {
        
        boolean isParameterAttack = false;
        
        // パラメータ名をチェック
        for (String paramName : httpServletRequest.getParameterMap().keySet()) {
            if (INVALID_PARAM_NM_PETTERN.matcher(paramName).find()) {
                log.error(LOG_FORMAT_PARAM_ATTACK, HttpRequestUtil.getRemoteAddress(httpServletRequest), paramName,
                        httpServletRequest.getParameter(paramName));
                
                isParameterAttack = true;
                
                break;
            }
        }
        
        // クッキー名をチェック
        if (!isParameterAttack && httpServletRequest.getCookies() != null) {
            for (Cookie cookie : httpServletRequest.getCookies()) {
                if (INVALID_PARAM_NM_PETTERN.matcher(cookie.getName()).find()) {
                    log.error(LOG_FORMAT_COOKIE_ATTACK, HttpRequestUtil.getRemoteAddress(httpServletRequest),
                            cookie.getName(), cookie.getValue());
                    
                    isParameterAttack = true;
                    
                    break;
                }
            }
        }
        
        return isParameterAttack;
    }
}
