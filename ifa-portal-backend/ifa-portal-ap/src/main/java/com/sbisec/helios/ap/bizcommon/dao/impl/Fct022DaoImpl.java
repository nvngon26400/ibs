package com.sbisec.helios.ap.bizcommon.dao.impl;

import java.util.List;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.bizcommon.component.Fct022;
import com.sbisec.helios.ap.bizcommon.dao.Fct022Dao;
import com.sbisec.helios.ap.bizcommon.dao.mapper.Fct022Mapper;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct022Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct022Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct022Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct022Sql002ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct022Sql003RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct022Sql003ResponseModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 共通関数：FCT022
 * FCT022_DaoImpl
 *
 * @author 陳
 */
@Component
public class Fct022DaoImpl extends RowSelectableDao implements Fct022Dao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Fct022.class);
    
    @Autowired
    private Fct022Mapper mapper;
    
    /**
     * 国内投信販売手数料取得
     *
     * @param sql002Req SQL002リクエストモデル
     * @return SQL002レスポンスモデルリスト
     */
    @Override
    public List<Fct022Sql002ResponseModel> getFct022Sql002(Fct022Sql002RequestModel sql002Req) {
        
        //ログ出力
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Fct022DaoImpl.getFct022Sql002");
        }
        
        return mapper.getFct022Sql002(sql002Req);
    }
    
    /**
     * 国内投信基準価額取得
     
     *　@param sql001Req SQL001リクエスト
     *　@return SQL001レスポンス
     */
    @Override
    public List<Fct022Sql001ResponseModel> getFct022Sql001(Fct022Sql001RequestModel sql001Req) {
        
        //ログ出力
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Fct022DaoImpl.getFct022Sql001");
        }
        
        return mapper.getFct022Sql001(sql001Req);
    }
    
    /**
     * 扱者個別国内投信販売手数料取得
     *
     * @param sql003Req SQL003リクエストモデル
     * @return SQL001レスポンスモデルリスト
     */
    @Override
    public List<Fct022Sql003ResponseModel> getFct022Sql003(Fct022Sql003RequestModel sql003Req) {
        
        //ログ出力
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Fct022DaoImpl.getFct022Sql003");
        }
        
        return mapper.getFct022Sql003(sql003Req);
    }
    
}
