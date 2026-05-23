package com.sbisec.helios.ap.suggestionBox.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalRegisterSql001ResponseModel;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalRegisterA006aRequestDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalRegisterA006bRequestDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB00_01-06_2
 * 画面名：要望事項 新規登録
 *
 * 2025/06/30 新規作成
 */

public interface IfaSuggestionBoxPersonalRegisterService extends Service {

    /**
     * 
     * Dto リクエスト：IfaSuggestionBoxPersonalRegisterA006bRequestDto
     * Dto レスポンス：String
     * model リクエスト：RequestModel
     * model レスポンス：String
     * @param req リクエストDTO
     * @return String
     * @exception Exception システムエラー
     */
    public DataList<IfaSuggestionBoxPersonalRegisterSql001ResponseModel> selectA006a(IfaSuggestionBoxPersonalRegisterA006aRequestDto dtoReq) throws Exception;

    /**
     * 
     * Dto リクエスト：IfaSuggestionBoxPersonalRegisterA006bRequestDto
     * Dto レスポンス：String
     * model リクエスト：RequestModel
     * model レスポンス：String
     * @param req リクエストDTO
     * @return DataList<String>
     * @exception Exception システムエラー
     * @see <reference item>
     */
    public DataList<String> insertA006b(IfaSuggestionBoxPersonalRegisterA006bRequestDto dtoReq) throws Exception;

}
