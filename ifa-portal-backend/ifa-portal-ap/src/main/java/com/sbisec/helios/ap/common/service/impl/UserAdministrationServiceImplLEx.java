package com.sbisec.helios.ap.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sbisec.helios.ap.common.dao.AccControlDao;
import com.sbisec.helios.ap.common.dao.BranchDao;
import com.sbisec.helios.ap.common.dao.MedGovMenuDao;
import com.sbisec.helios.ap.common.dao.MedUsersDao;
import com.sbisec.helios.ap.common.dao.MediateBranchDao;
import com.sbisec.helios.ap.common.dao.MediateChargeInfoDao;
import com.sbisec.helios.ap.common.enums.PrivId;
import com.sbisec.helios.ap.common.model.AuthCtrlListExclusionSettings;
import com.sbisec.helios.ap.common.model.AuthCtrlListExclusionSettings.ExclusionItem;
import com.sbisec.helios.ap.common.model.Branch;
import com.sbisec.helios.ap.common.model.MCode;
import com.sbisec.helios.ap.common.model.MedGovMenu;
import com.sbisec.helios.ap.common.model.MedUsers;
import com.sbisec.helios.ap.common.model.MediateBranch;
import com.sbisec.helios.ap.common.model.MediateChargeInfo;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.model.UserPermissionInfo;
import com.sbisec.helios.ap.common.service.MCodeService;
import com.sbisec.helios.ap.common.service.UserAdministrationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * ユーザ管理
 *
 * @author SCSK
 *
 */
@Component(value = "userAdministrationService")
public class UserAdministrationServiceImplLEx implements UserAdministrationService {
    
    @Autowired
    protected MedUsersDao medUsersDao;
    
    @Autowired
    protected AccControlDao accControlDao;
    
    @Autowired
    protected BranchDao branchDao;
    
    @Autowired
    protected MediateBranchDao mediateBranchDao;
    
    @Autowired
    protected MediateChargeInfoDao mediateChargeInfoDao;
    
    @Autowired
    protected MedGovMenuDao medGovMenuDao;
    
    @Autowired
    private MCodeService mcodeService;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAdministrationServiceImplLEx.class);
    
    private static final String DEFAULT_SUB_BROKER_CODE_KEY = "000";
    
    private static final String DEFAULT_EMPLOYEEID_CODE_KEY = "0000";
    
    private static final String DEFAULT_EMPLOYEEID_NAME = "仲介業者担当";
    
    /** コードマスタテーブル.種別 （'77':コース利用仲介業者コード） */
    private static final String CODE_TYPE_COURSE = "77";

    /** コードマスタテーブル.コード１ （'01':デフォルト） */
    private static final String CODE_1_COURSE = "01";
    
    //@Override
    /**
     * ユーザ共通情報取得
     *
     * @param userId ユーザID
     * @param settings メニュー除外設定。除外不要な場合はnullを指定
     * @return ユーザ共通情報
     * @throws Exception 例外
     * @see com.sbisec.helios.ap.common.service.UserAdministrationService#getUserAccount(java.lang.String, com.sbisec.helios.ap.common.model.AuthCtrlListExclusionSettings)
     */
    public UserAccount getUserAccount(String userId, AuthCtrlListExclusionSettings settings) throws Exception {
        
        LOGGER.debug("UserAdministrationServiceImplLEx.login:[{}]", userId);
        System.out.println("Execute getUserAccount not cahcaed.");
        
        if (userId == null) {
            throw new IllegalArgumentException("UserId is required.");
        }
        
        MedUsers medUsers = medUsersDao.getMedUsers(userId);
        
        // If it's not exists, return null.
        if (medUsers == null) {
            return null;
        }
        
        // Create new instance of the UserAccount.
        UserAccount userAccount = new UserAccount();
        // Set MedUser into the UserAccount.
        userAccount.setMedUsers(medUsers);
        // Set UserPermissionInfo into the UserAccount.
        userAccount.setUserPermissionInfo(getUserPermissionInfo(userId));
        // Set AccControl into the UserAccount.
        userAccount.setAccControls(accControlDao.getAccControl(userId));
        // Merge accessible view
        userAccount.mergeAccessibleView();
        
        if (settings != null) {
            
            for (ExclusionItem exclusionItem : settings.getExclusionList()) {
                // SQL001.権限コードが条件１に含まれるか判定する
                Boolean privIdExist = exclusionItem.getCondition1().contains(Integer.parseInt(medUsers.getPrivId()));
                if (!privIdExist) {
                    // 真：処理継続
                    // 偽：次の除外リスト要素の処理へ
                    continue;
                }
                // SQL001.仲介業者コードが条件２に含まれるか判定する
                Boolean brokerCodeExist = exclusionItem.getCondition2().contains(medUsers.getBrokerId());
                if (brokerCodeExist) {
                    // 真：次の除外リスト要素の処理へ
                    continue;
                } else {
                    // 偽：除外メニューを削除する。
                    List<String> accessibleViewList = new ArrayList<>(userAccount.getUserPermissionInfo().getAccessibleViewList());
                    for (int i = 0; i < accessibleViewList.size(); i++) {
                        String menuId = accessibleViewList.get(i);
                        if (exclusionItem.getExclusionMenu().contains(menuId)) {
                            // メニュー削除
                            userAccount.getUserPermissionInfo().removeAccessibleView(menuId);
                        }
                    }
                }
            }
            
        }
        
        // set branch 本支店
        Branch branch = branchDao.getBranch(medUsers.getBranchId());
        if (branch != null) {
            userAccount.setBranch(branch);
        }
        
        // set broker 仲介業者本店
        if (medUsers.getBrokerId() != null) {
            MediateBranch broker = mediateBranchDao.getMediateBranch(medUsers.getBrokerId(),
                    DEFAULT_SUB_BROKER_CODE_KEY);
            if (broker != null) {
                userAccount.setBroker(broker);
            }
            // set subBroker 仲介業者支店
            if (medUsers.getSubBrokerId() != null) {
                MediateBranch subBroker = mediateBranchDao.getMediateBranch(medUsers.getBrokerId(),
                        medUsers.getSubBrokerId());
                if (subBroker != null) {
                    userAccount.setSubBroker(subBroker);
                }
                if (medUsers.getEmployeeId() != null && !medUsers.getEmployeeId().equals(DEFAULT_EMPLOYEEID_CODE_KEY)) {
                    // set mediateChargeInfo 仲介業者営業員情報
                    MediateChargeInfo mediateChargeInfo = mediateChargeInfoDao.getMediateChargeInfo(
                            medUsers.getBrokerId(), medUsers.getSubBrokerId(), medUsers.getEmployeeId());
                    if (mediateChargeInfo != null) {
                        userAccount.setMediateChargeInfo(mediateChargeInfo);
                    }
                } else {
                    MediateChargeInfo mediateChargeInfo = new MediateChargeInfo();
                    mediateChargeInfo.setBrokerCode(medUsers.getBrokerId());
                    mediateChargeInfo.setBrokerBranchCode(medUsers.getSubBrokerId());
                    mediateChargeInfo.setBrokerChargeCode(DEFAULT_EMPLOYEEID_CODE_KEY);
                    mediateChargeInfo.setBrokerChargeName(DEFAULT_EMPLOYEEID_NAME);
                    userAccount.setMediateChargeInfo(mediateChargeInfo);
                }
            }
        }
        
        // set courseKDispFlg コース表示フラグ
        Boolean courseKDispFlg = Boolean.FALSE;
        // WMコース参照可能仲介業者取得
        List<MCode> mCodeList = mcodeService.getMCodeList(CODE_TYPE_COURSE, CODE_1_COURSE);
        
        // WMコース参照可仲介業者コードリスト
        List<String> courseKBrokerCodeList = new ArrayList<>();
        if (mCodeList != null && !mCodeList.isEmpty()) {
            for (MCode mCode : mCodeList) {
                courseKBrokerCodeList.add(mCode.getCode2());
            }
        }
        
        // ユーザ.仲介業者コードがWMコース参照可仲介業者コードリストに含まれるか判定する
        Boolean brokerCodeExist = courseKBrokerCodeList.contains(medUsers.getBrokerId());
        
        if (PrivId.HEAD_OFFICE.getId().equals(medUsers.getPrivId())
                || PrivId.BRANCH.getId().equals(medUsers.getPrivId()) || brokerCodeExist) {
            // IFAポータル上で特定のアカウントにのみWMコースを表示する(SBI証券社員、仲介業者コード：7000（WM部）)
            courseKDispFlg = Boolean.TRUE;
        }
        userAccount.setCourseKDispFlg(courseKDispFlg);
        
        return userAccount;
    }
    
    /**
     * ユーザ認可情報取得
     *
     * @param userId ユーザID
     * @return 認可情報
     * @throws Exception 例外
     */
    public UserPermissionInfo getUserPermissionInfo(String userId) throws Exception {
        
        LOGGER.debug("UserAdministrationServiceImplLEx.getUserPermissionInfo:[{}]", userId);
        
        if (userId == null) {
            throw new IllegalArgumentException("UserId is required.");
        }
        
        // Get MedGovMenu list of the user.
        List<MedGovMenu> medGovMenuList = medGovMenuDao.getMedGovMenuList(userId);
        // Create new instance of the UserPermissionInfo.
        UserPermissionInfo userPermissionInfo = new UserPermissionInfo();
        // Add all MedGovMenu data, into the UserPermissionInfo.
        userPermissionInfo.addViewPermissions(medGovMenuList);
        
        return userPermissionInfo;
    }
    
    @Transactional(rollbackFor = Throwable.class)
    @Override
    public Integer updateMedUsersPassword(String userId, String password) throws Exception {
        
        LOGGER.debug("UserAdministrationServiceImplLEx.updatePassword:[{}, {}]", userId, password);
        
        if (userId == null || password == null) {
            throw new IllegalArgumentException("userId, password and updatedBy is required.");
        }
        
        return medUsersDao.updateMedUsersPassword(userId, password);
    }
}
