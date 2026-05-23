package com.sbisec.helios.ap.common.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.common.dao.model.MedInformation;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MedInformationMapper {

	public List<MedInformation> getMedInformationList() throws Exception;
}
