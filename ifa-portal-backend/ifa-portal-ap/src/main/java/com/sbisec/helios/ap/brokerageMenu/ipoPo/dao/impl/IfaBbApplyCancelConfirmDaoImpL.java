package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.IfaBbApplyCancelConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.mapper.IfaBbApplyCancelConfirmEtintraMapper;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCancelConfirmSql015RequestModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 画面ID：SUB0204_02-03_2
 * 画面名：BB申込取消確認
 *
 * @author BASE 李
 2024/05/14 新規作成
 */
@Component
public class IfaBbApplyCancelConfirmDaoImpL extends RowSelectableDao implements IfaBbApplyCancelConfirmDao {
    
    @Autowired
    private IfaBbApplyCancelConfirmEtintraMapper mapper;
    
    
    
    
    
    /**
     * SQLID：Sql001
     * SQL名：BB申込情報取消
     * SQLタイプ：delete
     * リクエストクラス：IfaBbApplyCancelConfirmSql001RequestModel
     * レスポンスクラス：IfaBbApplyCancelConfirmSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int deleteIfaBbApplyCancelConfirmSql001(IfaBbApplyCancelConfirmSql001RequestModel req)
            throws Exception {

        return mapper.deleteIfaBbApplyCancelConfirmSql001(req);
    }
    
    
    
    /**
     * SQLID：Sql015
     * SQL名：BB申込情報登録
     * SQLタイプ：delete
     * リクエストクラス：IfaBbApplyCancelConfirmSql015RequestModel
     * レスポンスクラス：IfaBbApplyCancelConfirmSql015ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int deleteIfaBbApplyCancelConfirmSql015(IfaBbApplyCancelConfirmSql015RequestModel req)
            throws Exception {

        return mapper.deleteIfaBbApplyCancelConfirmSql015(req);
    }
}
