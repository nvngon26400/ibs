package com.sbisec.helios.ap.bizcommon.dao.impl;

import com.sbisec.helios.ap.bizcommon.dao.Fct032Dao;
import com.sbisec.helios.ap.bizcommon.dao.mapper.Fct032Mapper;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct032Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct032Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct032Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct032Sql002ResponseModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 共通関数：FCT032
 * ユーザー権限情報取得
 *
 * @author SCSK
 */
@Component
public class Fct032DaoImpl implements Fct032Dao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Fct032DaoImpl.class);
    
    @Autowired
    protected Fct032Mapper fct032Mapper;
    
    /**
     * 権限コードが6, 7, 8のいずれかであるログインユーザについて、参照可能な同一仲介業者内別支店の件数をHorusユーザー権限情報（TB_MED_USERS_PRIV）から取得する。
     *
     * @param fct032Sql001RequestModel リクエスト
     * @return レスポンス
     * @throws Exception ユーザ権限情報取得（仲介業者支店）に例外が発生した場合
     */
    @Override
    public Fct032Sql001ResponseModel getUserAuthorityBranch(Fct032Sql001RequestModel fct032Sql001RequestModel)
            throws Exception {
        
        return fct032Mapper.getUserAuthorityBranch(fct032Sql001RequestModel);
    }
    
    /**
     * ログインユーザが参照可能な仲介業者コードの件数をHorusユーザー権限情報（TB_MED_USERS_PRIV）から取得する。
     *
     * @param fct032Sql001RequestModel リクエスト
     * @return レスポンス
     * @throws Exception ユーザ権限情報取得（仲介業者）に例外が発生した場合
     */
    @Override
    public Fct032Sql002ResponseModel getUserAuthority(Fct032Sql002RequestModel fct032Sql002RequestModel)
            throws Exception {
        
        return fct032Mapper.getUserAuthority(fct032Sql002RequestModel);
    }
    
}
