package com.sbisec.helios.ap.safe.dao.model;

import lombok.Data;


/**
 * @author rongsheng.he
 *
 */
@Data
public class IfaSafeErrorMessageSql001RequestModel {
    
    /** エラーコード */
    private String errorCode;
    /** エラーレベル */
    private String errorLevel;
    /** エラー種別  */
    private String serviceType;
    
}