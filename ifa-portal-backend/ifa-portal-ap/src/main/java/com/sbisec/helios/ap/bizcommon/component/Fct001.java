package com.sbisec.helios.ap.bizcommon.component;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbisec.helios.ap.bizcommon.dao.Fct001Dao;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct001Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct001Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;
import com.sbisec.helios.ap.common.enums.PrivId;
import com.sbisec.helios.ap.common.model.MedUsers;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

/**
 * 共通関数：FCT001 利用者顧客参照権限チェック
 *
 * @author SCSK
 */

@Component
public class Fct001 {
    
    // 対象顧客参照権限有無0（権限なし）
    public static final String TARGET_CUSTOMER_REF_AUTH_FLAG_0 = "0";
    
    // 対象顧客参照権限有無1（権限あり）   
    public static final String TARGET_CUSTOMER_REF_AUTH_FLAG_1 = "1";
    
    // 取引停止フラグ△（初期値）
    public static final String TRADE_SUSPEND_FLAG_INITIAL = " ";
    
    // 取引停止フラグ1（取引停止口座）
    public static final String TRADE_SUSPEND_FLAG_1 = "1";
    
    // 取引停止フラグ0（取引停止口座でない）
    public static final String TRADE_SUSPEND_FLAG_0 = "0";
    
    // コンプラランク＝Z
    public static final String TC_COMP_RANK_Z = "Z";
    
    @Autowired
    private Fct001Dao dao;
    
    // 共通関数FCT030
    @Autowired
    private Fct030 brokerCodeChargeListAcquire;
    
    //ログ
    private static final Logger LOGGER = LoggerFactory.getLogger(Fct001.class);
    
    /**
    * 利用者顧客参照権限チェック
    *
    * @param input inputFct001Dto
    * @return OutputFct001Dto
    */
    public OutputFct001Dto doCheck(InputFct001Dto input) {
        
        //開始ログ出力
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Fct001.doCheck");
        }
        
        //返却値の初期化
        OutputFct001Dto outputUserCustomerRefAuthCheckDto = new OutputFct001Dto();
        
        //必須項目対する入力値チェック、NULLの場合空DTOを返却
        if (input == null || input.getAccountNumber() == null || input.getButenCode() == null) {
            return outputUserCustomerRefAuthCheckDto;
        }
        
        //SQLのパラメータ設定
        Fct001Sql001RequestModel fct001Sql001RequestModel = new Fct001Sql001RequestModel();
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        MedUsers medUsers = userAccount.getMedUsers();
        fct001Sql001RequestModel.setPrivId(medUsers.getPrivId());
        fct001Sql001RequestModel.setAccountNumber(input.getAccountNumber());
        fct001Sql001RequestModel.setButenCode(input.getButenCode());
        
        OutputFct030Dto outputBrokerCodeChargeListAcquireDto = null;
        if (!PrivId.HEAD_OFFICE.getId().equals(medUsers.getPrivId())) {
            // 利用者顧客参照範囲の仲介業者コード、営業員コードを取得する。(FCT030を呼び出す)
            outputBrokerCodeChargeListAcquireDto = brokerCodeChargeListAcquire.getData(null);
            // FCT030の取得結果0件の場合
            if (outputBrokerCodeChargeListAcquireDto.getBrokerChargeList().size() == 0) {
                // 対象顧客参照権限有無に0（権限なし）をセット
                outputUserCustomerRefAuthCheckDto.setTargetCustomerRefAuthFlag(TARGET_CUSTOMER_REF_AUTH_FLAG_0);
                // 取引停止フラグに△（初期値）をセット
                outputUserCustomerRefAuthCheckDto.setTradeSuspendFlag(TRADE_SUSPEND_FLAG_INITIAL);
                return outputUserCustomerRefAuthCheckDto;
            }
            //SQLのパラメータ仲介業者コード、営業員コードを追加
            fct001Sql001RequestModel.setBrokerChargeList(outputBrokerCodeChargeListAcquireDto.getBrokerChargeList());
        }
        
        List<Fct001Sql001ResponseModel> userCustomerRefAuthList = dao.getUserCustomerRefAuth(fct001Sql001RequestModel);
        if (userCustomerRefAuthList.size() == 0) {
            // 取得結果が0件の場合
            // 対象顧客参照権限有無に0（権限なし）をセット
            outputUserCustomerRefAuthCheckDto.setTargetCustomerRefAuthFlag(TARGET_CUSTOMER_REF_AUTH_FLAG_0);
            // 取引停止フラグに△（初期値）をセット
            outputUserCustomerRefAuthCheckDto.setTradeSuspendFlag(TRADE_SUSPEND_FLAG_INITIAL);
        } else {
            // 取得結果が0件以外の場合
            // 対象顧客参照権限有無に1（権限あり）をセット
            outputUserCustomerRefAuthCheckDto.setTargetCustomerRefAuthFlag(TARGET_CUSTOMER_REF_AUTH_FLAG_1);
            if (TC_COMP_RANK_Z.equals(userCustomerRefAuthList.get(0).getTcCompRank())) {
                // コンプラランクがZの場合
                // 取引停止フラグに1（取引停止口座）をセット
                outputUserCustomerRefAuthCheckDto.setTradeSuspendFlag(TRADE_SUSPEND_FLAG_1);
            } else {
                // コンプラランクがZ以外の場合
                // 取引停止フラグに0（取引停止口座でない）をセット
                outputUserCustomerRefAuthCheckDto.setTradeSuspendFlag(TRADE_SUSPEND_FLAG_0);
            }
        }
        return outputUserCustomerRefAuthCheckDto;
    }
    
}
