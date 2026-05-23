package com.sbisec.helios.ap.common.dto;

import lombok.Data;

/**
 * 新メインサイト用パラメータ リクエストパラメータ
 * 2026/02/09 新規作成
 *
 * @author 大連 葉
 */
@Data
public class LinkNewMainSiteParamRequestDto {

    // URLID
    private String urlId;

    // パラメータパターンID
    private String patternId;

    // HTTPメソッド
    private String httpMethod;

    // 銘柄コード
    private String brandCode;
}
