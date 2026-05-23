package com.sbisec.helios.ap.systemManageMenu.loginUserManage.service.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.EncryptPassword;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.IfaLoginIdNewRegisterDao;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql002RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql002ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql004RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql004ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql005RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql005ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql006RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql006ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql007RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql007ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql008RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql009RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql010RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql011RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql012RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA001RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA001RequestDto_MenuList;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA001ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA002RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA002ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA003RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA003ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA004RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA004ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA005RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA005ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA006RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA006ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA011RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA011ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterAPIResponseDto_common;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterResponseDto_CommonList;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterResponseDto_DisplayCommon;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.service.IfaLoginIdNewRegisterService;

import lombok.Data;

/**
 * 画面ID：SUB0601_01-02_1
 * 画面名：ログインID新規登録
 * @author 布施佑太
 *
 * 2023/11/09 新規作成
 */
@Component(value = "cmpIfaLoginIdNewRegisterService")
public class IfaLoginIdNewRegisterServiceImpL implements IfaLoginIdNewRegisterService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaLoginIdNewRegisterServiceImpL.class);
    
    /** 利用者権限 1:SBI証券本店 */
    private static final String USER_PERMISSION_SBI = "1";
    
    /** 権限コード 1:本店 */
    private static final String PRIVID_1 = "1";
    
    /** 権限コード 2:支店 */
    private static final String PRIVID_2 = "2";
    
    /** 権限コード 11:業務部 */
    private static final String PRIVID_11 = "11";
    
    /** 権限コード 12:管理部 */
    private static final String PRIVID_12 = "12";
    
    /** 本支店種別 00 */
    private static final String BRANCHCLASS_00 = "00";
    
    /** 本支店種別 01 */
    private static final String BRANCHCLASS_01 = "01";
    
    /** 実行条件 権限コード（1,2,3,4,5） */
    private static final List<String> SEARCH_PRIVID_HEAD_OFFICE = Arrays
            .asList(new String[] { "1", "2", "3", "4", "5" });
    
    /** 実行条件 権限コード（3,4,5） */
    private static final List<String> A004_SEARCH_PRIVID_BROKER = Arrays.asList(new String[] { "3", "4", "5" });
    
    /** 実行条件 権限コード（6,7,8,9） */
    private static final List<String> SEARCH_PRIVID_BROKER = Arrays.asList(new String[] { "6", "7", "8", "9" });
    
    /** チェック結果 権限コード　本店 */
    private static final String CHECK_RESULT_PRIVID_HEAD_OFFICE = "0";
    
    /** チェック結果 権限コード　仲介業者 */
    private static final String CHECK_RESULT_PRIVID_BROKER = "1";
    
    /** 設定後リストサイズ　0 */
    private static final int LIST_SIZE_0 = 0;
    
    /** 検索結果　0件 */
    private static final int SELECT_RESULT_0 = 0;
    
    /** INSERT結果　OK */
    private static final int INSERT_RESULT_OK = 1;
    
    /** ログイン情報の登録ができました。 */
    private static final String INFO_LOGINID_INSERT_COMPLETED = "info.loginIdInsertCompleted";
    
    /** アクセス権限がありません。 */
    private static final String ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE = "errors.cmn.loginUsers.insufficientPrivilege";
    
    /** {0}を入力してください。 */
    private static final String ERRORS_REQUIRED = "errors.required";
    
    /** {0}は{1}～{2}文字で入力してください。 */
    private static final String ERRORS_SIZE = "errors.size";
    
    /** 希望ユーザーＩＤはすでに使われています。 */
    private static final String ERRORS_LOGINID_EXIST = "errors.loginIdExist";
    
    /** エラーメッセージ　:ログインID */
    private static final String ERROR_MSG_LOGINID = "ログインID";
    
    /** エラーメッセージ　:パスワード */
    private static final String ERROR_MSG_PASSWORD = "パスワード";
    
    /** エラーメッセージ　:6 */
    private static final String ERROR_MSG_6 = "6";
    
    /** エラーメッセージ　:32 */
    private static final String ERROR_MSG_32 = "32";
    
    /** エラーメッセージ　:ユーザ名 */
    private static final String ERROR_MSG_USER_NAME = "ユーザ名";
    
    /** エラーメッセージ　:所属権限 */
    private static final String ERROR_MSG_PRIVID = "所属権限";
    
    /** エラーメッセージ　:本店／支店名 */
    private static final String ERROR_MSG_BRANCH_CODE = "本店／支店名";
    
    /**　入力チェック　パスワード　6文字 */
    private static final int CHECK_CHARACTER_6 = 6;
    
    /**　入力チェック　パスワード　32文字 */
    private static final int CHECK_CHARACTER_32 = 32;
    
    @Autowired
    private IfaLoginIdNewRegisterDao dao;
    
    /**
     * リクエスト共通項目
     *
     */
    @Data
    private class IfaLoginIdNewRegisterRequestCommonDto {
        
        /** 所属権限コード. */
        private String privId;
        
        /** 本支店コード. */
        private String branchCode;
        
        /** 仲介業者コード（数字）. */
        private String brokerCode;
        
        /** 仲介業者支店コード（数字）. */
        private String subBrokerId;
        
    }
    
    /**
     * アクションID：A001
     * アクション名：初期表示
     * Dto リクエスト：IfaLoginIdNewRegisterA001RequestDto
     * Dto レスポンス：IfaLoginIdNewRegisterA001ResponseDto
     * model リクエスト：IfaLoginIdNewRegisterSql004RequestModel
     * model レスポンス：IfaLoginIdNewRegisterSql004ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdNewRegisterA001ResponseDto> initialDisplayA001(IfaLoginIdNewRegisterA001RequestDto dtoReq)
            throws Exception {
        
        DataList<IfaLoginIdNewRegisterA001ResponseDto> dtoRes = new DataList<IfaLoginIdNewRegisterA001ResponseDto>();
        List<IfaLoginIdNewRegisterA001ResponseDto> resDto = new ArrayList<IfaLoginIdNewRegisterA001ResponseDto>();
        IfaLoginIdNewRegisterA001ResponseDto response = new IfaLoginIdNewRegisterA001ResponseDto();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaLoginIdNewRegisterServiceImplL.initialDisplayA001");
        
        //利用者権限チェック
        if (!checkPrivId()) {
            
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE));
        }
        
        //非表示リスト設定
        List<IfaLoginIdNewRegisterResponseDto_DisplayCommon> unDisplayList = new ArrayList<IfaLoginIdNewRegisterResponseDto_DisplayCommon>();
        //リクエスト.メニューリスト
        List<IfaLoginIdNewRegisterA001RequestDto_MenuList> menuList = new ArrayList<IfaLoginIdNewRegisterA001RequestDto_MenuList>();
        menuList = dtoReq.getMenuList();
        
        if (menuList != null) {
            
            for (IfaLoginIdNewRegisterA001RequestDto_MenuList menuInfo : menuList) {
                
                IfaLoginIdNewRegisterResponseDto_DisplayCommon unDisplayInfo = new IfaLoginIdNewRegisterResponseDto_DisplayCommon();
                
                unDisplayInfo.setKey(menuInfo.getMenuId());
                unDisplayInfo.setLabel(menuInfo.getMenuTitle());
                
                unDisplayList.add(unDisplayInfo);
            }
            response.setUnDisplayList(unDisplayList);
        }
        
        resDto.add(response);
        dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
        
        return dtoRes;
    }
    
    /**
     * アクションID：A002
     * アクション名：所属権限更新
     * Dto リクエスト：IfaLoginIdNewRegisterA002RequestDto
     * Dto レスポンス：IfaLoginIdNewRegisterA002ResponseDto
     * model リクエスト：IfaLoginIdNewRegisterSql002RequestModel
     * model レスポンス：IfaLoginIdNewRegisterSql002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdNewRegisterA002ResponseDto> affiliationAuthorityUpdateA002(
            IfaLoginIdNewRegisterA002RequestDto dtoReq) throws Exception {
        
        DataList<IfaLoginIdNewRegisterA002ResponseDto> dtoRes = new DataList<IfaLoginIdNewRegisterA002ResponseDto>();
        List<IfaLoginIdNewRegisterA002ResponseDto> resDto = new ArrayList<IfaLoginIdNewRegisterA002ResponseDto>();
        IfaLoginIdNewRegisterA002ResponseDto response = new IfaLoginIdNewRegisterA002ResponseDto();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaLoginIdNewRegisterServiceImplL.affiliationAuthorityUpdateA002");
        
        //利用者権限チェック
        if (!checkPrivId()) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE));
        }
        
        //権限コードが11:業務部、12:管理部以外の場合
        String screenPrivId = dtoReq.getPrivId();
        if (!(StringUtils.equals(screenPrivId, PRIVID_11) || StringUtils.equals(screenPrivId, PRIVID_12))) {
            
            //本店／支店名dropDownListのデータを取得
            DataList<IfaLoginIdNewRegisterSql004ResponseModel> selSql004Res = new DataList<IfaLoginIdNewRegisterSql004ResponseModel>();
            selSql004Res = getHeadOfficeBranchNameDropDownList(screenPrivId);
            
            //本店／支店名リストにセット
            if (SELECT_RESULT_0 != selSql004Res.getDataList().size()) {
                response.setHeadOfficeBranchNameList(addHeadOfficeBranchNameDropDownList(selSql004Res));
            }
            
        }
        
        //メニュータイトルリストの設定
        settingMenuTitleList(dtoReq, response, getMenuNameList(screenPrivId));
        
        resDto.add(response);
        dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
        
        return dtoRes;
    }
    
    /**
     * アクションID：A003
     * アクション名：本支名更新
     * Dto リクエスト：IfaLoginIdNewRegisterA003RequestDto
     * Dto レスポンス：IfaLoginIdNewRegisterA003ResponseDto
     * model リクエスト：IfaLoginIdNewRegisterSql005RequestModel
     * model レスポンス：IfaLoginIdNewRegisterSql005ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdNewRegisterA003ResponseDto> headOfficeNameUpdateA003(
            IfaLoginIdNewRegisterA003RequestDto dtoReq) throws Exception {
        
        DataList<IfaLoginIdNewRegisterA003ResponseDto> dtoRes = new DataList<IfaLoginIdNewRegisterA003ResponseDto>();
        List<IfaLoginIdNewRegisterA003ResponseDto> resDto = new ArrayList<IfaLoginIdNewRegisterA003ResponseDto>();
        IfaLoginIdNewRegisterA003ResponseDto response = new IfaLoginIdNewRegisterA003ResponseDto();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaLoginIdNewRegisterServiceImplL.headOfficeNameUpdateA003");
        
        //利用者権限チェック
        if (!checkPrivId()) {
            
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE));
        }
        
        // 所属権限コードが1:本店, 2:支店の場合
        if (StringUtils.equals(PRIVID_1, dtoReq.getPrivId()) || StringUtils.equals(PRIVID_2, dtoReq.getPrivId())) {
            // 仲介業者名dropDownListをクリア
            response.setBrokerNameList(null);
        } else {
            //仲介業者名dropDownListデータ取得
            DataList<IfaLoginIdNewRegisterSql005ResponseModel> selSql005Res = new DataList<IfaLoginIdNewRegisterSql005ResponseModel>();
            selSql005Res = getBrokerNameList(dtoReq.getBranchCode());
            
            //仲介業者名リストにセット
            if (SELECT_RESULT_0 != selSql005Res.getDataList().size()) {
                response.setBrokerNameList(addBrokerNameList(selSql005Res));
            }
        }
        
        resDto.add(response);
        dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
        
        return dtoRes;
    }
    
    /**
     * アクションID：A004
     * アクション名：仲介業者名更新
     * Dto リクエスト：IfaLoginIdNewRegisterA004RequestDto
     * Dto レスポンス：IfaLoginIdNewRegisterA004ResponseDto
     * model リクエスト：IfaLoginIdNewRegisterSql006RequestModel
     * model レスポンス：IfaLoginIdNewRegisterSql006ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdNewRegisterA004ResponseDto> brokerNameUpdateA004(
            IfaLoginIdNewRegisterA004RequestDto dtoReq) throws Exception {
        
        DataList<IfaLoginIdNewRegisterA004ResponseDto> dtoRes = new DataList<IfaLoginIdNewRegisterA004ResponseDto>();
        List<IfaLoginIdNewRegisterA004ResponseDto> resDto = new ArrayList<IfaLoginIdNewRegisterA004ResponseDto>();
        IfaLoginIdNewRegisterA004ResponseDto response = new IfaLoginIdNewRegisterA004ResponseDto();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaLoginIdNewRegisterServiceImplL.brokerNameUpdateA004");
        
        //利用者権限チェック
        if (!checkPrivId()) {
            
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE));
        }
        IfaLoginIdNewRegisterRequestCommonDto dtoReqCommon = new IfaLoginIdNewRegisterRequestCommonDto();
        IfaLoginIdNewRegisterAPIResponseDto_common responseCommon = new IfaLoginIdNewRegisterAPIResponseDto_common();
        
        copyFields(dtoReq, dtoReqCommon);
        
        // 権限コードが6,7,8,9の場合
        if (SEARCH_PRIVID_BROKER.contains(dtoReq.getPrivId())) {
            // 仲介業者支店名
            DataList<IfaLoginIdNewRegisterSql006ResponseModel> selSql006Res = new DataList<IfaLoginIdNewRegisterSql006ResponseModel>();
            selSql006Res = getBranchNameList(dtoReq.getBrokerCode());
            
            //仲介業者支店名リストにセット
            if (SELECT_RESULT_0 != selSql006Res.getDataList().size()) {
                response.setBranchNameList(addBranchNameList(selSql006Res));
            }
        }
        // 権限コードが3,4,5の場合
        if (A004_SEARCH_PRIVID_BROKER.contains(dtoReq.getPrivId())) {
            // 仲介業者支店名
            response.setBranchNameList(null);
            // 担当者名
            commonGetChargeNameList(dtoReqCommon, responseCommon);
            response.setChargeNameList(responseCommon.getChargeNameList());
        }
        
        resDto.add(response);
        dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
        
        return dtoRes;
    }
    
    /**
     * アクションID：A005
     * アクション名：仲介業者支店名更新
     * Dto リクエスト：IfaLoginIdNewRegisterA005RequestDto
     * Dto レスポンス：IfaLoginIdNewRegisterA005ResponseDto
     * model リクエスト：IfaLoginIdNewRegisterSql007RequestModel
     * model レスポンス：IfaLoginIdNewRegisterSql007ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdNewRegisterA005ResponseDto> brokerBranchNameUpdateA005(
            IfaLoginIdNewRegisterA005RequestDto dtoReq) throws Exception {
        
        DataList<IfaLoginIdNewRegisterA005ResponseDto> dtoRes = new DataList<IfaLoginIdNewRegisterA005ResponseDto>();
        List<IfaLoginIdNewRegisterA005ResponseDto> resDto = new ArrayList<IfaLoginIdNewRegisterA005ResponseDto>();
        IfaLoginIdNewRegisterA005ResponseDto response = new IfaLoginIdNewRegisterA005ResponseDto();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaLoginIdNewRegisterServiceImplL.brokerBranchNameUpdateA005");
        
        //利用者権限チェック
        if (!checkPrivId()) {
            
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE));
        }
        
        IfaLoginIdNewRegisterRequestCommonDto dtoReqCommon = new IfaLoginIdNewRegisterRequestCommonDto();
        IfaLoginIdNewRegisterAPIResponseDto_common responseCommon = new IfaLoginIdNewRegisterAPIResponseDto_common();
        
        copyFields(dtoReq, dtoReqCommon);
        
        commonGetChargeNameList(dtoReqCommon, responseCommon);
        
        copyFields(responseCommon, response);
        
        resDto.add(response);
        dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
        
        return dtoRes;
    }
    
    /**
     * アクションID：A006
     * アクション名：リセット
     * Dto リクエスト：IfaLoginIdNewRegisterA006RequestDto
     * Dto レスポンス：IfaLoginIdNewRegisterA006ResponseDto
     * model リクエスト：IfaLoginIdNewRegisterSql004RequestModel
     * model レスポンス：IfaLoginIdNewRegisterSql004ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdNewRegisterA006ResponseDto> resetA006(IfaLoginIdNewRegisterA006RequestDto dtoReq)
            throws Exception {
        
        DataList<IfaLoginIdNewRegisterA006ResponseDto> dtoRes = new DataList<IfaLoginIdNewRegisterA006ResponseDto>();
        List<IfaLoginIdNewRegisterA006ResponseDto> resDto = new ArrayList<IfaLoginIdNewRegisterA006ResponseDto>();
        IfaLoginIdNewRegisterA006ResponseDto response = new IfaLoginIdNewRegisterA006ResponseDto();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaLoginIdNewRegisterServiceImplL.resetA006");
        
        //利用者権限チェック
        if (!checkPrivId()) {
            
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE));
        }
        
        response.setUnDisplayList(addUnDisplayList(dtoReq.getUnDisplayList(), dtoReq.getDisplayList()));
        resDto.add(response);
        dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
        
        return dtoRes;
    }
    
    /**
     * アクションID：A011
     * アクション名：登録（OK）
     * Dto リクエスト：IfaLoginIdNewRegisterA011RequestDto
     * Dto レスポンス：IfaLoginIdNewRegisterA011ResponseDto
     * model リクエスト：IfaLoginIdNewRegisterSql012RequestModel
     * model レスポンス：IfaLoginIdNewRegisterSql012ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdNewRegisterA011ResponseDto> registrationOkA011(IfaLoginIdNewRegisterA011RequestDto dtoReq)
            throws Exception {
        
        DataList<IfaLoginIdNewRegisterA011ResponseDto> dtoRes = new DataList<IfaLoginIdNewRegisterA011ResponseDto>();
        List<IfaLoginIdNewRegisterA011ResponseDto> resDto = new ArrayList<IfaLoginIdNewRegisterA011ResponseDto>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaLoginIdNewRegisterServiceImplL.registrationOkA011");
        
        // エラー情報の初期化（[0]：エラーコード、[1]：エラーメッセージ）
        String[] errorInfo = new String[2];
        
        //ユーザー共通情報取得
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        //利用者権限チェック
        if (!checkPrivId()) {
            
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE));
        }
        
        //入力チェック
        if (!checkInput(dtoReq, errorInfo)) {
            
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorInfo[0], errorInfo[1]);
        }
        
        //ユーザ存在チェック
        if (!checkUser(dtoReq.getLoginId(), errorInfo)) {
            
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorInfo[0], errorInfo[1]);
        }
        
        //Horusユーザー情報登録処理
        if (INSERT_RESULT_OK != registerHorusUser(dtoReq, userAccount.getUserId())) {
            
            throw new Exception();
        }
        
        //認証ユーザー情報登録処理
        registerAuthUser(dtoReq, userAccount.getUserId());
        
        //Cordysユーザーとメニューマッピングの登録処理
        registerCordysUser(dtoReq, userAccount.getUserId());
        
        dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, INFO_LOGINID_INSERT_COMPLETED,
                IfaCommonUtil.getMessage(INFO_LOGINID_INSERT_COMPLETED));
        
        return dtoRes;
    }
    
    /**
     * 利用者権限チェック
     * @return チェック結果
     */
    private boolean checkPrivId() {
        
        //ユーザ共通情報
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        String privId = userAccount.getPrivId();
        
        return StringUtils.equals(privId, USER_PERMISSION_SBI);
    }
    
    /**
     * 画面.権限コードチェック
     * @param privId
     * @return チェック結果
     */
    private String checkScreenPrivId(String privId) {
        
        String checkResult = null;
        
        if (SEARCH_PRIVID_BROKER.contains(privId)) {
            
            checkResult = CHECK_RESULT_PRIVID_BROKER;
        } else if (SEARCH_PRIVID_HEAD_OFFICE.contains(privId)) {
            
            checkResult = CHECK_RESULT_PRIVID_HEAD_OFFICE;
        }
        
        return checkResult;
    }
    
    /**
     * 入力チェック
     * @param dtoReq
     * @param errorInfo エラー情報
     * @return チェック結果
     */
    private boolean checkInput(IfaLoginIdNewRegisterA011RequestDto dtoReq, String[] errorInfo) {
        
        //ログインIDチェック
        if (StringUtil.isNullOrEmpty(dtoReq.getLoginId())) {
            errorInfo[0] = ERRORS_REQUIRED;
            errorInfo[1] = IfaCommonUtil.getMessage(errorInfo[0], new String[] { ERROR_MSG_LOGINID });
            return false;
        }
        //パスワードチェック
        if (StringUtil.isNullOrEmpty(dtoReq.getPassword())) {
            errorInfo[0] = ERRORS_REQUIRED;
            errorInfo[1] = IfaCommonUtil.getMessage(errorInfo[0], new String[] { ERROR_MSG_PASSWORD });
            return false;
        }
        //パスワード>=6又は<=32
        int password = dtoReq.getPassword().length();
        if (password < CHECK_CHARACTER_6 || CHECK_CHARACTER_32 < password) {
            errorInfo[0] = ERRORS_SIZE;
            errorInfo[1] = IfaCommonUtil.getMessage(errorInfo[0],
                    new String[] { ERROR_MSG_PASSWORD, ERROR_MSG_6, ERROR_MSG_32 });
            return false;
        }
        //ユーザ名チェック
        if (StringUtil.isNullOrEmpty(dtoReq.getUserName())) {
            errorInfo[0] = ERRORS_REQUIRED;
            errorInfo[1] = IfaCommonUtil.getMessage(errorInfo[0], new String[] { ERROR_MSG_USER_NAME });
            return false;
        }
        //所属権限コードチェック
        if (StringUtil.isNullOrEmpty(dtoReq.getPrivId())) {
            errorInfo[0] = ERRORS_REQUIRED;
            errorInfo[1] = IfaCommonUtil.getMessage(errorInfo[0], new String[] { ERROR_MSG_PRIVID });
            return false;
        }
        //本支店コードチェック
        // 権限コードが11:業務部、12:管理部の場合は必須項目ではないためチェック不要
        if (!(StringUtils.equals(dtoReq.getPrivId(), PRIVID_11) || StringUtils.equals(dtoReq.getPrivId(), PRIVID_12))) {
            if (StringUtil.isNullOrEmpty(dtoReq.getBranchCode())) {
                errorInfo[0] = ERRORS_REQUIRED;
                errorInfo[1] = IfaCommonUtil.getMessage(errorInfo[0], new String[] { ERROR_MSG_BRANCH_CODE });
                return false;
            }
        }

        return true;
    }
    
    /**
     * ユーザ存在チェック
     * @param loginId
     * @param errorInfo エラー情報
     * @return チェック結果
     * @throws Exception
     */
    private boolean checkUser(String loginId, String[] errorInfo) throws Exception {
        
        IfaLoginIdNewRegisterSql011RequestModel selSql011Req = new IfaLoginIdNewRegisterSql011RequestModel();
        selSql011Req.setLoginId(loginId);
        
        if (SELECT_RESULT_0 != dao.selectIfaLoginIdNewRegisterSql011(selSql011Req).getDataList().size()) {
            errorInfo[0] = ERRORS_LOGINID_EXIST;            
            errorInfo[1] = IfaCommonUtil.getMessage(errorInfo[0]);
            return false;
        }
        
        return true;
    }
    
    /**
     * メニュー名一覧を取得
     * @param screenPrivId
     * @return メニュー名一覧
     * @throws Exception 
     */
    private DataList<IfaLoginIdNewRegisterSql002ResponseModel> getMenuNameList(String screenPrivId) throws Exception {
        
        IfaLoginIdNewRegisterSql002RequestModel selSql002Req = new IfaLoginIdNewRegisterSql002RequestModel();
        selSql002Req.setPrivId(screenPrivId);
        
        return dao.selectIfaLoginIdNewRegisterSql002(selSql002Req);
    }
    
    /**
     * メニュータイトルリストの設定を行う
     * @param dtoReq
     * @param response
     * @param selSql002Res
     */
    private void settingMenuTitleList(IfaLoginIdNewRegisterA002RequestDto dtoReq,
            IfaLoginIdNewRegisterA002ResponseDto response,
            DataList<IfaLoginIdNewRegisterSql002ResponseModel> selSql002Res) {
        
        //リクエスト.非表示リスト+リクエスト.表示リスト
        List<IfaLoginIdNewRegisterResponseDto_DisplayCommon> reqDisplayUnDisplayList = new ArrayList<IfaLoginIdNewRegisterResponseDto_DisplayCommon>();
        List<IfaLoginIdNewRegisterResponseDto_DisplayCommon> reqDisplayList = new ArrayList<IfaLoginIdNewRegisterResponseDto_DisplayCommon>();
        dtoReq.getDisplayList().stream().forEach(d -> {
            IfaLoginIdNewRegisterResponseDto_DisplayCommon disp = new IfaLoginIdNewRegisterResponseDto_DisplayCommon();
            disp.setKey(d);
            reqDisplayList.add(disp);
        });
        reqDisplayUnDisplayList = dtoReq.getUnDisplayList();
        reqDisplayUnDisplayList.addAll(reqDisplayList);
        // 重複削除
        HashSet<String> distinctSet = new HashSet<>();
        reqDisplayUnDisplayList.removeIf(r -> !distinctSet.add(r.getKey()));
        
        //レスポンス.非表示リスト
        List<IfaLoginIdNewRegisterResponseDto_DisplayCommon> resUnDisplayList = new ArrayList<IfaLoginIdNewRegisterResponseDto_DisplayCommon>();
        //レスポンス.表示リスト
        List<String> resDisplayList = new ArrayList<String>();
        
        //SQL002.メニューコードと一致するものを表示リストに、一致しないものは非表示リストに設定
        // 判定用リスト
        final List<String> tempList = selSql002Res.getDataList().stream().map(m -> m.getMenuId())
                .collect(Collectors.toList());
        
        reqDisplayUnDisplayList.stream().forEach(f -> {
            
            if (tempList.contains(f.getKey())) {
                
                //表示リストにセット
                resDisplayList.add(f.getKey());
            }
                
            IfaLoginIdNewRegisterResponseDto_DisplayCommon resUnDisplayInfo = new IfaLoginIdNewRegisterResponseDto_DisplayCommon();
            
            resUnDisplayInfo.setKey(f.getKey());
            resUnDisplayInfo.setLabel(f.getLabel());
            
            //非表示リストにセット
            resUnDisplayList.add(resUnDisplayInfo);
            
        });
        
        if (LIST_SIZE_0 != resUnDisplayList.size()) {
            
            response.setUnDisplayList(resUnDisplayList);
        }
        if (LIST_SIZE_0 != resDisplayList.size()) {
            
            response.setDisplayList(resDisplayList);
        }
    }
    
    /**
     * 共通処理　仲介業者名dropDownList取得処理を実行
     * @param dtoReq
     * @return response
     * @throws Exception
     */
    private void commonGetBrokerNameList(IfaLoginIdNewRegisterRequestCommonDto dtoReq,
            IfaLoginIdNewRegisterAPIResponseDto_common responseCommon) throws Exception {
        
        DataList<IfaLoginIdNewRegisterSql005ResponseModel> selSql005Res = new DataList<IfaLoginIdNewRegisterSql005ResponseModel>();
        
        //仲介業者名dropDownListデータ取得
        selSql005Res = getBrokerNameList(dtoReq.getBranchCode());
        
        //仲介業者名リストにセット
        if (SELECT_RESULT_0 != selSql005Res.getDataList().size()) {
            responseCommon.setBrokerNameList(addBrokerNameList(selSql005Res));
        }
        
        //画面.権限コード
        String screenPrivId = dtoReq.getPrivId();
        
        if (StringUtils.equals(CHECK_RESULT_PRIVID_BROKER, checkScreenPrivId(screenPrivId))) {
            
            //A004処理
            commonGetBranchNameList(dtoReq, responseCommon);
            
        } else if (StringUtils.equals(CHECK_RESULT_PRIVID_HEAD_OFFICE, checkScreenPrivId(screenPrivId))) {
            
            //A005処理
            commonGetChargeNameList(dtoReq, responseCommon);
        }
    }
    
    /**
     * 共通処理　仲介業者支店名dropDownList取得処理を実行
     * @param dtoReq
     * @return response
     * @throws Exception
     */
    private void commonGetBranchNameList(IfaLoginIdNewRegisterRequestCommonDto dtoReq,
            IfaLoginIdNewRegisterAPIResponseDto_common responseCommon) throws Exception {
        
        DataList<IfaLoginIdNewRegisterSql006ResponseModel> selSql006Res = new DataList<IfaLoginIdNewRegisterSql006ResponseModel>();
        
        selSql006Res = getBranchNameList(dtoReq.getBrokerCode());
        
        //仲介業者支店名リストにセット
        if (SELECT_RESULT_0 != selSql006Res.getDataList().size()) {
            responseCommon.setBranchNameList(addBranchNameList(selSql006Res));
        }
        
        //A005処理
        commonGetChargeNameList(dtoReq, responseCommon);
        
    }
    
    /**
     * 共通処理　担当者名dropDownList取得処理を実行
     * @param dtoReq
     * @return response
     * @throws Exception
     */
    private void commonGetChargeNameList(IfaLoginIdNewRegisterRequestCommonDto dtoReq,
            IfaLoginIdNewRegisterAPIResponseDto_common responseCommon) throws Exception {
        
        //担当者名dropDownListデータ取得
        DataList<IfaLoginIdNewRegisterSql007ResponseModel> selSql007Res = new DataList<IfaLoginIdNewRegisterSql007ResponseModel>();
        selSql007Res = getChargeNameList(dtoReq.getBrokerCode(), dtoReq.getSubBrokerId());
        //担当者名リストにセット
        if (SELECT_RESULT_0 != selSql007Res.getDataList().size()) {
            
            responseCommon.setChargeNameList(addChargeNameList(selSql007Res));
        }
    }
    
    /**
     * 本店／支店名dropDownListのデータを取得
     * @param privId
     * @return selSql004Res
     * @throws Exception 
     */
    private DataList<IfaLoginIdNewRegisterSql004ResponseModel> getHeadOfficeBranchNameDropDownList(String privId)
            throws Exception {
        
        IfaLoginIdNewRegisterSql004RequestModel selSql004Req = new IfaLoginIdNewRegisterSql004RequestModel();
        DataList<IfaLoginIdNewRegisterSql004ResponseModel> selSql004Res = new DataList<IfaLoginIdNewRegisterSql004ResponseModel>();
        
        if (StringUtils.equals(privId, PRIVID_1)) {
            
            selSql004Req.setBranchClass(BRANCHCLASS_00);
        } else if (Integer.parseInt(privId) >= Integer.parseInt(PRIVID_2)) {
            
            selSql004Req.setBranchClass(BRANCHCLASS_01);
        }
        selSql004Res = dao.selectIfaLoginIdNewRegisterSql004(selSql004Req);
        
        return selSql004Res;
    }
    
    /**
     * 本店／支店名dropDownListのデータをListに追加
     * @param headOfficeBranchNameList
     * @return headOfficeBranchNameList
     */
    private List<IfaLoginIdNewRegisterResponseDto_CommonList> addHeadOfficeBranchNameDropDownList(
            DataList<IfaLoginIdNewRegisterSql004ResponseModel> selSql004Res) {
        
        List<IfaLoginIdNewRegisterResponseDto_CommonList> headOfficeBranchNameList = new ArrayList<IfaLoginIdNewRegisterResponseDto_CommonList>();
        
        for (IfaLoginIdNewRegisterSql004ResponseModel res : selSql004Res.getDataList()) {
            
            IfaLoginIdNewRegisterResponseDto_CommonList headOfficeBranchNameInfo = new IfaLoginIdNewRegisterResponseDto_CommonList();
            headOfficeBranchNameInfo.setKey(res.getBranchCode());
            headOfficeBranchNameInfo.setValue(res.getBranchName());
            headOfficeBranchNameList.add(headOfficeBranchNameInfo);
        }
        
        return headOfficeBranchNameList;
    }
    
    /**
     * 仲介業者名dropDownListのデータを取得
     * @param branchCode
     * @return 仲介業者名dropDownList
     * @throws Exception 
     */
    private DataList<IfaLoginIdNewRegisterSql005ResponseModel> getBrokerNameList(String branchCode) throws Exception {
        
        IfaLoginIdNewRegisterSql005RequestModel selSql005Req = new IfaLoginIdNewRegisterSql005RequestModel();
        selSql005Req.setBranchCode(branchCode);
        
        return dao.selectIfaLoginIdNewRegisterSql005(selSql005Req);
    }
    
    /**
     * 仲介業者名dropDownListのデータをListに追加
     * @param selSql005Res
     * @return brokerNameList
     */
    private List<IfaLoginIdNewRegisterResponseDto_CommonList> addBrokerNameList(
            DataList<IfaLoginIdNewRegisterSql005ResponseModel> selSql005Res) {
        
        List<IfaLoginIdNewRegisterResponseDto_CommonList> brokerNameList = new ArrayList<IfaLoginIdNewRegisterResponseDto_CommonList>();
        
        for (IfaLoginIdNewRegisterSql005ResponseModel res : selSql005Res.getDataList()) {
            
            IfaLoginIdNewRegisterResponseDto_CommonList brokerNameInfo = new IfaLoginIdNewRegisterResponseDto_CommonList();
            
            //仲介業者名
            brokerNameInfo.setValue(res.getBranchNameBrokerName());
            //仲介業者コード
            brokerNameInfo.setKey(res.getBrokerCode());
            
            brokerNameList.add(brokerNameInfo);
        }
        
        return brokerNameList;
    }
    
    /**
     * 仲介業者支店名dropDownListのデータを取得
     * @param brokerCode
     * @return　仲介業者支店名dropDownList
     * @throws Exception
     */
    private DataList<IfaLoginIdNewRegisterSql006ResponseModel> getBranchNameList(String brokerCode) throws Exception {
        
        IfaLoginIdNewRegisterSql006RequestModel selSql006Req = new IfaLoginIdNewRegisterSql006RequestModel();
        selSql006Req.setBrokerCode(brokerCode);
        
        return dao.selectIfaLoginIdNewRegisterSql006(selSql006Req);
    }
    
    /**
     * 仲介業者支店名dropDownListのデータをListに追加
     * @param selSql006Res
     * @return branchNameList
     */
    private List<IfaLoginIdNewRegisterResponseDto_CommonList> addBranchNameList(
            DataList<IfaLoginIdNewRegisterSql006ResponseModel> selSql006Res) {
        
        List<IfaLoginIdNewRegisterResponseDto_CommonList> branchNameList = new ArrayList<IfaLoginIdNewRegisterResponseDto_CommonList>();
        
        for (IfaLoginIdNewRegisterSql006ResponseModel res : selSql006Res.getDataList()) {
            
            IfaLoginIdNewRegisterResponseDto_CommonList branchNameInfo = new IfaLoginIdNewRegisterResponseDto_CommonList();
            
            //仲介業者支店コード
            branchNameInfo.setKey(res.getSubBrokerId());
            //仲介業者支店名
            branchNameInfo.setValue(res.getBranchNameBrokerName());
            
            branchNameList.add(branchNameInfo);
        }
        
        return branchNameList;
    }
    
    /**
     * 担当者名dropDownListのデータを取得
     * @param brokerCode
     * @param subBrokerCode
     * @return 担当者名dropDownList
     * @throws Exception
     */
    private DataList<IfaLoginIdNewRegisterSql007ResponseModel> getChargeNameList(String brokerCode,
            String subBrokerCode) throws Exception {
        
        IfaLoginIdNewRegisterSql007RequestModel selSql007Req = new IfaLoginIdNewRegisterSql007RequestModel();
        selSql007Req.setBrokerCode(brokerCode);
        selSql007Req.setSubBrokerCode(subBrokerCode);
        
        return dao.selectIfaLoginIdNewRegisterSql007(selSql007Req);
    }
    
    /**
     * 担当者名dropDownListのデータをListに追加
     * @param selSql007Res
     * @return chargeNameList
     */
    private List<IfaLoginIdNewRegisterResponseDto_CommonList> addChargeNameList(
            DataList<IfaLoginIdNewRegisterSql007ResponseModel> selSql007Res) {
        
        List<IfaLoginIdNewRegisterResponseDto_CommonList> chargeNameList = new ArrayList<IfaLoginIdNewRegisterResponseDto_CommonList>();
        
        for (IfaLoginIdNewRegisterSql007ResponseModel res : selSql007Res.getDataList()) {
            
            IfaLoginIdNewRegisterResponseDto_CommonList chargeNameInfo = new IfaLoginIdNewRegisterResponseDto_CommonList();
            
            //仲介業者担当者コード
            chargeNameInfo.setKey(res.getBrokerChargeCode());
            //仲介業者担当者名
            chargeNameInfo.setValue(res.getEmployeeName());
            
            chargeNameList.add(chargeNameInfo);
        }
        
        return chargeNameList;
    }
    
    /**
     * 非表示リスト設定を行う
     * @param dtoReq
     * @return resUnDisplayList
     */
    private List<IfaLoginIdNewRegisterResponseDto_DisplayCommon> addUnDisplayList(
            List<IfaLoginIdNewRegisterResponseDto_DisplayCommon> reqUnDisplayList,
            List<String> reqDisplayList) {
        
        //レスポンス.非表示リスト
        List<IfaLoginIdNewRegisterResponseDto_DisplayCommon> resUnDisplayList = new ArrayList<IfaLoginIdNewRegisterResponseDto_DisplayCommon>();
        
        if (reqUnDisplayList != null) {
            
            for (IfaLoginIdNewRegisterResponseDto_DisplayCommon reqUnDisplayInfo : reqUnDisplayList) {
                
                IfaLoginIdNewRegisterResponseDto_DisplayCommon resUnDisplayInfo = new IfaLoginIdNewRegisterResponseDto_DisplayCommon();
                
                resUnDisplayInfo.setKey(reqUnDisplayInfo.getKey());
                resUnDisplayInfo.setLabel(reqUnDisplayInfo.getLabel());
                
                resUnDisplayList.add(resUnDisplayInfo);
            }
        }
        if (reqDisplayList != null) {
            
            for (String reqDisplayListInfo : reqDisplayList) {
                
                IfaLoginIdNewRegisterResponseDto_DisplayCommon resUnDisplayInfo = new IfaLoginIdNewRegisterResponseDto_DisplayCommon();
                
                resUnDisplayInfo.setKey(reqDisplayListInfo);
                
                resUnDisplayList.add(resUnDisplayInfo);
            }
        }
        
        return resUnDisplayList.size() == LIST_SIZE_0 ? null : resUnDisplayList;
    }
    
    /**
     * Horusユーザー情報登録
     * @return Insert結果
     * @throws Exception 
     */
    private int registerHorusUser(IfaLoginIdNewRegisterA011RequestDto dtoReq, String userId) throws Exception {
        
        IfaLoginIdNewRegisterSql008RequestModel insSql008Req = new IfaLoginIdNewRegisterSql008RequestModel();
        
        insSql008Req.setLoginId(dtoReq.getLoginId());
        insSql008Req.setUserName(dtoReq.getUserName());
        //リクエスト.パスワードを暗号化
        insSql008Req.setPassword(EncryptPassword.encrypt(dtoReq.getPassword()));
        insSql008Req.setPrivId(dtoReq.getPrivId());
        insSql008Req.setBranchCode(dtoReq.getBranchCode());
        insSql008Req.setBrokerCode(dtoReq.getBrokerCode());
        insSql008Req.setSubBrokerCode(dtoReq.getSubBrokerId());
        insSql008Req.setEmployeeId(dtoReq.getEmployeeId());
        insSql008Req.setChargeName(dtoReq.getChargeName());
        insSql008Req.setCreateUser(userId);
        insSql008Req.setUpdateUser(userId);
        insSql008Req.setMailAddress(dtoReq.getMailAddress());
        
        return dao.insertIfaLoginIdNewRegisterSql008(insSql008Req);
    }
    
    /**
     * 認証ユーザー情報登録
     * @param dtoReq
     * @param userId
     * @return Insert結果
     * @throws Exception 
     */
    private void registerAuthUser(IfaLoginIdNewRegisterA011RequestDto dtoReq, String userId) throws Exception {
        
        IfaLoginIdNewRegisterSql012RequestModel insSql012Req = new IfaLoginIdNewRegisterSql012RequestModel();
        
        insSql012Req.setLoginId(dtoReq.getLoginId());
        insSql012Req.setMailAddress(dtoReq.getMailAddress());
        insSql012Req.setPrivId(dtoReq.getPrivId());
        insSql012Req.setUpdateUser(userId);
        dao.insertIfaLoginIdNewRegisterSql012(insSql012Req);
    }
    
    /**
     * Cordysユーザーとメニューマッピングの登録処理
     * @param dtoReq
     * @param userId
     * @return Insert結果
     * @throws Exception
     */
    private void registerCordysUser(IfaLoginIdNewRegisterA011RequestDto dtoReq, String userId) throws Exception {
        
        IfaLoginIdNewRegisterSql009RequestModel delSql009Req = new IfaLoginIdNewRegisterSql009RequestModel();
        IfaLoginIdNewRegisterSql010RequestModel insSql010Req = new IfaLoginIdNewRegisterSql010RequestModel();
        
        //指定ユーザーIDにあるメニューをTB_MED_GOV_MENUテーブルより削除
        delSql009Req.setLoginId(dtoReq.getLoginId());
        dao.deleteIfaLoginIdNewRegisterSql009(delSql009Req);
        
        for (String displayInfo : dtoReq.getDisplayList()) {
            
            insSql010Req.setLoginId(dtoReq.getLoginId());
            insSql010Req.setMenuId(displayInfo);
            insSql010Req.setCreateUser(userId);
            insSql010Req.setUpdateUser(userId);
            
            if (INSERT_RESULT_OK != dao.insertIfaLoginIdNewRegisterSql010(insSql010Req)) {
                
                throw new Exception();
            }
        }
    }
    
    /**
     * 別クラスの同名フィールドをコピーする
     * (BeanUtils.copyPropertiesが機能しないため)
     * @param source コピー元のインスタンス
     * @param target コピー先のインスタンス
     */
    private static void copyFields(Object source, Object target) {
        
        final String prefixSet = "set";
        final String prefixGet = "get";
        
        Set<String> methodSet = new HashSet<String>();
        for (Method method : target.getClass().getMethods())
            if (method.getName().startsWith(prefixSet))
                methodSet.add(method.getName().substring(prefixSet.length()));
            
        try {
            for (Method method : source.getClass().getMethods()) {
                if (!method.getName().startsWith(prefixGet))
                    continue;
                String curName = method.getName().substring(prefixGet.length());
                if (methodSet.contains(curName)) {
                    Method newMethod = target.getClass().getMethod(prefixSet + curName, method.getReturnType());
                    newMethod.invoke(target, method.invoke(source));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
