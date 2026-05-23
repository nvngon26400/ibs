package com.sbisec.helios.ap.brokerageMenu.commFee.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.commFee.dao.IfaOtherFeeDetailDao;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.mapper.IfaOtherFeeDetailMapper;

import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaOtherFeeDetailSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaOtherFeeDetailSql001ResponseModel;





/**
 * 画面ID：SUB020502-02
 * 画面名：その他報酬詳細
 *
 * @author BASE 李
 2024/06/19 新規作成
 */
@Component
public class IfaOtherFeeDetailDaoImpL extends RowSelectableDao implements IfaOtherFeeDetailDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaOtherFeeDetailDaoImpL.class);
    @Autowired
    private IfaOtherFeeDetailMapper mapper;
    
	
    /**
     * SQLID：Sql001
     * SQL名：その他報酬詳細一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaOtherFeeDetailSql001RequestModel
     * レスポンスクラス：IfaOtherFeeDetailSql001ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaOtherFeeDetailSql001ResponseModel> selectIfaOtherFeeDetailSql001(IfaOtherFeeDetailSql001RequestModel req)
            throws Exception {
        
        DataList<IfaOtherFeeDetailSql001ResponseModel> res = new DataList<IfaOtherFeeDetailSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaOtherFeeDetailSql001(req));
        return res;
    }
    
    
    
    
}
