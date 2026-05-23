package com.sbisec.helios.ap.suggestionBox.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.service.Service;

/**
 * 目安箱共通処理
 */

public interface IfaSuggestionBoxService extends Service {
    
    /**
     * 
     * Dto レスポンス：String
     * @param catId カテゴリID
     * @return DataList<String>
     * @exception Exception 例外
     * @see <reference item>
     */
    public DataList<String> getSugBoxFileDir(String catId) throws Exception;

}
