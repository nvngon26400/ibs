package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaDomesticMarginCollateralDeficientAlertListDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper.IfaDomesticMarginCollateralDeficientAlertListMapper;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDomesticMarginCollateralDeficientAlertListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDomesticMarginCollateralDeficientAlertListSql001ResponseModel;





/**
 * 画面ID：SUB020301_01-02
 * 画面名：国内信用担保不足アラート一覧
 *
 * @author BASE 李
 2024/06/11 新規作成
 */
@Component
public class IfaDomesticMarginCollateralDeficientAlertListDaoImpL extends RowSelectableDao implements IfaDomesticMarginCollateralDeficientAlertListDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaDomesticMarginCollateralDeficientAlertListDaoImpL.class);
    @Autowired
    private IfaDomesticMarginCollateralDeficientAlertListMapper mapper;
    
	
    /**
     * SQLID：Sql001
     * SQL名：追証請求顧客情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaDomesticMarginCollateralDeficientAlertListSql001RequestModel
     * レスポンスクラス：IfaDomesticMarginCollateralDeficientAlertListSql001ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaDomesticMarginCollateralDeficientAlertListSql001ResponseModel> selectIfaDomesticMarginCollateralDeficientAlertListSql001(IfaDomesticMarginCollateralDeficientAlertListSql001RequestModel req)
            throws Exception {
        
        DataList<IfaDomesticMarginCollateralDeficientAlertListSql001ResponseModel> res = new DataList<IfaDomesticMarginCollateralDeficientAlertListSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaDomesticMarginCollateralDeficientAlertListSql001(req));
        return res;
    }
    
    
    
    
}
