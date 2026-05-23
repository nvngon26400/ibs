package com.sbisec.helios.ap.bizcommon.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct032Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct032Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct032Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct032Sql002ResponseModel;

/**
 * 共通関数：FCT032
 * ユーザー権限情報取得
 *
 * @author SCSK
 */
@Mapper
public interface Fct032Mapper {
    
    /**
     * SQL001ユーザ権限情報取得（仲介業者支店）
     *
     * @param fct032Sql001RequestModel リクエスト
     * @return レスポンス
     */
    public Fct032Sql001ResponseModel getUserAuthorityBranch(
            @Param("fct032Sql001RequestModel") Fct032Sql001RequestModel fct032Sql001RequestModel) throws Exception;
    
    /**
     * SQL002ユーザ権限情報取得（仲介業者）
     *
     * @param fct032Sql002RequestModel リクエスト
     * @return レスポンス
     */
    public Fct032Sql002ResponseModel getUserAuthority(
            @Param("fct032Sql002RequestModel") Fct032Sql002RequestModel fct032Sql002RequestModel) throws Exception;
    
}
