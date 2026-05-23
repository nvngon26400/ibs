package com.sbisec.helios.ap.athena.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbisec.helios.ap.athena.dao.CometCommonDao;
import com.sbisec.helios.ap.athena.dao.mapper.CometCommonMapper;
import com.sbisec.helios.ap.athena.model.AthenaErrorMessageModel;

@Component(value = "cometCommonDao")
public class CometCommonDaoImpl implements CometCommonDao {
	
	@Autowired
	CometCommonMapper cometCommonMapper;
		
	
	private static final Logger logger = LoggerFactory.getLogger(CometCommonDaoImpl.class);

	@Override
	public AthenaErrorMessageModel getAthenaErrorCodeAndMessage(String apiErrorCode) throws Exception {
		logger.debug("AthenaErrorMessageDaoImpl.getAthenaErrorCodeAndMessage:" + " apiErrorCode：" + apiErrorCode);
		return getAthenaErrorCodeAndMessageInternal(apiErrorCode);

	}


	private AthenaErrorMessageModel getAthenaErrorCodeAndMessageInternal(String apiErrorCode) throws Exception {
		logger.debug(
				"AmericaStockCommonDaoImpl.getAthenaErrorCodeAndMessageInternal:" + " apiErrorCode：" + apiErrorCode);
		AthenaErrorMessageModel athenaErrorMessageModel = cometCommonMapper
				.getAthenaErrorCodeAndMessage(apiErrorCode);
		if (!apiErrorCode.equals("ECOMN9999")) {
			if (null != athenaErrorMessageModel) {
				// 存在する場合、取得するエラーメッセージ（ERROR_MESSAGE）を取得する
				return athenaErrorMessageModel;
			} else {
				athenaErrorMessageModel = cometCommonMapper.getAthenaErrorCodeAndMessage("ECOMN9999");
				// レスポンス電文の"errorCode"をエラーメッセージ内の可変値に設定する
				String errorMessage = athenaErrorMessageModel.getErrorMessage().replace("{0}", apiErrorCode);
				athenaErrorMessageModel.setErrorMessage(errorMessage);
				return athenaErrorMessageModel;
			}
		} else {
			// errorcode=ECOMN9999の場合、エラーメッセージ内の可変値に設定する
			String errorMessage = athenaErrorMessageModel.getErrorMessage().replace("{0}", apiErrorCode);
			athenaErrorMessageModel.setErrorMessage(errorMessage);
			return athenaErrorMessageModel;
		}
	}
}
