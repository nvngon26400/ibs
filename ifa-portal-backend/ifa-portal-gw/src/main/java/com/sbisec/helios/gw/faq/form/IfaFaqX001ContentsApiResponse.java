package com.sbisec.helios.gw.faq.form;

import lombok.Data;

/**
 * 画面ID：SUB00-05
 * 画面名：よくある質問
 *
 * @author SCSK 仁井田
 2024/05/30 新規作成
 */
@Data
public class IfaFaqX001ContentsApiResponse {
    
    /** 大項目 */
    private String primaryItem;
    
    /** 小項目 */
    private String tertiaryItem;
    
    /** 見出し */
    private String heading;
    
    /** コンテンツNo */
    private String contentsNo;
    
}
