package com.sbisec.helios.ap.faq.dao.model;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：SUB00-05
 * 画面名：よくある質問
 *
 * @author SCSK 仁井田
 2024/05/30 新規作成
 */
@Data
public class IfaFaqSql003RequestModel {
    
    /** 最大取得件数 */
    private int maxCount;
    
    /** 検索キーワードリスト */
    private List<String> searchKeyWordList;
}
