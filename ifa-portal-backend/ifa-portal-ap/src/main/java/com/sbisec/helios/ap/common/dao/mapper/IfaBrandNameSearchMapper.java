package com.sbisec.helios.ap.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.common.dao.model.IfaBrandNameSearchRequestModel;
import com.sbisec.helios.ap.common.dao.model.IfaBrandNameSearchResponseModel;

/**
 * 画面ID：SUB07-03_1 画面名：投信銘柄検索ポップアップ
 *
 * @author xin.huang
 */
@Mapper
public interface IfaBrandNameSearchMapper {

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
    public List<IfaBrandNameSearchResponseModel> brandNameSearch(@Param("req") IfaBrandNameSearchRequestModel req)
            throws Exception;
}
