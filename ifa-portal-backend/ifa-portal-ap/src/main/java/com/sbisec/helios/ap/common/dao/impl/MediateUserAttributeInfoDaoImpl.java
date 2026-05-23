package com.sbisec.helios.ap.common.dao.impl;

import com.sbisec.helios.ap.common.dao.MediateUserAttributeInfoDao;
import com.sbisec.helios.ap.common.dao.mapper.MediateUserAttributeInfoMapper;
import com.sbisec.helios.ap.common.model.MediateUserAttributeInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MediateUserAttributeInfoDaoImpl implements MediateUserAttributeInfoDao{

	private static final Logger logger = LoggerFactory.getLogger(MediateUserAttributeInfoDaoImpl.class);

	@Autowired
	protected MediateUserAttributeInfoMapper mapper;

	@Override
	public MediateUserAttributeInfo getMediateUserAttributeInfo(String butenCode, String accountNumber) throws Exception {
		return mapper.getMediateUserAttributeInfo(butenCode, accountNumber);
	}
}
