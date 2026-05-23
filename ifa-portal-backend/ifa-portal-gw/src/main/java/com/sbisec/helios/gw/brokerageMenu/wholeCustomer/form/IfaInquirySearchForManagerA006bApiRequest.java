package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import lombok.Data;
import javax.validation.constraints.NotEmpty;

/**
 * 画面ID:SUB020304-01
 * 画面名:接触履歴（入力）検索
 *
 * @author SBI大連 夏
 * @date   2025/08/18
 */

@Data
public class IfaInquirySearchForManagerA006bApiRequest {

    /** CSVファイル名 */
    @NotEmpty(message = "CSVファイル名")
    private String csvDownloadFile;
}
