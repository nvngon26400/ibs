package com.sbisec.helios.ap.bizcommon.dao;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct009Sql001RequestModel;

/**
 * 共通関数Dao：FCT009
 *
 * @author SCSK
 */

public interface Fct009Dao {
    
    /**
     * 共同募集顧客(共募顧客)チェック
     *
     * @param model リクエストモデル
     * @return Integer　件数
     */
    int jointSubscriptCustomerCheck(Fct009Sql001RequestModel model);

}
