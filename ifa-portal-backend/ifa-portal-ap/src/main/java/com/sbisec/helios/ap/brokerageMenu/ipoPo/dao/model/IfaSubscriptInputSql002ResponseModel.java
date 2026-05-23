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
public class IfaSubscriptInputSql002ResponseModel {
    
    /** 裁量配分可能余回数 */
    private String maybeSairyouCount;
    
    /** 本年の年間裁量配分割当回数（数値(整数)） */
    private String sairyouAlloCount;
    
    /** 移管前本年の年間裁量配分割当回数 */
    private String oldSairyouAlloCount;
    
    /** 移管前部店 */
    private String oldButenNumber;
    
    /** 移管前口座番号 */
    private String oldAccountNumber;
    
    /** 本年の年間裁量配分割当上限数 */
    private String maxSairyouAllo;
    
}
