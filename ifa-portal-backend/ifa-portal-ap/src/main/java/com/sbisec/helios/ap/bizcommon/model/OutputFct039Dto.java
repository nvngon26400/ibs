package com.sbisec.helios.ap.bizcommon.model;

import com.sbibits.earth.util.StringUtil;

import lombok.Data;

/**
 * 共通関数DTO：FCT039
 *
 * @author SCSK
 */
@Data
public class OutputFct039Dto {
    
    public static final String AVAIL_HIDDEN = "0";
    
    /** ポイント種別 API001.レスポンス.ポイントアカウント.ポイント区分 */
    private String pointType;
    
    /** 顧客連携状態 API001.レスポンス.ポイントアカウント.会員ステータス */
    private String state;
    
    /** ポイント数 API001.レスポンス.利用可能ポイント数+期間固定情報：期間固定ポイント数合計 */
    private Integer pointNumber;
    
    /** うち期間固定ポイント API001.レスポンス.期間固定情報：期間固定ポイント数合計 */
    private Integer restrictPointSum;
    
    /** 最短有効期限 API001.レスポンス.ポイント有効期限 */
    private String expiredDate;
    
    /** 最低利用ポイント数 API001.レスポンス.最低利用ポイント数 */
    private Integer minimumUsePoint;
    
    /** 利用ポイント単位 API001.レスポンス.利用ポイント単位 */
    private String usePointUnit;
    
    /** ポイント表示エリア表示可否 */
    private String pointDisplayAreaAvailability = AVAIL_HIDDEN;
    
    /** ポイント名表示可否 */
    private String pointNameDisplayAvailability = AVAIL_HIDDEN;
    
    /** ポイント数表示可否 */
    private String pointNumberDisplayAvailability = AVAIL_HIDDEN;
    
    /** うち期間固定ポイント表示可否 */
    private String fixedTermPointDisplayAvailability = AVAIL_HIDDEN;
    
    /** 最短有効期限表示可否 */
    private String pointShortLimitDisplayAvailability = AVAIL_HIDDEN;
    
    /** ポイント利用エリア表示可否 */
    private String pointUseAreaAvailability = AVAIL_HIDDEN;

    /** 画面メッセージ */
    private String screenMessage = StringUtil.EMPTY_STRING;
    
}
