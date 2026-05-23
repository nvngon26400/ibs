package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaDocRequestCancelConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaDocRequestCancelConfirmMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestCancelConfirmSql002RequestModel;

/**
 * 画面ID：SUB0202_0704-03_1 画面名：書類請求取消
 * 
 * @author xin.huang
 *
 */
@Component
public class IfaDocRequestCancelConfirmDaoImpL extends RowSelectableDao implements IfaDocRequestCancelConfirmDao {

    @Autowired
    private IfaDocRequestCancelConfirmMapper mapper;

    /**
     * SQLID：Sql001
     * SQL名：書類請求取消
     * SQLタイプ：update 
     * リクエスト：IfaDocRequestCancelConfirmSql001RequestModel
     *
     * @param req {@code IfaDocRequestCancelConfirmSql001RequestModel }
     * @return int
     * @throws Exception 書類請求取消処理で例外が発生した場合
     */
    public int updateIfaDocRequestCancelConfirSql001(IfaDocRequestCancelConfirmSql001RequestModel req) throws Exception {
        return mapper.updateIfaDocRequestCancelConfirSql001(req);
    }

    /**
     * SQLID：Sql002
     * SQL名：BM交付取消
     * SQLタイプ：update 
     * リクエスト：IfaDocRequestCancelConfirmSql002RequestModel
     *
     * @param req {@code IfaDocRequestCancelConfirmSql002RequestModel }
     * @return int
     * @throws Exception 書類請求取消処理で例外が発生した場合
     */
    public int updateIfaDocRequestCancelConfirSql002(IfaDocRequestCancelConfirmSql002RequestModel req) throws Exception {
        return mapper.updateIfaDocRequestCancelConfirSql002(req);
    }
}
