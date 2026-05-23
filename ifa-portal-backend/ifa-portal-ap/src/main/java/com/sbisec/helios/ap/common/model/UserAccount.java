package com.sbisec.helios.ap.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sbibits.earth.model.ModelBase;
import com.sbisec.helios.ap.common.enums.AccControlId;
import com.sbisec.helios.ap.common.enums.PrivId;

import org.apache.commons.collections.CollectionUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**　ユーザ共通情報 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@EqualsAndHashCode(callSuper = false)
public class UserAccount extends ModelBase {
    
    /* serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    private MedUsers medUsers;
    
    private UserPermissionInfo userPermissionInfo;
    
    /** 本支店 */
    private Branch branch;
    
    /** 仲介業者本店 */
    private MediateBranch broker;
    
    /** 仲介業者支店 */
    private MediateBranch subBroker;
    
    /** 仲介業者営業員情報 */
    private MediateChargeInfo mediateChargeInfo;
    
    private String organization;
    
    private Boolean userNeedsToReadComplianceLetters;
    
    private Boolean userNeedsToReadMonthlySelfcheck;
    
    private List<AccControl> accControls;
    
    private Boolean courseKDispFlg;
    
    private static final String CSV_DOWN_LOAD = "btnCsvDownload";
    
    private static final String BROKER_CODE = "brokerCode";
    
    private static final String EMPLOYEE_CODE = "empCode";
    
    private static final String JOINT_BRANCH_CODE = "jointBranchCode";
    
    private static final String ITEM_ID_VIEW_CTL = "viewCtl";
    
    private String ccsUserID;
    
    public UserAccount() {
        
    }
    
    @JsonCreator
    public UserAccount(@JsonProperty("medUsers") MedUsers medUsers,
            @JsonProperty("userPermissionInfo") UserPermissionInfo userPermissionInfo,
            @JsonProperty("branch") Branch branch, @JsonProperty("broker") MediateBranch broker,
            @JsonProperty("subBroker") MediateBranch subBroker,
            @JsonProperty("mediateChargeInfo") MediateChargeInfo mediateChargeInfo,
            @JsonProperty("organization") String organization,
            @JsonProperty("userNeedsToReadComplianceLetters") Boolean userNeedsToReadComplianceLetters,
            @JsonProperty("userNeedsToReadMonthlySelfcheck") Boolean userNeedsToReadMonthlySelfcheck,
            @JsonProperty("accControls") List<AccControl> accControls,
            @JsonProperty("courseKDispFlg") Boolean courseKDispFlg) {
        
        this.medUsers = medUsers;
        this.userPermissionInfo = userPermissionInfo;
        this.branch = branch;
        this.broker = broker;
        this.subBroker = subBroker;
        this.mediateChargeInfo = mediateChargeInfo;
        this.organization = organization;
        this.userNeedsToReadComplianceLetters = userNeedsToReadComplianceLetters;
        this.userNeedsToReadMonthlySelfcheck = userNeedsToReadMonthlySelfcheck;
        this.accControls = accControls;
        this.courseKDispFlg = courseKDispFlg;
    }
    
    // ------------------------------------------------------------------------
    // ユーザ情報
    // ------------------------------------------------------------------------
    
    @JsonIgnore
    public String getUserId() {
        
        return medUsers.getUserId();
    }
    
    @JsonIgnore
    public String getUserNm() {
        
        return medUsers.getUserNm();
    }
    
    @JsonIgnore
    public String getPassword() {
        
        return medUsers.getPassword();
    }
    
    @JsonIgnore
    public Date getLastpwuptime() {
        
        return medUsers.getLastpwuptime();
    }
    
    @JsonIgnore
    public String getPrivId() {
        
        return medUsers.getPrivId();
    }
    
    @JsonIgnore
    public String getBranchId() {
        
        return medUsers.getBranchId();
    }
    
    @JsonIgnore
    public String getBrokerId() {
        
        return medUsers.getBrokerId();
    }
    
    @JsonIgnore
    public String getSubBrokerId() {
        
        return medUsers.getSubBrokerId();
    }
    
    @JsonIgnore
    public String getEmployeeId() {
        
        return medUsers.getEmployeeId();
    }
    
    @JsonIgnore
    public String getEmployeeName() {
        
        return medUsers.getEmployeeName();
    }
    
    @JsonIgnore
    public String getGovernorFlag() {
        
        return medUsers.getGovernorFlag();
    }
    
    @JsonIgnore
    public String getLoginStatus() {
        
        return medUsers.getLoginStatus();
    }
    
    @JsonIgnore
    public String getPartnerUserId() {
        
        return medUsers.getPartnerUserId();
    }
    
    @JsonIgnore
    public String getPartnerUserPw() {
        
        return medUsers.getPartnerUserPw();
    }
    
    @JsonIgnore
    public String getCcsUserId() {
        
        return medUsers.getCcsUserId();
    }
    
    @JsonIgnore
    public String getCcsUserPw() {
        
        return medUsers.getCcsUserPw();
    }
    
    @JsonIgnore
    public String getCreateUser() {
        
        return medUsers.getCreateUser();
    }
    
    @JsonIgnore
    public Date getCreateTime() {
        
        return medUsers.getCreateTime();
    }
    
    @JsonIgnore
    public String getUptimestampUser() {
        
        return medUsers.getUptimestampUser();
    }
    
    @JsonIgnore
    public Date getUptimestampTime() {
        
        return medUsers.getUptimestampTime();
    }
    
    @JsonIgnore
    public String getMailAddress() {
        
        return medUsers.getMailAddress();
    }
    
    // ------------------------------------------------------------------------
    // メニュー許可リスト
    // ------------------------------------------------------------------------
    
    @JsonIgnore
    public List<String> getAccessibleViewList() {
        
        return userPermissionInfo.getAccessibleViewList();
    }
    
    // ------------------------------------------------------------------------
    // 本支店
    // ------------------------------------------------------------------------
    
    @JsonIgnore
    public String getBranchCode() {
        
        return branch.getBranchCode();
    }
    
    @JsonIgnore
    public String getBranchKind() {
        
        return branch.getBranchKind();
    }
    
    @JsonIgnore
    public String getBranchName() {
        
        return branch.getBranchName();
    }
    
    // ------------------------------------------------------------------------
    // 仲介業者本店
    // ------------------------------------------------------------------------
    
    @JsonIgnore
    public String getBrokerCode() {
        
        return broker != null ? broker.getBrokerCode() : null;
    }
    
    @JsonIgnore
    public String getBrokerKind() {
        
        return broker != null ? broker.getBrokerBranchKind() : null;
    }
    
    @JsonIgnore
    public String getBrokerName() {
        
        return broker != null ? broker.getBranchName() : null;
    }
    
    // ------------------------------------------------------------------------
    // 仲介業者支店
    // ------------------------------------------------------------------------
    
    @JsonIgnore
    public String getSubBrokerCode() {
        
        return subBroker != null ? subBroker.getBrokerBranchCode() : null;
    }
    
    @JsonIgnore
    public String getSubBrokerKind() {
        
        return subBroker != null ? subBroker.getBrokerBranchKind() : null;
    }
    
    @JsonIgnore
    public String getSubBrokerName() {
        
        return subBroker != null ? subBroker.getBranchName() : null;
    }
    
    // ------------------------------------------------------------------------
    // 仲介業者営業員情報
    // ------------------------------------------------------------------------
    
    @JsonIgnore
    public String getBrokerChargeCode() {
        
        return mediateChargeInfo != null ? mediateChargeInfo.getBrokerChargeCode() : null;
    }
    
    @JsonIgnore
    public String getBrokerChargeName() {
        
        return mediateChargeInfo != null ? mediateChargeInfo.getBrokerChargeName() : null;
    }
    
    @JsonIgnore
    public String getEmployee() {
        
        return mediateChargeInfo != null ? mediateChargeInfo.getBrokerChargeCode() : null;
    }
    
    // ------------------------------------------------------------------------
    // 権限判定メソッド
    // ------------------------------------------------------------------------
    
    /**
     * SSOログイン可否チェック結果（CCSログイン情報登録有無）
     *
     * @return SSOログイン可否
     */
    public boolean isAccessibleToCcs() {
        
        return getCcsUserId() != null && getCcsUserPw() != null;
    }
    
    @JsonIgnore
    public boolean isAccessible(String menuId) {
        
        if (userPermissionInfo == null) {
            return false;
        }
        
        boolean accessible = userPermissionInfo.isAccessible(menuId);
        
        if (!accessible) {
            return false;
        }
        
        return true;
    }
    
    /**
     * CSVダウンロード可否チェック結果
     *
     * @return CSVダウンロード可否
     */
    @JsonProperty
    public boolean csvDownloadAllowed() {
        
        if (CollectionUtils.isEmpty(accControls)) {
            return false;
        }
        
        Optional<AccControl> ctlop = accControls.stream().filter(row -> row.getItemId().equals(CSV_DOWN_LOAD))
                .findFirst();
        int ctl = ctlop.get().getAccControl();
        
        if (ctl == AccControlId.ENABLE.getId()) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 個人情報管理台帳ダウンロード要否チェック結果
     *
     * @return 個人情報管理台帳ダウンロード要否
     */
    @JsonProperty
    public boolean csvDownloadPrivacyConfirmationRequired() {
        
        PrivId privId = PrivId.valueOfId(getPrivId());
        
        if (PrivId.B_INNER_MANAGEMENT.equals(privId)) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 仲介業者コード指定検索可否チェック
     *
     * @return 仲介業者コード指定検索可否
     */
    @JsonProperty("searchByBrokerCode")
    public boolean canSearchByBrokerCode() {
        
        if (CollectionUtils.isEmpty(accControls)) {
            return false;
        }
        
        Optional<AccControl> ctlop = accControls.stream().filter(row -> row.getItemId().equals(BROKER_CODE))
                .findFirst();
        
        int ctl = ctlop.get().getAccControl();
        
        if (ctl == AccControlId.ENABLE.getId()) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 営業員コード指定検索可否チェック
     *
     * @return 営業員コード指定検索可否
     */
    @JsonProperty("searchByEmployee")
    public boolean canSearchByEmployee() {
        
        if (CollectionUtils.isEmpty(accControls)) {
            return false;
        }
        
        Optional<AccControl> ctlop = accControls.stream().filter(row -> row.getItemId().equals(EMPLOYEE_CODE))
                .findFirst();
        int ctl = ctlop.get().getAccControl();
        
        if (ctl == AccControlId.ENABLE.getId()) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 共募支店コード指定検索可否チェック
     *
     * @return 共募支店コード指定検索可否
     */
    @JsonProperty("searchByjointBranchCode")
    public boolean canSearchByjointBranchCode() {
        
        if (CollectionUtils.isEmpty(accControls)) {
            return false;
        }
        
        Optional<AccControl> ctlop = accControls.stream().filter(row -> row.getItemId().equals(JOINT_BRANCH_CODE))
                .findFirst();
        int ctl = ctlop.get().getAccControl();
        
        if (ctl == AccControlId.ENABLE.getId()) {
            return true;
        } else {
            return false;
        }
    }
    
    // ------------------------------------------------------------------------
    // その他
    // ------------------------------------------------------------------------
    
    // 組織:ユーザID
    @JsonIgnore
    public void mergeAccessibleView() {
        
        List<String> removeList = new ArrayList<String>();
        
        for (int i = 0; i < accControls.size(); i++) {
            
            AccControl ctl = accControls.get(i);
            
            String itemId = ctl.getItemId();
            
            if (itemId.startsWith(ITEM_ID_VIEW_CTL) && ctl.getAccControl().intValue() != AccControlId.ENABLE.getId()) {
                removeList.add(ctl.getMenuId());
            }
        }
        
        for (int i = 0; i < removeList.size(); i++) {
            userPermissionInfo.removeAccessibleView(removeList.get(i));
        }
    }
    
    @JsonIgnore
    public String toString() {
        
        return getUserId();
    }
}
