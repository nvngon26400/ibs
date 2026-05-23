package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaHoldingSecurityListDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaHoldingSecurityListMapper;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql002ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql006ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql007ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql008ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql009RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql009ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql010RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql010ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql011RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql011ResponseModel;

/**
 * 画面ID：SUB0202_010201-01
 * 画面名：保有商品一覧
 *
 * @author SCSK
 *     2023/10/17 新規作成
 */
@Component
public class IfaHoldingSecurityListDaoImpL extends RowSelectableDao implements IfaHoldingSecurityListDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaHoldingSecurityListDaoImpL.class);
    @Autowired
    private IfaHoldingSecurityListMapper mapper;
    
    /**
     * SQLID：Sql002
     * SQL名：基準価額単位取得
     * SQLタイプ：select
     * リクエストクラス：IfaHoldingSecurityListSql002RequestModel
     * レスポンスクラス：IfaHoldingSecurityListSql002ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 基準価額単位取得時に例外が発生した場合
     */
    public DataList<IfaHoldingSecurityListSql002ResponseModel> selectIfaHoldingSecurityListSql002(IfaHoldingSecurityListSql002RequestModel req)
            throws Exception {
        
        DataList<IfaHoldingSecurityListSql002ResponseModel> res = new DataList<IfaHoldingSecurityListSql002ResponseModel>();
        
        try {
            List<IfaHoldingSecurityListSql002ResponseModel> resList = mapper.selectIfaHoldingSecurityListSql002(req);
            res.setDataList(resList);
        } catch (Exception e) {
            throw e;
        }
        return res;
    }
    
    /**
     * SQLID：Sql005
     * SQL名：優先市場取得
     * SQLタイプ：select
     * リクエストクラス：IfaHoldingSecurityListSql005RequestModel
     * レスポンスクラス：IfaHoldingSecurityListSql005ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 優先市場取得時に例外が発生した場合
     */
    public DataList<IfaHoldingSecurityListSql005ResponseModel> selectIfaHoldingSecurityListSql005(IfaHoldingSecurityListSql005RequestModel req)
            throws Exception {
        
        DataList<IfaHoldingSecurityListSql005ResponseModel> res = new DataList<IfaHoldingSecurityListSql005ResponseModel>();
        
        try {
            List<IfaHoldingSecurityListSql005ResponseModel> resList = mapper.selectIfaHoldingSecurityListSql005(req);
            res.setDataList(resList);
        } catch (Exception e) {
            throw e;
        }
        return res;
    }
    
    /**
     * SQLID：Sql006
     * SQL名：ISA買付可能判定区分取得
     * SQLタイプ：select
     * リクエストクラス：IfaHoldingSecurityListSql006RequestModel
     * レスポンスクラス：IfaHoldingSecurityListSql006ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 優先市場取得時に例外が発生した場合
     */
    public IfaHoldingSecurityListSql006ResponseModel selectIfaHoldingSecurityListSql006(
            IfaHoldingSecurityListSql006RequestModel req) throws Exception {
        
        IfaHoldingSecurityListSql006ResponseModel res = new IfaHoldingSecurityListSql006ResponseModel();
        
        try {
            res = mapper.selectIfaHoldingSecurityListSql006(req);
        } catch (Exception e) {
            throw e;
        }
        if (ObjectUtils.isEmpty(res)) {
            IfaHoldingSecurityListSql006ResponseModel sqlRes = new IfaHoldingSecurityListSql006ResponseModel();
            sqlRes.setIsaBuyAbleNextYear("");
            sqlRes.setIsaBuyAbleThisYear("");
            return sqlRes;
        }
        return res;
    }

    @Override
    public IfaHoldingSecurityListSql007ResponseModel selectIfaHoldingSecurityListSql007(
            IfaHoldingSecurityListSql007RequestModel req) throws Exception {
        return mapper.selectIfaHoldingSecurityListSql007(req);
    }
    
    /**
     * SQLID：Sql008
     * SQL名：債券銘柄用補助コード取得
     * SQLタイプ：select
     * リクエストクラス：IfaHoldingSecurityListSql008RequestModel
     * レスポンスクラス：IfaHoldingSecurityListSql008ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 債券銘柄用補助コード取得時に例外が発生した場合
     */
    public IfaHoldingSecurityListSql008ResponseModel selectIfaHoldingSecurityListSql008(IfaHoldingSecurityListSql008RequestModel req)
            throws Exception {
        
        IfaHoldingSecurityListSql008ResponseModel res = new IfaHoldingSecurityListSql008ResponseModel();
        
        try {
            res = mapper.selectIfaHoldingSecurityListSql008(req);
        } catch (Exception e) {
            throw e;
        }
        return res;
    }
    
    /**
     * SQLID：Sql009
     * SQL名：債券ST銘柄コード取得
     * SQLタイプ：select
     * リクエストクラス：IfaHoldingSecurityListSql009RequestModel
     * レスポンスクラス：IfaHoldingSecurityListSql009ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 債券ST銘柄コード取得時に例外が発生した場合
     */
    public DataList<IfaHoldingSecurityListSql009ResponseModel> selectIfaHoldingSecurityListSql009(IfaHoldingSecurityListSql009RequestModel req)
            throws Exception {
        
        DataList<IfaHoldingSecurityListSql009ResponseModel> res = new DataList<IfaHoldingSecurityListSql009ResponseModel>();
        
        try {
            List<IfaHoldingSecurityListSql009ResponseModel> resList = mapper.selectIfaHoldingSecurityListSql009(req);
            res.setDataList(resList);
        } catch (Exception e) {
            throw e;
        }
        return res;
    }
    
    /**
     * SQLID：Sql010
     * SQL名：ST（セキュリティ・トークン）の商品明細取得
     * SQLタイプ：select
     * リクエストクラス：IfaHoldingSecurityListSql010RequestModel
     * レスポンスクラス：IfaHoldingSecurityListSql010ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception ST（セキュリティ・トークン）の商品明細取得時に例外が発生した場合
     */
    public DataList<IfaHoldingSecurityListSql010ResponseModel> selectIfaHoldingSecurityListSql010(IfaHoldingSecurityListSql010RequestModel req)
            throws Exception {
        
        DataList<IfaHoldingSecurityListSql010ResponseModel> res = new DataList<IfaHoldingSecurityListSql010ResponseModel>();
        
        try {
            List<IfaHoldingSecurityListSql010ResponseModel> resList = mapper.selectIfaHoldingSecurityListSql010(req);
            res.setDataList(resList);
        } catch (Exception e) {
            throw e;
        }
        return res;
    }

    /**
     * SQLID：Sql011
     * SQL名：基準価額取得
     * SQLタイプ：select
     * リクエストクラス：IfaHoldingSecurityListSql011RequestModel
     * レスポンスクラス：IfaHoldingSecurityListSql011ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 基準価額取得に例外が発生した場合
     */
    public DataList<IfaHoldingSecurityListSql011ResponseModel> selectIfaHoldingSecurityListSql011(IfaHoldingSecurityListSql011RequestModel req)
            throws Exception {
        
        DataList<IfaHoldingSecurityListSql011ResponseModel> res = new DataList<IfaHoldingSecurityListSql011ResponseModel>();
        
        try {
            List<IfaHoldingSecurityListSql011ResponseModel> resList = mapper.selectIfaHoldingSecurityListSql011(req);
            res.setDataList(resList);
        } catch (Exception e) {
            throw e;
        }
        return res;
    }
    
}
