package com.sbisec.helios.ap.bizcommon.dao.impl;

import com.sbisec.helios.ap.bizcommon.dao.Fct039Dao;
import com.sbisec.helios.ap.bizcommon.dao.mapper.Fct039Mapper;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct039Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct039Sql001ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 共通関数：FCT039
 * ポイント関連項目表示可否取得 
 *
 * @author SCSK
 */
@Component
public class Fct039DaoImpl implements Fct039Dao {
    
    @Autowired
    protected Fct039Mapper fct039Mapper;
    
    /**
     * ポイント関連項目表示可否取得。
     *
     * @param fct039Sql001RequestModel リクエスト
     * @return レスポンス
     */
    @Override
    public Fct039Sql001ResponseModel getPointDisplayPermission(Fct039Sql001RequestModel fct039Sql001RequestModel) {
        
        return fct039Mapper.getPointDisplayPermission(fct039Sql001RequestModel);
    }
    
}
