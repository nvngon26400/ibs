package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * その他余力拘束注文取消確認 SQL002用リクエストモデル
 * 2025/03/10 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaOtherRemainPowerRestrainCancelConfirmSql002RequestModel {

    /** 部店CD */
    private String butenCd;

    /** 口座番号 */
    private String kouzaNo;

    /** EC受注番号（その他商品） */
    private String ecJuchuuNo;

    /** ステータスコード */
    private String stsCd;

    /** 取消区分 */
    private String torikeshiKbn;

    /** 変更日時 */
    private String henkouNichiji;

    /** CCS送付日 */
    private String ccsSendDate;

    /** 主キー：トランザクションＩＤ */
    private String tranId;

    /** 主キー：枝番 */
    private Integer edaban;
}
