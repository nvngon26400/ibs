package com.sbisec.helios.ap.common.dao.impl;

import java.util.List;

import com.sbisec.helios.ap.common.dao.MedInformationDao;
import com.sbisec.helios.ap.common.dao.mapper.MedInformationMapper;
import com.sbisec.helios.ap.common.dao.model.MedInformation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MedInformationDaoImpl implements MedInformationDao {

	private static final Logger logger = LoggerFactory.getLogger(MedInformationDaoImpl.class);

	@Autowired
	protected MedInformationMapper mapper;

	@Override
	public List<MedInformation> getMedInformationList() throws Exception {
		return mapper.getMedInformationList();
	}
}
