package com.sbisec.helios.ap.common.dto;

import lombok.Data;

/**
 * 新メインサイト用パラメータ
 *
 * @author 大連 葉
 */
@Data
public class NewMainSiteParamResponseDto {
    /** パラメーター */
    private String parm;

    /** パス */
    private String path;

    /** システム日時 */
    private String timestamp;

    /** 初期化ベクトル */
    private String initializationVector;

    /** ハッシュ */
    private String hash;

    /** 仲介業区分 */
    private String intMedKBN;

    /** 仲介業者コード */
    private String mediationCode;
}
