package com.sbisec.helios.ap.common.service;

import java.util.List;

import com.sbisec.helios.ap.common.dao.model.MedInformation;
import com.sbisec.helios.ap.common.dto.IfaLoginA001DtoRequest;
import com.sbisec.helios.ap.service.Service;


public interface MedInformationService extends Service {

	public List<MedInformation> getMedInformationList(IfaLoginA001DtoRequest dtoReq
	) throws Exception;
}
