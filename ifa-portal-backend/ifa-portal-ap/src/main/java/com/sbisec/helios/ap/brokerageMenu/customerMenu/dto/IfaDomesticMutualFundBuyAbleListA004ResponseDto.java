package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;


/**
 * 国内投信購入・積立可能一覧積立（直接入力）のレスポンスパラメタ
 *
 * @author WJL
 * 
 */
@Data
@JsonSerialize
public class IfaDomesticMutualFundBuyAbleListA004ResponseDto {
    /** 協会コード（全角半角）. */
    private String kyoukaiCd;
}

