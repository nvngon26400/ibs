package com.sbisec.helios.ap.ph1only.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sbisec.helios.ap.common.dao.MedUsersDao;
import com.sbisec.helios.ap.common.model.AuthCtrlListExclusionSettings;
import com.sbisec.helios.ap.common.model.MedGovMenu;
import com.sbisec.helios.ap.common.model.MedUsers;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.model.UserPermissionInfo;
import com.sbisec.helios.ap.common.model.AuthCtrlListExclusionSettings.ExclusionItem;
import com.sbisec.helios.ap.ph1only.dao.PreviousSystemAccControlDao;
import com.sbisec.helios.ap.ph1only.dao.PreviousSystemMedGovMenuDao;
import com.sbisec.helios.ap.ph1only.service.PreviousSystemUserAdministrationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 前システムのメニュー情報取得のためのクラス、下記のクラスをコピーして作成<br />
 * UserAdministrationServiceImp
 *
 * @author 河口
 *
 */
@Component(value = "previousSystemUserAdministrationService")
public class PreviousSystemUserAdministrationServiceImp implements PreviousSystemUserAdministrationService {
    
    @Autowired
    protected MedUsersDao medUsersDao;
    
    @Autowired
    protected PreviousSystemAccControlDao accControlDao;
    
    @Autowired
    protected PreviousSystemMedGovMenuDao medGovMenuDao;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PreviousSystemUserAdministrationServiceImp.class);
    
    //@Override
    /**
     * ユーザ共通情報取得
     *
     * @param userId ユーザID
     * @param settings メニュー除外設定。除外不要な場合はnullを指定
     * @return ユーザ共通情報
     * @throws Exception 例外
     * @see com.sbisec.helios.ap.ph1only.service.PreviousSystemUserAdministrationService#getUserAccount(java.lang.String)
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
        userAccount.setMedUsers(new MedUsers());
        // Set MedUser into the UserAccount.
        // userAccount.setMedUsers(medUsers);
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
}
