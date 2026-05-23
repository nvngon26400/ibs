package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto;

import lombok.Data;

/**
 * 画面ID：SUB0501_01-01
 * 画面名：情報登録照会
 *
 * @author SCSK今井
 2024/05/23 新規作成
 */
@Data
public class IfaInfoRegisterLookupA009RequestDto {
    
    /** お知らせID（数字）. */
    private String notificationId;
    
    /** 添付ファイル１. */
    private String attachFile1;
    
    /** 添付ファイル２. */
    private String attachFile2;
    
    /** 添付ファイル３. */
    private String attachFile3;
    
}
