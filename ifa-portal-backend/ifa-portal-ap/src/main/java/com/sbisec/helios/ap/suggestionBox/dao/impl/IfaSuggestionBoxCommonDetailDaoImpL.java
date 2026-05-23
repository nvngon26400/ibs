package com.sbisec.helios.ap.suggestionBox.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.suggestionBox.dao.IfaSuggestionBoxCommonDetailDao;
import com.sbisec.helios.ap.suggestionBox.dao.mapper.IfaSuggestionBoxCommonDetailMapper;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonDetailSql001RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonDetailSql001ResponseModel;


/**
 * 画面ID：SUB00_02, SUB0511_02
 * 画面名：皆様からの要望
 * 2025/06/18 新規作成
 *
 * @author SCSK 林
 */

@Component
public class IfaSuggestionBoxCommonDetailDaoImpL extends RowSelectableDao implements IfaSuggestionBoxCommonDetailDao {
    
    @Autowired
    private IfaSuggestionBoxCommonDetailMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：皆様からの要望詳細取得
     * SQLタイプ：select
     * リクエストクラス：IfaSuggestionBoxCommonDetailSql001RequestModel
     * レスポンスクラス：IfaSuggestionBoxCommonDetailSql001ResponseModel
     *
     * @param req パラメータ
     * @return 皆様からの要望詳細
     * @exception Exception システムエラー
     */
    @Override
    public DataList<IfaSuggestionBoxCommonDetailSql001ResponseModel> selectIfaSugBoxCommonDetailSql001(
    		IfaSuggestionBoxCommonDetailSql001RequestModel req) throws Exception
    {
        DataList<IfaSuggestionBoxCommonDetailSql001ResponseModel>  res = new DataList<IfaSuggestionBoxCommonDetailSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaSugBoxCommonDetailSql001(req));
        
        return res;
        
    }
    
    
    
}
