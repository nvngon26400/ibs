package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 回答情報リスト
 *
 * @author SBI大連 夏
 * @date   2025/07/31
 */

@Data
public class IfaContactAnswerListDto {

    /** IFA問合せNO */
    private String ifaToiawaseNo;
    /** IFA回答NO */
    private String ifaKaitouNo;
    /** 問合せNO */
    private String toiawaseNo;
    /** 回答NO */
    private String kaitouNo;
    /** ユーザID */
    private String userId;
    /** 更新ユーザID */
    private String kosinUserId;
    /** 削除ユーザID */
    private String sakujoUserId;
    /** 回答内容 */
    private String kaitouNaiyou;
    /** 回答日時 */
    private String kaitouNichiji;
    /** 登録日時 */
    private String tourokuNichiji;
    /** 変更日時 */
    private String henkouNichiji;
    /** 削除日時 */
    private String sakujoNichiji;
    /** 削除フラグ */
    private String sakujoFlg;
    /** 接触経路 */
    private String sessyokuKeiro;
    /** CCSユーザID */
    private String ccsUserId;
    /** ユーザ名 */
    private String userName;
    /** 登録グループ */
    private String tourokuGroup;
    
}
