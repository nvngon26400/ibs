package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0204_02-04_1
 * 画面名：募集入力
 * 2024/2/6 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaSubscriptInputSql006ResponseModel {
    
    /** 特定口座区分（半角英数字） */
    private String tokuteiKouzaKbn;
    
    /** ISA契約区分 */
    private String isaContractType;
    
    /** ISA買付可能判定区分（当年）（半角英数字） */
    private String isaBuyAbleJudgeClassificationYear;

    /** 顧客コード */
    private String customerId;
    
}
