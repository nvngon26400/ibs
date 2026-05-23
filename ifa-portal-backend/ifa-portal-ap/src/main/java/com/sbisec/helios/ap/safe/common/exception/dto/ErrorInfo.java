package com.sbisec.helios.ap.safe.common.exception.dto;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import com.sbisec.helios.ap.safe.common.exception.enums.ErrorLevel;
import com.sbisec.helios.ap.safe.common.exception.enums.ErrorType;
import com.sbisec.helios.ap.safe.common.exception.enums.MessageProvideType;
import com.sbisec.helios.ap.safe.common.exception.enums.ServiceType;

/**
 * エラー情報
 */
public class ErrorInfo {

    /** システム名 */
    private static final String SYSTEM_NAME = "SAFE";
    /** エラーレベル */
    private ErrorLevel errorLevel;
    /** エラーの区分 */
    private ErrorType errorType;
    /** サービス */
    private ServiceType serviceType;
    /** メッセージ提供タイプ */
    private MessageProvideType messageProvideType;
    /** コード */
    private String code;
    /** エラー項目 */
    private String errorItem;
    /** エラーメッセージ */
    private String message;

    /**
     * <code>SAFE.ERROR.SYSTEM.COMMON.9999</code> の形式のエラーコードを生成する
     * @param defaultCode コードを生成できなかった場合のデフォルトのコード
     * @return エラーコード
     */
    public String buildErrorCode(String defaultCode) {
        String errorCode = defaultCode;
        if (ObjectUtils.allNotNull(errorLevel, errorType, serviceType) && StringUtils.isNotBlank(code)) {
            errorCode = String.join(".",
                    SYSTEM_NAME,
                    errorLevel.name(),
                    errorType.name(),
                    serviceType.name(),
                    code);
        }
        return errorCode;
    }

    public String getSystemName() {
        return SYSTEM_NAME;
    }

    public ErrorLevel getErrorLevel() {
        return errorLevel;
    }

    public void setErrorLevel(final ErrorLevel errorLevel) {
        this.errorLevel = errorLevel;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(final ErrorType errorType) {
        this.errorType = errorType;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(final ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public MessageProvideType getMessageProvideType() {
        return messageProvideType;
    }

    public void setMessageProvideType(final MessageProvideType messageProvideType) {
        this.messageProvideType = messageProvideType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getErrorItem() {
        return errorItem;
    }

    public void setErrorItem(final String errorItem) {
        this.errorItem = errorItem;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * builder
     */
    public static final class ErrorInfoBuilder {
        /** エラーレベル */
        private ErrorLevel errorLevel;
        /** エラーの区分 */
        private ErrorType errorType;
        /** サービス */
        private ServiceType serviceType;
        /** メッセージ提供タイプ */
        private MessageProvideType messageProvideType;
        /** コード */
        private String code;
        /** エラー項目 */
        private String errorItem;
        /** エラーメッセージ */
        private String message;

        /**
         * コンストラクタ
         */
        private ErrorInfoBuilder() {
        }

        /**
         * Builderのインスタンスを生成
         * @return builder
         */
        public static ErrorInfoBuilder anErrorInfo() {
            return new ErrorInfoBuilder();
        }

        /**
         * errorLevel をセット
         * @param errorLevel エラーレベル
         * @return builder
         */
        public ErrorInfoBuilder errorLevel(final ErrorLevel errorLevel) {
            this.errorLevel = errorLevel;
            return this;
        }

        /**
         * errorType をセット
         * @param errorType エラーの区分
         * @return builder
         */
        public ErrorInfoBuilder errorType(final ErrorType errorType) {
            this.errorType = errorType;
            return this;
        }

        /**
         * serviceType をセット
         * @param serviceType サービス
         * @return builder
         */
        public ErrorInfoBuilder serviceType(final ServiceType serviceType) {
            this.serviceType = serviceType;
            return this;
        }

        /**
         * messageProvideType をセット
         * @param messageProvideType メッセージ提供タイプ
         * @return builder
         */
        public ErrorInfoBuilder messageProvideType(final MessageProvideType messageProvideType) {
            this.messageProvideType = messageProvideType;
            return this;
        }

        /**
         * code をセット
         * @param code コード
         * @return builder
         */
        public ErrorInfoBuilder code(final String code) {
            this.code = code;
            return this;
        }

        /**
         * errorItem をセット
         * @param errorItem エラー項目
         * @return builder
         */
        public ErrorInfoBuilder errorItem(final String errorItem) {
            this.errorItem = errorItem;
            return this;
        }

        /**
         * message をセット
         * @param message エラーメッセージ
         * @return builder
         */
        public ErrorInfoBuilder message(final String message) {
            this.message = message;
            return this;
        }

        /**
         * ErrorInfoのインスタンスを生成する
         * @return ErrorInfo
         */
        public ErrorInfo build() {
            final ErrorInfo errorInfo = new ErrorInfo();
            errorInfo.setErrorLevel(errorLevel);
            errorInfo.setErrorType(errorType);
            errorInfo.setServiceType(serviceType);
            errorInfo.setCode(code);
            errorInfo.setMessageProvideType(messageProvideType);
            errorInfo.setErrorItem(errorItem);
            errorInfo.setMessage(message);
            return errorInfo;
        }
    }
}
