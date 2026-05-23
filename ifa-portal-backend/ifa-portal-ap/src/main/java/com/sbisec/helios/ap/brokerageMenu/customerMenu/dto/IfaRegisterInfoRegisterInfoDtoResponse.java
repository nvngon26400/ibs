package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 登録情報
 * 2025/02/21 新規作成
 *
 * @author 大連 苗
 */
@Data
public class IfaRegisterInfoRegisterInfoDtoResponse {
    
    /** 分類一覧コード */
    private int categoryExtensionCd;
    
    /** 分類一覧名称 */
    private String categoryExtensionName;
    
    /** 順序 */
    private int displaySeq;
    
    /** 分類名補足説明 */
    private String remarks;
}
