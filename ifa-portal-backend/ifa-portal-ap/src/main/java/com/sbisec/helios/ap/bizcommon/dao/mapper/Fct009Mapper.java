package com.sbisec.helios.ap.bizcommon.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 共通関数Mapper：FCT009

 * @author SCSK
 */

@Mapper
public interface Fct009Mapper {

    /**
    * 共同募集顧客(共募顧客)チェック
    *
    * @param butenCode 部店コード
    * @param accountNumber 口座番号
    * @return Integer　件数
    */
    int jointSubscriptCustomerCheck(@Param("butenCode") String butenCode, @Param("accountNumber")String accountNumber);
}
