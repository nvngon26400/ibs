package com.sbisec.helios.gw.common.security;

import java.util.Collection;

import com.sbisec.helios.gw.model.UserAttr;

import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * After Ldap access is successful, set the user's permissions and other detailed information.
 * 
 * @Organization SBIBITS DaLian CB Group
 */
@Component
@Slf4j
public class AppLdapUserDetails extends LdapUserDetailsMapper {

//    @Autowired
//    UserAttrDao userAttrDao;

    private UserDetailsChecker checker = new AuthenticationCheck();

    @Override
    public UserDetails mapUserFromContext(
            DirContextOperations ctx,
            String userId,
            Collection<? extends GrantedAuthority> authorities) {

        // Get the user information by login user id.
//        UserAttr user = userAttrDao.findUserById(userId);
    	UserAttr user = new UserAttr();
        checker.check(user);

        return super.mapUserFromContext(ctx, userId, user.getAuthorities());
    }

    private class AuthenticationCheck implements UserDetailsChecker {
        @Override
        public void check(UserDetails user) {
//            if (!user.isAccountNonLocked()) {
//                log.debug("User account is locked");
//                throw new LockedException("User account is locked");
//            }
//            if (!user.isEnabled()) {
//                log.debug("User account is disabled");
//                throw new DisabledException("User is disabled");
//            }
//            if (!user.isAccountNonExpired()) {
//                log.debug("User account is expired");
//                throw new AccountExpiredException("User account has expired");
//            }
//            if (!user.isCredentialsNonExpired()) {
//                log.debug("User account credentials have expired");
//                throw new CredentialsExpiredException("User credentials have expired");
//            }
        }

    }
}
