package com.sbisec.helios.ap.common.service;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.common.dto.IfaAddressSearchRequestDto;
import com.sbisec.helios.ap.common.dto.IfaAddressSearchResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB07-01 画面名：住所検索ポップアップ
 *
 * @author xin.huang
 */
public interface IfaAddressSearchService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：住所検索
     * Dto リクエスト：IfaAddressSearchRequestDto
     * Dto レスポンス：IfaAddressSearchResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaAddressSearchResponseDto> addressSearch(IfaAddressSearchRequestDto dtoReq) throws Exception;
}
