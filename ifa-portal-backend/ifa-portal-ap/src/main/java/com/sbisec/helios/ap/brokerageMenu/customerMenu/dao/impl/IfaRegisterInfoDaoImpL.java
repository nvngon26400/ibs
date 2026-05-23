package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaRegisterInfoDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaRegisterInfoMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaRegisterInfoRequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaRegisterInfoResponseSql001Model;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaRegisterInfoResponseSql002Model;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaRegisterInfoResponseSql003Model;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaRegisterInfoResponseSql004Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 登録情報
 * 2025/02/21 新規作成
 *
 * @author 大連苗
 */
@Component
public class IfaRegisterInfoDaoImpL extends RowSelectableDao implements IfaRegisterInfoDao {
    
    @Autowired
    private IfaRegisterInfoMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：優先表示分類情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaRegisterInfoRequestModel
     * レスポンスクラス：IfaRegisterInfoResponseSql001Model
     *
     * @param req {@code IfaRegisterInfoRequestModel }
     * @return {@code DataList <IfaRegisterInfoResponseSql001Model>}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaRegisterInfoResponseSql001Model> selectIfaRegisterInfoSql001(IfaRegisterInfoRequestModel req)
            throws Exception {
        
        DataList<IfaRegisterInfoResponseSql001Model> res = new DataList<IfaRegisterInfoResponseSql001Model>();
        
        res.setDataList(mapper.selectIfaRegisterInfoSql001(req));
        return res;
    }
    /**
     * SQLID：Sql002
     * SQL名：優先表示分類ヘッダ取得
     * SQLタイプ：select
     * リクエストクラス：IfaRegisterInfoRequestModel
     * レスポンスクラス：IfaRegisterInfoResponseSql002Model
     *
     * @param req {@code IfaRegisterInfoRequestModel }
     * @return {@code DataList <IfaRegisterInfoResponseSql002Model>}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaRegisterInfoResponseSql002Model> selectIfaRegisterInfoSql002(IfaRegisterInfoRequestModel req)
            throws Exception {
        
        DataList<IfaRegisterInfoResponseSql002Model> res = new DataList<IfaRegisterInfoResponseSql002Model>();
        
        res.setDataList(mapper.selectIfaRegisterInfoSql002(req));
        return res;
    }

    /**
     * SQLID：Sql003
     * SQL名：分類情報一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaRegisterInfoRequestModel
     * レスポンスクラス：IfaRegisterInfoResponseSql003Model
     *
     * @param req {@code IfaRegisterInfoRequestModel }
     * @return {@code DataList <IfaRegisterInfoResponseSql003Model>}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaRegisterInfoResponseSql003Model> selectIfaRegisterInfoSql003(IfaRegisterInfoRequestModel req)
            throws Exception {
        
        DataList<IfaRegisterInfoResponseSql003Model> res = new DataList<IfaRegisterInfoResponseSql003Model>();
        
        res.setDataList(mapper.selectIfaRegisterInfoSql003(req));
        return res;
    }

    /**
     * SQLID：Sql004
     * SQL名：登録情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaRegisterInfoRequestModel
     * レスポンスクラス：IfaRegisterInfoResponseSql004Model
     *
     * @param req {@code IfaRegisterInfoRequestModel }
     * @return {@code DataList <IfaRegisterInfoResponseSql004Model>}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaRegisterInfoResponseSql004Model> selectIfaRegisterInfoSql004(IfaRegisterInfoRequestModel req)
            throws Exception {
        
        DataList<IfaRegisterInfoResponseSql004Model> res = new DataList<IfaRegisterInfoResponseSql004Model>();
        
        res.setDataList(mapper.selectIfaRegisterInfoSql004(req));
        return res;
    }

    
    
}
