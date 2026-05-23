package com.sbisec.helios.ap.common.dao.model;

import lombok.Data;

/**
 * 投信銘柄検索リクエスト
 *
 * @author xin.huang
 */
@Data
public class IfaBrandNameSearchRequestModel {

    /** 検索キーワード */
    private String keyWordSearch;
}
