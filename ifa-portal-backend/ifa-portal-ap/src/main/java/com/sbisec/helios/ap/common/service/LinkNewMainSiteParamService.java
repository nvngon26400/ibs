package com.sbisec.helios.ap.common.service;

import com.sbisec.helios.ap.common.dto.LinkNewMainSiteParamRequestDto;
import com.sbisec.helios.ap.common.dto.LinkNewMainSiteParamResponseDto;
import com.sbisec.helios.ap.service.Service;

public interface LinkNewMainSiteParamService extends Service {

    /**
     * 新メインサイト用パラメータ生成サービス
     *
     * @param dtoReq {@code LinkNewMainSiteParamRequestDto }
     * @return {@code LinkNewMainSiteParamResponseDto} 新メインサイト用パラメータ
     * @throws Exception システムエラーやデータアクセスエラーなど、処理中に発生した例外
     */
    public LinkNewMainSiteParamResponseDto getNewMainSiteParam(LinkNewMainSiteParamRequestDto dtoReq)
            throws Exception;

}
