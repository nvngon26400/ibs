package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 投信詳細情報 SQL004要求
 *
 * @author SCSK
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IfaMutualFundDetailInfoSql004RequestModel {
    
    /** 協会コード（全角半角）. */
    private String kyoukaiCd;
    
}
