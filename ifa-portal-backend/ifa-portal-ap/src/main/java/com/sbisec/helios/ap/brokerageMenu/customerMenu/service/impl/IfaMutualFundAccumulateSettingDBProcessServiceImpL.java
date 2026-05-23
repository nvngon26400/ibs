package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaMutualFundAccumulateSettingBulkChangeConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaMutualFundAccumulateSettingCancelConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingBulkChangeConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaMutualFundAccumulateSettingDBProcessService;

/**
 * 投信積立設定
 *
 * @author nicksen.li
 * 
 * 2025/08/27 新規作成
 */
@Component(value = "cmpIfaMutualFundAccumulateSettingDBProcessService")
public class IfaMutualFundAccumulateSettingDBProcessServiceImpL
        implements IfaMutualFundAccumulateSettingDBProcessService {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(IfaMutualFundAccumulateSettingDBProcessServiceImpL.class);

    @Autowired
    private IfaMutualFundAccumulateSettingCancelConfirmDao cancelConfirmDao;

    @Autowired
    private IfaMutualFundAccumulateSettingBulkChangeConfirmDao bulkChangeConfirmDao;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int cancelConfirmSql001(IfaMutualFundAccumulateSettingCancelConfirmSql001RequestModel insSql001Req)
            throws Exception {
        LOGGER.debug("IfaMutualFundAccumulateSettingDBProcessServiceImpL.cancelConfirmSql001:[{}]", "sql001");

        return cancelConfirmDao.insertIfaMutualFundAccumulateSettingCancelConfirmSql001(insSql001Req);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int bulkChangeConfirmSql001(IfaMutualFundAccumulateSettingBulkChangeConfirmSql001RequestModel sql001Req)
            throws Exception {
        LOGGER.debug("IfaMutualFundAccumulateSettingDBProcessServiceImpL.bulkChangeConfirmSql001:[{}]", "sql001");

        return bulkChangeConfirmDao.insertIfaMutualFundAccumulateSettingBulkChangeConfirmSql001(sql001Req);
    }
}
