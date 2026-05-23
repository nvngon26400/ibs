package com.sbisec.helios.ap.suggestionBox.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonRegisterSql001ResponseModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonRegisterSql003RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonRegisterSql004RequestModel;


/**
 * 画面ID：SUB0511_02-02
 * 画面名：皆様からの要望新規登録
 *
 2025/06/23 新規作成
 */

@Mapper
public interface IfaSuggestionBoxCommonRegisterMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：要望No取得
     * SQLタイプ：select
     * @return 要望No
     * @exception Exception システムエラー
     */
    public List<IfaSuggestionBoxCommonRegisterSql001ResponseModel> selectIfaSuggestionBoxCommonRegisterSql001() throws Exception;

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
            @Param("req") IfaSuggestionBoxCommonRegisterSql003RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：回答登録
     * SQLタイプ：insert
     * リクエストクラス：IfaSuggestionBoxCommonRegisterSql004RequestModel
     * レスポンスクラス：int
     *
     * @param req リクエスト
     * @return 更新件数
     * @exception Exception システムエラー
     */
    public int insertIfaSuggestionBoxCommonRegisterSql004(
            @Param("req") IfaSuggestionBoxCommonRegisterSql004RequestModel req
        ) throws Exception;
}
