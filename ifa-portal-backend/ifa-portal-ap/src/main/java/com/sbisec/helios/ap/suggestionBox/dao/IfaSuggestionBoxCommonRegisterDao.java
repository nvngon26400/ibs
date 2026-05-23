package com.sbisec.helios.ap.suggestionBox.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonRegisterSql001ResponseModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonRegisterSql003RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonRegisterSql004RequestModel;

/**
 * 画面ID：SUB0511_02-02
 * 画面名：皆様からの要望新規登録
 *
 2025/06/23 新規作成
 */

public interface IfaSuggestionBoxCommonRegisterDao {
    
    /**
     * SQLID：Sql001
     * SQL名：要望No取得
     * SQLタイプ：select
     * レスポンスクラス：IfaSuggestionBoxCommonRegisterSql001ResponseModel
     */
    public  DataList<IfaSuggestionBoxCommonRegisterSql001ResponseModel> selectIfaSuggestionBoxCommonRegisterSql001() throws Exception;

    /**
     * SQLID：Sql003
     * SQL名：要望登録
     * SQLタイプ：insert
     * リクエストクラス：IfaSuggestionBoxCommonRegisterSql003RequestModel
     * レスポンスクラス：int
     *
     * @param req リクエスト
     * @return 更新件数
     * @exception Exception システムエラー
     */
    public int insertIfaSuggestionBoxCommonRegisterSql003(
    		IfaSuggestionBoxCommonRegisterSql003RequestModel req
        ) throws Exception;

    
    /**
     * SQLID：Sql004
     * SQL名：回答登録
     * SQLタイプ：insert
     * リクエストクラス：IfaSuggestionBoxCommonRegisterSql004RequestModel
     * レスポンスクラス：int
     *
     * @param req リクエスト
     * @return 登録件数
     * @exception Exception システムエラー
     */
    public int insertIfaSuggestionBoxCommonRegisterSql004(
    		IfaSuggestionBoxCommonRegisterSql004RequestModel req
        ) throws Exception;


}
