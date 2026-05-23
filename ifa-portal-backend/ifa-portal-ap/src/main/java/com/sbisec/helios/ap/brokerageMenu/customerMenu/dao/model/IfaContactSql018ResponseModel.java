package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaContactSql018ResponseModel {

    /** トランザクションID */
    private String tranId;

    /** 取消区分 */
    private String torikeshiKbn;

    /** 拘束区分 */
    private String kousokuKbn;

    /** 拘束種別 */
    private String kousokuSyubetu;

    /** 拘束金額（NISA） */
    private String nisaKingaku;

    /** 枝番 */
    private String edaban;

    /** 特定預り売買区分 */
    private String tokuteiAzukariBaibaiKbn;

    /** 発注理由 */
    private String hattyuRiyu;

    /** 登録日時 */
    private String tourokuNichiji;

    /** 精算金額 */
    private String seisanKingaku;

    /** ユーザー名 */
    private String userNm;
}
