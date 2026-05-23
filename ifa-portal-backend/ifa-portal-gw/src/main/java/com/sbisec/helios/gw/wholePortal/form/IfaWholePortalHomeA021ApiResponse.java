package com.sbisec.helios.gw.wholePortal.form;

import java.util.List;

import lombok.Data;

/**
 * @author 池亀緑
 *
 */
@Data
public class IfaWholePortalHomeA021ApiResponse {

    /** お知らせリスト */
    private List<IfaWholePortalHomeA021ApiResponseNotification> notificationList;

    /** タイトル（全角半角） */
    private String title;

    /** 内容 */
    private String contents;

    /** URL（英数字記号B(+-_./@*#%!"$&()=~^\?>,|`[]{}:;<')） */
    private String url;

    /** URLコメント */
    private String urlComment;

    /** 添付ファイル1 */
    private String attachDocument1;

    /** 添付ファイル2 */
    private String attachDocument2;

    /** 添付ファイル3 */
    private String attachDocument3;

    /** 添付ファイルコメント1 */
    private String attachDocumentComment1;

    /** 添付ファイルコメント2 */
    private String attachDocumentComment2;

    /** 添付ファイルコメント3 */
    private String attachDocumentComment3;

    /** ディスクレーマー（全角半角） */
    private String disclaimer;

}
