package com.sbisec.helios.ap.ccsApi.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 接触履歴詳細参照APIパラメータ設定(In)
 */
@Data
public class ShowDealingDetailIFAIn {

    /** 顧客ID */
    @JsonProperty("AccountId")
    private String AccountId;

    /** 詳細区分 */
    @JsonProperty("ShousaiKbn")
    private String ShousaiKbn;

    /** 記録日時 */
    @JsonProperty("RecordDate")
    private String RecordDate;

    /** 枝番 */
    @JsonProperty("Edaban")
    private String Edaban; 

    /** 大分類 */
    @JsonProperty("BigClass")
    private String BigClass;

    /** CCSアカウントID */
    @JsonProperty("UserId")
    private String UserId;
}
