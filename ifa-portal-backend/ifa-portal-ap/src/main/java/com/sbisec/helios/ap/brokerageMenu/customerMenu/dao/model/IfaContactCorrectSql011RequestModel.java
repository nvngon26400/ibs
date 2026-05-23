package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * SQL011用リクエストモデル
 * 画面ID:SUB0202_0106-07
 * 画面名:接触履歴修正
 *
 * @author SBI大連 夏
 * @date   2025/08/06
 */

@Data
public class IfaContactCorrectSql011RequestModel {

    /** IFA問合せNO */
    private String ifaToiawaseNo;
    
    /** 問合せNO */
    private String toiawaseNo;
    
    /** 入力日時 */
    private String nyuuryokuDate;
    
    /** 入力者 */
    private String nyuuryokusyaId;
    
    /** 入力者名 */
    private String nyuuryokusyaName;
    
    /** 登録グループ */
    private String tourokuGroup;
    
    /** 修正日時 */
    private String syuuseiDate;
    
    /** 修正者 */
    private String syuuseisyaId;
    
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
    
    /** 顧客ID */
    private String kokyakuId;
    
    /** 部店コード */
    private String butenCode;
    
    /** 口座番号 */
    private String accountNumber;
    
    /** 仲介業者コード */
    private String brokerCode;
    
    /** 仲介業者営業員コード */
    private String intermediaryEmpCd;
    
    /** 顧客名_姓名(漢字) */
    private String nameKanji;
    
    /** 顧客名_姓名(カナ) */
    private String nameKana;
    
}
