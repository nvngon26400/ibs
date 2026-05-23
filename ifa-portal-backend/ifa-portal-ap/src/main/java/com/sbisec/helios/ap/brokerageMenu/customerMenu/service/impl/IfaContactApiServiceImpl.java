/**
 * IFA接触履歴APIサービス実装クラス
 * FastHelpへのAPI連携処理を担当するサービスクラス
 * 
 * @author lianhua.xia
 * @date 2025-01-22
 */
package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaContactConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaContactCorrectDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql009RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSqlCommonRequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.IfaContactInputUtil;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.IfaContactInputUtil.ClassName;
import com.sbisec.helios.ap.fasthelp.enums.RtnCd;
import com.sbisec.helios.ap.fasthelp.protocol.fasthelp.GetFasthelpCcsCallsImportReq;
import com.sbisec.helios.ap.fasthelp.service.FastHelp2CcsCallsImportService;
import com.sbisec.helios.ap.fasthelp.service.dto.BaseDtoIn;
import com.sbisec.helios.ap.fasthelp.service.dto.fasthelp.FasthelpCcsCallsImportIn;
import com.sbisec.helios.ap.fasthelp.service.dto.fasthelp.FasthelpCcsCallsImportOut;

@Service
public class IfaContactApiServiceImpl {

	/** ロガー */
	private static final Logger LOGGER = LoggerFactory.getLogger(IfaContactApiServiceImpl.class);

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

	/** FastHelpサービス */
	@Autowired
	private FastHelp2CcsCallsImportService g_fhService;

	/**
	 * API002 IFA問合せ、回答情報をFastHelpに非同期で連携する
	 * 
	 * @param p_doInList       FastHelpに送信するデータリスト
	 * @param x_sysDt          システム日時
	 * @param requestModelType リクエストモデルタイプ ("confirm" または "correct")
	 */
	@Async("taskExecutor")
	public void callFastHelpApi(List<FasthelpCcsCallsImportIn> p_doInList, String x_sysDt, String requestModelType) {
		LOGGER.info("FastHelp API連携開始");

		for (int i = 0; i < p_doInList.size(); i++) {
			FasthelpCcsCallsImportIn currentItem = p_doInList.get(i);
			try {
				GetFasthelpCcsCallsImportReq p_req = new GetFasthelpCcsCallsImportReq();
				/* FastHelp問合せデータ登録APIの呼び出し */
				p_req.setParameter(currentItem);
				FasthelpCcsCallsImportOut p_api002Out = g_fhService.getFasthelpCcsCallsImport(p_req)
						.getFasthelpCcsCallsImportOut();
				// APIエラー発生時：エラー内容をIFA問合せ・回答エラーテーブルに登録する
				if (!RtnCd.OK.getId().equals(p_api002Out.getRtn_cd())/* true */) {
					// 本次と残りの未送信データをループしてエラーテーブルに登録
					for (int j = i; j < p_doInList.size(); j++) {
						this.recordApiError(p_doInList.get(j), x_sysDt, IfaContactInputUtil.ApiFlg.FASTHELP.key,
								requestModelType, STATUS_API_ERROR);
					}
					break; // 後続データの送信を停止
				}
			} catch (Exception e) {
				// Exception発生時：エラー内容をIFA問合せ・回答エラーテーブルに登録する
				// 本次と残りの未送信データをループしてエラーテーブルに登録
				for (int j = i; j < p_doInList.size(); j++) {
					this.recordApiError(p_doInList.get(j), x_sysDt, IfaContactInputUtil.ApiFlg.FASTHELP.key,
							requestModelType, STATUS_EXP_ERROR);
				}
				break; // 後続データの送信を停止
			}
		}
		LOGGER.info("FastHelp API連携終了");
	}

	/**
	 * APIエラーをIFA問合わせ・回答エラーテーブルに記録する
	 *
	 * @param x_apiIn          BaseDtoIn(API設定値)
	 * @param x_sysDt          システム日時
	 * @param apiFlg           APIフラグ
	 * @param requestModelType リクエストモデルタイプ ("confirm" または "correct")
	 * @param errCnt           エラーカテゴリ (null:CCS, 0:FH-APIエラー, 1:FH-Exceptionエラー)
	 */
	public void recordApiError(BaseDtoIn x_apiIn, String x_sysDt, String apiFlg, String requestModelType,
			Integer errCnt) {

		if (StringUtils.equals(requestModelType, ClassName.CONFIRM.key)) {
			IfaContactConfirmSql009RequestModel confirmModel = new IfaContactConfirmSql009RequestModel();
			setErrorFields(confirmModel, x_apiIn, x_sysDt, apiFlg, errCnt);
			try {
				confirmDao.insertIfaContactConfirmSql009(confirmModel);
				LOGGER.info("問合せエラーテーブルへの登録成功");
			} catch (Exception e) {
				LOGGER.warn("SQL009.IFA問合せ・回答エラーテーブル登録失敗");
			}
		} else if (StringUtils.equals(requestModelType, ClassName.CORRECT.key)) {
			IfaContactCorrectSql008RequestModel correctModel = new IfaContactCorrectSql008RequestModel();
			setErrorFields(correctModel, x_apiIn, x_sysDt, apiFlg, errCnt);
			try {
				correctDao.insertIfaContactCorrectSql008(correctModel);
				LOGGER.info("修正エラーテーブルへの登録成功");
			} catch (Exception e) {
				LOGGER.warn("SQL008.IFA問合せ・回答エラーテーブル登録失敗");
			}
		}
	}

	/**
	 * APIエラーをIFA問合せ・回答エラーテーブルに記録する（デフォルトエラーカテゴリ）
	 *
	 * @param x_apiIn          BaseDtoIn(API設定値)
	 * @param x_sysDt          システム日時
	 * @param apiFlg           APIフラグ
	 * @param requestModelType リクエストモデルタイプ ("confirm" または "correct")
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void recordCCSApiError(BaseDtoIn x_apiIn, String x_sysDt, String apiFlg, String requestModelType,
			Integer errCnt) {
		LOGGER.info("デフォルトエラーカテゴリでAPIエラー記録");
		this.recordApiError(x_apiIn, x_sysDt, apiFlg, requestModelType, errCnt);
	}

	/**
	 * エラーレコードの共通フィールドを設定する
	 *
	 * @param commonModel モデルオブジェクト
	 * @param x_apiIn     BaseDtoIn(API設定値)
	 * @param x_sysDt     システム日時
	 * @param apiFlg      APIフラグ
	 * @param errCnt      エラーカテゴリ (null:正常, 0:APIエラー, 1:例外エラー)
	 */
	private void setErrorFields(IfaContactSqlCommonRequestModel commonModel, BaseDtoIn x_apiIn, String x_sysDt,
			String apiFlg, Integer errCnt) {
		LOGGER.debug("エラーフィールド設定開始");

		// 処理区分 API設定値.処理区分
		commonModel.setProcessId(x_apiIn.getProcess_id());
		// 問合せNO API設定値.問合せNO
		commonModel.setQToiawaseNo(x_apiIn.getQ_toiawase_no());
		// 顧客ID API設定値.顧客ID
		commonModel.setQKokyakuId(x_apiIn.getQ_kokyaku_id());
		// ユーザＩＤ API設定値.ユーザＩＤ
		commonModel.setQUserId(x_apiIn.getQ_user_id());
		// 更新ユーザID API設定値.更新ユーザID
		commonModel.setQKosinUserId(x_apiIn.getQ_kosin_user_id());
		// 問合せカテゴリコード（中） API設定値.問合せカテゴリコード（中）
		commonModel.setQToiawaseCd(x_apiIn.getQ_toiawase_cd());
		// カテゴリー名称（中） API設定値.カテゴリー名称（中）
		commonModel.setQToiawaseMei(x_apiIn.getQ_toiawase_mei());
		// 問合せカテゴリコード（大） API設定値.問合せカテゴリコード（大）
		commonModel.setQToiawaseDCd(x_apiIn.getQ_toiawase_d_cd());
		// カテゴリー名称（大） API設定値.カテゴリー名称（大）
		commonModel.setQToiawaseDMei(x_apiIn.getQ_toiawase_d_mei());
		// 問合せカテゴリコード（小） API設定値.問合せカテゴリコード（小）
		commonModel.setQToiawaseSCd(x_apiIn.getQ_toiawase_s_cd());
		// カテゴリー名称（小） API設定値.カテゴリー名称（小）
		commonModel.setQToiawaseSMei(x_apiIn.getQ_toiawase_s_mei());
		// 問合せ内容 API設定値.問合せ内容
		commonModel.setQToiawaseNaiyou(x_apiIn.getQ_toiawase_naiyou());
		// 問合せ日時 API設定値.問合せ日時
		commonModel.setQToiawaseNichiji(x_apiIn.getQ_toiawase_nichiji());
		// 重要度 API設定値.重要度
		commonModel.setQJuuyoudo(x_apiIn.getQ_juuyoudo());
		// クレーム API設定値.クレーム
		commonModel.setQCream(x_apiIn.getQ_cream());
		// リクエスト API設定値.リクエスト
		commonModel.setQRequest(x_apiIn.getQ_request());
		// 方向 API設定値.方向
		commonModel.setQHoukou(x_apiIn.getQ_houkou());
		// 対応ステータス API設定値.対応ステータス
		commonModel.setQTaiouSts(x_apiIn.getQ_taiou_sts());
		// 訪問日 API設定値.訪問日
		commonModel.setQHoumonbi(x_apiIn.getQ_houmonbi());
		// 次回訪問予定日 API設定値.次回訪問予定日
		commonModel.setQNextHoumonbi(x_apiIn.getQ_next_houmonbi());
		// 問合せ登録日時 API設定値.問合せ登録日時
		commonModel.setQTourokuNichiji(x_apiIn.getQ_touroku_nichiji());
		// 問合せ変更日時 API設定値.問合せ変更日時
		commonModel.setQHenkouNichiji(x_apiIn.getQ_henkou_nichiji());
		// 問合せNO API設定値.問合せNO
		commonModel.setAToiawaseNo(x_apiIn.getA_toiawase_no());
		// 回答NO API設定値.回答NO
		commonModel.setAKaitouNo(x_apiIn.getA_kaitou_no());
		// ユーザＩＤ API設定値.ユーザＩＤ
		commonModel.setAUserId(x_apiIn.getA_user_id());
		// 更新ユーザID API設定値.更新ユーザID
		commonModel.setAKosinUserId(x_apiIn.getA_kosin_user_id());
		// 回答内容 API設定値.回答内容
		commonModel.setAKaitouNaiyou(x_apiIn.getA_kaitou_naiyou());
		// 回答日時 API設定値.回答日時
		commonModel.setAKaitouNichiji(x_apiIn.getA_kaitou_nichiji());
		// 回答登録日時 API設定値.回答登録日時
		commonModel.setATourokuNichiji(x_apiIn.getA_touroku_nichiji());
		// 回答変更日時 API設定値.回答変更日時
		commonModel.setAHenkouNichiji(x_apiIn.getA_henkou_nichiji());
		// 登録日時 システム日時
		commonModel.setTourokuNichiji(x_sysDt);
		// 変更日時 システム日時
		commonModel.setHenkouNichiji(x_sysDt);
		// 削除日時 システム日時
		commonModel.setSakujoNichiji(StringUtil.EMPTY_STRING);
		// 処理フラグ 0
		commonModel.setSyoriFlg("0");
		// APIフラグ API002の場合:'1'
		commonModel.setApiFlg(apiFlg);
		// APIエラー、Exception発生時：Exception回数とAPIエラー回数を設定する
		if (errCnt == STATUS_API_ERROR) {
			// APIエラー発生時 Exception回数：0 APIエラー回数：1
			commonModel.setApiErrCnt("1");
			commonModel.setExpErrCnt("0");
		} else if (errCnt == STATUS_EXP_ERROR) {
			// Exception発生時 Exception回数：1 APIエラー回数：0
			commonModel.setApiErrCnt("0");
			commonModel.setExpErrCnt("1");
		}
		LOGGER.debug("エラーフィールド設定完了");
	}
}
