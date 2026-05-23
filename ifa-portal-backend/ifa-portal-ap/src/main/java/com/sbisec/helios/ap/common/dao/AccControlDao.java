package com.sbisec.helios.ap.common.dao;

import java.util.List;

import com.sbisec.helios.ap.common.model.AccControl;


public interface AccControlDao {

	public List<AccControl> getAccControl(String userId) throws Exception;

}
