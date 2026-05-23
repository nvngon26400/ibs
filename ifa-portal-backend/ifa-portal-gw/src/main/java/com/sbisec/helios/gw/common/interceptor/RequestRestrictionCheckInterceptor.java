package com.sbisec.helios.gw.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.cache.CacheManager;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sbisec.helios.gw.common.util.RequestRestrictionHelper;

/**
 * リクエスト制限チェックインターセプタークラス。<br>
 * RequestRestrictionFilterクラスと連動しており、リクエスト状態の管理はフィルター側の責任となっている。
 * 
 * @author SCSK宮坂
 */
public class RequestRestrictionCheckInterceptor implements HandlerInterceptor {
	/** 遷移先パス */
	private static final String FORWARD_PATH = "/error?page=500";

	/** リクエスト制限ヘルパー */
	private RequestRestrictionHelper requestRestrictionHelper = null;

	/**
	 * コンストラクタ。
	 * 
	 * @param cacheManager キャッシュマネージャー。
	 */
	public RequestRestrictionCheckInterceptor(CacheManager cacheManager) {
		this.requestRestrictionHelper = new RequestRestrictionHelper(cacheManager);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// スキップ対象のリクエストではない場合
		if (!requestRestrictionHelper.isSkippedRestriction(request)) {
			// リクエスト回数制限に抵触したかを判定
			if (requestRestrictionHelper.isRestrictedRequest()) {
				// エラー画面に遷移
				request.getRequestDispatcher(FORWARD_PATH).forward(request, response);

				return false;
			}
		}

		return true;
	}
}
