package com.sbisec.helios.gw.common.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

public class IfaCustomerCommonCheckInterceptor implements HandlerInterceptor {

    /** 顧客別メニューの機能ID prefix */
    private static final String CUSTOMER_MENU_SCREEN_ID_PREFIX = "SUB0202";

    /** エラー発生時のフォワード先 */
    private static final String FORWARD_NAME_JSON = "/error?page=409j";

    /** ロガー */
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpSessionCheckInterceptor.class);

    /** デバッグフラグ */
    private static final Boolean debugFlag = Boolean.valueOf(System.getenv().get("DEBUG"));

    /**
     * 顧客別メニュー配下の画面
     * かつ
     * 顧客選択以外の画面の場合
     *
     * 画面で保持している顧客共通情報とRedisで保持している顧客共通情報を突合し
     * 一致しない場合にはログアウトする。
     *
     * @param request HttpRequest
     * @param response HttpResponse
     * @param handler Handler
     * @return チェック結果
     * @throws Exception 例外
     * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {

        /* =======================================================================================*/
        /*                                                                                        */
        /* チェック不要の場合、処理を抜ける。                                                        */
        /*                                                                                        */
        /* =======================================================================================*/

        // 静的コンテンツに対するアクセスの場合、チェックを行わない
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        // 外部API認可チェックOKの場合はチェックを行わない
        if (Boolean.valueOf((String) request.getAttribute(IfaCommonUtil.ATTR_EXT_API_PERMISSION_FLAG))) {
            return true;
        }

        // 顧客別メニュー配下のメニュー以外の場合はチェックを行わない
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        @SuppressWarnings("rawtypes")
        Class controller = handlerMethod.getBeanType();
        ScreenId screenId = (ScreenId) controller.getAnnotation(ScreenId.class);
        if (StringUtils.startsWithIgnoreCase(screenId.id(), CUSTOMER_MENU_SCREEN_ID_PREFIX) == false) {
            return true;
        }

        // デバッグモードの場合はチェックを行わない
        if (debugFlag) {
            return true;
        }

        /* =======================================================================================*/
        /*                                                                                        */
        /* 画面がリクエストのヘッダにセットした部店、口座番号と                                        */
        /* Redisに登録された部店、口座番号が一致する場合、チェックOKで処理を抜ける。                    */
        /*                                                                                        */
        /* =======================================================================================*/

        // ヘッダから画面がセットした部店、口座番号を取得
        String butenCodeFromCliant = request.getHeader("ButenCode");
        String accountNumberFromCliant = request.getHeader("AccountNumber");

        // Redisに登録された部店、口座番号を取得
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        CustomerCommon cc = IfaGwCommonUtil.getCustomerCommon();
        String butenCodeFromRedis = (cc == null) ? "" : cc.getButenCode();
        String accountNumberFromRedis = (cc == null) ? "" : cc.getAccountNumber();

        // 部店コード、口座番号が一致する場合、チェックOKで処理を抜ける。
        if (
            StringUtils.equals(butenCodeFromCliant, butenCodeFromRedis)
            && StringUtils.equals(accountNumberFromCliant, accountNumberFromRedis)
        ) {
            return true;
        }

        /* =======================================================================================*/
        /*                                                                                        */
        /* タブ複製エラーでログアウト処理を実行する。                                                 */
        /*                                                                                        */
        /* =======================================================================================*/

        // デバッグモードであればログを出力する。
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Duplicate tab is not allowd.");
        }

        // 409jページへフォワードする。
        RequestDispatcher dispatcher = request.getRequestDispatcher(FORWARD_NAME_JSON);
        dispatcher.forward(request, response);

        return false;

    }
}
