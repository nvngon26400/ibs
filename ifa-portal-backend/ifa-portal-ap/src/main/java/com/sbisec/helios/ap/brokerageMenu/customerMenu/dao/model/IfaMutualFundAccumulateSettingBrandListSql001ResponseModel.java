package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 投信積立設定済銘柄一覧SQL001応答
 *
 * @author nicksen.li
 *
 */
@Data
public class IfaMutualFundAccumulateSettingBrandListSql001ResponseModel {
    /** 協会コード（8桁）. */
    private String mfcode;

    /** ファンドコード（回数）（半角英数字）. */
    private String mfkaisu;

    /** ファンドコード（号）（半角英数字）. */
    private String mfgo;

    /** NRIコード（回数(4桁)＋号(3桁)－ただし号の1桁目はスペース）. */
    private String mfnricode;

}
