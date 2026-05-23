package com.sbisec.helios.ap.bizcommon.dao;

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
public interface Fct032Dao {
    
    /**
     * 権限コードが6, 7, 8のいずれかであるログインユーザについて、参照可能な同一仲介業者内別支店の件数をHorusユーザー権限情報（TB_MED_USERS_PRIV）から取得する。
     *
     * @param fct032Sql001RequestModel リクエスト
     * @return レスポンス
     * @throws Exception ユーザ権限情報取得（仲介業者支店）に例外が発生した場合
     */
    public Fct032Sql001ResponseModel getUserAuthorityBranch(Fct032Sql001RequestModel fct032Sql001RequestModel)
            throws Exception;
    
    /**
     * ログインユーザが参照可能な仲介業者コードの件数をHorusユーザー権限情報（TB_MED_USERS_PRIV）から取得する。
     *
     * @param fct032Sql002RequestModel リクエスト
     * @return レスポンス
     * @throws Exception ユーザ権限情報取得（仲介業者）に例外が発生した場合
     */
    public Fct032Sql002ResponseModel getUserAuthority(Fct032Sql002RequestModel fct032Sql002RequestModel)
            throws Exception;
    
}
