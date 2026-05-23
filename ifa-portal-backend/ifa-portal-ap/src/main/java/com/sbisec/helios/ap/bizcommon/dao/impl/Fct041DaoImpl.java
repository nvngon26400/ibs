package com.sbisec.helios.ap.bizcommon.dao.impl;

import com.sbisec.helios.ap.bizcommon.dao.Fct041Dao;
import com.sbisec.helios.ap.bizcommon.dao.mapper.Fct041Mapper;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct041Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct041Sql001ResponseModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 共通関数：FCT041
 * ユーザー権限情報取得
 *
 * @author SCSK
 */
@Component
public class Fct041DaoImpl implements Fct041Dao {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(Fct041DaoImpl.class);

	@Autowired
	protected Fct041Mapper fct041Mapper;

	/**
	 * 権限コードが6, 7,
	 * 8のいずれかであるログインユーザについて、参照可能な同一仲介業者内別支店の件数をHorusユーザー権限情報（TB_MED_USERS_PRIV）から取得する。
	 *
	 * @param fct041Sql001RequestModel リクエスト
	 * @return レスポンス
	 * @throws Exception ユーザ権限情報取得（仲介業者支店）に例外が発生した場合
	 */
	@Override
	public Fct041Sql001ResponseModel getUserAuthorityBranch(Fct041Sql001RequestModel fct041Sql001RequestModel)
			throws Exception {
		return fct041Mapper.getUserAuthorityBranch(fct041Sql001RequestModel);
	}

}
