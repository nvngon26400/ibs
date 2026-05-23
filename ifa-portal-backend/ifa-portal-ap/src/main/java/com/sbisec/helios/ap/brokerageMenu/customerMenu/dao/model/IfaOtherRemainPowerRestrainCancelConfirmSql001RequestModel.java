package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * その他余力拘束注文取消確認 SQL001用リクエストモデル
 * 2025/03/10 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaOtherRemainPowerRestrainCancelConfirmSql001RequestModel {

    /** 部店コード */
    private String butenCd;

    /** 口座番号 */
    private String kouzaNo;

    /** EC受注番号（その他商品） */
    private String ecJuchuuNo;

    /** ステータスコード */
    private String stsCd;

}
