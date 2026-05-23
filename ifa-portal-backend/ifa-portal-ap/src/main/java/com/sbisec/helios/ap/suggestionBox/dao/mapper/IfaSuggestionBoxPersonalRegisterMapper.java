package com.sbisec.helios.ap.suggestionBox.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalRegisterSql001ResponseModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalRegisterSql002RequestModel;


/**
 * 画面ID：SUB00_01-06_2
 * 画面名：要望事項 新規登録
 *
 * 2025/06/30 新規作成
 */

@Mapper
public interface IfaSuggestionBoxPersonalRegisterMapper {

    /**
     * SQLID：Sql001
     * SQL名：要望No取得
     * SQLタイプ：select
     * @return 要望No
     * @exception Exception システムエラー
     */
    public List<IfaSuggestionBoxPersonalRegisterSql001ResponseModel> selectIfaSuggestionBoxPersonalRegisterSql001() throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：要望登録
     * SQLタイプ：insert
     * リクエストクラス：IfaSuggestionBoxPersonalRegisterSql002RequestModel
     * レスポンスクラス：int
     *
     * @param req リクエスト
     * @return 更新件数
     * @exception Exception システムエラー
     */
    public int insertIfaSuggestionBoxPersonalRegisterSql002(
            @Param("req") IfaSuggestionBoxPersonalRegisterSql002RequestModel req
        ) throws Exception;

}
