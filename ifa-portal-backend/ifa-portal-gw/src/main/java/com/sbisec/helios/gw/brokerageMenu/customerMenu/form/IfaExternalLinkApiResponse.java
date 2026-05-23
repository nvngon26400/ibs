package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import lombok.Data;

/**
 * 外部リンク
 * 2025/05/12 新規作成
 *
 * @author 大連 葉
 */
@Data
public class IfaExternalLinkApiResponse {

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
