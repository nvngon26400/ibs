package com.sbisec.helios.ap.bizcommon.util.dto;

import jp.co.sbisec.pcenter.dto.yanap.QueryStockUniteOrderPointOutData;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 訂正ボタン表示判定、株式統合注文一覧照会(ポイント)のレスポンスを所持する.
 *
 * @author SCSK 笹倉 秀行
 */
@Data
@AllArgsConstructor
public class CorrectOrderStatusDto {
    
    /** 株式統合注文一覧照会(ポイント)のレスポンス */
    private QueryStockUniteOrderPointOutData output;
    
    /** 訂正ボタン表示判定結果 */
    private boolean isCorrect;
}
