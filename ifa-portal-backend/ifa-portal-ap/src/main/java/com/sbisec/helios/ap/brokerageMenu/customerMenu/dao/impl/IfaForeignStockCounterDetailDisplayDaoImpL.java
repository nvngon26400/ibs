package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaForeignStockCounterDetailDisplayDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaForeignStockCounterDetailDisplayMapper;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterDetailDisplaySql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterDetailDisplaySql001ResponseModel;

/**
 * 画面ID：SUB0202_0104-03
 * 画面名：外国株式店頭詳細表示

 * @author 大崎
　　　2024/03/19 新規作成
 */

@Component
public class IfaForeignStockCounterDetailDisplayDaoImpL extends RowSelectableDao implements IfaForeignStockCounterDetailDisplayDao {
    
    @Autowired
    private IfaForeignStockCounterDetailDisplayMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：外国株式店頭注文詳細表示情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaForeignStockCounterDetailDisplaySql001RequestModel
     * レスポンスクラス：IfaForeignStockCounterDetailDisplaySql001ResponseModel

     * @param req リクエスト
     * @return IfaForeignStockCounterDetailDisplaySql001ResponseModel
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaForeignStockCounterDetailDisplaySql001ResponseModel> selectIfaForeignStockCounterDetailDisplaySql001(IfaForeignStockCounterDetailDisplaySql001RequestModel req)
            throws Exception {
        
        DataList<IfaForeignStockCounterDetailDisplaySql001ResponseModel> res = new DataList<IfaForeignStockCounterDetailDisplaySql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaForeignStockCounterDetailDisplaySql001(req));
        return res;
    }
    
    
    
    
}
