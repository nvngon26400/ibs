package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import java.util.List;
import lombok.Data;

/**
 * 画面ID：SUB020301_03-03
 * 画面名：ノックアウト銘柄保有一覧

 * @author 大崎 辰弥
    2024/06/12 新規作成
 */

@Data
public class IfaKnockOutBrandHoldingListA002DtoResponse {

    /** ノックアウト銘柄保有リスト. */
    private List<IfaKnockOutBrandHoldingListA002DtoResponseKnockOutBrandHoldingList> knockOutBrandHoldingList;

}
