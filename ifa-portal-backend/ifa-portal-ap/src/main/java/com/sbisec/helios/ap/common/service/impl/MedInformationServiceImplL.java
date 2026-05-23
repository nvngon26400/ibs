package com.sbisec.helios.ap.common.service.impl;

import java.util.List;

import com.sbisec.helios.ap.common.dao.MedInformationDao;
import com.sbisec.helios.ap.common.dao.model.MedInformation;
import com.sbisec.helios.ap.common.dto.IfaLoginA001DtoRequest;
import com.sbisec.helios.ap.common.service.MedInformationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author SCSK
 *
 */
@Component(value = "medInformationService")
public class MedInformationServiceImplL implements MedInformationService {

	private static final Logger logger = LoggerFactory.getLogger(MedInformationServiceImplL.class);
	
	@Autowired
	protected MedInformationDao medInformationDao;

	/**
	 * Setting MedInformationDao.<br>
	 * DI from spring.
	 *
	 * @param dao
	 */
	public void setMedInformationDao(MedInformationDao medInformationDao) {

		this.medInformationDao = medInformationDao;

		logger.debug("DI: setMedInformationDao:[{}] of MedInformationService:[{}]", medInformationDao, this);
	}

	@Override
	public List<MedInformation> getMedInformationList(IfaLoginA001DtoRequest dtoReq) throws Exception {

		logger.debug("MedInformationServiceImplL.getMedInformationList:[]");

		return medInformationDao.getMedInformationList();
	}
}
