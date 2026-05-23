package com.sbisec.helios.ap.suggestionBox.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.suggestionBox.dao.IfaSuggestionBoxCommonDao;
import com.sbisec.helios.ap.suggestionBox.dao.mapper.IfaSuggestionBoxCommonMapper;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonSql002RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonSql002ResponseModel;


/**
 * 画面ID：SUB00_02-06_1, SUB0511_02-01
 * 画面名：皆様からの要望
 * 2025/06/18 新規作成
 *
 * @author SCSK 林
 */
@Component
public class IfaSuggestionBoxCommonDaoImpL extends RowSelectableDao implements IfaSuggestionBoxCommonDao {
    
    @Autowired
    private IfaSuggestionBoxCommonMapper mapper;
    
    /**
     * SQLID：Sql002
     * SQL名：皆様からの要望一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaSuggestionBoxCommonSql002RequestModel
     * レスポンスクラス：IfaSuggestionBoxCommonSql002ResponseModel
     *
     * @param req パラメータ
     * @return 皆様からの要望一覧
     * @exception Exception システムエラー
     */
    @Override
    public DataList<IfaSuggestionBoxCommonSql002ResponseModel> selectIfaSuggestionBoxCommonSql002(
    		IfaSuggestionBoxCommonSql002RequestModel req) throws Exception
    {
        DataList<IfaSuggestionBoxCommonSql002ResponseModel>  res = new DataList<IfaSuggestionBoxCommonSql002ResponseModel>();
        
        res.setDataList(mapper.selectIfaSugBoxCommonSql002(req));
        
        return res;
        
    }
    
    
    
}
