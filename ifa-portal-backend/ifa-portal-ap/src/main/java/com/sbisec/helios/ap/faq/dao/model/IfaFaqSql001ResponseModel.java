package com.sbisec.helios.ap.faq.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB00-05
 * 画面名：よくある質問
 *
 * @author SCSK 仁井田
 2024/05/30 新規作成
 */
@Data
public class IfaFaqSql001ResponseModel {
    
    /** 見出し */
    private String heading;
    
    /** コンテンツ本文 */
    private String contentText;
    
}
