package com.sbisec.helios.ap.bizcommon.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ポイント関連項目表示可否取得
 *
 * @author SCSK
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fct039Sql001RequestModel {
    
    // 仲介業者コード
    private String brokerCode;
    
    // ポイント区分
    private String pointType;
}
