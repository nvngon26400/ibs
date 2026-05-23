package com.sbisec.helios.ap.suggestionBox.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.suggestionBox.dao.IfaSuggestionBoxPersonalDao;
import com.sbisec.helios.ap.suggestionBox.dao.mapper.IfaSuggestionBoxPersonalMapper;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalSql001ResponseModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalSql002RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalSql003RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalSql002_3ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB00_01-06_1
 * 画面名：あなたの要望
 * @author SCSK神木
 * 2025/06/12 新規作成
 */
@Component
public class IfaSuggestionBoxPersonalDaoImpL extends RowSelectableDao implements IfaSuggestionBoxPersonalDao {
    
    @Autowired
    private IfaSuggestionBoxPersonalMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：画面コメント取得
     * SQLタイプ：select
     * レスポンスクラス：IfaSuggestionBoxPersonalSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSuggestionBoxPersonalSql001ResponseModel> selectIfaSuggestionBoxPersonalSql001() throws Exception {
        
        DataList<IfaSuggestionBoxPersonalSql001ResponseModel> res = new DataList<IfaSuggestionBoxPersonalSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaSuggestionBoxPersonalSql001());
        return res;
    }


    /**
     * SQLID：Sql002
     * SQL名：仲介業者自身の要望一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaSuggestionBoxPersonalSql002RequestModel
     * レスポンスクラス：IfaSuggestionBoxPersonalSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSuggestionBoxPersonalSql002_3ResponseModel> selectIfaSuggestionBoxPersonalSql002(
    		IfaSuggestionBoxPersonalSql002RequestModel req) throws Exception {
        
        DataList<IfaSuggestionBoxPersonalSql002_3ResponseModel> res = new DataList<IfaSuggestionBoxPersonalSql002_3ResponseModel>();
        
        res.setDataList(mapper.selectIfaSuggestionBoxPersonalSql002(req));
        return res;
    }

    /**
     * SQLID：Sql003
     * SQL名：証券社員参照範囲の要望一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaSuggestionBoxPersonalSql003RequestModel
     * レスポンスクラス：IfaSuggestionBoxPersonalSql03ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSuggestionBoxPersonalSql002_3ResponseModel> selectIfaSuggestionBoxPersonalSql003(
    		IfaSuggestionBoxPersonalSql003RequestModel req) throws Exception {
        
        DataList<IfaSuggestionBoxPersonalSql002_3ResponseModel> res = new DataList<IfaSuggestionBoxPersonalSql002_3ResponseModel>();
        
        res.setDataList(mapper.selectIfaSuggestionBoxPersonalSql003(req));
        return res;
    }

}
