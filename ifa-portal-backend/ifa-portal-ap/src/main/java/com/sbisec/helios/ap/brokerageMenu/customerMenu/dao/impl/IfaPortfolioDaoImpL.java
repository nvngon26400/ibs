package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaPortfolioDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaPortfolioMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql008ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql009RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql009ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql010RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql010ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql011RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql011ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql012RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql012ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql013RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql013ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql014RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql014ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql015RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql015ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql017RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql017ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql018RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql018ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql019RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPortfolioSql019ResponseModel;

/**
 * 画面ID：SUB0202_0101-01
 * 画面名：資産状況
 * 2023/12/26 新規作成
 *
 * @author SCSK 江口
 */
@Component
public class IfaPortfolioDaoImpL extends RowSelectableDao implements IfaPortfolioDao {

    @Autowired
    private IfaPortfolioMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：前営業日の資産情報を取得
     * SQLタイプ：select
     * リクエストクラス：IfaPortfolioSql001RequestModel
     * レスポンスクラス：IfaPortfolioSql001ResponseModel
     *
     *
     * @param req リクエストパラメータ
     * @return 前営業日の資産情報
     * @exception Exception SQLExceptionなど
     */
    @Override
    public DataList<IfaPortfolioSql001ResponseModel> selectIfaPortfolioSql001(
            IfaPortfolioSql001RequestModel req
    ) throws Exception {
        
        DataList<IfaPortfolioSql001ResponseModel> res = new DataList<IfaPortfolioSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaPortfolioSql001(req));
        return res;
    }
    
    /**
     * SQLID：Sql002
     * SQL名：国内株式の商品明細取得
     * SQLタイプ：select
     * リクエストクラス：IfaPortfolioSql002RequestModel
     * レスポンスクラス：IfaPortfolioSql002ResponseModel
     *
     *
     * @param req リクエストパラメータ
     * @return 国内株式の商品明細
     * @exception Exception SQLExceptionなど
     */
    @Override
    public DataList<IfaPortfolioSql002ResponseModel> selectIfaPortfolioSql002(
            IfaPortfolioSql002RequestModel req
    ) throws Exception {
        
        DataList<IfaPortfolioSql002ResponseModel> res = new DataList<IfaPortfolioSql002ResponseModel>();
        
        res.setDataList(mapper.selectIfaPortfolioSql002(req));
        return res;
    }
    
    /**
     * SQLID：Sql003
     * SQL名：国内債券の商品明細取得
     * SQLタイプ：select
     * リクエストクラス：IfaPortfolioSql003RequestModel
     * レスポンスクラス：IfaPortfolioSql003ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 国内債券の商品明細
     * @exception Exception SQLExceptionなど
     */
    @Override
    public DataList<IfaPortfolioSql003ResponseModel> selectIfaPortfolioSql003(
            IfaPortfolioSql003RequestModel req
    ) throws Exception {
        
        DataList<IfaPortfolioSql003ResponseModel> res = new DataList<IfaPortfolioSql003ResponseModel>();
        
        res.setDataList(mapper.selectIfaPortfolioSql003(req));
        return res;
    }
    
    /**
     * SQLID：Sql005
     * SQL名：外国債券(円建)の商品明細取得
     * SQLタイプ：select
     * リクエストクラス：IfaPortfolioSql005RequestModel
     * レスポンスクラス：IfaPortfolioSql005ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 外国債券(円建)の商品明細
     * @exception Exception SQLExceptionなど
     */
    @Override
    public DataList<IfaPortfolioSql005ResponseModel> selectIfaPortfolioSql005(
            IfaPortfolioSql005RequestModel req
    ) throws Exception {
        
        DataList<IfaPortfolioSql005ResponseModel> res = new DataList<IfaPortfolioSql005ResponseModel>();
        
        res.setDataList(mapper.selectIfaPortfolioSql005(req));
        return res;
    }
    
    /**
     * SQLID：Sql008
     * SQL名：外国債券(外建)の商品明細取得
     * SQLタイプ：select
     * リクエストクラス：IfaPortfolioSql008RequestModel
     * レスポンスクラス：IfaPortfolioSql008ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 外国債券(外建)の商品明細
     * @exception Exception SQLExceptionなど
     */
    @Override
    public DataList<IfaPortfolioSql008ResponseModel> selectIfaPortfolioSql008(
            IfaPortfolioSql008RequestModel req
    ) throws Exception {
        
        DataList<IfaPortfolioSql008ResponseModel> res = new DataList<IfaPortfolioSql008ResponseModel>();
        
        res.setDataList(mapper.selectIfaPortfolioSql008(req));
        return res;
    }
    
    /**
     * SQLID：Sql009
     * SQL名：外国債券 (外貨建仕組債)の商品明細取得
     * SQLタイプ：select
     * リクエストクラス：IfaPortfolioSql009RequestModel
     * レスポンスクラス：IfaPortfolioSql009ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 外国債券 (外貨建仕組債)の商品明細
     * @exception Exception SQLExceptionなど
     */
    @Override
    public DataList<IfaPortfolioSql009ResponseModel> selectIfaPortfolioSql009(
            IfaPortfolioSql009RequestModel req
    ) throws Exception {
        
        DataList<IfaPortfolioSql009ResponseModel> res = new DataList<IfaPortfolioSql009ResponseModel>();
        
        res.setDataList(mapper.selectIfaPortfolioSql009(req));
        return res;
    }
    
    /**
     * SQLID：Sql010
     * SQL名：現金明細取得
     * SQLタイプ：select
     * リクエストクラス：IfaPortfolioSql010RequestModel
     * レスポンスクラス：IfaPortfolioSql010ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 現金明細
     * @exception Exception SQLExceptionなど
     */
    @Override
    public DataList<IfaPortfolioSql010ResponseModel> selectIfaPortfolioSql010(
            IfaPortfolioSql010RequestModel req
    ) throws Exception {
        
        DataList<IfaPortfolioSql010ResponseModel> res = new DataList<IfaPortfolioSql010ResponseModel>();
        
        res.setDataList(mapper.selectIfaPortfolioSql010(req));
        return res;
    }
    
    /**
     * SQLID：Sql011
     * SQL名：外貨現金明細取得
     * SQLタイプ：select
     * リクエストクラス：IfaPortfolioSql011RequestModel
     * レスポンスクラス：IfaPortfolioSql011ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 外貨現金明細
     * @exception Exception SQLExceptionなど
     */
    @Override
    public DataList<IfaPortfolioSql011ResponseModel> selectIfaPortfolioSql011(
            IfaPortfolioSql011RequestModel req
    ) throws Exception {
        
        DataList<IfaPortfolioSql011ResponseModel> res = new DataList<IfaPortfolioSql011ResponseModel>();
        
        res.setDataList(mapper.selectIfaPortfolioSql011(req));
        return res;
    }
    
    /**
     * SQLID：Sql012
     * SQL名：信用維持率取得
     * SQLタイプ：select
     * リクエストクラス：IfaPortfolioSql012RequestModel
     * レスポンスクラス：IfaPortfolioSql012ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 信用維持率
     * @exception Exception SQLExceptionなど
     */
    @Override
    public DataList<IfaPortfolioSql012ResponseModel> selectIfaPortfolioSql012(
            IfaPortfolioSql012RequestModel req
    ) throws Exception {
        
        DataList<IfaPortfolioSql012ResponseModel> res = new DataList<IfaPortfolioSql012ResponseModel>();
        
        res.setDataList(mapper.selectIfaPortfolioSql012(req));
        return res;
    }
    
    /**
     * SQLID：Sql013
     * SQL名：信用建玉明細取得
     * SQLタイプ：select
     * リクエストクラス：IfaPortfolioSql013RequestModel
     * レスポンスクラス：IfaPortfolioSql013ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 信用建玉明細
     * @exception Exception SQLExceptionなど
     */
    @Override
    public DataList<IfaPortfolioSql013ResponseModel> selectIfaPortfolioSql013(
            IfaPortfolioSql013RequestModel req
    ) throws Exception {
        
        DataList<IfaPortfolioSql013ResponseModel> res = new DataList<IfaPortfolioSql013ResponseModel>();
        
        res.setDataList(mapper.selectIfaPortfolioSql013(req));
        return res;
    }
    
    /**
     * SQLID：Sql014
     * SQL名：米株信用維持率取得
     * SQLタイプ：select
     * リクエストクラス：IfaPortfolioSql014RequestModel
     * レスポンスクラス：IfaPortfolioSql014ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 米株信用維持率
     * @exception Exception SQLExceptionなど
     */
    @Override
    public DataList<IfaPortfolioSql014ResponseModel> selectIfaPortfolioSql014(
            IfaPortfolioSql014RequestModel req
    ) throws Exception {
        
        DataList<IfaPortfolioSql014ResponseModel> res = new DataList<IfaPortfolioSql014ResponseModel>();
        
        res.setDataList(mapper.selectIfaPortfolioSql014(req));
        return res;
    }
    
    /**
     * SQLID：Sql015
     * SQL名：米株信用建玉明細取得
     * SQLタイプ：select
     * リクエストクラス：IfaPortfolioSql015RequestModel
     * レスポンスクラス：IfaPortfolioSql015ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 米株信用建玉明細
     * @exception Exception SQLExceptionなど
     */
    @Override
    public DataList<IfaPortfolioSql015ResponseModel> selectIfaPortfolioSql015(
            IfaPortfolioSql015RequestModel req
    ) throws Exception {
        
        DataList<IfaPortfolioSql015ResponseModel> res = new DataList<IfaPortfolioSql015ResponseModel>();
        
        res.setDataList(mapper.selectIfaPortfolioSql015(req));
        return res;
    }
    
    /**
     * SQLID：Sql017
     * SQL名：SBIラップ口座分の現金情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaPortfolioSql017RequestModel
     * レスポンスクラス：IfaPortfolioSql017ResponseModel
     *
     * @param req リクエストパラメータ
     * @return SBIラップ口座分の現金情報
     * @exception Exception SQLExceptionなど
     */
    @Override
    public DataList<IfaPortfolioSql017ResponseModel> selectIfaPortfolioSql017(
            IfaPortfolioSql017RequestModel req
    ) throws Exception {
        
        DataList<IfaPortfolioSql017ResponseModel> res = new DataList<IfaPortfolioSql017ResponseModel>();
        
        res.setDataList(mapper.selectIfaPortfolioSql017(req));
        return res;
    }
    
    /**
     * SQLID：Sql018
     * SQL名：トータルリターン取得処理
     * SQLタイプ：select
     * リクエストクラス：IfaPortfolioSql018RequestModel
     * レスポンスクラス：IfaPortfolioSql018ResponseModel
     *
     * @param req リクエストパラメータ
     * @return トータルリターン
     * @exception Exception SQLExceptionなど
     */
    @Override
    public DataList<IfaPortfolioSql018ResponseModel> selectIfaPortfolioSql018(
            IfaPortfolioSql018RequestModel req
    ) throws Exception {
        
        DataList<IfaPortfolioSql018ResponseModel> res = new DataList<IfaPortfolioSql018ResponseModel>();
        
        res.setDataList(mapper.selectIfaPortfolioSql018(req));
        return res;
    }
    
    /**
     * SQLID：Sql019
     * SQL名：投資信託、外国株式、外貨建MMFの商品明細取得
     * SQLタイプ：select
     * リクエストクラス：IfaPortfolioSql019RequestModel
     * レスポンスクラス：IfaPortfolioSql019ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 投資信託、外国株式、外貨建MMFの商品明細
     * @exception Exception SQLExceptionなど
     */
    @Override
    public DataList<IfaPortfolioSql019ResponseModel> selectIfaPortfolioSql019(
            IfaPortfolioSql019RequestModel req
    ) throws Exception {
        
        DataList<IfaPortfolioSql019ResponseModel> res = new DataList<IfaPortfolioSql019ResponseModel>();
        
        res.setDataList(mapper.selectIfaPortfolioSql019(req));
        return res;
    }
    
    /**
     * SQLID：Sql020
     * SQL名：T_BALANCE_PREV更新ジョブステータス取得
     * SQLタイプ：select
     *
     * @return String ジョブステータス
     * @exception Exception SQLExceptionなど
     */
    @Override
    public String selectIfaPortfolioSql020() throws Exception {
        
        return mapper.selectIfaPortfolioSql020();
    }
}
