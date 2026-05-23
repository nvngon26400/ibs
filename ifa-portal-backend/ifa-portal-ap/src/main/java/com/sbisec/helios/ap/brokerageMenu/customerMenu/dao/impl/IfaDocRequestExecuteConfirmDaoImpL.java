package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaDocRequestExecuteConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaDocRequestExecuteConfirmMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestExecuteConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestExecuteConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestExecuteConfirmSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestExecuteConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestExecuteConfirmSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestExecuteConfirmSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestExecuteConfirmSql005RequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0202_0704-02_1 画面名：書類請求確認
 * 
 * @author xin.huang
 *
 */
@Component
public class IfaDocRequestExecuteConfirmDaoImpL extends RowSelectableDao implements IfaDocRequestExecuteConfirmDao {

    @Autowired
    private IfaDocRequestExecuteConfirmMapper mapper;

    /**
     * SQLID：Sql001
     * SQL名：書類請求確認
     * SQLタイプ：insert 
     * リクエスト：IfaDocRequestExecuteConfirmSql001RequestModel
     *
     * @param req {@code IfaDocRequestExecuteConfirmSql001RequestModel }
     * @return int
     * @throws Exception 書類請求確認処理で例外が発生した場合
     */
    public int insertIfaDocRequestExecuteConfirmSql001(IfaDocRequestExecuteConfirmSql001RequestModel req)
            throws Exception {
        return mapper.insertIfaDocRequestExecuteConfirmSql001(req);
    }

    /**
     * SQLID：selectSeqTbCashPayRecJournalIfa
     * SQL名：SEQUENCE(トランザクションID)を取得する
     * SQLタイプ：select
     * レスポンス：String
     *
     * @return String
     * @throws Exception SEQUENCE(トランザクションID)を取得処理で例外が発生した場合
     */
    public String selectSeqTbRequestPapersIfa() throws Exception {
        return mapper.selectSeqTbRequestPapersIfa();
    }

    /**
     * SQLID：Sql002
     * SQL名：未閲読目論見書件数取得
     * SQLタイプ：select
     * リクエスト：IfaDocRequestExecuteConfirmSql002RequestModel
     * レスポンス：IfaDocRequestExecuteConfirmSql002ResponseModel
     *
     * @param req {@code IfaDocRequestExecuteConfirmSql002RequestModel }
     * @return {@code DataList <IfaDocRequestExecuteConfirmSql002ResponseModel>}
     * @throws Exception 未閲読目論見書件数取得処理で例外が発生した場合
     */
    public DataList<IfaDocRequestExecuteConfirmSql002ResponseModel> selectIfaDocRequestExecuteConfirmSql002(
            IfaDocRequestExecuteConfirmSql002RequestModel req) throws Exception {
        DataList<IfaDocRequestExecuteConfirmSql002ResponseModel> res = new DataList<IfaDocRequestExecuteConfirmSql002ResponseModel>();
        res.setDataList(mapper.selectIfaDocRequestExecuteConfirmSql002(req));
        return res;
    }

    /**
     * SQLID：Sql003
     * SQL名：IFA用BMスケジュール情報取得
     * SQLタイプ：select 
     * リクエスト：IfaDocRequestExecuteConfirmSql003RequestModel
     * レスポンス：IfaDocRequestExecuteConfirmSql003ResponseModel
     *
     * @param req {@code IfaDocRequestExecuteConfirmSql003RequestModel }
     * @return {@code DataList <IfaDocRequestExecuteConfirmSql003ResponseModel>}
     * @throws Exception IFA用BMスケジュール情報取得で例外が発生した場合
     */
    public DataList<IfaDocRequestExecuteConfirmSql003ResponseModel> selectIfaDocRequestExecuteConfirmSql003(
            IfaDocRequestExecuteConfirmSql003RequestModel req) throws Exception {
        DataList<IfaDocRequestExecuteConfirmSql003ResponseModel> res = new DataList<IfaDocRequestExecuteConfirmSql003ResponseModel>();
        res.setDataList(mapper.selectIfaDocRequestExecuteConfirmSql003(req));
        return res;
    }

    /**
     * SQLID：Sql004
     * SQL名：1回目配信予定時刻取得
     * SQLタイプ：select 
     * レスポンス：IfaDocRequestExecuteConfirmSql004ResponseModel
     *
     * @return {@code DataList <IfaDocRequestExecuteConfirmSql004ResponseModel>}
     * @throws Exception 1回目配信予定時刻取得で例外が発生した場合
     */
    public DataList<IfaDocRequestExecuteConfirmSql004ResponseModel> selectIfaDocRequestExecuteConfirmSql004()
            throws Exception {
        DataList<IfaDocRequestExecuteConfirmSql004ResponseModel> res = new DataList<IfaDocRequestExecuteConfirmSql004ResponseModel>();
        res.setDataList(mapper.selectIfaDocRequestExecuteConfirmSql004());
        return res;
    }

    /**
     * SQLID：Sql005
     * SQL名：BM交付登録
     * SQLタイプ：insert 
     * リクエスト：IfaDocRequestExecuteConfirmSql005RequestModel
     *
     * @param req {@code IfaDocRequestExecuteConfirmSql005RequestModel }
     * @return int
     * @throws ExceptionBM交付登録で例外が発生した場合
     */
    public int insertIfaDocRequestExecuteConfirmSql005(IfaDocRequestExecuteConfirmSql005RequestModel req)
            throws Exception {
        return mapper.insertIfaDocRequestExecuteConfirmSql005(req);
    }

    /**
     * SQLID：selectSeqTbBmDeliveryIfa
     * SQL名：SEQUENCE(トランザクションID)を取得する
     * SQLタイプ：select
     * レスポンス：String
     *
     * @return String
     * @throws Exception SEQUENCE(トランザクションID)を取得処理で例外が発生した場合
     */
    public String selectSeqTbBmDeliveryIfa() throws Exception {
        return mapper.selectSeqTbBmDeliveryIfa();
    }
}
