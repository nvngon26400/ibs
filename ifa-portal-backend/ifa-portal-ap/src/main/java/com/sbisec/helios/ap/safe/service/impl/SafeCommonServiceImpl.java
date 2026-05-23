package com.sbisec.helios.ap.safe.service.impl;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.safe.common.exception.dto.ErrorInfo;
import com.sbisec.helios.ap.safe.common.exception.enums.ErrorLevel;
import com.sbisec.helios.ap.safe.common.exception.enums.MessageProvideType;
import com.sbisec.helios.ap.safe.common.exception.enums.ServiceType;
import com.sbisec.helios.ap.safe.dao.IfaSafeErrorMessageListDao;
import com.sbisec.helios.ap.safe.dao.model.IfaSafeErrorMessageSql001RequestModel;
import com.sbisec.helios.ap.safe.dao.model.IfaSafeErrorMessageSql001ResponseModel;
import com.sbisec.helios.ap.safe.service.SafeCommonService;
import com.sbisec.helios.ap.safe.service.common.dto.DtoOut;
import com.sbisec.helios.ap.safe.utils.SafeBusinessException;

/**
 * [共通] SafeAPIの共通Serviceクラス
 * @author rongsheng.he
 */
@Component(value = "safeCommonService")
public class SafeCommonServiceImpl implements SafeCommonService {

    private static String WARNINGS_SAFE_APIWARNING = "warnings.safe.apiWarning";
    private static String ERRORS_SAFE_APIERROR = "errors.safe.apiError";
    private static String ERRORS_SAFE_APIERROR_MESSAGE_RETRIEVAL_FAILED = "errors.safe.apiErrorMessageRetrievalFailed";
    private static String WARNINGS_SAFE_APIWARNING_MESSAGE_RETRIEVAL_FAILED = "warnings.safe.apiWarningMessageRetrievalFailed";
    private static String ERRORS_SAFE_API_SERVICE_UNAVAILABLE = "errors.safe.APIServiceUnavailable";
    private static String ERRORS_SAFE_API_CONNECTION_FAILED = "errors.safe.APIConnectionFailed";
    private static String ERRORS_SAFE_API_INTERNET_TRAFFIC_LIMIT = "errors.safe.APIInternetTrafficLimit";

    /** 正常終了のリターンコード  */
    private static final String RETURN_CODE_SUCCESS = "0";

    /** 正常終了のメッセージ  */
    private static final String RETURN_MESSEAGE_SUCCESS = "";

    /** APIワーニング時メッセージ区切り文字 */
    public static final String SEPARETER_STRING = "<sep>";

    private static final Logger LOGGER = LoggerFactory.getLogger(SafeCommonServiceImpl.class);


    /** SafeAPIの共通daoクラス */
    @Autowired
    private IfaSafeErrorMessageListDao safeDao;
    
    /**
     * Safeエラーコードとエラーメッセージを取得する
     * 
     * @param apiErrorCode APIエラーコード errorLevel エラーレベル
     * @return IfaSafeErrorMessageSql001ResponseModel モデル
     * @throws Exception 異常
     */
    @Override
    public IfaSafeErrorMessageSql001ResponseModel getSafeErrorCodeAndMessage(String apiErrorCode, ErrorLevel errorLevel, ServiceType serviceType) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("SafeCommonServiceImpl.getSafeErrorCodeAndMessage");
        }

        IfaSafeErrorMessageSql001RequestModel req = new IfaSafeErrorMessageSql001RequestModel();
        req.setErrorCode(apiErrorCode);
        req.setErrorLevel(String.valueOf(errorLevel.getId()));
        req.setServiceType(serviceType.getId());

        return safeDao.selectIfaSafeErrorMessageSql001(req);
    }

    /**
     * Safe例外からエラーレベル、リターンコード、メッセージをDataListに設定する
     * （ErrorLevel はFATAL,リターンコードはSAFE_API_ERROR_CODE,メッセージはSAFE_ERROR_MESSAGE_MSTから取得し変換）
     * 
     * @param DataList
     * @param Exception
     * @return DataList
     * @throws Exception 異常
     */
    @Override
    public <T> DataList<T> checkSafeBussinessException(DataList<T> dataList, Exception ex, String... customMessageId) throws Exception {

        String messageId = (customMessageId.length > 0) ? customMessageId[0] : ERRORS_SAFE_APIERROR;// 可変引数でメッセージ指定

        if (ex instanceof SafeBusinessException) {
            LOGGER.info("SafeBusinessException is occured.");

            String errorCode = ((SafeBusinessException) ex).getErrorCode();
            String message = ((SafeBusinessException) ex).getMessage();
            ErrorLevel errorLevel = ((SafeBusinessException) ex).getErrorLevel();
            ServiceType serviceType = ((SafeBusinessException) ex).getServiceType();

            // error_info.codeに、エラーコードが設定されて返却されるため、DB上でメッセージへの変換を行い。
            if (StringUtil.isNullOrEmpty(message)) {

                if (!StringUtil.isNullOrEmpty(errorCode)) {
                    IfaSafeErrorMessageSql001ResponseModel model = getSafeErrorCodeAndMessage(errorCode, errorLevel, serviceType);

                    if (model == null) {
                        // APIエラーが発生しました。{0}
                        dataList.setMessage(IfaCommonUtil.getMessage(ERRORS_SAFE_APIERROR_MESSAGE_RETRIEVAL_FAILED, new String[] { errorCode }));
                        dataList.setReturnCode(ERRORS_SAFE_APIERROR_MESSAGE_RETRIEVAL_FAILED);
                    } else {
                        // message = model.getErrorMessage();
                        dataList.setMessage(IfaCommonUtil.getMessage(messageId, new String[] { errorCode, model.getErrorMessage() }));
                        dataList.setReturnCode(messageId);
                    }
                } else {
                    // APIエラーが発生しました。{0}
                    dataList.setMessage(IfaCommonUtil.getMessage(ERRORS_SAFE_APIERROR_MESSAGE_RETRIEVAL_FAILED, new String[] { "" }));
                    dataList.setReturnCode(ERRORS_SAFE_APIERROR_MESSAGE_RETRIEVAL_FAILED);
                }
            } else {
                dataList.setMessage(message);
                //dataListのReturnCode：　なし
            }
        } else {
            LOGGER.info("SafeException is occured.", ex.getMessage());

            if ("Api Service Unavailable".equals(ex.getMessage())) {
                dataList.setMessage(IfaCommonUtil.getMessage(ERRORS_SAFE_API_SERVICE_UNAVAILABLE, new String[] {}));
                dataList.setReturnCode(ERRORS_SAFE_API_SERVICE_UNAVAILABLE);
            } else if ("Api Internet Traffic Limit".equals(ex.getMessage())) {
                dataList.setMessage(IfaCommonUtil.getMessage(ERRORS_SAFE_API_INTERNET_TRAFFIC_LIMIT, new String[] {}));
                dataList.setReturnCode(ERRORS_SAFE_API_INTERNET_TRAFFIC_LIMIT);
            } else {
                dataList.setMessage(IfaCommonUtil.getMessage(ERRORS_SAFE_API_CONNECTION_FAILED, new String[] {}));
                dataList.setReturnCode(ERRORS_SAFE_API_CONNECTION_FAILED);
            }
        }
        dataList.setRequestedTime(IfaCommonUtil.getFormattedRequestedTime());
        dataList.setErrorLevel(com.sbisec.helios.ap.common.enums.ErrorLevel.FATAL.getId());
        return dataList;
    }

    /**
     * Safe例外からエラーレベル、リターンコード、メッセージをDataListに設定する
     * （ErrorLevel はFATAL,リターンコードはSAFE_API_ERROR_CODE,メッセージはSAFE_ERROR_MESSAGE_MSTから取得し変換）
     * 
     * @param DataList
     * @param Exception
     * @return DataList
     * @throws Exception 異常
     */
    @Override
    public <T> DataList<T> checkSafeBussinessException(List<T> resDtoList, Exception ex, String... customMessageId) throws Exception {

        DataList<T> dataList = IfaCommonUtil.createDataList(resDtoList,
                com.sbisec.helios.ap.common.enums.ErrorLevel.FATAL, null, null);

        return checkSafeBussinessException(dataList, ex, customMessageId);
    }

    /**
     * Safeワーニングからエラーレベル、リターンコード、メッセージをDataListに設定する
     * ErrorLevel はWarning場合,ワーニングメッセージを取得し、ワーニングコードがあれば、メッセージに変換します
     * @param <T>
     * @param dataList
     * @param obj
     * @return
     * @throws Exception
     */
    @Override
    public <T> DataList<T> checkSafeBussinessWarning(DataList<T> dataList, DtoOut dto) throws Exception {

        ErrorInfo errorInfo = dto.getErrorInfo();

        if(errorInfo != null && ErrorLevel.WARNING.equals(errorInfo.getErrorLevel())) {
            String beforeMessage = Objects.requireNonNullElse(dataList.getMessage(), "");
            String currentMessage = null;

            if (MessageProvideType.MESSAGE.equals(errorInfo.getMessageProvideType())) {
                currentMessage = Objects.requireNonNullElse(errorInfo.getMessage(), "");
            } else {

                String currentCode = Objects.requireNonNullElse(errorInfo.getCode(), "");
                // MessageProvideType = CODE
                if (!"".equals(currentCode)) {
                    //メッセージを変換
                    IfaSafeErrorMessageSql001ResponseModel model = getSafeErrorCodeAndMessage(currentCode, ErrorLevel.WARNING, dto.getErrorInfo().getServiceType());

                    if (model == null) {
                        currentMessage = IfaCommonUtil.getMessage(WARNINGS_SAFE_APIWARNING_MESSAGE_RETRIEVAL_FAILED, new String[] { currentCode });
                    } else {
                        currentMessage = IfaCommonUtil.getMessage(WARNINGS_SAFE_APIWARNING, new String[] { currentCode, model.getErrorMessage() });
                    }
                } else {
                    currentMessage = IfaCommonUtil.getMessage(WARNINGS_SAFE_APIWARNING_MESSAGE_RETRIEVAL_FAILED, new String[] { "" });
                }
            }

            String mergeWarningMsgs = null;
            String messageId = null;

            if (!StringUtil.isNullOrEmpty(beforeMessage) && !StringUtil.isNullOrEmpty(currentMessage)) {
                mergeWarningMsgs = String.join(SEPARETER_STRING, beforeMessage, currentMessage);
            } else if (!StringUtil.isNullOrEmpty(beforeMessage) && StringUtil.isNullOrEmpty(currentMessage)) {
                // 元ワーニングありの場合
                return dataList;
            } else if (StringUtil.isNullOrEmpty(beforeMessage) && !StringUtil.isNullOrEmpty(currentMessage)) {
                mergeWarningMsgs = currentMessage;
            } else if (StringUtil.isNullOrEmpty(beforeMessage) && StringUtil.isNullOrEmpty(currentMessage)) {
                mergeWarningMsgs = IfaCommonUtil.getMessage(WARNINGS_SAFE_APIWARNING_MESSAGE_RETRIEVAL_FAILED, new String[] { "" });
                messageId = WARNINGS_SAFE_APIWARNING_MESSAGE_RETRIEVAL_FAILED;
            }

            dataList.setMessage(mergeWarningMsgs);
            if (messageId != null) {
                dataList.setReturnCode(messageId);
            }
            dataList.setRequestedTime(IfaCommonUtil.getFormattedRequestedTime());
            dataList.setErrorLevel(com.sbisec.helios.ap.common.enums.ErrorLevel.WARNING.getId());
        }

        return dataList;
    }

    /**
     * Safeワーニングからエラーレベル、リターンコード、メッセージをDataListに設定する
     * ErrorLevel はWarning場合,ワーニングメッセージを取得し、ワーニングコードがあれば、メッセージに変換します
     * @param <T>
     * @param resDtoList
     * @param dto
     * @param beforeWarningMessage
     * @return
     * @throws Exception
     */
    public <T> DataList<T> checkSafeBussinessWarning(List<T> resDtoList, DtoOut dto) throws Exception {

        DataList<T> dataList = IfaCommonUtil.createDataList(resDtoList,
                com.sbisec.helios.ap.common.enums.ErrorLevel.SUCCESS, RETURN_CODE_SUCCESS, RETURN_MESSEAGE_SUCCESS);

        return checkSafeBussinessWarning(dataList, dto);
    }
}