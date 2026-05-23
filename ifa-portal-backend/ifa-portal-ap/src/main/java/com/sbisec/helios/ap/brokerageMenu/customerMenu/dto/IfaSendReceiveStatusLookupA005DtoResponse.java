package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;



import java.util.List;


import lombok.Data;

/**
 * 画面ID：SUB0202_0703-01
 * 画面名：受発信状況照会
 *
 * @author SBI大連 董
 *2025/03/20 新規作成
 */
@Data
public class IfaSendReceiveStatusLookupA005DtoResponse {

    /** 書類請求付加情報詳細 */
    private List<IfaSendReceiveStatusLookupA005docRequestAddInfoDtoResponse> docRequestAddInfo;
    /** 書類不備付加情報リスト */
    private List<IfaSendReceiveStatusLookupA005docDeficencyDtoResponse> selectDocDeficiencyInfoList;

}
