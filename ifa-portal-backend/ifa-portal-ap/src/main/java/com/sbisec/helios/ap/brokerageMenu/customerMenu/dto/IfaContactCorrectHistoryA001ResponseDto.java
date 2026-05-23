package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;
import lombok.Data;

/**
 * サービス用レスポンスモデル
 * 画面ID:SUB0202_0106-08
 * 画面名:接触履歴（入力）修正履歴
 * A001 初期化
 *
 * @author SBI大連 夏
 * @date   2025/08/14
 */

@Data
public class IfaContactCorrectHistoryA001ResponseDto {
    
    private List<IfaContactCorrectHistoryListDto> contactCorrectHistoryList;
    
}
