package com.sbisec.helios.ap.common.composite.dto;

import java.util.List;

import lombok.Data;

@Data
public class IfaBrandSearchA003DtoResponse {
    
    /** 検索結果件数（数値(整数)）. */
    private Integer searchResultCount;
    
    /** 検索結果明細. */
    private List<IfaBrandSearchA003DtoResponse_SearchResultDetail> searchResultDetail;
}
