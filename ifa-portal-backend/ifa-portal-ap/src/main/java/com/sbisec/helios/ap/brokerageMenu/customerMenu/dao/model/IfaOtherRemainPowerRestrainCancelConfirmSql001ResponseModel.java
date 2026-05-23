package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * その他余力拘束注文取消確認 SQL001用レスポンスモデル
 * 2025/03/10 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaOtherRemainPowerRestrainCancelConfirmSql001ResponseModel {

    /** トランザクションＩＤ */
    private String tranId;

    /** 枝番 */
    private Integer edaban;

    /** 取消区分 */
    private String torikeshiKbn;

}
