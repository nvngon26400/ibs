package com.sbisec.helios.ap.bizcommon.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct020Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct020Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct020Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct020Sql002ResponseModel;

import com.sbisec.helios.ap.common.annotation.dao.MariadbMapper;
import org.apache.ibatis.annotations.Param;

/**
 * FCT020 国内株リアル時価取得Mapper
 *
 * @author SCSK
 */
@MariadbMapper
public interface  Fct020Mapper {

    /**
     * 主要取引所コード取得
     * 株式銘柄マスタテーブルから、指定銘柄の主要取引所コードを取得する
     *
     * @param fct020Sql001RequestModel リクエスト
     * @return レスポンス
     */
    public List<Fct020Sql001ResponseModel>
            getPreExCode(@Param("fct020Sql001RequestModel")
                Fct020Sql001RequestModel fct020Sql001RequestModel);

    /**
     * 基準値取得
     * 銘柄翌日基準値テーブルから、指定銘柄・市場の基準値を取得する。
     *
     * @param fct020Sql002RequestModel リクエスト
     * @return レスポンス
     */
    public List<Fct020Sql002ResponseModel>
            getBasePrice(@Param("fct020Sql002RequestModel")
                Fct020Sql002RequestModel fct020Sql002RequestModel);

}
