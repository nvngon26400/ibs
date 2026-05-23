package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaContactDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaContactMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql006ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql007ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql008ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql011RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql011ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql012RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql012ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql013RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql013ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql015RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql015ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql016RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql016ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql017RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql017ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql018RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql018ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql019RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql019ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql020RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql020ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql021ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql022ResponseModel;

/**
 * 画面ID：SUB0202_0106-01
 * 画面名：接触履歴
 * 
 * @author 趙韫慧
 * 2025/03/27 新規作成
 *
 */
@Component
public class IfaContactDaoImpL extends RowSelectableDao implements IfaContactDao {

    @Autowired
    private IfaContactMapper mapper;

    /**
     * SQLID：Sql002
     * SQL名：国内株式現物注文接触履歴取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactSql002RequestModel
     * レスポンスクラス：IfaContactSql002ResponseModel
     * 
     * @param req リクエスト
     * @return 国内株式現物注文履歴情報
     * @exception Exception 異常
     */
    public DataList<IfaContactSql002ResponseModel> selectIfaContactSql002(IfaContactSql002RequestModel req)
            throws Exception {

        DataList<IfaContactSql002ResponseModel> res = new DataList<IfaContactSql002ResponseModel>();

        res.setDataList(mapper.selectIfaContactSql002(req));
        return res;
    }

    /**
     * SQLID：Sql003
     * SQL名：国内株式信用新規(返済)注文接触履歴取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactSql003RequestModel
     * レスポンスクラス：IfaContactSql003ResponseModel
     * 
     * @param req リクエスト
     * @return 国内株式信用新規(返済)注文履歴情報
     * @exception Exception 異常
     */
    public DataList<IfaContactSql003ResponseModel> selectIfaContactSql003(IfaContactSql003RequestModel req)
            throws Exception {

        DataList<IfaContactSql003ResponseModel> res = new DataList<IfaContactSql003ResponseModel>();

        res.setDataList(mapper.selectIfaContactSql003(req));
        return res;
    }

    /**
     * SQLID：Sql004
     * SQL名：問合せ接触履歴取得(当日まで)
     * SQLタイプ：select
     * リクエストクラス：IfaContactSql004RequestModel
     * レスポンスクラス：IfaContactSql004ResponseModel
     * 
     * @param req リクエスト
     * @return 問合せ接触履歴情報
     * @exception Exception 異常
     */
    public DataList<IfaContactSql004ResponseModel> selectIfaContactSql004(IfaContactSql004RequestModel req)
            throws Exception {

        DataList<IfaContactSql004ResponseModel> res = new DataList<IfaContactSql004ResponseModel>();

        res.setDataList(mapper.selectIfaContactSql004(req));
        return res;
    }

    /**
     * SQLID：Sql005
     * SQL名：書類請求接触履歴取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactSql005RequestModel
     * レスポンスクラス：IfaContactSql005ResponseModel
     * 
     * @param req リクエスト
     * @return 書類請求接触履歴情報
     * @exception Exception 異常
     */
    public DataList<IfaContactSql005ResponseModel> selectIfaContactSql005(IfaContactSql005RequestModel req)
            throws Exception {

        DataList<IfaContactSql005ResponseModel> res = new DataList<IfaContactSql005ResponseModel>();

        res.setDataList(mapper.selectIfaContactSql005(req));
        return res;
    }

    /**
     * SQLID：Sql006
     * SQL名：出金接触履歴取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactSql006RequestModel
     * レスポンスクラス：IfaContactSql006ResponseModel
     * 
     * @param req リクエスト
     * @return 出金接触履歴情報
     * @exception Exception 異常
     */
    public DataList<IfaContactSql006ResponseModel> selectIfaContactSql006(IfaContactSql006RequestModel req)
            throws Exception {

        DataList<IfaContactSql006ResponseModel> res = new DataList<IfaContactSql006ResponseModel>();

        res.setDataList(mapper.selectIfaContactSql006(req));
        return res;
    }

    /**
     * SQLID：Sql007
     * SQL名：出金取消接触履歴取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactSql007RequestModel
     * レスポンスクラス：IfaContactSql007ResponseModel
     * 
     * @param req リクエスト
     * @return 出金接触履歴情報
     * @exception Exception 異常
     */
    public DataList<IfaContactSql007ResponseModel> selectIfaContactSql007(IfaContactSql007RequestModel req)
            throws Exception {

        DataList<IfaContactSql007ResponseModel> res = new DataList<IfaContactSql007ResponseModel>();

        res.setDataList(mapper.selectIfaContactSql007(req));
        return res;
    }

    /**
     * SQLID：Sql008
     * SQL名：国内注文接触履歴見出
     * SQLタイプ：select
     * リクエストクラス：IfaContactSql008RequestModel
     * レスポンスクラス：IfaContactSql008ResponseModel
     * 
     * @param req リクエスト
     * @return 国内注文接触履歴情報
     * @exception Exception 異常
     */
    public DataList<IfaContactSql008ResponseModel> selectIfaContactSql008(IfaContactSql008RequestModel req)
            throws Exception {

        DataList<IfaContactSql008ResponseModel> res = new DataList<IfaContactSql008ResponseModel>();

        res.setDataList(mapper.selectIfaContactSql008(req));
        return res;
    }

    /**
     * SQLID：Sql011
     * SQL名：外株委託注文接触履歴取得(当日まで)
     * SQLタイプ：select
     * リクエストクラス：IfaContactSql011RequestModel
     * レスポンスクラス：IfaContactSql011ResponseModel
     * 
     * @param req リクエスト
     * @return 外株委託注文接触履歴情報
     * @exception Exception 異常
     */
    public DataList<IfaContactSql011ResponseModel> selectIfaContactSql011(IfaContactSql011RequestModel req)
            throws Exception {

        DataList<IfaContactSql011ResponseModel> res = new DataList<IfaContactSql011ResponseModel>();

        res.setDataList(mapper.selectIfaContactSql011(req));
        return res;
    }

    /**
     * SQLID：Sql012
     * SQL名：外株店頭注文接触履歴取得(当日まで)
     * SQLタイプ：select
     * リクエストクラス：IfaContactSql012RequestModel
     * レスポンスクラス：IfaContactSql012ResponseModel
     * 
     * @param req リクエスト
     * @return 外株店頭注文接触履歴情報
     * @exception Exception 異常
     */
    public DataList<IfaContactSql012ResponseModel> selectIfaContactSql012(IfaContactSql012RequestModel req)
            throws Exception {

        DataList<IfaContactSql012ResponseModel> res = new DataList<IfaContactSql012ResponseModel>();

        res.setDataList(mapper.selectIfaContactSql012(req));
        return res;
    }

    /**
     * SQLID：Sql013
     * SQL名：為替取引注文接触履歴取得(当日まで)
     * SQLタイプ：select
     * リクエストクラス：IfaContactSql013RequestModel
     * レスポンスクラス：IfaContactSql013ResponseModel
     * 
     * @param req リクエスト
     * @return 為替取引注文接触履歴情報
     * @exception Exception 異常
     */
    public DataList<IfaContactSql013ResponseModel> selectIfaContactSql013(IfaContactSql013RequestModel req)
            throws Exception {

        DataList<IfaContactSql013ResponseModel> res = new DataList<IfaContactSql013ResponseModel>();

        res.setDataList(mapper.selectIfaContactSql013(req));
        return res;
    }

    /**
     * SQLID：Sql015
     * SQL名：国内株式現引現渡注文接触履歴取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactSql015RequestModel
     * レスポンスクラス：IfaContactSql015ResponseModel
     * 
     * @param req リクエスト
     * @return 国内株式現引現渡注文接触履歴情報
     * @exception Exception 異常
     */
    public DataList<IfaContactSql015ResponseModel> selectIfaContactSql015(IfaContactSql015RequestModel req)
            throws Exception {

        DataList<IfaContactSql015ResponseModel> res = new DataList<IfaContactSql015ResponseModel>();

        res.setDataList(mapper.selectIfaContactSql015(req));
        return res;
    }

    /**
     * SQLID：Sql016
     * SQL名：国内投信(一般)注文接触履歴取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactSql016RequestModel
     * レスポンスクラス：IfaContactSql016ResponseModel
     * 
     * @param req リクエスト
     * @return 国内投信(一般)注文接触履歴情報
     * @exception Exception 異常
     */
    public DataList<IfaContactSql016ResponseModel> selectIfaContactSql016(IfaContactSql016RequestModel req)
            throws Exception {

        DataList<IfaContactSql016ResponseModel> res = new DataList<IfaContactSql016ResponseModel>();

        res.setDataList(mapper.selectIfaContactSql016(req));
        return res;
    }

    /**
     * SQLID：Sql017
     * SQL名：国内投信(累投)注文接触履歴取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactSql017RequestModel
     * レスポンスクラス：IfaContactSql017ResponseModel
     * 
     * @param req リクエスト
     * @return 国内投信(累投)注文接触履歴情報
     * @exception Exception 異常
     */
    public DataList<IfaContactSql017ResponseModel> selectIfaContactSql017(IfaContactSql017RequestModel req)
            throws Exception {

        DataList<IfaContactSql017ResponseModel> res = new DataList<IfaContactSql017ResponseModel>();

        res.setDataList(mapper.selectIfaContactSql017(req));
        return res;
    }

    /**
     * SQLID：Sql018
     * SQL名：その他注文その他余力拘束取消接触履歴取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactSql018RequestModel
     * レスポンスクラス：IfaContactSql018ResponseModel
     * 
     * @param req リクエスト
     * @return その他注文その他余力拘束取消接触履歴情報
     * @exception Exception 異常
     */
    public DataList<IfaContactSql018ResponseModel> selectIfaContactSql018(IfaContactSql018RequestModel req)
            throws Exception {

        DataList<IfaContactSql018ResponseModel> res = new DataList<IfaContactSql018ResponseModel>();

        res.setDataList(mapper.selectIfaContactSql018(req));
        return res;
    }

    /**
     * SQLID：Sql019
     * SQL名：IFA出金接触履歴比較
     * SQLタイプ：select
     * リクエストクラス：IfaContactSql019RequestModel
     * レスポンスクラス：IfaContactSql019ResponseModel
     * 
     * @param req リクエスト
     * @return IFA出金接触履歴比較結果
     * @exception Exception 異常
     */
    public DataList<IfaContactSql019ResponseModel> selectIfaContactSql019(IfaContactSql019RequestModel req)
        throws Exception {

        DataList<IfaContactSql019ResponseModel> res = new DataList<IfaContactSql019ResponseModel>();

        res.setDataList(mapper.selectIfaContactSql019(req));
        return res;
    }

    /**
     * SQLID：Sql020
     * SQL名：IFA出金取消接触履歴比較
     * SQLタイプ：select
     * リクエストクラス：IfaContactSql020RequestModel
     * レスポンスクラス：IfaContactSql020ResponseModel
     * 
     * @param req リクエスト
     * @return IFA出金取消接触履歴比較
     * @exception Exception 異常
     */
    public DataList<IfaContactSql020ResponseModel> selectIfaContactSql020(IfaContactSql020RequestModel req)
        throws Exception {

        DataList<IfaContactSql020ResponseModel> res = new DataList<IfaContactSql020ResponseModel>();

        res.setDataList(mapper.selectIfaContactSql020(req));
        return res;
    }

    /**
     * SQLID：Sql021
     * SQL名：同一のIFA注文番号内で最大情報取得
     * SQLタイプ：select
     * レスポンスクラス：IfaContactSql021ResponseModel
     * 
     * @return 最大情報
     * @exception Exception 異常
     */
    public DataList<IfaContactSql021ResponseModel> selectIfaContactSql021() throws Exception {

        DataList<IfaContactSql021ResponseModel> res = new DataList<IfaContactSql021ResponseModel>();

        res.setDataList(mapper.selectIfaContactSql021());
        return res;
    }

    /**
     * SQLID：Sql022
     * SQL名：同一のトランザクションID内で最大情報取得
     * SQLタイプ：select
     * レスポンスクラス：IfaContactSql022ResponseModel
     * 
     * @return 最大情報
     * @exception Exception 異常
     */
    public DataList<IfaContactSql022ResponseModel> selectIfaContactSql022() throws Exception {

        DataList<IfaContactSql022ResponseModel> res = new DataList<IfaContactSql022ResponseModel>();

        res.setDataList(mapper.selectIfaContactSql022());
        return res;
    }
}
