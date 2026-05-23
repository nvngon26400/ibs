package com.sbisec.helios.ap.common.composite.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.composite.dao.IfaNoticeInfoDao;
import com.sbisec.helios.ap.common.composite.dao.mapper.IfaNoticeInfoMapper;
import com.sbisec.helios.ap.common.composite.model.IfaNoticeInfoSql001RequestModel;
import com.sbisec.helios.ap.common.composite.model.IfaNoticeInfoSql001ResponseModel;
import com.sbisec.helios.ap.common.composite.model.IfaNoticeInfoSql002RequestModel;
import com.sbisec.helios.ap.common.composite.model.IfaNoticeInfoSql002ResponseModel;
import com.sbisec.helios.ap.common.composite.model.IfaNoticeInfoSql003RequestModel;
import com.sbisec.helios.ap.common.composite.model.IfaNoticeInfoSql003ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;





/**
 * 画面ID：CC016
 * 画面名：注意情報
 *
 * @author SCSK
 2024/06/20 新規作成
 */
@Component
public class IfaNoticeInfoDaoImpL extends RowSelectableDao implements IfaNoticeInfoDao {
    
    @Autowired
    private IfaNoticeInfoMapper mapper;
    

    /**
     * SQLID：Sql001
     * SQL名：注意情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaNoticeInfoSql001RequestModel
     * レスポンスクラス：IfaNoticeInfoSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaNoticeInfoSql001ResponseModel> selectIfaNoticeInfoSql001(IfaNoticeInfoSql001RequestModel req)
            throws Exception {
        
        DataList<IfaNoticeInfoSql001ResponseModel> res = new DataList<IfaNoticeInfoSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaNoticeInfoSql001(req));
        return res;
    }
    
    /**
     * SQLID：Sql002
     * SQL名：重要なお知らせによる取引制限取得
     * SQLタイプ：select
     * リクエストクラス：IfaNoticeInfoSql002RequestModel
     * レスポンスクラス：IfaNoticeInfoSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaNoticeInfoSql002ResponseModel> selectIfaNoticeInfoSql002(IfaNoticeInfoSql002RequestModel req)
            throws Exception {
        
        DataList<IfaNoticeInfoSql002ResponseModel> res = new DataList<IfaNoticeInfoSql002ResponseModel>();
        
        res.setDataList(mapper.selectIfaNoticeInfoSql002(req));
        return res;
    }
    
    /**
     * SQLID：Sql003
     * SQL名：重要なお知らせによる取引制限の制限番号に対する制限内容の表示文言取得
     * SQLタイプ：select
     * リクエストクラス：IfaNoticeInfoSql003RequestModel
     * レスポンスクラス：IfaNoticeInfoSql003ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaNoticeInfoSql003ResponseModel> selectIfaNoticeInfoSql003(IfaNoticeInfoSql003RequestModel req)
            throws Exception {
        
        DataList<IfaNoticeInfoSql003ResponseModel> res = new DataList<IfaNoticeInfoSql003ResponseModel>();
        
        res.setDataList(mapper.selectIfaNoticeInfoSql003(req));
        return res;
    }
}
