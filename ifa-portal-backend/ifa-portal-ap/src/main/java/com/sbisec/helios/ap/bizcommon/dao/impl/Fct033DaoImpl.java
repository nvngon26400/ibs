package com.sbisec.helios.ap.bizcommon.dao.impl;

import java.util.List;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.bizcommon.dao.Fct033Dao;
import com.sbisec.helios.ap.bizcommon.dao.mapper.Fct033Mapper;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct033Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct033Sql001ResponseModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * FCT020 国内株リアル時価取得クDAO
 *
 * @author SCSK
 *
 */
@Component
public class Fct033DaoImpl extends RowSelectableDao implements Fct033Dao {
    
    private static final Logger logger = LoggerFactory.getLogger(Fct033DaoImpl.class);
    
    @Autowired
    private Fct033Mapper fct033Mapper;

    /**
     * 主要取引所コード取得
     * 株式銘柄マスタテーブルから、指定銘柄の主要取引所コードを取得する

     * @param fct020Sql001RequestModel リクエスト
     * @return レスポンス
     * @throws Exception 主要取引所コード取得時に例外が発生した場合
     */
    @Override
    public List<Fct033Sql001ResponseModel> getBusinessDayType(Fct033Sql001RequestModel fct033Sql001RequestModel)
            throws Exception {
        return this.fct033Mapper.getBusinessDayType(fct033Sql001RequestModel);
    }
}
