package com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 画面ID：SUB0501_01-01
 * 画面名：情報登録照会
 *
 * @author SCSK今井
 2024/05/23 新規作成
 */
@Data
public class IfaInfoRegisterLookupA009ApiRequest {
    
    /** お知らせID（数字）. */
    @NotEmpty(message = "お知らせID")
    @Pattern(regexp = "0-9", message = "お知らせID")
    @Size(max = 10, message = "お知らせID")
    private String notificationId;
    
    /** 添付ファイル１. */
    @NotEmpty(message = "添付ファイル１")
    private String attachFile1;
    
    /** 添付ファイル２. */
    @NotEmpty(message = "添付ファイル２")
    private String attachFile2;
    
    /** 添付ファイル３. */
    @NotEmpty(message = "添付ファイル３")
    private String attachFile3;
    
}
