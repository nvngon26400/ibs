package com.sbisec.helios.ap.suggestionBox.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonUpdateA001RequestDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonUpdateA001ResponseDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonUpdateA009bRequestDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0511_02-03
 * 画面名：皆様からの要望更新
 *
 2025/06/23 新規作成
 */

public interface IfaSuggestionBoxCommonUpdateService extends Service {

    /**
     * 
     * Dto リクエスト：IfaSuggestionBoxCommonA001DtoRequest
     * Dto レスポンス：IfaSuggestionBoxCommonA001DtoResponse
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     */
    public DataList<IfaSuggestionBoxCommonUpdateA001ResponseDto> initializeA001(IfaSuggestionBoxCommonUpdateA001RequestDto dtoReq)
            throws Exception;
    
    /**
     * 
     * Dto リクエスト：IfaSuggestionBoxCommonUpdateA009bRequestDto
     * Dto レスポンス：String
     * model リクエスト：RequestModel
     * model レスポンス：String
     * @param req リクエストDTO
     * @return DataList<String>
     * @exception Exception システムエラー
     * @see <reference item>
     */
    public DataList<String> updateA009b(
            IfaSuggestionBoxCommonUpdateA009bRequestDto dtoReq
        ) throws Exception;

}
