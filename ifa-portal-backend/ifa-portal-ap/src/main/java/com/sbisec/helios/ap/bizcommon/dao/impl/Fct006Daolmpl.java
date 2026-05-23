package com.sbisec.helios.ap.bizcommon.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbisec.helios.ap.bizcommon.dao.Fct006Dao;
import com.sbisec.helios.ap.bizcommon.dao.mapper.Fct006Mapper;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct006Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct006Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct006Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct006Sql002ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct006Sql003RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct006Sql003ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct006Sql004RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct006Sql004ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct006Sql006RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct006Sql006ResponseModel;

@Component
public class Fct006Daolmpl implements Fct006Dao {

	@Autowired
	protected Fct006Mapper fct006Mapper;

	@Override
	public List<Fct006Sql001ResponseModel> getComplianceRank(Fct006Sql001RequestModel fct006Sql001RequestModel) {

		return fct006Mapper.getComplianceRank(fct006Sql001RequestModel);
	}

	@Override
	public List<Fct006Sql002ResponseModel> getComplianceRank2(Fct006Sql002RequestModel fct006Sql002RequestModel) {

		return fct006Mapper.getComplianceRank2(fct006Sql002RequestModel);
	}

	// 暫定対応用
	@Override
	public List<Fct006Sql002ResponseModel> getComplianceRank2ForeignStock(Fct006Sql002RequestModel fct006Sql002RequestModel) {

		return fct006Mapper.getComplianceRank2ForeignStock(fct006Sql002RequestModel);
	}

	@Override
	public List<Fct006Sql003ResponseModel> getComplianceRank3(Fct006Sql003RequestModel fct006Sql003RequestModel) {
		return fct006Mapper.getComplianceRank3(fct006Sql003RequestModel);
	}

	@Override
	public List<Fct006Sql004ResponseModel> getComplianceRank4(Fct006Sql004RequestModel fct006Sql004RequestModel) {
		return fct006Mapper.getComplianceRank4(fct006Sql004RequestModel);
	}

	@Override
	public List<Fct006Sql006ResponseModel> getComplianceRank6(Fct006Sql006RequestModel fct006Sql006RequestModel) {
		return fct006Mapper.getComplianceRank6(fct006Sql006RequestModel);
	}

}
