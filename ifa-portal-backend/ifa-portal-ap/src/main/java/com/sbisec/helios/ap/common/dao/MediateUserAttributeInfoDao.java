package com.sbisec.helios.ap.common.dao;

import com.sbisec.helios.ap.common.model.MediateUserAttributeInfo;

import org.springframework.stereotype.Component;

public interface MediateUserAttributeInfoDao {

	public MediateUserAttributeInfo getMediateUserAttributeInfo(String butenCode, String accountNumber) throws Exception;

}
