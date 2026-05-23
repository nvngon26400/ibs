package com.sbisec.helios.ap.systemManageMenu.loginUserManage.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.PrivId;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.IfaRepAddDao;

import com.sbisec.helios.ap.systemManageMenu.loginUserManage.model.IfaRepAddSql001ModelRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.model.IfaRepAddSql001ModelResponse;

import com.sbisec.helios.ap.systemManageMenu.loginUserManage.model.IfaRepAddSql002ModelRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.model.IfaRepAddSql002ModelResponse;

import com.sbisec.helios.ap.systemManageMenu.loginUserManage.model.IfaRepAddSql003ModelRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.model.IfaRepAddSql003ModelResponse;

import com.sbisec.helios.ap.systemManageMenu.loginUserManage.model.IfaRepAddSql004ModelRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.model.IfaRepAddSql004ModelResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.model.IfaRepAddSql005ModelRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.model.IfaRepAddSql006ModelRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.model.IfaRepAddSql006ModelResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA001DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA001DtoRequestBranchName;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA001DtoRequestBrokerBranchName;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA001DtoRequestBrokerName;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA001DtoRequestEmployeeName;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA001DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA002DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA002DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA003DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA003DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA005DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA005DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA006DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA006DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA008DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaRepAddA008DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.service.IfaRepAddService;

/**
 * 画面ID：SUB0601_01-06_1
 * 画面名：担当者追加
 *
 * @author 島崎 聡士 2023/09/08 新規作成
 */
@Component(value = "cmpIfaRepAddService")
public class IfaRepAddServiceImpL implements IfaRepAddService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaRepAddServiceImpL.class);
    
    /** アクセス権限なしエラー */
    private static final String ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE =
            "errors.cmn.loginUsers.insufficientPrivilege";
    
    /** 担当者情報重複エラー */
    private static final String ERRORS_EMP_LOGINUSERS_EXIST = "errors.emp.loginUsers.exist";
    
    /** Horusユーザー権限情報追加：正常終了 */
    private static final String INFO_INSERTCOMPLETED = "info.insertCompleted";
    
    /** SQL002実行前権限コードチェックリスト */
    private static final List<String> PRIVID_CHECK_LIST_002 = Arrays.asList(PrivId.BB_INNER_MANAGEMENT.getId(),
            PrivId.BB_SALES_EXECUTIVE.getId(), PrivId.BB_SALES_REPRESENTATIVE.getId(), PrivId.RESPONSIBLE.getId());
    
    /** SQL003実行前権限コードチェックリスト */
    private static final List<String> PRIVID_CHECK_LIST_003 = Arrays.asList(PrivId.B_INNER_MANAGEMENT.getId(),
            PrivId.B_SALES_EXECUTIVE.getId(), PrivId.B_SALES_REPRESENTATIVE.getId());
    
    /** SQL006：本支店種別_仲介業者本支店 */
    private static final String BRANCH_KIND = "01";
    
    /** SQL実行不要時の設定値 */
    private static final String DO_NOT_EXECUTE_SQL = "-1";
    
    @Autowired
    private IfaRepAddDao dao;
    
    /**
     * アクションID：A001
     * アクション名：初期表示
     * Dto リクエスト：IfaRepAddA001DtoRequest
     * Dto レスポンス：IfaRepAddA001DtoResponse
     * model リクエスト：IfaRepAddSql001RequestModel
     * model レスポンス：IfaRepAddSql001ResponseModel
     *
     * @param dtoReq Dto リクエスト
     * @return Dto レスポンス
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaRepAddA001DtoResponse> initialDisplayA001(IfaRepAddA001DtoRequest dtoReq) throws Exception {
        
        List<IfaRepAddA001DtoResponse> dtoResList = new ArrayList<>();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaRepAddServiceImplL.initialDisplayA001");
        }
        // ①利用者権限のチェック
        if (isNotHeadOffice()) {
            // 業務エラーメッセージの取得
            String message = IfaCommonUtil.getMessage(ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE);
            // エラーレベルとメッセージの設定
            return IfaCommonUtil.createDataList(
                    dtoResList, ErrorLevel.FATAL, ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE, message);
        }
        // ②プルダウン情報の取得
        IfaRepAddSql006ModelRequest sql006Req = new IfaRepAddSql006ModelRequest();
        // プレースホルダの設定
        sql006Req.setBranchKind(BRANCH_KIND);
        // 本店／支店名dropDownListのデータを取得
        DataList<IfaRepAddSql006ModelResponse> sql006Res = dao.selectIfaRepAddSql006(sql006Req);
        // レスポンスの設定
        IfaRepAddA001DtoResponse ifaRepAddA001DtoResponse =
                editIfaRepAddA001DtoResponse(dtoReq, editBranchNameList(sql006Res));
        dtoResList.add(ifaRepAddA001DtoResponse);
        return IfaCommonUtil.createDataList(
                dtoResList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
    }
    
    /**
     * アクションID：A002
     * アクション名：仲介業者名更新
     * Dto リクエスト：IfaRepAddA002DtoRequest
     * Dto レスポンス：IfaRepAddA002DtoResponse
     * model リクエスト：IfaRepAddSql002RequestModel
     * model レスポンス：IfaRepAddSql002ResponseModel
     *
     * @param dtoReq Dto リクエスト
     * @return Dto レスポンス
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaRepAddA002DtoResponse> brokerNameUpdateA002(IfaRepAddA002DtoRequest dtoReq) throws Exception {
        
        List<IfaRepAddA002DtoResponse> dtoResList = new ArrayList<>();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaRepAddServiceImplL.brokerNameUpdateA002");
        }
        // ①利用者権限のチェック
        if (isNotHeadOffice()) {
            // 業務エラーメッセージの取得
            String message = IfaCommonUtil.getMessage(ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE);
            // エラーレベルとメッセージの設定
            return IfaCommonUtil.createDataList(
                    dtoResList, ErrorLevel.FATAL, ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE, message);
        }
        // プレースホルダの設定
        IfaRepAddSql002ModelRequest sql002Req = new IfaRepAddSql002ModelRequest();
        sql002Req.setBrokerCode(dtoReq.getBrokerCode());
        // 仲介業者支店名dropDownListを更新
        DataList<IfaRepAddSql002ModelResponse> sql002Res = excuteSql002(sql002Req);
        // SQL結果をレスポンスに設定
        IfaRepAddA002DtoResponse dtoResponse = new IfaRepAddA002DtoResponse();
        dtoResponse.setBrokerBranchNameList(editBrokerBranchNameList(sql002Res));
        
        // プレースホルダの設定
        IfaRepAddSql003ModelRequest sql003Req = new IfaRepAddSql003ModelRequest();
        sql003Req.setBrokerCode(dtoReq.getBrokerCode());
        sql003Req.setSubBrokerId(DO_NOT_EXECUTE_SQL);
        // 担当者名dropDownListを更新
        DataList<IfaRepAddSql003ModelResponse> sql003Res = excuteSql003(sql003Req);
        // SQL結果をレスポンスに設定
        dtoResponse.setEmployeeNameList(editEmployeeNameList(sql003Res));
        // レスポンスの設定
        dtoResList.add(dtoResponse);
        
        return IfaCommonUtil.createDataList(
                dtoResList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
    }
    
    /**
     * アクションID：A003
     * アクション名：仲介業者支店名更新
     * Dto リクエスト：IfaRepAddA003DtoRequest
     * Dto レスポンス：IfaRepAddA003DtoResponse
     * model リクエスト：IfaRepAddSql003RequestModel
     * model レスポンス：IfaRepAddSql003ResponseModel
     *
     * @param dtoReq Dto リクエスト
     * @return Dto レスポンス
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaRepAddA003DtoResponse> brokerBranchNameUpdateA003(IfaRepAddA003DtoRequest dtoReq)
            throws Exception {
        
        List<IfaRepAddA003DtoResponse> dtoResList = new ArrayList<>();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaRepAddServiceImplL.brokerBranchNameUpdateA003");
        }
        // ①利用者権限のチェック
        if (isNotHeadOffice()) {
            // 業務エラーメッセージの取得
            String message = IfaCommonUtil.getMessage(ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE);
            // エラーレベルとメッセージの設定
            return IfaCommonUtil.createDataList(
                    dtoResList, ErrorLevel.FATAL, ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE, message);
        }
        // プレースホルダの設定
        IfaRepAddSql003ModelRequest sql003Req = new IfaRepAddSql003ModelRequest();
        sql003Req.setBrokerCode(dtoReq.getBrokerCode());
        sql003Req.setSubBrokerId(dtoReq.getSubBrokerId());
        // 担当者名dropDownListを更新
        DataList<IfaRepAddSql003ModelResponse> sql003Res = excuteSql003(sql003Req);
        // SQL結果をレスポンスに設定
        IfaRepAddA003DtoResponse dtoResponse = new IfaRepAddA003DtoResponse();
        dtoResponse.setEmployeeNameList(editEmployeeNameList(sql003Res));
        // レスポンスの設定
        dtoResList.add(dtoResponse);

        return IfaCommonUtil.createDataList(
                dtoResList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
    }
    
    /**
     * アクションID：A005
     * アクション名：追加
     * Dto リクエスト：IfaRepAddA005DtoRequest
     * Dto レスポンス：IfaRepAddA005DtoResponse
     * model リクエスト：IfaRepAddSql004RequestModel
     * model レスポンス：IfaRepAddSql004ResponseModel
     *
     * @param dtoReq Dto リクエスト
     * @return Dto レスポンス
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaRepAddA005DtoResponse> addA005(IfaRepAddA005DtoRequest dtoReq) throws Exception {
        
        List<IfaRepAddA005DtoResponse> dtoResList = new ArrayList<>();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaRepAddServiceImplL.addA005");
        }
        // プレースホルダの設定
        IfaRepAddSql004ModelRequest sql004Req = new IfaRepAddSql004ModelRequest();
        sql004Req.setUserId(dtoReq.getUserId());
        sql004Req.setBrokerCode(dtoReq.getBrokerCode());
        sql004Req.setEmployeeId(dtoReq.getEmployeeId());
        // SQL004の実行
        DataList<IfaRepAddSql004ModelResponse> sql004Res = excuteSql004(sql004Req);
        int searchResultCount = sql004Res.getDataList().get(0).getSearchResultCount();
        // ②存在チェック
        if (searchResultCount > 0) {
            // 業務エラーメッセージの取得
            String message = IfaCommonUtil.getMessage(ERRORS_EMP_LOGINUSERS_EXIST);
            // エラーレベルとメッセージの設定
            return IfaCommonUtil.createDataList(
                    dtoResList, ErrorLevel.FATAL, ERRORS_EMP_LOGINUSERS_EXIST, message);
        }
        // レスポンスの設定
        IfaRepAddA005DtoResponse dtoResponse = new IfaRepAddA005DtoResponse();
        dtoResponse.setSearchResultCount(searchResultCount);
        dtoResList.add(dtoResponse);
        return IfaCommonUtil.createDataList(
                dtoResList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
    }
    
    /**
     * アクションID：A006
     * アクション名：追加（OK）
     * Dto リクエスト：IfaRepAddA006DtoRequest
     * Dto レスポンス：IfaRepAddA006DtoResponse
     * model リクエスト：IfaRepAddSql005ModelRequest
     * model レスポンス：IfaRepAddSql005ResponseModel
     *
     * @param dtoReq Dto リクエスト
     * @return Dto レスポンス
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaRepAddA006DtoResponse> additionOkA006(IfaRepAddA006DtoRequest dtoReq) throws Exception {
        
        List<IfaRepAddA006DtoResponse> dtoResList = new ArrayList<>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaRepAddServiceImplL.additionOkA006");
        }
        // ①利用者権限のチェック
        if (isNotHeadOffice()) {
            // 業務エラーメッセージの取得
            String message = IfaCommonUtil.getMessage(ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE);
            // エラーレベルとメッセージの設定
            return IfaCommonUtil.createDataList(
                    dtoResList, ErrorLevel.FATAL, ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE, message);
        }
        // プレースホルダの設定
        IfaRepAddSql004ModelRequest sql004Req = new IfaRepAddSql004ModelRequest();
        sql004Req.setUserId(dtoReq.getUserId());
        sql004Req.setBrokerCode(dtoReq.getBrokerCode());
        sql004Req.setEmployeeId(dtoReq.getEmployeeId());
        // SQL004の実行
        DataList<IfaRepAddSql004ModelResponse> sql004Res = excuteSql004(sql004Req);
        int searchResultCountSql004 = sql004Res.getDataList().get(0).getSearchResultCount();
        // 
        if (searchResultCountSql004 > 0) {
            // 業務エラーメッセージの取得
            String message = IfaCommonUtil.getMessage(ERRORS_EMP_LOGINUSERS_EXIST);
            // エラーレベルとメッセージの設定
            return IfaCommonUtil.createDataList(
                    dtoResList, ErrorLevel.FATAL, ERRORS_EMP_LOGINUSERS_EXIST, message);
        }
        // プレースホルダの設定
        IfaRepAddSql005ModelRequest sql005Req = new IfaRepAddSql005ModelRequest();
        sql005Req.setUserId(dtoReq.getUserId());
        sql005Req.setSbiSecurityCode(dtoReq.getSbiSecurityCode());
        sql005Req.setBrokerCode(dtoReq.getBrokerCode());
        sql005Req.setSubBrokerId(dtoReq.getSubBrokerId());
        sql005Req.setEmployeeId(dtoReq.getEmployeeId());
        sql005Req.setChargeName(dtoReq.getChargeName());
        // SQL005の実行
        int sqlResult = excuteSql005(sql005Req);
        IfaRepAddA006DtoResponse dtoRes = new IfaRepAddA006DtoResponse();
        dtoRes.setSearchResultCount(searchResultCountSql004);
        dtoResList.add(dtoRes);
        // 正常終了：完了メッセージを設定する。
        if (sqlResult > 0) {
            String message = IfaCommonUtil.getMessage(INFO_INSERTCOMPLETED, new String[] {"担当者情報"});
            return IfaCommonUtil.createDataList(
                    dtoResList, ErrorLevel.SUCCESS, INFO_INSERTCOMPLETED, message);
        }
        
        return IfaCommonUtil.createDataList(
                dtoResList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
    }
    
    /**
     * アクションID：A008
     * アクション名：本店／支店名更新
     * Dto リクエスト：IfaRepAddA008DtoRequest
     * Dto レスポンス：IfaRepAddA008DtoResponse
     *
     * @param dtoReq Dto リクエスト
     * @return Dto レスポンス
     * @exception Exception 例外が発生した場合
     */
    @Override
    public DataList<IfaRepAddA008DtoResponse> branchNameUpdateA008(IfaRepAddA008DtoRequest dtoReq) throws Exception {
        
        List<IfaRepAddA008DtoResponse> dtoResList = new ArrayList<>();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaRepAddServiceImplL.branchNameUpdateA008");
        }
        // ①利用者権限のチェック
        if (isNotHeadOffice()) {
            // 業務エラーメッセージの取得
            String message = IfaCommonUtil.getMessage(ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE);
            // エラーレベルとメッセージの設定
            return IfaCommonUtil.createDataList(
                    dtoResList, ErrorLevel.FATAL, ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE, message);
        }
        // 仲介業者名dropDownListのデータを取得
        IfaRepAddSql001ModelRequest sql001Req = new IfaRepAddSql001ModelRequest();
        sql001Req.setSbiSecurityCode(dtoReq.getSbiSecurityCode());
        DataList<IfaRepAddSql001ModelResponse> sql001Res = dao.selectIfaRepAddSql001(sql001Req);
        // SQL結果をレスポンスに設定
        IfaRepAddA008DtoResponse ifaRepAddA008DtoResponse = new IfaRepAddA008DtoResponse();
        ifaRepAddA008DtoResponse.setBrokerNameList(editBrokerNamelist(sql001Res));
        // リクエスト.権限コード
        String privId = dtoReq.getPrivId();
        // 権限コードチェック
        if (PRIVID_CHECK_LIST_002.contains(privId)) {
            // プレースホルダの設定
            IfaRepAddSql002ModelRequest sql002Req = new IfaRepAddSql002ModelRequest();
            sql002Req.setBrokerCode(DO_NOT_EXECUTE_SQL);
            // 仲介業者支店名dropDownListを更新
            DataList<IfaRepAddSql002ModelResponse> sql002Res = excuteSql002(sql002Req);
            // SQL結果をレスポンスに設定
            ifaRepAddA008DtoResponse.setBrokerBranchNameList(editBrokerBranchNameList(sql002Res));
            
            // プレースホルダの設定
            IfaRepAddSql003ModelRequest sql003Req = new IfaRepAddSql003ModelRequest();
            sql003Req.setBrokerCode(DO_NOT_EXECUTE_SQL);
            sql003Req.setSubBrokerId(DO_NOT_EXECUTE_SQL);
            // 担当者名dropDownListを更新
            DataList<IfaRepAddSql003ModelResponse> selSql003Res = excuteSql003(sql003Req);
            // SQL結果をレスポンスに設定
            ifaRepAddA008DtoResponse.setEmployeeNameList(editEmployeeNameList(selSql003Res));

        }
        // 権限コードチェック
        if (PRIVID_CHECK_LIST_003.contains(privId)) {
            // プレースホルダの設定
            IfaRepAddSql003ModelRequest sql003Req = new IfaRepAddSql003ModelRequest();
            sql003Req.setBrokerCode(DO_NOT_EXECUTE_SQL);
            sql003Req.setSubBrokerId(DO_NOT_EXECUTE_SQL);
            // 担当者名dropDownListを更新
            DataList<IfaRepAddSql003ModelResponse> selSql003Res = excuteSql003(sql003Req);
            // SQL結果をレスポンスに設定
            ifaRepAddA008DtoResponse.setEmployeeNameList(editEmployeeNameList(selSql003Res));
        }
        // レスポンスの設定
        dtoResList.add(ifaRepAddA008DtoResponse);
        
        return IfaCommonUtil.createDataList(
                dtoResList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
    }
    
    /**
     * 利用者権限のチェックを行う
     *
     * @return 利用者権限のチェック結果
     */
    private boolean isNotHeadOffice() {
        // ①利用者権限のチェック
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        // ユーザ共通情報.権限コード
        String privId = userAccount.getPrivId();
        return !PrivId.HEAD_OFFICE.getId().equals(privId);
    }

    /**
     * SQL002.仲介業者支店名dropDownList取得
     *
     * @param dtoReq A002Dtoリクエスト
     * @return SQL002レスポンス
     * @throws Exception SQL002実行例外
     */
    private DataList<IfaRepAddSql002ModelResponse> excuteSql002(IfaRepAddSql002ModelRequest sql002Req)
            throws Exception {
        // SQL実行
        return dao.selectIfaRepAddSql002(sql002Req);
    }

    /**
     * SQL003.担当者名dropDownList取得
     *
     * @param dtoReq A003Dtoリクエスト
     * @return SQL003レスポンス
     * @throws Exception SQL003実行例外
     */
    private DataList<IfaRepAddSql003ModelResponse> excuteSql003(IfaRepAddSql003ModelRequest sql003Req)
            throws Exception {
        // SQL実行
        return dao.selectIfaRepAddSql003(sql003Req);
    }

    /**
     * SQL004.存在チェック
     *
     * @param dtoReq A005Dtoリクエスト
     * @return SQL004レスポンス
     * @throws Exception SQL004実行例外
     */
    private DataList<IfaRepAddSql004ModelResponse> excuteSql004(IfaRepAddSql004ModelRequest sql004Req)
            throws Exception {
        // SQL実行
        return dao.selectIfaRepAddSql004(sql004Req);
    }
    
    /**
     * SQL005.Horusユーザー権限情報追加
     *
     * @param dtoReq A006Dtoリクエスト
     * @return SQL005レスポンス
     * @throws Exception SQL005実行例外
     */
    private int excuteSql005(IfaRepAddSql005ModelRequest sql005Req) throws Exception {
        // ユーザ共通情報.ユーザーID
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        String userId = userAccount.getUserId();
        sql005Req.setCreateUser(userId);
        // SQL実行
        return dao.insertIfaRepAddSql005(sql005Req);
    }

    /**
     * 仲介業者名リストの設定
     *
     * @param sql001Res SQL001結果
     * @return 仲介業者名リスト
     */
    private List<IfaRepAddA001DtoRequestBrokerName> editBrokerNamelist(
            DataList<IfaRepAddSql001ModelResponse> sql001Res) {
        
        List<IfaRepAddA001DtoRequestBrokerName> brokerNamelist = new ArrayList<>();
        sql001Res.getDataList().forEach(res -> {
            IfaRepAddA001DtoRequestBrokerName dtoReqBrokerName = new IfaRepAddA001DtoRequestBrokerName();
            dtoReqBrokerName.setBrokerName(res.getBrokerBranchName());
            dtoReqBrokerName.setBrokerCode(res.getBrokerCode());
            brokerNamelist.add(dtoReqBrokerName);
        });
        return brokerNamelist;
    }
    
    /**
     * 仲介業者支店名リストの設定
     *
     * @param sql002Res SQL002結果
     * @return 仲介業者支店名リスト
     */
    private List<IfaRepAddA001DtoRequestBrokerBranchName> editBrokerBranchNameList(
            DataList<IfaRepAddSql002ModelResponse> sql002Res) {
        
        List<IfaRepAddA001DtoRequestBrokerBranchName> brokerBranchNameList = new ArrayList<>();
        sql002Res.getDataList().forEach(d -> {
            IfaRepAddA001DtoRequestBrokerBranchName dtoReqBrokerBranchName =
                    new IfaRepAddA001DtoRequestBrokerBranchName();
            dtoReqBrokerBranchName.setBrokerBranchName(d.getBrokerBranchName());
            dtoReqBrokerBranchName.setSubBrokerId(d.getSubBrokerId());
            brokerBranchNameList.add(dtoReqBrokerBranchName);
        });
        return brokerBranchNameList;
    }
    
    /**
     * 担当者名リストの設定
     *
     * @param sql003Res SQL003結果
     * @return 担当者名リスト
     */
    private List<IfaRepAddA001DtoRequestEmployeeName> editEmployeeNameList(
            DataList<IfaRepAddSql003ModelResponse> sql003Res) {
        List<IfaRepAddA001DtoRequestEmployeeName> employeeNameList = new ArrayList<>();
        sql003Res.getDataList().forEach(d -> {
            IfaRepAddA001DtoRequestEmployeeName dtoReqEmployeeName = new IfaRepAddA001DtoRequestEmployeeName();
            dtoReqEmployeeName.setEmployeeName(d.getEmployeeName());
            dtoReqEmployeeName.setEmployeeId(d.getBrokerChargeCode());
            employeeNameList.add(dtoReqEmployeeName);
        });
        
        return employeeNameList;
    }
    
    /**
     * 本店／支店名リストの設定
     *
     * @param sql006Res SQL006結果
     * @return 本店／支店名リスト
     */
    private List<IfaRepAddA001DtoRequestBranchName> editBranchNameList(
            DataList<IfaRepAddSql006ModelResponse> sql006Res) {
        
        List<IfaRepAddA001DtoRequestBranchName> branchNameList = new ArrayList<>();
        sql006Res.getDataList().forEach(d -> {
            IfaRepAddA001DtoRequestBranchName dtoReqBranchName = new IfaRepAddA001DtoRequestBranchName();
            dtoReqBranchName.setSbiSecurityCode(d.getBranchCode());
            dtoReqBranchName.setMainBranchName(d.getBranchName());
            branchNameList.add(dtoReqBranchName);
        });
        return branchNameList;
    }
    
    /**
     * A001DtoResponseの設定
     *
     * @param dtoReq A001Dtoリクエスト
     * @param branchNameList 本店／支店名リスト
     * @return A001DtoResponse
     */
    private IfaRepAddA001DtoResponse editIfaRepAddA001DtoResponse(IfaRepAddA001DtoRequest dtoReq,
            List<IfaRepAddA001DtoRequestBranchName> branchNameList) {
        
        IfaRepAddA001DtoResponse dtoRes = new IfaRepAddA001DtoResponse();
        dtoRes.setLoginId(dtoReq.getLoginId());
        dtoRes.setPrivId(dtoReq.getPrivId());
        dtoRes.setBranchNameList(branchNameList);
        List<IfaRepAddA001DtoRequestBrokerName> brokerNameList = new ArrayList<>();
        List<IfaRepAddA001DtoRequestBrokerBranchName> brokerBranchNameList = new ArrayList<>();
        List<IfaRepAddA001DtoRequestEmployeeName> employeeNameList = new ArrayList<>();
        dtoRes.setBrokerNameList(brokerNameList);
        dtoRes.setBrokerBranchNameList(brokerBranchNameList);
        dtoRes.setEmployeeNameList(employeeNameList);
        return dtoRes;
    }
}
