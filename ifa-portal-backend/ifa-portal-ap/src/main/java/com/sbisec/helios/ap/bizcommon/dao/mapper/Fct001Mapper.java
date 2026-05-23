package com.sbisec.helios.ap.bizcommon.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct001Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct001Sql001ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * FCT001 利用者顧客参照権限チェックMapper
 *
 * @author SCSK
 *
 */
@Mapper
public interface Fct001Mapper {
    
    /**
     * 利用者顧客参照権限チェック
     *
     * @param fct001Sql001RequestModel SQLパラメータ
     * @return 仲介業者顧客口座属性
     */
    public List<Fct001Sql001ResponseModel> getUserCustomerRefAuth(
            @Param("fct001Sql001RequestModel") Fct001Sql001RequestModel fct001Sql001RequestModel);
    
}
