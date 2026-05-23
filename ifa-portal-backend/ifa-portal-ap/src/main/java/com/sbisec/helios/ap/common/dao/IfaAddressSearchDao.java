package com.sbisec.helios.ap.common.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.dao.model.IfaAddressSearchRequestModel;
import com.sbisec.helios.ap.common.dao.model.IfaAddressSearchResponseModel;

/**
 * 画面ID：SUB07-01 画面名：住所検索ポップアップ
 *
 * @author xin.huang
 */
public interface IfaAddressSearchDao {
    
    /**
     * SQLID：sql001
     * SQL名：住所検索
     * SQLタイプ：select 
     * リクエスト：IfaAddressSearchRequestModel
     * レスポンス：IfaAddressSearchResponseModel
     *
     * @param req {@code IfaAddressSearchRequestModel }
     * @return {@code DataList <IfaAddressSearchResponseModel>}
     * @throws Exception住所検索処理で例外が発生した場合
     */
    public DataList<IfaAddressSearchResponseModel> addressSearch(IfaAddressSearchRequestModel req) throws Exception;
}
