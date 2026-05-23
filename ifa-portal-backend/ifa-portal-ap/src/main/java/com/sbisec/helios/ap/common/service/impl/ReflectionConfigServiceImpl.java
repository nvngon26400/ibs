package com.sbisec.helios.ap.common.service.impl;

import java.util.List;

import com.sbisec.helios.ap.common.model.RestClientRequestModel;
import com.sbisec.helios.ap.common.model.XmlServiceConfigModel;
import com.sbisec.helios.ap.common.model.XmlServiceModel;
import com.sbisec.helios.ap.common.service.ReflectionConfigService;

import org.springframework.stereotype.Component;

@Component(value = "reflectionConfigService")
public class ReflectionConfigServiceImpl implements ReflectionConfigService {

	private XmlServiceConfigModel serviceConfigModel;

	public ReflectionConfigServiceImpl() {
		this.serviceConfigModel = new XmlServiceConfigModel();
	}

    public XmlServiceModel getServiceConfig(RestClientRequestModel request) {
		List<XmlServiceModel> services = this.serviceConfigModel.getServices().getServices();
        XmlServiceModel service = services.stream()
                .filter(val -> val.getFunctionId().equals(request.getServiceName() + "." + request.getMethodName()))
                .findFirst().orElse(null);
		return service;
	}
}
