package com.sbisec.helios.ap.suggestionBox.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.suggestionBox.dao.IfaSuggestionBoxCommonRegisterDao;
import com.sbisec.helios.ap.suggestionBox.dao.mapper.IfaSuggestionBoxCommonRegisterMapper;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonRegisterSql001ResponseModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonRegisterSql003RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonRegisterSql004RequestModel;

/**
 * 画面ID：SUB0511_02-02
 * 画面名：皆様からの要望新規登録
 *
 2025/06/23 新規作成
 */

@Component
public class IfaSuggestionBoxCommonRegisterDaoImpL extends RowSelectableDao implements IfaSuggestionBoxCommonRegisterDao {
    
    @Autowired
    private IfaSuggestionBoxCommonRegisterMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：要望No取得
     * SQLタイプ：select
     * @return 要望No
     * @exception Exception システムエラー
     */
    @Override
    public DataList<IfaSuggestionBoxCommonRegisterSql001ResponseModel> selectIfaSuggestionBoxCommonRegisterSql001() throws Exception {

    	DataList<IfaSuggestionBoxCommonRegisterSql001ResponseModel> res = new DataList<IfaSuggestionBoxCommonRegisterSql001ResponseModel>();
    	
    	res.setDataList(mapper.selectIfaSuggestionBoxCommonRegisterSql001());
    	
    	return res;

    }
    
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
    @Override
    public int insertIfaSuggestionBoxCommonRegisterSql003(
    		IfaSuggestionBoxCommonRegisterSql003RequestModel req
        ) throws Exception {
        
        return mapper.insertIfaSuggestionBoxCommonRegisterSql003(req);
    }

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
    @Override
    public int insertIfaSuggestionBoxCommonRegisterSql004(
    		IfaSuggestionBoxCommonRegisterSql004RequestModel req
        ) throws Exception {
        
        return mapper.insertIfaSuggestionBoxCommonRegisterSql004(req);
    }
}
