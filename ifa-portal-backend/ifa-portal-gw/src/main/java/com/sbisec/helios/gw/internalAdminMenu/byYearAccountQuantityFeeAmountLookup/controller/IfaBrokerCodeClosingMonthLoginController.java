package com.sbisec.helios.gw.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaBrokerCodeClosingMonthLoginA001RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaBrokerCodeClosingMonthLoginA001ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaBrokerCodeClosingMonthLoginA004RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaBrokerCodeClosingMonthLoginA004ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaBrokerCodeClosingMonthLoginA006RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaBrokerCodeClosingMonthLoginA006ResponseDto;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.form.IfaBrokerCodeClosingMonthLoginApi001Request;
import com.sbisec.helios.gw.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.form.IfaBrokerCodeClosingMonthLoginApi001Response;
import com.sbisec.helios.gw.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.form.IfaBrokerCodeClosingMonthLoginApi004Request;
import com.sbisec.helios.gw.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.form.IfaBrokerCodeClosingMonthLoginApi004Response;
import com.sbisec.helios.gw.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.form.IfaBrokerCodeClosingMonthLoginApi006Request;
import com.sbisec.helios.gw.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.form.IfaBrokerCodeClosingMonthLoginApi006Response;

/**
 * 画面ID：SUB0406-01_1
 * 画面名：仲介業者決算月設定
 * 
 * @author SBI大連 夏
 * @date   2025/05/22
 */

@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN04", id = "SUB0406-01_1", screenNumber = "")
public class IfaBrokerCodeClosingMonthLoginController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaBrokerCodeClosingMonthLoginController.class);
    
    /**
     * 
     * アクセス：/internalAdminMenu/byYearAccountQuantityFeeAmountLookup/ifaBrokerCodeClosingMonthLoginInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * Api リクエスト：IfaBrokerCodeClosingMonthLoginApi001Request
     * Api レスポンス：IfaBrokerCodeClosingMonthLoginApi001Response
     * Dto リクエスト：IfaBrokerCodeClosingMonthLoginA001RequestDto
     * Dto レスポンス：IfaBrokerCodeClosingMonthLoginA001ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/internalAdminMenu/byYearAccountQuantityFeeAmountLookup/ifaBrokerCodeClosingMonthLoginInitializeA001")
    public String initializeA001(@RequestBody IfaBrokerCodeClosingMonthLoginApi001Request apiReq) throws Exception {
        
        IfaBrokerCodeClosingMonthLoginA001RequestDto appReq = new IfaBrokerCodeClosingMonthLoginA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaBrokerCodeClosingMonthLoginA001ResponseDto> appRes =
            ApiRequestUtil.invoke("cmpIfaBrokerCodeClosingMonthLoginService", "initializeA001",
                new TypeReference<DataList<IfaBrokerCodeClosingMonthLoginA001ResponseDto>>() {
                }, appReq);

        DataList<IfaBrokerCodeClosingMonthLoginApi001Response> apiRes =
            new DataList<IfaBrokerCodeClosingMonthLoginApi001Response>();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/internalAdminMenu/byYearAccountQuantityFeeAmountLookup/ifaBrokerCodeClosingMonthLoginGetBrokerNameClosingMonthA004
     * アクションID：A004
     * アクション名：仲介業者コード入力
     * Api リクエスト：IfaBrokerCodeClosingMonthLoginApi004Request
     * Api レスポンス：IfaBrokerCodeClosingMonthLoginApi004Response
     * Dto リクエスト：IfaBrokerCodeClosingMonthLoginA004RequestDto
     * Dto レスポンス：IfaBrokerCodeClosingMonthLoginA004ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/internalAdminMenu/byYearAccountQuantityFeeAmountLookup/ifaBrokerCodeClosingMonthLoginGetBrokerNameClosingMonthA004")
    public String getBrokerNameClosingMonthA004(@RequestBody IfaBrokerCodeClosingMonthLoginApi004Request apiReq) throws Exception {
        
        IfaBrokerCodeClosingMonthLoginA004RequestDto appReq = new IfaBrokerCodeClosingMonthLoginA004RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaBrokerCodeClosingMonthLoginA004ResponseDto> appRes =
            ApiRequestUtil.invoke("cmpIfaBrokerCodeClosingMonthLoginService", "getBrokerNameClosingMonthA004",
                new TypeReference<DataList<IfaBrokerCodeClosingMonthLoginA004ResponseDto>>() {
                }, appReq);

        DataList<IfaBrokerCodeClosingMonthLoginApi004Response> apiRes =
            new DataList<IfaBrokerCodeClosingMonthLoginApi004Response>();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/internalAdminMenu/byYearAccountQuantityFeeAmountLookup/ifaBrokerCodeClosingMonthLoginUpdateBrokerCodeClosingMonthA006
     * アクションID：A006
     * アクション名：登録･更新
     * Api リクエスト：IfaBrokerCodeClosingMonthLoginApi006Request
     * Api レスポンス：IfaBrokerCodeClosingMonthLoginApi006Response
     * Dto リクエスト：IfaBrokerCodeClosingMonthLoginA006RequestDto
     * Dto レスポンス：IfaBrokerCodeClosingMonthLoginA006ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/internalAdminMenu/byYearAccountQuantityFeeAmountLookup/ifaBrokerCodeClosingMonthLoginUpdateBrokerCodeClosingMonthA006")
    public String updateBrokerCodeClosingMonthA006(@RequestBody IfaBrokerCodeClosingMonthLoginApi006Request apiReq) throws Exception {
        
        IfaBrokerCodeClosingMonthLoginA006RequestDto appReq = new IfaBrokerCodeClosingMonthLoginA006RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaBrokerCodeClosingMonthLoginA006ResponseDto> appRes =
            ApiRequestUtil.invoke("cmpIfaBrokerCodeClosingMonthLoginService", "updateBrokerCodeClosingMonthA006",
                new TypeReference<DataList<IfaBrokerCodeClosingMonthLoginA006ResponseDto>>() {
                }, appReq);

        DataList<IfaBrokerCodeClosingMonthLoginApi006Response> apiRes =
            new DataList<IfaBrokerCodeClosingMonthLoginApi006Response>();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }
}
