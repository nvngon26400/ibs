package com.sbisec.helios.ap.brokerageMenu.commFee.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.IfaLevelFeeDao;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.mapper.IfaLevelFeeMapper;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaLevelFeeSql001To006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaLevelFeeSql001To006ResponseModel;


/**
 * 画面ID：SUB020505-01
 * 画面名：残高連動手数料・報酬
 * 2025/06/02 新規作成
 *
 * @author SCSK
 */
@Component
public class IfaLevelFeeDaoImpL extends RowSelectableDao implements IfaLevelFeeDao {
    
    @Autowired
    private IfaLevelFeeMapper mapper;
    
    /**
     * SQLID：Sql001～Sql006
     * SQL名：残高連動手数料・報酬 一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaLevelFeeSql001To006RequestModel
     * レスポンスクラス：IfaLevelFeeSql001To006ResponseModel
     *
     * @param req パラメータ
     * @return 残高連動手数料・報酬 一覧
     * @exception Exception システムエラー
     */
    @Override
    public DataList<IfaLevelFeeSql001To006ResponseModel> selectIfaLevelFeeSql001To006(
            IfaLevelFeeSql001To006RequestModel req
    ) throws Exception {
        
        DataList<IfaLevelFeeSql001To006ResponseModel> res = new DataList<IfaLevelFeeSql001To006ResponseModel>();
        
        res.setDataList(mapper.selectIfaLevelFeeSql001To006(req));
        return res;
    }
}
