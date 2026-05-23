package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * サービス用レスポンスモデル
 * 画面ID:SUB0202_0106-08
 * 画面名:接触履歴（入力）修正履歴
 * A001 初期化
 *
 * @author SBI大連 夏
 * @date   2025/08/14
 */

@Data
public class IfaContactCorrectHistoryListDto {

    /** 入力日時 */
    private String nyuuryokuDate;
    
    /** 入力者名 */
    private String nyuuryokusyaName;
    
    /** 修正日時 */
    private String syuuseiDate;
    
    /** 修正者名 */
    private String syuuseisyaName;
    
    /** 内容1 */
    private String naiyou1;
    
    /** 内容2 */
    private String naiyou2;
    
    /** 内容3 */
    private String naiyou3;
    
    /** 内容4 */
    private String naiyou4;
    
    /** 内容5 */
    private String naiyou5;
    
}
