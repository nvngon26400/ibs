package com.sbisec.helios.ap.common.service;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.common.dto.IfaBrandNameSearchRequestDto;
import com.sbisec.helios.ap.common.dto.IfaBrandNameSearchResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB07-03_1 画面名：投信銘柄検索ポップアップ
 *
 * @author xin.huang
 */
public interface IfaBrandNameSearchService extends Service {
    
    /**
     * アクションID：A002
     * アクション名：投信銘柄検索
     * Dtoリクエスト：IfaBrandNameSearchRequestDto
     * Dtoレスポンス：IfaBrandNameSearchResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBrandNameSearchResponseDto> brandNameSearch(IfaBrandNameSearchRequestDto dtoReq) throws Exception;
}
