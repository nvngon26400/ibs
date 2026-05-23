package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.util.Cp932;
import com.sbibits.earth.util.DateUtil;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaContactConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaContactCorrectDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql008ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectSql009RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactConfirmA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactCorrectA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.IfaContactInputUtil;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.IfaContactInputUtil.ClassName;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.fasthelp.enums.Cream;
import com.sbisec.helios.ap.fasthelp.enums.Houkou;
import com.sbisec.helios.ap.fasthelp.enums.ProcessId;
import com.sbisec.helios.ap.fasthelp.enums.Request;
import com.sbisec.helios.ap.fasthelp.enums.RtnCd;
import com.sbisec.helios.ap.fasthelp.enums.TaiouSts;
import com.sbisec.helios.ap.fasthelp.enums.Yuuyoudo;
import com.sbisec.helios.ap.fasthelp.protocol.ccs.GetCcsFastHelpInfoInsertDoReq;
import com.sbisec.helios.ap.fasthelp.protocol.fasthelp.GetFasthelpCcsCallsImportReq;
import com.sbisec.helios.ap.fasthelp.service.Ccs2FastHelpInfoInsertService;
import com.sbisec.helios.ap.fasthelp.service.dto.ccs.CcsFastHelpInfoInsertDoIn;
import com.sbisec.helios.ap.fasthelp.service.dto.ccs.CcsFastHelpInfoInsertDoOut;
import com.sbisec.helios.ap.fasthelp.service.dto.ccs.CcsInAndOut;
import com.sbisec.helios.ap.fasthelp.service.dto.fasthelp.FasthelpCcsCallsImportIn;

/**
 * IFA接触履歴共有サービスクラス IFA接触履歴の共通処理を提供するサービスクラス
 * 
 * @author lianhua.xia
 * @date 2025-01-22
 */
@Component
public class IfaContactCommonServiceImpl {

	/** ロガー */
	private static final Logger LOGGER = LoggerFactory.getLogger(IfaContactCommonServiceImpl.class);

	/** 接触経路コードリストキー */
	private static final String SESSYOKU_KEIRO = "SESSYOKU_KEIRO";

	/** 表示パターン1 */
	private static final String DISPLAY_PATTERN_1 = "1";

	/** APIエラー状態コード */
	private static final int STATUS_API_ERROR = 0;
	/** 例外エラー状態コード */
	private static final int STATUS_EXP_ERROR = 1;

	/** IFA接触確認DAO */
	@Autowired
	private IfaContactConfirmDao confirmDao;

	/** IFA接触修正DAO */
	@Autowired
	private IfaContactCorrectDao correctDao;

	/** コードリストサービス */
	@Autowired
	private CodeListService codeListService;

	/** CCSサービス */
	@Autowired
	private Ccs2FastHelpInfoInsertService g_ccsService;

	/** IFA接触APIサービス */
	@Autowired
	private IfaContactApiServiceImpl apiService;

	/**
	 * SQL002.IFA問合せテーブルの登録処理を行う (Confirm用)
	 *
	 * @param x_dtoReq       IfaContactConfirmA002RequestDto リクエストDTO
	 * @param toiawaseNaiyou 問合せ内容
	 * @param x_sysDt        システム日時
	 * @return 処理結果 (true:成功, false:失敗)
	 */
	public boolean execSql002Confirm(IfaContactConfirmA002RequestDto x_dtoReq, String toiawaseNaiyou, String x_sysDt) {
		String p_userId = IfaCommonUtil.getUserAccount().getUserId();
		/* 以下は挿入項目のセット */
		IfaContactConfirmSql002RequestModel p_sql002Req = new IfaContactConfirmSql002RequestModel();
		// 問合せNO null
		p_sql002Req.setToiawaseNo(StringUtil.EMPTY_STRING);
		// 顧客ID 顧客共通情報.顧客コード
		p_sql002Req.setKokyakuId(IfaCommonUtil.getCustomerCommon().getCustomerCode());
		// ユーザＩＤ ユーザ共通情報.ユーザーID
		p_sql002Req.setUserId(p_userId);
		// 更新ユーザID ユーザ共通情報.ユーザーID
		p_sql002Req.setKosinUserId(p_userId);
		// 削除ユーザID null
		p_sql002Req.setSakujoUserId(StringUtil.EMPTY_STRING);
		// 問合せカテゴリコード（中） SQL001.問合せカテゴリコード
		p_sql002Req.setToiawaseCd(x_dtoReq.getToiawaseCd());
		// カテゴリー名称（中） SQL001.カテゴリ名称
		p_sql002Req.setToiawaseMei(x_dtoReq.getToiawaseMei());
		// 問合せカテゴリコード（大） リクエスト.問合せカテゴリコード
		p_sql002Req.setToiawaseDCd(x_dtoReq.getToiawaseDCd());
		// カテゴリー名称（大） リクエスト.問合せカテゴリ名称
		p_sql002Req.setToiawaseDMei(x_dtoReq.getToiawaseDMei());
		// 問合せ内容 内容
		p_sql002Req.setToiawaseNaiyou(toiawaseNaiyou);
		// 問合せ日時 システム日時
		p_sql002Req.setToiawaseNichiji(x_sysDt);
		// 重要度 リクエスト.重要度
		p_sql002Req.setJuuyoudo(x_dtoReq.getJuuyoudo());
		// クレーム リクエスト.クレーム
		p_sql002Req.setCream(x_dtoReq.getCream());
		// リクエスト リクエスト.リクエスト
		p_sql002Req.setRequest(x_dtoReq.getRequest());
		// 方向 リクエスト.入電方向
		p_sql002Req.setHoukou(x_dtoReq.getHoukou());
		// 対応ステータス リクエスト.対応ステータス
		p_sql002Req.setTaiouSts(x_dtoReq.getTaiouSts());
		// 登録日時 システム日時
		p_sql002Req.setTourokuNichiji(x_sysDt);
		// 変更日時 システム日時
		p_sql002Req.setHenkouNichiji(x_sysDt);
		// 削除日時 null
		p_sql002Req.setSakujoNichiji(StringUtil.EMPTY_STRING);
		// 削除フラグ 0
		p_sql002Req.setSakujoFlg(IfaContactInputUtil.SakujoFlg.NO.key);
		// 訪問日 リクエスト.訪問日
		p_sql002Req.setHoumonbi(x_dtoReq.getHoumonbi());
		// 次回訪問予定日 リクエスト.次回訪問予定日
		p_sql002Req.setNextHoumonbi(x_dtoReq.getNextHoumonbi());
		// 問合せカテゴリコード（小） null
		p_sql002Req.setToiawaseSCd(x_dtoReq.getToiawaseSCd());
		// カテゴリー名称（小） null
		p_sql002Req.setToiawaseSMei(x_dtoReq.getToiawaseSMei());
		// 接触経路 リクエスト.接触経路
		p_sql002Req.setSessyokuKeiro(x_dtoReq.getSessyokuKeiro());
		// IFA入力フラグ 1
		p_sql002Req.setIfaNyuuryokuFlg(IfaContactInputUtil.IfaNyuuryokuFlg.DONE.key);
		// CCSユーザID ユーザ共通情報.CCSログイン用ID
		p_sql002Req.setCcsUserId(IfaCommonUtil.getUserAccount().getCcsUserId());
		// CCS更新ユーザID ユーザ共通情報.CCSログイン用ID
		p_sql002Req.setCcsKosinUserId(IfaCommonUtil.getUserAccount().getCcsUserId());
		// CCS削除ユーザID null
		p_sql002Req.setCcsSakujoUserId(StringUtil.EMPTY_STRING);
		// ユーザ名 ユーザ共通情報.ユーザー名
		p_sql002Req.setUserName(IfaCommonUtil.getUserAccount().getUserNm());
		// 部店コード 顧客共通情報.部店コード
		p_sql002Req.setButenCode(IfaCommonUtil.getCustomerCommon().getButenCode());
		// 口座番号 顧客共通情報.口座番号
		p_sql002Req.setAccountNumber(IfaCommonUtil.getCustomerCommon().getAccountNumber());
		// 仲介業者コード 顧客共通情報.仲介業者コード
		p_sql002Req.setBrokerCode(IfaCommonUtil.getCustomerCommon().getBrokerCode());
		// 仲介業者営業員コード 顧客共通情報.仲介業者営業員コード
		p_sql002Req.setIntermediaryEmpCd(IfaCommonUtil.getCustomerCommon().getBrokerChargeCode());
		// 顧客名_姓名(漢字) 顧客共通情報.顧客名（漢字）
		p_sql002Req.setNameKanji(IfaCommonUtil.getCustomerCommon().getCustomerNameKanji());
		// 顧客名_姓名(カナ) 顧客共通情報.顧客名（カナ）
		p_sql002Req.setNameKana(IfaCommonUtil.getCustomerCommon().getCustomerNameKana());
		/* SQL実行 */
		try {
			if (confirmDao.insertIfaContactConfirmSql002(p_sql002Req) > 0) {
				x_dtoReq.setIfaToiawaseNo(p_sql002Req.getIfaToiawaseNo());
				return true;
			}
		} catch (Exception e) {
			LOGGER.warn("IfaContactCommonService.execSql002Confirm error.");
			return false;
		}
		return true;
	}

	/**
	 * SQL003.問合せ情報更新 (Confirm用)
	 *
	 * @param x_dtoReq IfaContactConfirmA002RequestDto リクエストDTO
	 * @param x_sysDt  システム日時
	 * @return 処理結果 (true:成功, false:失敗)
	 */
	public boolean execSql003Confirm(IfaContactConfirmA002RequestDto x_dtoReq, String x_sysDt) {
		IfaContactConfirmSql003RequestModel p_sql003Req = new IfaContactConfirmSql003RequestModel();
		/* 以下は抽出条件のセット */
		// IFA問合せNO = リクエスト.IFA問合せNO
		p_sql003Req.setIfaToiawaseNo(x_dtoReq.getIfaToiawaseNo());
		/* 以下は更新項目のセット */
		// 問合せカテゴリコード（中） API設定値.問合せカテゴリコード（中）
		p_sql003Req.setToiawaseCd(x_dtoReq.getToiawaseCd());
		// カテゴリー名称（中） API設定値.カテゴリー名称（中）
		p_sql003Req.setToiawaseMei(x_dtoReq.getToiawaseMei());
		// 問合せカテゴリコード（大） API設定値.問合せカテゴリコード（大）
		p_sql003Req.setToiawaseDCd(x_dtoReq.getToiawaseDCd());
		// カテゴリー名称（大） API設定値.カテゴリー名称（大）
		p_sql003Req.setToiawaseDMei(x_dtoReq.getToiawaseDMei());
		// 問合せカテゴリコード（小） API設定値.問合せカテゴリコード（小）
		p_sql003Req.setToiawaseSCd(x_dtoReq.getToiawaseSCd());
		// カテゴリー名称（小） API設定値.カテゴリー名称（小）
		p_sql003Req.setToiawaseSMei(x_dtoReq.getToiawaseSMei());
		// 重要度 リクエスト.重要度
		p_sql003Req.setJuuyoudo(x_dtoReq.getJuuyoudo());
		// クレーム リクエスト.クレーム
		p_sql003Req.setCream(x_dtoReq.getCream());
		// リクエスト リクエスト.リクエスト
		p_sql003Req.setRequest(x_dtoReq.getRequest());
		// 方向 リクエスト.入電方向
		p_sql003Req.setHoukou(x_dtoReq.getHoukou());
		// 対応ステータス リクエスト.対応ステータス
		p_sql003Req.setTaiouSts(x_dtoReq.getTaiouSts());
		// 変更日時 システム日時
		p_sql003Req.setHenkouNichiji(x_sysDt);
		// 訪問日 リクエスト.訪問日
		p_sql003Req.setHoumonbi(x_dtoReq.getHoumonbi());
		// 次回訪問予定日 リクエスト.次回訪問予定日
		p_sql003Req.setNextHoumonbi(x_dtoReq.getNextHoumonbi());
		// 接触経路 リクエスト.接触経路
		p_sql003Req.setSessyokuKeiro(x_dtoReq.getSessyokuKeiro());
		// 更新ユーザID ユーザ共通情報.ユーザーID
		p_sql003Req.setKosinUserId(IfaCommonUtil.getUserAccount().getUserId());
		// CCS更新ユーザID ユーザ共通情報.CCSログイン用ID
		p_sql003Req.setCcsKosinUserId(IfaCommonUtil.getUserAccount().getCcsUserId());
		/* SQL実行 */
		try {
			if (confirmDao.updateIfaContactConfirmSql003(p_sql003Req) > 0) {
				return true;
			}
		} catch (Exception e) {
			LOGGER.warn("IfaContactCommonService.execSql003Confirm error.");
			return false;
		}
		return true;
	}

	/**
	 * SQL004.IFA回答テーブルの登録処理を行う (Confirm用)
	 *
	 * @param x_dtoReq       IfaContactConfirmA002RequestDto リクエストDTO
	 * @param toiawaseNaiyou 回答内容
	 * @param x_sysDt        システム日時
	 * @param tourokuGroup   登録グループ
	 * @return 処理結果 (true:成功, false:失敗)
	 */
	public boolean execSql004Confirm(IfaContactConfirmA002RequestDto x_dtoReq, String toiawaseNaiyou, String x_sysDt,
			String tourokuGroup) {
		String p_userId = IfaCommonUtil.getUserAccount().getUserId();
		/* 以下は挿入項目のセット */
		IfaContactConfirmSql004RequestModel p_sql004Req = new IfaContactConfirmSql004RequestModel();
		// IFA問合せNO リクエスト.IFA問合せNO
		p_sql004Req.setIfaToiawaseNo(x_dtoReq.getIfaToiawaseNo());
		// 問合せNO リクエスト.問合せNO
		p_sql004Req.setToiawaseNo(x_dtoReq.getToiawaseNo());
		// 回答NO null
		p_sql004Req.setKaitouNo(StringUtil.EMPTY_STRING);
		// ユーザＩＤ ユーザ共通情報.ユーザーID
		p_sql004Req.setUserId(p_userId);
		// 更新ユーザID ユーザ共通情報.ユーザーID
		p_sql004Req.setKosinUserId(p_userId);
		// 削除ユーザID null
		p_sql004Req.setSakujoUserId(StringUtil.EMPTY_STRING);
		// 回答内容 追加入力
		p_sql004Req.setKaitouNaiyou(toiawaseNaiyou);
		// 回答日時 システム日時
		p_sql004Req.setKaitouNichiji(x_sysDt);
		// 登録日時 システム日時
		p_sql004Req.setTourokuNichiji(x_sysDt);
		// 変更日時 システム日時
		p_sql004Req.setHenkouNichiji(x_sysDt);
		// 削除日時 null
		p_sql004Req.setSakujoNichiji(StringUtil.EMPTY_STRING);
		// 接触経路 リクエスト.接触経路
		p_sql004Req.setSessyokuKeiro(x_dtoReq.getSessyokuKeiro());
		// 削除フラグ 0
		p_sql004Req.setSakujoFlg(IfaContactInputUtil.SakujoFlg.NO.key);
		// CCSユーザID ユーザ共通情報.CCSログイン用ID
		p_sql004Req.setCcsUserId(IfaCommonUtil.getUserAccount().getCcsUserId());
		// CCS更新ユーザID ユーザ共通情報.CCSログイン用ID
		p_sql004Req.setCcsKosinUserId(IfaCommonUtil.getUserAccount().getCcsUserId());
		// CCS削除ユーザID null
		p_sql004Req.setCcsSakujoUserId(StringUtil.EMPTY_STRING);
		// ユーザ名 ユーザ共通情報.ユーザー名
		p_sql004Req.setUserName(IfaCommonUtil.getUserAccount().getUserNm());
		// 登録グループ リクエスト.登録グループ
		p_sql004Req.setTourokuGroup(tourokuGroup);
		/* SQL実行 */
		try {
			if (confirmDao.insertIfaContactConfirmSql004(p_sql004Req) > 0) {
				x_dtoReq.setIfaKaitouNo(p_sql004Req.getIfaKaitouNo());
				return true;
			}
		} catch (Exception e) {
			LOGGER.warn("IfaContactCommonService.execSql004Confirm error.");
			return false;
		}
		return true;
	}

	/**
	 * SQL004.IFA問合せテーブルの更新 (Correct用)
	 *
	 * @param x_dtoReq IfaContactCorrectA003RequestDto リクエストDTO
	 * @param naiyou   回答内容
	 * @param x_sysDt  システム日時
	 * @return 処理結果 (true:成功, false:失敗)
	 */
	public boolean execSql004Correct(IfaContactCorrectA003RequestDto x_dtoReq, String naiyou, String x_sysDt) {
		IfaContactCorrectSql004RequestModel p_sql004Req = new IfaContactCorrectSql004RequestModel();
		/* 以下は抽出条件のセット */
		// IFA問合せNO = リクエスト.IFA問合せNO
		p_sql004Req.setIfaToiawaseNo(x_dtoReq.getIfaToiawaseNo());
		/* 以下は更新項目のセット */
		// 問合せ内容 リクエスト.内容
		p_sql004Req.setToiawaseNaiyou(naiyou);
		// 変更日時 システム日時
		p_sql004Req.setHenkouNichiji(x_sysDt);
		// 更新ユーザID ユーザ共通情報.ユーザーID
		p_sql004Req.setKosinUserId(IfaCommonUtil.getUserAccount().getUserId());
		// CCS更新ユーザID ユーザ共通情報.CCSログイン用ID
		p_sql004Req.setCcsKosinUserId(IfaCommonUtil.getUserAccount().getCcsUserId());
		/* SQL実行 */
		try {
			if (correctDao.updateIfaContactCorrectSql004(p_sql004Req) > 0) {
				return true;
			}
		} catch (Exception e) {
			LOGGER.warn("IfaContactCommonService.execSql004Correct error.");
			return false;
		}
		return true;
	}

	/**
	 * SQL006.IFA回答テーブルの更新 (Correct用)
	 *
	 * @param x_dtoReq    IfaContactCorrectA003RequestDto リクエストDTO
	 * @param ifaKaitouNo IFA回答NO
	 * @param naiyou      回答内容
	 * @param x_sysDt     システム日時
	 * @return 処理結果 (true:成功, false:失敗)
	 */
	public boolean execSql006Correct(IfaContactCorrectA003RequestDto x_dtoReq, String ifaKaitouNo, String naiyou,
			String x_sysDt) {
		IfaContactCorrectSql006RequestModel p_sql006Req = new IfaContactCorrectSql006RequestModel();
		/* 以下は抽出条件のセット */
		// IFA問合せNO = リクエスト.IFA問合せNO
		p_sql006Req.setIfaToiawaseNo(x_dtoReq.getIfaToiawaseNo());
		// IFA回答NO = リクエスト.IFA回答NO
		p_sql006Req.setIfaKaitouNo(ifaKaitouNo);
		/* 以下は更新項目のセット */
		// 回答内容 リクエスト.内容
		p_sql006Req.setKaitouNaiyou(naiyou);
		// 変更日時 システム日時
		p_sql006Req.setHenkouNichiji(x_sysDt);
		// 更新ユーザID ユーザ共通情報.ユーザーID
		p_sql006Req.setKosinUserId(IfaCommonUtil.getUserAccount().getUserId());
		// CCS更新ユーザID ユーザ共通情報.CCSログイン用ID
		p_sql006Req.setCcsKosinUserId(IfaCommonUtil.getUserAccount().getCcsUserId());
		/* SQL実行 */
		try {
			if (correctDao.updateIfaContactCorrectSql006(p_sql006Req) > 0) {
				return true;
			}
		} catch (Exception e) {
			LOGGER.warn("IfaContactCommonService.execSql006Correct error.");
			return false;
		}
		return true;
	}

	/**
	 * SQL009.回答情報登録 (Correct用)
	 *
	 * @param x_dtoReq        IfaContactCorrectA003RequestDto リクエストDTO
	 * @param aKaitouNaiyou   回答内容
	 * @param aTourokuNichiji 登録日時
	 * @param sessyokuKeiro   接触経路
	 * @param x_sysDt         システム日時
	 * @return 処理結果 (true:成功, false:失敗)
	 */
	public boolean execSql009Correct(IfaContactCorrectA003RequestDto x_dtoReq, String aKaitouNaiyou,
			String aTourokuNichiji, String sessyokuKeiro, String x_sysDt) {
		/* 以下は挿入項目のセット */
		IfaContactCorrectSql009RequestModel p_sql009Req = new IfaContactCorrectSql009RequestModel();
		// IFA問合せNO リクエスト.IFA問合せNO
		p_sql009Req.setIfaToiawaseNo(x_dtoReq.getIfaToiawaseNo());
		// 問合せNO リクエスト.問合せNO
		p_sql009Req.setToiawaseNo(x_dtoReq.getToiawaseNo());
		// 回答NO null
		p_sql009Req.setKaitouNo(StringUtils.EMPTY);
		// ユーザＩＤ ユーザ共通情報.ユーザーID
		p_sql009Req.setUserId(IfaCommonUtil.getUserAccount().getUserId());
		// 更新ユーザID ユーザ共通情報.ユーザーID
		p_sql009Req.setKosinUserId(IfaCommonUtil.getUserAccount().getUserId());
		// 削除ユーザID null
		p_sql009Req.setSakujoUserId(StringUtils.EMPTY);
		// 回答内容 A003.回答内容
		p_sql009Req.setKaitouNaiyou(aKaitouNaiyou);
		// 回答日時 A003.回答日時
		p_sql009Req.setKaitouNichiji(aTourokuNichiji);
		// 登録日時 A003.回答日時
		p_sql009Req.setTourokuNichiji(aTourokuNichiji);
		// 変更日時 A003.システム日時
		p_sql009Req.setHenkouNichiji(x_sysDt);
		// 削除日時 null
		p_sql009Req.setSakujoNichiji(StringUtils.EMPTY);
		// 接触経路 リクエスト.接触経路
		p_sql009Req.setSessyokuKeiro(sessyokuKeiro);
		// 削除フラグ 0
		p_sql009Req.setSakujoFlg("0");
		// CCSユーザID ユーザ共通情報.CCSログイン用ID
		p_sql009Req.setCcsUserId(IfaCommonUtil.getUserAccount().getCcsUserId());
		// CCS更新ユーザID ユーザ共通情報.CCSログイン用ID
		p_sql009Req.setCcsKosinUserId(IfaCommonUtil.getUserAccount().getCcsUserId());
		// CCS削除ユーザID null
		p_sql009Req.setCcsSakujoUserId(StringUtil.EMPTY_STRING);
		// ユーザ名 ユーザ共通情報.ユーザー名
		p_sql009Req.setUserName(IfaCommonUtil.getUserAccount().getUserNm());
		// 登録グループ A003.登録グループ
		p_sql009Req.setTourokuGroup(x_dtoReq.getTourokuGroup());
		/* SQL実行 */
		try {
			if (correctDao.insertIfaContactCorrectSql009(p_sql009Req) > 0) {
				x_dtoReq.setIfaKaitouNo(p_sql009Req.getIfaKaitouNo());
				return true;
			}
		} catch (Exception e) {
			LOGGER.warn("IfaContactCommonService.execSql009Correct error.");
			return false;
		}
		return true;
	}

	/**
	 * API001 IFA問合せ、回答情報をCCSに連携する (Confirm用)
	 *
	 * @param x_dtoReq        IfaContactConfirmA002RequestDto リクエストDTO
	 * @param x_sql008Res     IfaContactConfirmSql008ResponseModel SQL008結果
	 * @param p_procId        API処理区分
	 * @param toiawaseNaiyou0 A002.内容リスト[0]
	 * @param content         A002.回答内容
	 * @param x_sysDt         システム日時
	 * @param kaitouDateTime  A002.回答日時
	 * @param toiawaseNo      問合せNo
	 * @return CcsInAndOut API応答
	 * @throws Exception
	 */
	public CcsInAndOut callCCSApiConfirm(IfaContactConfirmA002RequestDto x_dtoReq,
			IfaContactConfirmSql008ResponseModel x_sql008Res, String p_procId, String toiawaseNaiyou0, String content,
			String x_sysDt, String kaitouDateTime, String toiawaseNo) throws Exception {

		GetCcsFastHelpInfoInsertDoReq p_req = new GetCcsFastHelpInfoInsertDoReq();
		CcsFastHelpInfoInsertDoIn p_doIn = new CcsFastHelpInfoInsertDoIn();
		/* 以下がINの設定 */
		// API処理区分
		p_doIn.setProcess_id(p_procId);
		// コール番号 ""
		p_doIn.setCall_id(StringUtil.EMPTY_STRING);
		// サブコール番号 処理区分 "1"(問合せ入力)、"3"(問合せ入力+回答入力)の場合:"" 上記以外の場合:リクエスト.問合せNO
		p_doIn.setQ_toiawase_no(ProcessId.Q_INS.getId().equals(p_procId) || ProcessId.A_INS.getId().equals(p_procId)
				? StringUtil.EMPTY_STRING
				: toiawaseNo);
		// 顧客番号 顧客共通情報.顧客コード
		p_doIn.setQ_kokyaku_id(IfaCommonUtil.getCustomerCommon().getCustomerCode());
		// 登録担当者番号 処理区分 "1"(問合せ入力)、"3"(問合せ入力+回答入力)の場合:ユーザ共通情報.CCSログイン用ID
		// 上記以外の場合:SQL008.CCSログイン用ID
		p_doIn.setQ_user_id(ProcessId.Q_INS.getId().equals(p_procId) || ProcessId.A_INS.getId().equals(p_procId)
				? IfaCommonUtil.getUserAccount().getCcsUserId()
				: x_sql008Res.getCcsUserId());
		// 現在担当者番号 ユーザ共通情報.CCSログイン用ID
		p_doIn.setQ_kosin_user_id(IfaCommonUtil.getUserAccount().getCcsUserId());
		// （call_category）問合せカテゴリコード リクエスト.問合せカテゴリコード（中）
		p_doIn.setQ_toiawase_cd(x_dtoReq.getToiawaseCd());
		// （call_category）カテゴリ名 リクエスト.問合せカテゴリ名称（中）
		p_doIn.setQ_toiawase_mei(x_dtoReq.getToiawaseMei());
		// （call_category）問合せカテゴリコード（大） リクエスト.問合せカテゴリコード（大）
		p_doIn.setQ_toiawase_d_cd(x_dtoReq.getToiawaseDCd());
		// （call_category）カテゴリ名（大） リクエスト.問合せカテゴリ名称（大）
		p_doIn.setQ_toiawase_d_mei(x_dtoReq.getToiawaseDMei());
		// （call_category）問合せカテゴリコード（小） リクエスト.問合せカテゴリコード（小）
		p_doIn.setQ_toiawase_s_cd(x_dtoReq.getToiawaseSCd());
		// （call_category）カテゴリ名（小） リクエスト.問合せカテゴリ名称（小）
		p_doIn.setQ_toiawase_s_mei(x_dtoReq.getToiawaseSMei());
		// 問題詳細_ロング
		/*
		 * リクエスト.IFA入力フラグ = "1"の場合 区分.接触経路(リクエスト.接触経路)+A002.内容リスト[0] それ以外の場合
		 * A002.内容リスト[0]
		 */
		p_doIn.setQ_toiawase_naiyou(
				Cp932.toJIS(StringUtils.equals(x_dtoReq.getIfaNyuuryokuFlg(), "1")
						? "(" + IfaContactInputUtil.padStringByByte(codeListService.getValue(SESSYOKU_KEIRO,
								x_dtoReq.getSessyokuKeiro(), DISPLAY_PATTERN_1), ' ', 16) + ")" + toiawaseNaiyou0
						: toiawaseNaiyou0));
		// 問合せ日時/登録日時 処理区分 "1"(問合せ入力)、"3"(問合せ入力+回答入力)の場合:システム日時 上記以外の場合:SQL008.登録日時
		p_doIn.setQ_toiawase_nichiji(
				ProcessId.Q_INS.getId().equals(p_procId) || ProcessId.A_INS.getId().equals(p_procId) ? x_sysDt
						: x_sql008Res.getTourokuNichiji());
		// 優先度 リクエスト.重要度
		p_doIn.setQ_juuyoudo(Yuuyoudo.getVal(x_dtoReq.getJuuyoudo()));
		// クレームフラグ（1:あり, 1以外：なし） リクエスト.クレーム
		p_doIn.setQ_cream(Cream.getVal(x_dtoReq.getCream()));
		// リクエストフラグ（1:あり, 1以外：なし） リクエスト.リクエスト
		p_doIn.setQ_request(Request.getVal(x_dtoReq.getRequest()));
		// 受付種別 リクエスト.入電方向
		p_doIn.setQ_houkou(Houkou.getVal(x_dtoReq.getHoukou()));
		// ステータス1 リクエスト.対応ステータス
		p_doIn.setQ_taiou_sts(TaiouSts.getVal(x_dtoReq.getTaiouSts()));
		// 登録日時 処理区分 "1"(問合せ入力)、"3"(問合せ入力+回答入力)の場合:システム日時 上記以外の場合:SQL008.登録日時
		p_doIn.setQ_touroku_nichiji(
				ProcessId.Q_INS.getId().equals(p_procId) || ProcessId.A_INS.getId().equals(p_procId) ? x_sysDt
						: x_sql008Res.getTourokuNichiji());
		// 更新日時 システム日時
		p_doIn.setQ_henkou_nichiji(x_sysDt);
		// 訪問日 リクエスト.訪問日
		p_doIn.setQ_houmonbi(x_dtoReq.getHoumonbi());
		// 次回訪問予定日 リクエスト.次回訪問予定日
		p_doIn.setQ_next_houmonbi(x_dtoReq.getNextHoumonbi());
		/* "4"(問合せ変更+回答入力)の場合:リクエスト.問合せNO それ以外の場合:"" */
		p_doIn.setA_toiawase_no(ProcessId.Q_UPD_A_INS.getId().equals(p_procId) ? toiawaseNo : "");
		/* "1"(問合せ入力)、"2"(問合せ修正)の場合、以降の項目は未設定 */
		if (!(ProcessId.Q_INS.getId().equals(p_procId) || ProcessId.Q_UPD.getId().equals(p_procId))) {
			// tb_answerユーザID/登録担当者番号 ユーザ共通情報.CCSログイン用ID
			p_doIn.setA_user_id(IfaCommonUtil.getUserAccount().getCcsUserId());
			// 更新担当者番号 ユーザ共通情報.CCSログイン用ID
			p_doIn.setA_kosin_user_id(IfaCommonUtil.getUserAccount().getCcsUserId());
			// 回答詳細_ロング A002.回答内容
			p_doIn.setA_kaitou_naiyou(Cp932.toJIS(content));
			// 更新日時/最終回答日時/登録日時 A002.回答日時
			p_doIn.setA_kaitou_nichiji(kaitouDateTime);
			// tb_answer登録日時/登録日時 A002.回答日時
			p_doIn.setA_touroku_nichiji(kaitouDateTime);
			// 更新日時 システム日時
			p_doIn.setA_henkou_nichiji(x_sysDt);
		}
		/* 問合せ回答確認APIの呼び出し */
		p_req.setParameter(p_doIn);
		CcsFastHelpInfoInsertDoOut p_api001Out = new CcsFastHelpInfoInsertDoOut();
		try {
			p_api001Out = g_ccsService.getCcsFastHelpInfoInsertDo(p_req).getCcsFastHelpInfoInsertDoOut();
		} catch (Exception e) {
			apiService.recordCCSApiError(p_doIn, x_sysDt, IfaContactInputUtil.ApiFlg.CCS.key, ClassName.CORRECT.key,
					STATUS_EXP_ERROR);
			throw new Exception("IfaContactCommonService.callCCSApiConfirm error.", e);
		}
		// エラーの場合、エラー内容をIFA問合せ・回答エラーテーブルに登録する
		if (!RtnCd.OK.getId().equals(p_api001Out.getRtn_cd())) {
			apiService.recordCCSApiError(p_doIn, x_sysDt, IfaContactInputUtil.ApiFlg.CCS.key, ClassName.CONFIRM.key,
					STATUS_API_ERROR);
		}
		CcsInAndOut ccsInAndOut = new CcsInAndOut();
		ccsInAndOut.setCcsIn(p_doIn);
		ccsInAndOut.setCcsOut(p_api001Out);
		return ccsInAndOut;
	}

	/**
	 * API001 IFA問合せ、回答情報をCCSに連携する (Correct用)
	 *
	 * @param x_dtoReq        IfaContactCorrectA003RequestDto リクエストDTO
	 * @param x_sql003Res     IfaContactCorrectSql003ResponseModel SQL003結果
	 * @param x_sql005Res     IfaContactCorrectSql005ResponseModel SQL005結果
	 * @param naiyou0         String A003.内容リスト[0]
	 * @param naiyou          String A003.回答内容
	 * @param operationType   String 処理区分
	 * @param aTourokuNichiji String A003.回答日時
	 * @param x_sysDt         String システム日時
	 * @return CcsInAndOut API応答
	 * @throws Exception
	 */
	public CcsInAndOut callCCSApiCorrect(IfaContactCorrectA003RequestDto x_dtoReq,
			IfaContactCorrectSql003ResponseModel x_sql003Res, IfaContactCorrectSql005ResponseModel x_sql005Res,
			String naiyou0, String naiyou, String operationType, String aTourokuNichiji, String x_sysDt)
			throws Exception {

		GetCcsFastHelpInfoInsertDoReq p_req = new GetCcsFastHelpInfoInsertDoReq();
		CcsFastHelpInfoInsertDoIn p_doIn = new CcsFastHelpInfoInsertDoIn();
		/* 以下がINの設定 */
		// API処理区分
		p_doIn.setProcess_id(operationType);
		// コール番号 ""
		p_doIn.setCall_id(StringUtil.EMPTY_STRING);
		// サブコール番号 SQL003.問合せNO
		p_doIn.setQ_toiawase_no(x_sql003Res.getToiawaseNo());
		// 顧客番号 顧客共通情報.顧客コード
		p_doIn.setQ_kokyaku_id(IfaCommonUtil.getCustomerCommon().getCustomerCode());
		// 登録担当者番号 SQL003.CCSユーザID
		p_doIn.setQ_user_id(x_sql003Res.getCcsUserId());
		// 現在担当者番号 ユーザ共通情報.CCSログイン用ID
		p_doIn.setQ_kosin_user_id(IfaCommonUtil.getUserAccount().getCcsUserId());
		// （call_category）問合せカテゴリコード SQL003.問合せカテゴリコード（中）
		p_doIn.setQ_toiawase_cd(x_sql003Res.getToiawaseCd());
		// （call_category）カテゴリ名 SQL003.カテゴリー名称（中）
		p_doIn.setQ_toiawase_mei(x_sql003Res.getToiawaseMei());
		// （call_category）問合せカテゴリコード（大） SQL003.問合せカテゴリコード（大）
		p_doIn.setQ_toiawase_d_cd(x_sql003Res.getToiawaseDCd());
		// （call_category）カテゴリ名（大） SQL003.カテゴリー名称（大）
		p_doIn.setQ_toiawase_d_mei(x_sql003Res.getToiawaseDMei());
		// （call_category）問合せカテゴリコード（小） SQL003.問合せカテゴリコード（小）
		p_doIn.setQ_toiawase_s_cd(StringUtils.isNotEmpty(x_sql003Res.getToiawaseSCd()) ? x_sql003Res.getToiawaseSCd()
				: StringUtil.EMPTY_STRING);
		// （call_category）カテゴリ名（小） SQL003.問合せカテゴリ名称（小）
		p_doIn.setQ_toiawase_s_mei(StringUtils.isNotEmpty(x_sql003Res.getToiawaseSMei()) ? x_sql003Res.getToiawaseSMei()
				: StringUtil.EMPTY_STRING);
		// 問題詳細_ロング
		/*
		 * リクエスト.IFA入力フラグ = "1"の場合: 処理区分:"2"(問合せ修正)： 区分.接触経路(SQL003.接触経路)+A003.内容リスト[0]
		 * 接触経路部分は()で括り、18byte未満の場合は後ろスペース埋め それ以外の場合:SQL003.内容:
		 * 区分.接触経路(SQL003.接触経路)SQL003.内容 接触経路部分は()で括り、18byte未満の場合は後ろスペース埋め
		 * リクエスト.IFA入力フラグ != "1"の場合: 処理区分:"2"(問合せ修正)： A003.内容リスト[0] それ以外の場合:SQL003.内容
		 */
		if (StringUtils.equals(x_sql003Res.getIfaNyuuryokuFlg(), "1")) {
			p_doIn.setQ_toiawase_naiyou(
					Cp932.toJIS("("
							+ IfaContactInputUtil.padStringByByte(codeListService.getValue(SESSYOKU_KEIRO,
									x_sql003Res.getSessyokuKeiro(), DISPLAY_PATTERN_1), ' ', 16)
							+ ")" + (StringUtils.equals(x_dtoReq.getTourokuGroup(), "0") ? naiyou0
									: x_sql003Res.getToiawaseNaiyou())));
		} else {
			p_doIn.setQ_toiawase_naiyou(Cp932.toJIS(
					StringUtils.equals(x_dtoReq.getTourokuGroup(), "0") ? naiyou0 : x_sql003Res.getToiawaseNaiyou()));
		}
		// 問合せ日時/登録日時 SQL003.問合せ日時
		p_doIn.setQ_toiawase_nichiji(DateUtil.dateFormat(x_sql003Res.getToiawaseNichiji(),
				DateUtil.NOT_SEPARATED_YYYYMMDD_HHMMSS, DateUtil.SEPARATED_YYYYMMDD_HHMMSS));
		// 優先度 SQL003.重要度
		p_doIn.setQ_juuyoudo(Yuuyoudo.getVal(x_sql003Res.getJuuyoudo()));
		// クレームフラグ（1:あり, 1以外：なし） SQL003.クレーム
		p_doIn.setQ_cream(Cream.getVal(x_sql003Res.getCream()));
		// リクエストフラグ（1:あり, 1以外：なし） SQL003.リクエスト
		p_doIn.setQ_request(Request.getVal(x_sql003Res.getRequest()));
		// 受付種別 SQL003.入電方向
		p_doIn.setQ_houkou(Houkou.getVal(x_sql003Res.getHoukou()));
		// ステータス1 SQL003.対応ステータス
		p_doIn.setQ_taiou_sts(TaiouSts.getVal(x_sql003Res.getTaiouSts()));
		// 登録日時 SQL003.登録日時
		p_doIn.setQ_touroku_nichiji(DateUtil.dateFormat(x_sql003Res.getTourokuNichiji(),
				DateUtil.NOT_SEPARATED_YYYYMMDD_HHMMSS, DateUtil.SEPARATED_YYYYMMDD_HHMMSS));
		// 更新日時 登録グループ=0の場合 : システム日時 登録グループ=0以外の場合 : SQL003.更新日時
		p_doIn.setQ_henkou_nichiji(StringUtils.equals(x_dtoReq.getTourokuGroup(), "0") ? x_sysDt
				: DateUtil.dateFormat(x_sql003Res.getHenkouNichiji(), DateUtil.NOT_SEPARATED_YYYYMMDD_HHMMSS,
						DateUtil.SEPARATED_YYYYMMDD_HHMMSS));
		// 訪問日 リクエスト.訪問日
		p_doIn.setQ_houmonbi(x_sql003Res.getHoumonbi());
		// 次回訪問予定日 リクエスト.次回訪問予定日
		p_doIn.setQ_next_houmonbi(x_sql003Res.getNextHoumonbi());

		// "2"(問合せ修正) の場合、以降の項目は未設定
		if (!ProcessId.Q_UPD.getId().equals(operationType)) {
			// サブコール番号 SQL003.問合せNO
			p_doIn.setA_toiawase_no(x_sql003Res.getToiawaseNo());
			// 回答NO 処理区分 "4"(問合せ変更+回答入力)の場合:"" 上記以外の場合:SQL005.回答NO
			p_doIn.setA_kaitou_no(
					StringUtils.equals(operationType, "4") ? StringUtil.EMPTY_STRING : x_sql005Res.getKaitouNo());
			// tb_answerユーザID/登録担当者番号 処理区分 "4"(問合せ変更+回答入力)の場合:ユーザ共通情報.CCSログイン用ID
			// 上記以外の場合:SQL005.CCSユーザＩＤ
			p_doIn.setA_user_id(StringUtils.equals(operationType, "4") ? IfaCommonUtil.getUserAccount().getCcsUserId()
					: x_sql005Res.getCcsUserId());
			// 更新担当者番号 SQL005.CCSユーザID
			p_doIn.setA_kosin_user_id(IfaCommonUtil.getUserAccount().getCcsUserId());
			// 回答詳細_ロング A003.回答内容
			p_doIn.setA_kaitou_naiyou(Cp932.toJIS(naiyou));
			// 更新日時/最終回答日時/登録日時 処理区分 "4"(問合せ変更+回答入力)の場合:A003.回答日時 それ以外の場合:SQL005.登録日時
			p_doIn.setA_kaitou_nichiji(StringUtils.equals(operationType, "4") ? aTourokuNichiji
					: DateUtil.dateFormat(x_sql005Res.getTourokuNichiji(), DateUtil.NOT_SEPARATED_YYYYMMDD_HHMMSS,
							DateUtil.SEPARATED_YYYYMMDD_HHMMSS));
			// tb_answer登録日時/登録日時 処理区分 "4"(問合せ変更+回答入力)の場合:A003.回答日時 それ以外の場合:SQL005.登録日時
			p_doIn.setA_touroku_nichiji(StringUtils.equals(operationType, "4") ? aTourokuNichiji
					: DateUtil.dateFormat(x_sql005Res.getTourokuNichiji(), DateUtil.NOT_SEPARATED_YYYYMMDD_HHMMSS,
							DateUtil.SEPARATED_YYYYMMDD_HHMMSS));
			// 更新日時 システム日時
			p_doIn.setA_henkou_nichiji(x_sysDt);
		}
		/* 問合せ回答確認APIの呼び出し */
		p_req.setParameter(p_doIn);
		CcsFastHelpInfoInsertDoOut p_api001Out = new CcsFastHelpInfoInsertDoOut();
		try {
			p_api001Out = g_ccsService.getCcsFastHelpInfoInsertDo(p_req).getCcsFastHelpInfoInsertDoOut();
		} catch (Exception e) {
			apiService.recordCCSApiError(p_doIn, x_sysDt, IfaContactInputUtil.ApiFlg.CCS.key, ClassName.CORRECT.key,
					STATUS_EXP_ERROR);
			throw new Exception("IfaContactCommonService.callCCSApiCorrect error.", e);
		}
		// エラーの場合、エラー内容をIFA問合せ・回答エラーテーブルに登録する
		if (!RtnCd.OK.getId().equals(p_api001Out.getRtn_cd())) {
			apiService.recordCCSApiError(p_doIn, x_sysDt, IfaContactInputUtil.ApiFlg.CCS.key, ClassName.CORRECT.key,
					STATUS_API_ERROR);
		}
		CcsInAndOut ccsInAndOut = new CcsInAndOut();
		ccsInAndOut.setCcsIn(p_doIn);
		ccsInAndOut.setCcsOut(p_api001Out);
		return ccsInAndOut;
	}

	/**
	 * API002 IFA問合せ、回答情報をFastHelpに連携する (Confirm用)
	 *
	 * @param ccsInAndOut CcsInAndOut CCS連携データ
	 * @param p_procId    API処理区分
	 * @return FasthelpCcsCallsImportIn FastHelp連携データ
	 */
	public FasthelpCcsCallsImportIn callFastHelpApiConfirm(CcsInAndOut ccsInAndOut, String p_procId) {

		GetFasthelpCcsCallsImportReq p_req = new GetFasthelpCcsCallsImportReq();
		FasthelpCcsCallsImportIn p_doIn = new FasthelpCcsCallsImportIn();
		/* 以下がINの設定 */
		// API処理区分
		p_doIn.setProcess_id(p_procId);
		// サブコール番号 処理区分 "1"(問合せ入力)、"3"(問合せ入力+回答入力)の場合:API001.問合せNO 上記以外の場合:
		// API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_toiawase_no(ProcessId.Q_INS.getId().equals(p_procId) || ProcessId.A_INS.getId().equals(p_procId)
				? ccsInAndOut.getCcsOut().getRtn_toiawase_no()
				: ccsInAndOut.getCcsIn().getQ_toiawase_no());
		// 顧客番号 API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_kokyaku_id(ccsInAndOut.getCcsIn().getQ_kokyaku_id());
		// 登録担当者番号 API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_user_id(ccsInAndOut.getCcsIn().getQ_user_id());
		// 現在担当者番号 API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_kosin_user_id(ccsInAndOut.getCcsIn().getQ_kosin_user_id());
		// （call_category）問合せカテゴリコード API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_toiawase_cd(ccsInAndOut.getCcsIn().getQ_toiawase_cd());
		// （call_category）カテゴリ名 API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_toiawase_mei(ccsInAndOut.getCcsIn().getQ_toiawase_mei());
		// （call_category）問合せカテゴリコード（大） API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_toiawase_d_cd(ccsInAndOut.getCcsIn().getQ_toiawase_d_cd());
		// （call_category）カテゴリ名（大）API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_toiawase_d_mei(ccsInAndOut.getCcsIn().getQ_toiawase_d_mei());
		// （call_category）問合せカテゴリコード（小） API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_toiawase_s_cd(ccsInAndOut.getCcsIn().getQ_toiawase_s_cd());
		// （call_category）カテゴリ名（小） API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_toiawase_s_mei(ccsInAndOut.getCcsIn().getQ_toiawase_s_mei());
		// 問題詳細_ロング API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_toiawase_naiyou(ccsInAndOut.getCcsIn().getQ_toiawase_naiyou());
		// 問合せ日時/登録日時 API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_toiawase_nichiji(ccsInAndOut.getCcsIn().getQ_toiawase_nichiji());
		// 優先度 API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_juuyoudo(ccsInAndOut.getCcsIn().getQ_juuyoudo());
		// クレームフラグ（1:あり, 1以外：なし） API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_cream(ccsInAndOut.getCcsIn().getQ_cream());
		// リクエストフラグ（1:あり, 1以外：なし） API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_request(ccsInAndOut.getCcsIn().getQ_request());
		// 受付種別 API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_houkou(ccsInAndOut.getCcsIn().getQ_houkou());
		// ステータス1 API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_taiou_sts(ccsInAndOut.getCcsIn().getQ_taiou_sts());
		// 登録日時 API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_touroku_nichiji(ccsInAndOut.getCcsIn().getQ_touroku_nichiji());
		// 更新日時 API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_henkou_nichiji(ccsInAndOut.getCcsIn().getQ_henkou_nichiji());
		// 訪問日 API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_houmonbi(ccsInAndOut.getCcsIn().getQ_houmonbi());
		// 次回訪問予定日 API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_next_houmonbi(ccsInAndOut.getCcsIn().getQ_next_houmonbi());
		/* "1"(問合せ入力)、"2"(問合せ修正)の場合、以降の項目は未設定 */
		if (!(ProcessId.Q_INS.getId().equals(p_procId) || ProcessId.Q_UPD.getId().equals(p_procId))) {
			// サブコール番号 "4"(問合せ変更+回答入力)の場合: API001の同リクエスト項目と同じ値をセット
			// "3"(問合せ入力+回答入力)の場合:API001.問合せNO
			p_doIn.setA_toiawase_no(
					ProcessId.A_INS.getId().equals(p_procId) ? ccsInAndOut.getCcsOut().getRtn_toiawase_no()
							: ccsInAndOut.getCcsIn().getQ_toiawase_no());
			// 回答NO "3"(問合せ入力+回答入力)、"4"(問合せ変更+回答入力)の場合 API001のレスポンスの回答NOをセット それ以外の場合は
			// API001の同リクエスト項目と同じ値をセット
			p_doIn.setA_kaitou_no(
					ProcessId.A_INS.getId().equals(p_procId) || ProcessId.Q_UPD_A_INS.getId().equals(p_procId)
							? ccsInAndOut.getCcsOut().getRtn_kaitou_no()
							: ccsInAndOut.getCcsIn().getA_kaitou_no());
			// tb_answerユーザID/登録担当者番号 API001の同リクエスト項目と同じ値をセット
			p_doIn.setA_user_id(ccsInAndOut.getCcsIn().getA_user_id());
			// 更新担当者番号 API001の同リクエスト項目と同じ値をセット
			p_doIn.setA_kosin_user_id(ccsInAndOut.getCcsIn().getA_kosin_user_id());
			// 回答詳細_ロング API001の同リクエスト項目と同じ値をセット
			p_doIn.setA_kaitou_naiyou(ccsInAndOut.getCcsIn().getA_kaitou_naiyou());
			// 更新日時/最終回答日時/登録日時 API001の同リクエスト項目と同じ値をセット
			p_doIn.setA_kaitou_nichiji(ccsInAndOut.getCcsIn().getA_kaitou_nichiji());
			// tb_answer登録日時/登録日時 API001の同リクエスト項目と同じ値をセット
			p_doIn.setA_touroku_nichiji(ccsInAndOut.getCcsIn().getA_touroku_nichiji());
			// 更新日時 API001の同リクエスト項目と同じ値をセット
			p_doIn.setA_henkou_nichiji(ccsInAndOut.getCcsIn().getA_henkou_nichiji());
		}
		/* FastHelp問合せデータ登録APIの呼び出し */
		p_req.setParameter(p_doIn);
		return p_doIn;
	}

	/**
	 * API002 IFA問合せ、回答情報をFastHelpに連携する (Correct用)
	 *
	 * @param ccsInAndOut   CcsInAndOut CCS連携データ
	 * @param operationType String 処理区分
	 * @return FasthelpCcsCallsImportIn FastHelp連携データ
	 */
	public FasthelpCcsCallsImportIn callFastHelpApiCorrect(CcsInAndOut ccsInAndOut, String operationType) {

		GetFasthelpCcsCallsImportReq p_req = new GetFasthelpCcsCallsImportReq();
		FasthelpCcsCallsImportIn p_doIn = new FasthelpCcsCallsImportIn();
		/* 以下がINの設定 */
		// API処理区分 A003.API処理区分
		p_doIn.setProcess_id(operationType);
		// サブコール番号 API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_toiawase_no(ccsInAndOut.getCcsIn().getQ_toiawase_no());
		// 顧客番号 API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_kokyaku_id(ccsInAndOut.getCcsIn().getQ_kokyaku_id());
		// 登録担当者番号 API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_user_id(ccsInAndOut.getCcsIn().getQ_user_id());
		// 現在担当者番号 API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_kosin_user_id(ccsInAndOut.getCcsIn().getQ_kosin_user_id());
		// （call_category）問合せカテゴリコード API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_toiawase_cd(ccsInAndOut.getCcsIn().getQ_toiawase_cd());
		// （call_category）カテゴリ名 API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_toiawase_mei(ccsInAndOut.getCcsIn().getQ_toiawase_mei());
		// （call_category）問合せカテゴリコード（大） API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_toiawase_d_cd(ccsInAndOut.getCcsIn().getQ_toiawase_d_cd());
		// （call_category）カテゴリ名（大） API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_toiawase_d_mei(ccsInAndOut.getCcsIn().getQ_toiawase_d_mei());
		// （call_category）問合せカテゴリコード（小） API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_toiawase_s_cd(ccsInAndOut.getCcsIn().getQ_toiawase_s_cd());
		// （call_category）カテゴリ名（小） API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_toiawase_s_mei(ccsInAndOut.getCcsIn().getQ_toiawase_s_mei());
		// 問題詳細_ロング API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_toiawase_naiyou(ccsInAndOut.getCcsIn().getQ_toiawase_naiyou());
		// 問合せ日時/登録日時 API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_toiawase_nichiji(ccsInAndOut.getCcsIn().getQ_toiawase_nichiji());
		// 優先度 API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_juuyoudo(ccsInAndOut.getCcsIn().getQ_juuyoudo());
		// クレームフラグ（1:あり, 1以外：なし） API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_cream(ccsInAndOut.getCcsIn().getQ_cream());
		// リクエストフラグ（1:あり, 1以外：なし） API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_request(ccsInAndOut.getCcsIn().getQ_request());
		// 受付種別 API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_houkou(ccsInAndOut.getCcsIn().getQ_houkou());
		// ステータス1 API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_taiou_sts(ccsInAndOut.getCcsIn().getQ_taiou_sts());
		// 登録日時 API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_touroku_nichiji(ccsInAndOut.getCcsIn().getQ_touroku_nichiji());
		// 更新日時 API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_henkou_nichiji(ccsInAndOut.getCcsIn().getQ_henkou_nichiji());
		// 訪問日 API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_houmonbi(ccsInAndOut.getCcsIn().getQ_houmonbi());
		// 次回訪問予定日 API001の同リクエスト項目と同じ値をセット
		p_doIn.setQ_next_houmonbi(ccsInAndOut.getCcsIn().getQ_next_houmonbi());
		/* "2"(問合せ修正) の場合、以降の項目は未設定 */
		if (!ProcessId.Q_UPD.getId().equals(operationType)) {
			// サブコール番号 API001の同リクエスト項目と同じ値をセット
			p_doIn.setA_toiawase_no(ccsInAndOut.getCcsIn().getA_toiawase_no());
			// 回答NO 処理区分 "4"(問合せ変更+回答入力)の場合: API001.回答NO 上記以外の場合: API001の同リクエスト項目と同じ値をセット
			p_doIn.setA_kaitou_no(StringUtils.equals(operationType, "4") ? ccsInAndOut.getCcsOut().getRtn_kaitou_no()
					: ccsInAndOut.getCcsIn().getA_kaitou_no());
			// tb_answerユーザID/登録担当者番号 API001の同リクエスト項目と同じ値をセット
			p_doIn.setA_user_id(ccsInAndOut.getCcsIn().getA_user_id());
			// 更新担当者番号 API001の同リクエスト項目と同じ値をセット
			p_doIn.setA_kosin_user_id(ccsInAndOut.getCcsIn().getA_kosin_user_id());
			// 回答詳細_ロング API001の同リクエスト項目と同じ値をセット
			p_doIn.setA_kaitou_naiyou(ccsInAndOut.getCcsIn().getA_kaitou_naiyou());
			// 更新日時/最終回答日時/登録日時 API001の同リクエスト項目と同じ値をセット
			p_doIn.setA_kaitou_nichiji(ccsInAndOut.getCcsIn().getA_kaitou_nichiji());
			// tb_answer登録日時/登録日時 API001の同リクエスト項目と同じ値をセット
			p_doIn.setA_touroku_nichiji(ccsInAndOut.getCcsIn().getA_touroku_nichiji());
			// 更新日時 API001の同リクエスト項目と同じ値をセット
			p_doIn.setA_henkou_nichiji(ccsInAndOut.getCcsIn().getA_henkou_nichiji());
		}
		/* FastHelp問合せデータ登録APIの呼び出し */
		p_req.setParameter(p_doIn);
		return p_doIn;
	}
}
