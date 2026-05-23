package com.sbisec.helios.gw.common.interceptor;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

public class IfaLogoutHandler implements LogoutHandler {

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		
		try {
			String result = request.getReader().lines().collect(Collectors.joining("\r\n"));
			System.out.println(result);
		}catch (Exception e) {
			System.out.println("Error");
		}
	}
}
