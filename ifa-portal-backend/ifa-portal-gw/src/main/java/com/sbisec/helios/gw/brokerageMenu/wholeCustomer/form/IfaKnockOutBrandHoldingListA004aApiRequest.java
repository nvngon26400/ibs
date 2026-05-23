package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * 画面ID：SUB020301_03-03
 * 画面名：ノックアウト銘柄保有一覧

 * @author 大崎 辰弥
    2024/06/12 新規作成
 */

@Data
public class IfaKnockOutBrandHoldingListA004aApiRequest {

    /** PDF通知URL. */
    @NotEmpty(message = "PDF通知URL")
    @Size(max = 500, message = "PDF通知URL")
    private String pdfNoticeUrl;

}
