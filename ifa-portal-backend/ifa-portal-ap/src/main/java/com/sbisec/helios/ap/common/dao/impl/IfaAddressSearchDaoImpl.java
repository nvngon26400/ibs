package com.sbisec.helios.ap.common.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.dao.IfaAddressSearchDao;
import com.sbisec.helios.ap.common.dao.mapper.IfaAddressSearchMapper;
import com.sbisec.helios.ap.common.dao.model.IfaAddressSearchRequestModel;
import com.sbisec.helios.ap.common.dao.model.IfaAddressSearchResponseModel;

/**
 * 画面ID：SUB07-01 画面名：住所検索ポップアップ
 *
 * @author xin.huang
 */
@Repository
public class IfaAddressSearchDaoImpl implements IfaAddressSearchDao {
    
    @Autowired
    private IfaAddressSearchMapper mapper;
    
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
    public DataList<IfaAddressSearchResponseModel> addressSearch(IfaAddressSearchRequestModel req) throws Exception {
        DataList<IfaAddressSearchResponseModel> res = new DataList<IfaAddressSearchResponseModel>();
        res.setDataList(mapper.addressSearch(req));
        return res;
    }
}
