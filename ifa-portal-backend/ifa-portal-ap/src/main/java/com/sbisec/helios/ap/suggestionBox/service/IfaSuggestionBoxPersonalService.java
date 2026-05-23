package com.sbisec.helios.ap.suggestionBox.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalInitializeX001RequestDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalInitializeX001ResponseDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalDisplayA002RequestDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalDisplayA002ResponseDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalCsvOutputA006aRequestDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalCsvOutputA006aResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB00_01-06_1
 * 画面名：あなたの要望
 * @author SCSK神木
 * 2025/06/12 新規作成
 */
public interface IfaSuggestionBoxPersonalService extends Service {

    /**
     * アクションID：X001
     * アクション名：初期表示
     * 処理概要：
     * ①画面コメントを取得する。
     * ②要望一覧を取得する。
     * 呼出機能：SQL001, SQL002, SQL003
     * @param req リクエストDTO
     * @return 検索結果を含むレスポンスDTO
     * @exception Exception システムエラー
     */
    public DataList<IfaSuggestionBoxPersonalInitializeX001ResponseDto> initializeX001(IfaSuggestionBoxPersonalInitializeX001RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A002
     * アクション名：表示
     * 処理概要：
     * ①情報一覧を取得する。
     * 呼出機能：SQL002, SQL003
     * @param req リクエストDTO
     * @return 検索結果を含むレスポンスDTO
     * @exception Exception システムエラー
     */
    public DataList<IfaSuggestionBoxPersonalDisplayA002ResponseDto> displayA002(IfaSuggestionBoxPersonalDisplayA002RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A006
     * アクション名：CSV出力
     * 処理概要：
     * ①情報一覧を取得する。
     * ②CSV出力を行う。
     * 呼出機能：SQL003, FCT038
     * @param req リクエストDTO
     * @return 検索結果を含むレスポンスDTO
     * @exception Exception システムエラー
     */
    public DataList<IfaSuggestionBoxPersonalCsvOutputA006aResponseDto> csvOutputA006(IfaSuggestionBoxPersonalCsvOutputA006aRequestDto dtoReq, String fwSessionId)
            throws Exception;

}
