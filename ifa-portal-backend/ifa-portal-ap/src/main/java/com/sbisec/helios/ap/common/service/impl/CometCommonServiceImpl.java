package com.sbisec.helios.ap.common.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.dao.CometCommonDao;
import com.sbisec.helios.ap.athena.model.AthenaErrorMessageModel;
import com.sbisec.helios.ap.athena.utils.AthenaBusinessException;
import com.sbisec.helios.ap.athena.utils.AthenaException;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

@Component(value = "cometCommonService")
public class CometCommonServiceImpl implements com.sbisec.helios.ap.common.service.CometCommonService {

	
	private static String ERRORS_CMN_APIERROR = "errors.cmn.apiError";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CometCommonServiceImpl.class);
	/** AthenaAPIの共通Daoクラス */
	@Autowired
	private CometCommonDao cometCommonDao;

	
	/**
	 * Athenaエラーコードとエラーメッセージを取得する
	 * 
	 * @param apiErrorCode APIエラーコード
	 * @return AthenaErrorMessageModel モデル
	 * @throws Exception 異常
	 */
	@Override
	public AthenaErrorMessageModel getAthenaErrorCodeAndMessage(String apiErrorCode) throws Exception {
		LOGGER.debug("CometCommonServiceImpl.getAthenaErrorCodeAndMessage");

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("CometCommonServiceImpl.getAthenaErrorCodeAndMessage");
		}
		return cometCommonDao.getAthenaErrorCodeAndMessage(apiErrorCode);

	}

	/**
	 * Athena例外からエラーレベル、リターンコード、メッセージをDataListに設定する
	 * （ErrorLevel はFATAL,リターンコードはATHENA_API_ERROR_CODE,メッセージはATHENA_ERROR_MESSAGE_MSTから取得し変換）
	 * 
	 * @param DataList
	 * @param Exception
	 * @return DataList
	 * @throws Exception 異常
	 */
	@Override
	public <T> DataList<T> checkBussinessException(DataList<T> dataList, Exception exception,  String... customMessageId) throws Exception {
		
		String messageId = (customMessageId.length > 0) ? customMessageId[0] : ERRORS_CMN_APIERROR;//可変引数でメッセージ指定
        if (exception instanceof AthenaBusinessException) {
        	LOGGER.info("AthenaBusinessException is occured.");
            String errorCode = ((AthenaBusinessException) exception).getErrorCode();
            String message = ((AthenaBusinessException) exception).getMessage();
            //dataList.setTitle(errorCode);
            if (!StringUtil.isNullOrEmpty(errorCode)) {
            	if (customMessageId.length == 0) {
            		//メッセージを変換
            		dataList.setMessage(getAthenaErrorCodeAndMessage(errorCode).getErrorMessage());
            	} else {
            		//可変引数でメッセージ指定
            		dataList.setMessage(IfaCommonUtil.getMessage(messageId, new String[] { errorCode, message }));
            	}
            } else {
            	dataList.setMessage(message);
            }
        } else if (exception instanceof AthenaException) {
        	LOGGER.info("AthenaException is occured." ,exception.getMessage());
        	dataList.setMessage(ERRORS_CMN_APIERROR);
       	
		} else{
        	LOGGER.info("Exception is occured.",exception.getMessage());
        	dataList.setMessage(ERRORS_CMN_APIERROR);
        	//AthenaAPI例外以外はスロー
        	throw exception;
        }
        //ErrorLevelはFATAL　リターンコードはメッセージIDを設定
        dataList.setErrorLevel(ErrorLevel.FATAL.getId());
        dataList.setReturnCode(messageId);
        return dataList;
	}

}
