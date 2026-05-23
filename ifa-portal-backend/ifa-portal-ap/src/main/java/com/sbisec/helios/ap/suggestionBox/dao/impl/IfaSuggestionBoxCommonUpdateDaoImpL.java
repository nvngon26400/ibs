package com.sbisec.helios.ap.suggestionBox.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.suggestionBox.dao.IfaSuggestionBoxCommonUpdateDao;
import com.sbisec.helios.ap.suggestionBox.dao.mapper.IfaSuggestionBoxCommonUpdateMapper;
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

@Component
public class IfaSuggestionBoxCommonUpdateDaoImpL extends RowSelectableDao implements IfaSuggestionBoxCommonUpdateDao {
    
    @Autowired
    private IfaSuggestionBoxCommonUpdateMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：皆様からの要望詳細取得
     * SQLタイプ：select
     * リクエストクラス：IfaSuggestionBoxCommonUpdateSql001RequestModel
     * レスポンスクラス：IfaSuggestionBoxCommonUpdateSql001ResponseModel
     *
     * @param req パラメータ
     * @return 皆様からの要望詳細取得
     * @exception Exception システムエラー
     */
    @Override
    public DataList<IfaSuggestionBoxCommonUpdateSql001ResponseModel> selectIfaSuggestionBoxCommonUpdateSql001(
    		IfaSuggestionBoxCommonUpdateSql001RequestModel req) throws Exception
    {
        DataList<IfaSuggestionBoxCommonUpdateSql001ResponseModel>  res = new DataList<IfaSuggestionBoxCommonUpdateSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaSuggestionBoxCommonUpdateSql001(req));
        
        return res;
        
    }
    
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
    @Override
    public int updateIfaSuggestionBoxCommonUpdateSql003(
            IfaSuggestionBoxCommonUpdateSql003RequestModel req
        ) throws Exception {
        
        return mapper.updateIfaSuggestionBoxCommonUpdateSql003(req);
    }

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
    @Override
    public int updateIfaSuggestionBoxCommonUpdateSql004(
            IfaSuggestionBoxCommonUpdateSql004RequestModel req
        ) throws Exception {
        
        return mapper.updateIfaSuggestionBoxCommonUpdateSql004(req);
    }
    
    /**
     * SQLID：Sql005
     * SQL名：回答登録
     * SQLタイプ：update
     * リクエストクラス：IfaSuggestionBoxCommonUpdateSql005RequestModel
     * レスポンスクラス：int
     *
     * @param req リクエスト
     * @return 更新件数
     * @exception Exception システムエラー
     */
    @Override
    public int insertIfaSuggestionBoxCommonUpdateSql005(
            IfaSuggestionBoxCommonUpdateSql005RequestModel req
        ) throws Exception {
        
        return mapper.insertIfaSuggestionBoxCommonUpdateSql005(req);
    }
}
