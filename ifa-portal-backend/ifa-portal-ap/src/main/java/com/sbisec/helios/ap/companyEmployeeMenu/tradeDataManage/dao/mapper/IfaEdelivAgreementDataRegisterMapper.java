package com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.model.IfaEdelivAgreementDataRegisterSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.model.IfaEdelivAgreementDataRegisterSql002RequestModel;

/**
 * 画面ID：SUB0504_02-01
 * 画面名：電子交付同意データ登録
 */
@Mapper
public interface IfaEdelivAgreementDataRegisterMapper {

    /**
     * SQLID：Sql001
     * SQL名：顧客属性情報取得
     */
    Integer selectIfaEdelivAgreementDataRegisterSql001(
            @Param("req") IfaEdelivAgreementDataRegisterSql001RequestModel req) throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：電子交付承諾情報登録更新
     */
    int mergeIfaEdelivAgreementDataRegisterSql002(
            @Param("req") IfaEdelivAgreementDataRegisterSql002RequestModel req) throws Exception;
}
