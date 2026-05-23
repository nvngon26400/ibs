package com.sbisec.helios.ap.bizcommon.model;

import lombok.Data;
import com.sbisec.helios.ap.athena.model.AthenaErrorMessageModel;

/**
 * 共通関数アウトプットDTO共通パラメータ
 * @author SCSK
 */

@Data
public abstract class BaseOutputDto {
    
    public static final String MESSAGE_KEY_E001 = "E001";
    
    public static final String MESSAGE_KEY_E002 = "E002";
    
    public static final String MESSAGE_KEY_E003 = "E003";
    
    public static final String MESSAGE_KEY_E004 = "E004";
    
    //返却コードE001
    public static final String RETURN_CODE_E001 = "E001";
    
    //返却コードE002
    public static final String RETURN_CODE_E002 = "E002";
    
    //返却コードE003
    public static final String RETURN_CODE_E003 = "E003";
    
    //返却コードE004
    public static final String RETURN_CODE_E004 = "E004";
    
    //返却コードE005
    public static final String RETURN_CODE_E005 = "E005";
    
    // 返却コード
    protected String returnCode;
    
    // エラーメッセージ
    protected String errMessage;

    // エラー情報
    protected AthenaErrorMessageModel apiStatusModel;
    
}
