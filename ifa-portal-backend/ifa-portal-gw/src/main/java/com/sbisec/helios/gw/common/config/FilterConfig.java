package com.sbisec.helios.gw.common.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sbisec.helios.gw.common.constants.AppConstants;
import com.sbisec.helios.gw.common.filter.AccessLogFilter;
import com.sbisec.helios.gw.common.filter.HttpoxyFilter;
import com.sbisec.helios.gw.common.filter.ParameterAttackFilter;
import com.sbisec.helios.gw.common.filter.ParameterLogFilter;
import com.sbisec.helios.gw.common.filter.RequestEncodingFilter;
import com.sbisec.helios.gw.common.filter.RequestRestrictionFilter;
import com.sbisec.helios.gw.common.logger.LogFilter;

/**
 * Configure filters.
 * 
 * @Organization SBIBITS DaLian CB Group
 */
@Configuration
public class FilterConfig {
    /**
	 * Beanを登録する（HttpoxyFilter）。
	 * 
	 * @return Beanインスタンス。
	 */
	@Bean
	public FilterRegistrationBean<HttpoxyFilter> HttpoxyFilter() {
		FilterRegistrationBean<HttpoxyFilter> filterRegistrationBean = new FilterRegistrationBean<HttpoxyFilter>();

		filterRegistrationBean.setFilter(new HttpoxyFilter());
		filterRegistrationBean.addUrlPatterns(AppConstants.URL_ALL);
		filterRegistrationBean.setOrder(1);

		return filterRegistrationBean;
	}

	/**
	 * Beanを登録する（RequestEncodingFilter）。
	 * 
	 * @param cacheManager キャッシュマネージャー。
	 * @return Beanインスタンス。
	 */
	@Bean
	public FilterRegistrationBean<RequestEncodingFilter> encodingFilter(CacheManager cacheManager)  {
		FilterRegistrationBean<RequestEncodingFilter> filterRegistrationBean = new FilterRegistrationBean<RequestEncodingFilter>();

		filterRegistrationBean.setFilter(new RequestEncodingFilter(cacheManager));
		filterRegistrationBean.addUrlPatterns(AppConstants.URL_ALL);
		filterRegistrationBean.setOrder(2);

		return filterRegistrationBean;
	}

	/**
	 * Beanを登録する（RequestRestrictionFilter）。
	 * 
	 * @param cacheManager キャッシュマネージャー。
	 * @return Beanインスタンス。
	 */
	@Bean
	public FilterRegistrationBean<RequestRestrictionFilter> requestRestrictionFilter(CacheManager cacheManager) {
		FilterRegistrationBean<RequestRestrictionFilter> filterRegistrationBean = new FilterRegistrationBean<RequestRestrictionFilter>();

		filterRegistrationBean.setFilter(new RequestRestrictionFilter(cacheManager));
		filterRegistrationBean.addUrlPatterns(AppConstants.URL_ALL);
		filterRegistrationBean.setOrder(3);

		return filterRegistrationBean;
	}

	/**
	 * Beanを登録する（ParameterAttackFilter）。
	 * 
	 * @return Beanインスタンス。
	 */
	@Bean
	public FilterRegistrationBean<ParameterAttackFilter> parameterAttackFilter() {
		FilterRegistrationBean<ParameterAttackFilter> filterRegistrationBean = new FilterRegistrationBean<ParameterAttackFilter>();

		filterRegistrationBean.setFilter(new ParameterAttackFilter());
		filterRegistrationBean.addUrlPatterns(AppConstants.URL_ALL);
		filterRegistrationBean.setOrder(4);

		return filterRegistrationBean;
	}

	/**
	 * Beanを登録する（ParameterLogFilter）。
	 * 
	 * @param cacheManager キャッシュマネージャー。
	 * @return Beanインスタンス。
	 */
	@Bean
	public FilterRegistrationBean<ParameterLogFilter> parameterLogFilter(CacheManager cacheManager) {
		FilterRegistrationBean<ParameterLogFilter> filterRegistrationBean = new FilterRegistrationBean<ParameterLogFilter>();

		filterRegistrationBean.setFilter(new ParameterLogFilter(cacheManager));
		filterRegistrationBean.addUrlPatterns(AppConstants.URL_ALL);
		filterRegistrationBean.setOrder(11);

		return filterRegistrationBean;
	}

	/**
	 * Beanを登録する（AccessLogFilter）。
	 * 
	 * @param cacheManager キャッシュマネージャー。
	 * @return Beanインスタンス。
	 */
	@Bean
	public FilterRegistrationBean<AccessLogFilter> accessLogFilter(CacheManager cacheManager) {
		FilterRegistrationBean<AccessLogFilter> filterRegistrationBean = new FilterRegistrationBean<AccessLogFilter>();

		filterRegistrationBean.setFilter(new AccessLogFilter(cacheManager));
		filterRegistrationBean.addUrlPatterns(AppConstants.URL_ALL);
		filterRegistrationBean.setOrder(12);

		return filterRegistrationBean;
	}

    /**
     * Configure log filters.
     * 
     * @return
     */
    @Bean
    public FilterRegistrationBean<LogFilter> logbackFilter() {
        FilterRegistrationBean<LogFilter> reg = new FilterRegistrationBean<LogFilter>();
        reg.setFilter(new LogFilter());
        reg.addUrlPatterns(AppConstants.URL_ALL);
        reg.setOrder(21);
        return reg;
    }
}
