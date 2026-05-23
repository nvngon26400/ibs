package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;

/**
 * 外部リンク
 * 2025/05/13 新規作成
 *
 * @author 大連 葉
 */
@Data
public class IfaExternalLinkDtoResponse {

    /** 部店コード */
    private String butenCode;

    /** 口座番号 */
    private String accountNumber;

    /** 店群情報取得 */
    private String tenGunId;

    /** CCSログインID */
    private String ccsOpId;

    /** 契約締結前交付書面コード */
    private String customerAttribute;

    /** 分類エリア生成 */
    private List<String> categoryList;
}
