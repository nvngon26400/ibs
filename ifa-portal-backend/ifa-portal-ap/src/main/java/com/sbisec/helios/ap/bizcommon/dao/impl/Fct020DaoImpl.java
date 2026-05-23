package com.sbisec.helios.ap.bizcommon.dao.impl;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.dao.Fct020Dao;
import com.sbisec.helios.ap.bizcommon.dao.mapper.Fct020Mapper;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct020Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct020Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct020Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct020Sql002ResponseModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * FCT020 国内株リアル時価取得クDAO
 *
 * @author SCSK
 */
@Component
public class Fct020DaoImpl implements Fct020Dao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Fct020DaoImpl.class);
    
    @Autowired
    private Fct020Mapper fct020Mapper;
    
    /**
     * 主要取引所コード取得
     * 株式銘柄マスタテーブルから、指定銘柄の主要取引所コードを取得する
     *
     * @param fct020Sql001RequestModel リクエスト
     * @return レスポンス
     */
    @Override
    public List<Fct020Sql001ResponseModel> getPreExCode(Fct020Sql001RequestModel fct020Sql001RequestModel) {
        
        return this.fct020Mapper.getPreExCode(fct020Sql001RequestModel);
    }
    
    /**
     * 基準値取得
     * 銘柄翌日基準値テーブルから、指定銘柄・市場の基準値を取得する。
     *
     * @param fct020Sql002RequestModel リクエスト
     * @return レスポンス
     */
    @Override
    public List<Fct020Sql002ResponseModel> getBasePrice(Fct020Sql002RequestModel fct020Sql002RequestModel) {
        
        return this.fct020Mapper.getBasePrice(fct020Sql002RequestModel);
    }
}
