package com.sbisec.helios.ap.suggestionBox.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonA001DtoRequest;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonA001DtoResponse;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonA002DtoRequest;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonA002DtoResponse;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonA006aDtoRequest;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonA006aDtoResponse;
import com.sbisec.helios.ap.service.Service;

public interface IfaSuggestionBoxCommonService extends Service {

	
	
    /**
     * 
     * Dto リクエスト：IfaSuggestionBoxCommonA001DtoRequest
     * Dto レスポンス：IfaSuggestionBoxCommonA001DtoResponse
     * model リクエスト：IfaSuggestionBoxCommonA001DtoRequestModel
     * model レスポンス：IfaSuggestionBoxCommonA001DtoResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaSuggestionBoxCommonA001DtoResponse> initializeA001(IfaSuggestionBoxCommonA001DtoRequest dtoReq)
            throws Exception;
    
    
    /**
     * 
     * Dto リクエスト：IfaSuggestionBoxCommonA002DtoRequest
     * Dto レスポンス：IfaSuggestionBoxCommonA002DtoResponse
     * model リクエスト：IfaSuggestionBoxCommonA002DtoRequestModel
     * model レスポンス：IfaSuggestionBoxCommonA002DtoResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaSuggestionBoxCommonA002DtoResponse> displayA002(IfaSuggestionBoxCommonA002DtoRequest dtoReq)
            throws Exception;
    
    
    /**
     * 
     * Dto リクエスト：IfaSuggestionBoxCommonA006aDtoRequest
     * Dto レスポンス：IfaSuggestionBoxCommonA006aDtoResponse
     * model リクエスト：IfaSuggestionBoxCommonA006aDtoRequestModel
     * model レスポンス：IfaSuggestionBoxCommonA006aDtoResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaSuggestionBoxCommonA006aDtoResponse> csvOutputA006(IfaSuggestionBoxCommonA006aDtoRequest dtoReq,String fwSessionId)
            throws Exception;

}
