package com.sbisec.helios.ap.bizcommon.dao;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct001Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct001Sql001ResponseModel;

/**
 * 利用者顧客参照権限チェックDAO
 *
 * @author SCSK
 *
 */
public interface Fct001Dao {
    
    /**
     * 利用者顧客参照権限チェック
     *
     * @param fct001Sql001RequestModel SQLパラメータ
     * @return 仲介業者顧客口座属性
     */
    public List<Fct001Sql001ResponseModel> getUserCustomerRefAuth(Fct001Sql001RequestModel fct001Sql001RequestModel);
    
}
