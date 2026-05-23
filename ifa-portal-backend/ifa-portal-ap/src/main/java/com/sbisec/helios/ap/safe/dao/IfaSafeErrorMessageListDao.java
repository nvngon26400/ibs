package com.sbisec.helios.ap.safe.dao;

import org.springframework.stereotype.Component;

import com.sbisec.helios.ap.safe.dao.model.IfaSafeErrorMessageSql001RequestModel;
import com.sbisec.helios.ap.safe.dao.model.IfaSafeErrorMessageSql001ResponseModel;

/*
 * 
 * 共通 : エラーメッセージを取得
 * 
 * @author rongsheng.he
 * 
 * */
@Component
public interface IfaSafeErrorMessageListDao {
	
	/*
	 * SQLID：Sql001
	 * SQL名：エラーメッセージ取得
	 * SQLタイプ：select
	 * リクエストクラス：IfaSafeErrorMessageSql001RequestModel
	 * レスポンスクラス：IfaSafeErrorMessageSql001ResponseModel
	 * 
	 * @param req リクエストモデル
	 * @return 応答データリスト
	 * @exception Exception システム例外
	 * */
	public IfaSafeErrorMessageSql001ResponseModel
			selectIfaSafeErrorMessageSql001(IfaSafeErrorMessageSql001RequestModel req) throws Exception;

}