package com.sbisec.helios.ap.suggestionBox.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

@Mapper
public interface IfaSuggestionBoxCommonUpdateMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：皆様からの要望詳細取得
     * SQLタイプ：select
     * リクエストクラス：IfaSuggestionBoxCommonUpdateSql001RequestModel
     * レスポンスクラス：IfaSuggestionBoxCommonUpdateSql001ResponseModel
     *
     * @param req パラメータ
     * @return 皆様からの要望更新詳細
     * @exception Exception システムエラー
     */
    public List<IfaSuggestionBoxCommonUpdateSql001ResponseModel> selectIfaSuggestionBoxCommonUpdateSql001(
            @Param("req") IfaSuggestionBoxCommonUpdateSql001RequestModel req
    ) throws Exception;

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
            @Param("req") IfaSuggestionBoxCommonUpdateSql003RequestModel req
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
            @Param("req") IfaSuggestionBoxCommonUpdateSql004RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql005
     * SQL名：回答登録
     * SQLタイプ：insert
     * リクエストクラス：IfaSuggestionBoxCommonUpdateSql005RequestModel
     * レスポンスクラス：int
     *
     * @param req リクエスト
     * @return 更新件数
     * @exception Exception システムエラー
     */
    public int insertIfaSuggestionBoxCommonUpdateSql005(
            @Param("req") IfaSuggestionBoxCommonUpdateSql005RequestModel req
        ) throws Exception;
}
