package com.sbisec.helios.ap.suggestionBox.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.suggestionBox.dao.IfaSuggestionBoxPersonalRegisterDao;
import com.sbisec.helios.ap.suggestionBox.dao.mapper.IfaSuggestionBoxPersonalRegisterMapper;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalRegisterSql001ResponseModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalRegisterSql002RequestModel;

/**
 * 画面ID：SUB00_01-06_2
 * 画面名：要望事項 新規登録
 *
 * 2025/06/30 新規作成
 */

@Component
public class IfaSuggestionBoxPersonalRegisterDaoImpL extends RowSelectableDao implements IfaSuggestionBoxPersonalRegisterDao {
    
    @Autowired
    private IfaSuggestionBoxPersonalRegisterMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：要望No取得
     * SQLタイプ：select
     * レスポンスクラス：IfaSuggestionBoxPersonalRegisterSql001ResponseModel
     */
    @Override
    public DataList<IfaSuggestionBoxPersonalRegisterSql001ResponseModel> selectIfaSuggestionBoxPersonalRegisterSql001() throws Exception {

        DataList<IfaSuggestionBoxPersonalRegisterSql001ResponseModel> res = new DataList<IfaSuggestionBoxPersonalRegisterSql001ResponseModel>();

        res.setDataList(mapper.selectIfaSuggestionBoxPersonalRegisterSql001());

        return res;

    }
    
    /**
     * SQLID：Sql002
     * SQL名：要望登録
     * SQLタイプ：insert
     * リクエストクラス：IfaSuggestionBoxPersonalRegisterSql002RequestModel
     * レスポンスクラス：int
     *
     * @param req リクエスト
     * @return 登録件数
     * @exception Exception システムエラー
     */
    @Override
    public int insertIfaSuggestionBoxPersonalRegisterSql002(
    		IfaSuggestionBoxPersonalRegisterSql002RequestModel req
        ) throws Exception {
        
        return mapper.insertIfaSuggestionBoxPersonalRegisterSql002(req);
    }

}
