package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * その他余力拘束注文入力 SQL001用レスポンスモデル
 * 2025/03/10 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaOtherRemainPowerRestrainInputSql001ResponseModel {

    /** EC受注番号（その他商品） */
    private String ecJuchuuNo;
    
    /** 拘束理由 */
    private String hattyuRiyu;

    /** 取消区分 */
    private String torikeshiKbn;

}


