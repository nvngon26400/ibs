package com.sbisec.helios.ap.common.composite.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct041;
import com.sbisec.helios.ap.bizcommon.model.InputFct041Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct041Dto;
import com.sbisec.helios.ap.common.composite.dto.IfaJointSubscriptSearchA001DtoRequest;
import com.sbisec.helios.ap.common.composite.dto.IfaJointSubscriptSearchA001DtoResponse;
import com.sbisec.helios.ap.common.composite.service.IfaJointSubscriptSearchService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

/**
 * サービスインターフェースの実装クラス
 * 画面ID：CC020
 * 画面名：共同募集検索条件（一覧画面）
 * 2024/12/12 新規作成
 *
 * @author 大連 王永宝
 */
@Component(value = "cmpIfaJointSubscriptSearchService")
public class IfaJointSubscriptSearchServiceImpL implements IfaJointSubscriptSearchService {

	private static final Logger LOGGER = LoggerFactory.getLogger(IfaJointSubscriptSearchServiceImpL.class);

	@Autowired
	private Fct041 g_fct041;

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
	public DataList<IfaJointSubscriptSearchA001DtoResponse> initializeA001(IfaJointSubscriptSearchA001DtoRequest x_dtoReq) throws Exception {

		if (LOGGER.isDebugEnabled())
			LOGGER.debug("IfaJointSubscriptSearchServiceImplL.initializeA001");

		DataList<IfaJointSubscriptSearchA001DtoResponse> p_dtoRes = new DataList<IfaJointSubscriptSearchA001DtoResponse>();
		List<IfaJointSubscriptSearchA001DtoResponse> p_resDtoList = new ArrayList<IfaJointSubscriptSearchA001DtoResponse>();

		// 入力項目（仲介業者コード、支店コード、営業員コード）の自動入力フラグを取得する。
		// FCT032を呼び出す
		InputFct041Dto p_fct041Req = new InputFct041Dto();
		OutputFct041Dto p_fct041Res = new OutputFct041Dto();
		p_fct041Res = g_fct041.getData(p_fct041Req);
		IfaJointSubscriptSearchA001DtoResponse p_resDto = new IfaJointSubscriptSearchA001DtoResponse();
		// 共募支店コード自動表示フラグ
		p_resDto.setJointBranchCodeAutoDispFlag(p_fct041Res.getJointBranchCodeSettingJudge());
		// 営業員コード自動表示フラグ
		p_resDto.setEmpCodeAutoDispFlag(p_fct041Res.getEmpCodeCodeSettingJudge());

		p_resDtoList.add(p_resDto);
		p_dtoRes = IfaCommonUtil.createDataList(p_resDtoList, ErrorLevel.SUCCESS, "", "");
		return p_dtoRes;
	}

}
