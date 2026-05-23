package com.sbisec.helios.ap.extapi.servicenow.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Arrays;

import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.PrivId;
import com.sbisec.helios.ap.common.util.EncryptPassword;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.extapi.servicenow.common.util.ServiceNowUtil;
import com.sbisec.helios.ap.extapi.servicenow.dao.IfaServiceNowUserAccountManagerDao;
import com.sbisec.helios.ap.extapi.servicenow.dao.model.IfaA005VerifyUserModel;
import com.sbisec.helios.ap.extapi.servicenow.dao.model.IfaA006MedUsersModel;
import com.sbisec.helios.ap.extapi.servicenow.dao.model.IfaTbMedUserModel;
import com.sbisec.helios.ap.extapi.servicenow.dao.model.IfaTbMedUsersPriv;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA011RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA012RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA013RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowMenuAndAclManagerA013ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowUserAccountManagerA005RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowUserAccountManagerA005ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowUserAccountManagerA006RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowUserAccountManagerA006ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowUserAccountManagerA007RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowUserAccountManagerA007ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowUserAccountManagerA008RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowUserAccountManagerA008ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowUserAccountManagerA009RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowUserAccountManagerA009ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowUserAccountManagerA014RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowUserAccountManagerA014ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.common.IfaServiceNowBrokerDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.common.IfaServiceNowDataList;
import com.sbisec.helios.ap.extapi.servicenow.dto.common.IfaServiceNowMenuDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.common.IfaServiceNowPrivDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.common.IfaServiceNowUserDto;
import com.sbisec.helios.ap.extapi.servicenow.service.IfaServiceNowMenuAndAclManagerService;
import com.sbisec.helios.ap.extapi.servicenow.service.IfaServiceNowUserAccountManagerService;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * cmpIfaServiceNowUserAccountManagerService サービス
 *
 * @autor SCSK
 */
@Component(value = "cmpIfaServiceNowUserAccountManagerService")
public class IfaServiceNowUserAccountManagerServiceImpl implements IfaServiceNowUserAccountManagerService {
    
    /** {0}を正しく入力して下さい。 */
    private static final String ERRORS_ACCURATELY = "errors.accurately";
    
    /** 希望ユーザーＩＤはすでに使われています。 */
    private static final String ERRORS_ID_EXIST = "errors.loginIdExist";
    
    /** {0}のデータが存在しません。 */
    private static final String ERRORS_NOT_EXIST = "info.orderedDataNotExist";
    
    /** {0}が重複しています。 */
    private static final String ERRORS_DUP_EXIST = "errors.emp.loginUsers.exist#2";
    
    /** {0}は入力不要です。 */
    private static final String ERRORS_UNREQUIRED = "errors.unrequired";
    
    /** {0}は入力不要です。 */
    private static final String ERRORS_SIZE = "Size";
    
    /** {0}を正しく入力して下さい。 */
    private static final String ERRORS_PATTERN = "Pattern";
    
    /** ログイン状況: 0 (ログアウト) */
    private static final String LOGOUT_STATUS = "0";
    
    /** 削除フラグ: 0 (有効) */
    private static final String DELETE_FLAG_VALID = "0";
    
    /** 認証対象: 1 (無効) */
    private static final String VERIFY_STATUS = "1";
    
    /** 管理者フラグ: 1 (管理者) */
    private static final String GOVERNOR_FLAG_ADMIN = "1";
    
    /** 管理者フラグ: 0 (非管理者) */
    private static final String GOVERNOR_FLAG_NON_ADMIN = "0";
    
    /** 親ユーザー */
    private static final String PARENT_USER = "1";

    @Autowired
    private IfaServiceNowUserAccountManagerDao dao;
    
    @Autowired
    private IfaServiceNowMenuAndAclManagerService menuServcie;
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public IfaServiceNowUserAccountManagerA005ResponseDto invokeA005(
            IfaServiceNowUserAccountManagerA005RequestDto dtoReq) throws Exception {
        
        IfaServiceNowUserAccountManagerA005ResponseDto dtoRes = new IfaServiceNowUserAccountManagerA005ResponseDto();
        
        // 仲介業者リストがNULLの場合は 空とみなす
        List<IfaServiceNowBrokerDto> brokerList = ListUtils.emptyIfNull(dtoReq.getBrokerList());
        
        // 1. 入力パラメータチェック (相関チェックのみ)
        // 1.1 入力パラメータチェック (相関チェックのみ)
        // * 単体チェックはコントローラにて実施
        if (!validAll(dtoRes, dtoReq.getPrivId(), dtoReq.getGovernorFlag(), brokerList, dtoReq.getCcsUserId())) {
            return dtoRes;
        }
        
        // 2. ユーザー情報存在チェック
        // 2.1 Horusユーザー情報(TB_MED_USERS)テーブルからユーザー情報を検索する
        String userId = dtoReq.getUserId();
        List<IfaTbMedUserModel> users = dao.selectA005UserByUserId(userId);
        
        // 2.2 2.1の検索結果を元にユーザー情報存在チェックを実施
        if (users.size() != 0) {
            ServiceNowUtil.createDataListServiceNow(dtoRes, 0, ErrorLevel.FATAL, ERRORS_ID_EXIST,
                    IfaCommonUtil.getMessage(ERRORS_ID_EXIST, new String[] {}));
            return dtoRes;
        }
        
        // 3　Horusユーザー情報(TB_MED_USERS)テーブルを登録する
        // ※引数.仲介業者リストは、親ユーザーフラグ="1"の要素
        // ※引数仲介業者リストが空(またはNULL)の場合も登録 （本支店コード～担当者名 はNULL として登録）
        // -> 親ユーザの登録
        
        // 親ユーザーフラグ="1"の仲介業者リスト
        final IfaServiceNowBrokerDto parentBroker = brokerList.stream()
        .filter(f -> PARENT_USER.equals(f.getParentUserFlag())).findFirst().orElse(
                // 親ユーザが不在の場合(引数.権限コードが11,12の場合にあり得る)
                new IfaServiceNowBrokerDto());
        
        final String all0 = StringUtils.repeat("0", 16);
        IfaTbMedUserModel userModel = new IfaTbMedUserModel();
        userModel.setUserId(userId);
        userModel.setUserNm(dtoReq.getUserNm());
        userModel.setPassword(EncryptPassword.encrypt(dtoReq.getPassword()));
        // userModel.setLastPasswordUpdateDate(); -> SYSDATE 使用
        userModel.setPrivId(dtoReq.getPrivId());
        userModel.setBranchId(parentBroker.getBranchCode());
        userModel.setBrokerId(parentBroker.getBrokerCode());
        userModel.setSubBrokerId(parentBroker.getBrokerBranchCode());
        userModel.setEmployeeId(parentBroker.getEmployeeId());
        userModel.setEmployeeName(parentBroker.getEmployeeName());
        userModel.setGovernorFlag(dtoReq.getGovernorFlag());
        userModel.setLoginStatus(LOGOUT_STATUS);
        userModel.setPartnerUserId(null);
        userModel.setPartnerUserPw(null);
        userModel.setCcsUserId(dtoReq.getCcsUserId());
        userModel.setCcsUserPw(null);
        userModel.setCreateUser(all0);
        // userModel.setCreateTime(); -> SYSDATE 使用
        userModel.setUptimestampUser(all0);
        // userModel.setUptimestampTime(); -> SYSDATE 使用
        userModel.setMailAddress(dtoReq.getMailAddress());
        
        int total = dao.insertA005MedUser(userModel);
        
        // 4. Horusユーザー権限情報(TB_MED_USERS_PRIV)テーブルを登録する
        // ※引数.仲介業者リストの件数分、当該処理を繰り返し実施
        // -> 子ユーザの登録
        for (IfaServiceNowBrokerDto broker : brokerList) {
            if (!PARENT_USER.equals(broker.getParentUserFlag())) {
                IfaTbMedUsersPriv privModel = new IfaTbMedUsersPriv();
                privModel.setUserId(userId);
                // model.setSeqNo(); -> シーケンス使用
                privModel.setBranchId(broker.getBranchCode());
                privModel.setBrokerId(broker.getBrokerCode());
                privModel.setSubBrokerId(broker.getBrokerBranchCode());
                privModel.setEmployeeId(broker.getEmployeeId());
                privModel.setEmployeeName(broker.getEmployeeName());
                privModel.setDeleteFlg(DELETE_FLAG_VALID);
                privModel.setCreateUser(all0);
                // m.setCreateTime(); -> SYSDATE 使用
                privModel.setDeleteUser(all0);
                privModel.setDeleteTime(null);
                total += dao.insertA005UserPriv(privModel);
            }
        }
        
        // 5. 認証ユーザー情報テーブルに最新データ挿入
        // 5.1　認証ユーザー情報(TB_MED_VERIFY_USERS)テーブルを登録する
        IfaA005VerifyUserModel model = new IfaA005VerifyUserModel();
        model.setUserId(userId);
        model.setMailAddress(dtoReq.getMailAddress());
        model.setVerifyStatus(VERIFY_STATUS);
        model.setUpdateBy(all0);
        model.setPrivId(dtoReq.getPrivId());
        dao.insertA005VerifyUser(model);
        
        // 6. Cordysユーザーとメニューマッピング情報_新テーブルにデータ挿入
        String privId = dtoReq.getPrivId();
        // ※引数.メニューリストの件数分、当該処理を繰り返し実施
        // 6.1 API(ユーザ利用できるメニューを登録)を呼び出す
        IfaServiceNowMenuAndAclManagerA012RequestDto dtoA012 = new IfaServiceNowMenuAndAclManagerA012RequestDto();
        dtoA012.setUserId(userId);
        dtoA012.setPrivId(privId);
        dtoA012.setBrokerCode(parentBroker.getBrokerCode());
        dtoA012.setCreateUser(all0);
        dtoA012.setUptimestampUser(all0);
        // メニューリストが存在する場合にはメニューIDをセット
        if (CollectionUtils.isNotEmpty(dtoReq.getMenuList())) {
            for (IfaServiceNowMenuDto menu : dtoReq.getMenuList()) {
                dtoA012.setMenuId(menu.getMenuId());
                menuServcie.invokeA012(dtoA012);
            }
        } else {
            dtoA012.setMenuId(null);
            menuServcie.invokeA012(dtoA012);
        }
        
        // ・応答.結果コード＝3.2の登録件数＋3.3の登録件数
        ServiceNowUtil.createDataListServiceNow(dtoRes, total);
        return dtoRes;
    }

    private boolean validAll(IfaServiceNowDataList dtoRes, String privId, String governorFlag,
            List<IfaServiceNowBrokerDto> brokerList, String ccsUserId) {
        
        // 管理者フラグ - 権限チェック
        if (!validGovernorFlag(privId, governorFlag)) {
            ServiceNowUtil.createDataListServiceNow(dtoRes, 0, ErrorLevel.FATAL, ERRORS_ACCURATELY,
                    IfaCommonUtil.getMessage(ERRORS_ACCURATELY, new String[] { "管理者フラグ" }));
            return false;
        }
        
        // 仲介業者リスト.親ユーザーフラグ - 親ユーザチェック
        if (!validParentUserFlag(brokerList, privId)) {
            ServiceNowUtil.createDataListServiceNow(dtoRes, 0, ErrorLevel.FATAL, ERRORS_ACCURATELY,
                    IfaCommonUtil.getMessage(ERRORS_ACCURATELY, new String[] { "仲介業者リスト" }));
            return false;
        }
        
        // 仲介業者リスト - 入力チェック
        if (!validBrokerList(brokerList, privId)) {
            ServiceNowUtil.createDataListServiceNow(dtoRes, 0, ErrorLevel.FATAL, ERRORS_ACCURATELY,
                    IfaCommonUtil.getMessage(ERRORS_ACCURATELY, new String[] { "仲介業者リスト" }));
            return false;
        }
        
        // 仲介業者リスト - 重複チェック
        if (!uniqueBrokerList(brokerList)) {
            ServiceNowUtil.createDataListServiceNow(dtoRes, 0, ErrorLevel.FATAL, ERRORS_DUP_EXIST,
                    IfaCommonUtil.getMessage(ERRORS_DUP_EXIST, new String[] { "仲介業者リスト" }));
            return false;
        }
        
        if (!(PrivId.ES_BUSSINESS.getId().equals(privId) || PrivId.ES_MANAGEMENT.getId().equals(privId))) {
            // CCSユーザーID - 未入力チェック
            if (!isEmptyCssUserId(ccsUserId)) {
                // CCSユーザーID - 桁数チェック
                if (!validSizeCssUserId(ccsUserId)) {
                    ServiceNowUtil.createDataListServiceNow(dtoRes, 0, ErrorLevel.FATAL, ERRORS_SIZE,
                            IfaCommonUtil.getMessage(ERRORS_SIZE, new String[] { "CCSユーザーID", "16", "1" }));
                    return false;
                }
                
                // CCSユーザーID - フォーマットチェック
                if (!validPatternCssUserId(ccsUserId)) {
                    ServiceNowUtil.createDataListServiceNow(dtoRes, 0, ErrorLevel.FATAL, ERRORS_PATTERN,
                            IfaCommonUtil.getMessage(ERRORS_PATTERN, new String[] { "CCSユーザーID", "16", "1" }));
                    return false;
                }
            }
        } else {
            // 引数.権限コードが11,12のいずれか、かつ設定済みの場合
            
            // CCSユーザーID - 未入力チェック
            if (!isEmptyCssUserId(ccsUserId)) {
                ServiceNowUtil.createDataListServiceNow(dtoRes, 0, ErrorLevel.FATAL, ERRORS_UNREQUIRED,
                        IfaCommonUtil.getMessage(ERRORS_UNREQUIRED, new String[] { "CCSユーザーID" }));
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * 管理者フラグ - 権限チェック
     *
     * @param privId 権限ID
     * @param governorFlag 管理者フラグ
     * @return True: 有効 False: 無効
     */
    private boolean validGovernorFlag(String privId, String governorFlag) {
        
        if (PrivId.HEAD_OFFICE.getId().equals(privId) || PrivId.BRANCH.getId().equals(privId)) {
            // '1'（管理者）
            if (GOVERNOR_FLAG_ADMIN.equals(governorFlag)) {
                return true;
            }
        } else {
            // '0'（非管理者）
            if (GOVERNOR_FLAG_NON_ADMIN.equals(governorFlag)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * 仲介業者リスト.親ユーザーフラグ - 親ユーザチェック
     *
     * @param brokerList 仲介業者リスト
     * @param privId 権限ID
     * @return True: 仲介業者リストに親ユーザフラグのデータが1件のみ存在する
     */
    private boolean validParentUserFlag(List<IfaServiceNowBrokerDto> brokerList, String privId) {
        
        if (!(PrivId.ES_BUSSINESS.getId().equals(privId) || PrivId.ES_MANAGEMENT.getId().equals(privId))) {
            // 引数.権限コードが11,12ではない場合、親ユーザーフラグ＝"1"の仲介業者リスト≠１件
            // （親ユーザーの仲介業者リストが存在しない、または親ユーザーの仲介業者リストが重複）
            if (brokerList.stream().filter(broker -> {
                return PARENT_USER.equals(broker.getParentUserFlag());
            }).count() != 1) {
                return false;
            }
        } else {
            // 引数.権限コードが11,12の場合、仲介業者リスト>１件
            // （仲介業者リストが存在しないことは許容するが、複数の仲介業者リストは許容しない）
            if (brokerList.size() > 1) {
                return false;
            }
        }
        
        // 引数.権限コードが1,2,11,12の場合、親ユーザーフラグ≠"1"の仲介業者リスト≠0件
        // （子ユーザーの仲介業者リストが存在する）
        if (PrivId.HEAD_OFFICE.getId().equals(privId) ///
                || PrivId.BRANCH.getId().equals(privId) ///
                || PrivId.ES_BUSSINESS.getId().equals(privId) ///
                || PrivId.ES_MANAGEMENT.getId().equals(privId)) {
            if (brokerList.stream().filter(broker -> {
                return !PARENT_USER.equals(broker.getParentUserFlag());
            }).count() != 0) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * 仲介業者リスト - 入力チェック
     *
     * @param brokerList 仲介業者リスト
     * @param privId 権限ID
     * @return True: 仲介業者リストのフォーマットが適正
     */
    private boolean validBrokerList(List<IfaServiceNowBrokerDto> brokerList, String privId) {
        
        return brokerList.stream().allMatch(broker -> {
            
            String branchCode = broker.getBranchCode();
            String brokerCode = broker.getBrokerCode();
            String brokerBranchCode = broker.getBrokerBranchCode();
            String employeeName = broker.getEmployeeName();
            
            // 権限チェックごとに判定
            if (PrivId.HEAD_OFFICE.getId().equals(privId) || PrivId.BRANCH.getId().equals(privId)) {
                if (!StringUtil.isNullOrEmpty(branchCode) ///
                        && StringUtil.isNullOrEmpty(brokerCode) ///
                        && StringUtil.isNullOrEmpty(brokerBranchCode) ///
                        && StringUtil.isNullOrEmpty(employeeName)) {
                    return true;
                }
            } else if (PrivId.B_INNER_MANAGEMENT.getId().equals(privId)
                    || PrivId.B_SALES_EXECUTIVE.getId().equals(privId)
                    || PrivId.B_SALES_REPRESENTATIVE.getId().equals(privId)) {
                if (!StringUtil.isNullOrEmpty(branchCode) ///
                        && !StringUtil.isNullOrEmpty(brokerCode) ///
                        && StringUtil.isNullOrEmpty(brokerBranchCode) ///
                        && !StringUtil.isNullOrEmpty(employeeName)) {
                    return true;
                }
            } else if (PrivId.BB_INNER_MANAGEMENT.getId().equals(privId)
                    || PrivId.BB_SALES_EXECUTIVE.getId().equals(privId)
                    || PrivId.BB_SALES_REPRESENTATIVE.getId().equals(privId)
                    || PrivId.RESPONSIBLE.getId().equals(privId)) {
                if (!StringUtil.isNullOrEmpty(branchCode) ///
                        && !StringUtil.isNullOrEmpty(brokerCode) ///
                        && !StringUtil.isNullOrEmpty(brokerBranchCode) ///
                        && !StringUtil.isNullOrEmpty(employeeName)) {
                    return true;
                }
            } else if (PrivId.ES_BUSSINESS.getId().equals(privId) || PrivId.ES_MANAGEMENT.getId().equals(privId)) {
                if (StringUtil.isNullOrEmpty(branchCode) ///
                        && StringUtil.isNullOrEmpty(brokerCode) ///
                        && StringUtil.isNullOrEmpty(brokerBranchCode) ///
                        && StringUtil.isNullOrEmpty(employeeName)) {
                    return true;
                }
            } else {
                return false;
            }
            
            return false;
        });
        
    }
    
    /**
     * 仲介業者リスト - 重複チェック
     *
     * @param brokerList 仲介業者リスト
     * @return True: 重複なし
     */
    private boolean uniqueBrokerList(List<IfaServiceNowBrokerDto> brokerList) {
        
        Set<String> items = new HashSet<>();
        for (IfaServiceNowBrokerDto broker : brokerList) {
            String key = String.format("%s:-:%s:-:%s:-:%s", broker.getBranchCode(), broker.getBrokerCode(),
                    broker.getBrokerBranchCode(), broker.getEmployeeName());
            if (!items.add(key)) {
                return false; // 重複が見つかった場合に false を返す
            }
        }
        
        return true; // 重複がない場合に true を返す
    }
    
    /**
     * CCSユーザーID - 未入力チェック
     *
     * @param ccsUserId CCSユーザーID
     * @return True: 未入力 False: 入力あり
     */
    private boolean isEmptyCssUserId(String ccsUserId) {
        
        return StringUtil.isNullOrEmpty(ccsUserId);
    }

    /**
     * CCSユーザーID - 桁数チェック
     *
     * @param ccsUserId CCSユーザーID
     * @return True: 有効桁数 False: 無効
     */
    private boolean validSizeCssUserId(String ccsUserId) {
        
        return ccsUserId.length() >= 1 && ccsUserId.length() <= 16;
    }
    
    /**
     * CCSユーザーID - フォーマットチェック
     *
     * @param ccsUserId CCSユーザーID
     * @return True: 英数字のみで構成 False: それ以外
     */
    private boolean validPatternCssUserId(String ccsUserId) {
        
        String pattern = "^[0-9a-zA-Z]+$";
        return ccsUserId.matches(pattern);
    }
    
    @Override
    public IfaServiceNowUserAccountManagerA006ResponseDto invokeA006(
            IfaServiceNowUserAccountManagerA006RequestDto dtoReq) throws Exception {
        
        // 1. 引数.ユーザID=nullの場合
        // -> 単項目エラーで処理される
        
        // 2. Horusユーザー情報を取得する
        String userId = dtoReq.getUserId();
        // 2.1 テーブル「Horusユーザー情報」からユーザー情報を取得する
        // 3. Horusユーザー権限情報を取得する
        // 3.1 テーブル「Horusユーザー権限情報」からユーザー情報を取得する
        // -> Union でまとめて取得
        List<IfaA006MedUsersModel> users = dao.selectA006User(userId);
        IfaA006MedUsersModel parentUser = users.stream().filter(f -> PARENT_USER.equals(f.getParentUserFlag()))
                .findFirst().orElse(null);
        
        IfaServiceNowUserAccountManagerA006ResponseDto dtoRes = new IfaServiceNowUserAccountManagerA006ResponseDto();
        // ・2.1の検索結果がNULLの場合
        // -> NULLを返して、処理終了
        if (parentUser != null) {
            
            // 4. ユーザー利用可能メニュー一覧を取得する
            // 4.1 API(ユーザ利用可能メニュー一覧取得)を呼び出す
            IfaServiceNowMenuAndAclManagerA013RequestDto dtoA013 = new IfaServiceNowMenuAndAclManagerA013RequestDto();
            dtoA013.setUserId(userId);
            IfaServiceNowMenuAndAclManagerA013ResponseDto menus = menuServcie.invokeA013(dtoA013);
            
            // 5. 応答項目を設定して返却する
            List<IfaServiceNowBrokerDto> brokerList = extractBrokerDtoList(users);
            // 5.1 Horusユーザー情報を応答項目に設定
            // 5.2 Horusユーザー権限情報を応答項目に設定
            dtoRes.setUserId(parentUser.getUserId());
            dtoRes.setUserNm(parentUser.getUserNm());
            dtoRes.setPassword(parentUser.getPassword());
            dtoRes.setPrivId(parentUser.getPrivId());
            dtoRes.setMenuList(menus.getMenuList());
            dtoRes.setBrokerList(brokerList);
            dtoRes.setGovernorFlag(parentUser.getGovernorFlag());
            dtoRes.setCcsUserId(parentUser.getCcsUserId());
            dtoRes.setMailAddress(parentUser.getMailAddress());
        }
        
        ServiceNowUtil.createDataListServiceNow(dtoRes, 0);
        return dtoRes;
    }
    
    private List<IfaServiceNowBrokerDto> extractBrokerDtoList(List<IfaA006MedUsersModel> users)
            throws IllegalAccessException, InvocationTargetException {
        
        List<IfaServiceNowBrokerDto> brokerList = new ArrayList<>();
        
        for (IfaA006MedUsersModel u : users) {
            IfaServiceNowBrokerDto broker = new IfaServiceNowBrokerDto();
            BeanUtils.copyProperties(broker, u);
            brokerList.add(broker);
        }
        
        return brokerList;
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public IfaServiceNowUserAccountManagerA007ResponseDto invokeA007(
            IfaServiceNowUserAccountManagerA007RequestDto dtoReq) throws Exception {
        
        IfaServiceNowUserAccountManagerA007ResponseDto dtoRes = new IfaServiceNowUserAccountManagerA007ResponseDto();
        
        // 仲介業者リストがNULLの場合は 空とみなす
        List<IfaServiceNowBrokerDto> brokerList = ListUtils.emptyIfNull(dtoReq.getBrokerList());

        // 1. 入力パラメータチェック (相関チェックのみ)
        // 1.1 入力パラメータチェック (相関チェックのみ)
        // * 単体チェックはコントローラにて実施
        // -> 登録処理と内容は同じ
        if (!validAll(dtoRes, dtoReq.getPrivId(), dtoReq.getGovernorFlag(), brokerList, dtoReq.getCcsUserId())) {
            return dtoRes;
        }
        
        // 2. ユーザー情報存在チェック
        // 2.1 Horusユーザー情報(TB_MED_USERS)テーブルからユーザー情報を検索する
        String userId = dtoReq.getUserId();
        List<IfaTbMedUserModel> users = dao.selectA005UserByUserId(userId);
        
        // 2.2 2.1の検索結果を元にユーザー情報存在チェックを実施
        if (users.size() != 1) {
            ServiceNowUtil.createDataListServiceNow(dtoRes, 0, ErrorLevel.FATAL, ERRORS_NOT_EXIST,
                    IfaCommonUtil.getMessage(ERRORS_NOT_EXIST, new String[] { "該当" }));
            return dtoRes;
        }
        
        // 3　Horusユーザー情報(TB_MED_USERS)テーブルを更新する
        // ※引数.仲介業者リストは、親ユーザーフラグ="1"の要素
        // ※引数仲介業者リストが空(またはNULL)の場合も登録 （本支店コード～担当者名 はNULL として登録）
        // -> 親ユーザの登録

        // 親ユーザーフラグ="1"の仲介業者リスト
        final IfaServiceNowBrokerDto parentBroker = brokerList.stream()
        .filter(f -> PARENT_USER.equals(f.getParentUserFlag())).findFirst().orElse(
                // 親ユーザが不在の場合(引数.権限コードが11,12の場合にあり得る)
                new IfaServiceNowBrokerDto());

        final String all0 = StringUtils.repeat("0", 16);
        IfaTbMedUserModel userModel = new IfaTbMedUserModel();
        userModel.setUserId(userId);
        userModel.setUserNm(dtoReq.getUserNm());
        if (!StringUtil.isNullOrEmpty(dtoReq.getPassword())) {
            userModel.setPassword(EncryptPassword.encrypt(dtoReq.getPassword()));
        }
        // userModel.setLastPasswordUpdateDate(); -> SYSDATE 使用
        userModel.setPrivId(dtoReq.getPrivId());
        userModel.setBranchId(parentBroker.getBranchCode());
        userModel.setBrokerId(parentBroker.getBrokerCode());
        userModel.setSubBrokerId(parentBroker.getBrokerBranchCode());
        userModel.setEmployeeId(parentBroker.getEmployeeId());
        userModel.setEmployeeName(parentBroker.getEmployeeName());
        userModel.setGovernorFlag(dtoReq.getGovernorFlag());
        userModel.setCcsUserId(dtoReq.getCcsUserId());
        userModel.setUptimestampUser(all0);
        // userModel.setUptimestampTime(); -> SYSDATE 使用
        userModel.setMailAddress(dtoReq.getMailAddress());
        
        int total = dao.updateA007MedUser(userModel);
        

        // 4. Horusユーザー権限情報(TB_MED_USERS_PRIV)テーブルを削除する
        dao.deleteA008UserPriv(userId);

        // 5. Horusユーザー権限情報登録
        // ※引数.仲介業者リストの件数分、当該処理を繰り返し実施
        // 5.1 Horusユーザー権限情報(TB_MED_USERS_PRIV)テーブルを登録する
        for (IfaServiceNowBrokerDto broker : brokerList) {
            if (!PARENT_USER.equals(broker.getParentUserFlag())) {
                // 3.2.2 Horusユーザー権限情報(TB_MED_USERS_PRIV)テーブルを登録する
                IfaTbMedUsersPriv privModel = new IfaTbMedUsersPriv();
                privModel.setUserId(userId);
                // model.setSeqNo(); -> シーケンス使用
                privModel.setBranchId(broker.getBranchCode());
                privModel.setBrokerId(broker.getBrokerCode());
                privModel.setSubBrokerId(broker.getBrokerBranchCode());
                privModel.setEmployeeId(broker.getEmployeeId());
                privModel.setEmployeeName(broker.getEmployeeName());
                privModel.setDeleteFlg(DELETE_FLAG_VALID);
                privModel.setCreateUser(all0);
                // model.setCreateTime(); -> SYSDATE 使用
                privModel.setDeleteUser(all0);
                privModel.setDeleteTime(null);
                total += dao.insertA005UserPriv(privModel);
            }
        }
        
        // 6. Cordysユーザーとメニューマッピング情報_新テーブルのデータ削除
        // 6.1 API(ユーザ利用できるメニューを削除)を呼び出す
        IfaServiceNowMenuAndAclManagerA011RequestDto dtoA011 = new IfaServiceNowMenuAndAclManagerA011RequestDto();
        dtoA011.setUserId(userId);
        menuServcie.invokeA011(dtoA011);
        
        // 7. Cordysユーザーとメニューマッピング情報_新テーブルにデータ挿入
        String privId = dtoReq.getPrivId();
        // ※引数.メニューリストの件数分、当該処理を繰り返し実施
        // 7.1 API(ユーザ利用できるメニューを登録)を呼び出す
        IfaServiceNowMenuAndAclManagerA012RequestDto dtoA012 = new IfaServiceNowMenuAndAclManagerA012RequestDto();
        dtoA012.setUserId(userId);
        dtoA012.setPrivId(privId);
        dtoA012.setBrokerCode(parentBroker.getBrokerCode());
        dtoA012.setCreateUser(all0);
        dtoA012.setUptimestampUser(all0);

        // メニューリストが存在する場合にはメニューIDをセット
        if (CollectionUtils.isNotEmpty(dtoReq.getMenuList())) {
            for (IfaServiceNowMenuDto menu : dtoReq.getMenuList()) {
                dtoA012.setMenuId(menu.getMenuId());
                menuServcie.invokeA012(dtoA012);
            }
        } else {
            dtoA012.setMenuId(null);
            menuServcie.invokeA012(dtoA012);
        }
        
        // 8. 応答.実行結果コードを設定して返却する
        // ・応答.結果コード＝3.2の更新件数＋3.3.2の登録件数
        ServiceNowUtil.createDataListServiceNow(dtoRes, total);
        return dtoRes;
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public IfaServiceNowUserAccountManagerA008ResponseDto invokeA008(
            IfaServiceNowUserAccountManagerA008RequestDto dtoReq) throws Exception {
        
        int total = 0;
        
        // 1. ユーザ利用できるメニューを削除
        // 1.1 テーブル「Cordysユーザーとメニューマッピング情報_新」の削除
        total += dao.deleteA008GovMenu(dtoReq.getUserId());
        
        // 1.2 テーブル「認証ユーザー情報」の削除
        total += dao.deleteA008VerifyUsers(dtoReq.getUserId());

        // 1.3 テーブル「Horusユーザー権限情報」の削除
        total += dao.deleteA008UserPriv(dtoReq.getUserId());
        
        // 1.4 テーブル「Horusユーザー情報」の削除
        total += dao.deleteA008MedUsers(dtoReq.getUserId());
        
        
        // 1.5 応答.実行結果コードを設定して返却する。    
        // ・応答.結果コード＝1.1の削除件数＋1.2の削除件数＋1.3の削除件数＋1.4の削除件数
        IfaServiceNowUserAccountManagerA008ResponseDto dtoRes = new IfaServiceNowUserAccountManagerA008ResponseDto();
        ServiceNowUtil.createDataListServiceNow(dtoRes, total);
        return dtoRes;
    }
    
    @Override
    public IfaServiceNowUserAccountManagerA009ResponseDto invokeA009(
            IfaServiceNowUserAccountManagerA009RequestDto dtoReq) throws Exception {
        
        List<IfaServiceNowPrivDto> privList = new ArrayList<IfaServiceNowPrivDto>();
        
        // 権限コード        権限コード名称                     入力パターン（※）
        // 1              本店                           1
        // 2              支店                           1
        // 3              仲介業者(内部管理責任者)           2
        // 4              仲介業者(営業責任者)              2
        // 5              仲介業者(外務員)                 2
        // 6              仲介業者支店(内部管理責任者)        3
        // 7              仲介業者支店(営業責任者)           3
        // 8              仲介業者支店(外務員)              3
        // 9              担当                           3
        // 11             業務部                          4
        // 12             管理部                          4
        privList.add(createDto("1", PrivId.HEAD_OFFICE.getId(), "本店"));
        privList.add(createDto("1", PrivId.BRANCH.getId(), "支店"));
        privList.add(createDto("2", PrivId.B_INNER_MANAGEMENT.getId(), "仲介業者(内部管理責任者)"));
        privList.add(createDto("2", PrivId.B_SALES_EXECUTIVE.getId(), "仲介業者(営業責任者)"));
        privList.add(createDto("2", PrivId.B_SALES_REPRESENTATIVE.getId(), "仲介業者(外務員)"));
        privList.add(createDto("3", PrivId.BB_INNER_MANAGEMENT.getId(), "仲介業者支店(内部管理責任者)"));
        privList.add(createDto("3", PrivId.BB_SALES_EXECUTIVE.getId(), "仲介業者支店(営業責任者)"));
        privList.add(createDto("3", PrivId.BB_SALES_REPRESENTATIVE.getId(), "仲介業者支店(外務員)"));
        privList.add(createDto("3", PrivId.RESPONSIBLE.getId(), "担当"));
        privList.add(createDto("4", PrivId.ES_BUSSINESS.getId(), "業務部"));
        privList.add(createDto("4", PrivId.ES_MANAGEMENT.getId(), "管理部"));
        
        IfaServiceNowUserAccountManagerA009ResponseDto dtoRes = new IfaServiceNowUserAccountManagerA009ResponseDto();
        dtoRes.setPrivList(privList);
        ServiceNowUtil.createDataListServiceNow(dtoRes, 0);
        return dtoRes;
    }
    
    /**
     * IfaServiceNowPrivDto を作成用のヘルパー関数
     *
     * @param inputPattern 入力パターン
     * @param privId 権限コード
     * @param privIdNm 権限コード名称
     * @return 作成したDto
     */
    private static IfaServiceNowPrivDto createDto(String inputPattern, String privId, String privIdNm) {
        
        IfaServiceNowPrivDto dto = new IfaServiceNowPrivDto();
        dto.setPrivId(privId);
        dto.setPrivIdNm(privIdNm);
        dto.setInputPattern(inputPattern);
        return dto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public IfaServiceNowUserAccountManagerA014ResponseDto invokeA014(
            IfaServiceNowUserAccountManagerA014RequestDto dtoReq) throws Exception {

        List<IfaServiceNowUserDto> res = dao.selectA014(dtoReq);
        
        IfaServiceNowUserAccountManagerA014ResponseDto dtoRes = new IfaServiceNowUserAccountManagerA014ResponseDto();
        dtoRes.setUserList(res);
        ServiceNowUtil.createDataListServiceNow(dtoRes, 0);
        return dtoRes;
    }
}
