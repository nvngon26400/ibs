package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * その他余力拘束入力確認 SQL002用リクエストモデル
 * 2025/03/10 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaOtherRemainPowerRestrainInputConfirmSql002RequestModel {

    /** トランザクションＩＤ */
    private String tranId;

    /** 枝番 */
    private Integer edaban;

    /** 受注日 */
    private String juchubi;

    /** EC受注番号（その他商品） */
    private String ecJuchuuNo;

    /** EC受注日時 */
    private String ecJuchuuNichiji;

    /** ステータスコード */
    private String stsCd;

    /** エラーコード */
    private String errorCd;

    /** エラーメッセージ */
    private String errorMessage;

    /** 種別 */
    private String shubetsu;

    /** 買付可能金額 */
    private String kaitsukekanouKingaku;

    /** 注文後の買付可能金額 */
    private String chumonKaitsukekanouKingaku;

    /** 入力受注日時 */
    private String nyuuryokuJuchuuNichiji;

    /** 変更日時 */
    private String henkouNichiji;

    /** APIエラーかどうかフラグ */
    private String apiError;

}
