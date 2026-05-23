package com.sbisec.helios.ap.common.config;

import java.util.List;
import java.util.stream.Collectors;

import com.sbisec.helios.ap.common.security.SecLogoutSuccessHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;

/**
 * Service security authentication configuration.
 * 
 * @Organization SBIBITS DaLian CB Group
 */
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Slf4j
public class SecurityConfig {

//    @Autowired
//    private SessionRegistry sessionRegistry;
//    @Autowired
//    private SecLoginSuccessHandler secLoginSuccessHandler;
//    @Autowired
//    private SecLoginFailureHandler secLoginFailureHandler;
	@Autowired
	private SecLogoutSuccessHandler secLogoutSuccessHandler;
//    @Autowired
//    private SecAccessDeniedHandler secAccessDeniedHandler;
//    @Autowired
//    private SecInvalidSessionStrategy secInvalidSessionStrategy;
//    @Autowired
//    private SecExpiredSessionStrategy secExpiredSessionStrategy;
//    @Autowired
//    private AppLdapUserDetails appLdapUserDetails;
	@Autowired
	private PropertyHolder prop;
	@Autowired
	protected MessageSourceAccessor message;

//	@Autowired
//	private TokenService tokenService;

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() throws Exception {
        return (web) -> {
            web.ignoring().antMatchers(prop.getSecurityIgnore());
        };
	}

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors()
			.and()
				.csrf()
				.disable()
				.authorizeRequests()
				.anyRequest()
				.anonymous()
			.and();
        return http.build();
//				.addFilterBefore(new LoginFilter(message, prop.getApiVersion(), tokenService),UsernamePasswordAuthenticationFilter.class);
//		http.cors()
//            .and()
//            .csrf()
//            .ignoringAntMatchers(AppConstants.URL_EXT_LOGIN)
//            .ignoringAntMatchers(prop.getSecurityIgnore());
//            .csrfTokenRepository(getCsrfTokenRepository());
//				.addFilterBefore(new MultipleReadEnableFilter(), LoginFilter.class);

//        	.csrf().disable();
//        	.authorizeRequests()
//        	.antMatchers("/h2-console/**").permitAll()
//            .anyRequest().anonymous()
//            .and()
//          .logout()
//          .logoutUrl("/logout")
//          .logoutSuccessHandler(secLogoutSuccessHandler);

//            .and()
//            .formLogin()
//            .loginProcessingUrl(AppConstants.URL_EXT_LOGIN)
//            .usernameParameter(AppConstants.LOGIN_USERID)
//            .successHandler(secLoginSuccessHandler)
//            .failureHandler(secLoginFailureHandler)
//            .and()
//            .logout()
//            .logoutUrl(AppConstants.URL_EXT_LOGOUT)
//            .logoutSuccessHandler(secLogoutSuccessHandler)
//            .deleteCookies(AppConstants.COOKIES_ID)
//            .and()
//            .exceptionHandling()
//            .accessDeniedHandler(secAccessDeniedHandler);
//        http.sessionManagement()
//            .invalidSessionStrategy(secInvalidSessionStrategy)
//            .maximumSessions(1)
//            .maxSessionsPreventsLogin(false)
//            .expiredSessionStrategy(secExpiredSessionStrategy)
//            .sessionRegistry(sessionRegistry);
//        http.addFilterBefore(new LoginFilter(message, prop.getApiVersion(), tokenService ), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

	/**
	 * Csrf token.
	 * 
	 * @return
	 */
	@Bean
	public CsrfTokenRepository getCsrfTokenRepository() {
		CookieCsrfTokenRepository cookie = CookieCsrfTokenRepository.withHttpOnlyFalse();
		cookie.setCookiePath("/");
		return cookie;
	}

//    @Bean
//    @Override
//    protected AuthenticationManager authenticationManager() throws Exception {
//        WebProviderManager authenticationManager = new WebProviderManager(buildProviders());
//        authenticationManager.setEraseCredentialsAfterAuthentication(false);
//        return authenticationManager;
//    }

	List<AuthenticationProvider> buildProviders() {
		return prop.getEnvLdaps().stream().map(ldap -> {
//                       log.info("AD domain {}", ldap.getDomain());
//                       log.info("AD url {}", ldap.getUrl());
			ActiveDirectoryLdapAuthenticationProvider provider = new ActiveDirectoryLdapAuthenticationProvider(
					ldap.getDomain(), ldap.getUrl());
			provider.setConvertSubErrorCodesToExceptions(true);
			provider.setUseAuthenticationRequestCredentials(true);
//                       provider.setUserDetailsContextMapper(appLdapUserDetails);
			return provider;
		}).collect(Collectors.toList());
	}
}
