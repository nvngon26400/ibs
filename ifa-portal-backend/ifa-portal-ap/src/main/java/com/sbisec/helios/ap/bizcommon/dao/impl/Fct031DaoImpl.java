package com.sbisec.helios.ap.bizcommon.dao.impl;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.dao.Fct031Dao;
import com.sbisec.helios.ap.bizcommon.dao.mapper.Fct031Mapper;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct031Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct031Sql001ResponseModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 共通関数：FCT031
 * 顧客情報取得
 *
 * @author SCSK
 */
@Component
public class Fct031DaoImpl implements Fct031Dao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Fct031DaoImpl.class);
    
    @Autowired
    protected Fct031Mapper fct031Mapper;
    
    /**
     * 仲介業者顧客口座属性（MEDIATE_USER_ATTRIBUTE_INFO）テーブル等より顧客情報を取得する。
     *
     * @param fct031Sql001RequestModel リクエスト
     * @return レスポンス
     * @throws Exception 顧客情報取得に例外が発生した場合
     */
    @Override
    public List<Fct031Sql001ResponseModel> getCustomerInfo(Fct031Sql001RequestModel fct031Sql001RequestModel)
            throws Exception {
        
        return fct031Mapper.getCustomerInfo(fct031Sql001RequestModel);
    }
    
}
