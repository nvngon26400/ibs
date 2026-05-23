package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 登録情報
 * 2025/02/21 新規作成
 *
 * @author 大連 苗
 */
@Data
public class IfaRegisterInfoRegisterInfoApiResponse {
    
    /** 分類一覧コード */
    private int categoryExtensionCd;
    
    /** 分類一覧名称 */
    private String categoryExtensionName;
    
    /** 順序 */
    private int displaySeq;
    
    /** 分類名補足説明 */
    private String remarks;
}
