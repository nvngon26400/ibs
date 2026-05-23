package com.sbisec.helios.ap.bizcommon.component;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.dao.Fct003Dao;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct003Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct003Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct003Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct003Sql002ResponseModel;
import com.sbisec.helios.ap.bizcommon.model.BaseOutputDto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct009Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto.MediatePropriety;
import com.sbisec.helios.ap.bizcommon.model.OutputFct009Dto;
import com.sbisec.helios.ap.common.exception.IfaRuntimeException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 共通関数：FCT003 取引コース媒介可否チェック
 *
 * @author base 鄒
 */
@Component
public class Fct003 {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Fct003.class);
    
    // 媒介可否(不可)
    private static final String MEDIATE_PROPRIETY_VALUE_0 = "0";
    
    // 媒介可否(可)
    private static final String MEDIATE_PROPRIETY_VALUE_1 = "1";
    
    // 媒介可否(条件付可（MP直営、共同店舗・募集のみ可）)
    private static final String MEDIATE_PROPRIETY_VALUE_2 = "2";
    
    // 媒介可否(条件付可（新生WMのみ可）)
    private static final String MEDIATE_PROPRIETY_VALUE_3 = "3";

    // 仲介業者コード(0509)
    private static final String BROKER_CODE_0509 = "0509";
    
    // 仲介業者コード(2010)
    private static final String BROKER_CODE_2010 = "2010";
    
    // 媒介可取引有無フラグ
    private static final boolean MEDIATE_PROPRIETY_EXITS_FLAG = true;
    
    // 媒介可取引有無(1:あり)
    private static final String MEDIATE_ABLE_TRADE_FLAG_1 = "1";
    
    // 媒介可取引有無(0:なし)
    private static final String MEDIATE_ABLE_TRADE_FLAG_0 = "0";
    
    private static final String MESSAGE_E001 = "取引種別は証券金銭種別を指定した場合のみ指定可能";
    
    private static final String MESSAGE_E002 = "国籍コードは証券金銭種別と取引種別を指定した場合のみ指定可能";
    
    private static final String MESSAGE_E003 = "通貨コードは証券金銭種別と取引種別を指定した場合のみ指定可能";
    
    private static final String MESSAGE_E004 = "指定された口座情報が仲介業者顧客口座属性から取得できない";
    
    @Autowired
    private Fct003Dao fct003Dao;
    
    // 共通関数FCT009
    @Autowired
    private Fct009 fct009;
    
    /**
     * FCT003 取引コース媒介可否チェック
     *
     * @param input リクエスト
     * @return outputFct003Dto
     */
    public OutputFct003Dto doCheck(InputFct003Dto input) {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Fct003.doCheck");
        }
        
        // レスポンス項目を初期化する。
        OutputFct003Dto outputFct003Dto = new OutputFct003Dto();
        // ① リクエスト項目に対して相関チェックを行う。
        // 取引種別が指定されているのに証券金銭種別が指定されていない場合、エラーを返す。
        if (StringUtil.isNullOrEmpty(input.getCurrencyCode()) && StringUtil.isNullOrEmpty(input.getCountryCd())
                && !StringUtil.isNullOrEmpty(input.getTradeCd()) && StringUtil.isNullOrEmpty(input.getProductCd())) {
            // エラーを返す
            outputFct003Dto.setReturnCode(BaseOutputDto.MESSAGE_KEY_E001);
            outputFct003Dto.setErrMessage(MESSAGE_E001);
            return outputFct003Dto;
        }
        // 国籍コードが指定されているのに証券金銭種別または取引種別が指定されていない場合、エラーを返す。
        if (!StringUtil.isNullOrEmpty(input.getCountryCd())
                && (StringUtil.isNullOrEmpty(input.getTradeCd()) || StringUtil.isNullOrEmpty(input.getProductCd()))) {
            // エラーを返す
            outputFct003Dto.setReturnCode(BaseOutputDto.MESSAGE_KEY_E002);
            outputFct003Dto.setErrMessage(MESSAGE_E002);
            return outputFct003Dto;
        }
        // 通貨コードが指定されているのに証券金銭種別または取引種別が指定されていない場合、エラーを返す。
        if (!StringUtil.isNullOrEmpty(input.getCurrencyCode())
                && (StringUtil.isNullOrEmpty(input.getTradeCd()) || StringUtil.isNullOrEmpty(input.getProductCd()))) {
            // エラーを返す
            outputFct003Dto.setReturnCode(BaseOutputDto.MESSAGE_KEY_E003);
            outputFct003Dto.setErrMessage(MESSAGE_E003);
            return outputFct003Dto;
        }
        
        // ② 顧客の取引コース、仲介業者コードを取得する。
        Fct003Sql001RequestModel fct003Sql001RequestModel = new Fct003Sql001RequestModel();
        fct003Sql001RequestModel.setButenCode(input.getButenCode());
        fct003Sql001RequestModel.setAccountNumber(input.getAccountNumber());
        List<Fct003Sql001ResponseModel> fct003Sql001ResponseList = fct003Dao.getFct003Sql001(fct003Sql001RequestModel);
        if (null == fct003Sql001ResponseList || fct003Sql001ResponseList.size() != 1) {
            // 上記以外：処理を終了して呼出元にエラーを返す。
            outputFct003Dto.setReturnCode(BaseOutputDto.MESSAGE_KEY_E004);
            outputFct003Dto.setErrMessage(MESSAGE_E004);
            return outputFct003Dto;
        }
        // 取得件数1件：次の処理へ。
        // ③ 共同店舗・募集顧客に該当するかどうかを取得する。
        InputFct009Dto inputFct009Dto = new InputFct009Dto();
        inputFct009Dto.setAccountNumber(input.getAccountNumber());
        inputFct009Dto.setButenCode(input.getButenCode());
        OutputFct009Dto outputFct009Dto = fct009.doCheck(inputFct009Dto);
        
        // ④ 取引コース媒介可否リストを取得する。
        Fct003Sql002RequestModel fct003Sql002RequestModel = new Fct003Sql002RequestModel();
        fct003Sql002RequestModel.setCustomerAttribute(fct003Sql001ResponseList.get(0).getCustomerAttribute());
        fct003Sql002RequestModel.setProductCd(input.getProductCd());
        fct003Sql002RequestModel.setTradeCd(input.getTradeCd());
        fct003Sql002RequestModel.setCountryCd(input.getCountryCd());
        fct003Sql002RequestModel.setCurrencyCode(input.getCurrencyCode());
        List<Fct003Sql002ResponseModel> fct003Sql002ResponseList = fct003Dao.getFct003Sql002(fct003Sql002RequestModel);
        
        // ⑤ SQL002の結果からレスポンス.媒介可否リストの中身をセットする。（取得件数0件の場合は空のリストをセット。）
        boolean mediateProprietyFlag = false;
        List<MediatePropriety> mediateProprietyList = new ArrayList();
        for (Fct003Sql002ResponseModel fct003Sql002ResponseModel : fct003Sql002ResponseList) {
            MediatePropriety mediatePropriety = new MediatePropriety();
            // 証券金銭種別にSQL002.証券金銭種別をセットする。
            mediatePropriety.setSecurityMoneyClass(fct003Sql002ResponseModel.getProductCd());
            // 取引種別にSQL002.取引種別をセットする。
            mediatePropriety.setTradeClass(fct003Sql002ResponseModel.getTradeCd());
            // 国籍コードにSQL002.国籍コードをセットする。
            mediatePropriety.setNationalityCode(fct003Sql002ResponseModel.getCountryCd());
            // 通貨コードにSQL002.通貨コードをセットする。
            mediatePropriety.setCurrencyCode(fct003Sql002ResponseModel.getCurrencyCode());
            // 媒介可否を以下のとおりセットする。
            if (MEDIATE_PROPRIETY_VALUE_1.equals(fct003Sql002ResponseModel.getValue())) {
                mediatePropriety.setMediatePropriety(MEDIATE_PROPRIETY_VALUE_1);
                mediateProprietyFlag = MEDIATE_PROPRIETY_EXITS_FLAG;
            } else if (MEDIATE_PROPRIETY_VALUE_0.equals(fct003Sql002ResponseModel.getValue())) {
                mediatePropriety.setMediatePropriety(MEDIATE_PROPRIETY_VALUE_0);
            } else if (MEDIATE_PROPRIETY_VALUE_2.equals(fct003Sql002ResponseModel.getValue())) {
                if (BROKER_CODE_0509.equals(fct003Sql001ResponseList.get(0).getBrokerCode())
                        || Integer.valueOf(outputFct009Dto.getAcquireNumber()) > 0) {
                    mediatePropriety.setMediatePropriety(MEDIATE_PROPRIETY_VALUE_1);
                    mediateProprietyFlag = MEDIATE_PROPRIETY_EXITS_FLAG;
                } else {
                    mediatePropriety.setMediatePropriety(MEDIATE_PROPRIETY_VALUE_0);
                }
            } else if (MEDIATE_PROPRIETY_VALUE_3.equals(fct003Sql002ResponseModel.getValue())) {
                if (BROKER_CODE_2010.equals(fct003Sql001ResponseList.get(0).getBrokerCode())) {
                    mediatePropriety.setMediatePropriety(MEDIATE_PROPRIETY_VALUE_1);
                    mediateProprietyFlag = MEDIATE_PROPRIETY_EXITS_FLAG;
                } else {
                    mediatePropriety.setMediatePropriety(MEDIATE_PROPRIETY_VALUE_0);
                }
            } else {
                throw new IfaRuntimeException("対象外エラー");
            }
            mediateProprietyList.add(mediatePropriety);
        }
        outputFct003Dto.setMediateProprietyList(mediateProprietyList);
        if (mediateProprietyFlag) {
            outputFct003Dto.setMediateAbleTradeFlag(MEDIATE_ABLE_TRADE_FLAG_1);
        } else {
            outputFct003Dto.setMediateAbleTradeFlag(MEDIATE_ABLE_TRADE_FLAG_0);
        }
        return outputFct003Dto;
    }
}
