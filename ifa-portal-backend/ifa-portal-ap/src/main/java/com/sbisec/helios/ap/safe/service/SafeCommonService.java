package com.sbisec.helios.ap.safe.service;

import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.service.Service;
import com.sbisec.helios.ap.safe.common.exception.enums.ErrorLevel;
import com.sbisec.helios.ap.safe.common.exception.enums.ServiceType;
import com.sbisec.helios.ap.safe.dao.model.IfaSafeErrorMessageSql001ResponseModel;
import com.sbisec.helios.ap.safe.service.common.dto.DtoOut;

/**
 * [共通] SafeAPIの共通Serviceクラス
 * @author rongsheng.he
 */
public interface SafeCommonService extends Service {
	/**
	 * Safeエラーコードとエラーメッセージを取得する
	 * 
	 * @param apiErrorCode APIエラーコード errorLeavel
	 * @return SafeErrorMessageModel モデル
	 * @throws Exception
	 */
	public IfaSafeErrorMessageSql001ResponseModel getSafeErrorCodeAndMessage(String apiErrorCode,ErrorLevel errorLevel,ServiceType serviceType) throws Exception;

	/**
	 * Safeエラーコードとエラーメッセージを取得する
	 * 
	 * @param DataList 
	 * @param Exception
	 * @return DataList 
	 * @throws Exception
	 */
	public <T> DataList<T> checkSafeBussinessException(DataList<T> dataList, Exception ex , String... customMessageId) throws Exception;

	/**
	 * Safeエラーコードとエラーメッセージを取得する
	 * 
	 * @param DataList 
	 * @param Exception
	 * @return DataList 
	 * @throws Exception
	 */
	public <T> DataList<T> checkSafeBussinessException(List<T> resDtoList, Exception ex , String... customMessageId) throws Exception;

	/**
	 * Safeワーニングコードとエラーメッセージを取得する
	 * @param <T>
	 * @param dataList
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public <T> DataList<T> checkSafeBussinessWarning(DataList<T> dataList, DtoOut dto) throws Exception;

	/**
	 * Safeワーニングコードとエラーメッセージを取得する
	 * @param <T>
	 * @param resDtoList
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public <T> DataList<T> checkSafeBussinessWarning(List<T> resDtoList, DtoOut dto) throws Exception;
}