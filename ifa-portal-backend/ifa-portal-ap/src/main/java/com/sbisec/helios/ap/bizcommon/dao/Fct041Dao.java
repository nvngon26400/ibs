package com.sbisec.helios.ap.bizcommon.dao;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct041Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct041Sql001ResponseModel;

/**
 * 共通関数：FCT041
 * 紐付け共募支店コードを取得する
 * 2024/12/12 新規作成
 *
 * @author 大連 王永宝
 */
public interface Fct041Dao {

	/**
	 * 権限コードが6, 7,
	 * 8のいずれかであるログインユーザについて、参照可能な同一仲介業者内別支店の件数をHorusユーザー権限情報（TB_MED_USERS_PRIV）から取得する。
	 *
	 * @param fct041Sql001RequestModel リクエスト
	 * @return レスポンス
	 * @throws Exception ユーザ権限情報取得（仲介業者支店）に例外が発生した場合
	 */
	public Fct041Sql001ResponseModel getUserAuthorityBranch(Fct041Sql001RequestModel fct041Sql001RequestModel)
			throws Exception;

}
