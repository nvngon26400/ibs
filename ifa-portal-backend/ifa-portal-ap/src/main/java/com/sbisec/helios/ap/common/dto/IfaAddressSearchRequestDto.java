package com.sbisec.helios.ap.common.dto;

import lombok.Data;

/**
 * 住所検索リクエスト
 *
 * @author xin.huang
 */
@Data
public class IfaAddressSearchRequestDto {

    /** 郵便番号 */
    private String yuusouNumber;
}
