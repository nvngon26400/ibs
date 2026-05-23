package com.sbisec.helios.ap.common.dao.impl;

import com.sbisec.helios.ap.common.dao.BranchDao;
import com.sbisec.helios.ap.common.dao.mapper.BranchMapper;
import com.sbisec.helios.ap.common.model.Branch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BranchDaoImpl implements BranchDao {
	private static final Logger logger = LoggerFactory.getLogger(BranchDaoImpl.class);

	@Autowired
	protected BranchMapper mapper;

	@Override
	public Branch getBranch(String branchCode) throws Exception {
		return mapper.getBranch(branchCode);
	}

}
