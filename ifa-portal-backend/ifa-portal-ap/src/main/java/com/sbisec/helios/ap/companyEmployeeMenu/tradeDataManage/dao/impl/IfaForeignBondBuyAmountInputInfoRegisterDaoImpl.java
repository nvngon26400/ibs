package com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.IfaForeignBondBuyAmountInputInfoRegisterDao;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.mapper.IfaForeignBondBuyAmountInputInfoRegisterMapper;
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
@Component
public class IfaForeignBondBuyAmountInputInfoRegisterDaoImpl extends RowSelectableDao
        implements IfaForeignBondBuyAmountInputInfoRegisterDao
{
    
    @Autowired
    private IfaForeignBondBuyAmountInputInfoRegisterMapper mapper;
    
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
    @Override
    public DataList<IfaForeignBondBuyAmountInputInfoRegisterSql001ResponseModel> selectIfaForeignBondBuyAmountInputInfoRegisterSql001(
            IfaForeignBondBuyAmountInputInfoRegisterSql001RequestModel req) throws Exception {
        
        var res = new DataList<IfaForeignBondBuyAmountInputInfoRegisterSql001ResponseModel>();
        res.setDataList(mapper.selectIfaForeignBondBuyAmountInputInfoRegisterSql001(req));
        
        return res;
    }
    
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
    @Override
    public int insertIfaForeignBondBuyAmountInputInfoRegisterSql003(
            IfaForeignBondBuyAmountInputInfoRegisterSql003RequestModel req) throws Exception {
        
        return mapper.insertIfaForeignBondBuyAmountInputInfoRegisterSql003(req);
    }
    
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
	@Override
	public Integer selectIfaForeignBondBuyAmountInputInfoRegisterSql002(
			IfaForeignBondBuyAmountInputInfoRegisterSql002RequestModel req) throws Exception {
		
		return mapper.selectIfaForeignBondBuyAmountInputInfoRegisterSql002(req);
	}
}
