package com.sbisec.helios.ap.common.dto;

import lombok.Data;

/**
 * 投信銘柄検索リクエスト
 *
 * @author xin.huang
 */
@Data
public class IfaBrandNameSearchRequestDto {

    /** 検索キーワード */
    private String keyWordSearch;
}
