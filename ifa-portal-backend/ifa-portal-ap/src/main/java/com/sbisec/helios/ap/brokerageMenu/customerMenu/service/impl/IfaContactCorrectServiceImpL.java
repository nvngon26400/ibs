package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.DateUtil;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaContactCorrectDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectSql010RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectSql011RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactAnswerListDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactCorrectA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactCorrectA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactCorrectA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactCorrectA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaContactCorrectService;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.IfaContactApiException;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.IfaContactInputUtil;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.IfaContactInputUtil.ClassName;
import com.sbisec.helios.ap.common.dao.SystemDateDao;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.fasthelp.enums.ProcessId;
import com.sbisec.helios.ap.fasthelp.service.dto.ccs.CcsInAndOut;
import com.sbisec.helios.ap.fasthelp.service.dto.fasthelp.FasthelpCcsCallsImportIn;

/**
 * IFA接触履歴修正サービス実装クラス 画面ID:SUB0202_0106-07 画面名:接触履歴修正
 * 
 * @author 大連 王永宝
 * @date 2025-04-24
 */
@Component(value = "cmpIfaContactCorrectService")
public class IfaContactCorrectServiceImpL implements IfaContactCorrectService {

	/** ロガー */
	private static final Logger LOGGER = LoggerFactory.getLogger(IfaContactCorrectServiceImpL.class);

	/** DB更新完了メッセージキー */
	private static final String INFO_ENDCOMPLETED = "info.endCompleted";

	/** 接触履歴修正メッセージ */
	private static final String CONTACT_CORRECT_MESSAGE = "接触履歴修正";

	/** IFA接触修正DAO */
	@Autowired
	private IfaContactCorrectDao g_dao;

	/** システム日付DAO */
	@Autowired
	private SystemDateDao g_sysdate;

	/** IFA接触入力ユーティリティ */
	@Autowired
	private IfaContactInputUtil g_util;

	/** IFA接触トランザクションサービス */
	@Autowired
	private IfaContactTransactionServiceImpl transactionService;

	/** IFA接触共通サービス */
	@Autowired
	private IfaContactCommonServiceImpl commonService;

	/** IFA接触APIサービス */
	@Autowired
	private IfaContactApiServiceImpl apiService;

	/**
	 * アクションID：A001 アクション名：初期化 Dto リクエスト：IfaContactCorrectA001RequestDto Dto
	 * レスポンス：IfaContactCorrectA001ResponseDto
	 *
	 * @param x_dtoReq リクエストパラメータ
	 * @return レスポンス
	 * @exception Exception システムエラー
	 */
	public DataList<IfaContactCorrectA001ResponseDto> initializeA001(IfaContactCorrectA001RequestDto x_dtoReq)
			throws Exception {

		if (LOGGER.isDebugEnabled())
			LOGGER.debug("IfaContactCorrectServiceImpl.initializeA001");

		// 戻り値の初期化
		IfaContactCorrectA001ResponseDto p_resDto = new IfaContactCorrectA001ResponseDto();

		// 顧客共通情報の取得
		CustomerCommon p_cc = IfaCommonUtil.getCustomerCommon();

		/* 1.利用者の口座に対する権限チェックを行う */
		IfaContactInputUtil.ErrorResponseDto p_errDto = g_util.callFct001(p_cc);
		if (p_errDto.isErrorFlg()) {
			return IfaCommonUtil.createDataList(Arrays.asList(p_resDto), ErrorLevel.FATAL, p_errDto.getErrorMessageId(),
					p_errDto.getErrorMessage());
		}

		/* 2.問合せ・回答内容情報を取得する */
		if (StringUtils.isNotEmpty(x_dtoReq.getIfaToiawaseNo())) {
			// SQL003.問合せ情報取得を実行
			IfaContactCorrectSql003ResponseModel p_sql003Res = this.execSql003(x_dtoReq.getIfaToiawaseNo());
			// 取得したプロパティをレスポンスDTOにコピー
			BeanUtils.copyProperties(p_sql003Res, p_resDto);
			// 個別セット：回答内容をレスポンスDTOに設定
			p_resDto.setNaiyou(p_sql003Res.getToiawaseNaiyou());
		}
		if (StringUtils.isNotEmpty(x_dtoReq.getTourokuGroup())) {
			// SQL005.IFA回答テーブルへの取得を実行
			DataList<IfaContactCorrectSql005ResponseModel> p_sql005Res = this.execSql005(x_dtoReq.getIfaToiawaseNo(),
					x_dtoReq.getTourokuGroup());
			// 取得したプロパティをレスポンスDTOにコピー
			if (p_sql005Res != null) {
				List<IfaContactAnswerListDto> answerList = new ArrayList<IfaContactAnswerListDto>();
				for (IfaContactCorrectSql005ResponseModel sql005 : p_sql005Res.getDataList()) {
					IfaContactAnswerListDto answer = new IfaContactAnswerListDto();
					BeanUtils.copyProperties(sql005, answer);
					answerList.add(answer);
				}
				// 個別セット：回答内容をレスポンスDTOに設定
				p_resDto.setAnswerList(answerList);
			}
			p_resDto.setTourokuGroup(x_dtoReq.getTourokuGroup());
		}
		// レスポンスを返却する
		return IfaCommonUtil.createDataList(Arrays.asList(p_resDto), ErrorLevel.SUCCESS, StringUtils.EMPTY,
				StringUtils.EMPTY);
	}

	/**
	 * アクションID:A003 アクション名:更新 リクエスト:IfaContactCorrectA003RequestDto
	 * レスポンス:IfaContactCorrectA003ResponseDto
	 *
	 * @param x_dtoReq リクエストパラメータ
	 * @return レスポンス
	 * @exception Exception システムエラー
	 */
	public DataList<IfaContactCorrectA003ResponseDto> updateA003(IfaContactCorrectA003RequestDto x_dtoReq)
			throws Exception {

		if (LOGGER.isDebugEnabled())
			LOGGER.debug("IfaContactCorrectServiceImpl.updateA003");

		// 戻り値の初期化
		DataList<IfaContactCorrectA003ResponseDto> dtoRes = new DataList<IfaContactCorrectA003ResponseDto>();
		IfaContactCorrectA003ResponseDto p_resDto = new IfaContactCorrectA003ResponseDto();
		BeanUtils.copyProperties(x_dtoReq, p_resDto);

		// 顧客共通情報の取得
		CustomerCommon p_cc = IfaCommonUtil.getCustomerCommon();

		/* 1.利用者の口座に対する権限チェックを行う */
		IfaContactInputUtil.ErrorResponseDto p_errDto = g_util.callFct001(p_cc);
		if (p_errDto.isErrorFlg()) {
			return IfaCommonUtil.createDataList(Arrays.asList(p_resDto), ErrorLevel.FATAL, p_errDto.getErrorMessageId(),
					p_errDto.getErrorMessage());
		}
		/* 2.ユーザ共通情報.CCSログイン用IDのチェックを行う。 */
		p_errDto = g_util.checkHasCcsId();
		if (p_errDto.isErrorFlg()) {
			return IfaCommonUtil.createDataList(Arrays.asList(p_resDto), ErrorLevel.FATAL, p_errDto.getErrorMessageId(),
					p_errDto.getErrorMessage());
		}
		/* 3.システム日時を取得 SQL007 */
		String p_sysDt = execSql007();
		/* 4.入力内容の分割処理を実施する。 */
		// 内容を782byte（半角1byte、全角2byteとして扱う）毎に分割し、内容リストを作成する
		List<String> naiyouList = new ArrayList<String>();
		naiyouList = g_util.splitStringByByteLength(x_dtoReq.getNaiyou(), 782);
		String naiyou0 = null;
		/* 5.登録グループにより、6～9の処理を繰り返し実施する。 */
		// 実行継続フラグ（true:処理継続, false:エラー発生）
		boolean p_execRtn = true;
		// 操作タイプ（処理ID）
		String operationType = null;
		// SQL003.問合せ情報取得を実行
		IfaContactCorrectSql003ResponseModel p_sql003Res = this.execSql003(x_dtoReq.getIfaToiawaseNo());
		if (p_sql003Res == null) {
			return IfaCommonUtil.createDataList(Arrays.asList(p_resDto), ErrorLevel.FATAL,
					IfaContactInputUtil.Msg.ERRORS_CMN_PREQUESTIONEXECUTION_FAILED.key,
					IfaCommonUtil.getMessage(IfaContactInputUtil.Msg.ERRORS_CMN_PREQUESTIONEXECUTION_FAILED.key));
		}
		if (StringUtils.isNotEmpty(p_sql003Res.getToiawaseNo())) {
			x_dtoReq.setToiawaseNo(p_sql003Res.getToiawaseNo());
		}
		// SQL005.IFA回答テーブルへの取得を実行
		// SQL005.IFA回答テーブルから既存の回答情報を取得
		DataList<IfaContactCorrectSql005ResponseModel> p_sql005Res = this.execSql005(x_dtoReq.getIfaToiawaseNo(),
				x_dtoReq.getTourokuGroup());

		// 内容リストのサイズ（nullの場合は0）
		int naiyouListSize = naiyouList != null ? naiyouList.size() : 0;
		// 既存回答リストのサイズ
		int answerListSize = p_sql005Res != null ? p_sql005Res.getDataList().size() : 0;
		// 問合せ内容（リストの最初の要素）
		naiyou0 = naiyouList.size() > 0 ? naiyouList.get(0) : "";

		// 修正履歴テーブルに登録するレコード数
		int historySize = 0;

		// エラー結果格納用
		DataList<IfaContactCorrectA003ResponseDto> errorResult = null;

		// 処理ループサイズの計算（登録グループ0の場合は特殊計算）
		int listSize = Math.max(Math.max(naiyouListSize - 1, 1), answerListSize);
		int size = StringUtils.equals(x_dtoReq.getTourokuGroup(), "0") ? listSize
				: Math.max(naiyouListSize, answerListSize);

		// 登録グループ0判定フラグ
		boolean isGroupZero = StringUtils.equals(x_dtoReq.getTourokuGroup(), "0");
		// 対象日時（回答登録時に使用）
		String targetDate = null;
		// FastHelp連携用リスト
		List<FasthelpCcsCallsImportIn> fasthelpList = new ArrayList<FasthelpCcsCallsImportIn>();

		for (int i = 0; i < size; i++) {
			// 条件に応じてSQL呼び出しフラグを設定
			boolean callSql004 = (isGroupZero && i == 0);
			boolean callSql006 = false;
			boolean callSql009 = false;

			// インデックスと参照データの決定
			int contentIdx = isGroupZero ? i + 1 : i;
			String content = (contentIdx < naiyouListSize && StringUtils.isNotEmpty(naiyouList.get(contentIdx)))
					? naiyouList.get(contentIdx)
					: "-";

			// 問合更新(Sql004)の判定
			if (callSql004)
				operationType = ProcessId.Q_UPD.getId();

			// 回答更新(Sql006) または 回答登録(Sql009) の判定
			if (i < answerListSize) {
				operationType = ProcessId.A_UPD.getId();
				String ifaKaitouNo = p_sql005Res.getDataList().get(i).getIfaKaitouNo();
				if (!isGroupZero)
					x_dtoReq.setIfaKaitouNo(ifaKaitouNo);
				callSql006 = true;
			} else if (i >= answerListSize && contentIdx < naiyouListSize
					&& StringUtils.isNotEmpty(naiyouList.get(contentIdx))) {
				operationType = ProcessId.Q_UPD_A_INS.getId();
				String baseDate = isGroupZero ? p_sql003Res.getToiawaseNichiji()
						: p_sql005Res.getDataList().get(0).getTourokuNichiji();
				targetDate = g_util.addOneSecond(baseDate, contentIdx);
				callSql009 = true;
			}

			// トランザクション処理
			try {
				CcsInAndOut ccsInAndOut = transactionService.processInTransactionForCorrect(x_dtoReq, p_sql003Res,
						i < answerListSize ? p_sql005Res.getDataList().get(i)
								: new IfaContactCorrectSql005ResponseModel(),
						naiyou0, content, operationType, targetDate, p_sysDt, callSql004, callSql006, callSql009,
						x_dtoReq.getTourokuGroup(), i);

				// IFA問合せ、回答情報をFastHelpに連携する API002
				if (callSql004 && !StringUtils.equals(operationType, ProcessId.Q_UPD.getId())) {
					FasthelpCcsCallsImportIn fasthelp_Q_UPD = new FasthelpCcsCallsImportIn();
					fasthelp_Q_UPD = commonService.callFastHelpApiCorrect(ccsInAndOut, ProcessId.Q_UPD.getId());
					fasthelpList.add(fasthelp_Q_UPD);
				}

				FasthelpCcsCallsImportIn fasthelp = new FasthelpCcsCallsImportIn();
				fasthelp = commonService.callFastHelpApiCorrect(ccsInAndOut, operationType);
				// 処理成功時は履歴カウントを増加し、FastHelp連携リストに追加
				historySize++;
				fasthelpList.add(fasthelp);
				if (StringUtils.equals(operationType, ProcessId.Q_UPD_A_INS.getId())) {
					/* 9.IFA回答テーブルに回答NOを更新する */
					if ((errorResult = getDtoRes(this.execSql010(x_dtoReq, ccsInAndOut.getCcsOut().getRtn_kaitou_no()),
							"execSql010", p_execRtn, i)) != null) {
						p_execRtn = false;
						dtoRes = errorResult;
					}
				}
			} catch (IfaContactApiException e) {
				// トランザクション失敗
				LOGGER.warn("Transaction failed in loop iteration {}", i);
				String errorClass = e.getMessageKey();
				errorResult = getDtoRes(false, errorClass, p_execRtn, i);
				if (errorResult != null) {
					dtoRes = errorResult;
					p_execRtn = false;
				}
				// 最初のレードで失敗した場合は即座に返却、それ以外は中断
				if (i == 0) {
					return dtoRes;
				}
				break;
			}

		}

		if (fasthelpList.size() > 0) {
			apiService.callFastHelpApi(fasthelpList, p_sysDt, ClassName.CORRECT.key);
		}

		/* 10.IFA接触履歴修正履歴テーブルに履歴情報を登録する */
		if (historySize > 0) {
			if ((errorResult = getDtoRes(this.execSql011(x_dtoReq, p_sql003Res,
					p_sql005Res != null ? p_sql005Res.getDataList().get(0) : null, p_sysDt, naiyouList, historySize),
					ClassName.EXECSQL011.key, p_execRtn, 0)) != null) {
				p_execRtn = false;
				dtoRes = errorResult;
			}
		}

		if (p_execRtn) {
			dtoRes = IfaCommonUtil.createDataList(Arrays.asList(p_resDto), ErrorLevel.INFO, INFO_ENDCOMPLETED,
					IfaCommonUtil.getMessage(INFO_ENDCOMPLETED, new String[] { CONTACT_CORRECT_MESSAGE }));
		}
		// レスポンスを返却する
		return dtoRes;
	}

	/**
	 * SQL003.問合せ情報取得
	 * 
	 * @param x_ifaToiawaseNo IFA問合せNO
	 * @return IfaContactCorrectSql003ResponseModel SQL結果
	 */
	private IfaContactCorrectSql003ResponseModel execSql003(String x_ifaToiawaseNo) {
		IfaContactCorrectSql003RequestModel p_sql003Req = new IfaContactCorrectSql003RequestModel();
		/* 以下は抽出条件のセット */
		// IFA問合せNO = リクエスト.IFA問合せNO
		p_sql003Req.setIfaToiawaseNo(x_ifaToiawaseNo);
		/* SQL実行 */
		try {
			DataList<IfaContactCorrectSql003ResponseModel> p_sql003Res = g_dao
					.selectIfaContactCorrectSql003(p_sql003Req);
			if (p_sql003Res != null && 0 < p_sql003Res.size()) {
				return p_sql003Res.get(0);
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	/**
	 * SQL005.IFA回答テーブルへの取得
	 * 
	 * @param x_ifaToiawaseNo IFA問合せNO
	 * @param tourokuGroup    登録グループ
	 * @return IfaContactCorrectSql005ResponseModelのリスト
	 */
	private DataList<IfaContactCorrectSql005ResponseModel> execSql005(String x_ifaToiawaseNo, String tourokuGroup) {
		if (StringUtil.isNullOrEmpty(tourokuGroup)) {
			return null;
		}
		IfaContactCorrectSql005RequestModel p_sql005Req = new IfaContactCorrectSql005RequestModel();
		/* 以下は抽出条件のセット */
		// IFA問合せNO = リクエスト.IFA問合せNO
		p_sql005Req.setIfaToiawaseNo(x_ifaToiawaseNo);
		// 登録グループ = リクエスト.登録グループ
		p_sql005Req.setTourokuGroup(tourokuGroup);
		/* SQL実行 */
		try {
			DataList<IfaContactCorrectSql005ResponseModel> p_sql005Res = g_dao
					.selectIfaContactCorrectSql005(p_sql005Req);
			if (p_sql005Res != null && !CollectionUtils.isEmpty(p_sql005Res.getDataList())) {
				return p_sql005Res;
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	/**
	 * SQL007.システム日時の取得
	 * 
	 * @return String (yyyyMMddHHmmss)
	 * @throws Exception
	 */
	private String execSql007() throws Exception {
		return DateUtil.format(g_sysdate.getSystemDate(), DateUtil.NOT_SEPARATED_YYYYMMDD_HHMMSS);
	}

	/**
	 * SQL010.回答NO更新
	 * 
	 * @param x_dtoReq    IfaContactCorrectA003RequestDto リクエストDTO
	 * @param a_kaitou_no API001.回答NO
	 * @return 処理結果 (true:成功, false:失敗)
	 */
	private boolean execSql010(IfaContactCorrectA003RequestDto x_dtoReq, String a_kaitou_no) {
		IfaContactCorrectSql010RequestModel p_sql010Req = new IfaContactCorrectSql010RequestModel();
		// IFA問合せNO = リクエスト.IFA問合せNO
		p_sql010Req.setIfaToiawaseNo(x_dtoReq.getIfaToiawaseNo());
		// IFA回答NO = リクエスト.IFA回答NO
		p_sql010Req.setIfaKaitouNo(x_dtoReq.getIfaKaitouNo());
		// 回答NO API001.回答NO
		p_sql010Req.setKaitouNo(a_kaitou_no);
		/* SQL実行 */
		try {
			if (g_dao.updateIfaContactCorrectSql010(p_sql010Req) > 0) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	/**
	 * SQL011.IFA接触履歴修正履歴テーブル登録
	 * 
	 * @param x_dtoReq    IfaContactCorrectA003RequestDto リクエストDTO
	 * @param x_sql003Res IfaContactCorrectSql003ResponseModel SQL003結果
	 * @param x_sql005Res IfaContactCorrectSql005ResponseModel SQL005結果
	 * @param x_sysDt     システム日時
	 * @param naiyouList  リクエスト.内容
	 * @return 処理結果 (true:成功, false:失敗)
	 */
	private boolean execSql011(IfaContactCorrectA003RequestDto x_dtoReq,
			IfaContactCorrectSql003ResponseModel x_sql003Res, IfaContactCorrectSql005ResponseModel x_sql005Res,
			String x_sysDt, List<String> naiyouList, int historySize) {
		/* 以下は挿入項目のセット */
		IfaContactCorrectSql011RequestModel p_sql011Req = new IfaContactCorrectSql011RequestModel();
		// IFA問合せNO A003.IFA問合せNO
		p_sql011Req.setIfaToiawaseNo(x_dtoReq.getIfaToiawaseNo());
		// 問合せNO A003.問合せNO
		p_sql011Req.setToiawaseNo(x_dtoReq.getToiawaseNo());
		// 入力日時
		// 登録グループ=0の場合、リクエスト.問合せ情報.入力日時 それ以外の場合、リクエスト.回答情報リスト[0].入力日時
		p_sql011Req
				.setNyuuryokuDate(StringUtils.equals(x_dtoReq.getTourokuGroup(), "0") ? x_sql003Res.getTourokuNichiji()
						: (x_sql005Res != null ? x_sql005Res.getTourokuNichiji() : StringUtil.EMPTY_STRING));
		// 入力者
		// 登録グループ=0の場合、リクエスト.問合せ情報.ユーザID それ以外の場合、リクエスト.回答情報リスト[0].ユーザID
		p_sql011Req.setNyuuryokusyaId(StringUtils.equals(x_dtoReq.getTourokuGroup(), "0") ? x_sql003Res.getUserId()
				: (x_sql005Res != null ? x_sql005Res.getUserId() : StringUtil.EMPTY_STRING));
		// 入力者名
		// 登録グループ=0の場合、リクエスト.問合せ情報.ユーザ名 それ以外の場合、リクエスト.回答情報リスト[0].ユーザ名
		p_sql011Req.setNyuuryokusyaName(StringUtils.equals(x_dtoReq.getTourokuGroup(), "0") ? x_sql003Res.getUserName()
				: (x_sql005Res != null ? x_sql005Res.getUserName() : StringUtil.EMPTY_STRING));
		// 登録グループ A003.登録グループ
		p_sql011Req.setTourokuGroup(x_dtoReq.getTourokuGroup());
		// 修正日時 システム日時
		p_sql011Req.setSyuuseiDate(x_sysDt);
		// 修正者 ユーザ共通情報.ユーザーID
		p_sql011Req.setSyuuseisyaId(IfaCommonUtil.getUserAccount().getUserId());
		// 修正者名 ユーザ共通情報.ユーザー名
		p_sql011Req.setSyuuseisyaName(IfaCommonUtil.getUserAccount().getUserNm());
		int naiyouListSize = naiyouList.size();
		int size = StringUtils.equals(x_dtoReq.getTourokuGroup(), "0") && naiyouListSize > 1 ? historySize + 1
				: historySize;

		// 内容1~5を設定（最大size回まで）
		for (int i = 0; i < Math.min(size, naiyouListSize); i++) {
			String value = naiyouList.get(i);
			switch (i) {
			case 0:
				p_sql011Req.setNaiyou1(value);
				break;
			case 1:
				p_sql011Req.setNaiyou2(value);
				break;
			case 2:
				p_sql011Req.setNaiyou3(value);
				break;
			case 3:
				p_sql011Req.setNaiyou4(value);
				break;
			case 4:
				p_sql011Req.setNaiyou5(value);
				break;
			}
		}
		// 顧客ID 顧客共通情報.顧客コード
		p_sql011Req.setKokyakuId(IfaCommonUtil.getCustomerCommon().getCustomerCode());
		// 部店コード 顧客共通情報.部店コード
		p_sql011Req.setButenCode(IfaCommonUtil.getCustomerCommon().getButenCode());
		// 口座番号 顧客共通情報.口座番号
		p_sql011Req.setAccountNumber(IfaCommonUtil.getCustomerCommon().getAccountNumber());
		// 仲介業者コード 顧客共通情報.仲介業者コード
		p_sql011Req.setBrokerCode(IfaCommonUtil.getCustomerCommon().getBrokerCode());
		// 仲介業者営業員コード 顧客共通情報.仲介業者営業員コード
		p_sql011Req.setIntermediaryEmpCd(IfaCommonUtil.getCustomerCommon().getBrokerChargeCode());
		// 顧客名_姓名(漢字) 顧客共通情報.顧客名（漢字）
		p_sql011Req.setNameKanji(IfaCommonUtil.getCustomerCommon().getCustomerNameKanji());
		// 顧客名_姓名(カナ) 顧客共通情報.顧客名（カナ）
		p_sql011Req.setNameKana(IfaCommonUtil.getCustomerCommon().getCustomerNameKana());
		try {
			if (g_dao.insertIfaContactCorrectSql011(p_sql011Req) > 0) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	/**
	 * レスポンスDTOを取得する
	 *
	 * @param isSuccess  実行結果（true:成功, false:失敗）
	 * @param errorClass エラークラス名
	 * @param p_execRtn  実行継続フラグ
	 * @param loopCount  ループカウント
	 * @return IfaContactCorrectA003ResponseDtoのリスト
	 */
	public DataList<IfaContactCorrectA003ResponseDto> getDtoRes(boolean isSuccess, String errorClass, boolean p_execRtn,
			int loopCount) {

		// 成功時または既にエラーが発生している場合はnullを返却
		if (isSuccess || !p_execRtn)
			return null;

		// エラークラスに応じて適切なエラーレスポンスを返却
		switch (errorClass) {
		// SQL実行エラーまたはCCS API呼び出しエラーの場合
		case "execSql004":
		case "execSql006":
		case "execSql009":
		case "callCCSApi":
			return createErrorResponse(errorClass,
					// ループ1回目以降は警告、初回はエラー
					loopCount > 0 ? IfaContactInputUtil.Msg.WARNINGS_CMN_QUESTIONREGISTRATIONEXECUTION_FAILED.key
							: IfaContactInputUtil.Msg.ERRORS_CMN_PREQUESTIONEXECUTION_FAILED.key);
		// 履歴登録エラーの場合
		case "execSql010":
		case "execSql011":
			return createErrorResponse(errorClass,
					IfaContactInputUtil.Msg.WARNINGS_CMN_QUESTIONEXECUTION_COMPLETED.key);
		default:
			return null;
		}
	}

	/**
	 * エラーレスポンスを作成する
	 *
	 * @param errorClass エラークラス名
	 * @param messageKey メッセージキー
	 * @return IfaContactCorrectA003ResponseDtoのリスト
	 */
	private DataList<IfaContactCorrectA003ResponseDto> createErrorResponse(String errorClass, String messageKey) {
		LOGGER.error("IfaContactCorrectServiceImpl.{} error.", errorClass);
		return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL, messageKey,
				IfaCommonUtil.getMessage(messageKey));
	}
}
