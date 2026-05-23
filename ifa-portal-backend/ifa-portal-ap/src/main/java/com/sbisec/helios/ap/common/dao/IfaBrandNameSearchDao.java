package com.sbisec.helios.ap.common.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.dao.model.IfaBrandNameSearchRequestModel;
import com.sbisec.helios.ap.common.dao.model.IfaBrandNameSearchResponseModel;

/**
 * 画面ID：SUB07-03_1 画面名：投信銘柄検索ポップアップ
 *
 * @author xin.huang
 */
public interface IfaBrandNameSearchDao {
    
    /**
     * SQLID：sql001
     * アクション名：投信銘柄検索
     * Dtoリクエスト：IfaBrandNameSearchRequestDto
     * Dtoレスポンス：IfaBrandNameSearchResponseDto
     * Modelリクエスト：IfaBrandNameSearchRequestModel
     * Modelレスポンス：IfaBrandNameSearchResponseModel
     *
     * @param req {@code IfaBrandNameSearchRequestModel }
     * @return {@code DataList <IfaBrandNameSearchResponseModel>}
     * @throws Exception投信銘柄検索処理で例外が発生した場合
     */
    public DataList<IfaBrandNameSearchResponseModel> brandNameSearch(IfaBrandNameSearchRequestModel req) throws Exception;
}
