package com.sbisec.helios.gw.common.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ResponseJson;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.common.form.RemoveCustomerInfoForm;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * キャッシュ管理コントローラークラス。
 * 
 * @author SCSK宮坂
 */
@RestController
@RequestMapping(value = "/common")
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "COMMON", id = "CacheManage", ignoreCheck = true)
@Slf4j
public class IfaCacheManageController extends BaseController {
	/** デバッグメッセージ（入力パラメータ不正） */
	private static final String DEBUG_MESSAGE_INVALID_INPUT_PARAM = "顧客共通情報削除エラー（入力パラメータ不正） {}={}";
	/** デバッグメッセージ（キャッシュなし） */
	private static final String DEBUG_MESSAGE_CACHE_NOT_FOUND = "顧客共通情報削除エラー（顧客共通情報キャッシュなし）";
	/** デバッグメッセージ（キャッシュ不一致） */
	private static final String DEBUG_MESSAGE_ACCOUNT_MISMATCH = "顧客共通情報削除エラー（キャッシュ不一致） 部店コード={} 口座番号={}";
	/** デバッグメッセージ（その他例外発生） */
	private static final String DEBUG_MESSAGE_DETECTED_EXCEPTION = "顧客共通情報削除エラー（その他例外発生）";

	/** JSONコンバータ */
	private JsonConverter jc = JsonConverter.getInstance();

	/**
	 * 顧客共通情報を削除する。<br>
	 * レスポンスのJSON変換に失敗した場合を除き、発生するエラーはすべて無視され常に正常レスポンスを返す。
	 * 
	 * @param form   顧客共通情報削除入力項目。
	 * @param result 入力項目妥当性チェック結果。
	 * @return JSONレスポンス。
	 * @throws Exception レスポンスのJSON変換に失敗した場合。
	 */
	@PostMapping("/ifaCacheManageRemoveCustomerInfo")
	@ResponseJson
	public String handleRemoveCustomerInfo(@RequestBody @Validated RemoveCustomerInfoForm form, BindingResult result) throws Exception {
		try {
			// デバッグモードの場合のみチェックしてデバッグログ出力
			if (log.isDebugEnabled()) {
				if (!result.hasErrors()) {
					// 顧客共通情報を取得
					CustomerCommon customerCommon = IfaGwCommonUtil.getCustomerCommon();

					if (customerCommon != null) {
						// 顧客共通情報が指定された部店コード・口座番号の情報か判定
						if (!form.getButenCode().equals(customerCommon.getButenCode()) ||
								!form.getAccountNumber().equals(customerCommon.getAccountNumber())) {
							log.debug(DEBUG_MESSAGE_ACCOUNT_MISMATCH, form.getButenCode(), form.getAccountNumber());
						}
					} else {
						log.debug(DEBUG_MESSAGE_CACHE_NOT_FOUND);
					}
				} else {
					for (FieldError fieldError : result.getFieldErrors()) {
						log.debug(DEBUG_MESSAGE_INVALID_INPUT_PARAM, fieldError.getDefaultMessage(), fieldError.getCode());
					}
				}
			}

			// 顧客共通情報を削除
			IfaGwCommonUtil.evictCustomerCommon();
		} catch (Throwable t) {
			// 発生するすべての例外を無視（実際発生しうるのはRedisが落ちている場合）
			log.debug(DEBUG_MESSAGE_DETECTED_EXCEPTION, t);
		}

		// レスポンスDataListを構築
		DataList<Object> responseDataList = IfaCommonUtil.createDataList(null, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), StringUtils.EMPTY);

		return jc.toString(responseDataList);
	}
}
