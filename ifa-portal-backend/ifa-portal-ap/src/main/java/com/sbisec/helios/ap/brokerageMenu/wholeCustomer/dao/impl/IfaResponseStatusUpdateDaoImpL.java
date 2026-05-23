package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaResponseStatusUpdateDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper.IfaResponseStatusUpdateMapper;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaResponseStatusUpdateSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaResponseStatusUpdateSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaResponseStatusUpdateSql002RequestModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;





/**
 * 画面ID：SUB020301_03-01_1
 * 画面名：対応状況更新
 *
 * @author BASE 李
 2024/05/28 新規作成
 */
@Component
public class IfaResponseStatusUpdateDaoImpL extends RowSelectableDao implements IfaResponseStatusUpdateDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaResponseStatusUpdateDaoImpL.class);
    @Autowired
    private IfaResponseStatusUpdateMapper mapper;
    
	
    /**
     * SqlID：Sql001
     * Sql名：対応状況取得
     * Sqlタイプ：select
     * リクエストクラス：IfaResponseStatusUpdateSql001RequestModel
     * レスポンスクラス：IfaResponseStatusUpdateSql001ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaResponseStatusUpdateSql001ResponseModel> selectIfaResponseStatusUpdateSql001(IfaResponseStatusUpdateSql001RequestModel req)
            throws Exception {
        
        DataList<IfaResponseStatusUpdateSql001ResponseModel> res = new DataList<IfaResponseStatusUpdateSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaResponseStatusUpdateSql001(req));
        return res;
    }
    
    
    /**
     * SqlID：Sql002
     * Sql名：対応状況更新
     * Sqlタイプ：update
     * リクエストクラス：IfaResponseStatusUpdateSql002RequestModel
     * レスポンスクラス：IfaResponseStatusUpdateSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int updateIfaResponseStatusUpdateSql002(IfaResponseStatusUpdateSql002RequestModel req)
            throws Exception {

        return mapper.updateIfaResponseStatusUpdateSql002(req);
    }
}
