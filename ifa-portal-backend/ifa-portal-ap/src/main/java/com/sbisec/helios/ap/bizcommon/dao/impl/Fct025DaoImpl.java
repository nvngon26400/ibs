package com.sbisec.helios.ap.bizcommon.dao.impl;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.dao.Fct025Dao;
import com.sbisec.helios.ap.bizcommon.dao.mapper.Fct025Mapper;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct025Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct025Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct025Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct025Sql002ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Fct025DaoImpl implements Fct025Dao {
    
    @Autowired
    private Fct025Mapper fct025Mapper;

	@Override
	public List<Fct025Sql001ResponseModel> getFct025Sql001(Fct025Sql001RequestModel fct025Sql001RequestModel) {
		return fct025Mapper.getFct025Sql001(fct025Sql001RequestModel);
	}

	@Override
	public List<Fct025Sql002ResponseModel> getFct025Sql002(Fct025Sql002RequestModel fct025Sql002RequestModel) {
		return fct025Mapper.getFct025Sql002(fct025Sql002RequestModel);
	}

}
