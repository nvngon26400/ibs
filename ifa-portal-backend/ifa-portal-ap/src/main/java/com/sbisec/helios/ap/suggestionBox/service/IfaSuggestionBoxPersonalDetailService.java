package com.sbisec.helios.ap.suggestionBox.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalDetailA001RequestDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalDetailA001ResponseDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalDetailA007bRequestDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB00_01-06_3
 * 画面名：あなたの要望
 * @author SCSK神木
 * 2025/06/19 新規作成
 */
public interface IfaSuggestionBoxPersonalDetailService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * 処理概要：
     * ①要望を取得する。
     * ②回答を取得する。
     * ③取得した回答を既読に更新する。
     * ④未読の回答がある要望件数を取得する。
     * ⑤画面ストレージ保持項目.目安箱未読件数をSQL004.未読要望件数で更新する。
     * 呼出機能：SQL001, SQL002, SQL003, SQL004
     * @param req リクエストDTO
     * @return 検索結果を含むレスポンスDTO
     * @exception Exception システムエラー
     */
    public DataList<IfaSuggestionBoxPersonalDetailA001ResponseDto> initializeA001(IfaSuggestionBoxPersonalDetailA001RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A007b
     * アクション名：登録
     * 処理概要：
     * ①要望を更新する。
     * 呼出機能：SQL006
     * @param req リクエストDTO
     * @return 検索結果を含むレスポンスDTO
     * @exception Exception システムエラー
     */
    public DataList<String> registerA007b(IfaSuggestionBoxPersonalDetailA007bRequestDto dtoReq)
            throws Exception;

    /**
     * 目安箱の未読回答のある要望件数取得メソッド
     *
     * @param userId ユーザID
     * @return 目安箱未読件数
     * @throws Exception 例外
     */
    public Integer getSugBoxUnreadItems(String userId) throws Exception;

}
