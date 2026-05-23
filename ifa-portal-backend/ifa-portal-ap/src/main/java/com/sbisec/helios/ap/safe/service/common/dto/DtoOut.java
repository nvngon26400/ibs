package com.sbisec.helios.ap.safe.service.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sbisec.helios.ap.safe.common.exception.dto.ErrorInfo;
import com.sbisec.helios.ap.safe.service.common.enums.ServiceResult;

/**
 * ServiceのOutputのDtoベース
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DtoOut {
    /** 結果コード */
    private ServiceResult result = ServiceResult.SUCCESS;
    /** エラー情報 */
    private ErrorInfo errorInfo;

    public ServiceResult getResult() {
        return result;
    }

    public void setResult(ServiceResult result) {
        this.result = result;
    }

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }
}
