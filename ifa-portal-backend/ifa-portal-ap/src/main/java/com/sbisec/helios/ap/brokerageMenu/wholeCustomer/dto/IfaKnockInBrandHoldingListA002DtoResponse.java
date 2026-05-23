package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import java.util.List;
import lombok.Data;

/**
 * 画面ID：SUB020301_03-02
 * 画面名：ノックイン銘柄保有一覧

 * @author 大崎 辰弥
    2024/06/12 新規作成
 */

@Data
public class IfaKnockInBrandHoldingListA002DtoResponse {

    /** ノックイン銘柄保有リスト. */
    private List<IfaKnockInBrandHoldingListA002DtoResponseKnockInBrandHoldingList> knockInBrandHoldingList;


}
