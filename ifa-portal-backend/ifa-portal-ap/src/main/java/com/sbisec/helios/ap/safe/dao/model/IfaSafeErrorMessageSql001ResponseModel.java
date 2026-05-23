package com.sbisec.helios.ap.safe.dao.model;

import lombok.Data;

/**
 * @author rongsheng.he
 *
 */
@Data
public class IfaSafeErrorMessageSql001ResponseModel {
    
    /** エラーコード **/
    private String errorCode;
    /** エラーレベル **/
    private String errorLevel;
    /** エラー種別 **/
    private String errorType;
    /** サービス種別 **/
    private String ServiceType;
    /** エラーメッセージ **/
    private String ErrorMessage;    
}