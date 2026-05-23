package com.sbisec.helios.ap.suggestionBox.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonDetailA001RequestDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonDetailA001ResponseDto;
import com.sbisec.helios.ap.service.Service;

public interface IfaSuggestionBoxCommonDetailService extends Service {

    /**
     * 
     * Dto リクエスト：IfaSuggestionBoxCommonA001DtoRequest
     * Dto レスポンス：IfaSuggestionBoxCommonA001DtoResponse
     * model リクエスト：IfaSuggestionBoxCommonA001DtoRequestModel
     * model レスポンス：IfaSuggestionBoxCommonA001DtoResponseModel
     */
    public DataList<IfaSuggestionBoxCommonDetailA001ResponseDto> initializeA001(IfaSuggestionBoxCommonDetailA001RequestDto dtoReq)
            throws Exception;
    
}
