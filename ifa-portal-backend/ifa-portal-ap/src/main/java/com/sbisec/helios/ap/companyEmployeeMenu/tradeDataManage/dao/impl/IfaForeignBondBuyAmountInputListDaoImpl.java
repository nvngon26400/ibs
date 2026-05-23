package com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.IfaForeignBondBuyAmountInputListDao;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.mapper.IfaForeignBondBuyAmountInputListMapper;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.model.IfaForeignBondBuyAmountInputListSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.model.IfaForeignBondBuyAmountInputListSql002RequestModel;

/**
 * 画面ID：SUB0504_04-01 
 * 画面名：外債買付代金入力一覧
 * 2026/04/14 新規作成
 *
 * @author CuongHD
 */
@Component
public class IfaForeignBondBuyAmountInputListDaoImpl extends RowSelectableDao
        implements IfaForeignBondBuyAmountInputListDao
{
    
    @Autowired
    private IfaForeignBondBuyAmountInputListMapper mapper;
    
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
    @Override
    public DataList<IfaForeignBondBuyAmountInputListSql001ResponseModel> selectIfaForeignBondBuyAmountInputListSql001(int maxRowNum)
            throws Exception {
        
        var res = new DataList<IfaForeignBondBuyAmountInputListSql001ResponseModel>();
        res.setDataList(mapper.selectIfaForeignBondBuyAmountInputListSql001(maxRowNum));
        return res;
    }
    
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
    @Override
    public int deleteIfaForeignBondBuyAmountInputListSql002(IfaForeignBondBuyAmountInputListSql002RequestModel req)
            throws Exception {
        
        return mapper.deleteIfaForeignBondBuyAmountInputListSql002(req);
    }
}
