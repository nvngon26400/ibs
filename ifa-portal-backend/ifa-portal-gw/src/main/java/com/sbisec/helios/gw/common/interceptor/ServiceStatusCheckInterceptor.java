package com.sbisec.helios.gw.common.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.Popup;
import com.sbibits.earth.servlet.annotation.ResponseFile;
import com.sbibits.earth.servlet.annotation.ResponseJson;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbisec.helios.ap.common.constants.ServiceNameConstants;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ServiceStatusCheckInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(ServiceStatusCheckInterceptor.class);
	private static final String FORWARD_NAME_PREFIX  = "/error?page=503";
	private static final String FORWARD_NAME_JSON    = FORWARD_NAME_PREFIX + "j";

	private List<String> ignorePathList = new ArrayList<String>();
	private List<String> ignoreScreenIdList = new ArrayList<String>();

	private static final String DEFAULT_MENU_ID = "undefined";

	/**
	 * コンストラクタ。
	 * @param ignorePathList
	 * @param ignoreScreenIdList
	 */
	public ServiceStatusCheckInterceptor(List<String> ignorePathList , List<String> ignoreScreenIdList) {
		this.ignorePathList = ignorePathList;
		this.ignoreScreenIdList = ignoreScreenIdList;
	}
	/**
	 * Setting ignorePath.<br>
	 * DI from spring.
	 *
	 * @param ignorePath
	 */
	public void setIgnorePath(String ignorePath) {

		if (ignorePath != null && 0 < ignorePath.trim().length()) {

			logger.info("DI: setIgnorePath:[{}] of ServiceStatusCheckInterceptor:[{}]", ignorePath, this);

			StringTokenizer tokens = new StringTokenizer(ignorePath.trim(), ", ", false);

			while (tokens.hasMoreTokens()) {

				String path = tokens.nextToken().trim();

				if (! this.ignorePathList.contains(path)) this.ignorePathList.add(path);
			}
		}
	}

	/**
	 * Setting ignoreScreenId.<br>
	 * DI from spring.
	 *
	 * @param ignorePath
	 */
	public void setIgnoreScreenId(String ignoreScreenId) {

		if (ignoreScreenId != null && 0 < ignoreScreenId.trim().length()) {

			logger.info("DI: setIgnoreScreenId:[{}] of ServiceStatusCheckInterceptor:[{}]", ignoreScreenId, this);

			StringTokenizer tokens = new StringTokenizer(ignoreScreenId.trim(), ", ", false);

			while (tokens.hasMoreTokens()) {

				String path = tokens.nextToken().trim();

				if (! this.ignoreScreenIdList.contains(path)) this.ignoreScreenIdList.add(path);
			}
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String servletPath = request.getServletPath();
		for(String ignorePath : ignorePathList){
//			if(servletPath.equals(ignorePath)){
			if (servletPath.startsWith(ignorePath)) {
				return true;
			}
		}

        // 静的コンテンツに対するアクセスの場合、チェックを行わない
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		// Get screenId id.
		String menuId = DEFAULT_MENU_ID;
        ScreenId screenId = (ScreenId) handlerMethod.getBeanType().getAnnotation(ScreenId.class);
		if (screenId != null) {
			menuId = screenId.id();
		} else {
			return true;
		}

		for(String ignoreScreenId : ignoreScreenIdList){
			if(menuId.equals(ignoreScreenId)){
				return true;
			}
		}
		// [PROTO2] サービス呼び出し
		// Boolean ststus = serviceStatusService.getServiceStatus(menuId);
		Boolean ststus = ApiRequestUtil.invoke(ServiceNameConstants.SERVICE_STATUS_SERVICE, "getServiceStatus",
				new TypeReference<Boolean>() {}, menuId);
		if(!ststus){
			logger.info("forward " + FORWARD_NAME_PREFIX);
			setForwardToRequestDispatcher((HandlerMethod) handler, request, response);
			return false;
		} else {
			return true;
		}

	}

	private void setForwardToRequestDispatcher(HandlerMethod handlerMethod, HttpServletRequest request, HttpServletResponse response) throws Exception {

		// Forward to the 503 page.
		RequestDispatcher dispatcher = request.getRequestDispatcher(FORWARD_NAME_JSON);

		dispatcher.forward(request, response);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    	// Nothing to do.
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    	// Nothing to do.
	}


}
