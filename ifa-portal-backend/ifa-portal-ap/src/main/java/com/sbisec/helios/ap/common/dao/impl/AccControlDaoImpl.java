package com.sbisec.helios.ap.common.dao.impl;

import java.util.List;

import com.sbisec.helios.ap.common.dao.AccControlDao;
import com.sbisec.helios.ap.common.dao.mapper.AccControlMapper;
import com.sbisec.helios.ap.common.model.AccControl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccControlDaoImpl implements AccControlDao {
	private static final Logger logger = LoggerFactory.getLogger(AccControlDaoImpl.class);

	@Autowired
	protected AccControlMapper mapper;

	@Override
	public List<AccControl> getAccControl(String userId) throws Exception {
		return mapper.getAccControl(userId);
	}

}
