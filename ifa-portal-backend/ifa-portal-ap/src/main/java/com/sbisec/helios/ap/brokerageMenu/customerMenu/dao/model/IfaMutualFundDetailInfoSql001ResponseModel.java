package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 投信詳細情報SQL001応答
 *
 * @author SCSK
 *
 */
@Data
public class IfaMutualFundDetailInfoSql001ResponseModel {
    
    /** 協会コード（全角半角）. */
    private String mFCode;
    
    /** 特殊区分. */
    private String mFTokusyu;
    
    /** 注文締切時間. */
    private String mFShimekiri;
    
}
