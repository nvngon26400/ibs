package com.sbisec.helios.ap.wholePortal.dto;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：SUB01-02
 * 画面名：顧客アラートCSV出力
 *
 * @author SCSK丹波
 2024/05/16 新規作成
 */
@Data
public class IfaCustomerAlertCsvOutputA002aRequestDto {
    
    /** 仲介業者コードリスト. */
    private List<String> brokerCodeArrayList;
    
    /** 取引コースリスト. */
    private String tradeCourseList;
    
    /** アラート分類リスト. */
    private String alertClassList;
}
