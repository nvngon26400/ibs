package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * その他余力拘束注文取消確認 SQL004用リクエストモデル
 * 2025/03/10 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaOtherRemainPowerRestrainCancelConfirmSql004RequestModel {

    /** トランザクションＩＤ */
    private String tranId;

    /** 枝番 */
    private Integer edaban;

    /** 受注日 */
    private String juchubi;

    /** EC受注番号（その他商品） */
    private String ecJuchuuNo;

    /** 約定予定日 */
    private String yakujouYoteibi;

    /** エラーコード */
    private String errorCd;

    /** エラーメッセージ */
    private String errorMessage;

    /** 種別 */
    private String shubetsu;

    /** 変更日時 */
    private String henkouNichiji;

    /** APIエラーかどうかフラグ */
    private String apiError;
}
