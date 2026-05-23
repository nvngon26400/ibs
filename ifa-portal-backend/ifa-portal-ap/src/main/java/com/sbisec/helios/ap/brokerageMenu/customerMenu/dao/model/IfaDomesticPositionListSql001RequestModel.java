package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 国内建玉一覧SQL001リクエスト
 *
 * @author SCSK 金志
 *
 */
@Data
public class IfaDomesticPositionListSql001RequestModel {
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
}
