package com.sbisec.helios.ap.bizcommon.dao.impl;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.dao.Fct004Dao;
import com.sbisec.helios.ap.bizcommon.dao.mapper.Fct004Mapper;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct004Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct004Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct004Sql002ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct004Sql003RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct004Sql003ResponseModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 共通関数：FCT004
 * IFAP米株 余力チェック（金額）
 *
 * @author SCSK
 */
@Component
public class Fct004DaoImpl implements Fct004Dao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Fct004DaoImpl.class);
    
    @Autowired
    protected Fct004Mapper fct004Mapper;
    
    /**
     * コードマスタテーブルから、当日外国債券買（金額）の取得する上で引数として必要となる"遡る時間"を取得する。
     *
     * @return レスポンス
     * @throws Exception 遡る時間の取得に例外が発生した場合
     */
    @Override
    public Fct004Sql001ResponseModel getTime() throws Exception {
        
        return fct004Mapper.getTime();
    }
    
    /**
     * 外債買付代金入力テーブルから、当日外国債券買（金額）を取得して、合計値を算出する。
     *
     * @param fct004Sql002RequestModel リクエスト
     * @return レスポンス
     * @throws Exception 外債買付代金合計の算出に例外が発生した場合
     */
    @Override
    public Fct004Sql002ResponseModel getForeignBondBuyAmount(Fct004Sql002RequestModel fct004Sql002RequestModel)
            throws Exception {
        
        return fct004Mapper.getForeignBondBuyAmount(fct004Sql002RequestModel);
    }
    
    /**
     * 店頭取引注文テーブルから、当日店頭注文（買付）情報を取得する。
     *
     * @param fct004Sql003RequestModel リクエスト
     * @return レスポンス
     * @throws Exception 店頭取引注文情報の取得に例外が発生した場合
     */
    @Override
    public List<Fct004Sql003ResponseModel> getShopTransactionOrder(Fct004Sql003RequestModel fct004Sql003RequestModel)
            throws Exception {
        
        return fct004Mapper.getShopTransactionOrder(fct004Sql003RequestModel);
    }
}
