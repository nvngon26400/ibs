package com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.model.IfaForeignBondBuyAmountInputInfoRegisterSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.model.IfaForeignBondBuyAmountInputInfoRegisterSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.model.IfaForeignBondBuyAmountInputInfoRegisterSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.model.IfaForeignBondBuyAmountInputInfoRegisterSql003RequestModel;

/**
 * 画面ID：SUB0504_04-02_1 
 * 画面名：外債買付代金入力情報登録 
 * 2026/04/14 新規作成
 *
 * @author CuongHD
 */
@Mapper
public interface IfaForeignBondBuyAmountInputInfoRegisterMapper
{
    
    /**
     * SQLID：Sql001
     * SQL名：登録された外債買付代金入力情報を取得する
     * SQLタイプ：select
     * リクエストクラス：IfaForeignBondBuyAmountInputInfoRegisterSql001RequestModel
     * レスポンスクラス：IfaForeignBondBuyAmountInputInfoRegisterSql001ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 登録された外債買付代金入力情報一覧
     * @exception Exception SQLExceptionなど
     */
    public List<IfaForeignBondBuyAmountInputInfoRegisterSql001ResponseModel> selectIfaForeignBondBuyAmountInputInfoRegisterSql001(
            @Param("req") IfaForeignBondBuyAmountInputInfoRegisterSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名： 登録された外債買付代金入力情報を取得する
     * SQLタイプ：insert
     * リクエストクラス：IfaForeignBondBuyAmountInputInfoRegisterSql003RequestModel
     *
     * @param req 登録するコンプライアンス通信閲覧要否情報
     * @return SUCCESS:0以上、FAIL：0
     * @exception Exception SQLExceptionなど
     */
    public int insertIfaForeignBondBuyAmountInputInfoRegisterSql003(
            @Param("req") IfaForeignBondBuyAmountInputInfoRegisterSql003RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名： Butenアカウント非存在フラグを取得する
     * SQLタイプ：select
     * リクエストクラス：IfaForeignBondBuyAmountInputInfoRegisterSql002RequestModel
     *
     * @param req 登録するコンプライアンス通信閲覧要否情報
     * @return SUCCESS:0以上、FAIL：0
     * @exception Exception SQLExceptionなど
     */
    public Integer selectIfaForeignBondBuyAmountInputInfoRegisterSql002(
            @Param("req") IfaForeignBondBuyAmountInputInfoRegisterSql002RequestModel req) throws Exception;
    
}
