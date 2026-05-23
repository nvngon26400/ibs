package com.sbisec.helios.ap.wholePortal.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.wholePortal.dao.IfaCustomerAlertCsvOutputDao;
import com.sbisec.helios.ap.wholePortal.dao.mapper.IfaCustomerAlertCsvOutputMapper;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaCustomerAlertCsvOutputSql001RequestModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaCustomerAlertCsvOutputSql001ResponseModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaCustomerAlertCsvOutputSql002RequestModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaCustomerAlertCsvOutputSql002ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB01-02
 * 画面名：顧客アラートCSV出力
 *
 * @author SCSK丹波
 2024/05/16 新規作成
 */
@Component
public class IfaCustomerAlertCsvOutputDaoImpL extends RowSelectableDao implements IfaCustomerAlertCsvOutputDao {
    
    @Autowired
    private IfaCustomerAlertCsvOutputMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：顧客アラート情報取得（SBI証券本店）
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerAlertCsvOutputSql001RequestModel
     * レスポンスクラス：IfaCustomerAlertCsvOutputSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaCustomerAlertCsvOutputSql001ResponseModel> selectIfaCustomerAlertCsvOutputSql001(
            IfaCustomerAlertCsvOutputSql001RequestModel req) throws Exception {
        
        DataList<IfaCustomerAlertCsvOutputSql001ResponseModel> res = new DataList<IfaCustomerAlertCsvOutputSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaCustomerAlertCsvOutputSql001(req));
        return res;
    }
    
    /**
     * SQLID：Sql002
     * SQL名：顧客アラート情報取得（SBI証券支店）
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerAlertCsvOutputSql002RequestModel
     * レスポンスクラス：IfaCustomerAlertCsvOutputSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaCustomerAlertCsvOutputSql002ResponseModel> selectIfaCustomerAlertCsvOutputSql002(
            IfaCustomerAlertCsvOutputSql002RequestModel req) throws Exception {
        
        DataList<IfaCustomerAlertCsvOutputSql002ResponseModel> res = new DataList<IfaCustomerAlertCsvOutputSql002ResponseModel>();
        
        res.setDataList(mapper.selectIfaCustomerAlertCsvOutputSql002(req));
        return res;
    }
    
    @Override
    public Integer selectIfaCustomerAlertCsvOutputSql004() throws Exception {
        
        return mapper.selectIfaCustomerAlertCsvOutputSql004();
    }
    
    @Override
    public String selectIfaCustomerAlertCsvOutputSql005() throws Exception {
        
        return mapper.selectIfaCustomerAlertCsvOutputSql005();
    }
    
}
