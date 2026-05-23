package com.sbisec.helios.ap.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.common.dao.model.IfaAddressSearchRequestModel;
import com.sbisec.helios.ap.common.dao.model.IfaAddressSearchResponseModel;

/**
 * 画面ID：SUB07-01 画面名：住所検索ポップアップ
 *
 * @author xin.huang
 */
@Mapper
public interface IfaAddressSearchMapper {

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
    public List<IfaAddressSearchResponseModel> addressSearch(@Param("req") IfaAddressSearchRequestModel req)
            throws Exception;
}
