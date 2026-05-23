package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaCurrencyDealtListDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaCurrencyDealtListMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCurrencyDealtListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCurrencyDealtListSql001ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;





/**
 * 画面ID：SUB0202_0502-01 画面名：取扱通貨一覧
 * 
 *
 * @author 池亀緑
 *
 *         2023/08/23 新規作成
 */
@Component
public class IfaCurrencyDealtListDaoImpL extends RowSelectableDao implements IfaCurrencyDealtListDao {
    
    @Autowired
    private IfaCurrencyDealtListMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaCurrencyDealtListSql001RequestModel
     * レスポンスクラス：IfaCurrencyDealtListSql001ResponseModel
     *
     * @param req {@code IfaCurrencyDealtListSql001RequestModel }
     * @return {@code DataList <IfaCurrencyDealtListSql001ResponseModel>}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaCurrencyDealtListSql001ResponseModel> selectIfaCurrencyDealtListSql001(IfaCurrencyDealtListSql001RequestModel req)
            throws Exception {
        
        DataList<IfaCurrencyDealtListSql001ResponseModel> res = new DataList<IfaCurrencyDealtListSql001ResponseModel>();
        
        res.getDataList().add(mapper.selectIfaCurrencyDealtListSql001(req));
        return res;
    }
    
    
    
    
}
