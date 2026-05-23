package com.sbisec.helios.ap.bizcommon.component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbisec.helios.ap.bizcommon.dao.Fct041Dao;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct041Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct041Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.model.BaseOutputDto;
import com.sbisec.helios.ap.bizcommon.model.InputFct041Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct041Dto;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

/**
 * 共通関数：FCT041
 * 共同募集権限情報取得
 * 2024/12/12 新規作成
 *
 * @author 大連 王永宝
 */
@Component
public class Fct041 {

	@Autowired
	private Fct041Dao dao;

	private static final Logger LOGGER = LoggerFactory.getLogger(Fct041.class);

	/** 自動設定 */
	enum AutoSet {
		// 自動設定あり
		AUTOSETOK("1"),
		// 自動設定なし
		AUTOSETNO("0");

		private String key;

		private AutoSet(String key) {
			this.key = key;
		}
	}

	/**
	 * ユーザー権限情報取得
	 *
	 * @param input リクエストDto
	 * @return レスポンスDto
	 * @throws Exception ユーザー権限情報取得時に例外が発生した場合
	 */
	public OutputFct041Dto getData(InputFct041Dto x_input) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Fct041.getData");
		}

		OutputFct041Dto p_resDto = new OutputFct041Dto();
		// ユーザ共通情報
		UserAccount p_ua = IfaCommonUtil.getUserAccount();
		// ユーザ共通情報.権限コード
		String p_validId = p_ua.getPrivId();

		// "1" ：SBI証券本店 または
		// "2" ：SBI証券支店
		// のリスト
		Set<String> p_priv12 = new HashSet<>(Arrays.asList("1", "2"));
		// "3" ：仲介業者 - 内部管理責任者 または
		// "4" ：仲介業者 - 営業責任者 または
		// "5" ：仲介業者 - 外務員
		// のリスト
		Set<String> p_priv345 = new HashSet<>(Arrays.asList("3", "4", "5"));
		// "6" ：仲介業者 - 支店 - 内部管理責任者 または
		// "7" ：仲介業者 - 支店 - 営業責任者 または
		// "8" ：仲介業者 - 支店 - 外務員
		// のリスト
		Set<String> p_priv678 = new HashSet<>(Arrays.asList("6", "7", "8"));
		// "9" ：営業員担当
		// のリスト
		Set<String> p_priv9 = new HashSet<>(Arrays.asList("9"));

		// ユーザ共通情報.権限コードが
		// "1" ：SBI証券本店 または
		// "2" ：SBI証券支店 または
		// "3" ：仲介業者 - 内部管理責任者 の場合
		if (p_priv12.contains(p_validId)) {
			// 共募支店コード＝0：自動設定なし
			p_resDto.setJointBranchCodeSettingJudge(AutoSet.AUTOSETNO.key);
			// 営業員コード＝0：自動設定なし
			p_resDto.setEmpCodeCodeSettingJudge(AutoSet.AUTOSETNO.key);
		// ユーザ共通情報.権限コードが
		// "3" ：仲介業者 - 内部管理責任者 または
		// "4" ：仲介業者 - 営業責任者 または
		// "5" ：仲介業者 - 外務員 の場合
		} else if (p_priv345.contains(p_validId)) {
			// 共募支店コード＝0：自動設定なし
			p_resDto.setJointBranchCodeSettingJudge(AutoSet.AUTOSETNO.key);
			// 営業員コード＝0：自動設定なし
			p_resDto.setEmpCodeCodeSettingJudge(AutoSet.AUTOSETNO.key);
		// ユーザ共通情報.権限コードが
		// "6" ：仲介業者 - 支店 - 内部管理責任者 または
		// "7" ：仲介業者 - 支店 - 営業責任者 または
		// "8" ：仲介業者 - 支店 - 外務員 の場合
		} else if (p_priv678.contains(p_validId)) {
			// ログインユーザ自分共募支店コード以外の他共募支店コードが有りません
			if (isAutoSetJointBranchCode(p_ua)) {
				// 共募支店コード＝1：自動設定あり
				p_resDto.setJointBranchCodeSettingJudge(AutoSet.AUTOSETOK.key);
			// ログインユーザ自分共募支店コード以外の他共募支店コードが有り
			} else {
				// 共募支店コード＝0：自動設定なし
				p_resDto.setJointBranchCodeSettingJudge(AutoSet.AUTOSETNO.key);
			}
			// 営業員コード＝0：自動設定なし
			p_resDto.setEmpCodeCodeSettingJudge(AutoSet.AUTOSETNO.key);
		// ユーザ共通情報.権限コードが
		// "9" ：営業員担当 の場合
		} else if (p_priv9.contains(p_validId)) {
			if (isAutoSetJointBranchCode(p_ua)) {
				// 共募支店コード＝0：自動設定なし
				p_resDto.setJointBranchCodeSettingJudge(AutoSet.AUTOSETOK.key);
			} else {
				// 共募支店コード＝0：自動設定なし
				p_resDto.setJointBranchCodeSettingJudge(AutoSet.AUTOSETNO.key);
			}
			// 営業員コード＝0：自動設定なし
			p_resDto.setEmpCodeCodeSettingJudge(AutoSet.AUTOSETNO.key);
		// その以外 の場合
		} else {
			// 返却コード(E001)をレスポンスに設定。
			p_resDto.setReturnCode(BaseOutputDto.RETURN_CODE_E001);
		}
		// return レスポンス
		return p_resDto;
	}

	/**
	 * 共募支店コード自動設定するかどうか判定
	 * @param x_ua UserAccount
	 * @return 共募支店コード自動設定あり(ログインユーザ自分共募支店コード以外の他共募支店コードが有りません)の場合、true
	 */
	private boolean isAutoSetJointBranchCode(UserAccount x_ua) {

		boolean p_rtn = false;
		// SQL001リクエストパラメータ 設定元
		// ユーザーID ユーザ共通情報.ユーザーID
		// 仲介業者コード ユーザ共通情報.仲介業者コード
		Fct041Sql001RequestModel p_sql001Req = new Fct041Sql001RequestModel();
		p_sql001Req.setUserId(x_ua.getUserId());
		p_sql001Req.setBrokerId(x_ua.getBrokerId());
		p_sql001Req.setSubBrokerId(x_ua.getSubBrokerId());
		// ユーザ権限情報取得（仲介業者支店）を呼び出す(SQL001)
		Fct041Sql001ResponseModel sql001Res = new Fct041Sql001ResponseModel();
		try {
			sql001Res = dao.getUserAuthorityBranch(p_sql001Req);
		} catch (Exception e) {
			LOGGER.error("Fct041.getData Fct032DaoImpl.getUserAuthorityBranch Exception[{}]", e.getMessage());
		}
		// SQL001.取得件数<1の場合(ログインユーザ自分共募支店コード以外の他共募支店コードが有りません)
		if (sql001Res.getCount() < 1) {
			p_rtn = true;
		}
		return p_rtn;
	}
}
