package com.sbisec.helios.gw.common.interceptor;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sbibits.earth.servlet.annotation.Popup;
import com.sbibits.earth.servlet.annotation.ResponseFile;
import com.sbibits.earth.servlet.annotation.ResponseJson;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.gw.common.controller.BaseController;

// RT3760 - POSTでパラメータがnullでの送信に対応
public class HttpPostCheckInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(HttpPostCheckInterceptor.class);
	private static final String FORWARD_NAME_PREFIX  = "/error?page=405";
	private static final String FORWARD_NAME_JSON    = FORWARD_NAME_PREFIX + "j";

	private List<String> ignorePathList = new ArrayList<String>();

	public HttpPostCheckInterceptor(List<String> ignorePathList) {
		this.ignorePathList = ignorePathList;
	}
	/**
	 * Setting ignorePath.<br>
	 * DI from spring.
	 *
	 * @param ignorePath
	 */
	public void setIgnorePath(String ignorePath) {

		if (ignorePath != null && 0 < ignorePath.trim().length()) {

			logger.info("DI: setIgnorePath:[{}] of HttpPostCheckInterceptor:[{}]", ignorePath, this);

			StringTokenizer tokens = new StringTokenizer(ignorePath.trim(), ", ", false);

			while (tokens.hasMoreTokens()) {

				String path = tokens.nextToken().trim();

				if (! this.ignorePathList.contains(path)) this.ignorePathList.add(path);
			}
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String servletPath = request.getServletPath();
		for(String ignorePath : ignorePathList){
			if(servletPath.equals(ignorePath)){
				return true;
			}
		}
		String methodName = request.getMethod();
		// [PROTO2] POST時の内容変更に伴い、処理修正。
		if(methodName.equals("POST") && request.getContentLength() < 2) {
            logger.info("forward " + FORWARD_NAME_PREFIX);
            setForwardToRequestDispatcher((HandlerMethod) handler, request, response);
			return false;
//		if(methodName.equals("POST")){
//			Enumeration<String> names = request.getParameterNames();
//			if(!names.hasMoreElements()){
//				String viewName = StringUtil.EMPTY_STRING;
//				try{
//					HandlerMethod handlerMethod = (HandlerMethod) handler;
//					BaseController controller = (BaseController)  handlerMethod.getBean();
//					viewName = controller.getViewName();
//				} catch (ClassCastException e){
//					viewName = StringUtil.EMPTY_STRING;
//				}
//				if(StringUtil.isNullOrEmpty(viewName)){
//					logger.info("forward " + FORWARD_NAME_PREFIX);
//					setForwardToRequestDispatcher((HandlerMethod) handler, request, response);
//				} else {
//					String scheme = request.getScheme();
//					String serverName = request.getServerName();
//					int serverPort = request.getServerPort();
//					String contextPath = request.getContextPath();
//					String url = String.format("%s://%s:%d%s%s", scheme,serverName,serverPort,contextPath,viewName);
//					logger.info("Redirect " + url);
//					response.sendRedirect(url);
//				}
//				return false;
//			}
			
		}

		return true;
	}

	private void setForwardToRequestDispatcher(HandlerMethod handlerMethod, HttpServletRequest request, HttpServletResponse response) throws Exception {

		// Forward to the 405 page.
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
