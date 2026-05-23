package com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.controller;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoUpdateA001DtoRequest;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoUpdateA001DtoResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaInfoUpdateA001ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaInfoUpdateA001ApiResponse;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoUpdateA002DtoRequest;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoUpdateA002DtoResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaInfoUpdateA002ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaInfoUpdateA002ApiResponse;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoUpdateA008aDtoRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaInfoUpdateA008aApiResponse;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoUpdateA008bDtoRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaInfoUpdateA008bApiRequest;
import org.springframework.web.multipart.MultipartFile;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.util.FileUploadUtil;

/**
 * 画面ID：SUB0501_01-03_1
 * 画面名：情報更新
 *
 * @author SCSK 大崎
 2024/05/23 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN05", id = "SUB0501_01-03_1", screenNumber = "")
public class IfaInfoUpdateController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    private static final Logger logger = LoggerFactory.getLogger(IfaInfoUpdateController.class);
    
    /**
     * アクセス：/companyEmployeeMenu/infoRegister/ifaInfoUpdateInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaInfoUpdateA001ApiRequest
     * Apiレスポンス：IfaInfoUpdateA001ApiResponse
     * Dtoリクエスト：IfaInfoUpdateA001DtoRequest
     * Dtoレスポンス：IfaInfoUpdateA001DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/companyEmployeeMenu/infoRegister/ifaInfoUpdateInitializeA001")
    public String initializeA001(@RequestBody IfaInfoUpdateA001ApiRequest apiReq) throws Exception {

        IfaInfoUpdateA001DtoRequest appReq = new IfaInfoUpdateA001DtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaInfoUpdateA001DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaInfoUpdateService",
                "initializeA001", new TypeReference<DataList<IfaInfoUpdateA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaInfoUpdateA001ApiResponse> apiRes = new DataList<IfaInfoUpdateA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/companyEmployeeMenu/infoRegister/ifaInfoUpdateResetA002
     * アクションID：A002
     * アクション名：リセット
     * APIリクエスト：IfaInfoUpdateA002ApiRequest
     * Apiレスポンス：IfaInfoUpdateA002ApiResponse
     * Dtoリクエスト：IfaInfoUpdateA002DtoRequest
     * Dtoレスポンス：IfaInfoUpdateA002DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/companyEmployeeMenu/infoRegister/ifaInfoUpdateResetA002")
    public String resetA002(@RequestBody IfaInfoUpdateA002ApiRequest apiReq) throws Exception {

        IfaInfoUpdateA002DtoRequest appReq = new IfaInfoUpdateA002DtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaInfoUpdateA002DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaInfoUpdateService",
                "resetA002", new TypeReference<DataList<IfaInfoUpdateA002DtoResponse>>() {
                }, appReq);
        
        DataList<IfaInfoUpdateA002ApiResponse> apiRes = new DataList<IfaInfoUpdateA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/companyEmployeeMenu/infoRegister/ifaInfoUpdateUpdateA008a
     * アクションID：A008a
     * アクション名：更新
     * APIリクエスト：IfaInfoUpdateA008aApiRequest
     * Apiレスポンス：IfaInfoUpdateA008aApiResponse
     * Dtoリクエスト：IfaInfoUpdateA008aDtoRequest
     * Dtoレスポンス：IfaInfoUpdateA008aDtoResponse
     *
     * @param file1 {@code MultipartFile }
     * @param file2 {@code MultipartFile }
     * @param file3 {@code MultipartFile }
     * @return {@code String}
     * @exception exception システムエラー
     */
    @PostMapping(value = "/companyEmployeeMenu/infoRegister/ifaInfoUpdateUpdateA008a")
    public String updateA008a(@RequestPart(value = "registerFile1", required = false) MultipartFile file1,
            @RequestPart(value = "registerFile2", required = false) MultipartFile file2,
            @RequestPart(value = "registerFile3", required = false) MultipartFile file3) throws Exception {

        IfaInfoUpdateA008aApiResponse res = new IfaInfoUpdateA008aApiResponse(); 

        // --------------------------------
        // ファイルアップロード
        // --------------------------------
        // アップロード先のファイルディレクトリの取得
        DataList<String> fileDirectoryDataList = ApiRequestUtil.invoke("cmpIfaInfoUpdateService",
                "updateA008a", new TypeReference<DataList<String>>() {
                }, new IfaInfoUpdateA008aDtoRequest());
        
        String fileDirectory = fileDirectoryDataList.getDataList().get(0);
        res.setFileDirectory(fileDirectory);
        Path fileDirectoryPath = Paths.get(fileDirectory);

        //顧客共通情報
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        String userId = IfaCommonUtil.getUserAccount().getUserId();

        try {
            FileUploadUtil.saveAndRename(fileDirectoryPath, userId, file1) //
                    .ifPresent(p -> res.setRegisterFileName1(p.getFileName().toString()));
            FileUploadUtil.saveAndRename(fileDirectoryPath, userId, file2) //
                    .ifPresent(p -> res.setRegisterFileName2(p.getFileName().toString()));
            FileUploadUtil.saveAndRename(fileDirectoryPath, userId, file3) //
                    .ifPresent(p -> res.setRegisterFileName3(p.getFileName().toString()));
            
            var resDs = IfaCommonUtil.createDataList(List.of(res), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(),
                    null);
            return jc.toString(resDs);
        } catch (IOException e) {
            logger.info("Exception occured.", e);
            return jc.toString(IfaCommonUtil.createDataList(null, ErrorLevel.SUCCESS, "errors.processingFailed", null));
        }
    }

    /**
     * アクセス：/companyEmployeeMenu/infoRegister/ifaInfoUpdateUpdateA008b
     * アクションID：A008b
     * アクション名：更新
     * APIリクエスト：IfaInfoUpdateA008bApiRequest
     * Apiレスポンス：IfaInfoUpdateA008bApiResponse
     * Dtoリクエスト：IfaInfoUpdateA008bDtoRequest
     * Dtoレスポンス：IfaInfoUpdateA008bDtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/companyEmployeeMenu/infoRegister/ifaInfoUpdateUpdateA008b")
    public String updateA008b(@RequestBody IfaInfoUpdateA008bApiRequest apiReq) throws Exception {

        IfaInfoUpdateA008bDtoRequest appReq = new IfaInfoUpdateA008bDtoRequest();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        // --------------------------------
        // ファイル削除
        // --------------------------------

        // 登録ファイル削除フラグ
        List<String> fileDeleteFlgList = new ArrayList<>(); 
        fileDeleteFlgList.add("0");
        fileDeleteFlgList.add("0");
        fileDeleteFlgList.add("0");
        
        // 登録ファイル（削除）が空の場合、もしくは登録ファイル（ファイル名）が空でない場合、登録ファイル削除フラグを"1"に設定
        if (StringUtil.isNullOrEmpty(apiReq.getFileDelete1()) || !StringUtil.isNullOrEmpty(apiReq.getRegisterFileName1())) {
            fileDeleteFlgList.set(0, "1");
        }
        if (StringUtil.isNullOrEmpty(apiReq.getFileDelete2()) || !StringUtil.isNullOrEmpty(apiReq.getRegisterFileName2())) {
            fileDeleteFlgList.set(1, "1");
        }
        if (StringUtil.isNullOrEmpty(apiReq.getFileDelete3()) || !StringUtil.isNullOrEmpty(apiReq.getRegisterFileName3())) {
            fileDeleteFlgList.set(2, "1");
        }
        
        // A008.ファイルディレクトリが空の場合、ファイルディレクトリパスを取得する
        String fileDirectory = apiReq.getFileDirectory();
        if (StringUtil.isNullOrEmpty(fileDirectory)) {
            DataList<String> fileDirectoryDataList = ApiRequestUtil.invoke("cmpIfaInfoUpdateService",
                    "updateA008a", new TypeReference<DataList<String>>() {
                    }, new IfaInfoUpdateA008aDtoRequest());

            if (fileDirectoryDataList.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
                return jc.toString(fileDirectoryDataList);
            }
            
            fileDirectory = fileDirectoryDataList.getDataList().get(0);
        }

        DataList<String> attachFileRes = ApiRequestUtil.invoke("cmpIfaInfoUpdateService",
                "getAttachFileA008b", new TypeReference<DataList<String>>() {
                }, appReq.getNotificationId()); 
        
        if (attachFileRes.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            return jc.toString(attachFileRes);
        }

        List<String> attachFileList = attachFileRes.getDataList();
        // 登録ファイルが存在するかつ、登録ファイル削除フラグが'1'の場合、登録ファイルを削除する。
        if (attachFileList != null && attachFileList.size() >= 3) {
            for (int i = 0; i < 3; i++) {
                if (!StringUtil.isNullOrEmpty(attachFileList.get(i)) && "1".equals(fileDeleteFlgList.get(i))) {
                    deleteFromFileSyst(fileDirectory, attachFileList.get(i));
                }
            }
        }

        // テーブルデータ更新
        DataList<String> updateA008bRes = ApiRequestUtil.invoke("cmpIfaInfoUpdateService",
                "updateA008b", new TypeReference<DataList<String>>() {
                }, appReq); 
        
        if (updateA008bRes.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            return jc.toString(updateA008bRes);
        }

        return jc.toString(IfaCommonUtil.createDataList(null, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null));
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }

    /**
    * 元のファイルを削除する
    *
    * @param filePath {@code String }
    * @param fileName {@code String }
    * @return 成功:True,失敗:False {@code Boolean}
    * @throws Exception ファイル操作処理で例外が発生した場合
    */
    private Boolean deleteFromFileSyst(String filePath, String fileName) {
        try {
            File f = new File(filePath + fileName);
            // ファイルが存在すれば削除
            if (f.exists() && !f.isDirectory()) {
                if (f.delete()) {
                    return true;
                }
            }
        } catch (Exception e) {
            logger.error("Could not delete the file " + fileName + "from the server");
            throw e;
        }
        return false;
    }
}

