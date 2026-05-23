package com.sbisec.helios.ap.wholePortal.dto;

import lombok.Data;

/**
 * @author 池亀緑
 *
 */
@Data
public class IfaWholePortalHomeA021ResponseDtoNotification {

    /** 更新日時 */
    private String updateTime;

    /** お知らせID（数字） */
    private String notificationId;

    /** カテゴリ名 */
    private String categoryName;

    /** タイトル（全角半角） */
    private String title;

    /** 登録日時 */
    private String registerDayTime;

}
