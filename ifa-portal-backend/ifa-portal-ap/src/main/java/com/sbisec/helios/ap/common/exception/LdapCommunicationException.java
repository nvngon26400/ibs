package com.sbisec.helios.ap.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Ldap connection error exception.
 * 
 * @Organization SBIBITS DaLian CB Group
 */
public class LdapCommunicationException extends AuthenticationException {

    private static final long serialVersionUID = -5399691634736639392L;

    /**
     * Constructs a <code>LdapCommunicationException</code> with the specified message.
     *
     * @param msg the detail message
     */
    public LdapCommunicationException(String msg) {
        super(msg);
    }
}
