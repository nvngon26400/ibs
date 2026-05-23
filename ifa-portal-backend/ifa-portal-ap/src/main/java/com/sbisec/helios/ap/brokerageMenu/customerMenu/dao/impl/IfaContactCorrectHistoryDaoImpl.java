package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaContactCorrectHistoryDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaContactCorrectHistoryMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectHistorySql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectHistorySql001ResponseModel;

/**
 * 画面ID:SUB0202_0106-08
 * 画面名:接触履歴（入力）修正履歴
 *
 * @author SBI大連 夏
 * @date   2025/08/14
 */

@Component
public class IfaContactCorrectHistoryDaoImpl extends RowSelectableDao implements IfaContactCorrectHistoryDao {

    @Autowired
    private IfaContactCorrectHistoryMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：修正履歴取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactCorrectHistorySql001RequestModel
     * レスポンスクラス：IfaContactCorrectHistorySql001ResponseModel
     *
     * @param req {@code IfaContactCorrectHistorySql001RequestModel }
     * @return {@code DataList<IfaContactCorrectHistorySql001ResponseModel>}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    @Override
    public DataList<IfaContactCorrectHistorySql001ResponseModel> selectIfaContactCorrectHistorySql001(
        IfaContactCorrectHistorySql001RequestModel req) throws Exception {
        
        DataList<IfaContactCorrectHistorySql001ResponseModel> res = new DataList<IfaContactCorrectHistorySql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaContactCorrectHistorySql001(req));
        return res;
    }

}
