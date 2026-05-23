package com.sbisec.helios.ap.systemManageMenu.loginUserManage.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.utils.AthenaBusinessException;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.PrivId;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.EncryptPassword;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.IfaLoginIdUpdateRegisterDao;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql001RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql001ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql003RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql003ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql004RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql004ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql005RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql005ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql006RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql006ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql007RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql008RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql009RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql010RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql011RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql011ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql012RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql013RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql013ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA001DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA001DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA002DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA002DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA003DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA003DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA004DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA004DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA005DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA005DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA006DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA006DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA014DeleteCcsDataDtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA014DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA014DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterDtoRequest_menuList;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterDtoResponse_branchName;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterDtoResponse_brokerName;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterDtoResponse_chargeName;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterDtoResponse_headOfficeBranchName;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterDtoResponse_unDisplay;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.service.IfaLoginIdUpdateRegisterService;

import lombok.Data;

/**
 * 画面ID：SUB0601_01-03_1
 * 画面名：ログインID更新登録
 * @author 齋藤優輝
 *
 * 2023/11/10 新規作成
 */
@Component(value = "cmpIfaLoginIdUpdateRegisterService")
public class IfaLoginIdUpdateRegisterServiceImpL implements IfaLoginIdUpdateRegisterService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaLoginIdUpdateRegisterServiceImpL.class);
    
    @Autowired
    private IfaLoginIdUpdateRegisterDao dao;
    // --------------------------------
    // 設定値
    // -------------------------------
    
    /**本支店種別の設定値(権限コードが本店の場合)*/
    private final static String BRANCHKIND_HEAD_OFFICE = "00";
    
    /**本支店種別の設定値(権限コードが本店でない場合)*/
    private final static String BRANCHKIND_BRANCH = "01";
    
    /**管理者フラグ 1:"管理者*/
    private final static String GOVERNOR_FALAG_TRUE = "1";
    
    /**管理者フラグ 0:"非管理者*/
    private final static String GOVERNOR_FALAG_FALSE = "0";
    
    /**6~9の権限コード*/
    private final static List<String> CALL_A004_PRIV_ID = Arrays.asList(PrivId.BB_INNER_MANAGEMENT.getId(),
            PrivId.BB_SALES_EXECUTIVE.getId(), PrivId.BB_SALES_REPRESENTATIVE.getId(), PrivId.RESPONSIBLE.getId());
    
    /**3~5の権限コード*/
    private final static List<String> PRIV_ID_INTERMEDIARY = Arrays.asList(PrivId.B_INNER_MANAGEMENT.getId(),
            PrivId.B_SALES_EXECUTIVE.getId(), PrivId.B_SALES_REPRESENTATIVE.getId());
    
    // --------------------------------
    // メッセージ
    // -------------------------------
    
    /** アクセス権限がありません。*/
    private final static String ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE = "errors.cmn.loginUsers.insufficientPrivilege";
    
    /**{0}有効データが存在しません。{0}："ログインID："*/
    private final static String ERRORS_EMPTY_RECORD = "errors.emptyRecord";
    
    /** ログイン情報の変更ができました。 */
    private final static String INFO_LOGINID_UPDATE_COMPLETED = "info.loginIdUpdateCompleted";
    
    //ドロップダウンリストをまとめてやり取りするためのクラス
    @Data
    private class DropDownListsDto {
        
        //本店支店名リスト
        private List<IfaLoginIdUpdateRegisterDtoResponse_headOfficeBranchName> headOfficeBranchNameList;
        
        //仲介業者名リスト
        private List<IfaLoginIdUpdateRegisterDtoResponse_brokerName> brokerNameList;
        
        //仲介業者支店名リスト
        private List<IfaLoginIdUpdateRegisterDtoResponse_branchName> branchNameList;
        
        //担当者名リスト
        private List<IfaLoginIdUpdateRegisterDtoResponse_chargeName> chargeNameList;
        
        //非表示リスト
        private List<IfaLoginIdUpdateRegisterDtoResponse_unDisplay> unDisplayList;
        
        //表示リスト
        private List<String> displayList;
    }
    
    /**
     * アクションID：A001
     * アクション名：初期表示
     * Dto リクエスト：IfaLoginIdUpdateRegisterA001DtoRequest
     * Dto レスポンス：IfaLoginIdUpdateRegisterA001DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdUpdateRegisterA001DtoResponse> initialDisplayA001(
            IfaLoginIdUpdateRegisterA001DtoRequest dtoReq) throws Exception {
        
        List<IfaLoginIdUpdateRegisterA001DtoResponse> resDto = new ArrayList<IfaLoginIdUpdateRegisterA001DtoResponse>();
        IfaLoginIdUpdateRegisterA001DtoResponse response = new IfaLoginIdUpdateRegisterA001DtoResponse();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaLoginIdUpdateRegisterServiceImplL.initialDisplayA001");
        
        //利用者権限が「SBI証券本店」（権限=1）でない場合エラーを返却
        if (!CheckHeadOfiice()) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE));
        }
        // 初期化処理
        DropDownListsDto ddlList = initializeAllDropDownLists(dtoReq.getPrivId(), dtoReq.getBranchCode(),
                dtoReq.getBrokerCode(), dtoReq.getSubBrokerId(), dtoReq.getEmployeeId(), dtoReq.getLoginId(),
                dtoReq.getMenuList());
        
        response.setHeadOfficeBranchNameList(ddlList.getHeadOfficeBranchNameList());
        response.setBrokerNameList(ddlList.getBrokerNameList());
        response.setBranchNameList(ddlList.getBranchNameList());
        response.setChargeNameList(ddlList.getChargeNameList());
        response.setDisplayList(ddlList.getDisplayList());
        response.setUnDisplayList(ddlList.getUnDisplayList());
        resDto.add(response);
        return IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
    }
    
    /**
     * アクションID：A002
     * アクション名:所属権限更新
     * Dto リクエスト：IfaLoginIdUpdateRegisterA002DtoRequest
     * Dto レスポンス：IfaLoginIdUpdateRegisterA002DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdUpdateRegisterA002DtoResponse> affiliationAuthorityUpdateA002(
            IfaLoginIdUpdateRegisterA002DtoRequest dtoReq) throws Exception {
        
        List<IfaLoginIdUpdateRegisterA002DtoResponse> resDto = new ArrayList<IfaLoginIdUpdateRegisterA002DtoResponse>();
        IfaLoginIdUpdateRegisterA002DtoResponse response = new IfaLoginIdUpdateRegisterA002DtoResponse();
        
        List<IfaLoginIdUpdateRegisterDtoResponse_unDisplay> unDisplayList = new ArrayList<IfaLoginIdUpdateRegisterDtoResponse_unDisplay>();
        List<String> displayList = new ArrayList<String>();
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaLoginIdUpdateRegisterServiceImplL.affiliationAuthorityUpdateA002");
        
        //利用者権限が「SBI証券本店」（権限=1）でない場合エラーを返却
        if (!CheckHeadOfiice()) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE));
        }
        // 本店/支店名dropDownListのデータを取得
        response.setHeadOfficeBranchNameList(callHeadOfficeBranchDropDownList(dtoReq.getPrivId()));
        
        //表示/非表示リスト全量に対して再度表示/非表示を振り分け
        //メニューリストに表示非表示全値格納
        List<IfaLoginIdUpdateRegisterDtoRequest_menuList> menuList = new ArrayList<IfaLoginIdUpdateRegisterDtoRequest_menuList>();
        for (IfaLoginIdUpdateRegisterDtoResponse_unDisplay m : dtoReq.getUnDisplayList()) {
            IfaLoginIdUpdateRegisterDtoRequest_menuList menu = new IfaLoginIdUpdateRegisterDtoRequest_menuList();
            menu.setMenuId(m.getKey());
            menu.setMenuTitle(m.getLabel());
            menuList.add(menu);
        }
        //SQL001の実行結果からmenuListの各値をdisplayListとunDisplayListに振り分けて格納
        setDisplayUndisplayList(dtoReq.getPrivId(), menuList, displayList, unDisplayList, false);
        // 表示リスト
        response.setDisplayList(displayList);
        // 非表示リスト
        response.setUnDisplayList(unDisplayList);
        
        resDto.add(response);
        return IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
    }
    
    /**
     * アクションID：A003
     * アクション名：本支名更新
     * Dto リクエスト：IfaLoginIdUpdateRegisterA003DtoRequest
     * Dto レスポンス：IfaLoginIdUpdateRegisterA003DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdUpdateRegisterA003DtoResponse> headOfficeNameUpdateA003(
            IfaLoginIdUpdateRegisterA003DtoRequest dtoReq) throws Exception {
        
        List<IfaLoginIdUpdateRegisterA003DtoResponse> resDto = new ArrayList<IfaLoginIdUpdateRegisterA003DtoResponse>();
        IfaLoginIdUpdateRegisterA003DtoResponse response = new IfaLoginIdUpdateRegisterA003DtoResponse();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaLoginIdUpdateRegisterServiceImplL.headOfficeNameUpdateA003");
        //利用者権限が「SBI証券本店」（権限=1）でない場合エラーを返却
        if (!CheckHeadOfiice()) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE));
        }
        // 所属権限コードが1:本店, 2:支店でない場合
        if (!(StringUtils.equals(PrivId.HEAD_OFFICE.getId(), dtoReq.getPrivId())
                || StringUtils.equals(PrivId.BRANCH.getId(), dtoReq.getPrivId()))) {
            // 仲介業者名dropDownListのデータを取得
            DataList<IfaLoginIdUpdateRegisterSql004ResponseModel> sql004Res = getBrokerNameList(dtoReq.getBranchCode());
            response.setBrokerNameList(setBrokerNameList(sql004Res));
        }
        
        resDto.add(response);
        return IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
        
    }
    
    /**
     * アクションID：A004
     * アクション名：仲介業者名更新
     * Dto リクエスト：IfaLoginIdUpdateRegisterA004DtoRequest
     * Dto レスポンス：IfaLoginIdUpdateRegisterA004DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdUpdateRegisterA004DtoResponse> brokerNameUpdateA004(
            IfaLoginIdUpdateRegisterA004DtoRequest dtoReq) throws Exception {
        
        List<IfaLoginIdUpdateRegisterA004DtoResponse> resDto = new ArrayList<IfaLoginIdUpdateRegisterA004DtoResponse>();
        IfaLoginIdUpdateRegisterA004DtoResponse response = new IfaLoginIdUpdateRegisterA004DtoResponse();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaLoginIdUpdateRegisterServiceImplL.brokerNameUpdateA004");
        
        //利用者権限が「SBI証券本店」（権限=1）でない場合エラーを返却
        if (!CheckHeadOfiice()) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE));
        }
        // 所属コードが6:仲介業者支店(内部管理責任者), 7:仲介業者支店(営業責任者), 8:仲介業者支店(外務員), 9:担当
        if (CALL_A004_PRIV_ID.contains(dtoReq.getPrivId())) {
            // 仲介業者支店名dropDownListを更新
            DataList<IfaLoginIdUpdateRegisterSql005ResponseModel> sql005Res = getBranchNameList(
                    dtoReq.getBrokerCode());
            response.setBranchNameList(setBranchNameList(sql005Res));
        }
        //  所属コードが3:仲介業者(内部管理責任者), 4:仲介業者(営業責任者),5:仲介業者(外務員)
        if (PRIV_ID_INTERMEDIARY.contains(dtoReq.getPrivId())) {
            DataList<IfaLoginIdUpdateRegisterSql006ResponseModel> sql006Res = getChargeNameList(dtoReq.getBrokerCode(),
                    dtoReq.getSubBrokerId());
            response.setChargeNameList(setChargeNameList(sql006Res));
        }
        
        resDto.add(response);
        return IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
    }
    
    /**
     * アクションID：A005
     * アクション名：仲介業者支店名更新
     * Dto リクエスト：IfaLoginIdUpdateRegisterA005DtoRequest
     * Dto レスポンス：IfaLoginIdUpdateRegisterA005DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdUpdateRegisterA005DtoResponse> brokerBranchNameUpdateA005(
            IfaLoginIdUpdateRegisterA005DtoRequest dtoReq) throws Exception {
        
        List<IfaLoginIdUpdateRegisterA005DtoResponse> resDto = new ArrayList<IfaLoginIdUpdateRegisterA005DtoResponse>();
        IfaLoginIdUpdateRegisterA005DtoResponse response = new IfaLoginIdUpdateRegisterA005DtoResponse();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaLoginIdUpdateRegisterServiceImplL.brokerBranchNameUpdateA005");
        
        //利用者権限が「SBI証券本店」（権限=1）でない場合エラーを返却
        if (!CheckHeadOfiice()) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE));
        }
        
        //A005処理
        DropDownListsDto ddLs = createChargeNameDropDownLists(dtoReq.getBrokerCode(), dtoReq.getSubBrokerId());
        //パラメータ設定
        response.setChargeNameList(ddLs.getChargeNameList());
        
        resDto.add(response);
        return IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
    }
    
    /**
     * アクションID：A006
     * アクション名：リセット
     * Dto リクエスト：IfaLoginIdUpdateRegisterA006DtoRequest
     * Dto レスポンス：IfaLoginIdUpdateRegisterA006DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdUpdateRegisterA006DtoResponse> resetA006(IfaLoginIdUpdateRegisterA006DtoRequest dtoReq)
            throws Exception {
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaLoginIdUpdateRegisterServiceImplL.resetA006");
        
        List<IfaLoginIdUpdateRegisterA006DtoResponse> resDto = new ArrayList<IfaLoginIdUpdateRegisterA006DtoResponse>();
        IfaLoginIdUpdateRegisterA006DtoResponse response = new IfaLoginIdUpdateRegisterA006DtoResponse();
        
        // 初期化処理
        DropDownListsDto ddlList = initializeAllDropDownLists(dtoReq.getPrivId(), dtoReq.getBranchCode(),
                dtoReq.getBrokerCode(), dtoReq.getSubBrokerId(), dtoReq.getEmployeeId(), dtoReq.getLoginId(),
                dtoReq.getMenuList());
        
        response.setHeadOfficeBranchNameList(ddlList.getHeadOfficeBranchNameList());
        response.setBrokerNameList(ddlList.getBrokerNameList());
        response.setBranchNameList(ddlList.getBranchNameList());
        response.setChargeNameList(ddlList.getChargeNameList());
        response.setDisplayList(ddlList.getDisplayList());
        response.setUnDisplayList(ddlList.getUnDisplayList());
        
        resDto.add(response);
        return IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
    }
    
    /**
     * アクションID：A014
     * アクション名：更新（OK）
     * Dto リクエスト：IfaLoginIdUpdateRegisterA014DtoRequest
     * Dto レスポンス：IfaLoginIdUpdateRegisterA014DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdUpdateRegisterA014DtoResponse> updateOkA014(IfaLoginIdUpdateRegisterA014DtoRequest dtoReq)
            throws Exception {
        
        List<IfaLoginIdUpdateRegisterA014DtoResponse> resDto = new ArrayList<IfaLoginIdUpdateRegisterA014DtoResponse>();
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaLoginIdUpdateRegisterServiceImplL.updateOkA014");
        //利用者権限が「SBI証券本店」（権限=1）でない場合エラーを返却
        if (!CheckHeadOfiice()) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE));
        }
        
        //リクエスト.ログインIDが存在しない場合、エラーメッセージを表示
        if (StringUtil.isNullOrEmpty(dtoReq.getLoginId())) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_EMPTY_RECORD,
                    IfaCommonUtil.getMessage(ERRORS_EMPTY_RECORD, new String[] { dtoReq.getLoginId() }));
        }
        //更新項目編集
        //パスワード再設定の場合、パスワードを暗号化
        String passwordHash = StringUtil.EMPTY_STRING;
        if (!StringUtil.isNullOrEmpty(dtoReq.getPassword())) {
            passwordHash = EncryptPassword.encrypt(dtoReq.getPassword());
        }
        //CCS IDを設定
        String ccsId = StringUtil.EMPTY_STRING;
        String ccsPw = StringUtil.EMPTY_STRING;
        //SQL011
        DataList<IfaLoginIdUpdateRegisterSql011ResponseModel> sql011Res = getHorusUserInfo(dtoReq.getLoginId());
        if (sql011Res != null) {
            if (!CollectionUtils.isEmpty(sql011Res.getDataList())) {
                ccsId = sql011Res.getDataList().get(0).getCcsUserId();
                ccsPw = sql011Res.getDataList().get(0).getCcsUserPw();
            }
        }
        
        //変更処理
        //パスワード再設定の場合、認証ユーザー情報を更新
        if (!StringUtils.equals(passwordHash, StringUtil.EMPTY_STRING)) {
            try {
                //SQL007
                updateAuthUserInfo(dtoReq.getLoginId());
            } catch (Exception e) {
                if (e instanceof AthenaBusinessException) {
                    return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL,
                            ((AthenaBusinessException) e).getErrorCode(), ((AthenaBusinessException) e).getMessage());
                }
            }
        }
        //ユーザ情報更新
        //SQL008
        updateUserInfo(dtoReq, ccsId, ccsPw, passwordHash);
        
        //Cordysユーザーとメニューマッピングの登録処理
        //指定ユーザーIDにあるメニューをTB_MED_GOV_MENUテーブルより削除(SQL009)
        deleteNewTable(dtoReq.getLoginId());
        //新しいユーザー情報をTB_MED_GOV_MENUテーブルに追加(SQL010)
        insertNewTable(dtoReq.getLoginId(), dtoReq.getDisplayList());
        
        return IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, INFO_LOGINID_UPDATE_COMPLETED,
                IfaCommonUtil.getMessage(INFO_LOGINID_UPDATE_COMPLETED));
    }
    
    /**
     * A014 CCS情報リセット処理部
     * 
     * @param loginId ログインID
     * @return 処理結果
     * @throws Exception
     */
    public DataList<Object> custMgmtUpdateCcsData(IfaLoginIdUpdateRegisterA014DeleteCcsDataDtoRequest dtoReq)
            throws Exception {
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaLoginIdUpdateRegisterServiceImplL.CustMgmtUpdateCcsData");
        
        updateCcsData(dtoReq.getLoginId());
        
        return IfaCommonUtil.createDataList(null, ErrorLevel.SUCCESS, INFO_LOGINID_UPDATE_COMPLETED,
                null);
    }
    
    /**利用者権限がSBI証券本店であるかのチェック
     * 
     */
    private boolean CheckHeadOfiice() {
        
        // ユーザ共通情報
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        // 利用者権限のチェック
        String userPrivId = userAccount.getPrivId();
        //利用者権限が「SBI証券本店」（権限=1）ならtrueを返却
        if (StringUtils.equals(PrivId.HEAD_OFFICE.getId(), userPrivId)) {
            return true;
        }
        return false;
    }
    
    /**
     * A001の処理を行う(全ドロップダウンリスト取得)
     * 
     * @param privId 権限コード
     * @param branchCode 本支店コード
     * @param brokerCode 仲介業者コード
     * @param subBrokerId 仲介業者支店コード
     * @param loginId ログインID
     * @param employeeId 担当者コード
     * @param menuList メニューリスト
     * @return A001の処理結果
     * @throws Exception
     */
    private DropDownListsDto initializeAllDropDownLists(String privId, String branchCode, String brokerCode,
            String subBrokerId, String employeeId, String loginId,
            List<IfaLoginIdUpdateRegisterDtoRequest_menuList> menuList)
            throws Exception {
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaLoginIdUpdateRegisterServiceImplL.initialDisplayA001");
        
        DropDownListsDto response = new DropDownListsDto();
        //非表示リスト
        List<IfaLoginIdUpdateRegisterDtoResponse_unDisplay> unDisplayList = new ArrayList<IfaLoginIdUpdateRegisterDtoResponse_unDisplay>();
        //表示リスト
        List<String> displayList = new ArrayList<String>();
        
        // 本店/支店名dropDownListのデータを取得
        response.setHeadOfficeBranchNameList(callHeadOfficeBranchDropDownList(privId));
        
        // 仲介業者名dropDownListのデータを取得
        if (!StringUtil.isNullOrEmpty(brokerCode)) {
            DataList<IfaLoginIdUpdateRegisterSql004ResponseModel> sql004Res = getBrokerNameList(branchCode);
            response.setBrokerNameList(setBrokerNameList(sql004Res));
        }
        
        // 仲介業者支店名dropDownListのデータを取得
        if (!StringUtil.isNullOrEmpty(brokerCode)) {
            DataList<IfaLoginIdUpdateRegisterSql005ResponseModel> sql005Res = getBranchNameList(brokerCode);
            response.setBranchNameList(setBranchNameList(sql005Res));
        }
        // 担当者名dropDownListを取得
        if (!StringUtil.isNullOrEmpty(employeeId)) {
            DataList<IfaLoginIdUpdateRegisterSql006ResponseModel> sql006Res = getChargeNameList(brokerCode,
                    subBrokerId);
            response.setChargeNameList(setChargeNameList(sql006Res));
        }
        
        //SQL001の実行結果からmenuListの各値をdisplayListとunDisplayListに振り分けて格納
        setDisplayUndisplayList(loginId, menuList, displayList, unDisplayList, true);
        // 表示リスト
        response.setDisplayList(displayList);
        // 非表示リスト
        response.setUnDisplayList(unDisplayList);
        
        return response;
    }
    
    /**
     * A001とA002の共通処理(権限コードが業務部・管理部でない場合SQL003を呼ぶ処理)
     * 
     * @param privId 権限コード
     * @param branchCode 本支店コード
     * @param brokerCode 仲介業者コード
     * @param subBrokerId 仲介業者支店コード
     * @return A003の出力結果
     * @throws Exception
     */
    private List<IfaLoginIdUpdateRegisterDtoResponse_headOfficeBranchName> callHeadOfficeBranchDropDownList(
            String privId) throws Exception {
        
        //本店支店名リスト
        List<IfaLoginIdUpdateRegisterDtoResponse_headOfficeBranchName> headOfficeBranchNameList = new ArrayList<IfaLoginIdUpdateRegisterDtoResponse_headOfficeBranchName>();
        //権限コードが業務部や管理部でないなら、以下の処理を行う
        if (!StringUtils.equals(PrivId.ES_BUSSINESS.getId(), privId)
                && !StringUtils.equals(PrivId.ES_MANAGEMENT.getId(), privId)) {
            //本店／支店名dropDownListのデータを取得
            //本支店種別
            String branchKind = null;
            //権限コードが1:本店の場合、本支店種別は"00"を設定
            if (StringUtils.equals(PrivId.HEAD_OFFICE.getId(), privId)) {
                branchKind = BRANCHKIND_HEAD_OFFICE;
                
            }
            //権限コードが >=2:支店の場合、本支店種別は"01"を設定
            else if (PrivId.valueOfId(privId) != null) {
                branchKind = BRANCHKIND_BRANCH;
            }
            
            //SQL003を呼び、本店/支店名dropDownListを取得
            DataList<IfaLoginIdUpdateRegisterSql003ResponseModel> sql003Res = getHeadOfficeBranchDropDownList(
                    branchKind);
            headOfficeBranchNameList = setHeadOfficeBranchNameList(sql003Res);
        }
        
        return headOfficeBranchNameList;
    }
    
    /**
     * A001とA002の共通処理(表示リスト/非表示リストの作成)
     * 
     * @param loginId ログインID
     * @param menuList メニューリスト
     * @param displayList　表示リスト
     * @param unDisplayList　非表示リスト
     * @throws Exception
     */
    private void setDisplayUndisplayList(String loginId, List<IfaLoginIdUpdateRegisterDtoRequest_menuList> menuList,
            List<String> displayList,
            List<IfaLoginIdUpdateRegisterDtoResponse_unDisplay> unDisplayList, boolean isExecuteSql001)
            throws Exception {
        
        List<String> menuIdList = getMenuCodeList(loginId, isExecuteSql001);
        
        //メニュータイトルリストの表示/非表示を設定する
        
        if (menuList != null) {
            for (IfaLoginIdUpdateRegisterDtoRequest_menuList m : menuList) {
                //SQL001のメニューIDリストに含まれていれば表示、含まれていなければ非表示
                if (menuIdList.contains(m.getMenuId())) {
                    displayList.add(m.getMenuId());
                }
                
                IfaLoginIdUpdateRegisterDtoResponse_unDisplay unDisplay = new IfaLoginIdUpdateRegisterDtoResponse_unDisplay();
                unDisplay.setKey(m.getMenuId());
                unDisplay.setLabel(m.getMenuTitle());
                unDisplayList.add(unDisplay);
                    
            }
        }
        
    }
    
    /**
     * A005の処理を行う(担当者名ドロップダウンリスト取得)
     * @param brokerCode 仲介業者コード
     * @param subBrokerId 仲介業者支店コード
     * @return A005の処理結果
     * @throws Exception
     */
    private DropDownListsDto createChargeNameDropDownLists(String brokerCode, String subBrokerId) throws Exception {
        
        DropDownListsDto response = new DropDownListsDto();
        
        //担当者名リスト
        List<IfaLoginIdUpdateRegisterDtoResponse_chargeName> chargeNameList = new ArrayList<IfaLoginIdUpdateRegisterDtoResponse_chargeName>();
        
        //担当者名dropDownListを取得
        DataList<IfaLoginIdUpdateRegisterSql006ResponseModel> sql006Res = getChargeNameList(brokerCode, subBrokerId);
        chargeNameList = setChargeNameList(sql006Res);
        
        response.setChargeNameList(chargeNameList);
        return response;
    }
    
    /**
     * SQL001 メニュー一覧を取得
     * 
     * @param attrValue ログインID
     * @return SQL001の出力結果
     * @throws Exception
     */
    
    private List<String> getMenuCodeList(String attrValue, boolean isExecuteSql001) throws Exception {
        
        List<String> menuIdList = new ArrayList<String>();
        
        if (isExecuteSql001) {
            IfaLoginIdUpdateRegisterSql001RequestModel sql001Req = new IfaLoginIdUpdateRegisterSql001RequestModel();
            
            // 部店コード（半角英数字）
            sql001Req.setLoginId(attrValue);
            DataList<IfaLoginIdUpdateRegisterSql001ResponseModel> sql001Res = dao
                    .selectIfaLoginIdUpdateRegisterSql001(sql001Req);
            if (sql001Res != null && !CollectionUtils.isEmpty(sql001Res.getDataList())) {
                menuIdList = sql001Res.getDataList().stream().map(d -> d.getMenuId()).collect(Collectors.toList());
            }
            
        } else {
            IfaLoginIdUpdateRegisterSql013RequestModel sql013Req = new IfaLoginIdUpdateRegisterSql013RequestModel();
            
            // 部店コード（半角英数字）
            sql013Req.setPrivId(attrValue);
            DataList<IfaLoginIdUpdateRegisterSql013ResponseModel> sql013Res = dao
                    .selectIfaLoginIdUpdateRegisterSql013(sql013Req);
            if (sql013Res != null && !CollectionUtils.isEmpty(sql013Res.getDataList())) {
                menuIdList = sql013Res.getDataList().stream().map(d -> d.getMenuId()).collect(Collectors.toList());
            }
        }
        
        
        
        return menuIdList;
    }
    
    /**
     * SQL003 本支店DropDownListを取得
     * 
     * @param branchKind 本支店種別
     * @return SQL003の出力結果
     * @throws Exception
     */
    
    private DataList<IfaLoginIdUpdateRegisterSql003ResponseModel> getHeadOfficeBranchDropDownList(String branchKind)
            throws Exception {
        
        IfaLoginIdUpdateRegisterSql003RequestModel sql003Req = new IfaLoginIdUpdateRegisterSql003RequestModel();
        
        // 本支店種別
        sql003Req.setBranchKind(branchKind);
        
        return dao.selectIfaLoginIdUpdateRegisterSql003(sql003Req);
    }
    
    /**
     * SQL003から取得した値を本店/支店名リストに設定
     * 
     * @param sql003Res　SQL003の出力結果
     * @return headOfficeBranchNameList　本店/支店名リスト
     * @throws Exception
     */
    private List<IfaLoginIdUpdateRegisterDtoResponse_headOfficeBranchName> setHeadOfficeBranchNameList(
            DataList<IfaLoginIdUpdateRegisterSql003ResponseModel> sql003Res) {
        
        List<IfaLoginIdUpdateRegisterDtoResponse_headOfficeBranchName> headOfficeBranchNameList = new ArrayList<IfaLoginIdUpdateRegisterDtoResponse_headOfficeBranchName>();
        if (sql003Res == null) {
            return headOfficeBranchNameList;
        }
        if (CollectionUtils.isEmpty(sql003Res.getDataList())) {
            return headOfficeBranchNameList;
        }
        for (IfaLoginIdUpdateRegisterSql003ResponseModel s : sql003Res.getDataList()) {
            IfaLoginIdUpdateRegisterDtoResponse_headOfficeBranchName headOfficeBranchName = new IfaLoginIdUpdateRegisterDtoResponse_headOfficeBranchName();
            headOfficeBranchName.setKey(s.getBranchCode());
            headOfficeBranchName.setValue(s.getBranchName());
            headOfficeBranchNameList.add(headOfficeBranchName);
        }
        
        return headOfficeBranchNameList;
    }
    
    /**
     * SQL004 本支店DropDownListを取得
     * 
     * @param branchKind 本支店種別
     * @return SQL003の出力結果
     * @throws Exception
     */
    
    private DataList<IfaLoginIdUpdateRegisterSql004ResponseModel> getBrokerNameList(String branchCode)
            throws Exception {
        
        IfaLoginIdUpdateRegisterSql004RequestModel sql004Req = new IfaLoginIdUpdateRegisterSql004RequestModel();
        
        // 本支店コード
        sql004Req.setBranchCode(branchCode);
        
        return dao.selectIfaLoginIdUpdateRegisterSql004(sql004Req);
    }
    
    /**
     * SQL004から取得した値を仲介業者名リストに設定
     * 
     * @param sql004Res　SQL004の出力結果
     * @return headOfficeBranchNameList　仲介業者名リスト
     * @throws Exception
     */
    private List<IfaLoginIdUpdateRegisterDtoResponse_brokerName> setBrokerNameList(
            DataList<IfaLoginIdUpdateRegisterSql004ResponseModel> sql004Res) {
        
        List<IfaLoginIdUpdateRegisterDtoResponse_brokerName> brokerNameList = new ArrayList<IfaLoginIdUpdateRegisterDtoResponse_brokerName>();
        if (sql004Res == null) {
            return brokerNameList;
        }
        if (CollectionUtils.isEmpty(sql004Res.getDataList())) {
            return brokerNameList;
        }
        for (IfaLoginIdUpdateRegisterSql004ResponseModel s : sql004Res.getDataList()) {
            IfaLoginIdUpdateRegisterDtoResponse_brokerName brokerName = new IfaLoginIdUpdateRegisterDtoResponse_brokerName();
            brokerName.setKey(s.getBrokerCode());
            brokerName.setValue(s.getBranchName());
            brokerNameList.add(brokerName);
        }
        
        return brokerNameList;
    }
    
    /**
     * SQL005 本支店DropDownListを取得
     * 
     * @param branchKind 本支店種別
     * @return SQL005の出力結果
     * @throws Exception
     */
    
    private DataList<IfaLoginIdUpdateRegisterSql005ResponseModel> getBranchNameList(String brokerCode)
            throws Exception {
        
        IfaLoginIdUpdateRegisterSql005RequestModel sql005Req = new IfaLoginIdUpdateRegisterSql005RequestModel();
        
        // 仲介業者コード
        sql005Req.setBrokerCode(brokerCode);
        
        return dao.selectIfaLoginIdUpdateRegisterSql005(sql005Req);
    }
    
    /**
     * SQL005から取得した値を仲介業者支店名リストに設定
     * 
     * @param sql005Res　SQL005の出力結果
     * @return branchNameList 仲介業者支店名リスト
     * @throws Exception
     */
    private List<IfaLoginIdUpdateRegisterDtoResponse_branchName> setBranchNameList(
            DataList<IfaLoginIdUpdateRegisterSql005ResponseModel> sql005Res) {
        
        List<IfaLoginIdUpdateRegisterDtoResponse_branchName> branchNameList = new ArrayList<IfaLoginIdUpdateRegisterDtoResponse_branchName>();
        if (sql005Res == null) {
            return branchNameList;
        }
        if (CollectionUtils.isEmpty(sql005Res.getDataList())) {
            return branchNameList;
        }
        for (IfaLoginIdUpdateRegisterSql005ResponseModel s : sql005Res.getDataList()) {
            IfaLoginIdUpdateRegisterDtoResponse_branchName branchName = new IfaLoginIdUpdateRegisterDtoResponse_branchName();
            branchName.setKey(s.getBrokerBranchCode());
            branchName.setValue(s.getBranchName());
            branchNameList.add(branchName);
        }
        
        return branchNameList;
    }
    
    /**
     * SQL006 本支店DropDownListを取得
     * 
     * @param branchKind 本支店種別
     * @return SQL006の出力結果
     * @throws Exception
     */
    
    private DataList<IfaLoginIdUpdateRegisterSql006ResponseModel> getChargeNameList(String brokerCode,
            String subBrokerId) throws Exception {
        
        IfaLoginIdUpdateRegisterSql006RequestModel sql006Req = new IfaLoginIdUpdateRegisterSql006RequestModel();
        
        // 仲介業者コード
        sql006Req.setBrokerCode(brokerCode);
        // 仲介業者支店コード
        sql006Req.setSubBrokerId(subBrokerId);
        
        return dao.selectIfaLoginIdUpdateRegisterSql006(sql006Req);
    }
    
    /**
     * SQL006から取得した値を担当者名リストに設定
     * 
     * @param sql006Res　SQL006の出力結果
     * @return chargeNameList 担当者名リスト
     * @throws Exception
     */
    private List<IfaLoginIdUpdateRegisterDtoResponse_chargeName> setChargeNameList(
            DataList<IfaLoginIdUpdateRegisterSql006ResponseModel> sql006Res) {
        
        List<IfaLoginIdUpdateRegisterDtoResponse_chargeName> chargeNameList = new ArrayList<IfaLoginIdUpdateRegisterDtoResponse_chargeName>();
        
        if (sql006Res == null) {
            return chargeNameList;
        }
        if (CollectionUtils.isEmpty(sql006Res.getDataList())) {
            return chargeNameList;
        }
        for (IfaLoginIdUpdateRegisterSql006ResponseModel s : sql006Res.getDataList()) {
            IfaLoginIdUpdateRegisterDtoResponse_chargeName chargeName = new IfaLoginIdUpdateRegisterDtoResponse_chargeName();
            chargeName.setKey(s.getBrokerChargeCode());
            chargeName.setValue(s.getBrokerChargeName());
            chargeNameList.add(chargeName);
        }
        
        return chargeNameList;
    }
    
    /**
     * SQL007 認証ユーザー情報を更新
     * 
     * @param loginId ログインID
     * @return 更新処理結果
     * @throws Exception
     */
    
    private int updateAuthUserInfo(String loginId) throws Exception {
        
        IfaLoginIdUpdateRegisterSql007RequestModel sql007Req = new IfaLoginIdUpdateRegisterSql007RequestModel();
        
        // ログインID
        sql007Req.setLoginId(loginId);
        
        return dao.updateIfaLoginIdUpdateRegisterSql007(sql007Req);
    }
    
    /**
     * SQL008 ユーザ情報を更新
     * 
     * @param dtoReq A014のリクエスト
     * @param ccsId　A014内で取得したCCSID
     * @param ccsPw A014内で取得したCCSPW
     * @return 更新処理結果
     * @throws Exception
     */
    
    private int updateUserInfo(IfaLoginIdUpdateRegisterA014DtoRequest dtoReq, String ccsId, String ccsPw,
            String passwordHash) throws Exception {
        
        IfaLoginIdUpdateRegisterSql008RequestModel sql008Req = new IfaLoginIdUpdateRegisterSql008RequestModel();
        // ユーザ共通情報
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        // ログインID
        sql008Req.setLoginId(dtoReq.getLoginId());
        // ユーザ名
        sql008Req.setUserName(dtoReq.getUserName());
        // パスワード
        if (!StringUtil.isNullOrEmpty(passwordHash)) {
            sql008Req.setPassword(passwordHash);
        }
        // 所属権限コード
        sql008Req.setPrivId(dtoReq.getPrivId());
        // 本支店コード
        sql008Req.setBranchCode(dtoReq.getBranchCode());
        // 仲介業者コード
        sql008Req.setBrokerCode(dtoReq.getBrokerCode());
        // 仲介業者支店コード
        sql008Req.setSubBrokerId(dtoReq.getSubBrokerId());
        // 担当者コード
        sql008Req.setEmployeeId(dtoReq.getEmployeeId());
        // 担当者名
        sql008Req.setChargeName(dtoReq.getChargeName());
        
        // 管理者フラグ
        String governorFlag = StringUtil.EMPTY_STRING;
        if (StringUtils.equals(dtoReq.getPrivId(), PrivId.HEAD_OFFICE.getId())) {
            governorFlag = GOVERNOR_FALAG_TRUE;
        } else {
            governorFlag = GOVERNOR_FALAG_FALSE;
        }
        sql008Req.setGovernorFlag(governorFlag);
        // CCSID
        sql008Req.setCcsId(ccsId);
        // CCSPW
        sql008Req.setCcsPw(ccsPw);
        // ユーザID
        sql008Req.setUserId(userAccount.getUserId());
        // メールアドレス
        sql008Req.setMailAddress(dtoReq.getMailAddress());
        
        return dao.updateIfaLoginIdUpdateRegisterSql008(sql008Req);
    }
    
    /**
     * SQL009 Cordysユーザーとメニューマッピング情報_新テーブル削除
     * 
     * @param loginId ログインID
     * @return 削除処理結果
     * @throws Exception
     */
    private int deleteNewTable(String loginId) throws Exception {
        
        IfaLoginIdUpdateRegisterSql009RequestModel sql009Req = new IfaLoginIdUpdateRegisterSql009RequestModel();
        
        // ログインID
        sql009Req.setLoginId(loginId);
        
        return dao.deleteIfaLoginIdUpdateRegisterSql009(sql009Req);
    }
    
    /**
     * SQL010 Cordysユーザーとメニューマッピング情報_新テーブル登録
     * 
     * @param loginId ログインID
     * @param menuCode メニューコード
     * @return 登録処理結果
     * @throws Exception
     */
    private int insertNewTable(String loginId, List<String> display)
            throws Exception {
        
        IfaLoginIdUpdateRegisterSql010RequestModel sql010Req = new IfaLoginIdUpdateRegisterSql010RequestModel();
        // ユーザ共通情報
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        // ログインID
        sql010Req.setLoginId(loginId);
        // ユーザID
        sql010Req.setUserId(userAccount.getUserId());
        // 表示リスト
        sql010Req.setDisplayList(display);
        
        return dao.insertIfaLoginIdUpdateRegisterSql010(sql010Req);
    }
    
    /**
     * SQL011 Horusユーザー情報取得
     * 
     * @param loginId ログインID
     * @return SQL011の出力結果
     * @throws Exception
     *
     **/
    
    private DataList<IfaLoginIdUpdateRegisterSql011ResponseModel> getHorusUserInfo(String loginId) throws Exception {
        
        IfaLoginIdUpdateRegisterSql011RequestModel sql011Req = new IfaLoginIdUpdateRegisterSql011RequestModel();
        
        // ログインID
        sql011Req.setLoginId(loginId);
        
        return dao.selectIfaLoginIdUpdateRegisterSql011(sql011Req);
    }
    
    /**
     * SQL012 CCS情報リセット 
     * 
     * @param loginId ログインID
     * @return 更新処理結果
     * @throws Exception
     */
    
    public int updateCcsData(String loginId) throws Exception {
        
        IfaLoginIdUpdateRegisterSql012RequestModel sql012Req = new IfaLoginIdUpdateRegisterSql012RequestModel();
        // ログインID
        sql012Req.setLoginId(loginId);
        
        return dao.updateIfaLoginIdUpdateRegisterSql012(sql012Req);
    }
    
}
