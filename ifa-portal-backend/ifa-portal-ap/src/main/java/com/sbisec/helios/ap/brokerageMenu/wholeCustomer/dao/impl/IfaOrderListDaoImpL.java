package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaOrderListDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper.IfaOrderListMapper;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql007ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql008ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.impl.IfaOrderListServiceImpL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;





/**
 * 画面ID：SUB020302_0101-01
 * 画面名：注文一覧
 *
 * @author BASE李
 *
 2024/03/30 新規作成
 */
@Component
public class IfaOrderListDaoImpL extends RowSelectableDao implements IfaOrderListDao {
    

    
    @Autowired
    private IfaOrderListMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：国内株式一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaOrderListSql001RequestModel
     * レスポンスクラス：IfaOrderListSql001ResponseModel
     *
     * @param req リクエスト
     * @return res
     * @exception e システムエラー
     */
    public DataList<IfaOrderListSql001ResponseModel> selectIfaOrderListSql001(IfaOrderListSql001RequestModel req)
            throws Exception {
        
        DataList<IfaOrderListSql001ResponseModel> res = new DataList<IfaOrderListSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaOrderListSql001(req));

        return res;
    }
    
    /**
     * SQLID：Sql002
     * SQL名：国内投資信託一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaOrderListSql002RequestModel
     * レスポンスクラス：IfaOrderListSql002ResponseModel
     *
     * @param req リクエスト
     * @return res
     * @exception e システムエラー
     */
    public DataList<IfaOrderListSql002ResponseModel> selectIfaOrderListSql002(IfaOrderListSql002RequestModel req)
            throws Exception {
        
        DataList<IfaOrderListSql002ResponseModel> res = new DataList<IfaOrderListSql002ResponseModel>();
        
        res.setDataList(mapper.selectIfaOrderListSql002(req));

        return res;
    }
    
    /**
     * SQLID：Sql003
     * SQL名：募集注文一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaOrderListSql003RequestModel
     * レスポンスクラス：IfaOrderListSql003ResponseModel
     *
     * @param req リクエスト
     * @return res
     * @exception e システムエラー
     */
    public DataList<IfaOrderListSql003ResponseModel> selectIfaOrderListSql003(IfaOrderListSql003RequestModel req)
            throws Exception {
        
        DataList<IfaOrderListSql003ResponseModel> res = new DataList<IfaOrderListSql003ResponseModel>();
        
        res.setDataList(mapper.selectIfaOrderListSql003(req));

        return res;
    }
    
    /**
     * SQLID：Sql004
     * SQL名：外国株式（委託注文）一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaOrderListSql004RequestModel
     * レスポンスクラス：IfaOrderListSql004ResponseModel
     *
     * @param req リクエスト
     * @return res
     * @exception e システムエラー
     */
    public DataList<IfaOrderListSql004ResponseModel> selectIfaOrderListSql004(IfaOrderListSql004RequestModel req)
            throws Exception {
        
        DataList<IfaOrderListSql004ResponseModel> res = new DataList<IfaOrderListSql004ResponseModel>();
        
        res.setDataList(mapper.selectIfaOrderListSql004(req));

        return res;
    }
    
    /**
     * SQLID：Sql005
     * SQL名：外国株式（店頭注文）一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaOrderListSql005RequestModel
     * レスポンスクラス：IfaOrderListSql005ResponseModel
     *
     * @param req リクエスト
     * @return res
     * @exception e システムエラー
     */
    public DataList<IfaOrderListSql005ResponseModel> selectIfaOrderListSql005(IfaOrderListSql005RequestModel req)
            throws Exception {
        
        DataList<IfaOrderListSql005ResponseModel> res = new DataList<IfaOrderListSql005ResponseModel>();
        
        res.setDataList(mapper.selectIfaOrderListSql005(req));

        return res;
    }    
    
    /**
     * SQLID：Sql007
     * SQL名：注文一覧コメント取得
     * SQLタイプ：select
     * リクエストクラス：IfaOrderListSql007RequestModel
     * レスポンスクラス：IfaOrderListSql007ResponseModel
     *
     * @return res
     * @exception e システムエラー
     */
    public DataList<IfaOrderListSql007ResponseModel> selectIfaOrderListSql007()
            throws Exception {
        
        DataList<IfaOrderListSql007ResponseModel> res = new DataList<IfaOrderListSql007ResponseModel>();
        
        res.setDataList(mapper.selectIfaOrderListSql007());

        return res;
    }
    
    /**
     * SQLID：Sql008
     * SQL名：国内投資信託（定期積立）一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaOrderListSql008RequestModel
     * レスポンスクラス：IfaOrderListSql008ResponseModel
     *
     * @param req リクエスト
     * @return res
     * @exception e システムエラー
     */
    public DataList<IfaOrderListSql008ResponseModel> selectIfaOrderListSql008(IfaOrderListSql008RequestModel req)
            throws Exception {
        
        DataList<IfaOrderListSql008ResponseModel> res = new DataList<IfaOrderListSql008ResponseModel>();
        
        res.setDataList(mapper.selectIfaOrderListSql008(req));

        return res;
    }
}
