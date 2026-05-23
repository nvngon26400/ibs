package com.sbisec.helios.ap.common.util;

import java.text.MessageFormat;

import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.dto.CheckApiResultDto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * ApiWrapperUtil
 *
 * @author 松田
 */
@Component
public class ApiWrapperUtil {
    
    /** APIレスポンス 種別：正常 */
    private static final String API_RESPONSE_SHUBETU_SUCCESS = String.format("%5s", StringUtil.EMPTY_STRING);
    
    /** APIレスポンス 種別：APL */
    private static final String API_RESPONSE_SHUBETU_APL = String.format("%-5s", "APL");
    
    /** APIレスポンス コード：ワーニング判定文字 */
    private static final String API_RESPONSE_CODE_WARNING_TOP_STR = "W";
    
    /** API処理結果　エラー時メッセージID */
    private static final String API_RESULT_MESSAGE_ID_ERROR = "errors.cmn.apiError";
    
    /** API処理結果　ワーニング時メッセージID */
    private static final String API_RESULT_MESSAGE_ID_WARNING = "warnings.cmn.apiWarning";
    
    /**
     * API取得結果からエラー判定結果を取得する.
     *
     * @param shubetu 種別
     * @param code エラーコード
     * @param message エラーメッセージ
     * @return エラー判定結果
     */
    public CheckApiResultDto getApiResponseResult(String shubetu, String code, String message) {
        
        CheckApiResultDto result = new CheckApiResultDto();
        
        if (StringUtils.equals(shubetu, API_RESPONSE_SHUBETU_SUCCESS)) {
            // 正常終了
            result.setErrorLevel(ErrorLevel.SUCCESS);
            result.setMessageId(ErrorLevel.SUCCESS.toString());
            result.setMessage(null);
        } else if (StringUtils.equals(shubetu, API_RESPONSE_SHUBETU_APL)
                && code.startsWith(API_RESPONSE_CODE_WARNING_TOP_STR)) {
            // 警告
            result.setErrorLevel(ErrorLevel.WARNING);
            result.setMessageId(API_RESULT_MESSAGE_ID_WARNING);
            result.setMessage(MessageFormat.format("{0}({1})", StringUtil.trim(message), code));
        } else {
            // 異常終了
            result.setErrorLevel(ErrorLevel.FATAL);
            result.setMessageId(API_RESULT_MESSAGE_ID_ERROR);
            result.setMessage(MessageFormat.format("{0}({1})", StringUtil.trim(message), code));
        }
        return result;
    }
    
    /**
     * API取得結果からエラーか判定する.
     *
     * @param shubetu 種別
     * @param code エラーコード
     * @param message エラーメッセージ
     * @return API取得結果がエラーの場合、trueを返却する。正常・ワーニングの場合、falseを返却する。
     */
    public boolean isError(String shubetu, String code, String message) {
        
        return ErrorLevel.FATAL.equals(getApiResponseResult(shubetu, code, message).getErrorLevel());
    }
}
