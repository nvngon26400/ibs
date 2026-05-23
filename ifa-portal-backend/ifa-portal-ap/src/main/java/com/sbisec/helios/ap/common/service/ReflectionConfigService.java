package com.sbisec.helios.ap.common.service;

import com.sbisec.helios.ap.common.model.RestClientRequestModel;
import com.sbisec.helios.ap.common.model.XmlServiceModel;
import com.sbisec.helios.ap.service.Service;

public interface ReflectionConfigService extends Service{

    public XmlServiceModel getServiceConfig(RestClientRequestModel request);

}
