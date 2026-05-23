package com.sbisec.helios.ap.common.service.impl;

import com.sbisec.helios.ap.common.dao.ServiceStatusDao;
import com.sbisec.helios.ap.common.service.ServiceStatusService;
import com.sbisec.helios.ap.common.util.ServiceStatusUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component(value = "serviceStatusService")
public class ServiceStatusServiceImpl implements ServiceStatusService {

	private static final Logger logger = LoggerFactory.getLogger(ServiceStatusServiceImpl.class);

	private ServiceStatusUtil serviceStatusUtil;
	
	public ServiceStatusServiceImpl(ServiceStatusDao serviceStatusDao) {
		this.serviceStatusUtil = new ServiceStatusUtil();
		this.serviceStatusUtil.setServiceStatusDao(serviceStatusDao);
	}

	/**
	 * Setting serviceStatusUtil.<br>
	 * DI from spring.
	 *
	 * @param serviceStatusUtil
	 */
	public void setServiceStatusUtil(ServiceStatusUtil serviceStatusUtil) {
		this.serviceStatusUtil = serviceStatusUtil;
		logger.debug("DI: setServiceStatusUtil:[" + serviceStatusUtil + "] of ServiceStatusServiceImplL:[" + this + "]");
	}

	@Override
	public Boolean getServiceStatus(String screenId) throws Exception {
		logger.debug("ServiceStatusServiceImplL.getServiceStatus:[{}]", screenId);

        return serviceStatusUtil.getServiceStatus(screenId);
	}

}
