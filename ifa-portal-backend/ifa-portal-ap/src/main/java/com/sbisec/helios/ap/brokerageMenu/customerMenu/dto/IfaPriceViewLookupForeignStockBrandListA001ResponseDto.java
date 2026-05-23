package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 画面ID：SUB0202_0302-01
 * 画面名：単価表照会（外国株式銘柄一覧）
 * 2024/03/27 新規作成
 *
 * @author SCSK今井
 */
@Data
public class IfaPriceViewLookupForeignStockBrandListA001ResponseDto {
    
    /** お知らせ */
    private String notification;
    
    /** 注意事項 */
    private String noticeNote;
    
    /** 最新の外国証券情報一覧URL */
    private String latestForeignSecuritiesInfoListUrl;
    
    /** 国内店頭取引マニュアルURL */
    private String domesticOverTheCounterTradingManualUrl;
    
    /** 外国証券情報更新履歴URL */
    private String foreignSecuritiesInfoUpdateHistoryUrl;
    
}
