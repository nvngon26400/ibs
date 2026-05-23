package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaForeignMarginCollateralDeficientAlertListDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper.IfaForeignMarginCollateralDeficientAlertListMapper;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaForeignMarginCollateralDeficientAlertListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaForeignMarginCollateralDeficientAlertListSql001ResponseModel;





/**
 * 画面ID：SUB020301_01-04
 * 画面名：米株信用担保不足アラート一覧
 *
 * @author BASE 李
 2024/06/17 新規作成
 */
@Component
public class IfaForeignMarginCollateralDeficientAlertListDaoImpL extends RowSelectableDao implements IfaForeignMarginCollateralDeficientAlertListDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaForeignMarginCollateralDeficientAlertListDaoImpL.class);
    @Autowired
    private IfaForeignMarginCollateralDeficientAlertListMapper mapper;
    
	
    /**
     * SQLID：Sql001
     * SQL名：追証請求顧客情報取得（SBI証券本店）
     * SQLタイプ：select
     * リクエストクラス：IfaForeignMarginCollateralDeficientAlertListSql001RequestModel
     * レスポンスクラス：IfaForeignMarginCollateralDeficientAlertListSql001ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaForeignMarginCollateralDeficientAlertListSql001ResponseModel> selectIfaForeignMarginCollateralDeficientAlertListSql001(IfaForeignMarginCollateralDeficientAlertListSql001RequestModel req)
            throws Exception {
        
        DataList<IfaForeignMarginCollateralDeficientAlertListSql001ResponseModel> res = new DataList<IfaForeignMarginCollateralDeficientAlertListSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaForeignMarginCollateralDeficientAlertListSql001(req));
        return res;
    }
    
    
    
    
}
