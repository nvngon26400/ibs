package com.sbisec.helios.ap.bizcommon.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct008Sql002ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct008Sql003RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct008Sql003ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 共通関数Mapper：FCT008
 *
 * @author SCSK
 */

@Mapper
public interface Fct008Mapper {
    
    /**
     * 営業日取得
     * 備考：カレンダーマスタ（M_CALENDAR）より、現在日付以降の営業日（16営業日分）を取得する
     *
     * @return List 現在日付以降の営業日リスト(YYYYMMDD)
     */
    List<String> getBusinessDayList();
    
    /**
     * 優先市場の取得
     * 備考：銘柄マスタ（I_PRODUCT_MASTER）より、優先市場を取得する
     *
     * @param brandCode 銘柄コード
     * @return String　優先市場
     */
    Fct008Sql002ResponseModel getMarket(@Param("brandCode") String brandCode);

    Fct008Sql003ResponseModel getOverTime(@Param("req") Fct008Sql003RequestModel requestModel);
}
