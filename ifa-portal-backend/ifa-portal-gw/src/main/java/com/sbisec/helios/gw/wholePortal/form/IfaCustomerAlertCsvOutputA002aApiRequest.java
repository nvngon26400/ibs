package com.sbisec.helios.gw.wholePortal.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 画面ID：SUB01-02
 * 画面名：顧客アラートCSV出力
 *
 * @author SCSK丹波
 2024/05/16 新規作成
 */
@Data
public class IfaCustomerAlertCsvOutputA002aApiRequest {
    
    /** 仲介業者コードリスト. */
    @Pattern(regexp = "[a-zA-Z0-9\\,]*", message = "仲介業者コード")
    @Size(max = 49, message = "仲介業者コード")
    private String brokerCodeList;
    
    /** 取引コースリスト. */
    private String tradeCourseList;
    
    /** アラート分類リスト. */
    @NotEmpty(message = "アラート分類リスト")
    private String alertClassList;
}
