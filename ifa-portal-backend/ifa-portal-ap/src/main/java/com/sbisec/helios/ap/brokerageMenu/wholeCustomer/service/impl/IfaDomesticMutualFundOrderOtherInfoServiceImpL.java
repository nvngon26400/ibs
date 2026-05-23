package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaDomesticMutualFundOrderOtherInfoDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDomesticMutualFundOrderOtherInfoSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDomesticMutualFundOrderOtherInfoSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDomesticMutualFundOrderOtherInfoA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDomesticMutualFundOrderOtherInfoA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.IfaDomesticMutualFundOrderOtherInfoService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

/**
 * 画面ID：SUB020302_0101-03
 * 画面名：コンプラ項目詳細
 *
 * @author BASE 丁
 2024/06/20 新規作成
 */
@Component(value = "cmpIfaDomesticMutualFundOrderOtherInfoService")
public class IfaDomesticMutualFundOrderOtherInfoServiceImpL implements IfaDomesticMutualFundOrderOtherInfoService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaDomesticMutualFundOrderOtherInfoServiceImpL.class);
	
    @Autowired
    private IfaDomesticMutualFundOrderOtherInfoDao dao;
    
    @Autowired
    private Fct030 fct030;
    
    /** ユーザ権限コード '1':SBI証券本店 */
    private static final String PRI_ID_1 = "1";
    
    private enum MessageId {
        ERRORS_CMN_IFAAGENTCODES_NOTEXIST("errors.cmn.ifaAgentCodes.notExist"),
        ERRORS_CMN_INFORMATION_NOTFOUND("errors.cmn.information.notfound"),
        ;
        private String key;
        
        private MessageId(String key) {
            this.key = key;
        }
    }

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaDomesticMutualFundOrderOtherInfoA001RequestDto
     * Dto レスポンス：IfaDomesticMutualFundOrderOtherInfoA001ResponseDto
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
	 *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaDomesticMutualFundOrderOtherInfoA001ResponseDto> initializeA001(IfaDomesticMutualFundOrderOtherInfoA001RequestDto dtoReq)
            throws Exception {
        DataList<IfaDomesticMutualFundOrderOtherInfoA001ResponseDto> dtoRes = new DataList<IfaDomesticMutualFundOrderOtherInfoA001ResponseDto>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaDomesticMutualFundOrderOtherInfoServiceImplL.initializeA001");
        
        // ① ユーザ共通情報.権限コード ≠ '1':SBI証券本店 の場合：参照可能な仲介業者コードをと営業員コードを取得する。
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        String privId = userAccount.getPrivId();
        IfaDomesticMutualFundOrderOtherInfoSql001RequestModel ifaDomesticMutualFundOrderOtherInfoSql001RequestModel = new IfaDomesticMutualFundOrderOtherInfoSql001RequestModel();
        ifaDomesticMutualFundOrderOtherInfoSql001RequestModel.setBrokerChargeList(new ArrayList<BrokerCharge>());
        if (!PRI_ID_1.equals(privId)) {
            OutputFct030Dto outputFct030Dto = fct030.getData(null);
            List<BrokerCharge> brokerChargeList = outputFct030Dto.getBrokerChargeList();
            if (brokerChargeList.size() == 0) {
                return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                        MessageId.ERRORS_CMN_IFAAGENTCODES_NOTEXIST.key, 
                        IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_IFAAGENTCODES_NOTEXIST.key));
            } else {
                ifaDomesticMutualFundOrderOtherInfoSql001RequestModel.setBrokerChargeList(brokerChargeList);
            }
        }
       // ② 国内投信注文その他情報を取得する。
        BeanUtils.copyProperties(ifaDomesticMutualFundOrderOtherInfoSql001RequestModel, dtoReq);
        ifaDomesticMutualFundOrderOtherInfoSql001RequestModel.setPrivId(privId);
        
        DataList<IfaDomesticMutualFundOrderOtherInfoSql001ResponseModel> sql001Res = dao.selectIfaDomesticMutualFundOrderOtherInfoSql001(ifaDomesticMutualFundOrderOtherInfoSql001RequestModel);
        
        if(sql001Res.size() == 1) {
           for(IfaDomesticMutualFundOrderOtherInfoSql001ResponseModel sql001ResModel:sql001Res.getDataList()) {  
            IfaDomesticMutualFundOrderOtherInfoA001ResponseDto ifaDomesticMutualFundOrderOtherInfoA001ResponseDto = new IfaDomesticMutualFundOrderOtherInfoA001ResponseDto();
            BeanUtils.copyProperties(ifaDomesticMutualFundOrderOtherInfoA001ResponseDto, sql001ResModel);
            dtoRes.getDataList().add(ifaDomesticMutualFundOrderOtherInfoA001ResponseDto);
            }
        }else {
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    MessageId.ERRORS_CMN_INFORMATION_NOTFOUND.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_INFORMATION_NOTFOUND.key)); 
        }

        return dtoRes;
    }

}
