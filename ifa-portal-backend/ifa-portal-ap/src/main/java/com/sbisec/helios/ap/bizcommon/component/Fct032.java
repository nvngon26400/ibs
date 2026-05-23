package com.sbisec.helios.ap.bizcommon.component;

import com.sbisec.helios.ap.bizcommon.dao.Fct032Dao;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct032Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct032Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct032Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct032Sql002ResponseModel;
import com.sbisec.helios.ap.bizcommon.model.BaseOutputDto;
import com.sbisec.helios.ap.bizcommon.model.InputFct032Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct032Dto;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 共通関数：FCT032
 * ユーザー権限情報取得
 *
 * @author SCSK
 */
@Component
public class Fct032 {
    
    @Autowired
    private Fct032Dao dao;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Fct032.class);
    
    /** 権限コード */
    enum Authority {
        
        // "1" ：SBI証券本店
        SBIMAIN("1"),
        // "2" ：SBI証券支店
        SBIBRANCH("2"),
        // "3" ：仲介業者　-　内部管理責任者
        INTERNALADMINISTRATOR("3"),
        // "4" ：仲介業者　-　営業責任者
        RESPONSIBILITY("4"),
        // "5" ：仲介業者　-　外務員
        FOREIGNAFFAIRSMEMBER("5"),
        // "6" ：仲介業者 - 支店 - 内部管理責任者
        BRANCH_INTERNALADMINISTRATOR("6"),
        // "7" ：仲介業者 - 支店 - 営業責任者
        BRANCH_RESPONSIBILITY("7"),
        // "8" ：仲介業者 - 支店 - 外務員
        BRANCH_FOREIGNAFFAIRSMEMBER("8"),
        // "9" ：営業員担当
        SALESMAN("9");
        
        private String key;
        
        private Authority(String key) {
            
            this.key = key;
        }
    }
    
    /** 自動設定 */
    enum Set {
        
        // 自動設定あり
        AUTOSETOK("1"),
        // 自動設定なし
        AUTOSETNO("0");
        
        private String key;
        
        private Set(String key) {
            
            this.key = key;
        }
    }
    
    /**
     * ユーザー権限情報取得
     *
     * @param input リクエストDto
     * @return レスポンスDto
     * @throws Exception ユーザー権限情報取得時に例外が発生した場合
     */
    public OutputFct032Dto getData(InputFct032Dto input) {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Fct032.getData");
        }
        
        OutputFct032Dto resDto = new OutputFct032Dto();
        
        // ユーザ共通情報
        UserAccount ua = IfaCommonUtil.getUserAccount();
        
        // ユーザ共通情報.権限コードが
        // "1" ：SBI証券本店   または
        // "2" ：SBI証券支店   または
        // "3" ：仲介業者　-　内部管理責任者   または
        // "4" ：仲介業者　-　営業責任者   または
        // "5" ：仲介業者　-　外務員   または
        // "6" ：仲介業者 - 支店 - 内部管理責任者   または
        // "7" ：仲介業者 - 支店 - 営業責任者   または
        // "8" ：仲介業者 - 支店 - 外務員   または
        // "9" ：営業員担当   の場合
        if (StringUtils.equals(ua.getPrivId(), Authority.SBIMAIN.key)
                || StringUtils.equals(ua.getPrivId(), Authority.SBIBRANCH.key)
                || StringUtils.equals(ua.getPrivId(), Authority.INTERNALADMINISTRATOR.key)
                || StringUtils.equals(ua.getPrivId(), Authority.RESPONSIBILITY.key)
                || StringUtils.equals(ua.getPrivId(), Authority.FOREIGNAFFAIRSMEMBER.key)
                || StringUtils.equals(ua.getPrivId(), Authority.BRANCH_INTERNALADMINISTRATOR.key)
                || StringUtils.equals(ua.getPrivId(), Authority.BRANCH_RESPONSIBILITY.key)
                || StringUtils.equals(ua.getPrivId(), Authority.BRANCH_FOREIGNAFFAIRSMEMBER.key)
                || StringUtils.equals(ua.getPrivId(), Authority.SALESMAN.key)) {
            // ユーザ共通情報.権限コードごとにレスポンスに初期値を設定する。
            if (StringUtils.equals(ua.getPrivId(), Authority.SBIMAIN.key)
                    || StringUtils.equals(ua.getPrivId(), Authority.SBIBRANCH.key)) {
                // 仲介業者コード＝0：自動設定なし
                // 仲介業者支店コード＝0：自動設定なし
                // 営業員コード＝0：自動設定なし
                resDto.setBrokerCodeAutoSettingJudge(Set.AUTOSETNO.key);
                resDto.setBrokerBranchCodeSettingJudge(Set.AUTOSETNO.key);
                resDto.setEmpCodeCodeSettingJudge(Set.AUTOSETNO.key);
            } else if (StringUtils.equals(ua.getPrivId(), Authority.INTERNALADMINISTRATOR.key)
                    || StringUtils.equals(ua.getPrivId(), Authority.RESPONSIBILITY.key)
                    || StringUtils.equals(ua.getPrivId(), Authority.FOREIGNAFFAIRSMEMBER.key)) {
                // 仲介業者コード＝1：自動設定あり
                // 仲介業者支店コード＝0：自動設定なし
                // 営業員コード＝0：自動設定なし
                resDto.setBrokerCodeAutoSettingJudge(Set.AUTOSETOK.key);
                resDto.setBrokerBranchCodeSettingJudge(Set.AUTOSETNO.key);
                resDto.setEmpCodeCodeSettingJudge(Set.AUTOSETNO.key);
            } else if (StringUtils.equals(ua.getPrivId(), Authority.BRANCH_INTERNALADMINISTRATOR.key)
                    || StringUtils.equals(ua.getPrivId(), Authority.BRANCH_RESPONSIBILITY.key)
                    || StringUtils.equals(ua.getPrivId(), Authority.BRANCH_FOREIGNAFFAIRSMEMBER.key)) {
                // 仲介業者コード＝1：自動設定あり
                // 仲介業者支店コード＝1：自動設定あり
                // 営業員コード＝0：自動設定なし
                resDto.setBrokerCodeAutoSettingJudge(Set.AUTOSETOK.key);
                resDto.setBrokerBranchCodeSettingJudge(Set.AUTOSETOK.key);
                resDto.setEmpCodeCodeSettingJudge(Set.AUTOSETNO.key);
            } else if (StringUtils.equals(ua.getPrivId(), Authority.SALESMAN.key)) {
                // 仲介業者コード＝1：自動設定あり
                // 仲介業者支店コード＝0：自動設定なし
                // 営業員コード＝1：自動設定あり
                resDto.setBrokerCodeAutoSettingJudge(Set.AUTOSETOK.key);
                resDto.setBrokerBranchCodeSettingJudge(Set.AUTOSETNO.key);
                resDto.setEmpCodeCodeSettingJudge(Set.AUTOSETOK.key);
            }
            
            // if(ユーザ共通情報.権限コードが
            // "6" ：仲介業者 - 支店 - 内部管理責任者   または
            // "7" ：仲介業者 - 支店 - 営業責任者   または
            // "8" ：仲介業者 - 支店 - 外務員
            if (StringUtils.equals(ua.getPrivId(), Authority.BRANCH_INTERNALADMINISTRATOR.key)
                    || StringUtils.equals(ua.getPrivId(), Authority.BRANCH_RESPONSIBILITY.key)
                    || StringUtils.equals(ua.getPrivId(), Authority.BRANCH_FOREIGNAFFAIRSMEMBER.key)) {
                //  SQL001リクエストパラメータ        設定元
                // ユーザーID                   ユーザ共通情報.ユーザーID
                // 仲介業者コード                ユーザ共通情報.仲介業者コード
                // 仲介業者支店コード             ユーザ共通情報.仲介業者支店コード
                Fct032Sql001RequestModel sql001Req = new Fct032Sql001RequestModel();
                sql001Req.setUserId(ua.getUserId());
                sql001Req.setBrokerId(ua.getBrokerId());
                sql001Req.setSubBrokerId(ua.getSubBrokerId());
                // ユーザ権限情報取得（仲介業者支店）を呼び出す(SQL001)
                Fct032Sql001ResponseModel sql001Res = new Fct032Sql001ResponseModel();
                try {
                    sql001Res = dao.getUserAuthorityBranch(sql001Req);
                } catch (Exception e) {
                    LOGGER.error("Fct032.getData Fct032DaoImpl.getUserAuthorityBranch Exception[{}]", e.getMessage());
                    return new OutputFct032Dto();
                }
                
                // SQL001.取得件数＞0の場合
                if (sql001Res.getCount() > 0) {
                    // レスポンスの編集を行う:
                    // 仲介業者コード＝1：自動設定あり
                    // 仲介業者支店コード＝0：自動設定なし
                    // 営業員コード＝0：自動設定なし
                    resDto.setBrokerCodeAutoSettingJudge(Set.AUTOSETOK.key);
                    resDto.setBrokerBranchCodeSettingJudge(Set.AUTOSETNO.key);
                    resDto.setEmpCodeCodeSettingJudge(Set.AUTOSETNO.key);
                }
            }
            
            // if(ユーザ共通情報.権限コードが
            // "3" ：仲介業者　-　内部管理責任者   または
            // "4" ：仲介業者　-　営業責任者   または
            // "5" ：仲介業者　-　外務員   または
            // "6" ：仲介業者 - 支店 - 内部管理責任者   または
            // "7" ：仲介業者 - 支店 - 営業責任者   または
            // "8" ：仲介業者 - 支店 - 外務員   または
            // "9" ：営業員担当   の場合){
            if (StringUtils.equals(ua.getPrivId(), Authority.INTERNALADMINISTRATOR.key)
                    || StringUtils.equals(ua.getPrivId(), Authority.RESPONSIBILITY.key)
                    || StringUtils.equals(ua.getPrivId(), Authority.FOREIGNAFFAIRSMEMBER.key)
                    || StringUtils.equals(ua.getPrivId(), Authority.BRANCH_INTERNALADMINISTRATOR.key)
                    || StringUtils.equals(ua.getPrivId(), Authority.BRANCH_RESPONSIBILITY.key)
                    || StringUtils.equals(ua.getPrivId(), Authority.BRANCH_FOREIGNAFFAIRSMEMBER.key)
                    || StringUtils.equals(ua.getPrivId(), Authority.SALESMAN.key)) {
                // SQL002リクエストパラメータ        設定元
                // ユーザーID                  ユーザ共通情報.ユーザーID
                // 仲介業者コード               ユーザ共通情報.仲介業者コード
                Fct032Sql002RequestModel sql002Req = new Fct032Sql002RequestModel();
                sql002Req.setUserId(ua.getUserId());
                sql002Req.setBrokerId(ua.getBrokerId());
                // ユーザ権限情報取得（仲介業者）を呼び出す
                Fct032Sql002ResponseModel sql002Res = new Fct032Sql002ResponseModel();
                try {
                    sql002Res = dao.getUserAuthority(sql002Req);
                } catch (Exception e) {
                    LOGGER.error("Fct032.getData Fct032DaoImpl.getUserAuthority Exception[{}]", e.getMessage());
                    return new OutputFct032Dto();
                }
                // SQL002.取得件数＞0の場合
                if (sql002Res.getCount() > 0) {
                    // レスポンスの編集を行う:
                    // 仲介業者コード＝0：自動設定なし
                    // 仲介業者支店コード＝0：自動設定なし
                    // 営業員コード＝0：自動設定なし
                    resDto.setBrokerCodeAutoSettingJudge(Set.AUTOSETNO.key);
                    resDto.setBrokerBranchCodeSettingJudge(Set.AUTOSETNO.key);
                    resDto.setEmpCodeCodeSettingJudge(Set.AUTOSETNO.key);
                }
            }
        } else {
            // 返却コード(E001)をレスポンスに設定。
            resDto.setReturnCode(BaseOutputDto.RETURN_CODE_E001);
        }
        // return レスポンス
        return resDto;
    }
}
