package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBulkChangeInputData;

import lombok.Data;

/**
 * 投信積立設定一括変更入力 A005 リクエストパラメータ
 *
 * @author nicksen.li
 */
@Data
public class IfaMutualFundAccumulateSettingBulkChangeInputA005ApiRequest {

    // ジュニアNISA口座開設有無
    private String openedJnisa;

    // 積立設定リスト
    List<IfaMutualFundAccumulateSettingBulkChangeInputData> bulkChangeInputDataList;

    /** 勧誘区分（全角半角）. */
    @NotEmpty(message = "勧誘区分")
    @Size(max = 2, message = "勧誘区分")
    private String kanyuKbn;

    /** 受注方法. */
    @NotEmpty(message = "受注方法")
    private String receiveMethod;

    @NotEmpty(message = "注意事項 説明済確認")
    @Size(min = 1, max = 1, message = "注意事項 説明済確認")
    private String checkMadoAki;

}
