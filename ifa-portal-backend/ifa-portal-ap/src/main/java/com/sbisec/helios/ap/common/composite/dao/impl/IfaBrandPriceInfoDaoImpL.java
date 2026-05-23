package com.sbisec.helios.ap.common.composite.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.composite.dao.IfaBrandPriceInfoDao;
import com.sbisec.helios.ap.common.composite.dao.mapper.IfaBrandPriceInfoMapper;
import com.sbisec.helios.ap.common.composite.model.IfaBrandPriceInfoSql001RequestModel;
import com.sbisec.helios.ap.common.composite.model.IfaBrandPriceInfoSql001ResponseModel;
import com.sbisec.helios.ap.common.composite.model.IfaBrandPriceInfoSql002RequestModel;
import com.sbisec.helios.ap.common.composite.model.IfaBrandPriceInfoSql002ResponseModel;
import com.sbisec.helios.ap.common.composite.model.IfaBrandPriceInfoSql003RequestModel;
import com.sbisec.helios.ap.common.composite.model.IfaBrandPriceInfoSql003ResponseModel;

/**
 * 画面ID：CC013
 * 画面名：銘柄時価情報（国内株）
 * @author <author-name>
 *
 * 2023/08/24 新規作成
 */
@Component
public class IfaBrandPriceInfoDaoImpL extends RowSelectableDao implements IfaBrandPriceInfoDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaBrandPriceInfoDaoImpL.class);
    
    @Autowired
    private IfaBrandPriceInfoMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaBrandPriceInfoSql001RequestModel
     * レスポンスクラス：IfaBrandPriceInfoSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaBrandPriceInfoSql001ResponseModel> selectIfaBrandPriceInfoSql001(
            IfaBrandPriceInfoSql001RequestModel req) throws Exception {
        
        DataList<IfaBrandPriceInfoSql001ResponseModel> res = new DataList<IfaBrandPriceInfoSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaBrandPriceInfoSql001(req));
        return res;
    }
    
    /**
     * SQLID：Sql002
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaBrandPriceInfoSql002RequestModel
     * レスポンスクラス：IfaBrandPriceInfoSql002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaBrandPriceInfoSql002ResponseModel> selectIfaBrandPriceInfoSql002(
            IfaBrandPriceInfoSql002RequestModel req) throws Exception {
        
        DataList<IfaBrandPriceInfoSql002ResponseModel> res = new DataList<IfaBrandPriceInfoSql002ResponseModel>();
        
        res.setDataList(mapper.selectIfaBrandPriceInfoSql002(req));
        return res;
    }

    /**
     * SQLID：Sql003
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaBrandPriceInfoSql003RequestModel
     * レスポンスクラス：IfaBrandPriceInfoSql003ResponseModel
     * @param req リクエストパラメータ
     * @return 呼値管理番号
     * @exception Exception システムエラー
     */
    public DataList<IfaBrandPriceInfoSql003ResponseModel> selectIfaBrandPriceInfoSql003(
            IfaBrandPriceInfoSql003RequestModel req) throws Exception {
        
        DataList<IfaBrandPriceInfoSql003ResponseModel> res = new DataList<IfaBrandPriceInfoSql003ResponseModel>();
        
        res.setDataList(mapper.selectIfaBrandPriceInfoSql003(req));
        return res;
    }
    
}
