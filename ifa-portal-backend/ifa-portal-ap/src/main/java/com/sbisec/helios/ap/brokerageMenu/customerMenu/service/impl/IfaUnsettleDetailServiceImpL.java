package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaUnsettleDetailA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaUnsettleDetailA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaUnsettleDetailA001ResponseDto_UnsettleDetail;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.ApplyType;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.SortType;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaUnsettleDetailService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sbisec.pcenter.dto.yanap.QueryStatementAccountDetail;
import jp.co.sbisec.pcenter.dto.yanap.QueryStatementAccountIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryStatementAccountInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryStatementAccountOutData;

/**
 * 画面ID：SUB0202_010301-02
 * 画面名：未精算明細
 * @author 布施佑太
 *
 * 2023/09/15 新規作成
 */
@Component(value = "cmpIfaUnsettleDetailService")
public class IfaUnsettleDetailServiceImpL implements IfaUnsettleDetailService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaUnsettleDetailServiceImpL.class);
    
    /**
     * API呼び出しクラス
     */
    @Autowired
    private ApiWrapper apiwrapper;
    
    /**
     * 共通関数Function001クラス
     */
    @Autowired
    private Fct001 fct001;
    
    /** 検索番号 FORM */
    private static final String SEARCH_REF_FROM_FIRST = String.format("%05d", 1);
    
    /** 検索番号 TO */
    private static final String SEARCH_REF_TO_FIRST = String.format("%05d", 100);
    
    /** 国内株式 */
    private static final String DOMESTIC_STOCK = "K0";
    
    /** 国内債権 */
    private static final String DOMESTIC_CLAIM = "S0";
    
    /** 国内投信 */
    private static final String DOMESTIC_MUTUAL = "T0";
    
    /** 外国株式 */
    private static final String FOREIGN_STOCK = "K1";
    
    /** 外国債権 */
    private static final String FOREIGN_CLAIM = "S1";
    
    /** 外国投信 */
    private static final String FOREIGN_MUTUAL = "T1";
    
    /** スペース */
    private static final String SPACE = " ";
    
    /** ハイフン */
    private static final String HYPHEN = "-";
    
    /** 空文字 */
    private static final String EMPTY_STRING = "";
    
    /** 摘要銘柄名フォーマット */
    private static final String BRAND_NAME_FORMAT = "(%s)";

    /** 数量フォーマット（国内株、国内債券、国内投信、外国債券） */
    private static final String QUANTITY_FORMAT = "#,##0";

    /** 単価フォーマット（国内株式、国内投信用） */
    private static final String DOMESTIC_FORMAT = "#,##0.##";
    
    /** 単価フォーマット（国内債券、外国債券用） */
    private static final String CLAIM_FORMAT = "#,##0.0000";
    
    /** 項番　初期値 */
    private static final int KOUBAN_DEFAULT = 1;
    
    /** 国内外国区分　外国 */
    private static final String DOMESTIC_FOREIGN_KBN_FOREIGN = "1";
    
    /** 摘要表示　外国株式 */
    private static final String SUMMARY_FOREIGN_STOCK = "外国株式";
    
    /** 摘要表示　信用 */
    private static final String SUMMARY_CREDIT = "信用";
    
    /** 入力した部店口座は存在しません。<br>部店: [{0}]、口座: [{1}] */
    private static final String ERRORS_BUTENACCOUNTNOTEXIST = "errors.butenAccountNotExist";
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaUnsettleDetailA001DtoRequest
     * Dto レスポンス：IfaUnsettleDetailA001DtoResponse
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaUnsettleDetailA001DtoResponse> initializeA001(IfaUnsettleDetailA001DtoRequest dtoReq)
            throws Exception {
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaUnsettleDetailServiceImplL.initializeA001");
        
        DataList<IfaUnsettleDetailA001DtoResponse> dtoRes = new DataList<IfaUnsettleDetailA001DtoResponse>();
        List<IfaUnsettleDetailA001DtoResponse> resDtoList = new ArrayList<IfaUnsettleDetailA001DtoResponse>();
        IfaUnsettleDetailA001DtoResponse resDto = new IfaUnsettleDetailA001DtoResponse();
        
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        String butenCode = dtoReq.getButenCode();
        String accountNumber = dtoReq.getAccountNumber();
        String requestType = dtoReq.getRequestType();
        String requestDateClassification = dtoReq.getRequestDateClassification();
        String settlementDate = dtoReq.getSettlementDate();
        String accountType = dtoReq.getAccountType();
        
        // 初期化
        String errorMessage = StringUtil.EMPTY_STRING;
        String errorMessageId = StringUtil.EMPTY_STRING;
        
        //①利用者の口座に対する権限チェックを行う。
        if (!callFCT001(butenCode, accountNumber)) {
            errorMessage = IfaCommonUtil.getMessage(ERRORS_BUTENACCOUNTNOTEXIST,
                    new String[] { butenCode, accountNumber });
            errorMessageId = ERRORS_BUTENACCOUNTNOTEXIST;
            return IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, errorMessageId, errorMessage);
        }
        
        //②未精算明細情報を取得する        
        QueryStatementAccountInData api001InDto = new QueryStatementAccountInData();
        api001InDto.setButenCd(butenCode);
        api001InDto.setKozaNo(createApiRequestAccountNo(accountNumber));
        api001InDto.setRequestType(requestType);
        api001InDto.setRequestSub(requestDateClassification);
        api001InDto.setSettlementDate(settlementDate);
        api001InDto.setRefFrom(SEARCH_REF_FROM_FIRST);
        api001InDto.setRefTo(SEARCH_REF_TO_FIRST);
        api001InDto.setAccountGetKbn(accountType);
        
        QueryStatementAccountIn input = new QueryStatementAccountIn();
        input.setIndata(api001InDto);
        
        QueryStatementAccountOutData api001ResDto = apiwrapper.queryStatementAccount(input);
        apiErrorUtil.checkApiResponse(api001ResDto.getShubetu(), api001ResDto.getCode(), api001ResDto.getMessage());
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        
        // TODO 不具合検証用
        LOGGER.debug(ReflectionToStringBuilder.toString(api001ResDto,ToStringStyle.MULTI_LINE_STYLE));
        
        //分類表示
        resDto.setClassificationDisplay(SortType.nameOfValue(requestType).getName());
        
        // 未精算明細リストの作成
        List<IfaUnsettleDetailA001ResponseDto_UnsettleDetail> unsettleDetail = new ArrayList<IfaUnsettleDetailA001ResponseDto_UnsettleDetail>();
        addDetail(api001ResDto.getReceiptData(), unsettleDetail);
        
        //顧客名をセット
        resDto.setCustomerName(String.format("%s（%s）", cc.getCustomerNameKanji(), cc.getCustomerNameKana()));
        //口座番号をセット
        resDto.setAccountNumber(String.format("%s-%s", cc.getButenCode(), cc.getAccountNumber()));
        //検索結果総数をセット
        resDto.setSearchResultCount(api001ResDto.getHitNumber());
        //精算金額合計をセット
        resDto.setSumSettlementAmount(api001ResDto.getTotalAmount());
        //未精算明細リストをセット
        resDto.setUnsettleDetail(unsettleDetail);
        resDtoList.add(resDto);
        
        dtoRes = apiErrorUtil.createDataList(resDtoList, null);
        
        return dtoRes;
    }
    
    /**
     * 未精算明細リストから1件ずつ未精算明細をセット
     * @param unsettleDetailData
     */
    private void addDetail(List<QueryStatementAccountDetail> unsettleDetailData,
            List<IfaUnsettleDetailA001ResponseDto_UnsettleDetail> unsettleDetail) {
        
        int kouban = KOUBAN_DEFAULT;
        for (QueryStatementAccountDetail detailData : unsettleDetailData) {
            
            IfaUnsettleDetailA001ResponseDto_UnsettleDetail detail = new IfaUnsettleDetailA001ResponseDto_UnsettleDetail();
            
            //摘要コード
            String applyCode = detailData.getApplyCode();
            //商品タイプ（商品区分+国内外国区分）
            String secType = detailData.getSecId() + detailData.getDomesticFgnId();
            
            //項番をセット
            detail.setKouban(Integer.toString(kouban));
            
            //受渡日をセット
            detail.setSettlementDate(detailData.getSettlementDate());
            
            //約定日をセット
            detail.setTradeDate(getTradeDate(detailData));
            
            //取引をセット
            detail.setOpenTradeKbn(detailData.getTradeCode());
            
            //摘要をセット
            detail.setDispAbstract(getDisplayApplyName(detailData, applyCode, secType));
            
            //数量をセット
            detail.setQuantity(getQuantity(detailData, secType));
            
            //単価をセット
            detail.setPrice(getPrice(detailData, secType));
            
            //精算金額をセット
            detail.setSettlementAmount(detailData.getSettleAmount());
            
            kouban++;
            
            //未精算明細リストにセット
            unsettleDetail.add(detail);
        }
    }
    
    /**
     *　単価取得
     * @param unsettleDetailData
     * @param secType
     * @return 単価
     */
    private String getPrice(QueryStatementAccountDetail unsettleDetailData, String secType) {
        
        String price = unsettleDetailData.getPrice();
        DecimalFormat domesticFormat = new DecimalFormat(DOMESTIC_FORMAT);
        DecimalFormat claimFormat = new DecimalFormat(CLAIM_FORMAT);
        
        if (StringUtils.equals(secType, DOMESTIC_STOCK) || StringUtils.equals(secType, DOMESTIC_MUTUAL)) {
            
            //#,##0.##形式で設定
            price = domesticFormat.format(Double.parseDouble(price));
        } else if (StringUtils.equals(secType, DOMESTIC_CLAIM) || StringUtils.equals(secType, FOREIGN_CLAIM)) {
            
            //#,##0.0000形式で設定
            price = claimFormat.format(Double.parseDouble(price));
        } else if (StringUtils.equals(secType, FOREIGN_STOCK) || StringUtils.equals(secType, FOREIGN_MUTUAL)) {
            
            //未設定
            price = null;
        } else {
            
            price = HYPHEN;
        }
        
        return price;
    }
    
    /**
     * 数量取得
     * @param unsettleDetailData
     * @param secType
     * @return 数量
     */
    private String getQuantity(QueryStatementAccountDetail unsettleDetailData, String secType) {
        
        String quantity = null;
        
        // ①国内株、国内債券、国内投信の場合
        if (StringUtils.equals(secType, DOMESTIC_STOCK) || StringUtils.equals(secType, DOMESTIC_CLAIM)
                || StringUtils.equals(secType, DOMESTIC_MUTUAL)) {
            
            // 数量
            if (unsettleDetailData.getAmount() != null && unsettleDetailData.getAmount().trim().length() != 0) {
                DecimalFormat decimalFormat = new DecimalFormat(QUANTITY_FORMAT);
                quantity = decimalFormat.format(Long.parseLong(unsettleDetailData.getAmount().trim()));
            }
        // ②外国株、外国投信
        } else if (StringUtils.equals(secType, FOREIGN_STOCK) || StringUtils.equals(secType, FOREIGN_MUTUAL)) {
            
            //未設定
        // ③外国債券
        } else if (StringUtils.equals(secType, FOREIGN_CLAIM)) {
            
            //発行通貨コード
            String ccyCode = unsettleDetailData.getIssueCcyCode();
            // 数量
            String  quantityTmp = "";
            if (unsettleDetailData.getAmount() != null && unsettleDetailData.getAmount().trim().length() != 0) {
                DecimalFormat decimalFormat = new DecimalFormat(QUANTITY_FORMAT);
                quantityTmp = decimalFormat.format(Long.parseLong(unsettleDetailData.getAmount().trim()));
            }
            quantity = ccyCode.trim() + quantityTmp;
        // ④上記以外
        } else {
            
            quantity = HYPHEN;
        }
        
        return quantity;
    }
    
    /**
     * 摘要表示名取得
     * @param unsettleDetailData
     * @return　摘要表示名
     */
    private String getDisplayApplyName(QueryStatementAccountDetail unsettleDetailData, String applyCode, String secType) {
        
        //摘要コード区分
        ApplyType applyType = ApplyType.nameOfValue(applyCode);
        //銘柄名
        String brandName;
        //表示名
        String displayName = null;
        if (null != applyType) {
            displayName = applyType.getJpName();
        }
        //表示摘要
        String displayApplyName = null;
        //API会社コード
        String companyCode = unsettleDetailData.getCompanyCode().trim();
        //API新旧区分
        String newOldId = unsettleDetailData.getNewOldId().trim();
        //API正式漢字銘柄名
        String secName = unsettleDetailData.getSecName().trim();
        //API商品種別1
        String secType1 = unsettleDetailData.getSecType1().trim();
        
        if (null != displayName) {
            
            switch (applyType) {
            
            case BRAND_NAME:
                
                if (StringUtils.equals(secType, DOMESTIC_STOCK)) {
                    
                    brandName = companyCode + newOldId + secName;
                } else if (StringUtils.equals(secType, FOREIGN_CLAIM)) {
                    
                    brandName = secType1 + companyCode + secName;
                } else {
                    
                    brandName = secName;
                }
                
                //銘柄コード,銘柄名が△で表示できない場合、何も表示しない
                if (!(StringUtils.equals(EMPTY_STRING, brandName))) {
                    
                    displayApplyName = brandName;
                }
                break;
            
            case CREDIT_DIVIDEND:
                
                brandName = companyCode + newOldId + secName;
                
                //国内外国区分が1の場合、外国株式配当金を表示
                displayName = StringUtils.equals(DOMESTIC_FOREIGN_KBN_FOREIGN, unsettleDetailData.getDomesticFgnId())
                        ? SUMMARY_FOREIGN_STOCK + applyType.getJpName()
                        : SUMMARY_CREDIT + applyType.getJpName();
                
                displayApplyName = StringUtils.equals(EMPTY_STRING, brandName) ? displayName
                        : displayName + String.format(BRAND_NAME_FORMAT, brandName);
                break;
            
            case RIGHTS_PROCESSING_FEE:
            case STOCK_DIVIDEND:
                
                brandName = companyCode + newOldId + secName;
                displayApplyName = StringUtils.equals(EMPTY_STRING, brandName) ? displayName
                        : displayName + String.format(BRAND_NAME_FORMAT, brandName);
                break;
            
            case REDEMPTION_AMOUNT:
            case PROFIT_AND_EARNINGS:
                
                //銘柄名が△の場合は銘柄名を表示しない
                if (StringUtils.equals(EMPTY_STRING, secName)) {
                    
                    displayApplyName = displayName;
                } else {
                    
                    displayApplyName = displayName + String.format(BRAND_NAME_FORMAT, secName);
                }
                
                break;
            
            default:
                displayApplyName = displayName;
            }
            
        }
        
        return displayApplyName;
    }
    
    /** 
     * 約定日取得
     * @param unsettleDetailData
     * @return 約定日
     */
    private String getTradeDate(QueryStatementAccountDetail unsettleDetailData) {
        
        String tradeDate = null;
        
        if (StringUtils.equals(SPACE, unsettleDetailData.getTradeCode())) {
            
            tradeDate = SPACE;
        } else {
            
            tradeDate = unsettleDetailData.getTradeDate();
        }
        
        return tradeDate;
    }
    
    /**
     * FCT001チェック
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @return API結果
     */
    private boolean callFCT001(String butenCode, String accountNumber) {
        
        InputFct001Dto input = new InputFct001Dto();
        input.setAccountNumber(accountNumber);
        input.setButenCode(butenCode);
        
        OutputFct001Dto output = fct001.doCheck(input);
        if (output != null) {
            if (StringUtils.equals(output.getTargetCustomerRefAuthFlag(), Fct001.TARGET_CUSTOMER_REF_AUTH_FLAG_0)) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * APIリクエスト項目：口座番号設定値作成
     * 
     * @param accountNo
     * @return
     */
    private static String createApiRequestAccountNo(String accountNo) {
        
        return String.format("%7s", accountNo).replace(" ", "0");
    }
    
}
