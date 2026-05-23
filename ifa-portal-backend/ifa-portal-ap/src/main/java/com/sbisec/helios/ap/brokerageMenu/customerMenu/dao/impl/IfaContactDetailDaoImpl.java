package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaContactDetailDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaContactDetailMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactDetailSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactDetailSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactDetailSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactDetailSql002ResponseModel;

/**
 * DB処理インターフェースの実装クラス
 * 画面ID:SUB0202_0106-06
 * 画面名:接触履歴詳細
 * 2025/04/24 新規作成
 *
 * @author 大連 王永宝
 */
@Component
public class IfaContactDetailDaoImpl extends RowSelectableDao 
    implements IfaContactDetailDao {

    @Autowired
    private IfaContactDetailMapper g_mapper;

    /**
     * SQLID:Sql001
     * SQL名:問合せ情報取得
     * SQLタイプ:select
     * リクエストクラス：IfaContactDetailSql001RequestModel
     * レスポンスクラス：IfaContactDetailSql001ResponseModel
     *
     * @param x_req prepared statement
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaContactDetailSql001ResponseModel> selectIfaContactDetailSql001(
            IfaContactDetailSql001RequestModel x_req) throws Exception {
        // 結果の格納先を準備
        DataList<IfaContactDetailSql001ResponseModel> p_res = new DataList<IfaContactDetailSql001ResponseModel>();
        // SQLを実行し結果を格納    
        p_res.setDataList(g_mapper.selectIfaContactDetailSql001(x_req));
        return p_res;
    }

    /**
     * SQLID:Sql002
     * SQL名:問合せ回答内容情報取得
     * SQLタイプ:select
     * リクエストクラス：IfaContactDetailSql002RequestModel
     * レスポンスクラス：IfaContactDetailSql002ResponseModel
     *
     * @param x_req prepared statement
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaContactDetailSql002ResponseModel> selectIfaContactDetailSql002(
            IfaContactDetailSql002RequestModel x_req) throws Exception {
        // 結果の格納先を準備
        DataList<IfaContactDetailSql002ResponseModel> p_res = new DataList<IfaContactDetailSql002ResponseModel>();
        // SQLを実行し結果を格納    
        p_res.setDataList(g_mapper.selectIfaContactDetailSql002(x_req));
        return p_res;
    }
}
