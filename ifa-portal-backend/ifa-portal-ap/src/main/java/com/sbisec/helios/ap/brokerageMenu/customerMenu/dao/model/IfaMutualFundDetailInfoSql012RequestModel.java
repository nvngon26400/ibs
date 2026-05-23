package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 投信詳細情報SQL012要求
 *
 * @author SCSK
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IfaMutualFundDetailInfoSql012RequestModel {
    
    /** 協会コード（全角半角）. */
    private String kyoukaiCd;
    
}
