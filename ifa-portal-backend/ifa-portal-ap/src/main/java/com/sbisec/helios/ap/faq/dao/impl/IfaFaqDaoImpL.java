package com.sbisec.helios.ap.faq.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.faq.dao.IfaFaqDao;
import com.sbisec.helios.ap.faq.dao.mapper.IfaFaqMapper;
import com.sbisec.helios.ap.faq.dao.model.IfaFaqSql001RequestModel;
import com.sbisec.helios.ap.faq.dao.model.IfaFaqSql001ResponseModel;
import com.sbisec.helios.ap.faq.dao.model.IfaFaqSql002ResponseModel;
import com.sbisec.helios.ap.faq.dao.model.IfaFaqSql003RequestModel;
import com.sbisec.helios.ap.faq.dao.model.IfaFaqSql003ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB00-05
 * 画面名：よくある質問
 *
 * @author SCSK 仁井田
 2024/05/30 新規作成
 */
@Component
public class IfaFaqDaoImpL extends RowSelectableDao implements IfaFaqDao {
    
    @Autowired
    private IfaFaqMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：コンテンツ本文取得
     * SQLタイプ：select
     * リクエストクラス：IfaFaqSql001RequestModel
     * レスポンスクラス：IfaFaqSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaFaqSql001ResponseModel> selectIfaFaqSql001(IfaFaqSql001RequestModel req) throws Exception {
        
        DataList<IfaFaqSql001ResponseModel> res = new DataList<IfaFaqSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaFaqSql001(req));
        return res;
    }
    
    /**
     * SQLID：Sql002
     * SQL名：コンテンツリスト取得
     * SQLタイプ：select
     * リクエストクラス：IfaFaqSql002RequestModel
     * レスポンスクラス：IfaFaqSql002ResponseModel
     *
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaFaqSql002ResponseModel> selectIfaFaqSql002() throws Exception {
        
        DataList<IfaFaqSql002ResponseModel> res = new DataList<IfaFaqSql002ResponseModel>();
        
        res.setDataList(mapper.selectIfaFaqSql002());
        return res;
    }
    
    /**
     * SQLID：Sql003
     * SQL名：コンテンツ本文検索
     * SQLタイプ：select
     * リクエストクラス：IfaFaqSql003RequestModel
     * レスポンスクラス：IfaFaqSql003ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaFaqSql003ResponseModel> selectIfaFaqSql003(IfaFaqSql003RequestModel req) throws Exception {
        
        DataList<IfaFaqSql003ResponseModel> res = new DataList<IfaFaqSql003ResponseModel>();
        
        res.setDataList(mapper.selectIfaFaqSql003(req));
        return res;
    }
    
}
