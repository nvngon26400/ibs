package com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.IfaJointSubscriptTrustFeeDao;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.mapper.IfaJointSubscriptTrustFeeMapper;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptTrustFeeSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptTrustFeeSql001ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0206_03-01
 * 画面名：共同募集　信託報酬
 *
 * @author SBI 苗萌
 * 2024/12/18 新規作成
 */
@Component
public class IfaJointSubscriptTrustFeeDaoImpL extends RowSelectableDao implements IfaJointSubscriptTrustFeeDao {
    
    @Autowired
    private IfaJointSubscriptTrustFeeMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：共同募集　信託報酬一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaJointSubscriptTrustFeeSql001RequestModel
     * レスポンスクラス：IfaJointSubscriptTrustFeeSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointSubscriptTrustFeeSql001ResponseModel> selectIfaJointSubscriptTrustFeeSql001(
            IfaJointSubscriptTrustFeeSql001RequestModel req) throws Exception {
        
        DataList<IfaJointSubscriptTrustFeeSql001ResponseModel> res = new DataList<IfaJointSubscriptTrustFeeSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaJointSubscriptTrustFeeSql001(req));
        return res;
    }

}
