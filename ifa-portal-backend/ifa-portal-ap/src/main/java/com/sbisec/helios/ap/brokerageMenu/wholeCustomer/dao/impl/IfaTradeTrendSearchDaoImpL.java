package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaTradeTrendSearchDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper.IfaTradeTrendSearchMapper;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTradeTrendSearchSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTradeTrendSearchSql002ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 取引動向検索
 * 2025/04/10 新規作成
 *
 * @author 大連 苗
 */
@Component
public class IfaTradeTrendSearchDaoImpL extends RowSelectableDao implements IfaTradeTrendSearchDao {
    
    @Autowired
    private IfaTradeTrendSearchMapper mapper;
    
    /**
     * SQLID：Sql002
     * SQL名：取引動向検索一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaTradeTrendSearchSql002RequestModel
     * レスポンスクラス：IfaTradeTrendSearchSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaTradeTrendSearchSql002ResponseModel> selectIfaTradeTrendSearchSql002(
            IfaTradeTrendSearchSql002RequestModel req) throws Exception {
        
        DataList<IfaTradeTrendSearchSql002ResponseModel> res = new DataList<IfaTradeTrendSearchSql002ResponseModel>();
        
        res.setDataList(mapper.selectIfaTradeTrendSearchSql002(req));
        return res;
    }

}
