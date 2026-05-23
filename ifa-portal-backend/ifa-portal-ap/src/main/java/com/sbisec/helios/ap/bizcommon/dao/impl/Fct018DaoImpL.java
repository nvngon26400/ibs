package com.sbisec.helios.ap.bizcommon.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.bizcommon.dao.Fct018Dao;
import com.sbisec.helios.ap.bizcommon.dao.mapper.Fct018Mapper;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct018Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct018Sql001ResponseModel;


/**
 * 画面ID：FCT018
 * 画面名：サービス時間チェック（外国）
 * @author AKKODiS 齋藤
 *
 * 2023/08/23 新規作成
 */
@Component
public class Fct018DaoImpL extends RowSelectableDao implements Fct018Dao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Fct018DaoImpL.class);
    @Autowired
    private Fct018Mapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：Fct018Sql001RequestModel
     * レスポンスクラス：Fct018Sql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<Fct018Sql001ResponseModel> selectFct018Sql001(Fct018Sql001RequestModel req)
            throws Exception {
        
        DataList<Fct018Sql001ResponseModel> res = new DataList<Fct018Sql001ResponseModel>();
        
        res.setDataList(mapper.selectFct018Sql001(req));
        return res;
    }
    
    
    
    
}
