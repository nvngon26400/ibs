package com.sbisec.helios.gw.faq.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 画面ID：SUB00-05
 * 画面名：よくある質問
 *
 * @author SCSK 仁井田
 2024/05/30 新規作成
 */
@Data
public class IfaFaqA002ApiRequest {
    
    /** 検索ワード（全角半角）. */
    @NotEmpty(message = "検索ワード")
    @Size(max = 255, message = "検索ワード")
    private String searchWord;
    
}
