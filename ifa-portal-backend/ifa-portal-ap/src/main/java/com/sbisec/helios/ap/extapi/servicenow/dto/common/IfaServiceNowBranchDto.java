package com.sbisec.helios.ap.extapi.servicenow.dto.common;

import lombok.Data;

/**
 * 本店・支店情報 Dto
 *
 * @author SCSK
 *
 */
@Data
public class IfaServiceNowBranchDto {
    
    /** 本支店コード. */
    private String branchCode;
    
    /** 本支店種別. */
    private String branchKind;
    
    /** 本支店名. */
    private String branchName;
    
    /** 作成日時. */
    private String createDate;
    
    /** 作成者. */
    private String createBy;
    
    /** 更新日時. */
    private String updateDate;
    
    /** 更新者. */
    private String updateBy;
    
    /** 削除日時. */
    private String deleteDate;
    
    /** 削除フラグ. */
    private String deleteFlg;
    
}
