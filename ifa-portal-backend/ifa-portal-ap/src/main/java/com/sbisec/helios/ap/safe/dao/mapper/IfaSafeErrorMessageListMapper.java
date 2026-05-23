package com.sbisec.helios.ap.safe.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.sbisec.helios.ap.safe.dao.model.IfaSafeErrorMessageSql001RequestModel;
import com.sbisec.helios.ap.safe.dao.model.IfaSafeErrorMessageSql001ResponseModel;

/**
 * 異常処理：「共通」エラー文言を取得する
 * 
 * @author rongsheng.he
 *
 *         2025/04/22 新規作成
 */
@Mapper
public interface IfaSafeErrorMessageListMapper{
	
	public IfaSafeErrorMessageSql001ResponseModel selectIfaSafeErrorMessageSql001(
            @Param("req") IfaSafeErrorMessageSql001RequestModel req);
}