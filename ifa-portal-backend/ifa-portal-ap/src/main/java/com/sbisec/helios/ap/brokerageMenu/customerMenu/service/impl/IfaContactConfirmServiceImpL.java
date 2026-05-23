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
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.DateUtil;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaContactConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql008ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql010RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql010ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql011RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactConfirmA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactConfirmA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaContactConfirmService;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.IfaContactApiException;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.IfaContactInputUtil;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.IfaContactInputUtil.ClassName;
import com.sbisec.helios.ap.common.dao.SystemDateDao;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.fasthelp.enums.ProcessId;
import com.sbisec.helios.ap.fasthelp.service.dto.ccs.CcsFastHelpInfoInsertDoOut;
import com.sbisec.helios.ap.fasthelp.service.dto.ccs.CcsInAndOut;
import com.sbisec.helios.ap.fasthelp.service.dto.fasthelp.FasthelpCcsCallsImportIn;

/**
 * IFA接触履歴入力確認サービス実装クラス 画面ID:SUB0202_0106-04 画面名:接触履歴入力確認
 * 
 * @author 大連 王永宝
 * @date 2025-04-24
 */
@Component(value = "cmpIfaContactConfirmService")
public class IfaContactConfirmServiceImpL implements IfaContactConfirmService {

	/** ロガー */
	private static final Logger LOGGER = LoggerFactory.getLogger(IfaContactConfirmServiceImpL.class);

	/** IFA接触確認DAO */
	@Autowired
	private IfaContactConfirmDao g_dao;

	/** IFA接触入力ユーティリティ */
	@Autowired
	private IfaContactInputUtil g_util;

	/** システム日付DAO */
	@Autowired
	private SystemDateDao g_sysdate;

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
	 * アクションID:A002 アクション名:登録 リクエスト:IfaContactConfirmA002RequestDto
	 * レスポンス:IfaContactConfirmA002ResponseDto
	 *
	 * @param x_dtoReq リクエストパラメータ
	 * @return レスポンス
	 * @exception Exception システムエラー
	 */
	public DataList<IfaContactConfirmA002ResponseDto> insertA002(IfaContactConfirmA002RequestDto x_dtoReq)
			throws Exception {

		if (LOGGER.isDebugEnabled())
			LOGGER.debug("IfaContactConfirmServiceImpl.insertA002");

		// 訪問日と次回訪問予定日から「/」を除去する（yyyy/MM/dd → yyyyMMdd形式に変換）
		x_dtoReq.setHoumonbi(!StringUtil.isNullOrEmpty(x_dtoReq.getHoumonbi()) ? x_dtoReq.getHoumonbi().replace("/", "")
				: x_dtoReq.getHoumonbi());
		x_dtoReq.setNextHoumonbi(
				!StringUtil.isNullOrEmpty(x_dtoReq.getNextHoumonbi()) ? x_dtoReq.getNextHoumonbi().replace("/", "")
						: x_dtoReq.getNextHoumonbi());
		// レスポンスデータの初期化
		DataList<IfaContactConfirmA002ResponseDto> dtoRes = new DataList<IfaContactConfirmA002ResponseDto>();
		IfaContactConfirmA002ResponseDto p_resDto = new IfaContactConfirmA002ResponseDto();
		// リクエストDTOのプロパティをレスポンスDTOにコピーする（入力値をそのまま返却用に設定）
		BeanUtils.copyProperties(x_dtoReq, p_resDto);
		// セッションから顧客共通情報を取得する
		CustomerCommon p_cc = IfaCommonUtil.getCustomerCommon();
		// 処理区分（1:問合せ入力、2:管理項目修正、3:回答入力）に応じた列挙型を取得する
		IfaContactInputUtil.OperationType p_operationType = IfaContactInputUtil.OperationType
				.valueOfKey(x_dtoReq.getOperationType());
		// API処理区分（CCS連携時に使用する処理ID）
		String p_procId = StringUtil.EMPTY_STRING;

		// API001.問合せNOをリクエストから取得（初期値設定）
		String toiawaseNo = x_dtoReq.getToiawaseNo();
		/* 1.利用者の口座に対する権限チェックを行う */
		IfaContactInputUtil.ErrorResponseDto p_errDto = g_util.callFct001(p_cc);
		if (p_errDto.isErrorFlg()) {
			dtoRes = IfaCommonUtil.createDataList(Arrays.asList(p_resDto), ErrorLevel.FATAL,
					p_errDto.getErrorMessageId(), p_errDto.getErrorMessage());
			return dtoRes;
		}
		/* 2.ユーザ共通情報.CCSログイン用IDのチェックを行う。 */
		p_errDto = g_util.checkHasCcsId();
		if (p_errDto.isErrorFlg()) {
			dtoRes = IfaCommonUtil.createDataList(Arrays.asList(p_resDto), ErrorLevel.FATAL,
					p_errDto.getErrorMessageId(), p_errDto.getErrorMessage());
			return dtoRes;
		}
		/* 3.システム日時を取得 SQL007 */
		String p_sysDt = this.execSql007();
		/* 4.入力内容の分割処理を行う（CCSの文字数制限対応） */
		// 問合せ内容を391文字（782バイト）毎に分割し、リストを作成する
		List<String> toiawaseNaiyouList = new ArrayList<>();
		String toiawaseNaiyou0 = "";
		if (StringUtils.isNotEmpty(x_dtoReq.getToiawaseNaiyou())) {
			// 全角391文字までの制限に対応するため、バイト単位で分割
			toiawaseNaiyouList = g_util.splitStringByByteLength(x_dtoReq.getToiawaseNaiyou(), 782);
			toiawaseNaiyou0 = toiawaseNaiyouList.get(0);
		}
		// 処理区分が'3'(回答入力)の場合：追加入力（回答内容）を同様に分割する
		List<String> kaitouNaiyouList = new ArrayList<>();
		if (StringUtils.isNotEmpty(x_dtoReq.getKaitouNaiyou())
				&& StringUtils.equals("3", x_dtoReq.getOperationType())) {
			kaitouNaiyouList = g_util.splitStringByByteLength(x_dtoReq.getKaitouNaiyou(), 782);
		}
		/* 5.登録グループの設定（履歴管理用のグループ番号） */
		String tourokuGroup = null;
		if (StringUtils.equals(IfaContactInputUtil.OperationType.QUESTION_INPUT.key, x_dtoReq.getOperationType())) {
			// 新規問合せの場合はグループ'0'を設定
			tourokuGroup = "0";
		} else if (StringUtils.equals(IfaContactInputUtil.OperationType.ANSWER_INPUT.key,
				x_dtoReq.getOperationType())) {
			// 回答入力の場合は既存の問合せから登録グループを取得
			tourokuGroup = this.execSql010(x_dtoReq);
		}
		/* 6.処理区分に応じて7～10の処理を繰り返し実施する（分割された内容を順次登録） */
		// SQL008実行結果を保持する変数（問合せ情報取得）
		IfaContactConfirmSql008ResponseModel p_sql008Res = null;
		// 処理継続フラグ（true:成功、false:失敗）
		boolean p_execRtn = true;
		// 修正履歴テーブルに登録するレコード数
		int historySize = 0;

		// エラー時のレスポンスを保持
		DataList<IfaContactConfirmA002ResponseDto> errorResult = null;

		// ループ回数を計算（分割された内容の最大数に基づく）
		int size = p_operationType == IfaContactInputUtil.OperationType.QUESTION_INPUT
				? Math.max(toiawaseNaiyouList.size() - 1, 1)
				: kaitouNaiyouList.size();
		String content = "";
		// 問合せ入力以外の場合は既存の問合せ情報を取得する
		if (!StringUtils.equals(IfaContactInputUtil.OperationType.QUESTION_INPUT.key, x_dtoReq.getOperationType())) {
			p_sql008Res = this.execSql008(x_dtoReq);
			if (p_sql008Res == null) {
				LOGGER.error("IfaContactConfirmServiceImpl.execSql008 data not found.");
			}
		}
		// 管理項目修正の場合は1回のみの処理とする
		if (StringUtils.equals(IfaContactInputUtil.OperationType.QUESTION_CORRECT.key, x_dtoReq.getOperationType())) {
			size = 1;
		}

		// FastHelp連携用のデータリストを初期化
		List<FasthelpCcsCallsImportIn> fasthelpList = new ArrayList<FasthelpCcsCallsImportIn>();

		// 分割された内容を順次処理するループ
		for (int i = 0; i < size; i++) {
			// 【処理区分：問合せ入力】
			if (p_operationType == IfaContactInputUtil.OperationType.QUESTION_INPUT) {
				if (i == 0) {
					// 最初のレコードは問合せ入力としてAPI処理区分を設定
					p_procId = ProcessId.Q_INS.getId();
				}

				// 内容が複数に分割されている場合、後続レコードは回答入力として処理
				if (toiawaseNaiyouList.size() > 1) {
					content = toiawaseNaiyouList.get(i + 1);
					// 1件目は回答入力、2件目以降は問合せ変更+回答入力
					p_procId = i == 0 ? ProcessId.A_INS.getId() : ProcessId.Q_UPD_A_INS.getId();
				}
			}

			// 【処理区分：管理項目修正】
			if (p_operationType == IfaContactInputUtil.OperationType.QUESTION_CORRECT) {
				// 問合せ修正としてAPI処理区分を設定
				p_procId = ProcessId.Q_UPD.getId();
			}

			// 【処理区分：回答入力】
			if (p_operationType == IfaContactInputUtil.OperationType.ANSWER_INPUT) {
				content = kaitouNaiyouList.get(i);
				// 問合せ変更+回答入力としてAPI処理区分を設定
				p_procId = ProcessId.Q_UPD_A_INS.getId();
			}

			// 各SQLの実行要否を判定するフラグを設定
			// SQL002:問合せテーブル登録（問合せ入力かつ最初のレコード）
			boolean callSql002 = (p_operationType == IfaContactInputUtil.OperationType.QUESTION_INPUT && i == 0);
			// SQL003:問合せテーブル更新（管理項目修正、または回答入力の最初のレコード）
			boolean callSql003 = ((p_operationType == IfaContactInputUtil.OperationType.QUESTION_CORRECT)
					|| (p_operationType == IfaContactInputUtil.OperationType.ANSWER_INPUT && i == 0));
			// SQL004:回答テーブル登録（問合せ入力で内容が複数、または回答入力）
			boolean callSql004 = ((p_operationType == IfaContactInputUtil.OperationType.QUESTION_INPUT
					&& toiawaseNaiyouList.size() > 1)
					|| (p_operationType == IfaContactInputUtil.OperationType.ANSWER_INPUT));

			try {
				// トランザクション処理を実行（CCS API呼び出し、SQL実行）
				CcsInAndOut ccsInAndOut = transactionService.processInTransaction(x_dtoReq, p_sql008Res, p_procId,
						toiawaseNaiyou0, content, p_sysDt, toiawaseNo, callSql002, callSql003, callSql004, tourokuGroup,
						i);
				/* 9.IFA問合せ、回答情報をFastHelpに連携する API002 */
				FasthelpCcsCallsImportIn fasthelp = commonService.callFastHelpApiConfirm(ccsInAndOut, p_procId);

				if (StringUtils.equals(p_procId, ProcessId.Q_UPD_A_INS.getId()) && i == 0) {
					FasthelpCcsCallsImportIn fasthelp_add = commonService.callFastHelpApiConfirm(ccsInAndOut,
							ProcessId.Q_UPD.getId());
					fasthelpList.add(fasthelp_add);
				}

				// 処理成功時は履歴カウントを増加し、FastHelp連携リストに追加
				historySize++;
				fasthelpList.add(fasthelp);

				/* 10.IFA問合せテーブル、IFA回答テーブルにCCSから返却された問合せNO、回答NOを更新する */
				// SQL005:IFA問合せテーブルにCCS問合せNOを更新（新規問合せの場合）
				if (callSql002) {
					if ((errorResult = getDtoRes(
							this.execSql005(x_dtoReq, ccsInAndOut.getCcsOut().getRtn_toiawase_no()),
							ClassName.EXECSQL005.key, p_execRtn, i)) != null) {
						p_execRtn = false;
						dtoRes = errorResult;
					} else {
						// 更新成功時は取得した問合せNOを保持し、問合せ情報を再取得
						toiawaseNo = ccsInAndOut.getCcsOut().getRtn_toiawase_no();
						p_sql008Res = this.execSql008(x_dtoReq);
					}
				}

				// SQL006:IFA回答テーブルにCCS回答NOを更新（回答入力の場合）
				if (callSql004) {
					if ((errorResult = getDtoRes(this.execSql006(x_dtoReq, ccsInAndOut.getCcsOut()),
							ClassName.EXECSQL006.key, p_execRtn, i)) != null) {
						p_execRtn = false;
						dtoRes = errorResult;
					}
				}
			} catch (IfaContactApiException e) {
				// CCS APIまたはSQL処理でエラー発生時の処理
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

		// FastHelp連携処理（登録されたデータをFastHelpに一括連携）
		if (fasthelpList.size() > 0) {
			apiService.callFastHelpApi(fasthelpList, p_sysDt, ClassName.CONFIRM.key);
		}

		/* 11.処理区分が'2'(管理項目修正)以外の場合：修正履歴テーブルに登録 */
		// 問合せ入力または回答入力の場合、履歴情報を保存する
		if (!StringUtils.equals(p_operationType.key, IfaContactInputUtil.OperationType.QUESTION_CORRECT.key)
				&& historySize > 0) {
			if ((errorResult = getDtoRes(this.execSql011(x_dtoReq, toiawaseNo, p_sysDt, tourokuGroup,
					toiawaseNaiyouList, kaitouNaiyouList, historySize), ClassName.EXECSQL011.key, p_execRtn,
					0)) != null) {
				p_execRtn = false;
				dtoRes = errorResult;
			}
		}
		/* 12.問合せ入力完了画面を表示する（正常終了レスポンスを返却） */
		// 全ての処理が成功した場合、正常レスポンスを設定
		if (p_execRtn) {
			dtoRes = IfaCommonUtil.createDataList(Arrays.asList(p_resDto), ErrorLevel.SUCCESS,
					ErrorLevel.SUCCESS.name(), StringUtils.EMPTY);
		}
		return dtoRes;
	}

	/**
	 * SQL005.IFA問合せテーブルの問合せNO更新 CCS APIから返却された問合せNOをIFA問合せテーブルに設定する
	 * 
	 * @param x_dtoReq              IfaContactConfirmA002RequestDto リクエストDTO
	 * @param x_api001RtnToiawaseNo API001.戻り値の問合せNO
	 * @return 処理結果 (true:成功, false:失敗)
	 */
	private boolean execSql005(IfaContactConfirmA002RequestDto x_dtoReq, String x_api001RtnToiawaseNo) {
		IfaContactConfirmSql005RequestModel p_sql005Req = new IfaContactConfirmSql005RequestModel();
		/* 以下は抽出条件のセット */
		// IFA問合せNO = リクエスト.IFA問合せNO（更新対象を特定）
		p_sql005Req.setIfaToiawaseNo(x_dtoReq.getIfaToiawaseNo());
		/* 以下は更新項目のセット */
		// 問合せNOにCCSから返却された値を設定
		p_sql005Req.setToiawaseNo(x_api001RtnToiawaseNo);
		/* SQL実行 */
		try {
			if (g_dao.updateIfaContactConfirmSql005(p_sql005Req) > 0) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	/**
	 * SQL006.IFA回答テーブルの問合せNO・回答NO更新 CCS APIから返却された問合せNOと回答NOをIFA回答テーブルに設定する
	 * 
	 * @param x_dtoReq    IfaContactConfirmA002RequestDto リクエストDTO
	 * @param x_api001Out API001.戻り値（CCS問合せNO、CCS回答NOを含む）
	 * @return 処理結果 (true:成功, false:失敗)
	 */
	private boolean execSql006(IfaContactConfirmA002RequestDto x_dtoReq, CcsFastHelpInfoInsertDoOut x_api001Out) {
		IfaContactConfirmSql006RequestModel p_sql006Req = new IfaContactConfirmSql006RequestModel();
		/* 以下は抽出条件のセット */
		// IFA問合せNO = リクエスト.IFA問合せNO（更新対象を特定）
		p_sql006Req.setIfaToiawaseNo(x_dtoReq.getIfaToiawaseNo());
		// IFA回答NO = リクエスト.IFA回答NO（更新対象を特定）
		p_sql006Req.setIfaKaitouNo(x_dtoReq.getIfaKaitouNo());
		/* 以下は更新項目のセット */
		// 問合せNOにCCSから返却された値を設定
		p_sql006Req.setToiawaseNo(x_api001Out.getRtn_toiawase_no());
		// 回答NOにCCSから返却された値を設定
		p_sql006Req.setKaitouNo(x_api001Out.getRtn_kaitou_no());
		/* SQL実行 */
		try {
			if (g_dao.updateIfaContactConfirmSql006(p_sql006Req) > 0) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	/**
	 * SQL007.システム日時の取得 データベースから現在のシステム日時を取得し、指定フォーマットで返却する
	 * 
	 * @return String システム日時（yyyyMMddHHmmss形式）
	 * @throws Exception データベースアクセスエラー時に発生
	 */
	private String execSql007() throws Exception {
		return DateUtil.format(g_sysdate.getSystemDate(), DateUtil.NOT_SEPARATED_YYYYMMDD_HHMMSS);
	}

	/**
	 * SQL008.IFA問合せ情報の取得 IFA問合せテーブルから指定された問合せの詳細情報を取得する
	 * 
	 * @param x_dtoReq IfaContactConfirmA002RequestDto リクエストDTO
	 * @return IfaContactConfirmSql008ResponseModel 問合せ情報（取得失敗時はnull）
	 */
	private IfaContactConfirmSql008ResponseModel execSql008(IfaContactConfirmA002RequestDto x_dtoReq) {
		IfaContactConfirmSql008RequestModel p_sql008Req = new IfaContactConfirmSql008RequestModel();
		/* 以下は抽出条件のセット */
		// IFA問合せNO = リクエスト.IFA問合せNO（検索キー）
		p_sql008Req.setIfaToiawaseNo(x_dtoReq.getIfaToiawaseNo());
		/* SQL実行 */
		try {
			DataList<IfaContactConfirmSql008ResponseModel> p_sql008Res = g_dao
					.selectIfaContactConfirmSql008(p_sql008Req);
			if (p_sql008Res != null && 0 < p_sql008Res.size()) {
				return p_sql008Res.get(0);
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	/**
	 * SQL010.IFA問合せテーブルから登録グループを取得 既存の問合せに紐づく登録グループ番号を取得する（回答入力時に使用）
	 * 
	 * @param x_dtoReq IfaContactConfirmA002RequestDto リクエストDTO
	 * @return 登録グループ番号（取得失敗時はnull）
	 * @throws IfaContactApiException データベースアクセスエラー時に発生
	 */
	private String execSql010(IfaContactConfirmA002RequestDto x_dtoReq) throws IfaContactApiException {
		IfaContactConfirmSql010RequestModel p_sql010Req = new IfaContactConfirmSql010RequestModel();
		/* 以下は抽出条件のセット */
		// IFA問合せNO = リクエスト.IFA問合せNO（検索キー）
		p_sql010Req.setIfaToiawaseNo(x_dtoReq.getIfaToiawaseNo());
		/* SQL実行 */
		try {
			DataList<IfaContactConfirmSql010ResponseModel> p_sql010Res = g_dao
					.selectIfaContactConfirmSql010(p_sql010Req);
			if (p_sql010Res != null && 0 < p_sql010Res.size()) {
				return p_sql010Res.get(0).getTourokuGroup();
			} else {
				return null;
			}
		} catch (Exception e) {
			LOGGER.warn(
					"IfaContactConfirmServiceImpl.execSql010: Failed to retrieve registration group for IFA inquiry number: {}",
					x_dtoReq.getIfaToiawaseNo(), e);
			throw new IfaContactApiException("execSql010: Failed to retrieve registration group from database", e);
		}
	}

	/**
	 * SQL011.IFA接触履歴修正履歴テーブルに登録 問合せ・回答の入力内容を履歴として保存する
	 * 
	 * @param x_dtoReq           IfaContactConfirmA002RequestDto リクエストDTO
	 * @param toiawaseNo         API001.問合せNO（CCS採番）
	 * @param x_sysDt            システム日時
	 * @param tourokuGroup       登録グループ
	 * @param toiawaseNaiyouList 問合せ内容リスト（分割済み）
	 * @param kaitouNaiyouList   回答内容リスト（分割済み）
	 * @param historySize        履歴に保存するレコード数
	 * @return 処理結果 (true:成功, false:失敗)
	 */
	private boolean execSql011(IfaContactConfirmA002RequestDto x_dtoReq, String toiawaseNo, String x_sysDt,
			String tourokuGroup, List<String> toiawaseNaiyouList, List<String> kaitouNaiyouList, int historySize) {
		String p_userId = IfaCommonUtil.getUserAccount().getUserId();
		String p_userName = IfaCommonUtil.getUserAccount().getUserNm();
		/* 以下は挿入項目のセット */
		IfaContactConfirmSql011RequestModel p_sql011Req = new IfaContactConfirmSql011RequestModel();
		// IFA問合せNO リクエスト.IFA問合せNO
		p_sql011Req.setIfaToiawaseNo(x_dtoReq.getIfaToiawaseNo());
		// 問合せNO リクエスト.問合せNO"
		p_sql011Req.setToiawaseNo(toiawaseNo);
		// 入力日時
		p_sql011Req.setNyuuryokuDate(x_sysDt);
		// 入力者
		p_sql011Req.setNyuuryokusyaId(p_userId);
		// 入力者名
		p_sql011Req.setNyuuryokusyaName(p_userName);
		// 登録グループ
		p_sql011Req.setTourokuGroup(tourokuGroup);
		// 修正日時
		p_sql011Req.setSyuuseiDate(x_sysDt);
		// 修正者
		p_sql011Req.setSyuuseisyaId(p_userId);
		// 修正者名
		p_sql011Req.setSyuuseisyaName(p_userName);
		// 内容 登録グループ=0の場合は、A002.内容,それ以外の場合はA002.追加入力
		List<String> contentList;
		int size;

		if (StringUtils.equals(tourokuGroup, "0")) {
			// 登録グループ=0の場合
			size = toiawaseNaiyouList.size() > 1 ? historySize + 1 : historySize;
			contentList = toiawaseNaiyouList;
		} else {
			// それ以外の場合
			size = historySize;
			contentList = kaitouNaiyouList;
		}

		// ループを使用して内容フィールドを設定
		for (int i = 0; i < size; i++) {
			String value = size > i ? contentList.get(i) : StringUtil.EMPTY_STRING;
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
			default:
				break;
			}
		}
		// 顧客ID
		p_sql011Req.setKokyakuId(IfaCommonUtil.getCustomerCommon().getCustomerCode());
		p_sql011Req.setButenCode(IfaCommonUtil.getCustomerCommon().getButenCode());
		p_sql011Req.setAccountNumber(IfaCommonUtil.getCustomerCommon().getAccountNumber());
		p_sql011Req.setBrokerCode(IfaCommonUtil.getCustomerCommon().getBrokerCode());
		p_sql011Req.setIntermediaryEmpCd(IfaCommonUtil.getCustomerCommon().getBrokerChargeCode());
		p_sql011Req.setNameKanji(IfaCommonUtil.getCustomerCommon().getCustomerNameKanji());
		p_sql011Req.setNameKana(IfaCommonUtil.getCustomerCommon().getCustomerNameKana());
		/* SQL実行 */
		try {
			if (g_dao.insertIfaContactConfirmSql011(p_sql011Req) > 0) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	/**
	 * エラーレスポンスDTOを取得する SQL処理やAPI処理でエラー発生時のレスポンスを生成する
	 *
	 * @param isSuccess  実行結果（true:成功、false:失敗）
	 * @param errorClass エラー発生箇所のクラス名
	 * @param p_execRtn  実行継続フラグ（前回までの処理結果）
	 * @param loopCount  ループカウント（分割登録時のインデックス）
	 * @return エラーレスポンス（正常時はnull）
	 */
	public DataList<IfaContactConfirmA002ResponseDto> getDtoRes(boolean isSuccess, String errorClass, boolean p_execRtn,
			int loopCount) {

		if (isSuccess || !p_execRtn)
			return null;

		switch (errorClass) {
		case "execSql002":
		case "execSql003":
		case "execSql004":
		case "callCCSApi":
			return createErrorResponse(errorClass,
					loopCount > 0 ? IfaContactInputUtil.Msg.WARNINGS_CMN_QUESTIONREGISTRATIONEXECUTION_FAILED.key
							: IfaContactInputUtil.Msg.ERRORS_CMN_PREQUESTIONEXECUTION_FAILED.key);
		case "execSql005":
		case "execSql006":
		case "execSql011":
			return createErrorResponse(errorClass,
					IfaContactInputUtil.Msg.WARNINGS_CMN_QUESTIONEXECUTION_COMPLETED.key);
		default:
			return null;
		}
	}

	/**
	 * エラーレスポンスを作成する 指定されたエラー情報に基づいて標準化されたエラーレスポンスを生成する
	 *
	 * @param errorClass エラー発生箇所のクラス名
	 * @param messageKey メッセージプロパティのキー
	 * @return エラーレスポンスのデータリスト
	 */
	private DataList<IfaContactConfirmA002ResponseDto> createErrorResponse(String errorClass, String messageKey) {
		LOGGER.error("IfaContactConfirmServiceImpl.{} error.", errorClass);
		return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL, messageKey,
				IfaCommonUtil.getMessage(messageKey));
	}

}
