package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.List;
import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql008ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactConfirmA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactCorrectA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.IfaContactApiException;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.IfaContactInputUtil;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.IfaContactInputUtil.ClassName;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.fasthelp.enums.RtnCd;
import com.sbisec.helios.ap.fasthelp.service.dto.ccs.CcsInAndOut;

/**
 * IFA接触履歴トランザクション処理サービス実装クラス
 * <p>
 * IFA接触履歴の登録・更新処理におけるトランザクション管理を担当するサービスクラス。
 * SQL操作とCCS API呼び出しを1つのトランザクションで管理し、API呼び出し失敗時にはロールバックを行う。
 * </p>
 * 
 * @author lianhua.xia
 * @date 2025-01-22
 */
@Component
public class IfaContactTransactionServiceImpl {

	/** ロガー */
	private static final Logger LOGGER = LoggerFactory.getLogger(IfaContactTransactionServiceImpl.class);

	/** IFA接触入力ユーティリティ */
	@Autowired
	private IfaContactInputUtil g_util;

	/** IFA接触共通サービス */
	@Autowired
	private IfaContactCommonServiceImpl commonService;

	/**
	 * IFA接触履歴登録トランザクション処理（新規登録用）
	 * <p>
	 * 新規登録時のSQL操作（SQL002、SQL003、SQL004）とCCS API呼び出しを
	 * 1つのトランザクションで管理する。CCS API呼び出しが失敗した場合は全てのSQL操作をロールバックする。
	 * </p>
	 *
	 * @param x_dtoReq        IFA接触確認リクエストDTO
	 * @param p_sql008Res     SQL008実行結果（顧客情報）
	 * @param p_procId        処理ID
	 * @param toiawaseNaiyou0 問い合わせ内容（先頭782バイト）
	 * @param content         回答内容
	 * @param p_sysDt         システム日時
	 * @param toiawaseNo      問い合わせ番号
	 * @param callSql002      SQL002（問い合わせ内容登録）実行フラグ
	 * @param callSql003      SQL003（顧客接触情報登録）実行フラグ
	 * @param callSql004      SQL004（回答内容登録）実行フラグ
	 * @param tourokuGroup    登録グループ（0:初回登録、1:2件目以降）
	 * @param i               登録順序インデックス（時刻調整用）
	 * @return CCS API応答情報（{@link CcsInAndOut}）
	 * @throws IfaContactApiException SQL実行失敗またはCCS API呼び出し失敗時
	 */
	@Transactional(rollbackFor = Exception.class)
	public CcsInAndOut processInTransaction(IfaContactConfirmA002RequestDto x_dtoReq,
			IfaContactConfirmSql008ResponseModel p_sql008Res, String p_procId, String toiawaseNaiyou0, String content,
			String p_sysDt, String toiawaseNo, boolean callSql002, boolean callSql003, boolean callSql004,
			String tourokuGroup, int i) throws IfaContactApiException {

		if (LOGGER.isDebugEnabled())
			LOGGER.debug("IfaContactConfirmServiceImpl.Transactional");

		// SQL002: 問い合わせ内容登録処理
		if (callSql002) {
			if (!commonService.execSql002Confirm(x_dtoReq, toiawaseNaiyou0, p_sysDt)) {
				throw new IfaContactApiException(ClassName.EXECSQL002.key);
			}
		}

		// SQL003: 問い合わせ内容更新処理
		if (callSql003) {
			if (!commonService.execSql003Confirm(x_dtoReq, p_sysDt)) {
				throw new IfaContactApiException(ClassName.EXECSQL003.key);
			}
		}

		// SQL004: 回答内容登録処理
		if (callSql004) {
			if (!commonService.execSql004Confirm(x_dtoReq, content,
					g_util.addOneSecond(p_sysDt, StringUtils.equals(tourokuGroup, "0") ? i + 1 : i), tourokuGroup)) {
				throw new IfaContactApiException(ClassName.EXECSQL004.key);
			}
		}

		CcsInAndOut ccsInAndOut = new CcsInAndOut();
		try {
			ccsInAndOut = commonService.callCCSApiConfirm(x_dtoReq, p_sql008Res, p_procId, toiawaseNaiyou0, content,
					p_sysDt, g_util.addOneSecond(p_sysDt, StringUtils.equals(tourokuGroup, "0") ? i + 1 : i),
					toiawaseNo);

			if (!RtnCd.OK.getId().equals(ccsInAndOut.getCcsOut().getRtn_cd())) {
				throw new IfaContactApiException(ClassName.CALLCCSAPI.key);
			}
		} catch (Exception e) {
			throw new IfaContactApiException(ClassName.CALLCCSAPI.key, e);
		}

		return ccsInAndOut;
	}

	/**
	 * IFA接触履歴更新トランザクション処理（修正用）
	 * <p>
	 * 修正時のSQL操作（SQL004、SQL006、SQL009）とCCS API呼び出しを
	 * 1つのトランザクションで管理する。CCS API呼び出しが失敗した場合は全てのSQL操作をロールバックする。
	 * </p>
	 *
	 * @param x_dtoReq      IFA接触修正リクエストDTO
	 * @param p_sql003Res   SQL003実行結果（問い合わせ内容情報）
	 * @param p_sql005Res   SQL005実行結果（回答内容情報）
	 * @param naiyou0       問い合わせ内容（先頭782バイト）
	 * @param content       回答内容
	 * @param operationType 操作タイプ（1:内容更新、2:回答追加）
	 * @param targetDate    登録対象日時
	 * @param p_sysDt       システム日時
	 * @param callSql004    SQL004（問い合わせ内容更新）実行フラグ
	 * @param callSql006    SQL006（回答内容更新）実行フラグ
	 * @param callSql009    SQL009（回答内容新規登録）実行フラグ
	 * @param tourokuGroup  登録グループ（0:初回登録、1:2件目以降）
	 * @param i             登録順序インデックス（時刻調整用）
	 * @return CCS API応答情報（{@link CcsInAndOut}）
	 * @throws IfaContactApiException SQL実行失敗またはCCS API呼び出し失敗時
	 */
	@Transactional(rollbackFor = Exception.class)
	public CcsInAndOut processInTransactionForCorrect(IfaContactCorrectA003RequestDto x_dtoReq,
			IfaContactCorrectSql003ResponseModel p_sql003Res, IfaContactCorrectSql005ResponseModel p_sql005Res,
			String naiyou0, String content, String operationType, String targetDate, String p_sysDt, boolean callSql004,
			boolean callSql006, boolean callSql009, String tourokuGroup, int i) throws IfaContactApiException {

		if (LOGGER.isDebugEnabled())
			LOGGER.debug("IfaContactCorrectServiceImpl.Transactional");

		// SQL004: 問い合わせ内容更新処理
		if (callSql004) {
			List<String> nayouList = g_util.splitStringByByteLength(x_dtoReq.getNaiyou(), 782);
			String naiyou = nayouList.size() > 0 ? nayouList.get(0) : StringUtil.EMPTY_STRING;
			if (!commonService.execSql004Correct(x_dtoReq, naiyou, p_sysDt)) {
				throw new IfaContactApiException(ClassName.EXECSQL004.key);
			}
			/* 以下は更新項目のセット */
			// 問合せ内容
			p_sql003Res.setToiawaseNaiyou(naiyou);
			// 変更日時
			p_sql003Res.setHenkouNichiji(p_sysDt);
			// 更新ユーザID
			p_sql003Res.setCcsUserId(IfaCommonUtil.getUserAccount().getCcsUserId());
		}

		// SQL006: 回答内容更新処理
		if (callSql006) {
			String ifaKaitouNo = p_sql005Res.getIfaKaitouNo();
			if (!commonService.execSql006Correct(x_dtoReq, ifaKaitouNo, content, p_sysDt)) {
				throw new IfaContactApiException(ClassName.EXECSQL006.key);
			}
			/* 以下は更新項目のセット */
			// 回答内容
			p_sql005Res.setKaitouNaiyou(content);
			// 変更日時
			p_sql005Res.setHenkouNichiji(p_sysDt);
			// 更新ユーザID
			p_sql005Res.setCcsUserId(IfaCommonUtil.getUserAccount().getCcsUserId());
		}

		// SQL009: 回答内容新規登録処理
		if (callSql009) {
			if (!commonService.execSql009Correct(x_dtoReq, content, targetDate, p_sql003Res.getSessyokuKeiro(),
					p_sysDt)) {
				throw new IfaContactApiException(ClassName.EXECSQL009.key);
			}
			/* 以下は挿入項目のセット */
			// 登録日時
			p_sql005Res.setTourokuNichiji(targetDate);
			// 変更日時
			p_sql005Res.setHenkouNichiji(p_sysDt);
			// CCSユーザID
			p_sql005Res.setCcsUserId(IfaCommonUtil.getUserAccount().getCcsUserId());
		}

		CcsInAndOut ccsInAndOut = new CcsInAndOut();
		try {
			ccsInAndOut = commonService.callCCSApiCorrect(x_dtoReq, p_sql003Res, p_sql005Res, naiyou0, content,
					operationType, targetDate, p_sysDt);

			if (!RtnCd.OK.getId().equals(ccsInAndOut.getCcsOut().getRtn_cd())) {
				throw new IfaContactApiException(ClassName.CALLCCSAPI.key);
			}
		} catch (Exception e) {
			throw new IfaContactApiException(ClassName.CALLCCSAPI.key, e);
		}

		return ccsInAndOut;
	}
}
