package com.sbisec.helios.ap.suggestionBox.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonSql002RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonSql002ResponseModel;

/**
 * 画面ID：SUB00_02-06_1・SUB0511_02-01
 * 画面名：皆様からの要望
 * @author 林
 * 
 * 2025/06/18 新規作成
 *
 */
public interface IfaSuggestionBoxCommonDao {
    
    /**
     * SQLID：Sql002
     * SQL名：皆様からの要望一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaSuggestionBoxCommonSql002Request
     * レスポンスクラス：IfaSuggestionBoxCommonSql002Response
     */
    public DataList<IfaSuggestionBoxCommonSql002ResponseModel> selectIfaSuggestionBoxCommonSql002(
    		IfaSuggestionBoxCommonSql002RequestModel req) throws Exception;

}
