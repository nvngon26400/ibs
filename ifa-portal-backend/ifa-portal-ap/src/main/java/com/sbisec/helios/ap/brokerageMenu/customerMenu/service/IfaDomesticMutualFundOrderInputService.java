package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA006RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA006ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA009RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA009ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA010RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA010ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0401-02_1
 * 画面名：国内投信注文入力
 * @author <author-name>
 *
 * 2024/03/25 新規作成
 */
public interface IfaDomesticMutualFundOrderInputService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaDomesticMutualFundOrderInputA001RequestDto
     * Dto レスポンス：IfaDomesticMutualFundOrderInputA001ResponseDto
     * model リクエスト：IfaDomesticMutualFundOrderInputA001RequestModel
     * model レスポンス：IfaDomesticMutualFundOrderInputA001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaDomesticMutualFundOrderInputA001ResponseDto> initializeA001(IfaDomesticMutualFundOrderInputA001RequestDto dtoReq)
            throws Exception;


    /**
     * アクションID：A004
     * アクション名：リセット
     * Dto リクエスト：IfaDomesticMutualFundOrderInputA004RequestDto
     * Dto レスポンス：IfaDomesticMutualFundOrderInputA004ResponseDto
     * model リクエスト：IfaDomesticMutualFundOrderInputA004RequestModel
     * model レスポンス：IfaDomesticMutualFundOrderInputA004ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaDomesticMutualFundOrderInputA004ResponseDto> resetA004(IfaDomesticMutualFundOrderInputA004RequestDto dtoReq)
            throws Exception;


    /**
     * アクションID：A005
     * アクション名：口座選択
     * Dto リクエスト：IfaDomesticMutualFundOrderInputA005RequestDto
     * Dto レスポンス：IfaDomesticMutualFundOrderInputA005ResponseDto
     * model リクエスト：IfaDomesticMutualFundOrderInputA005RequestModel
     * model レスポンス：IfaDomesticMutualFundOrderInputA005ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaDomesticMutualFundOrderInputA005ResponseDto> accountSelectionA005(IfaDomesticMutualFundOrderInputA005RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A006
     * アクション名：乗換優遇枠適用変更
     * Dto リクエスト：IfaDomesticMutualFundOrderInputA006RequestDto
     * Dto レスポンス：IfaDomesticMutualFundOrderInputA006ResponseDto
     * model リクエスト：IfaDomesticMutualFundOrderInputA006RequestModel
     * model レスポンス：IfaDomesticMutualFundOrderInputA006ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaDomesticMutualFundOrderInputA006ResponseDto> transferPreferentialFrameChangeA006(IfaDomesticMutualFundOrderInputA006RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A009
     * アクション名：預り区分変更
     * Dto リクエスト：IfaDomesticMutualFundOrderInputA009RequestDto
     * Dto レスポンス：IfaDomesticMutualFundOrderInputA009ResponseDto
     * model リクエスト：IfaDomesticMutualFundOrderInputA009RequestModel
     * model レスポンス：IfaDomesticMutualFundOrderInputA009ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaDomesticMutualFundOrderInputA009ResponseDto> depositCategoryChangeA009(IfaDomesticMutualFundOrderInputA009RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A010
     * アクション名：注文確認
     * Dto リクエスト：IfaDomesticMutualFundOrderInputA010RequestDto
     * Dto レスポンス：IfaDomesticMutualFundOrderInputA010ResponseDto
     * model リクエスト：IfaDomesticMutualFundOrderInputA010RequestModel
     * model レスポンス：IfaDomesticMutualFundOrderInputA010ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaDomesticMutualFundOrderInputA010ResponseDto> orderConfirmA010(IfaDomesticMutualFundOrderInputA010RequestDto dtoReq)
            throws Exception;




}
