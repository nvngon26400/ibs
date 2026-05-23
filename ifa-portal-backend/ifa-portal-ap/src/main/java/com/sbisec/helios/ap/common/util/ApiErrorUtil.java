package com.sbisec.helios.ap.common.util;

import java.util.List;
import java.util.ArrayList;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.dto.CheckApiResultDto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.exception.ApiError;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;
import java.util.stream.Collectors;
import java.util.Collections;

/**
 * ApiErrorUtil
 * 以下のユーティリティ機能を提供
 *　 ApiWrapper（NRI-API）のレスポンスDTOの"shubetu","code"を元にエラー判定
 *  HeracrossApiWrapperのレスポンスDTOの
 *　 画面出力用のメッセージをストア
 *　 レスポンスのDataList作成
 * @author SCSK
 */

@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ApiErrorUtil {
    
    /** APIレスポンス 種別：正常 */
    private static final String API_RESPONSE_SHUBETU_SUCCESS = String.format("%5s", StringUtil.EMPTY_STRING);
    
    /** APIレスポンス 種別：DB */
    private static final String API_RESPONSE_SHUBETU_DB = String.format("%-5s", "ORA");
    
    /** APIレスポンス 種別：MIDDLEWARE */
    private static final String API_RESPONSE_SHUBETU_MW = String.format("%-5s", "MDL");
    
    /** APIレスポンス コード：ワーニング判定文字 */
    private static final String API_RESPONSE_CODE_WARNING_TOP_STR = "W";
    
    /** APIレスポンス コード：エラー判定文字 */
    private static final String API_RESPONSE_CODE_ERROR_TOP_STR = "E";
    
    /** API処理結果　エラー時メッセージID */
    public static final String API_RESULT_MESSAGE_ID_ERROR = "errors.cmn.apiError";
    
    /** API処理結果　ワーニング時メッセージID */
    public static final String API_RESULT_MESSAGE_ID_WARNING = "warnings.cmn.apiWarning";
    
    /** HeracrossAPI処理結果　エラー時メッセージID */
    public static final String API_RESULT_MESSAGE_ID_HERA_ERROR = "errors.cmn.HeracrossApiError";
    
    /** HeracrossAPI処理結果　ワーニング時メッセージID */
    public static final String API_RESULT_MESSAGE_ID_HERA_WARNING = "warnings.cmn.HeracrossApiWarning";
    
    
    
    /** APIワーニング時メッセージ区切り文字 */
    public static final String SEPARETER_STRING = "<sep>";

    /**
     * エラーメッセージストア用リスト
     */
    private final List<CheckApiResultDto> errors = new ArrayList<>();

    /**
     * WARNING Message Idを出力する（WarningメッセージID）
     */
    public String getWarningMessageId() {
        // メッセージIDの適用優先度が最優先のものを検索する
        String alwaysMessageId = errors.stream()
                .filter(dto -> dto.getMessagePriority() == CheckApiResultDto.MessagePriority.ALWAYS)
                .findFirst()
                .map(dto -> dto.getMessageId())
                .orElse(null);

        if (alwaysMessageId != null) {
            return alwaysMessageId;
        }

        // メッセージIDの適用優先度がALWAYS_IF_NO_OTHER_ERRORSかつ、エラーが1件のみの場合
        if (
                errors.size() == 1 
                && errors.get(0).getMessagePriority() == CheckApiResultDto.MessagePriority.ALWAYS_IF_NO_OTHER_ERRORS
        ) {
            return errors.get(0).getMessageId();
        }

        // デフォルトのメッセージID
     	return API_RESULT_MESSAGE_ID_WARNING;
    }
    
    /**
     * FATAL Message Idを出力する（FatalエラーメッセージID）
     * @return
     */
    public String getFatalMessageId() {
    	return API_RESULT_MESSAGE_ID_ERROR;
    }
    
    /**
     * API取得結果からエラー判定を行いメッセージを格納する
     *
     * @param shubetu 種別
     * @param code エラーコード
     * @param message エラーメッセージ
     */
    public void checkApiResponse(String shubetu, String code, String message) {
        
        getApiResponseResult(shubetu,code,message);
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
    
    /**
     * NRI API取得結果からエラー判定結果を取得する.結果をリストに格納する。
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
        } else if (StringUtils.equals(shubetu, API_RESPONSE_SHUBETU_DB) ||
        		   StringUtils.equals(shubetu, API_RESPONSE_SHUBETU_MW) ||
                   code.startsWith(API_RESPONSE_CODE_ERROR_TOP_STR)) {
            // 異常終了
            result.setErrorLevel(ErrorLevel.FATAL);
            result.setMessageId(API_RESULT_MESSAGE_ID_ERROR);
            result.setCode(StringUtil.trim(code));
            result.setConvertedMessage(IfaCommonUtil.getMessage(API_RESULT_MESSAGE_ID_ERROR, new String[] {StringUtil.trim(message), StringUtil.trim(code)}));
            result.setMessage(StringUtil.trim(message));
            //メッセージストア
            addError(result);            
        } else if (code.startsWith(API_RESPONSE_CODE_WARNING_TOP_STR)){
            // 警告
            result.setErrorLevel(ErrorLevel.WARNING);
            result.setMessageId(API_RESULT_MESSAGE_ID_WARNING);
            result.setCode(code);
            result.setConvertedMessage(IfaCommonUtil.getMessage(API_RESULT_MESSAGE_ID_WARNING, new String[] {StringUtil.trim(message), StringUtil.trim(code)}));
            result.setMessage(StringUtil.trim(message));
            //メッセージストア
            addError(result);
        } else {
        	// None
        }
        // CheckApiResultDtoを返却
        return result;
    }

    /**
     * Heracross API取得結果からエラー判定結果を取得する.結果をリストに格納する。
     *　@param returnCode
     * @param messageCode
	 * @param messageText
     * @return エラー判定結果
     */
    public CheckApiResultDto getHeracrossApiResponseResult(Integer returnCode, String messageCode, String messageText) {
        
        CheckApiResultDto result = new CheckApiResultDto();

        //returnCodeを参照しエラー判定
        switch (returnCode){
        case 0:
        	//　正常
        	break;
        case 1:
            // 警告
            result.setErrorLevel(ErrorLevel.WARNING);
            result.setMessageId(API_RESULT_MESSAGE_ID_HERA_WARNING);
            result.setCode(messageCode);
            result.setConvertedMessage(IfaCommonUtil.getMessage(API_RESULT_MESSAGE_ID_HERA_WARNING, new String[] {StringUtil.trim(messageCode)}));
            result.setMessage(StringUtil.trim(messageText));
            //メッセージストア
            addError(result); 
        	break;
        case -1:
            result.setErrorLevel(ErrorLevel.FATAL);
            result.setMessageId(API_RESULT_MESSAGE_ID_HERA_ERROR);
            result.setCode(StringUtil.trim(messageCode));
            result.setConvertedMessage(IfaCommonUtil.getMessage(API_RESULT_MESSAGE_ID_HERA_ERROR, new String[] {StringUtil.trim(messageCode)}));
            result.setMessage(StringUtil.trim(messageText));
            //メッセージストア
            addError(result); 
        	throw new ApiError(ErrorLevel.SYSTEM_ERROR,"-1", "SYSTEMERROR");
        default :
        	break;
        }
        // CheckApiResultDtoを返却
        return result;
    }
    
    /**
     * APIが正常終了しているか判定する
     * @return
     */
    public boolean isSuccess() {
        
        return errors.isEmpty();
    }

    /**
     * APIシステムエラーが発生しているか判定する
     * @return
     */
    public boolean isFatal() {
        return errors.stream() // リストをストリームに変換
                .anyMatch(dto -> dto.getMessage() != null && ErrorLevel.FATAL.equals(dto.getErrorLevel()));
    }
    
    /**
     * APIワーニングが発生しているか判定する（Fatalエラーありの時はFalse）
     * @return
     */
    public boolean isWarn() {
    	if (isFatal()) {
    		return false;
    	}
        return errors.stream() // リストをストリームに変換
                .anyMatch(dto -> dto.getMessage() != null && ErrorLevel.WARNING.equals(dto.getErrorLevel()));
    }
    
    /**
     * エラーメッセージをリストに追加
     * @param dto
     */
    public void addError(CheckApiResultDto dto) {
        errors.add(dto);
    }

    /**
     * DBエラー発生時のメッセージをリストに格納する(エラーレベルはWARNING固定)
     *
     * @param ifaMessageId メッセージID
     * @param messageParams メッセージパラメータ
     */
    public void addDbError(String ifaMessageId, String[] messageParams) {
        CheckApiResultDto result = new CheckApiResultDto();

        result.setErrorLevel(ErrorLevel.WARNING);
        result.setMessageId(ifaMessageId);
        result.setMessagePriority(CheckApiResultDto.MessagePriority.ALWAYS_IF_NO_OTHER_ERRORS);
        result.setCode("");
        result.setConvertedMessage(IfaCommonUtil.getMessage(ifaMessageId, messageParams));
        result.setMessage(IfaCommonUtil.getMessage(ifaMessageId, messageParams));

        //メッセージストア
        errors.add(result);
    }
    
    /**
     * POM APIエラー発生時のメッセージをリストに格納する(エラーレベルはWARNING固定)
     * @param ifaMessageId メッセージID
     * @param message メッセージパラメータ
     */
    public void addPomApiError(String ifaMessageId, String message) {
        CheckApiResultDto result = new CheckApiResultDto();

        result.setErrorLevel(ErrorLevel.WARNING);
        result.setMessageId(ifaMessageId);
        result.setMessagePriority(CheckApiResultDto.MessagePriority.ALWAYS_IF_NO_OTHER_ERRORS);
        result.setCode("");
        result.setConvertedMessage(message);
        result.setMessage(message);

        //メッセージストア
        errors.add(result);
    }

    /**
     * エラーメッセージクリア
     */
    public void clearErrors() {
        errors.clear();
    }
    
    /**
     * エラーメッセージ取得（リスト形式）
     * @return
     */
    public List<CheckApiResultDto> getErrors() {
        return Collections.unmodifiableList(errors);
    }
    
    /**
     * 編集済みエラーメッセージリストを区切り文字付きで出力する（Warningメッセージのみ.String形式）
     * @return 
     */
    public String getWarningMessages() {
    	return errors.stream()
    			.filter(dto -> ErrorLevel.WARNING.equals(dto.getErrorLevel()))
                .map(CheckApiResultDto::getConvertedMessage) // messageフィールドを取得
                .collect(Collectors.joining(SEPARETER_STRING));
    }
       
    /**
     * Fatal Messageを取得（リストの最後から1件目のFatalエラー時の編集ずみエラーメッセージを取得）
     * @return
     */
    public CheckApiResultDto getFatalResult() {
        if (errors != null && !errors.isEmpty()) {
            // リストを逆順にサーチ
            for (int i = errors.size() - 1; i >= 0; i--) {
            	CheckApiResultDto error = errors.get(i);
                if (ErrorLevel.FATAL.equals(error.getErrorLevel())) {
                    return error;
                }
            }
        }
        //Fatalがない場合はNULL
        return null; 
    }
    
    /**
     * ＤａｔａＬｉｓｔ生成（正常、ワーニング、エラーのパターン毎に生成）
     * @param <T>
     * @param resDto
     * @param customMessageId
     * @return
     */
    public <T> DataList<T> createDataList(List<T> resDto, String customMessageId) {
    	String fatalMessageId = "";
    	String fatalMessage = "";
    	// 引数でメッセージＩＤが指定されている場合は設定、指定がない場合はデフォルト値を設定
    	if (!StringUtils.isEmpty(customMessageId) && this.isFatal()) {
    		//カスタムメッセージ
    		fatalMessageId = customMessageId;
    		//message Id, message の順でメッセージを編集
    		fatalMessage   = IfaCommonUtil.getMessage(customMessageId, new String[] {StringUtil.trim(getFatalResult().getCode()), StringUtil.trim(getFatalResult().getMessage())});
    	} else if (this.isFatal()){
    		//デフォルトメッセージ
    		fatalMessageId = getFatalMessageId();
    		fatalMessage   = getFatalResult().getConvertedMessage();
    	}
    	
    	// DataListを生成・リターン
        if (this.isSuccess()) {
        	return IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        } else if(this.isWarn()) {
        	return IfaCommonUtil.createDataList(resDto, ErrorLevel.WARNING, getWarningMessageId(), getWarningMessages());
        } else {
        	return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, fatalMessageId, fatalMessage);
        }
    	
    }
}
