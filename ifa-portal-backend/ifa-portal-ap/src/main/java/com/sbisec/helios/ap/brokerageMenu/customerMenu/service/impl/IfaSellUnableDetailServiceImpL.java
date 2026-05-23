package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSellUnableDetailA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSellUnableDetailA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSellUnableDetailA001DtoResponseSellUnableDetail;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaSellUnableDetailService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sbisec.pcenter.dto.yanap.NextKeyInfoIn;
import jp.co.sbisec.pcenter.dto.yanap.QuerySaleLimitIn;
import jp.co.sbisec.pcenter.dto.yanap.QuerySaleLimitInData;
import jp.co.sbisec.pcenter.dto.yanap.QuerySaleLimitOutData;

/**
 * 画面ID：SUB0202_010201-03
 * 画面名：売却不可明細
 * アクションID：A001
 * アクション名：初期化

 * @author SCSK渡辺
    2024/04/09 新規作成
 */
@Component(value = "cmpIfaSellUnableDetailService")
public class IfaSellUnableDetailServiceImpL implements IfaSellUnableDetailService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaSellUnableDetailServiceImpL.class);
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private ApiWrapper apiWrapper;
    
    /** 権限チェックエラー値 */
    public static final String AUTH_ERROR_VALUE = "0";
    
    /** 権限チェックエラー  */
    public static final String ERRORS_ACCOUNT_NOT_EXISTS = "errors.butenAccountNotExist";
    
    /** ジュニアISA契約区分：契約  */
    public static final String KEIYAKU = "1";
    
    /**取得口座区分:ジュニアNISA口座  */
    public static final String JRNISA_ACCOUNT = "2";
    
    /**取得口座区分:通常口座およびジュニアNISA口座の第一口座  */
    public static final String NOT_JRNISA_ACCOUNT = " ";
    
    /**半角スペース  */
    public static final String HANKAKU_SPACE = " ";
    
    /**商品区分：投資信託  */
    public static final String TOSHI_SHINTAKU = "投資信託";
    
    /**商品区分：国内株式  */
    public static final String KOKUNAI_KABUSHIKI = "国内株式";
    
    /**ハイフン"-"  */
    public static final String HAYHUN = "-";

    /**ピリオド"."  */
    public static final String PERIOD = ".";
    
    /**'T'(投信(一般口)  */
    public static final String IPPAN = "T";
    
    /**'Y'(投信(累投口)  */
    public static final String RUITO = "Y";
    
    /**'K'(株式)  */
    public static final String KABUSHIKI = "K";
    
    /**'0'（第一口座 特定口座における特定預り） 兼 フラグ  */
    public static final String ZERO = "0";
    
    /**'4'（第一口座 NISA預り）  */
    public static final String FOUR = "4";
    
    /**'8'（第一口座 つみたてNISA預り）  */
    public static final String EIGHT = "8";
    
    /**'H'（第一口座 NISA預り（成長投資枠））  */
    public static final String BIG_H = "H";
    
    /**'I'（第一口座 NISA預り（つみたて投資枠））  */
    public static final String BIG_I = "I";
    
    /**'a'（第二口座 特定口座における特定預り）  */
    public static final String SMALL_A = "a";
    
    /**'b'（第二口座 特定口座における制度対象商品の非特定預り）  */
    public static final String SMALL_B = "b";
    
    /**'c'（第二口座 特定口座における制度対象外商品）  */
    public static final String SMALL_C = "c";
    
    /**'e'（第二口座 非特定口座及び未登録口座における預り）  */
    public static final String SMALL_E = "e";
    
    /**'d'（第二口座 JrNISA預り）  */
    public static final String SMALL_D = "d";
    
    /**'J'（第二口座 JrNISA預り（継続管理勘定））  */
    public static final String SMALL_J = "J";
    
    /**"1" フラグ  */
    public static final String ONE = "1";
    
    /**'11'：保振移管  */
    public static final String EREVEN = "11";
    
    /**'21'：買取請求  */
    public static final String TWENTYONE = "21";
    
    /**'31'：個別株オプション  */
    public static final String THRTYONE = "31";
    
    /**'32'：名義書換出庫（出庫中）  */
    public static final String THRTYTWO = "32";
    
    /**'41'：特定→一般  */
    public static final String FOURTYONE = "41";
    
    /**'42'：一般→特定  */
    public static final String FOURTYTWO = "42";
    
    /**'43'：特定→特定  */
    public static final String FOURTYTHREE = "43";
    
    /**'51'：株券出庫  */
    public static final String FIFTYONE = "51";
    
    /**'61'：TOB予約  */
    public static final String SIXTYONE = "61";
    
    /**'91'：その他（WEB表示）  */
    public static final String NINETYONE = "91";
    
    /**'92'：その他（WEB非表示）  */
    public static final String NINETYTWO = "92";
    
    /**未送信データ区分='1'  */
    public static final String DATA_ONE = "1";
    
    /**未送信データ区分=' '(半角スペース)  */
    public static final String DATA_HANKAKU = " ";
    
    /**'11'：保振移管  */
    public static final String HOSHINIKAN = "保振移管";
    
    /**'21'：買取請求  */
    public static final String KAITORISEIKYU = "買取請求";
    
    /**'31'：個別株オプション  */
    public static final String KOBETSUKABUOP = "個別株オプション";
    
    /**'32'：名義書換出庫（出庫中）  */
    public static final String MEIGISYO = "名義書換出庫（出庫中）";
    
    /**'41'：特定→一般  */
    public static final String TOKUTEI_IPPAN = "特定→一般";
    
    /**'42'：一般→特定  */
    public static final String IPPAN_TOKUTEI = "一般→特定";
    
    /**'43'：特定→特定  */
    public static final String TOKUTEI_TOKUTEI = "特定→特定";
    
    /**'51'：株券出庫  */
    public static final String KABUKEN = "株券出庫";
    
    /**'61'：TOB予約  */
    public static final String TOBYOYAKU = "TOB予約";
    
    /**'91'：その他（WEB表示）  */
    public static final String WEB_OPEN = "その他（WEB表示）";
    
    /**'92'：その他（WEB非表示）  */
    public static final String WEB_CLOSE = "その他（WEB非表示）";
    
    /** 桁数フォーマットによる"7桁" */
    private static final String SEVEN = "%7s";
    
    /** 桁数フォーマットによる"前0埋め" */
    private static final String ZEROUME = "0";
    
    /** ブランク設定 */
    private static final String BLANK_SPACE = " ";
    
    /**'0'（第一口座 特定口座における特定預り） 兼 フラグ  */
    public static final String SOUGO_TOKUTEI = "総合口座 特定預り";
    
    /**'4'（第一口座 NISA預り）  */
    public static final String SOUGO_KYUNISA = "総合口座 旧NISA預り";
    
    /**'8'（第一口座 つみたてNISA預り）  */
    public static final String SOUGO_KYUTSUMITATE = "総合口座 旧つみたてNISA預り";
    
    /**'H'（第一口座 NISA預り（成長投資枠））  */
    public static final String SOUGO_SEITYO = "総合口座 NISA預り（成長投資枠）";
    
    /**'I'（第一口座 NISA預り（つみたて投資枠））  */
    public static final String SOUGO_TSUMITATE = "総合口座 NISA預り（つみたて投資枠）";
    
    /**'a'（第二口座 特定口座における特定預り）  */
    public static final String JUNIOR_TOKUTEI = "ジュニアNISA口座 特定預り";
    
    /**'b'（第二口座 特定口座における制度対象商品の非特定預り）  */
    /**'c'（第二口座 特定口座における制度対象外商品）  */
    /**'e'（第二口座 非特定口座及び未登録口座における預り）  */
    public static final String JUNIOR_IPPAN = "ジュニアNISA口座 一般預り";
    
    /**'d'（第二口座 JrNISA預り）  */
    public static final String JUNIOR_NISA = "ジュニアNISA口座 NISA預り";
    
    /**'J'（第二口座 JrNISA預り（継続管理勘定））  */
    public static final String JUNIOR_KEIZOKU = "ジュニアNISA口座 NISA預り（継続管理勘定）";
    
    /**上記以外  */
    public static final String SOUGO_IPPAN = "総合口座 一般預り";
    
    /**'0'（第一口座 特定口座における特定預り） '≠'1'(契約)  */
    public static final String TOKUTEIAZU = "特定預り";
    
    /**'4'（第一口座 NISA預り） '≠'1'(契約) */
    public static final String KYUNISA = "旧NISA預り";
    
    /**'8'（第一口座 つみたてNISA預り） '≠'1'(契約) */
    public static final String KYUTSUMITATE = "旧つみたてNISA預り";
    
    /**'H'（第一口座 NISA預り（成長投資枠）） '≠'1'(契約) */
    public static final String NISA_SEITYO = "NISA預り（成長投資枠）";
    
    /**'I'（第一口座 NISA預り（つみたて投資枠）） '≠'1'(契約) */
    public static final String NISA_TSUMITATE = "NISA預り（つみたて投資枠）";
    
    /**上記以外 '≠'1'(契約) */
    public static final String IPPANAZU = "一般預り";
    
    /**商品区分='T'(投信(一般口) */
    public static final String KOSU = "(口数)";
    
    /**商品区分='Y'(投信(累投口) */
    public static final String KINGAKU = "(金額)";
    
    /**売却不可明細リスト.代用適格区分 代用適格フラグ='0' */
    public static final String HUTEKIKAKU = "不適格";
    
    /**売却不可明細リスト.代用適格区分 代用適格フラグ='1' */
    public static final String TEKIKAKU = "適格";
    
    /**表示しない */
    public static final String NOT_HYOZI = null;
    
    /**空文字 */
    public static final String KARAMOZI = "";
    
    /**データがない場合 */
    public static final String NOT_DATA = null;
    
    /**
     * 
     * Dto リクエスト：IfaSellUnableDetailA001DtoRequest
     * Dto レスポンス：IfaSellUnableDetailA001DtoResponse
     * model リクエスト：IfaMarginNewOrderConfirmSql001RequestModel
     * model レスポンス：IfaMarginNewOrderConfirmSql001ResponseModel
     */
    public DataList<IfaSellUnableDetailA001DtoResponse> initializeA001(IfaSellUnableDetailA001DtoRequest dtoReq)
            throws Exception {
        
        DataList<IfaSellUnableDetailA001DtoResponse> dtoRes = new DataList<IfaSellUnableDetailA001DtoResponse>();
        List<IfaSellUnableDetailA001DtoResponse> resDto = new ArrayList<IfaSellUnableDetailA001DtoResponse>();
        List<IfaSellUnableDetailA001DtoResponseSellUnableDetail> sellUnableDetailList = new ArrayList<>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSellUnableDetailServiceImplL.initializeA001");
        }
        
        // 顧客情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // Fct001
        InputFct001Dto inptFct001Dto = new InputFct001Dto();
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        inptFct001Dto.setButenCode(butenCode);
        inptFct001Dto.setAccountNumber(accountNumber);
        OutputFct001Dto outFct001Dto = fct001.doCheck(inptFct001Dto);
        
        // Fct001　利用者の口座に対する権限チェックを行う
        // ①対象顧客参照権限有無＝権限なしの場合：権限なしエラーを返す
        if (outFct001Dto == null || StringUtils.equals(outFct001Dto.getTargetCustomerRefAuthFlag(),
                IfaSellUnableDetailServiceImpL.AUTH_ERROR_VALUE)) {
            // 業務エラーメッセージの取得
            dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL,
                    IfaSellUnableDetailServiceImpL.ERRORS_ACCOUNT_NOT_EXISTS,
                    IfaCommonUtil.getMessage(IfaSellUnableDetailServiceImpL.ERRORS_ACCOUNT_NOT_EXISTS,
                            new String[] { cc.getButenCode(), cc.getAccountNumber() }));
            return dtoRes;
        }
        
        // ②.APIパラメータ.取得口座区分の編集を行う。
        String accountGetKbn = KARAMOZI;
        if (cc.getJrIsaContractType().equals(KEIYAKU)) {
            accountGetKbn = JRNISA_ACCOUNT;
        } else {
            accountGetKbn = NOT_JRNISA_ACCOUNT;
        }
        
        // ③.売却不可明細情報を取得する。
        QuerySaleLimitInData inData = new QuerySaleLimitInData();
        inData.setButenCd(butenCode);
        String accountNo = (String.format(SEVEN, accountNumber).replace(BLANK_SPACE, ZEROUME));
        inData.setKozaNo(accountNo);
        NextKeyInfoIn nextKeyInfoIn = new NextKeyInfoIn();
        inData.setNextKeyInfoIn(nextKeyInfoIn);
        inData.getNextKeyInfoIn().setButenCdN(butenCode);
        inData.getNextKeyInfoIn().setKozaNoN(HANKAKU_SPACE);
        inData.getNextKeyInfoIn().setBrandCdN(HANKAKU_SPACE);
        inData.getNextKeyInfoIn().setKaisuN(HANKAKU_SPACE);
        inData.getNextKeyInfoIn().setGou1N(HANKAKU_SPACE);
        inData.getNextKeyInfoIn().setGou2N(HANKAKU_SPACE);
        inData.getNextKeyInfoIn().setHitokuteiKbnN(HANKAKU_SPACE);
        inData.getNextKeyInfoIn().setQualifiedClltrlN(HANKAKU_SPACE);
        inData.getNextKeyInfoIn().setSeqNoN(HANKAKU_SPACE);
        inData.setAccountGetKbn(HANKAKU_SPACE);
        
        QuerySaleLimitIn input = new QuerySaleLimitIn();
        
        input.setIndata(inData);
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // API001呼び出し
        QuerySaleLimitOutData api001Output = apiWrapper.querySaleLimit(input);
        apiErrorUtil.checkApiResponse(api001Output.getShubetu(), api001Output.getCode(), api001Output.getMessage());
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        
        // 3.API001.レスポンス.明細部が0件、かつ API001.未送信データ区分=' '(半角スペース)（検索結果をすべて取得した）の場合、
        // 売却不可明細リストに何も設定しないでレスポンスを返す。
        if ((api001Output.getQuerySaleLimitDetail() == null || api001Output.getQuerySaleLimitDetail().isEmpty()) && api001Output.getNextUmuFlg().equals(HANKAKU_SPACE)) {
            IfaSellUnableDetailA001DtoResponse resMain = new IfaSellUnableDetailA001DtoResponse();
            resMain.setSellUnableDetailList(sellUnableDetailList);
            resDto.add(resMain);
            dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), "");
            return dtoRes;
        }
        QuerySaleLimitOutData api001OutputSub = api001Output;
        boolean api001OutputNullFlag = false;
        // 明細が0件、かつ API001.未送信データ区分≠' '
        if (api001Output.getQuerySaleLimitDetail() == null || api001Output.getQuerySaleLimitDetail().isEmpty()) {
            api001OutputNullFlag = true;
            // api001Output.getQuerySaleLimitDetail()に空のオブジェクトを挿入
            api001Output.setQuerySaleLimitDetail(new ArrayList<>());
            jp.co.sbisec.pcenter.dto.yanap.QuerySaleLimitDetail detail = new jp.co.sbisec.pcenter.dto.yanap.QuerySaleLimitDetail();
            api001Output.getQuerySaleLimitDetail().add(detail);
        }
        
        // 明細部が１件以上の場合、繰り返しレスポンス部に値を追加する処理
        for (int i = 0; i < api001Output.getQuerySaleLimitDetail().size(); i++) {
            IfaSellUnableDetailA001DtoResponseSellUnableDetail sellDatail = new IfaSellUnableDetailA001DtoResponseSellUnableDetail();
            // 4.
            while (api001OutputSub.getNextUmuFlg().equals(DATA_ONE)) {
                // 5.API001.next_key_info（出力）の項目をAPI001.next_key_info_in（入力）の同項目に設定し、売却不可明細情報を取得する
                inData.setNextKeyInfoIn(nextKeyInfoIn);
                inData.getNextKeyInfoIn().setButenCdN(butenCode);
                inData.getNextKeyInfoIn().setKozaNoN(api001OutputSub.getNextKeyInfoOut().getKozaNoN());
                inData.getNextKeyInfoIn().setBrandCdN(api001OutputSub.getNextKeyInfoOut().getBrandCdN());
                inData.getNextKeyInfoIn().setKaisuN(api001OutputSub.getNextKeyInfoOut().getKaisuN());
                inData.getNextKeyInfoIn().setGou1N(api001OutputSub.getNextKeyInfoOut().getGou1N());
                inData.getNextKeyInfoIn().setGou2N(api001OutputSub.getNextKeyInfoOut().getGou2N());
                inData.getNextKeyInfoIn().setHitokuteiKbnN(api001OutputSub.getNextKeyInfoOut().getHitokuteiKbnN());
                inData.getNextKeyInfoIn().setQualifiedClltrlN(api001OutputSub.getNextKeyInfoOut().getQualifiedClltrlN());
                inData.getNextKeyInfoIn().setSeqNoN(api001OutputSub.getNextKeyInfoOut().getSeqNoN());
                inData.setAccountGetKbn(accountGetKbn);
                
                input.setIndata(inData);
                // 売却不可明細情報を再取得
                api001OutputSub = apiWrapper.querySaleLimit(input);
                if (apiErrorUtil.isError(api001OutputSub.getShubetu(), api001OutputSub.getCode(),
                        api001OutputSub.getMessage())) {
                    return apiErrorUtil.createDataList(new ArrayList<>(), null);
                }

                // APIレスポンスを連結する
                if (api001OutputSub.getQuerySaleLimitDetail() != null && !api001OutputSub.getQuerySaleLimitDetail().isEmpty()) {
                    api001Output.getQuerySaleLimitDetail().addAll(api001OutputSub.getQuerySaleLimitDetail());
                }
            }

            // api001OutputNullFlagがtrueかつi=0の場合は処理を行わない
            if (!(api001OutputNullFlag && i == 0)) {
                // 6.売却不可明細リスト.商品区分の編集を行う。
                if (api001Output.getQuerySaleLimitDetail().get(i).getShohinKbnT() == NOT_DATA) {
                    sellDatail.setSecurityType(NOT_DATA);
                } else if (api001Output.getQuerySaleLimitDetail().get(i).getShohinKbnT().equals(IPPAN)
                        || api001Output.getQuerySaleLimitDetail().get(i).getShohinKbnT().equals(RUITO)) {
                    sellDatail.setSecurityType(TOSHI_SHINTAKU);
                } else if (api001Output.getQuerySaleLimitDetail().get(i).getShohinKbnT().equals(KABUSHIKI)) {
                    sellDatail.setSecurityType(KOKUNAI_KABUSHIKI);
                }
                
                // 7.売却不可明細リスト.コード　銘柄名の編集を行う。
                if (api001Output.getQuerySaleLimitDetail().get(i).getShohinKbnT() == NOT_DATA) {
                    sellDatail.setBrandCodeBrandName(NOT_DATA);
                } else if (api001Output.getQuerySaleLimitDetail().get(i).getShohinKbnT().equals(IPPAN)
                        || api001Output.getQuerySaleLimitDetail().get(i).getShohinKbnT().equals(RUITO)) {
                    sellDatail.setBrandCodeBrandName(api001Output.getQuerySaleLimitDetail().get(i).getKaisuT() + PERIOD
                            + api001Output.getQuerySaleLimitDetail().get(i).getGou1T()
                            + api001Output.getQuerySaleLimitDetail().get(i).getGou2T() + HANKAKU_SPACE
                            + api001Output.getQuerySaleLimitDetail().get(i).getSecNameT());
                } else if (api001Output.getQuerySaleLimitDetail().get(i).getShohinKbnT().equals(KABUSHIKI)) {
                    sellDatail.setBrandCodeBrandName(api001Output.getQuerySaleLimitDetail().get(i).getBrandCdT()
                            + HANKAKU_SPACE + api001Output.getQuerySaleLimitDetail().get(i).getSecNameT());
                }
                
                //8.売却不可明細リスト.預り区分の編集を行う。
                if (cc.getJrIsaContractType().equals(KEIYAKU)) {
                    if (api001Output.getQuerySaleLimitDetail().get(i).getHitokuteiKbnT() == NOT_DATA) {
                        sellDatail.setDepositType(NOT_DATA);
                    } else if (api001Output.getQuerySaleLimitDetail().get(i).getHitokuteiKbnT().equals(ZERO)) {
                        sellDatail.setDepositType(SOUGO_TOKUTEI);
                    } else if (api001Output.getQuerySaleLimitDetail().get(i).getHitokuteiKbnT().equals(FOUR)) {
                        sellDatail.setDepositType(SOUGO_KYUNISA);
                    } else if (api001Output.getQuerySaleLimitDetail().get(i).getHitokuteiKbnT().equals(EIGHT)) {
                        sellDatail.setDepositType(SOUGO_KYUTSUMITATE);
                    } else if (api001Output.getQuerySaleLimitDetail().get(i).getHitokuteiKbnT().equals(BIG_H)) {
                        sellDatail.setDepositType(SOUGO_SEITYO);
                    } else if (api001Output.getQuerySaleLimitDetail().get(i).getHitokuteiKbnT().equals(BIG_I)) {
                        sellDatail.setDepositType(SOUGO_TSUMITATE);
                    } else if (api001Output.getQuerySaleLimitDetail().get(i).getHitokuteiKbnT().equals(SMALL_A)) {
                        sellDatail.setDepositType(JUNIOR_TOKUTEI);
                    } else if (api001Output.getQuerySaleLimitDetail().get(i).getHitokuteiKbnT().equals(SMALL_B)
                            || api001Output.getQuerySaleLimitDetail().get(i).getHitokuteiKbnT().equals(SMALL_C)
                            || api001Output.getQuerySaleLimitDetail().get(i).getHitokuteiKbnT().equals(SMALL_E)) {
                        sellDatail.setDepositType(JUNIOR_IPPAN);
                    } else if (api001Output.getQuerySaleLimitDetail().get(i).getHitokuteiKbnT().equals(SMALL_D)) {
                        sellDatail.setDepositType(JUNIOR_NISA);
                    } else if (api001Output.getQuerySaleLimitDetail().get(i).getHitokuteiKbnT().equals(SMALL_J)) {
                        sellDatail.setDepositType(JUNIOR_KEIZOKU);
                    } else {
                        sellDatail.setDepositType(SOUGO_IPPAN);
                    }
                } else {
                    if (api001Output.getQuerySaleLimitDetail().get(i).getHitokuteiKbnT() == NOT_DATA) {
                        sellDatail.setDepositType(NOT_DATA);
                    } else if (api001Output.getQuerySaleLimitDetail().get(i).getHitokuteiKbnT().equals(ZERO)) {
                        sellDatail.setDepositType(TOKUTEIAZU);
                    } else if (api001Output.getQuerySaleLimitDetail().get(i).getHitokuteiKbnT().equals(FOUR)) {
                        sellDatail.setDepositType(KYUNISA);
                    } else if (api001Output.getQuerySaleLimitDetail().get(i).getHitokuteiKbnT().equals(EIGHT)) {
                        sellDatail.setDepositType(KYUTSUMITATE);
                    } else if (api001Output.getQuerySaleLimitDetail().get(i).getHitokuteiKbnT().equals(BIG_H)) {
                        sellDatail.setDepositType(NISA_SEITYO);
                    } else if (api001Output.getQuerySaleLimitDetail().get(i).getHitokuteiKbnT().equals(BIG_I)) {
                        sellDatail.setDepositType(NISA_TSUMITATE);
                    } else {
                        sellDatail.setDepositType(IPPANAZU);
                    }
                }
                
                // 9.売却不可明細リスト.預り区分(ファンドタイプ)の編集を行う。
                if (api001Output.getQuerySaleLimitDetail().get(i).getShohinKbnT() == NOT_DATA) {
                    sellDatail.setFundDepositType(NOT_DATA);
                } else if (api001Output.getQuerySaleLimitDetail().get(i).getShohinKbnT().equals(IPPAN)) {
                    sellDatail.setFundDepositType(KOSU);
                } else if (api001Output.getQuerySaleLimitDetail().get(i).getShohinKbnT().equals(RUITO)) {
                    sellDatail.setFundDepositType(KINGAKU);
                } else {
                    sellDatail.setFundDepositType(NOT_HYOZI);
                }
                
                // 10.売却不可明細リスト.代用適格区分の編集を行う。
                if (api001Output.getQuerySaleLimitDetail().get(i).getQualifiedClltrlT() == NOT_DATA) {
                    sellDatail.setCollateralEligibleType(NOT_DATA);
                } else if (api001Output.getQuerySaleLimitDetail().get(i).getQualifiedClltrlT().equals(ZERO)) {
                    sellDatail.setCollateralEligibleType(HUTEKIKAKU);
                } else if (api001Output.getQuerySaleLimitDetail().get(i).getQualifiedClltrlT().equals(ONE)) {
                    sellDatail.setCollateralEligibleType(TEKIKAKU);
                }
                
                // 11.売却不可明細リスト.理由の編集を行う。
                if (api001Output.getQuerySaleLimitDetail().get(i).getSellControlReasonT() == NOT_DATA) {
                    sellDatail.setReason(NOT_DATA);
                } else if (api001Output.getQuerySaleLimitDetail().get(i).getSellControlReasonT().equals(EREVEN)) {
                    sellDatail.setReason(HOSHINIKAN);
                } else if (api001Output.getQuerySaleLimitDetail().get(i).getSellControlReasonT().equals(TWENTYONE)) {
                    sellDatail.setReason(KAITORISEIKYU);
                } else if (api001Output.getQuerySaleLimitDetail().get(i).getSellControlReasonT().equals(THRTYONE)) {
                    sellDatail.setReason(KOBETSUKABUOP);
                } else if (api001Output.getQuerySaleLimitDetail().get(i).getSellControlReasonT().equals(THRTYTWO)) {
                    sellDatail.setReason(MEIGISYO);
                } else if (api001Output.getQuerySaleLimitDetail().get(i).getSellControlReasonT().equals(FOURTYONE)) {
                    sellDatail.setReason(TOKUTEI_IPPAN);
                } else if (api001Output.getQuerySaleLimitDetail().get(i).getSellControlReasonT().equals(FOURTYTWO)) {
                    sellDatail.setReason(IPPAN_TOKUTEI);
                } else if (api001Output.getQuerySaleLimitDetail().get(i).getSellControlReasonT().equals(FOURTYTHREE)) {
                    sellDatail.setReason(TOKUTEI_TOKUTEI);
                } else if (api001Output.getQuerySaleLimitDetail().get(i).getSellControlReasonT().equals(FIFTYONE)) {
                    sellDatail.setReason(KABUKEN);
                } else if (api001Output.getQuerySaleLimitDetail().get(i).getSellControlReasonT().equals(SIXTYONE)) {
                    sellDatail.setReason(TOBYOYAKU);
                } else if (api001Output.getQuerySaleLimitDetail().get(i).getSellControlReasonT().equals(NINETYONE)) {
                    sellDatail.setReason(WEB_OPEN);
                } else if (api001Output.getQuerySaleLimitDetail().get(i).getSellControlReasonT().equals(NINETYTWO)) {
                    sellDatail.setReason(WEB_CLOSE);
                }
                
                //残りのレスポンス項目にAPIの値をセット
                sellDatail.setRestrictionCount(api001Output.getQuerySaleLimitDetail().get(i).getSellControlAmountT());
                sellDatail.setLimitedPeriodStart(api001Output.getQuerySaleLimitDetail().get(i).getSellControlFromT());
                sellDatail.setLimitedPeriodFinish(api001Output.getQuerySaleLimitDetail().get(i).getSellControlToT());
                sellDatail.setSubstituteStart(api001Output.getQuerySaleLimitDetail().get(i).getSubControlFromT());
                sellDatail.setSubstituteFinish(api001Output.getQuerySaleLimitDetail().get(i).getSubControlToT());
                sellDatail.setRegisteredDate(api001Output.getQuerySaleLimitDetail().get(i).getUpdateDateT());
                sellUnableDetailList.add(sellDatail);
            }
        }
        IfaSellUnableDetailA001DtoResponse resMain = new IfaSellUnableDetailA001DtoResponse();
        resMain.setSellUnableDetailList(sellUnableDetailList);
        resDto.add(resMain);
        dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), "");
        return dtoRes;
    }

}
