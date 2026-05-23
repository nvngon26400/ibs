package com.sbisec.helios.ap.common.dao.model;

import lombok.Data;

/**
 * 投信銘柄検索レスポンス
 *
 * @author xin.huang
 */
@Data
public class IfaBrandNameSearchResponseModel {

    /** 協会コード */
    private String fundCode;

    /** ファンドタイプ */
    private String fundType;

    /** ファンド正式名 */
    private String fundName;

    /** 委託会社コード */
    private String fundItakuCmp;

    /** 回数 */
    private String fundKaisu;

    /** 号 */
    private String fundGo;

    /** 委託会社名 */
    private String familyName;

    /** 購入可否判定区分 */
    private String fundMdBuyKubun;
}
