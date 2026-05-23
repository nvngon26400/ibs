package com.sbisec.helios.ap.bizcommon.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct004Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct004Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct004Sql002ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct004Sql003RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct004Sql003ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface Fct004Mapper {
    
    /**
     * SQL001遡る時間の取得
     * @param fct004Sql001RequestModel 
     * @return
     */
    public Fct004Sql001ResponseModel getTime() throws Exception;
    
    /**
     * SQL002外債買付代金合計の算出
     * @param fct004Sql002RequestModel 
     * @return
     */
    public Fct004Sql002ResponseModel getForeignBondBuyAmount(
            @Param("fct004Sql002RequestModel") Fct004Sql002RequestModel fct004Sql002RequestModel) throws Exception;
    
    /**
     * SQL003店頭取引注文情報の取得
     * @param fct004Sql003RequestModel 
     * @return
     */
    public List<Fct004Sql003ResponseModel> getShopTransactionOrder(
            @Param("fct004Sql003RequestModel") Fct004Sql003RequestModel fct004Sql003RequestModel) throws Exception;
}
