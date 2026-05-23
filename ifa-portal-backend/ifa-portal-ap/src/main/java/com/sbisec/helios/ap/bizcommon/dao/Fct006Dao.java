package com.sbisec.helios.ap.bizcommon.dao;

import java.util.List;

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

public interface Fct006Dao {

	public List<Fct006Sql001ResponseModel> getComplianceRank(Fct006Sql001RequestModel fct006Sql001RequestModel);

	public List<Fct006Sql002ResponseModel> getComplianceRank2(Fct006Sql002RequestModel fct006Sql002RequestModel);

	// 暫定対応用
	public List<Fct006Sql002ResponseModel> getComplianceRank2ForeignStock(Fct006Sql002RequestModel fct006Sql002RequestModel);

	public List<Fct006Sql003ResponseModel> getComplianceRank3(Fct006Sql003RequestModel fct006Sql003RequestModel);

	public List<Fct006Sql004ResponseModel> getComplianceRank4(Fct006Sql004RequestModel fct006Sql004RequestModel);

	public List<Fct006Sql006ResponseModel> getComplianceRank6(Fct006Sql006RequestModel fct006Sql006RequestModel);

}