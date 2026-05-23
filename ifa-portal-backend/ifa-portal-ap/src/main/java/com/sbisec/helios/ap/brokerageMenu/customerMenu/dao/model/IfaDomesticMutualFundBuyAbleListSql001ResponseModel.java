package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 国内投信購入積立可能一覧SQL001応答
 *
 * @author WJL
 *
 */
@Data
public class IfaDomesticMutualFundBuyAbleListSql001ResponseModel {
    
    /** 協会コード（全角半角）. */
    private String mFCode;   
    
    /** ファンドタイプ. */
    private String mFType;
    
    /** ファンド正式名. */
    private String mFName;   
    
}
