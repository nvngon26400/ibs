package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA002LookupDtoResponse;

import lombok.Data;

/**
* 画面ID：SUB0202_0703-01
* 画面名：受発信状況照会
*
* @author SBI大連 董
*2025/03/20 新規作成
*/

@Data
public class IfaSendReceiveStatusLookupA003ApiResponse {
    /** 受発信状況リスト */
    private List<IfaSendReceiveStatusLookupA002LookupDtoResponse> sendReceiveStatusLookupList;
}
