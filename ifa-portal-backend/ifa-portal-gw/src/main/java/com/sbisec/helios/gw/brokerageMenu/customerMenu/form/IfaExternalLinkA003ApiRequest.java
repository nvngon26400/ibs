package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/**
 * 外部リンク
 * 2025/07/29 新規作成
 *
 * @author 大連 葉
 */
@Data
@JsonSerialize
public class IfaExternalLinkA003ApiRequest {

    private String urlId;

    private String patternId;

    private String httpMethod;
}
