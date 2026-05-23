package com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.IfaSelfInspectBlotterReplyConfirmReplyReasonInputDao;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.mapper.IfaSelfInspectBlotterReplyConfirmReplyReasonInputMapper;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterReplyConfirmReplyReasonInputSql001RequestModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0401_02-03
 * 画面名：自己点検記録簿回答確認・回答理由入力
 *
 * @author SCSK丹波
 2024/06/05 新規作成
 */
@Component
public class IfaSelfInspectBlotterReplyConfirmReplyReasonInputDaoImpL extends RowSelectableDao
        implements IfaSelfInspectBlotterReplyConfirmReplyReasonInputDao {
    
    @Autowired
    private IfaSelfInspectBlotterReplyConfirmReplyReasonInputMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：自己点検確認テーブル更新
     * SQLタイプ：update
     * リクエストクラス：IfaSelfInspectBlotterReplyConfirmReplyReasonInputSql001RequestModel
     * レスポンスクラス：IfaSelfInspectBlotterReplyConfirmReplyReasonInputSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int updateIfaSelfInspectBlotterReplyConfirmReplyReasonInputSql001(
            IfaSelfInspectBlotterReplyConfirmReplyReasonInputSql001RequestModel req) throws Exception {
        
        return mapper.updateIfaSelfInspectBlotterReplyConfirmReplyReasonInputSql001(req);
    }
    
}
