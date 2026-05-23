package com.sbisec.helios.ap.wholePortal.dto;

import lombok.Data;

/**
 * 画面ID：SUB01-03
 * 画面名：Information詳細
 * 2025/01/20 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaInfoDetailA001RequestDto {

    /** お知らせID */
    private String notificationId;

    /** ファイルディレクトリ */
    private String fileDirectory;

}
