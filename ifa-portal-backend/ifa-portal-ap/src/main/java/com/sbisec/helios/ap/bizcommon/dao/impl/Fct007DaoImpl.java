package com.sbisec.helios.ap.bizcommon.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbisec.helios.ap.bizcommon.dao.Fct007Dao;
import com.sbisec.helios.ap.bizcommon.dao.mapper.Fct007Mapper;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct007Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct007Sql001ResponseModel;

@Component
public class Fct007DaoImpl implements Fct007Dao {
	
	private static final Logger logger = LoggerFactory.getLogger(Fct007DaoImpl.class);
	
	@Autowired
	protected Fct007Mapper fct007Mapper;

	@Override
	public Fct007Sql001ResponseModel getDesignatedDate(Fct007Sql001RequestModel fct007Sql001RequestModel){
		return fct007Mapper.getDesignatedDate(fct007Sql001RequestModel);
	}
}
