package com.sbisec.helios.ap.common.service;

import com.sbisec.helios.ap.service.Service;

public interface ServiceStatusService extends Service {

	public Boolean getServiceStatus(String screenId) throws Exception;
}
