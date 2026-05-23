package com.sbisec.helios.ap.common.service;

import com.sbibits.earth.service.Service;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.athena.model.AthenaErrorMessageModel;

	/**
	 * [共通] AthenaAPIの共通Serviceクラス
	 * @author SCSK
	 */
	public interface CometCommonService extends Service {

	/**
	 * Athenaエラーコードとエラーメッセージを取得する
	 * 
	 * @param apiErrorCode APIエラーコード
	 * @return AthenaErrorMessageModel モデル
	 * @throws Exception
	 */
	public AthenaErrorMessageModel getAthenaErrorCodeAndMessage(String apiErrorCode) throws Exception;
	
	/**
	 * Athenaエラーコードとエラーメッセージを取得する
	 * 
	 * @param DataList 
	 * @param Exception
	 * @return DataList 
	 * @throws Exception
	 */
	public <T> DataList<T> checkBussinessException(DataList<T> dataList,Exception ex,  String... optionalArgs) throws Exception;

}
