package com.sbisec.helios.gw.common.form;

import lombok.Data;

/**
 * 投信銘柄検索リクエスト
 *
 * @author xin.huang
 */
@Data
public class IfaBrandNameSearchRequestForm {

    /** 検索キーワード */
    private String keyWordSearch;
}
