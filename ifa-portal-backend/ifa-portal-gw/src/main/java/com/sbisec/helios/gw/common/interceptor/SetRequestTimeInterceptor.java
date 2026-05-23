package com.sbisec.helios.gw.common.interceptor;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.util.DateUtil;
import com.sbisec.helios.ap.common.dto.SystemDateDtoRequest;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.collections.CollectionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * リクエスト日時をリクエストスコープにセットする
 *
 * @author SCSK
 *
 */
public class SetRequestTimeInterceptor implements HandlerInterceptor {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SetRequestTimeInterceptor.class);
    
    private static final String HOUR = "HH";
    
    private static final String YMDHMS = "yyyyMMddHHmmss";
    
    /**
     * リクエスト日時をリクエストスコープにセットする<br/>
     * <br/>
     * このメソッドは、リクエストがコントローラで処理される前に呼び出され、<br/>
     * 以下の属性をHTTPリクエストに設定します：
     * <ul>
     *     <li>{@code IfaCommonUtil.ATTR_KEY_RTIME}: 現在の日付と時刻を示す{@link java.util.Date}オブジェクト。</li>
     *     <li>{@code IfaCommonUtil.ATTR_KEY_RHOUR}: 現在の時刻 (時間) を文字列としてフォーマットしたもの。</li>
     *     <li>{@code IfaCommonUtil.ATTR_KEY_RYMDHMS}: 現在の日付と時刻 (年、月、日、時、分、秒) を文字列としてフォーマットしたもの。</li>
     * </ul>
     *
     * @param request requestオブジェクト
     * @param response responseオブジェクト
     * @param handler ハンドラ
     * @return 常に{@code true}を返す
     * @throws Exception 例外発生時にスロー
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
        Date requestedTime = new Date();
        
        // ログレベルDEBUGの場合、DBのIFA_DATE_UTIL.IFA_GET_DATEからシステム日付を取得する
        if (LOGGER.isDebugEnabled()) {
            try {
                SystemDateDtoRequest req = new SystemDateDtoRequest();
                DataList<Date> resp = ApiRequestUtil.invoke("systemDateService", "getSystemDate",
                        new TypeReference<DataList<Date>>() {
                        }, req);
                List<Date> dateList = resp.getDataList();
                if (!CollectionUtils.isEmpty(dateList)) {
                    // データが取得できた場合、設定する
                    requestedTime = dateList.get(0);
                }
            } catch (Exception e) {
                // データ取得でエラーが発生した場合、サーバ日付を設定する
                LOGGER.debug("The system date retrieval failed.");
                requestedTime = new Date();
            }
        }
        request.setAttribute(IfaCommonUtil.ATTR_KEY_RTIME, requestedTime);
        request.setAttribute(IfaCommonUtil.ATTR_KEY_RHOUR, DateUtil.format(requestedTime, HOUR));
        request.setAttribute(IfaCommonUtil.ATTR_KEY_RYMDHMS, DateUtil.format(requestedTime, YMDHMS));
        
        return true;
    }
    
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        
        // Nothing to do.
    }
    
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        
        // Nothing to do.
    }
}
