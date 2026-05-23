package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaTodayTradeListDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper.IfaTodayTradeListMapper;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper.IfaTodayTradeListMapperMariaDb;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTodayTradeListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTodayTradeListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTodayTradeListSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTodayTradeListSql002ResponseModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;





/**
 * 画面ID：SUB020302_0102-01
 * 画面名：国内株当日約定一覧
 * @author <author-name>
 *
 * 2023/11/21 新規作成
 */
@Component
public class IfaTodayTradeListDaoImpL extends RowSelectableDao implements IfaTodayTradeListDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaTodayTradeListDaoImpL.class);
    @Autowired
    private IfaTodayTradeListMapper mapper;

    @Autowired
    private IfaTodayTradeListMapperMariaDb mapperMariaDb;
    
    /**
     * SQLID：Sql001
     * SQL名：顧客口座情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaTodayTradeListSql001RequestModel
     * レスポンスクラス：IfaTodayTradeListSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaTodayTradeListSql001ResponseModel> selectIfaTodayTradeListSql001(IfaTodayTradeListSql001RequestModel req)
            throws Exception {
        
        DataList<IfaTodayTradeListSql001ResponseModel> res = new DataList<IfaTodayTradeListSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaTodayTradeListSql001(req));
        return res;
    }
    
    /**
     * SQLID：Sql002
     * SQL名：銘柄コード存在チェック
     * SQLタイプ：select
     * リクエストクラス：IfaTodayTradeListSql002RequestModel
     * レスポンスクラス：IfaTodayTradeListSql002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaTodayTradeListSql002ResponseModel> selectIfaTodayTradeListSql002(IfaTodayTradeListSql002RequestModel req)
            throws Exception {
        
        DataList<IfaTodayTradeListSql002ResponseModel> res = new DataList<IfaTodayTradeListSql002ResponseModel>();
        
        res.setDataList(mapperMariaDb.selectIfaTodayTradeListSql002(req));
        return res;
    }
    
    
    
    
}
