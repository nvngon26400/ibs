package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaMutualFundAccumulateSettingCancelConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaMutualFundAccumulateSettingCancelConfirmMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingCancelConfirmSql001RequestModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0202_0403-01
 * 画面名：投信積立設定解除確認
 *
 * @author WJL
 *     2025/04/13 新規作成
 *
 */
@Component
public class IfaMutualFundAccumulateSettingCancelConfirmDaoImpL extends RowSelectableDao
        implements IfaMutualFundAccumulateSettingCancelConfirmDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaMutualFundAccumulateSettingCancelConfirmDaoImpL.class);
    
    @Autowired
    private IfaMutualFundAccumulateSettingCancelConfirmMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：注文取消前の注文登録
     * SQLタイプ：insert
     * リクエストクラス：IfaMutualFundAccumulateSettingCancelConfirmSql001RequestModel
     *
     * @param req リクエストパラメータ
     * @return レスポンス（resModel）：サービス側で使うのはIFA注文番号とIFA注文サブ番号だけ
     * @exception Exception 注文取消前の注文登録処理で例外が発生した場合
     */
    public int insertIfaMutualFundAccumulateSettingCancelConfirmSql001(
            IfaMutualFundAccumulateSettingCancelConfirmSql001RequestModel req) throws Exception {
       
        int insertCnt = 0;       
            
        try {
            insertCnt = mapper.insertIfaMutualFundAccumulateSettingCancelConfirmSql001(req);
        } catch (Exception e) {
            LOGGER.error("Exception occured.");
            LOGGER.info("Exception occured.", e);
            throw e;
        }
   
        return insertCnt;
    }
    
  
}
