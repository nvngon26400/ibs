package com.sbisec.helios.ap.bizcommon.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

@Mapper
public interface Fct006Mapper {

	public List<Fct006Sql001ResponseModel> getComplianceRank(
			@Param("fct006Sql001RequestModel") Fct006Sql001RequestModel fct006Sql001RequestModel);

	public List<Fct006Sql002ResponseModel> getComplianceRank2(
			@Param("fct006Sql002RequestModel") Fct006Sql002RequestModel fct006Sql002RequestModel);

	// 暫定対応用
	public List<Fct006Sql002ResponseModel> getComplianceRank2ForeignStock(
			@Param("fct006Sql002RequestModel") Fct006Sql002RequestModel fct006Sql002RequestModel);

	public List<Fct006Sql003ResponseModel> getComplianceRank3(
			@Param("fct006Sql003RequestModel") Fct006Sql003RequestModel fct006Sql003RequestModel);

	public List<Fct006Sql004ResponseModel> getComplianceRank4(
			@Param("fct006Sql004RequestModel") Fct006Sql004RequestModel fct006Sql004RequestModel);

	public List<Fct006Sql006ResponseModel> getComplianceRank6(
			@Param("fct006Sql006RequestModel") Fct006Sql006RequestModel fct006Sql006RequestModel);

}