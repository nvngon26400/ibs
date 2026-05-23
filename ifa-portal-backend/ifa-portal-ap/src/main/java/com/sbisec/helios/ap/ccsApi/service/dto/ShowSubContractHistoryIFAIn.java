package com.sbisec.helios.ap.ccsApi.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 接触履歴参照APIパラメータ設定(In)
 */
@Data
public class ShowSubContractHistoryIFAIn {

    /** 顧客ID */
    @JsonProperty("AccountId")
    private String AccountId;

    /** 大分類 */
    @JsonProperty("CategoryCode")
    private String CategoryCode;

    /** CCSアカウントID */
    @JsonProperty("UserId")
    private String UserId;
}
