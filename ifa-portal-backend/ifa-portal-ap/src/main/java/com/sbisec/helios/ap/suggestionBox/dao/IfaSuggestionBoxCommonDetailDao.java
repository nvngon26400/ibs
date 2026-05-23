package com.sbisec.helios.ap.suggestionBox.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonDetailSql001RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonDetailSql001ResponseModel;

/**
 * 画面ID：SUB00_02・SUB0511_02
 * 画面名：皆様からの要望
 * @author 林
 * 
 * 2025/06/18 新規作成
 *
 */
public interface IfaSuggestionBoxCommonDetailDao {
    
    /**
     * SQLID：Sql001
     * SQL名：皆様からの要望詳細取得
     * SQLタイプ：select
     * リクエストクラス：IfaSuggestionBoxCommonSql002Request
     * レスポンスクラス：IfaSuggestionBoxCommonSql002Response
     */
    public DataList<IfaSuggestionBoxCommonDetailSql001ResponseModel> selectIfaSugBoxCommonDetailSql001(
    		IfaSuggestionBoxCommonDetailSql001RequestModel req) throws Exception;

}
