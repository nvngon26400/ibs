package com.sbisec.helios.gw.common.composite.form;

import java.util.List;

import lombok.Data;

@Data
public class IfaBrandSearchA002ApiResponse {
    
    /** 検索結果件数（数値(整数)）. */
    private Integer searchResultCount;
    
    /** 検索結果明細. */
    // TODO 項目辞書未登録
    private List<IfaBrandSearchA002ApiResponse_SearchResultDetail> searchResultDetail;
    
}
