package com.sbisec.helios.ap.athena.dao;


import org.springframework.stereotype.Component;
import com.sbisec.helios.ap.athena.model.AthenaErrorMessageModel;

@Component
public interface CometCommonDao {

	/**
	 * Athenaエラーメッセージマスタ（ATHENA_ERROR_MESSAGE_MST）エラーコードとエラーメッセージを取得する
	 * 
	 * @param apiErrorCode APIエラーコード
	 * @return AthenaErrorMessageModel モテル
	 * @throws Exception 異常
	 */
	public AthenaErrorMessageModel getAthenaErrorCodeAndMessage(String apiErrorCode) throws Exception;

}
