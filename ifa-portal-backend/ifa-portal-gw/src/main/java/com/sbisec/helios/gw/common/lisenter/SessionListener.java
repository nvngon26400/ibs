package com.sbisec.helios.gw.common.lisenter;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.sbisec.helios.gw.common.config.PropertyHolder;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Session listener settings.
 * 
 * @Organization SBIBITS DaLian CB Group
 * @Author kui.zhang
 * @Date 2020/07/17
 */
public class SessionListener implements HttpSessionListener {

    @Autowired
    PropertyHolder prop;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // Set session timeout.
        se.getSession().setMaxInactiveInterval(prop.getSysTimeout());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    }

}
