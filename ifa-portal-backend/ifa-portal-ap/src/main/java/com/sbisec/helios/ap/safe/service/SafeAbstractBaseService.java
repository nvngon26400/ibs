package com.sbisec.helios.ap.safe.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.safe.SafeOkHttpClientWrapper;
import com.sbisec.helios.ap.safe.SafeOkHttpRequest;
import com.sbisec.helios.ap.safe.SafeOkHttpResponse;
import com.sbisec.helios.ap.safe.common.exception.dto.ErrorInfo;
import com.sbisec.helios.ap.safe.common.exception.enums.ErrorLevel;
import com.sbisec.helios.ap.safe.common.exception.enums.MessageProvideType;
import com.sbisec.helios.ap.safe.common.exception.enums.ServiceType;
import com.sbisec.helios.ap.safe.protocol.SafeBaseRequest;
import com.sbisec.helios.ap.safe.service.common.dto.DtoOut;
import com.sbisec.helios.ap.safe.service.common.enums.ServiceResult;
import com.sbisec.helios.ap.safe.utils.SafeBusinessException;
import com.sbisec.helios.ap.safe.utils.SafeConfig;
import com.sbisec.helios.ap.safe.utils.SafeException;

/**
 * SafeAbstractBaseService.
 * 
 * @author nicksen.li
 * @date 03/31/2025
 */
public abstract class SafeAbstractBaseService {

    private static final Logger LOG = LoggerFactory.getLogger(SafeAbstractBaseService.class);

    protected final String getUrl(String api) {
        if (!StringUtil.isNullOrEmpty(SafeConfig.getSafeEnv())) {
            // local環境指定
            return SafeConfig.getHostProtocol() + "://" + SafeConfig.getHostIp() + ":" + SafeConfig.getHostPort() +"/safe"+ api;
        }
        return SafeConfig.getHostProtocol() + "://" + SafeConfig.getHostIp() + ":" + SafeConfig.getHostPort() +"/api/v1"+ api;
    }

    /**
     * header & parameterの正確性を確認する.
     * 
     * @param token
     * @param parameter
     * @return チェック結果(void)
     * @throws SafeException
     */
    protected final void checkHeaderAndParameter(String token, Object parameter) throws SafeException {
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // 必須入力チェック「token」
            if (StringUtil.isNullOrEmpty(token)) {
                warnMsg = "Token is not exists or empty!";
                break;
            }
            if (parameter == null) {
                warnMsg = "apiIn is not exists or empty!";
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Safe Exception ： " + warnMsg);
            throw new SafeException("Parameter verification failed!");
        }
    }

    /**
     * 
     * @param url API
     * @param req 検索条件</BR>
     *            req.getHeader() 『Header』</BR>
     *            req.getParameter() 『パラメータ』
     * @return SafeOkHttpResponse
     * @throws Exception
     */
    protected final SafeOkHttpResponse post(String url, SafeBaseRequest req) throws Exception {

        // Create Safe's request protocol class
        SafeOkHttpRequest request = new SafeOkHttpRequest();
        // Set URL
        request.setUrl(url);
        // Set request header
        request.setHeaders(req.getHeader());
        // Set request parameters
        request.setParameters(req.getParameter());
        // Execute get request
        SafeOkHttpResponse res = SafeOkHttpClientWrapper.getInstance().post(request);

        // Check Response
        checkSafeResponseException(res);

        // Return response.
        return res;
    }

    /**
     * レスポンスエラー検知をチェックする
     * @param res
     * @throws SafeBusinessException
     * @throws SafeException
     */
    private void checkSafeResponseException(SafeOkHttpResponse res) throws SafeBusinessException,SafeException {

        if (null == res) {
            LOG.warn("Safe api Response is null!");
            throw new SafeException("Response is null!");
        }

        if (res.getSuccessful()) {
            DtoOut dtoOut = res.getResponseData(DtoOut.class);

            // リクエスト結果は異常場合
            if (ServiceResult.FAILURE.equals(dtoOut.getResult())) {
                ErrorInfo errorInfo = dtoOut.getErrorInfo();

                if (errorInfo != null) {

                    if (ErrorLevel.ERROR.equals(errorInfo.getErrorLevel())
                            && MessageProvideType.CODE.equals(errorInfo.getMessageProvideType())) {
                        /**
                         * error_info.messageProvideTypeが「ERROR」の場合、
                         * error_info.codeに、エラーコードが設定されて返却されるため、 DB上でメッセージへの変換を行い、画面に表示する。
                         */
                        setSafeBusinessException(errorInfo.getCode(), null, ErrorLevel.ERROR,
                                errorInfo.getServiceType());
                    } else if (ErrorLevel.ERROR.equals(errorInfo.getErrorLevel())
                            && MessageProvideType.MESSAGE.equals(errorInfo.getMessageProvideType())) {
                        /**
                         * error_info.messageProvideTypeが「MESSAGE」の場合、
                         * error_info.messageに、エラーメッセージが設定されて返却されるため、 返却値をそのまま画面に表示する。
                         */
                        setSafeBusinessException(null, errorInfo.getMessage(), ErrorLevel.ERROR,
                                errorInfo.getServiceType());
                    } else if (ErrorLevel.WARNING.equals(errorInfo.getErrorLevel())) {

                        if (MessageProvideType.MESSAGE.equals(errorInfo.getMessageProvideType())) {
                            /**
                             * error_info.messageProvideTypeが「MESSAGE」の場合、
                             * error_info.messageに、エラーメッセージが設定されて返却されるため、 返却値をそのまま画面に表示する。
                             */
                            setSafeBusinessException(null, errorInfo.getMessage(), ErrorLevel.WARNING,
                                    errorInfo.getServiceType());
                        } else {
                            setSafeBusinessException(errorInfo.getCode(), null, ErrorLevel.WARNING,
                                    errorInfo.getServiceType());
                        }
                    } else {
                        // 想定外の異常(データ不整合)
                        LOG.warn("Safe api Response is error!");
                        throw new SafeException("Response is error!");
                    }
                } else {
                    // 想定外の異常(データ不整合)
                    LOG.warn("Safe api Response is error!");
                    throw new SafeException("Response is error!");
                }
            }

            //正常(ServiceResult.SUCCESSの場合)

        } else {
            // システム異常が発生してしまいましたんです。
            SafeException se = new SafeException();

            if (res.getStatusCode() == 503) {
                LOG.warn("call Api service unavailable.");
                se.setMessage("Api Service Unavailable");
            } else if (res.getStatusCode() == 429) {
                LOG.warn("call Api internet traffic limit.");
                se.setMessage("Api Internet Traffic Limit");
            } else {
                LOG.warn("call Api Connection Failed.");
                se.setMessage("API Connection Failed");
            }

            // システム異常を投げる
            throw se;
        }
    }

    /**
     * ビジネス異常処理
     * 
     * @param code
     * @param message
     * @param errorLevel
     * @throws SafeBusinessException
     */
    private void setSafeBusinessException(String code, String message, ErrorLevel errorLevel, ServiceType serviceType)
            throws SafeBusinessException {

        SafeBusinessException sbe = new SafeBusinessException(message);
        sbe.setErrorCode(code);
        sbe.setMessage(message);
        sbe.setErrorLevel(errorLevel);
        sbe.setServiceType(serviceType);

        throw sbe;
    }
}
