package com.sbisec.helios.ap.common.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.dao.ServiceStatusDao;
import com.sbisec.helios.ap.common.dao.mapper.ServiceStatusMapper;
import com.sbisec.helios.ap.common.model.ServiceStatusModel;

@Component
public class ServiceStatusDaoImpl extends RowSelectableDao implements ServiceStatusDao {

	// private static final String SERVICE_INE = "0";
	private static final String SERVICE_OUTAGE = "1";

	 @Autowired
	private ServiceStatusMapper mapper;

	@Override
	public Boolean getServiceStatus(String screenId) throws Exception {
		screenId = StringUtil.emptyToNull(screenId);
		Boolean result = Boolean.TRUE;

		List<ServiceStatusModel> serviceStatusList = mapper.getServiceStatus(screenId);
		for(ServiceStatusModel serviceStatus : serviceStatusList){
			String serviceSstatus = serviceStatus.getServiceStatus();

			if(SERVICE_OUTAGE.equals(serviceSstatus)){
				result = Boolean.FALSE;
				break;
			}
		}
		return result;
	}

}
