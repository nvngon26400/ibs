package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaForeignMarginAutoTransferSettingConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaForeignMarginAutoTransferSettingConfirmMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginAutoTransferSettingConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginAutoTransferSettingConfirmSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginAutoTransferSettingConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginAutoTransferSettingConfirmSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginAutoTransferSettingConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginAutoTransferSettingConfirmSql004RequestModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0202_0306-01_2
 * 画面名：米株信用自動振替設定確認
 * @author <author-name>
 *
 * 2023/11/10 新規作成
 */
@Component
public class IfaForeignMarginAutoTransferSettingConfirmDaoImpL extends RowSelectableDao
        implements IfaForeignMarginAutoTransferSettingConfirmDao {
    
    private static final Logger LOGGER = LoggerFactory
            .getLogger(IfaForeignMarginAutoTransferSettingConfirmDaoImpL.class);
    
    @Autowired
    private IfaForeignMarginAutoTransferSettingConfirmMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：当日店頭取引売却注文のチェック
     * SQLタイプ：select
     * リクエストクラス：IfaForeignMarginAutoTransferSettingConfirmSql001RequestModel
     * レスポンスクラス：IfaForeignMarginAutoTransferSettingConfirmSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaForeignMarginAutoTransferSettingConfirmSql001ResponseModel> selectIfaForeignMarginAutoTransferSettingConfirmSql001(
            IfaForeignMarginAutoTransferSettingConfirmSql001RequestModel req) throws Exception {
        
        DataList<IfaForeignMarginAutoTransferSettingConfirmSql001ResponseModel> res = new DataList<IfaForeignMarginAutoTransferSettingConfirmSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaForeignMarginAutoTransferSettingConfirmSql001(req));
        return res;
    }
    
    /**
     * SQLID：Sql002_1
     * SQL名：当日店頭取引買付注文の入力のチェック
     * SQLタイプ：select
     * リクエストクラス：IfaForeignMarginAutoTransferSettingConfirmSql002RequestModel
     * レスポンスクラス：IfaForeignMarginAutoTransferSettingConfirmSql002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaForeignMarginAutoTransferSettingConfirmSql002ResponseModel> selectIfaForeignMarginAutoTransferSettingConfirmSql002_1(
            IfaForeignMarginAutoTransferSettingConfirmSql002RequestModel req) throws Exception {
        
        DataList<IfaForeignMarginAutoTransferSettingConfirmSql002ResponseModel> res = new DataList<IfaForeignMarginAutoTransferSettingConfirmSql002ResponseModel>();
        
        res.setDataList(mapper.selectIfaForeignMarginAutoTransferSettingConfirmSql002_1(req));
        return res;
    }
    
    /**
     * SQLID：Sql002_2
     * SQL名：外債買付代金の入力のチェック
     * SQLタイプ：select
     * リクエストクラス：IfaForeignMarginAutoTransferSettingConfirmSql002RequestModel
     * レスポンスクラス：IfaForeignMarginAutoTransferSettingConfirmSql002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaForeignMarginAutoTransferSettingConfirmSql002ResponseModel> selectIfaForeignMarginAutoTransferSettingConfirmSql002_2(
            IfaForeignMarginAutoTransferSettingConfirmSql002RequestModel req) throws Exception {
        
        DataList<IfaForeignMarginAutoTransferSettingConfirmSql002ResponseModel> res = new DataList<IfaForeignMarginAutoTransferSettingConfirmSql002ResponseModel>();
        
        res.setDataList(mapper.selectIfaForeignMarginAutoTransferSettingConfirmSql002_2(req));
        return res;
    }
    
    /**
     * SQLID：Sql002_3
     * SQL名：遡る時間の取得
     * SQLタイプ：select
     * リクエストクラス：IfaForeignMarginAutoTransferSettingConfirmSql002RequestModel
     * レスポンスクラス：IfaForeignMarginAutoTransferSettingConfirmSql002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaForeignMarginAutoTransferSettingConfirmSql002ResponseModel> selectIfaForeignMarginAutoTransferSettingConfirmSql002_3(
            IfaForeignMarginAutoTransferSettingConfirmSql002RequestModel req) throws Exception {
        
        DataList<IfaForeignMarginAutoTransferSettingConfirmSql002ResponseModel> res = new DataList<IfaForeignMarginAutoTransferSettingConfirmSql002ResponseModel>();
        
        res.setDataList(mapper.selectIfaForeignMarginAutoTransferSettingConfirmSql002_3(req));
        return res;
    }
    
    /**
     * SQLID：Sql004
     * SQL名：設定指示後の指示情報更新
     * SQLタイプ：update
     * リクエストクラス：IfaForeignMarginAutoTransferSettingConfirmSql004RequestModel
     * レスポンスクラス：IfaForeignMarginAutoTransferSettingConfirmSql004ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int updateIfaForeignMarginAutoTransferSettingConfirmSql004(
            IfaForeignMarginAutoTransferSettingConfirmSql004RequestModel req) throws Exception {
        
        return mapper.updateIfaForeignMarginAutoTransferSettingConfirmSql004(req);
    }
    
    /**
     * SQLID：Sql003
     * SQL名：設定指示前の指示情報登録
     * SQLタイプ：insert
     * リクエストクラス：IfaForeignMarginAutoTransferSettingConfirmSql003RequestModel
     * レスポンスクラス：IfaForeignMarginAutoTransferSettingConfirmSql003ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int insertIfaForeignMarginAutoTransferSettingConfirmSql003(
            IfaForeignMarginAutoTransferSettingConfirmSql003RequestModel req) throws Exception {
        
        return mapper.insertIfaForeignMarginAutoTransferSettingConfirmSql003(req);
    }
    
    /**
     * SQLID：Sql005
     * SQL名：検索
     * SQLタイプ：select
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public String selectIfaForeignMarginAutoTransferSettingConfirmSql005() throws Exception {
        
        return mapper.selectIfaForeignMarginAutoTransferSettingConfirmSql005();
    }
    
}
