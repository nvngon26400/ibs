package com.sbisec.helios.ap.suggestionBox.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonUpdateSql001RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonUpdateSql001ResponseModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonUpdateSql003RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonUpdateSql004RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonUpdateSql005RequestModel;

/**
 * 画面ID：SUB0511_02-03
 * 画面名：皆様からの要望更新
 *
 2025/06/23 新規作成
 */

public interface IfaSuggestionBoxCommonUpdateDao {
    
    /**
     * SQLID：Sql001
     * SQL名：皆様からの要望更新詳細取得
     * SQLタイプ：select
     * リクエストクラス：IfaSuggestionBoxCommonUpdateSql001RequestModel
     * レスポンスクラス：IfaSuggestionBoxCommonUpdateSql001ResponseModel
     */
    public DataList<IfaSuggestionBoxCommonUpdateSql001ResponseModel> selectIfaSuggestionBoxCommonUpdateSql001(
    		IfaSuggestionBoxCommonUpdateSql001RequestModel req) throws Exception;

    /**
     * SQLID：Sql003
     * SQL名：要望更新
     * SQLタイプ：update
     * リクエストクラス：IfaSuggestionBoxCommonUpdateSql003RequestModel
     * レスポンスクラス：int
     *
     * @param req リクエスト
     * @return 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaSuggestionBoxCommonUpdateSql003(
            IfaSuggestionBoxCommonUpdateSql003RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：回答更新
     * SQLタイプ：update
     * リクエストクラス：IfaSuggestionBoxCommonUpdateSql004RequestModel
     * レスポンスクラス：int
     *
     * @param req リクエスト
     * @return 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaSuggestionBoxCommonUpdateSql004(
            IfaSuggestionBoxCommonUpdateSql004RequestModel req
        ) throws Exception;

    
    /**
     * SQLID：Sql005
     * SQL名：回答登録
     * SQLタイプ：insert
     * リクエストクラス：IfaSuggestionBoxCommonUpdateSql005RequestModel
     * レスポンスクラス：int
     *
     * @param req リクエスト
     * @return 登録件数
     * @exception Exception システムエラー
     */
    public int insertIfaSuggestionBoxCommonUpdateSql005(
    		IfaSuggestionBoxCommonUpdateSql005RequestModel req
        ) throws Exception;


}
