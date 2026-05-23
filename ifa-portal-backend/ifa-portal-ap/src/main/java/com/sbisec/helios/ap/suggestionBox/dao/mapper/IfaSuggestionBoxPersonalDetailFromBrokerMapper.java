package com.sbisec.helios.ap.suggestionBox.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailFromBrokerSql001RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailFromBrokerSql001ResponseModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailFromBrokerSql002RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailFromBrokerSql002ResponseModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailFromBrokerSql003RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailFromBrokerSql004RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailFromBrokerSql005RequestModel;


/**
 * 画面ID：SUB0511_01-02
 * 画面名：仲介業者からの要望詳細
 * @author SCSK山岸
 * 2025/07/25 新規作成
 */
@Mapper
public interface IfaSuggestionBoxPersonalDetailFromBrokerMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：仲介業者からの要望取得
     * SQLタイプ：select
     * リクエストクラス：IfaSuggestionBoxPersonalDetailFromBrokerSql001RequestModel
     * レスポンスクラス：IfaSuggestionBoxPersonalDetailFromBrokerSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    public List<IfaSuggestionBoxPersonalDetailFromBrokerSql001ResponseModel> selectIfaSuggestionBoxPersonalDetailFromBrokerSql001(
            @Param("req") IfaSuggestionBoxPersonalDetailFromBrokerSql001RequestModel req
        ) throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：仲介業者からの要望回答取得
     * SQLタイプ：select
     * リクエストクラス：IfaSuggestionBoxPersonalDetailFromBrokerSql002RequestModel
     * レスポンスクラス：IfaSuggestionBoxPersonalDetailFromBrokerSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    public List<IfaSuggestionBoxPersonalDetailFromBrokerSql002ResponseModel> selectIfaSuggestionBoxPersonalDetailFromBrokerSql002(
            @Param("req") IfaSuggestionBoxPersonalDetailFromBrokerSql002RequestModel req
        ) throws Exception;

    /**
     * SQLID：Sql003
     * SQL名：回答更新
     * SQLタイプ：update
     * リクエストクラス：IfaSuggestionBoxPersonalDetailFromBrokerSql003RequestModel
     * レスポンスクラス：int
     *
     * @param req リクエスト
     * @return 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaSuggestionBoxPersonalDetailFromBrokerSql003(
            @Param("req") IfaSuggestionBoxPersonalDetailFromBrokerSql003RequestModel req
        ) throws Exception;

    /**
     * SQLID：Sql004
     * SQL名：回答登録
     * SQLタイプ：insert
     * リクエストクラス：IfaSuggestionBoxPersonalDetailFromBrokerSql004RequestModel
     * レスポンスクラス：int
     *
     * @param req リクエスト
     * @return 登録件数
     * @exception Exception システムエラー
     */
    public int insertIfaSuggestionBoxPersonalDetailFromBrokerSql004(
            @Param("req") IfaSuggestionBoxPersonalDetailFromBrokerSql004RequestModel req
        ) throws Exception;

    /**
     * SQLID：Sql005
     * SQL名：要望更新
     * SQLタイプ：update
     * リクエストクラス：IfaSuggestionBoxPersonalDetailFromBrokerSql005RequestModel
     * レスポンスクラス：int
     *
     * @param req リクエスト
     * @return 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaSuggestionBoxPersonalDetailFromBrokerSql005(
            @Param("req") IfaSuggestionBoxPersonalDetailFromBrokerSql005RequestModel req
        ) throws Exception;
}
