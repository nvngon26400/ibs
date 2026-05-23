package com.sbisec.helios.ap.faq.dto;

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
public class IfaFaqX001DtoResponse {
    
    /** コンテンツNo */
    private String contentsNo;
    
    /** コンテンツリスト */
    private List<IfaFaqX001ContentsDtoResponse> contentsList;
    
}
