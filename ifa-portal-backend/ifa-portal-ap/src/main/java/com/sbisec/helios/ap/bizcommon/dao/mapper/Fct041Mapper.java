package com.sbisec.helios.ap.bizcommon.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.bizcommon.dao.model.Fct041Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct041Sql001ResponseModel;

/**
 * 共通関数：FCT032
 * ユーザー権限情報取得
 *
 * @author SCSK
 */
@Mapper
public interface Fct041Mapper {

	/**
	 * SQL001紐付け共募支店コード取得
	 *
	 * @param fct041Sql001RequestModel リクエスト
	 * @return レスポンス
	 */
	public Fct041Sql001ResponseModel getUserAuthorityBranch(
			@Param("fct041Sql001RequestModel") Fct041Sql001RequestModel fct041Sql001RequestModel) throws Exception;

}
