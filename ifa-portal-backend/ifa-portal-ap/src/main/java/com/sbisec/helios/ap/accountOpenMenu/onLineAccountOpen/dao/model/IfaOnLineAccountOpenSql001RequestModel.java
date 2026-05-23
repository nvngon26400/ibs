package com.sbisec.helios.ap.accountOpenMenu.onLineAccountOpen.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0207_0201
 * 画面名：オンライン口座開設
 *
 * @author SCSK 木村
 2025/02/06 新規作成
 */
@Data
public class IfaOnLineAccountOpenSql001RequestModel {

    /** ユーザ共通情報.仲介業者コード. */
    private String brokerCode;

    /** ユーザ共通情報.ユーザーID. */
    private String userId;

    /** 権限コード */
    private String privId;

}