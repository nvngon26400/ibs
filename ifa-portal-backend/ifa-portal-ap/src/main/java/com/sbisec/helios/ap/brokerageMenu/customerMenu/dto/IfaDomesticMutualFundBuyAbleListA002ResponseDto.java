package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/**
 * 空定義のレスポンスパラメタ
 *
 * @author SCSK池田
 * 
 */
@Data
@JsonSerialize
public class IfaDomesticMutualFundBuyAbleListA002ResponseDto {
    /** 協会コード（全角半角）. */
    private String kyoukaiCd;
    
    /** ファンドタイプ . */
    private String fundType;
       
    /** 目論見書チェック区分. */
    private String prospectusFlag;
}
