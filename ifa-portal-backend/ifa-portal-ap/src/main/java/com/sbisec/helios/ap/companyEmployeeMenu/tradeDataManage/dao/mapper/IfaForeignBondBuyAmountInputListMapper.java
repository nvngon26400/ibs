package com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.model.IfaForeignBondBuyAmountInputListSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.model.IfaForeignBondBuyAmountInputListSql002RequestModel;

/**
 * 画面ID：SUB0504_04-01 
 * 画面名：外債買付代金入力一覧
 * 2026/04/14 新規作成
 *
 * @author CuongHD
 */
@Mapper
public interface IfaForeignBondBuyAmountInputListMapper
{
    
    /**
     * SQLID：Sql001
     * SQL名：外債買付代金入力一覧を取得する
     * SQLタイプ：select
     * レスポンスクラス：IfaForeignBondBuyAmountInputListSql001ResponseModel
     *
     * @param req リクエストパラメータ
     * @return コンプライアンス通信要否管理情報一覧
     * @exception Exception SQLExceptionなど
     */
    public List<IfaForeignBondBuyAmountInputListSql001ResponseModel> selectIfaForeignBondBuyAmountInputListSql001(int maxRowNum)
            throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名： 外債買付代金入力一覧を削除する
     * SQLタイプ：delete
     * リクエストクラス：IfaForeignBondBuyAmountInputListSql002RequestModel
     *
     * @param req 登録するコンプライアンス通信閲覧要否情報
     * @return SUCCESS:0以上、FAIL：0
     * @exception Exception SQLExceptionなど
     */
    public int deleteIfaForeignBondBuyAmountInputListSql002(
            @Param("req") IfaForeignBondBuyAmountInputListSql002RequestModel req) throws Exception;
}
