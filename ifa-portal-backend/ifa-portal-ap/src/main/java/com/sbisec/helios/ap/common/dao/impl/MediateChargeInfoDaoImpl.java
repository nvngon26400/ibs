package com.sbisec.helios.ap.common.dao.impl;

import com.sbisec.helios.ap.common.dao.MediateChargeInfoDao;
import com.sbisec.helios.ap.common.dao.mapper.MediateChargeInfoMapper;
import com.sbisec.helios.ap.common.model.MediateChargeInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MediateChargeInfoDaoImpl implements MediateChargeInfoDao {

	private static final Logger logger = LoggerFactory.getLogger(MediateChargeInfoDaoImpl.class);

	@Autowired
	protected MediateChargeInfoMapper mapper;

	@Override
	public MediateChargeInfo getMediateChargeInfo(String brokerCode, String brokerBranchCode, String brokerChargeCode) throws Exception{
		return mapper.getMediateChargeInfo(brokerCode, brokerBranchCode, brokerChargeCode);
	}
}
