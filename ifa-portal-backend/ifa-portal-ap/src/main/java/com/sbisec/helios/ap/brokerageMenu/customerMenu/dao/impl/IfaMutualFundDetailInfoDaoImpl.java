package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import java.util.Objects;
import java.util.stream.Collectors;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaMutualFundDetailInfoDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaMutualFundDetailInfoMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql006ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql009RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql009ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql010RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql010ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql011RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql011ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql012RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql012ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql013ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0202_0401-03
 * 画面名：投信詳細情報
 *
 * @author SCSK
 *
 *     2024/04/15 新規作成
 */
@Component
public class IfaMutualFundDetailInfoDaoImpl extends RowSelectableDao implements IfaMutualFundDetailInfoDao {
    
    @Autowired
    private IfaMutualFundDetailInfoMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：協会コード取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundDetailInfoSql001RequestModel
     * レスポンスクラス：IfaMutualFundDetailInfoSql001ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 実行時例外
     */
    public DataList<IfaMutualFundDetailInfoSql001ResponseModel> selectIfaMutualFundDetailInfoSql001(
            IfaMutualFundDetailInfoSql001RequestModel req) throws Exception {
        
        DataList<IfaMutualFundDetailInfoSql001ResponseModel> res = new DataList<IfaMutualFundDetailInfoSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaMutualFundDetailInfoSql001(req));
        return res;
    }
    
    /**
     * SQLID：Sql002
     * SQL名：投信銘柄当月休場日取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundDetailInfoSql002RequestModel
     * レスポンスクラス：IfaMutualFundDetailInfoSql002ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 実行時例外
     */
    public DataList<IfaMutualFundDetailInfoSql002ResponseModel> selectIfaMutualFundDetailInfoSql002(
            IfaMutualFundDetailInfoSql002RequestModel req) throws Exception {
        
        DataList<IfaMutualFundDetailInfoSql002ResponseModel> res = new DataList<IfaMutualFundDetailInfoSql002ResponseModel>();
        
        res.setDataList(mapper.selectIfaMutualFundDetailInfoSql002(req));
        return res;
    }
    
    /**
     * SQLID：Sql003
     * SQL名：投信銘柄翌月休場日取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundDetailInfoSql003RequestModel
     * レスポンスクラス：IfaMutualFundDetailInfoSql003ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 実行時例外
     */
    public DataList<IfaMutualFundDetailInfoSql003ResponseModel> selectIfaMutualFundDetailInfoSql003(
            IfaMutualFundDetailInfoSql003RequestModel req) throws Exception {
        
        DataList<IfaMutualFundDetailInfoSql003ResponseModel> res = new DataList<IfaMutualFundDetailInfoSql003ResponseModel>();
        
        res.setDataList(mapper.selectIfaMutualFundDetailInfoSql003(req));
        return res;
    }
    
    /**
     * SQLID：Sql004
     * SQL名：締切日（直近）取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundDetailInfoSql004RequestModel
     * レスポンスクラス：IfaMutualFundDetailInfoSql004ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 実行時例外
     */
    public DataList<IfaMutualFundDetailInfoSql004ResponseModel> selectIfaMutualFundDetailInfoSql004(
            IfaMutualFundDetailInfoSql004RequestModel req) throws Exception {
        
        DataList<IfaMutualFundDetailInfoSql004ResponseModel> res = new DataList<IfaMutualFundDetailInfoSql004ResponseModel>();
        // 集合クエリのため、存在しない場合はnull応答になるため除外する
        res.setDataList(mapper.selectIfaMutualFundDetailInfoSql004(req).stream().filter(Objects::nonNull)
                .collect(Collectors.toList()));
        return res;
    }
    
    /**
     * SQLID：Sql005
     * SQL名：締切日（次回）取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundDetailInfoSql005RequestModel
     * レスポンスクラス：IfaMutualFundDetailInfoSql005ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 実行時例外
     */
    public DataList<IfaMutualFundDetailInfoSql005ResponseModel> selectIfaMutualFundDetailInfoSql005(
            IfaMutualFundDetailInfoSql005RequestModel req) throws Exception {
        
        DataList<IfaMutualFundDetailInfoSql005ResponseModel> res = new DataList<IfaMutualFundDetailInfoSql005ResponseModel>();
        // 集合クエリのため、存在しない場合はnull応答になるため除外する
        res.setDataList(mapper.selectIfaMutualFundDetailInfoSql005(req).stream().filter(Objects::nonNull)
                .collect(Collectors.toList()));
        return res;
    }
    
    /**
     * SQLID：Sql006
     * SQL名：当社からのお知らせ取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundDetailInfoSql006RequestModel
     * レスポンスクラス：IfaMutualFundDetailInfoSql006ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 実行時例外
     */
    public DataList<IfaMutualFundDetailInfoSql006ResponseModel> selectIfaMutualFundDetailInfoSql006(
            IfaMutualFundDetailInfoSql006RequestModel req) throws Exception {
        
        DataList<IfaMutualFundDetailInfoSql006ResponseModel> res = new DataList<IfaMutualFundDetailInfoSql006ResponseModel>();
        
        res.setDataList(mapper.selectIfaMutualFundDetailInfoSql006(req));
        return res;
    }
    
    /**
     * SQLID：Sql009
     * SQL名：投信明細取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundDetailInfoSql009RequestModel
     * レスポンスクラス：IfaMutualFundDetailInfoSql009ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 実行時例外
     */
    public DataList<IfaMutualFundDetailInfoSql009ResponseModel> selectIfaMutualFundDetailInfoSql009(
            IfaMutualFundDetailInfoSql009RequestModel req) throws Exception {
        
        DataList<IfaMutualFundDetailInfoSql009ResponseModel> res = new DataList<IfaMutualFundDetailInfoSql009ResponseModel>();
        
        res.setDataList(mapper.selectIfaMutualFundDetailInfoSql009(req));
        return res;
    }
    
    /**
     * SQLID：Sql010
     * SQL名：手数料取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundDetailInfoSql010RequestModel
     * レスポンスクラス：IfaMutualFundDetailInfoSql010ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 実行時例外
     */
    public DataList<IfaMutualFundDetailInfoSql010ResponseModel> selectIfaMutualFundDetailInfoSql010(
            IfaMutualFundDetailInfoSql010RequestModel req) throws Exception {
        
        DataList<IfaMutualFundDetailInfoSql010ResponseModel> res = new DataList<IfaMutualFundDetailInfoSql010ResponseModel>();
        
        res.setDataList(mapper.selectIfaMutualFundDetailInfoSql010(req));
        return res;
    }
    
    /**
     * SQLID：Sql011
     * SQL名：扱者別手数料取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundDetailInfoSql011RequestModel
     * レスポンスクラス：IfaMutualFundDetailInfoSql011ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 実行時例外
     */
    public DataList<IfaMutualFundDetailInfoSql011ResponseModel> selectIfaMutualFundDetailInfoSql011(
            IfaMutualFundDetailInfoSql011RequestModel req) throws Exception {
        
        DataList<IfaMutualFundDetailInfoSql011ResponseModel> res = new DataList<IfaMutualFundDetailInfoSql011ResponseModel>();
        
        res.setDataList(mapper.selectIfaMutualFundDetailInfoSql011(req));
        return res;
    }
    
    /**
     * SQLID：Sql012
     * SQL名：積立単位取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundDetailInfoSql012RequestModel
     * レスポンスクラス：IfaMutualFundDetailInfoSql012ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 実行時例外
     */
    public DataList<IfaMutualFundDetailInfoSql012ResponseModel> selectIfaMutualFundDetailInfoSql012(
            IfaMutualFundDetailInfoSql012RequestModel req) throws Exception {
        
        DataList<IfaMutualFundDetailInfoSql012ResponseModel> res = new DataList<IfaMutualFundDetailInfoSql012ResponseModel>();
        
        res.setDataList(mapper.selectIfaMutualFundDetailInfoSql012(req));
        return res;
    }
    
    /**
     * SQLID：Sql013
     * SQL名：消費税取得
     * SQLタイプ：select
     * レスポンスクラス：IfaMutualFundDetailInfoSql013ResponseModel
     *
     * @return レスポンス
     * @exception Exception 実行時例外
     */
    public DataList<IfaMutualFundDetailInfoSql013ResponseModel> selectIfaMutualFundDetailInfoSql013() throws Exception {
        
        DataList<IfaMutualFundDetailInfoSql013ResponseModel> res = new DataList<IfaMutualFundDetailInfoSql013ResponseModel>();
        
        res.setDataList(mapper.selectIfaMutualFundDetailInfoSql013());
        return res;
    }
    
}
