package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class TbAdditionalOrderIfaBaseModel {

    /** 拘束区分 */
    private String kousokuKbn;

    /** 拘束種別 */
    private String kousokuSyubetu;

    /** 拘束金額（NISA） */
    private String nisaKingaku;

    /** 特定預り売買区分 */
    private String tokuteiAzukariBaibaiKbn;

    /** 発注理由 */
    private String hattyuRiyu;

    /** ユーザー名 */
    private String userNm;
}
