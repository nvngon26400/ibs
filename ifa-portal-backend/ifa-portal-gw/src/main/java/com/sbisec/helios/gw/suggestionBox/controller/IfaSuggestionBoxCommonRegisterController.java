package com.sbisec.helios.gw.suggestionBox.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.util.FileUploadUtil;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxCommonRegisterSql001ResponseModel;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonRegisterA007bRequestDto;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxCommonRegisterA007aApiResponse;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxCommonRegisterA007bApiRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestPart;

/**
 * 画面ID：SUB0511_02-02
 * 画面名：皆様からの要望新規登録
 *
 2025/06/25 新規作成
 */

@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN05", id = "SUB0511_02-02", screenNumber = "")
public class IfaSuggestionBoxCommonRegisterController extends BaseController {

	private JsonConverter jc = JsonConverter.getInstance();

	private static final Logger LOGGER = LoggerFactory.getLogger(IfaSuggestionBoxCommonRegisterController.class);

	private static final String SUG_BOX_COMMON_CAT_ID = "1";

	/** エラーメッセージ：{0}が失敗しました。 */
	private static final String ERRORS_PROCESSINGFAILED = "errors.processingFailed";

	/** エラーメッセージ引数 */
	private static final String REGISTERED_ERROR = "ファイルのアップロード";

	/**
	 * アクセス：/suggestionBox/ifaSuggestionBoxCommonRegisterRegisterA007a
	 * アクションID：A007a
	 * アクション名：更新
	 * APIリクエスト：ifaSuggestionBoxCommonRegisterA007aApiRequest
	 * APIレスポンス：ifaSuggestionBoxCommonRegisterA007aApiResponse
	 * Dtoリクエスト：共通dto
	 * Dtoレスポンス：共通dto
	 *
	 * @param apiReq リクエスト
	 * @return apiRes レスポンス
	 * @exception Exception システムエラー
	 */
	@PostMapping(value = "/suggestionBox/ifaSuggestionBoxCommonRegisterRegisterA007a")
	public String registA007a(	@RequestPart(value = "attachFile1", required = false) MultipartFile file1,
			@RequestPart(value = "attachFile2", required = false) MultipartFile file2,
			@RequestPart(value = "attachFile3", required = false) MultipartFile file3) throws Exception {


		// ====================================
		// 要望No取得
		// ====================================

		DataList<IfaSuggestionBoxCommonRegisterSql001ResponseModel> sbcNoDataList = ApiRequestUtil.invoke("cmpIfaSuggestionBoxCommonRegisterService",
				"selectA007", new TypeReference<DataList<IfaSuggestionBoxCommonRegisterSql001ResponseModel>>() {
		}); 

		String sbcNo = sbcNoDataList.getDataList().get(0).getSbcNo();

		// --------------------------------
		// ディレクトリパス取得
		// --------------------------------
		// 共通処理の呼び出し
		DataList<String> fileDirectoryDataList = ApiRequestUtil.invoke("ifaSuggestionBoxService",
				"getSugBoxFileDir", new TypeReference<DataList<String>>() {
		}, SUG_BOX_COMMON_CAT_ID);

		if (fileDirectoryDataList.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            return jc.toString(fileDirectoryDataList);
        }

		String fileDirectory = fileDirectoryDataList.getDataList().get(0);

		Path fileDirectoryPath = Paths.get(fileDirectory);

		// --------------------------------
		// ファイルアップロード
		// --------------------------------
		//顧客共通情報
		IfaGwCommonUtil.setCustomerCommonToRequestScope();
		String userId = IfaCommonUtil.getUserAccount().getUserId();

		IfaSuggestionBoxCommonRegisterA007aApiResponse res = new IfaSuggestionBoxCommonRegisterA007aApiResponse(); 

		try {
			FileUploadUtil.saveAndRenameSuggestion(fileDirectoryPath, userId, sbcNo, file1) //
			.ifPresent(p -> res.setRegisterFileName1(p.getFileName().toString()));
			FileUploadUtil.saveAndRenameSuggestion(fileDirectoryPath, userId, sbcNo, file2) //
			.ifPresent(p -> res.setRegisterFileName2(p.getFileName().toString()));
			FileUploadUtil.saveAndRenameSuggestion(fileDirectoryPath, userId, sbcNo, file3) 
			.ifPresent(p -> res.setRegisterFileName3(p.getFileName().toString()));

			res.setSbcNo(sbcNo);

			var resDs = IfaCommonUtil.createDataList(List.of(res), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(),null);

			return jc.toString(resDs);

		} catch (IOException e) {
			LOGGER.info("Exception occured.", e);
			String errorMessage = IfaCommonUtil.getMessage(ERRORS_PROCESSINGFAILED, new String[] {REGISTERED_ERROR});
			return jc.toString(IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL, ERRORS_PROCESSINGFAILED, errorMessage));
		}
	}


	/**
	 * アクセス：/suggestionBox/ifaSuggestionBoxCommonRegisterRegisterA007b
	 * アクションID：A007b
	 * アクション名：更新
	 * APIリクエスト：ifaSuggestionBoxCommonRegisterA007bApiRequest
	 * APIレスポンス：ifaSuggestionBoxCommonRegisterA007bApiResponse
	 * Dtoリクエスト：ifaSuggestionBoxCommonRegisterA007bDtoRequest
	 * Dtoレスポンス：ifaSuggestionBoxCommonRegisterA007bDtoResponse
	 *
	 * @param apiReq リクエスト
	 * @return apiRes レスポンス
	 * @exception exception システムエラー
	 */
	@PostMapping(value = "/suggestionBox/ifaSuggestionBoxCommonRegisterRegisterA007b")
	public String registA007b(@RequestBody IfaSuggestionBoxCommonRegisterA007bApiRequest apiReq) throws Exception {

		IfaSuggestionBoxCommonRegisterA007bRequestDto appReq = new IfaSuggestionBoxCommonRegisterA007bRequestDto();
		// Beanコピー用共通部品。
		BeanUtils.copyProperties(appReq, apiReq);

		// --------------------------------
		// ディレクトリパス取得
		// --------------------------------
		// 共通処理の呼び出し
		DataList<String> fileDirectoryDataList = ApiRequestUtil.invoke("ifaSuggestionBoxService",
				"getSugBoxFileDir", new TypeReference<DataList<String>>() {
		}, SUG_BOX_COMMON_CAT_ID);

		if (fileDirectoryDataList.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
			return jc.toString(fileDirectoryDataList);
		}

		String fileDirectory = fileDirectoryDataList.getDataList().get(0);

		// 要望No(添付ファイル)無しの場合、要望Noを取得しリクエストに設定する。
		if(StringUtil.isNullOrEmpty(apiReq.getSbcNo())) {

			DataList<IfaSuggestionBoxCommonRegisterSql001ResponseModel> sbcNoDataList = ApiRequestUtil.invoke("cmpIfaSuggestionBoxCommonRegisterService",
					"selectA007", new TypeReference<DataList<IfaSuggestionBoxCommonRegisterSql001ResponseModel>>() {
			}); 

			String sbcNo = sbcNoDataList.getDataList().get(0).getSbcNo();
			appReq.setSbcNo(sbcNo);

		}

		// --------------------------------
		// テーブルデータ登録, ファイル削除
		// --------------------------------

		DataList<String> appRes = ApiRequestUtil.invoke("cmpIfaSuggestionBoxCommonRegisterService",
				"registA007b", new TypeReference<DataList<String>>() {
		}, appReq, fileDirectory); 

		if (appRes.getErrorLevel() == ErrorLevel.FATAL.getId()) {
			// 登録済みファイルの削除（ロールバック処理）
			List<String> registerFileNameList = Stream.of(apiReq.getRegisterFileName1(),
					apiReq.getRegisterFileName2(),
					apiReq.getRegisterFileName3())
					.map(registeredAttachFile -> (registeredAttachFile == null || registeredAttachFile.isEmpty()) ? null : registeredAttachFile)
					.collect(Collectors.toList());

			for (int i = 0; i < 3; i++) {
				if (!StringUtil.isNullOrEmpty(registerFileNameList.get(i))) {
					FileUploadUtil.deleteFromFileSyst(fileDirectory, registerFileNameList.get(i));
				}
			}
		}

		return jc.toString(appRes);
	}

	@Override
	protected String getFirstViewName() {

		return null;
	}

}
