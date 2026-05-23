package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：SUB0202_0302-01
 * 画面名：単価表照会（外国株式銘柄一覧）
 * 2024/03/27 新規作成
 *
 * @author SCSK今井
 */
@Data
public class IfaPriceViewLookupForeignStockBrandListA008ResponseDto {
    
    /** 更新時間 */
    private String updateTime;
    
    /** 銘柄リスト */
    private List<IfaPriceViewLookupForeignStockBrandListResponseDtoBrandListItem> brandList;
    
}
