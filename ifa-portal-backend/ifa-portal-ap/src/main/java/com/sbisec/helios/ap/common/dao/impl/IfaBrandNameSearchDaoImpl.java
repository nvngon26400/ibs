package com.sbisec.helios.ap.common.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.dao.IfaBrandNameSearchDao;
import com.sbisec.helios.ap.common.dao.mapper.IfaBrandNameSearchMapper;
import com.sbisec.helios.ap.common.dao.model.IfaBrandNameSearchRequestModel;
import com.sbisec.helios.ap.common.dao.model.IfaBrandNameSearchResponseModel;

/**
 * 画面ID：SUB07-03_1 画面名：投信銘柄検索ポップアップ
 *
 * @author xin.huang
 */
@Repository
public class IfaBrandNameSearchDaoImpl implements IfaBrandNameSearchDao {
    
    @Autowired
    private IfaBrandNameSearchMapper mapper;
    
    /**
     * SQLID：sql001
     * アクション名：投信銘柄検索
     * Modelリクエスト：IfaBrandNameSearchRequestModel
     * Modelレスポンス：IfaBrandNameSearchResponseModel
     *
     * @param req {@code IfaBrandNameSearchRequestModel }
     * @return {@code DataList <IfaBrandNameSearchResponseModel>}
     * @throws Exception投信銘柄検索処理で例外が発生した場合
     */
    public DataList<IfaBrandNameSearchResponseModel> brandNameSearch(IfaBrandNameSearchRequestModel req) throws Exception {
        DataList<IfaBrandNameSearchResponseModel> res = new DataList<IfaBrandNameSearchResponseModel>();
        res.setDataList(mapper.brandNameSearch(req));
        return res;
    }
}
