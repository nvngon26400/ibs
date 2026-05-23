package com.sbisec.helios.gw.common.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbibits.earth.servlet.annotation.Popup;
import com.sbibits.earth.servlet.annotation.ResponseFile;
import com.sbibits.earth.servlet.annotation.ResponseJson;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.error.NoMarkedException;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class PermissionCheckInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(PermissionCheckInterceptor.class);

	private static final String LOG_HEADER_PRE_HANDLE = "PermissionCheckInterceptor.preHandle";

	private static final String FORWARD_NAME_PREFIX = "/error?page=403";
	private static final String FORWARD_NAME_JSON = FORWARD_NAME_PREFIX + "j";

	@SuppressWarnings("unchecked")
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

//		if (logger.isDebugEnabled()) logger.debug("{}", LOG_HEADER_PRE_HANDLE);

        // 静的コンテンツに対するアクセスの場合、チェックを行わない
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        
        // 外部API認可チェックOKの場合はチェックを行わない
        if (Boolean.valueOf((String) request.getAttribute(IfaCommonUtil.ATTR_EXT_API_PERMISSION_FLAG))) {
            return true;
        }
	    
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		@SuppressWarnings("rawtypes")
        Class controller = handlerMethod.getBeanType();

		ScreenId screenId = (ScreenId) controller.getAnnotation(ScreenId.class);

		// This is bug at development.
		if (screenId == null || screenId.id() == null) {
			throw new NoMarkedException(
                    handlerMethod.getBeanType().getName() + " has no ScreenId or it's id is null.");
		}

		request.setAttribute("screenId_groupId", screenId.groupId());
		request.setAttribute("screenId_id", screenId.id());
		request.setAttribute("screenId_screenNumber", screenId.screenNumber());

		if (!screenId.ignoreCheck()) {

			try {
                UserAccount userAccount = IfaCommonUtil.getUserAccount();

				if (userAccount != null) {

					if (!userAccount.isAccessible(screenId.id())) {

                        logger.warn("{}: This user:[{}] has no accessibility to {}/{}", LOG_HEADER_PRE_HANDLE,
                                userAccount.getUserId(), screenId.id(), handlerMethod.getBeanType().getName());

						setForwardToRequestDispatcher(handlerMethod, request, response);

						// Subsequent processing is not performed
						return false;
					}
				}

			} catch (IllegalStateException e) {

				if (logger.isDebugEnabled())
					logger.debug("{}: This session has already invalidate.", LOG_HEADER_PRE_HANDLE);
			}
		}

		return true;
	}

	private void setForwardToRequestDispatcher(HandlerMethod handlerMethod, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// Forward to the 403 page.
		RequestDispatcher dispatcher = request.getRequestDispatcher(FORWARD_NAME_JSON);

		dispatcher.forward(request, response);
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

//    	if (logger.isDebugEnabled()) logger.debug("PermissionCheckInterceptor.postHandle");

		// Nothing to do.
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

//		if (logger.isDebugEnabled()) logger.debug("PermissionCheckInterceptor.afterCompletion");

		// Nothing to do.
	}
}
