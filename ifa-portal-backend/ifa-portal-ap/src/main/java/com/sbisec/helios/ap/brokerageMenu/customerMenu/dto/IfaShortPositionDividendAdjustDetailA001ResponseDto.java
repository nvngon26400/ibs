package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：SUB0202_010302-02
 * 画面名：売建配当調整金明細
 * 2024/06/21 新規作成
 *
 * @author SCSK 笹倉秀行
 */
@Data
public class IfaShortPositionDividendAdjustDetailA001ResponseDto {
    
    /** 部店コード. */
    private String butenCode;
    
    /** 口座番号. */
    private String accountNumber;
    
    /** 売建配当調整金明細リスト. */
    List<IfaShortPositionDividendAdjustDetailA001ResponseDtoShortPositionDividendAdjustDetail> detailList;
    
    /** 検索結果件数. */
    private String searchResultCount;
}
