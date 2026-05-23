package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;

/**
 * サービス用レスポンスモデル
 * 画面ID:SUB0202_0106-03
 * 画面名:接触履歴入力
 * アクション：A001 初期化
 * 2025/04/24 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaContactInputA001ResponseDto {

    /** 問合せカテゴリリスト */
    private List<IfaContactToiawaseListDto> toiawaseDList;

    /** 処理区分 */
    private String operationType;
}
