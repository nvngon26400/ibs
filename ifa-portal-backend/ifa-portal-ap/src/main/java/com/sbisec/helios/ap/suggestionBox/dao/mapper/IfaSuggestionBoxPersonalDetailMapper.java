package com.sbisec.helios.ap.suggestionBox.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailSql001RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailSql001ResponseModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailSql002RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailSql002ResponseModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailSql003RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailSql004RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailSql006RequestModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 画面ID：SUB00_01-06_3
 * 画面名：要望事項 詳細
 * @author SCSK神木
 * 2025/06/20 新規作成
 */
@Mapper
public interface IfaSuggestionBoxPersonalDetailMapper {

    /**
     * SQLID：Sql001
     * SQL名：要望取得
     * SQLタイプ：select
     * リクエストクラス：IfaSuggestionBoxPersonalDetailSql001RequestModel
     * レスポンスクラス：IfaSuggestionBoxPersonalDetailSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaSuggestionBoxPersonalDetailSql001ResponseModel> selectIfaSuggestionBoxPersonalDetailSql001(
            @Param("req") IfaSuggestionBoxPersonalDetailSql001RequestModel req) throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：回答取得
     * SQLタイプ：select
     * リクエストクラス：IfaSuggestionBoxPersonalDetailSql002RequestModel
     * レスポンスクラス：IfaSuggestionBoxPersonalDetailSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaSuggestionBoxPersonalDetailSql002ResponseModel> selectIfaSuggestionBoxPersonalDetailSql002(
            @Param("req") IfaSuggestionBoxPersonalDetailSql002RequestModel req) throws Exception;

    /**
     * SQLID：Sql003
     * SQL名：回答既読更新
     * SQLタイプ：update
     * リクエストクラス：IfaSuggestionBoxPersonalDetailSql003RequestModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int updateIfaSuggestionBoxPersonalDetailSql003(@Param("req") IfaSuggestionBoxPersonalDetailSql003RequestModel req)
    		throws Exception;

    /**
     * SQLID：Sql004
     * SQL名：未読回答のある要望件数取得
     * SQLタイプ：select
     * リクエストクラス：IfaSuggestionBoxPersonalDetailSql004RequestModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public Integer selectIfaSuggestionBoxPersonalDetailSql004(@Param("req") IfaSuggestionBoxPersonalDetailSql004RequestModel req)
    		throws Exception;

    /**
     * SQLID：Sql006
     * SQL名：要望更新
     * SQLタイプ：update
     * リクエストクラス：IfaSuggestionBoxPersonalDetailSql006RequestModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int updateIfaSuggestionBoxPersonalDetailSql006(@Param("req") IfaSuggestionBoxPersonalDetailSql006RequestModel req)
    		throws Exception;

}
