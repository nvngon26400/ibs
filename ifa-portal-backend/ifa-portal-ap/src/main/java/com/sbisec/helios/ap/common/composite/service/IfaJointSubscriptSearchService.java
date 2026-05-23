package com.sbisec.helios.ap.common.composite.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.composite.dto.IfaJointSubscriptSearchA001DtoRequest;
import com.sbisec.helios.ap.common.composite.dto.IfaJointSubscriptSearchA001DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * サービスのインターフェース
 * 画面ID：CC020
 * 画面名：共同募集検索条件（一覧画面）
 * 2024/12/12 新規作成
 *
 * @author 大連 王永宝
 */
public interface IfaJointSubscriptSearchService extends Service {

	/**
	 * アクションID：A001
	 * アクション名：初期化
	 * Dto リクエスト：IfaJointSubscriptSearchA001DtoRequest
	 * Dto レスポンス：IfaJointSubscriptSearchA001DtoResponse
	 * model リクエスト：IfaJointSubscriptSearchA001RequestModel
	 * model レスポンス：IfaJointSubscriptSearchA001ResponseModel
	 * @param <paramName> <description of param value>
	 * @return <description of return value>
	 * @exception <exceptionName> <description>
	 * @see <reference item>
	 */
	public DataList<IfaJointSubscriptSearchA001DtoResponse> initializeA001(IfaJointSubscriptSearchA001DtoRequest x_dtoReq)
			throws Exception;

}
