package com.sbisec.helios.ap.bizcommon.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct033Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct033Sql001ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * FCT033 営業日情報チェックMapper
 * 
 * @author SCSK
 *
 */
@Mapper
public interface  Fct033Mapper {

    /**
     * 営業日・非営業日区分取得
     * カレンダーマスタ（M_CALENDAR）からパラメータで渡された日付から営業日・非営業日区分を取得する

     * @param fct033Sql001RequestModel リクエスト
     * @return レスポンス
     * @throws Exception 営業日・非営業日区分取得時に例外が発生した場合
     */
    public List<Fct033Sql001ResponseModel>
            getBusinessDayType(@Param("fct033Sql001RequestModel")
                Fct033Sql001RequestModel fct033Sql001RequestModel) throws Exception;

}
