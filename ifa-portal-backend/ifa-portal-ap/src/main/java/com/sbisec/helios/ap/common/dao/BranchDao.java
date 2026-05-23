package com.sbisec.helios.ap.common.dao;

import com.sbisec.helios.ap.common.model.Branch;

import org.springframework.stereotype.Component;

@Component
public interface BranchDao {

	public Branch getBranch(String branchCode) throws Exception;

}
