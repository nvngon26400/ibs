package com.sbisec.helios.gw.faq.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * 画面ID：SUB00-05
 * 画面名：よくある質問
 *
 * @author SCSK 仁井田
 2024/05/30 新規作成
 */
@Data
public class IfaFaqA005ApiRequest {
    
    /** コンテンツNo. */
    @NotEmpty(message = "コンテンツNo")
    private String contentsNo;
    
}
