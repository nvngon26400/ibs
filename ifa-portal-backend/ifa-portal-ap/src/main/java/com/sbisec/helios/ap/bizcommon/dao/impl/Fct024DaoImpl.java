package com.sbisec.helios.ap.bizcommon.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.bizcommon.dao.Fct024Dao;
import com.sbisec.helios.ap.bizcommon.dao.mapper.Fct024Mapper;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct024Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct024Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct024Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct024Sql002ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct024Sql003RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct024Sql003ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 共通関数DAOImpl： FCT024
 *
 * @author 陳
 */

@Component
public class Fct024DaoImpl extends RowSelectableDao implements Fct024Dao {
    
    @Autowired
    private Fct024Mapper mapper;
    
    /**
     * 国内投信販売手数料取得
     *
     * @param sql002Req sql002リクエスト
     * @return sql002レスポンス
     */
    @Override
    public Fct024Sql002ResponseModel getFct024Sql002(Fct024Sql002RequestModel sql002Req) {
        
        return mapper.getFct024Sql002(sql002Req);
    }
    
    /**
     * 国内投信基準価額取得
     *
     * @param sql001Req sql001リクエスト
     * @return sql001レスポンス
     */
    @Override
    public Fct024Sql001ResponseModel getFct024Sql001(Fct024Sql001RequestModel sql001Req) {
        
        return mapper.getFct024Sql001(sql001Req);
    }
    
    /**
     * 扱者個別国内投信販売手数料取得
     *
     * @param sql003Req sql003リクエスト
     * @return sql003レスポンス
     */
    @Override
    public Fct024Sql003ResponseModel getFct024Sql003(Fct024Sql003RequestModel sql003Req) {
        
        return mapper.getFct024Sql003(sql003Req);
    }
    
}
