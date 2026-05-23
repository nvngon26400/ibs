package com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.IfaJointSubscriptTradeListDao;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.mapper.IfaJointSubscriptTradeListMapper;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptTradeListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptTradeListSql001ResponseModel;


/**
 * 画面ID：SUB0206_02-01
 * 画面名：共同募集　取引検索
 *
 * @author SBIチョウ
   2024/12/12 新規作成
 */
@Component
public class IfaJointSubscriptTradeListDaoImpL extends RowSelectableDao implements IfaJointSubscriptTradeListDao {
    
    @Autowired
    private IfaJointSubscriptTradeListMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：共同募集　取引検索情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaJointSubscriptTradeListSql001RequestModel
     * レスポンスクラス：IfaJointSubscriptTradeListSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointSubscriptTradeListSql001ResponseModel> selectIfaJointSubscriptTradeListSql001(IfaJointSubscriptTradeListSql001RequestModel req)
            throws Exception {
        
        DataList<IfaJointSubscriptTradeListSql001ResponseModel> res = new DataList<IfaJointSubscriptTradeListSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaJointSubscriptTradeListSql001(req));
        return res;
    }
}
