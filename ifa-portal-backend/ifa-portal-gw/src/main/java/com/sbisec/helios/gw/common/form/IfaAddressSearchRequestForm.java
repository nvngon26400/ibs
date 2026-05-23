package com.sbisec.helios.gw.common.form;

import lombok.Data;

/**
 * 住所検索リクエスト
 *
 * @author xin.huang
 */
@Data
public class IfaAddressSearchRequestForm {

    /** 郵便番号 */
    private String yuusouNumber;
}
