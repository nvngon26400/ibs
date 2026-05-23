package com.sbisec.helios.gw.faq.form;

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
public class IfaFaqA002ApiResponse {
    
    /** 検索キーワードリスト */
    private List<String> searchKeyWordList;
    
    /** メッセージ */
    private String msg;
    
    /** 検索結果リスト件数 */
    private int searchResultListCount;
    
    /** 検索結果リスト */
    private List<IfaFaqA002SearchResultApiResponse> searchResultList;
    
}
