package com.sbisec.helios.ap.bizcommon.dao.mapper;


import com.sbisec.helios.ap.bizcommon.dao.model.Fct017Sql001ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 共通関数MAPPER：FCT017
 * 確認書受入および強制注文が必要な国内投信銘柄情報取得

 * @author SCSK
 */

@Mapper
public interface Fct017Mapper {

    /**
     * SQL001:受入書類取得
     *
     * @param nriCd NRIコード
     * @return Fct017Sql001ResponseModel 受入書類
     */
    Fct017Sql001ResponseModel acceptanceAcquire(@Param("nriCd") String nriCd);
    
    /**
     * SQL002:銘柄別書類受入状況取得
     *
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @param brandCode 銘柄コード
     * @param shoruiCd 書類コード
     * @return Integer 抽出件数
     */
    int brandDocumentAcceptanceAcquire(@Param("butenCode") String butenCode,
            @Param("accountNumber") String accountNumber,
            @Param("brandCode")String brandCode,
            @Param("shoruiCd")String shoruiCd);
    
    /**
     * SQL003:共通書類受入状況取得
     *
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @param shoruiCd 書類コード
     * @return Integer 抽出件数
     */
    int commonDocumentAcceptanceAcquire(@Param("butenCode") String butenCode,
            @Param("accountNumber") String accountNumber,
            @Param("shoruiCd")String shoruiCd);
    
}
