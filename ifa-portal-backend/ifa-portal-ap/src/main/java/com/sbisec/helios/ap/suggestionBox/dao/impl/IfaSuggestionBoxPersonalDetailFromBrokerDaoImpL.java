package com.sbisec.helios.ap.suggestionBox.dao.impl;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.suggestionBox.dao.IfaSuggestionBoxPersonalDetailFromBrokerDao;
import com.sbisec.helios.ap.suggestionBox.dao.mapper.IfaSuggestionBoxPersonalDetailFromBrokerMapper;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailFromBrokerSql001RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailFromBrokerSql001ResponseModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailFromBrokerSql002RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailFromBrokerSql002ResponseModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailFromBrokerSql003RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailFromBrokerSql004RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailFromBrokerSql005RequestModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0511_01-02
 * 画面名：仲介業者からの要望詳細
 * @author SCSK山岸
 * 2025/07/25 新規作成
 */
@Component
public class IfaSuggestionBoxPersonalDetailFromBrokerDaoImpL implements IfaSuggestionBoxPersonalDetailFromBrokerDao {

    @Autowired
    private IfaSuggestionBoxPersonalDetailFromBrokerMapper mapper;

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
    @Override
    public DataList<IfaSuggestionBoxPersonalDetailFromBrokerSql001ResponseModel> selectIfaSuggestionBoxPersonalDetailFromBrokerSql001(
            IfaSuggestionBoxPersonalDetailFromBrokerSql001RequestModel req
        ) throws Exception {

        DataList<IfaSuggestionBoxPersonalDetailFromBrokerSql001ResponseModel> res = new DataList<IfaSuggestionBoxPersonalDetailFromBrokerSql001ResponseModel>();

        res.setDataList(mapper.selectIfaSuggestionBoxPersonalDetailFromBrokerSql001(req));

        return res;
    }

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
    @Override
    public DataList<IfaSuggestionBoxPersonalDetailFromBrokerSql002ResponseModel> selectIfaSuggestionBoxPersonalDetailFromBrokerSql002(
    		IfaSuggestionBoxPersonalDetailFromBrokerSql002RequestModel req
        ) throws Exception {

        DataList<IfaSuggestionBoxPersonalDetailFromBrokerSql002ResponseModel> res = new DataList<IfaSuggestionBoxPersonalDetailFromBrokerSql002ResponseModel>();

        res.setDataList(mapper.selectIfaSuggestionBoxPersonalDetailFromBrokerSql002(req));

        return res;
    }

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
    @Override
    public int updateIfaSuggestionBoxPersonalDetailFromBrokerSql003(
            IfaSuggestionBoxPersonalDetailFromBrokerSql003RequestModel req
        ) throws Exception {
        
        return mapper.updateIfaSuggestionBoxPersonalDetailFromBrokerSql003(req);
    }

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
    @Override
    public int insertIfaSuggestionBoxPersonalDetailFromBrokerSql004(
            IfaSuggestionBoxPersonalDetailFromBrokerSql004RequestModel req
        ) throws Exception {

        return mapper.insertIfaSuggestionBoxPersonalDetailFromBrokerSql004(req);
    }

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
    @Override
    public int updateIfaSuggestionBoxPersonalDetailFromBrokerSql005(
            IfaSuggestionBoxPersonalDetailFromBrokerSql005RequestModel req
        ) throws Exception {

        return mapper.updateIfaSuggestionBoxPersonalDetailFromBrokerSql005(req);
    }
}
