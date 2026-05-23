package com.sbisec.helios.gw.common.util;

import com.sbisec.helios.ap.common.enums.PrivId;
import com.sbisec.helios.ap.common.model.UserAccount;

/**
 * 共通コントローラーヘルパー
 *
 * @author SCSK
 *
 */
public class CommonControllerHelper {
    
    /**
     * ユーザ共通情報に所属組織情報をセットする
     *
     * @param userAccount ユーザ共通情報
     */
    public static void setOrganization(UserAccount userAccount) {
        
        StringBuilder organization = new StringBuilder();
        String privId = userAccount.getPrivId();
        if (privId != null) {
            if (privId.equals(PrivId.HEAD_OFFICE.getId()) // 本店
                    || privId.equals(PrivId.BRANCH.getId())) { // 支店
                // 本支店名
                organization.append(userAccount.getBranchName());
                
                // 仲介業者に所属している場合「仲介業者本店名＋担当者名(ログインユーザ名)」
            } else if (privId.equals(PrivId.B_INNER_MANAGEMENT.getId())// 仲介業者(内部管理責任者)
                    || privId.equals(PrivId.B_SALES_EXECUTIVE.getId())// 仲介業者(営業責任者)
                    || privId.equals(PrivId.B_SALES_REPRESENTATIVE.getId())// 仲介業者(外務員)
                    || privId.equals(PrivId.B_OUT_SALES_REPRESENTATIVE.getId())) { // 仲介業者外務員(外出先アクセス専用)
                
                // 仲介業者本店名
                organization.append(userAccount.getBrokerName());
                
                // 仲介業者(支店)に所属している場合「仲介業者支店名＋担当者名(ログインユーザ名)」
            } else if (privId.equals(PrivId.BB_INNER_MANAGEMENT.getId()) // 仲介業者支店(内部管理責任者)
                    || privId.equals(PrivId.BB_SALES_EXECUTIVE.getId()) // 仲介業者支店(営業責任者)
                    || privId.equals(PrivId.BB_SALES_REPRESENTATIVE.getId()) // 仲介業者支店(外務員)
                    || privId.equals(PrivId.BB_OUT_SALES_REPRESENTATIVE.getId())) { // 仲介業者支店外務員(外出先アクセス専用)
                // 仲介業者支店名
                organization.append(userAccount.getSubBrokerName());
                
                // 外株の場合「groupLabel＋Label」
            } else if (privId.equals(PrivId.ES_BUSSINESS.getId()) // 外株業務部
                    || privId.equals(PrivId.ES_MANAGEMENT.getId())) { // 外株管理部
                // 仲介業者支店名
                organization.append(PrivId.valueOfId(privId).getGroupLabel() + PrivId.valueOfId(privId).getLabel());
                // 以外
            } else {
                // if(privId.equals(PrivId.RESPONSIBLE.getId()) ||              // 担当
                //    privId.equals(PrivId.ALL_RESPONSIBLE.getId())){           // 担当(全担当者参照可能)
                //    privId.equals(PrivId.OUT_RESPONSIBLE.getId())){           // 担当(外出先アクセス専用)
                // 一般仲介業支店ではない場合
                if (userAccount.getBranchKind().equals("00")) {
                    // 仲介業者本店名
                    organization.append(userAccount.getBrokerName());
                } else {
                    // 仲介業者支店名
                    organization.append(userAccount.getSubBrokerName());
                }
            }
            organization.append(" ");
            organization.append(userAccount.getUserNm());
            organization.append("（");
            organization.append(userAccount.getUserId());
            organization.append("）");
            userAccount.setOrganization(organization.toString());
        } else {
            userAccount.setOrganization(userAccount.getUserNm());
        }
    }
}
