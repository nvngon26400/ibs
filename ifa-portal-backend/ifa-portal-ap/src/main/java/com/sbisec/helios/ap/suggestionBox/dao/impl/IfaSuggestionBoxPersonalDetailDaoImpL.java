package com.sbisec.helios.ap.suggestionBox.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.suggestionBox.dao.IfaSuggestionBoxPersonalDetailDao;
import com.sbisec.helios.ap.suggestionBox.dao.mapper.IfaSuggestionBoxPersonalDetailMapper;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailSql001RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailSql001ResponseModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailSql002RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailSql002ResponseModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailSql003RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailSql004RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalDetailSql006RequestModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB00_01-06_3
 * 画面名：要望事項 詳細
 * @author SCSK神木
 * 2025/06/20 新規作成
 */
@Component
public class IfaSuggestionBoxPersonalDetailDaoImpL extends RowSelectableDao implements IfaSuggestionBoxPersonalDetailDao {
    
    @Autowired
    private IfaSuggestionBoxPersonalDetailMapper mapper;

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
    public DataList<IfaSuggestionBoxPersonalDetailSql001ResponseModel> selectIfaSuggestionBoxPersonalDetailSql001(
    		IfaSuggestionBoxPersonalDetailSql001RequestModel req) throws Exception {
        
        DataList<IfaSuggestionBoxPersonalDetailSql001ResponseModel> res = new DataList<IfaSuggestionBoxPersonalDetailSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaSuggestionBoxPersonalDetailSql001(req));
        return res;
    }

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
    public DataList<IfaSuggestionBoxPersonalDetailSql002ResponseModel> selectIfaSuggestionBoxPersonalDetailSql002(
    		IfaSuggestionBoxPersonalDetailSql002RequestModel req) throws Exception {
        
        DataList<IfaSuggestionBoxPersonalDetailSql002ResponseModel> res = new DataList<IfaSuggestionBoxPersonalDetailSql002ResponseModel>();
        
        res.setDataList(mapper.selectIfaSuggestionBoxPersonalDetailSql002(req));
        return res;
    }

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
    public int updateIfaSuggestionBoxPersonalDetailSql003(IfaSuggestionBoxPersonalDetailSql003RequestModel req) throws Exception {
        
        return mapper.updateIfaSuggestionBoxPersonalDetailSql003(req);
    }

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
    @Override
    public Integer selectIfaSuggestionBoxPersonalDetailSql004(IfaSuggestionBoxPersonalDetailSql004RequestModel req) throws Exception {
        
        return mapper.selectIfaSuggestionBoxPersonalDetailSql004(req);
    }

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
    public int updateIfaSuggestionBoxPersonalDetailSql006(IfaSuggestionBoxPersonalDetailSql006RequestModel req) throws Exception {
        
        return mapper.updateIfaSuggestionBoxPersonalDetailSql006(req);
    }

}
