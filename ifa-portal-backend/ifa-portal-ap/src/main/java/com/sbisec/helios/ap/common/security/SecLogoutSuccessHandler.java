package com.sbisec.helios.ap.common.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbisec.helios.ap.common.enums.RtnCdEnum;
import com.sbisec.helios.ap.common.util.ServletUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Processing when logout succeeds.
 * 
 * @Organization SBIBITS DaLian CB Group
 */
@Component
@Slf4j
public class SecLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    protected MessageSourceAccessor message;

//    @Autowired
//    private TokenService tokenService;
    
    @Override
    public void onLogoutSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {

        // String userId = ((User) authentication.getPrincipal()).getUsername();

        // log.warn(message.getMessage(MessagesConstants.E9007, new Object[] { userId }));
    	
//     	FrameworkSessionInfo sessionInfo = tokenService

    	ServletUtil.instance(response)
                   .returnCd(RtnCdEnum.INFO.toString())
                   .print();
    }
}
