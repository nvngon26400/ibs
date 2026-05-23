package com.sbisec.helios.ap.common.composite.dto;

import java.util.List;

import lombok.Data;

@Data
public class IfaBrandSearchA002DtoResponse {
    
    /** 検索結果件数（数値(整数)）. */
    private Integer searchResultCount;
    
    /** 検索結果明細. */
    private List<IfaBrandSearchA002DtoResponse_SearchResultDetail> searchResultDetail;
    
}
