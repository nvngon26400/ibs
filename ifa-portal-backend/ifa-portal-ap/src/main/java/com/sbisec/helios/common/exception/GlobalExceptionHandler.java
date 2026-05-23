package com.sbisec.helios.common.exception;

import java.util.Collections;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.util.IfaSubscriptInputException;
import com.sbisec.helios.ap.common.constants.MessagesConstants;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.exception.IfaRuntimeException;
import com.sbisec.helios.ap.common.exception.SystemErrorException;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.athena.utils.AthenaException;
import com.sbisec.helios.ap.athena.utils.AthenaBusinessException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionTimedOutException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import lombok.extern.slf4j.Slf4j;

/**
 * Common handling when service is abnormal.
 *
 * @Organization SBIBITS DaLian CB Group
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    
    /* ロガー */
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    private static String ERRORS_CMN_APIERROR = "errors.cmn.apiError";

    @Autowired
    protected MessageSourceAccessor message;

    
    /**
     * This process occurs when a request timeout exception occurs.
     *
     * @param e exception TransactionTimedOutException
     * @return エラー情報を設定したJSON
     */
    @ExceptionHandler(value = { TransactionTimedOutException.class })
    ResponseEntity<DataList<Object>> handleTimeoutException(Throwable e) {
        
        log.warn(e.getMessage(), e);
        
        return new ResponseEntity<DataList<Object>>(
                IfaCommonUtil.createDtaList(null, ErrorLevel.WARNING, MessagesConstants.W9004), //
                HttpStatus.OK);
    }
    
    /**
     * 個別のハンドリングが不要な例外発生時のハンドラ
     *
     * @param e exception Exception
     * @return エラー情報を設定したJSON
     */
    @ExceptionHandler(value = { Exception.class })
    ResponseEntity<DataList<String>> handleException(Throwable e) {
        
        log.error(e.getMessage(), e);
        logout();
        
        List<String> dataList = IfaCommonUtil.createErrorDataList(e);
        
        return new ResponseEntity<DataList<String>>(
                IfaCommonUtil.createDtaList(dataList, ErrorLevel.SYSTEM_ERROR, "errors.systemError"), //
                HttpStatus.OK);
    }
    
    /**
     * DataListが設定されている例外発生時のハンドラ
     *
     * @param e exception SystemErrorException、IfaRuntimeException
     * @return エラー情報を設定したJSON
     */
    @ExceptionHandler(value = { SystemErrorException.class })
    ResponseEntity<DataList<?>> handleSystemErrorException(SystemErrorException e) {
        
        log.error(e.getMessage(), e);
        logout();
        
        if (e.getDataList() != null) {
            
            return new ResponseEntity<DataList<?>>(e.getDataList(), HttpStatus.OK);
            
        }
        
        List<String> dataList = IfaCommonUtil.createErrorDataList(e);
        
        return new ResponseEntity<DataList<?>>(
                IfaCommonUtil.createDtaList(dataList, ErrorLevel.SYSTEM_ERROR, "errors.systemError"), HttpStatus.OK);
    }
    
    /**
     * メッセージIDが設定されている例外発生時のハンドラ
     *
     * @param e exception IfaRuntimeException
     * @return エラー情報を設定したJSON
     */
    @ExceptionHandler(value = { IfaRuntimeException.class })
    ResponseEntity<DataList<String>> handleIfaRuntimeException(IfaRuntimeException e) {
        
        log.error(e.getMessage(), e);
        logout();
        
        List<String> dataList = IfaCommonUtil.createErrorDataList(e);
        
        return new ResponseEntity<DataList<String>>(
                IfaCommonUtil.createDataList(dataList, ErrorLevel.SYSTEM_ERROR, e.getMessageId(), //
                        e.getMessageWithoutMessageId()),
                HttpStatus.OK);
    }
    
    /**
     * Bean Validation での実行時例外発生時のハンドラ
     * 発生したバリデーションエラーを1つ返却する
     *
     * @param e exception MethodArgumentNotValidException
     * @return エラー情報を設定したJSON
     */
    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    ResponseEntity<DataList<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        
        log.debug(e.getMessage(), e);
        
        String message = "";
        
        FieldError fieldError = e.getBindingResult().getFieldError();
        if ("Size".equals(fieldError.getCode())) {
            // Sizeの場合、エラーメッセージに組み込む日本語項目名、MAX値、MIN値をparamにセットする
            Object[] arguments = fieldError.getArguments();
            Object[] param = new Object[3];
            try {
                if (arguments != null && arguments.length == 3
                        && Integer.parseInt(arguments[1].toString()) == Integer.MAX_VALUE) {
                    // MAX値が未設定（Integer.MAX_VALUEと同じ）である場合、空文字とする
                    arguments[1] = "";
                }
            } catch (Exception ex) {
                // MAX値の変換に失敗した場合、空文字とする
                arguments[1] = "";
            }
            // Size arguments = {code, max, min}
            System.arraycopy(arguments, 1, param, 1, 2);
            param[0] = fieldError.getDefaultMessage();
            message = IfaCommonUtil.getMessage(fieldError.getCode(), param);
        } else {
            // Size以外の場合、エラーメッセージに組み込む日本語項目名をparamにセットする
            String[] param = new String[1];
            param[0] = fieldError.getDefaultMessage();
            message = IfaCommonUtil.getMessage(fieldError.getCode(), param);
        }
        
        return new ResponseEntity<DataList<Object>>(
                IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, fieldError.getCode(), message),
                HttpStatus.OK);
    }
    
    /**
     * AthenaAPIの例外発生時（業務エラー）のハンドラ（共通関数での発生をキャッチ）
     *
     * @param e exception AthenaBusinessException
     * @return エラー情報を設定したJSON
     */
    @ExceptionHandler(value = { AthenaBusinessException.class })
    ResponseEntity<DataList<Object>> handleAthenaBusinessException(AthenaBusinessException e) {
        
        log.error(e.getMessage(), e);
        String message = ((AthenaBusinessException) e).getMessage();

        return new ResponseEntity<DataList<Object>>(
            IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_CMN_APIERROR, message),
            HttpStatus.OK);
    }

    /**
     * AthenaAPIの例外発生時のハンドラ（共通関数での発生をキャッチ）
     *
     * @param e exception AthenaException
     * @return エラー情報を設定したJSON
     */
    @ExceptionHandler(value = { AthenaException.class })
    ResponseEntity<DataList<Object>> handleAthenaException(AthenaException e) {
        
        log.error(e.getMessage(), e);

        return new ResponseEntity<DataList<Object>>(
            IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_CMN_APIERROR, ERRORS_CMN_APIERROR),
            HttpStatus.OK);
    }

    /**
     * ログアウトを行う。環境変数DEBUGが有効な場合、ログアウトしない。
     */
    private void logout() {
        
        // 環境変数DEBUGが有効な場合、ログアウトはしない
        if (!IfaCommonUtil.hasDebugEnvironmentVariable()) {
            
            LOGGER.debug("実行時例外が発生したためログアウト処理を行います。RedisのFWセッション情報は削除されます。");
            
            IfaCommonUtil.logout();
        }
    }
    
    
    /**
     * 募集入力系エラー
     *
     * @param e exception IfaSubscriptInputException
     * @return エラー情報を設定したJSON
     */
    @ExceptionHandler(value = { IfaSubscriptInputException.class })
    ResponseEntity<DataList<?>> handleIfaSubscriptInputException(IfaSubscriptInputException e) {
        
        log.error(e.getMessage(), e);
        
        
        return new ResponseEntity<DataList<?>>(
                IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL, e.getMessageId(), //
                        e.getMessageWithoutMessageId()),
                HttpStatus.OK);
    }
    
    /**
     * サーバ側に必要なメッセージを取得しないため、画面側でメッセージを表示します。
     *
     * @param e ファイルの総サイズが最大サイズを越えています。
     * @return 正常終了のDataList、エラーコードはerrors.processingFailed
     */
    @ExceptionHandler(value = { MaxUploadSizeExceededException.class })
    ResponseEntity<DataList<?>> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        log.debug("ファイルの総サイズが最大サイズを越えています。");
        
        return new ResponseEntity<DataList<?>>(
                IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.SUCCESS, "errors.processingFailed",
                        ""),
                HttpStatus.OK);
    }
    
    
}
