package com.sbisec.helios.ap.common.dao;

import java.util.List;

import com.sbisec.helios.ap.common.dao.model.MedInformation;

import org.springframework.stereotype.Component;

@Component
public interface MedInformationDao {

	public List<MedInformation> getMedInformationList() throws Exception;
}
