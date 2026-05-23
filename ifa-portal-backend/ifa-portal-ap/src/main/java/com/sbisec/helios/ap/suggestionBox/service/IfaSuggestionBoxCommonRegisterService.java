package com.sbisec.helios.ap.suggestionBox.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonRegisterSql001ResponseModel;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonRegisterA007bRequestDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0511_02-02
 * 画面名：皆様からの要望新規登録
 *
 2025/06/23 新規作成
 */

public interface IfaSuggestionBoxCommonRegisterService extends Service {
	
    /**
     * 
     * Dto リクエスト：IfaSuggestionBoxCommonRegisterA007bRequestDto
     * Dto レスポンス：String
     * model リクエスト：RequestModel
     * model レスポンス：String
     * @param req リクエストDTO
     * @return String
     * @exception Exception システムエラー
     */
    public DataList<IfaSuggestionBoxCommonRegisterSql001ResponseModel> selectA007() throws Exception;
    
    /**
     * 
     * Dto リクエスト：IfaSuggestionBoxCommonRegisterA007bRequestDto
     * Dto レスポンス：String
     * model リクエスト：RequestModel
     * model レスポンス：String
     * @param req リクエストDTO
     * @return DataList<String>
     * @exception Exception システムエラー
     * @see <reference item>
     */
    public DataList<String> registA007b(
            IfaSuggestionBoxCommonRegisterA007bRequestDto dtoReq,
            String fileDirectory
        ) throws Exception;

}
