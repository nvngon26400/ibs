package com.sbisec.helios.ap.wholePortal.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB01-03
 * 画面名：Information詳細
 * 2025/01/20 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaInfoDetailSql001RequestModel {

    /** お知らせID */
    private String notificationId;

    /** ユーザ共通情報.ユーザーID */
    private String userId;

}
