package com.sbisec.helios.ap.fasthelp;

import com.sbisec.helios.ap.fasthelp.exception.FastCcsException;
import com.sbisec.helios.ap.fasthelp.service.dto.ccs.CcsFastHelpInfoInsertDoOut;
import com.sbisec.helios.ap.fasthelp.service.dto.fasthelp.FasthelpCcsCallsImportOut;

/**
 * FastCcsOkHttpResponse
 *
 * @author dalian
 * @version 1.0
 * @date 3/31/2025
 */
public class FastCcsOkHttpResponse {

    public FastCcsOkHttpResponse() {
    }

    // リクエスト成功 => true:成功；false:失敗
    private Boolean successful;
    // HTTP状態 => 200:成功
    private Integer statusCode;
    // 応答の結果
    private String responseData;

    public Boolean getSuccessful() {
        return successful;
    }

    public void setSuccessful(Boolean successful) {
        this.successful = successful;
    }

    /**
     * @return the statusCode
     */
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getResponsData() {
        return responseData;
    }

    public CcsFastHelpInfoInsertDoOut getCcsFastHelpResponseData() throws FastCcsException {
        try {
            if (!(this.responseData instanceof String)) {
                throw new FastCcsException("responseData is not a String");
            }
            String responseDataStr = (String) this.responseData;

            if (responseDataStr.length() != 40) { // 18 + 2 + 10 + 10 = 40
                throw new FastCcsException("responseData length is invalid");
            }

            CcsFastHelpInfoInsertDoOut dtoOut = new CcsFastHelpInfoInsertDoOut();
            dtoOut.setCall_id(responseDataStr.substring(0, 18));
            dtoOut.setRtn_cd(responseDataStr.substring(18, 20));
            dtoOut.setRtn_toiawase_no(responseDataStr.substring(20, 30));
            dtoOut.setRtn_kaitou_no(responseDataStr.substring(30, 40));
            return dtoOut;
        } catch (Exception e) {
            throw new FastCcsException(e);
        }
    }
    

    public FasthelpCcsCallsImportOut getFastHelpCcsCallsResponseData() throws FastCcsException {
        try {
            if (!(this.responseData instanceof String)) {
                throw new FastCcsException("responseData is not a String");
            }
            FasthelpCcsCallsImportOut dtoOut = new FasthelpCcsCallsImportOut();
            dtoOut.setRtn_cd(this.responseData);
            return dtoOut;
        } catch (Exception e) {
            throw new FastCcsException(e);
        }
    }

    public void setResponseData(String responseMsg) {
        this.responseData = responseMsg;
    }
}
