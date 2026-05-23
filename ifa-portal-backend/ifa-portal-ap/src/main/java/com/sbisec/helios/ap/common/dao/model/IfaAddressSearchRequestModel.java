package com.sbisec.helios.ap.common.dao.model;

import lombok.Data;

/**
 * 住所検索リクエスト
 *
 * @author xin.huang
 */
@Data
public class IfaAddressSearchRequestModel {

    /** 郵便番号 */
    private String yuusouNumber;
}
