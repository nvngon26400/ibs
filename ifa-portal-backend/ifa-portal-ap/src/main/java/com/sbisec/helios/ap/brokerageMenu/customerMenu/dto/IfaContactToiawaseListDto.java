package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 接触履歴用問合せカテゴリリストのモテル
 * 画面ID：SUB0202_0106-XX
 * 画面名：接触履歴XX
 * 2025/04/24 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaContactToiawaseListDto {

    /** 問合せカテゴリコード */
    private String toiawaseCd;

    /** カテゴリー名称 */
    private String toiawaseMei;
}
