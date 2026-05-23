package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaContactBigClass;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaContactDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql006ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql007ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql008ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql011RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql011ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql012RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql012ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql013RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql013ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql015RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql015ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql016RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql016ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql017RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql017ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql018RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql018ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql019RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql019ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql020RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactSql020ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.JpToushinOrderBaseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.TJpOrderBaseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.TbAdditionalOrderIfaBaseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactCommonResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.HassouSts;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.ReferenceKbn;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.TaiouSts;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaContactService;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.IfaContactInputUtil;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.IfaContactUtil;
import com.sbisec.helios.ap.ccsApi.enums.BigClass;
import com.sbisec.helios.ap.ccsApi.enums.CategoryCode;
import com.sbisec.helios.ap.ccsApi.enums.MidClass;
import com.sbisec.helios.ap.ccsApi.enums.ShousaiKbn;
import com.sbisec.helios.ap.ccsApi.protocol.ShowSubContractHistoryIFAReq;
import com.sbisec.helios.ap.ccsApi.service.CcsApiService;
import com.sbisec.helios.ap.ccsApi.service.dto.ShowSubContractHistoryIFAIn;
import com.sbisec.helios.ap.ccsApi.service.dto.ShowSubContractHistoryIFAOut;
import com.sbisec.helios.ap.ccsApi.util.CcsApiErrUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.service.MCodeService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

/**
 * 画面ID：SUB0202_0106-01
 * 画面名：接触履歴
 * 
 * @author 趙韫慧
 * 2025/04/01 新規作成
 */
@SuppressWarnings("unused")
@Component(value = "cmpIfaContactService")
public class IfaContactServiceImpL implements IfaContactService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaBuyingPowerDomesticServiceImpL.class);

    /** 権限チェックエラー   */
    public static final String ERRORS_ACCOUNT_NOT_EXISTS = "errors.butenAccountNotExist";

    /** 大分類(国内注文) */
    private static final String BIG_CLASS_1 = "1";
    /** 大分類(接触履歴（入力）) */
    private static final String BIG_CLASS_2 = "2";
    /** 大分類(積立買付／定期売却) */
    private static final String BIG_CLASS_3 = "3";
    /** 大分類(書類請求) */
    private static final String BIG_CLASS_4 = "4";
    /** 大分類(入出金) */
    private static final String BIG_CLASS_5 = "5";
    /** 大分類(PWリセット) */
    private static final String BIG_CLASS_6 = "6";
    /** 大分類(貸株) */
    private static final String BIG_CLASS_7 = "7";
    /** 大分類(ネーム変更) */
    private static final String BIG_CLASS_8 = "8";
    /** 大分類(貸株振替) */
    private static final String BIG_CLASS_9 = "9";
    /** 大分類(外株委託注文) */
    private static final String BIG_CLASS_10 = "10";
    /** 大分類(外株店頭注文) */
    private static final String BIG_CLASS_11 = "11";
    /** 大分類(為替取引注文) */
    private static final String BIG_CLASS_12 = "12";

    /** 大分類(画面表示)：国内注文 */
    private static final String BIG_CLS_DOMESTIC_ORDER = "国内注文";
    /** 大分類(画面表示)：接触履歴（入力） */
    private static final String BIG_CLS_IFA_CONTACT_INPUT = "接触履歴（入力）";
    /** 大分類(画面表示)：書類請求 */
    private static final String BIG_CLS_PAPERS = "書類請求";
    /** 大分類(画面表示)：入出金 */
    private static final String BIG_CLS_NYUUSYUKKIN = "入出金";
    /** 大分類(画面表示)：積立買付／定期売却 */
    private static final String BIG_CLS_ACCUMULATE = "積立買付／定期売却";
    /** 大分類(画面表示)：PWリセット */
    private static final String BIG_CLS_PWRESET = "PWリセット";
    /** 大分類(画面表示)：貸株 */
    private static final String BIG_CLS_STOCKLENDING = "貸株";
    /** 大分類(画面表示)：ネーム変更 */
    private static final String BIG_CLS_RENAME = "ネーム変更";
    /** 大分類(画面表示)：貸株振替 */
    private static final String BIG_CLS_STOCKLENDING_TRANSFER = "貸株振替";
    /** 大分類(画面表示)：外株委託注文 */
    private static final String BIG_CLS_FOREIGN_CONSIGNMENT_ORDER = "外株委託注文";
    /** 大分類(画面表示)：外株店頭注文 */
    private static final String BIG_CLS_COUNTER_ORDER = "外株店頭注文";
    /** 大分類(画面表示)：為替取引注文 */
    private static final String BIG_CLS_EXCHANGE_ORDER = "為替取引注文";

    /** 中分類(画面表示)：出金 */
    private static final String MID_CLS_SHUKKIN = "出金";
    /** 中分類(画面表示)：出金取消 */
    private static final String MID_CLS_SHUKKIN_CANCEL = "出金取消";
    /** 中分類(画面表示)：注文受付 */
    private static final String MID_CLS_TYUUMON_UKETSUKE = "注文受付";

    /** 内容(画面表示)：内容は詳細を参照 */
    private static final String CONTENT_DETAIL_REF = "内容は詳細を参照";

    /** ステータス(画面表示)：注訂、取消 */
    private static final String STATUS_CORRECTION_CANCELLATION = "注訂、取消";
    /** ステータス(画面表示)：注文訂正 */
    private static final String STATUS_ORDER_CORRECTION = "注文訂正";
    /** ステータス(画面表示)：注文取消 */
    private static final String STATUS_ORDER_CANCELLATION = "注文取消";

    /** 詳細(画面表示):'0'(接触履歴詳細無し) */
    private static final String DETAILS_0 = "0";
    /** 詳細(画面表示):'1'(接触履歴詳細有り) */
    private static final String DETAILS_1 = "1";

    /** CCS API OUT値の枝番:30000 */
    private static final String CCS_API_OUT_EDABAN = "30000";
    
    /** 明細表示の最大件数 */
    private static final Integer MAX_COUNT = 100;

    @Autowired
    private IfaContactDao dao;
    @Autowired
    MCodeService mcodeService;
    @Autowired
    private IfaContactUtil g_ifaContactUtil;
    @Autowired
    private CcsApiService g_ccsApiService;

    /**
     * アクションID：A001
     * アクション名：初期化
     * リクエスト：IfaContactA001DtoRequest
     * レスポンス：IfaContactA001DtoResponse
     * 
     * @param dtoReq dtoリクエスト
     * @return 接触履歴情報
     * @exception Exception 異常
     */
    public DataList<IfaContactA001ResponseDto> initializeA001(IfaContactA001RequestDto dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaContactServiceImpL.initializeA001");
        }

        // 接触履歴リスト
        List<IfaContactCommonResponseDto> ifaContactInfoList = new ArrayList<IfaContactCommonResponseDto>();
        // 初期表示では全ての大分類の履歴を取得、表示.
        CcsApiErrUtil ccsApiErr = new CcsApiErrUtil();
        ifaContactInfoList = getAllBigClassIfaContactList(ccsApiErr);
        // 編集後の接触履歴リストを取得する.
        List<IfaContactCommonResponseDto> ifaContactResList = getIfaContactResList(ifaContactInfoList);

        List<IfaContactA001ResponseDto> a001ResDtoList = new ArrayList<IfaContactA001ResponseDto>();
        IfaContactA001ResponseDto a001ResDto = new IfaContactA001ResponseDto();
        a001ResDto.setContactInfoList(ifaContactResList);
        a001ResDtoList.add(a001ResDto);
        return g_ifaContactUtil.createDataList(a001ResDtoList, ccsApiErr);
    }

    /**
     * アクションID：A002
     * アクション名：大分類変更
     * Dto リクエスト：IfaContactA002RequestDto
     * レスポンス：IfaContactA002ResponseDto
     *
     * @param dtoReq dtoリクエスト
     * @return 接触履歴情報(大分類別)
     * @exception Exception 異常
     */
    public DataList<IfaContactA002ResponseDto> bigClassChangeA002(IfaContactA002RequestDto dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaContactServiceImpL.bigClassChangeA002");
        }

        // 接触履歴リスト
        List<IfaContactCommonResponseDto> ifaContactInfoList = new ArrayList<IfaContactCommonResponseDto>();

        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        // 部店コード
        String butenCode = cc.getButenCode();
        // 口座番号
        String accountNumber = cc.getAccountNumber();
        // 顧客コード
        String customerId = cc.getCustomerCode();
        // ジュニアISA契約区分
        String jrIsaContractType = cc.getJrIsaContractType();

        // APIエラーかどうかクラス
        CcsApiErrUtil p_ccsApiErr = new CcsApiErrUtil();

        // CCS API用統合リスト
        List<IfaContactCommonResponseDto> p_ccsAllLst = new ArrayList<IfaContactCommonResponseDto>();

        for (IfaContactBigClass bigClass : dtoReq.getBigClassList()) {
            // 大分類毎を取得
            if (StringUtils.equals(bigClass.getIsSelected(), "true")) {
                // 現在大分類が選択されている場合、 選択した大分類ID
                String bigClassId = bigClass.getId();

                switch (bigClassId) {
                case (BIG_CLASS_1):
                    // 国内株式現物注文履歴情報取得して設定する
                    setIfaContactSql002InfoList(customerId, jrIsaContractType, ifaContactInfoList);
                    // 国内株式信用新規(返済)注文履歴情報取得して設定する
                    setIfaContactSql003InfoList(customerId, jrIsaContractType, ifaContactInfoList);
                    // 国内株式現引現渡注文接触履歴情報取得して設定する
                    setIfaContactSql015InfoList(customerId, ifaContactInfoList);
                    // 国内注文接触履歴情報取得して設定する
                    setIfaContactSql008InfoList(customerId, ifaContactInfoList);
                    // 国内投信(一般)注文接触履歴情報取得して設定する
                    setIfaContactSql016InfoList(customerId, jrIsaContractType, ifaContactInfoList);
                    // 国内投信(累投)注文接触履歴情報取得して設定する
                    setIfaContactSql017InfoList(customerId, jrIsaContractType, ifaContactInfoList);
                    // その他注文その他余力拘束取消の当日接触履歴情報取得して設定する
                    setIfaContactSql018InfoList(customerId, jrIsaContractType, ifaContactInfoList);
                    /* ---------- ↓↓↓↓ CCS接触履歴を取得する ↓↓↓↓ ---------- */
                    // APIにエラーが存在する場合、この処理は行われません。
                    if (p_ccsApiErr.getErrLevel() != 0) {
                        break;
                    }
                    // ユーザ共通情報.CCSログイン用ID＝未設定(Null または空文字)のチェック
                    p_ccsApiErr.fromCcsIdWarn();
                    if (p_ccsApiErr.getErrLevel() != 0) {
                        break;
                    }
                    List<ShowSubContractHistoryIFAOut.Contact> p_combinedCcsApiListBc1 = new ArrayList<ShowSubContractHistoryIFAOut.Contact>();
                    // カテゴリコード＝"01"(信用・株式注文履歴)でAPI呼び出し、CCS接触履歴リストを取得する
                    List<ShowSubContractHistoryIFAOut.Contact> p_ccsApiList01 = getCcsApiOutList(customerId, CategoryCode.CC_01.getId(), p_ccsApiErr);
                    // APIエラーの場合、エラーメッセージを表示し、次の処理へ
                    if (p_ccsApiErr.getErrLevel() != 0) {
                        break;
                    } else {
                        p_combinedCcsApiListBc1.addAll(p_ccsApiList01);
                    }
                    // カテゴリコード＝"02"(投信注文履歴)でAPI呼び出し、CCS接触履歴リストを取得する
                    List<ShowSubContractHistoryIFAOut.Contact> p_ccsApiList02 = getCcsApiOutList(customerId, CategoryCode.CC_02.getId(), p_ccsApiErr);
                    // APIエラーの場合、エラーメッセージを表示し、次の処理へ
                    if (p_ccsApiErr.getErrLevel() != 0) {
                        break;
                    } else {
                        p_combinedCcsApiListBc1.addAll(p_ccsApiList02);
                    }
                    // カテゴリコード＝"03"(CW注文履歴)でAPI呼び出し、CCS接触履歴リストを取得する
                    List<ShowSubContractHistoryIFAOut.Contact> p_ccsApiList03 = getCcsApiOutList(customerId, CategoryCode.CC_03.getId(), p_ccsApiErr);
                    // APIエラーの場合、エラーメッセージを表示し、次の処理へ
                    if (p_ccsApiErr.getErrLevel() != 0) {
                        break;
                    } else {
                        p_combinedCcsApiListBc1.addAll(p_ccsApiList03);
                    }
                    // カテゴリコード＝"04"(その他注文履歴)でAPI呼び出し、CCS接触履歴リストを取得する
                    List<ShowSubContractHistoryIFAOut.Contact> p_ccsApiList04 = getCcsApiOutList(customerId, CategoryCode.CC_04.getId(), p_ccsApiErr);
                    // APIエラーの場合、エラーメッセージを表示し、次の処理へ
                    if (p_ccsApiErr.getErrLevel() != 0) {
                        break;
                    } else {
                        p_combinedCcsApiListBc1.addAll(p_ccsApiList04);
                    }
                    // APIのOUTから取得したCCS接触履歴リストのうち、以下の条件に従いレコードを取得する
                    // 「注文」大分類のCCSAPI出力リストをフィルタリングする
                    filterTyumonListForCcs(p_combinedCcsApiListBc1);
                    // 取得した項目を編集し、ActionパラメータCCS接触履歴リストに設定する
                    setIfaContactCcsApiInfoList(BIG_CLASS_1, p_combinedCcsApiListBc1, p_ccsAllLst);
                    /* ---------- ↑↑↑↑ CCS接触履歴を取得する ↑↑↑↑ ---------- */
                    break;
                case (BIG_CLASS_10):
                    // 外株委託注文接触履歴情報取得して設定する
                    setIfaContactSql011InfoList(butenCode, accountNumber, ifaContactInfoList);
                    break;
                case (BIG_CLASS_11):
                    // 外株店頭注文接触履歴情報取得して設定する
                    setIfaContactSql012InfoList(butenCode, accountNumber, ifaContactInfoList);
                    break;
                case (BIG_CLASS_12):
                    // 為替取引注文接触履歴情報取得して設定する
                    setIfaContactSql013InfoList(butenCode, accountNumber, ifaContactInfoList);
                    break;
                case (BIG_CLASS_2):
                    // 問合せ接触履歴情報取得して設定する
                    setIfaContactSql004InfoList(customerId, ifaContactInfoList);
                    break;
                case (BIG_CLASS_4):
                    // 書類請求接触履歴情報取得して設定する
                    setIfaContactSql005InfoList(customerId, ifaContactInfoList);
                    /* ---------- ↓↓↓↓ CCS接触履歴を取得する ↓↓↓↓ ---------- */
                    // APIにエラーが存在する場合、この処理は行われません。
                    if (p_ccsApiErr.getErrLevel() != 0) {
                        break;
                    }
                    // ユーザ共通情報.CCSログイン用ID＝未設定(Null または空文字)のチェック
                    p_ccsApiErr.fromCcsIdWarn();
                    if (p_ccsApiErr.getErrLevel() != 0) {
                        break;
                    }
                    // カテゴリコード＝"08"(書類請求情報)でAPI呼び出し、CCS接触履歴リストを取得する
                    List<ShowSubContractHistoryIFAOut.Contact> p_ccsApiList08 = getCcsApiOutList(customerId, CategoryCode.CC_08.getId(), p_ccsApiErr);
                    // APIエラーの場合、エラーメッセージを表示して終了、正常の場合、次の処理へ
                    if (p_ccsApiErr.getErrLevel() != 0) {
                        break;
                    }
                    // 取得した項目を編集し、ActionパラメータCCS接触履歴リストに設定する
                    setIfaContactCcsApiInfoList(BIG_CLASS_4, p_ccsApiList08, p_ccsAllLst);
                    /* ---------- ↑↑↑↑ CCS接触履歴を取得する ↑↑↑↑ ---------- */
                    break;
                case (BIG_CLASS_5):
                    // 出金取消接触履歴情報取得して設定する
                    setIfaContactSql006InfoList(customerId, ifaContactInfoList);
                    // 出金取消接触履歴情報取得して設定する
                    setIfaContactSql007InfoList(customerId, ifaContactInfoList);
                    /* ---------- ↓↓↓↓ CCS接触履歴を取得する ↓↓↓↓ ---------- */
                    // APIにエラーが存在する場合、この処理は行われません。
                    if (p_ccsApiErr.getErrLevel() != 0) {
                        break;
                    }
                    // ユーザ共通情報.CCSログイン用ID＝未設定(Null または空文字)のチェック
                    p_ccsApiErr.fromCcsIdWarn();
                    if (p_ccsApiErr.getErrLevel() != 0) {
                        break;
                    }
                    // カテゴリコード＝"09"(入出金履歴)でAPI呼び出し、CCS接触履歴リストを取得する
                    List<ShowSubContractHistoryIFAOut.Contact> p_ccsApiList09 = getCcsApiOutList(customerId, CategoryCode.CC_09.getId(), p_ccsApiErr);
                    // APIエラーの場合、エラーメッセージを表示して終了、正常の場合、次の処理へ
                    if (p_ccsApiErr.getErrLevel() != 0) {
                        break;
                    }
                    // APIのOUTから取得したCCS接触履歴リストのうち、以下の条件に従いレコードを取得する
                    // 出金（出金履歴）のCCS接触履歴リストをフィルタリングする
                    filterShukkinListForCcs(customerId, p_ccsApiList09);
                    // 出金取消（出金履歴）のCCS接触履歴リストをフィルタリングする
                    filterShukkinToriKeshiListForCcs(customerId, p_ccsApiList09);
                    // 取得した項目を編集し、ActionパラメータCCS接触履歴リストに設定する
                    setIfaContactCcsApiInfoList(BIG_CLASS_5, p_ccsApiList09, p_ccsAllLst);
                    /* ---------- ↑↑↑↑ CCS接触履歴を取得する ↑↑↑↑ ---------- */
                    break;
                case (BIG_CLASS_3):
                    /* ---------- ↓↓↓↓ CCS接触履歴を取得する ↓↓↓↓ ---------- */
                    // APIにエラーが存在する場合、この処理は行われません。
                    if (p_ccsApiErr.getErrLevel() != 0) {
                        break;
                    }
                    // ユーザ共通情報.CCSログイン用ID＝未設定(Null または空文字)のチェック
                    p_ccsApiErr.fromCcsIdWarn();
                    if (p_ccsApiErr.getErrLevel() != 0) {
                        break;
                    }
                    // 積立買付／定期売却 接触履歴情報取得して設定する
                    List<ShowSubContractHistoryIFAOut.Contact> p_combinedCcsApiListBc3 = new ArrayList<ShowSubContractHistoryIFAOut.Contact>();
                    // カテゴリコード＝"05"(積立買付履歴)でAPI呼び出し、CCS接触履歴リストを取得する
                    List<ShowSubContractHistoryIFAOut.Contact> p_ccsApiList05 = getCcsApiOutList(customerId, CategoryCode.CC_05.getId(), p_ccsApiErr);
                    // APIエラーの場合、エラーメッセージを表示して終了、正常の場合、次の処理へ
                    if (p_ccsApiErr.getErrLevel() != 0) {
                        break;
                    } else {
                        p_combinedCcsApiListBc3.addAll(p_ccsApiList05);
                    }
                    // カテゴリコード＝"11"(積立買付履歴)でAPI呼び出し、CCS接触履歴リストを取得する
                    List<ShowSubContractHistoryIFAOut.Contact> p_ccsApiList11 = getCcsApiOutList(customerId, CategoryCode.CC_11.getId(), p_ccsApiErr);
                    // APIエラーの場合、エラーメッセージを表示して終了、正常の場合、次の処理へ
                    if (p_ccsApiErr.getErrLevel() != 0) {
                        break;
                    } else {
                        p_combinedCcsApiListBc3.addAll(p_ccsApiList11);
                    }
                    // 取得した項目を編集し、ActionパラメータCCS接触履歴リストに設定する
                    setIfaContactCcsApiInfoList(BIG_CLASS_3, p_combinedCcsApiListBc3, p_ccsAllLst);
                    break;
                    /* ---------- ↑↑↑↑ CCS接触履歴を取得する ↑↑↑↑ ---------- */
                case (BIG_CLASS_6):
                    /* ---------- ↓↓↓↓ CCS接触履歴を取得する ↓↓↓↓ ---------- */
                    // APIにエラーが存在する場合、この処理は行われません。
                    if (p_ccsApiErr.getErrLevel() != 0) {
                        break;
                    }
                    // ユーザ共通情報.CCSログイン用ID＝未設定(Null または空文字)のチェック
                    p_ccsApiErr.fromCcsIdWarn();
                    if (p_ccsApiErr.getErrLevel() != 0) {
                        break;
                    }
                    // PWリセット 接触履歴情報取得して設定する
                    // カテゴリコード＝"07"(PWリセット履歴)でAPI呼び出し、CCS接触履歴リストを取得する
                    List<ShowSubContractHistoryIFAOut.Contact> p_ccsApiList07 = getCcsApiOutList(customerId, CategoryCode.CC_07.getId(), p_ccsApiErr);
                    // APIエラーの場合、エラーメッセージを表示して終了、正常の場合、次の処理へ
                    if (p_ccsApiErr.getErrLevel() != 0) {
                        break;
                    }
                    // 取得した項目を編集し、ActionパラメータCCS接触履歴リストに設定する
                    setIfaContactCcsApiInfoList(BIG_CLASS_6, p_ccsApiList07, p_ccsAllLst);
                    break;
                    /* ---------- ↑↑↑↑ CCS接触履歴を取得する ↑↑↑↑ ---------- */
                case (BIG_CLASS_7):
                    /* ---------- ↓↓↓↓ CCS接触履歴を取得する ↓↓↓↓ ---------- */
                    // APIにエラーが存在する場合、この処理は行われません。
                    if (p_ccsApiErr.getErrLevel() != 0) {
                        break;
                    }
                    // ユーザ共通情報.CCSログイン用ID＝未設定(Null または空文字)のチェック
                    p_ccsApiErr.fromCcsIdWarn();
                    if (p_ccsApiErr.getErrLevel() != 0) {
                        break;
                    }
                    // 貸株 接触履歴情報取得して設定する
                    // カテゴリコード＝"10"(貸株履歴)でAPI呼び出し、CCS接触履歴リストを取得する
                    List<ShowSubContractHistoryIFAOut.Contact> p_ccsApiList10 = getCcsApiOutList(customerId, CategoryCode.CC_10.getId(), p_ccsApiErr);
                    // APIエラーの場合、エラーメッセージを表示して終了、正常の場合、次の処理へ
                    if (p_ccsApiErr.getErrLevel() != 0) {
                        break;
                    }
                    // 取得した項目を編集し、ActionパラメータCCS接触履歴リストに設定する
                    setIfaContactCcsApiInfoList(BIG_CLASS_7, p_ccsApiList10, p_ccsAllLst);
                    break;
                    /* ---------- ↑↑↑↑ CCS接触履歴を取得する ↑↑↑↑ ---------- */
                case (BIG_CLASS_8):
                    /* ---------- ↓↓↓↓ CCS接触履歴を取得する ↓↓↓↓ ---------- */
                    // APIにエラーが存在する場合、この処理は行われません。
                    if (p_ccsApiErr.getErrLevel() != 0) {
                        break;
                    }
                    // ユーザ共通情報.CCSログイン用ID＝未設定(Null または空文字)のチェック
                    p_ccsApiErr.fromCcsIdWarn();
                    if (p_ccsApiErr.getErrLevel() != 0) {
                        break;
                    }
                    // ネーム変更 接触履歴情報取得して設定する
                    // カテゴリコード＝"12"(ユーザーネーム変更履歴)でAPI呼び出し、CCS接触履歴リストを取得する
                    List<ShowSubContractHistoryIFAOut.Contact> p_ccsApiList12 = getCcsApiOutList(customerId, CategoryCode.CC_12.getId(), p_ccsApiErr);
                    // APIエラーの場合、エラーメッセージを表示して終了、正常の場合、次の処理へ
                    if (p_ccsApiErr.getErrLevel() != 0) {
                        break;
                    }
                    // 取得した項目を編集し、ActionパラメータCCS接触履歴リストに設定する
                    setIfaContactCcsApiInfoList(BIG_CLASS_8, p_ccsApiList12, p_ccsAllLst);
                    break;
                    /* ---------- ↑↑↑↑ CCS接触履歴を取得する ↑↑↑↑ ---------- */
                case (BIG_CLASS_9):
                    /* ---------- ↓↓↓↓ CCS接触履歴を取得する ↓↓↓↓ ---------- */
                    // APIにエラーが存在する場合、この処理は行われません。
                    if (p_ccsApiErr.getErrLevel() != 0) {
                        break;
                    }
                    // ユーザ共通情報.CCSログイン用ID＝未設定(Null または空文字)のチェック
                    p_ccsApiErr.fromCcsIdWarn();
                    if (p_ccsApiErr.getErrLevel() != 0) {
                        break;
                    }
                    // 貸株振替 接触履歴情報取得して設定する
                    // カテゴリコード＝"13"(貸株振替履歴)でAPI呼び出し、CCS接触履歴リストを取得する
                    List<ShowSubContractHistoryIFAOut.Contact> p_ccsApiList13 = getCcsApiOutList(customerId, CategoryCode.CC_13.getId(), p_ccsApiErr);
                    // APIエラーの場合、エラーメッセージを表示して終了、正常の場合、次の処理へ
                    if (p_ccsApiErr.getErrLevel() != 0) {
                        break;
                    }
                    // 取得した項目を編集し、ActionパラメータCCS接触履歴リストに設定する
                    setIfaContactCcsApiInfoList(BIG_CLASS_9, p_ccsApiList13, p_ccsAllLst);
                    break;
                    /* ---------- ↑↑↑↑ CCS接触履歴を取得する ↑↑↑↑ ---------- */
                }
            }
        }
        // 最後、CCSAPIがエラーがなければ、CCSAPIの統合リストを、統合リストの後に追加します。
        if (p_ccsApiErr.getErrLevel() == 0) {
            ifaContactInfoList.addAll(p_ccsAllLst);
        }
        // 編集後の接触履歴リストを取得する.
        List<IfaContactCommonResponseDto> ifaContactResList = getIfaContactResList(ifaContactInfoList);

        List<IfaContactA002ResponseDto> a002ResDtoList = new ArrayList<IfaContactA002ResponseDto>();
        IfaContactA002ResponseDto a002ResDto = new IfaContactA002ResponseDto();
        a002ResDto.setContactInfoList(ifaContactResList);
        a002ResDtoList.add(a002ResDto);
        return g_ifaContactUtil.createDataList(a002ResDtoList, p_ccsApiErr);
    }

    /**
     * 国内株式現物注文履歴情報取得して設定する.
     * 
     * @param customerId           顧客コード
     * @param jrIsaContractType    ジュニアISA契約区分
     * @param ifaContactList       接触履歴リスト
     * @return
     */
    private void setIfaContactSql002InfoList(String customerId, String jrIsaContractType,
            List<IfaContactCommonResponseDto> ifaContactList) throws Exception {

        // 国内株式現物注文履歴情報取得SQLのリクエストモデル
        IfaContactSql002RequestModel selSql002Req = new IfaContactSql002RequestModel();
        // 顧客コード
        selSql002Req.setCustomerId(customerId);

        // 国内株式現物注文履歴情報を取得する。
        DataList<IfaContactSql002ResponseModel> selSql002Res = dao.selectIfaContactSql002(selSql002Req);

        if (selSql002Res != null && CollectionUtils.isNotEmpty(selSql002Res.getDataList())) {
            // 同一のIFA注文番号のレコード内(作成日時の降順でソート済)で以下の仕様で明細の表示/非表示を判断する

            // 接触履歴モデル
            IfaContactCommonResponseDto a002ContactListDto = null;

            // 同一のIFA注文番号の1番目データの番号
            int num = 0;
            // 同一のIFA注文番号のデータ番号
            int recordNum = 1;
            // 「2番目以降に訂正のレコードがなし」フラグ
            boolean noneOrderStatus1Flg = true;

            List<IfaContactSql002ResponseModel> selSql002ResList = selSql002Res.getDataList();

            for (int i = 0; i < selSql002ResList.size(); i++) {

                // 接触履歴モデル
                a002ContactListDto = new IfaContactCommonResponseDto();

                if (!selSql002ResList.get(num).getIfaOrderNo().equals(selSql002ResList.get(i).getIfaOrderNo())) {
                    // 次のデータセットの場合、新しい組データセットの「1番目データの番号」を設定
                    num = i;

                    // 「同一のIFA注文番号のデータ番号」の初期化
                    recordNum = 1;

                    // 同一のIFA注文番号の「2番目以降に訂正のレコードがなしフラグ」の初期化
                    noneOrderStatus1Flg = true;
                }

                // 現在のデータ
                IfaContactSql002ResponseModel nowData = selSql002ResList.get(i);
                // 同一のIFA注文番号の1番目データレコード
                IfaContactSql002ResponseModel firstData = selSql002ResList.get(num);

                // 戻り結果の設定用のモデル
                IfaContactSql002ResponseModel finalData = null;

                if (firstData.getIfaOrderNo().equals(nowData.getIfaOrderNo())) {
                    // 同一のIFA注文番号のレコードの場合、

                    if (IfaContactUtil.OrderStatus.TJO_ORDER_STATUS_2.equals(firstData.getOrderStatus())) {
                        // 該当レコードの1番目が注文状況＝'2'(取消)の場合、

                        // 注文履歴情報Max Index
                        BigDecimal listMaxIndex = new BigDecimal(selSql002ResList.size() - 1);
                        // 同一のIFA注文番号の2番目データレコードIndex
                        BigDecimal secondDataIndex = new BigDecimal(num + 1);

                        if (secondDataIndex.compareTo(listMaxIndex) <= 0) {
                            // 同一のIFA注文番号の2番目データレコードがある場合、

                            // 同一のIFA注文番号の2番目データレコード
                            IfaContactSql002ResponseModel secondData = selSql002ResList.get(num + 1);
                            if (IfaContactUtil.OrderStatus.TJO_ORDER_STATUS_1.equals(secondData.getOrderStatus())) {
                                // 2番目の注文状況＝'1'(訂正)の場合、

                                // 「2番目以降に訂正のレコードがなし」フラグを設定
                                noneOrderStatus1Flg = false;

                                if (IfaContactUtil.OrderStatus.TJO_ORDER_STATUS_1.equals(nowData.getOrderStatus())) {
                                    if (recordNum == 2) {
                                        // 2番目のA.注文状況＝'1'(訂正)の明細は表示 ・・・①
                                        finalData = nowData;
                                        a002ContactListDto.setStatus(STATUS_CORRECTION_CANCELLATION);
                                    } else {
                                        // 3番目以降のA.注文状況＝'1'(訂正)の明細は表示 ・・・②
                                        finalData = nowData;
                                        a002ContactListDto.setStatus(STATUS_ORDER_CORRECTION);
                                    }
                                }
                            } else {
                                // 2番目の注文状況≠'1'(訂正)の場合、
                                if (noneOrderStatus1Flg && !IfaContactUtil.OrderStatus.TJO_ORDER_STATUS_0
                                        .equals(nowData.getOrderStatus())) {
                                    // 2番目以降のレコードに注文状況＝'1'(訂正)が無い場合 && 現在のデータの注文状況≠'0'、1番目の明細は表示 ・・・③
                                    finalData = firstData;
                                    // ステータス
                                    a002ContactListDto.setStatus(STATUS_ORDER_CANCELLATION);
                                }
                            }
                        } else {
                            // 同一のIFA注文番号の2番目データレコードがない場合、

                            // 2番目以降のレコードに注文状況＝'1'(訂正)が無い場合、1番目の明細は表示 ・・・③
                            finalData = firstData;
                            // ステータス
                            a002ContactListDto.setStatus(STATUS_ORDER_CANCELLATION);
                        }
                    } else if (IfaContactUtil.OrderStatus.TJO_ORDER_STATUS_1.equals(firstData.getOrderStatus())) {
                        // 該当レコードの1番目が注文状況＝'1'(訂正)の場合、1番目以降の注文状況＝'1'(訂正)の明細は表示
                        if (IfaContactUtil.OrderStatus.TJO_ORDER_STATUS_1.equals(nowData.getOrderStatus())) {
                            finalData = nowData;
                            // ステータス
                            a002ContactListDto.setStatus(STATUS_ORDER_CORRECTION);
                        }
                    }

                    recordNum++;
                }

                if (finalData != null) {
                    // 大分類
                    a002ContactListDto.setBigClass(BIG_CLS_DOMESTIC_ORDER);
                    // 中分類
                    a002ContactListDto.setMidClass(g_ifaContactUtil.getTradeKbnNm(finalData.getTradeKbn()));
                    // 記録日時
                    a002ContactListDto.setRecordDate(finalData.getCreateTime());
                    // 内容
                    TJpOrderBaseModel tJpOrderBaseModel = new TJpOrderBaseModel();
                    BeanUtils.copyProperties(tJpOrderBaseModel, finalData);
                    a002ContactListDto.setContents(g_ifaContactUtil.getContentsFor002(tJpOrderBaseModel,
                            jrIsaContractType, IfaContactUtil.IFA_CONTACT));
                    // 担当者名
                    a002ContactListDto.setChargeName(finalData.getUserNm());
                    // 詳細("0"(履歴詳細無し))
                    a002ContactListDto.setDetails(DETAILS_0);

                    // 接触履歴情報リストに接触履歴情報を追加する。
                    ifaContactList.add(a002ContactListDto);
                }
            }
        }
    }

    /**
     * 国内株式信用新規(返済)注文履歴情報取得して設定する.
     * 
     * @param customerId           顧客コード
     * @param jrIsaContractType    ジュニアISA契約区分
     * @param ifaContactList       接触履歴リスト
     * @return
     */
    private void setIfaContactSql003InfoList(String customerId, String jrIsaContractType,
            List<IfaContactCommonResponseDto> ifaContactList) throws Exception {

        // 国内株式信用新規(返済)注文履歴情報取得SQLのリクエストモデル
        IfaContactSql003RequestModel selSql003Req = new IfaContactSql003RequestModel();
        // 顧客コード
        selSql003Req.setCustomerId(customerId);

        // 国内株式信用新規(返済)注文履歴情報を取得する。
        DataList<IfaContactSql003ResponseModel> selSql003Res = dao.selectIfaContactSql003(selSql003Req);

        if (selSql003Res != null && CollectionUtils.isNotEmpty(selSql003Res.getDataList())) {
            // 同一のIFA注文番号のレコード内(作成日時の降順でソート済)で以下の仕様で明細の表示/非表示を判断する

            // 接触履歴モデル
            IfaContactCommonResponseDto a003ContactListDto = null;

            // 同一のIFA注文番号の1番目データの番号
            int num = 0;
            // 同一のIFA注文番号のデータ番号
            int recordNum = 1;
            // 「2番目以降に訂正のレコードがなし」フラグ
            boolean noneOrderStatus1Flg = true;

            List<IfaContactSql003ResponseModel> selSql003ResList = selSql003Res.getDataList();

            for (int i = 0; i < selSql003ResList.size(); i++) {

                // 接触履歴モデル
                a003ContactListDto = new IfaContactCommonResponseDto();

                if (!selSql003ResList.get(i).getIfaOrderNo().equals(selSql003ResList.get(num).getIfaOrderNo())) {
                    // 次のデータセットの場合、新しい組データセットの「1番目データの番号」を設定
                    num = i;

                    // 「同一のIFA注文番号のデータ番号」の初期化
                    recordNum = 1;

                    // 同一のIFA注文番号の「2番目以降に訂正のレコードがなしフラグ」の初期化
                    noneOrderStatus1Flg = true;
                }

                // 現在のデータ
                IfaContactSql003ResponseModel nowData = selSql003ResList.get(i);
                // 同一のIFA注文番号の1番目データレコード
                IfaContactSql003ResponseModel firstData = selSql003ResList.get(num);

                // 戻り結果の設定用のモデル
                IfaContactSql003ResponseModel finalData = null;

                if (firstData.getIfaOrderNo().equals(nowData.getIfaOrderNo())) {
                    // 同一のIFA注文番号のレコードの場合、

                    if (IfaContactUtil.OrderStatus.TJO_ORDER_STATUS_2.equals(firstData.getOrderStatus())) {
                        // 該当レコードの1番目が注文状況＝'2'(取消)の場合、

                        // 注文履歴情報Max Index
                        BigDecimal listMaxIndex = new BigDecimal(selSql003ResList.size() - 1);
                        // 同一のIFA注文番号の2番目データレコードIndex
                        BigDecimal secondDataIndex = new BigDecimal(num + 1);

                        if (secondDataIndex.compareTo(listMaxIndex) <= 0) {
                            // 同一のIFA注文番号の2番目データレコードがある場合、

                            // 同一のIFA注文番号の2番目データレコード
                            IfaContactSql003ResponseModel secondData = selSql003ResList.get(num + 1);
                            if (IfaContactUtil.OrderStatus.TJO_ORDER_STATUS_1.equals(secondData.getOrderStatus())) {
                                // 2番目の注文状況＝'1'(訂正)の場合、

                                // 「2番目以降に訂正のレコードがなし」フラグを設定
                                noneOrderStatus1Flg = false;

                                if (IfaContactUtil.OrderStatus.TJO_ORDER_STATUS_1.equals(nowData.getOrderStatus())) {
                                    if (recordNum == 2) {
                                        // 2番目のA.注文状況＝'1'(訂正)の明細は表示 ・・・①
                                        finalData = nowData;
                                        a003ContactListDto.setStatus(STATUS_CORRECTION_CANCELLATION);
                                    } else {
                                        // 3番目以降のA.注文状況＝'1'(訂正)の明細は表示 ・・・②
                                        finalData = nowData;
                                        a003ContactListDto.setStatus(STATUS_ORDER_CORRECTION);
                                    }
                                }
                            } else {
                                // 2番目の注文状況≠'1'(訂正)の場合、
                                if (noneOrderStatus1Flg && !IfaContactUtil.OrderStatus.TJO_ORDER_STATUS_0
                                        .equals(nowData.getOrderStatus())) {
                                    // 2番目以降のレコードに注文状況＝'1'(訂正)が無い && 現在のデータの注文状況≠'0'場合、1番目の明細は表示 ・・・③
                                    finalData = firstData;
                                    // ステータス
                                    a003ContactListDto.setStatus(STATUS_ORDER_CANCELLATION);
                                }
                            }
                        } else {
                            // 同一のIFA注文番号の2番目データレコードがない場合、

                            // 2番目以降のレコードに注文状況＝'1'(訂正)が無い場合、1番目の明細は表示 ・・・③
                            finalData = firstData;
                            // ステータス
                            a003ContactListDto.setStatus(STATUS_ORDER_CANCELLATION);

                        }
                    } else if (IfaContactUtil.OrderStatus.TJO_ORDER_STATUS_1.equals(firstData.getOrderStatus())) {
                        // 該当レコードの1番目が注文状況＝'1'(訂正)の場合、1番目以降の注文状況＝'1'(訂正)の明細は表示
                        if (IfaContactUtil.OrderStatus.TJO_ORDER_STATUS_1.equals(nowData.getOrderStatus())) {
                            finalData = nowData;
                            // ステータス
                            a003ContactListDto.setStatus(STATUS_ORDER_CORRECTION);
                        }
                    }

                    recordNum++;
                }

                if (finalData != null) {
                    // 大分類
                    a003ContactListDto.setBigClass(BIG_CLS_DOMESTIC_ORDER);
                    // 中分類
                    a003ContactListDto.setMidClass(g_ifaContactUtil.getMidClass(finalData.getTradeKbn(),
                            finalData.getPaymentLimit(), finalData.getDailyCreditKbn(), finalData.getPaymentLimitDate(),
                            finalData.getDateKbn()));
                    // 記録日時
                    a003ContactListDto.setRecordDate(finalData.getCreateTime());
                    // 内容
                    TJpOrderBaseModel tJpOrderBaseModel = new TJpOrderBaseModel();
                    BeanUtils.copyProperties(tJpOrderBaseModel, finalData);
                    a003ContactListDto.setContents(
                            g_ifaContactUtil.getContentsFor003(tJpOrderBaseModel, IfaContactUtil.IFA_CONTACT));
                    // 詳細("0"(履歴詳細無し))
                    a003ContactListDto.setDetails(DETAILS_0);
                    // 担当者名
                    a003ContactListDto.setChargeName(finalData.getUserNm());

                    // 接触履歴情報リストに接触履歴情報を追加する。
                    ifaContactList.add(a003ContactListDto);
                }
            }
        }
    }

    /**
     * 問合せ接触履歴情報取得して設定する.
     * 
     * @param customerId     顧客コード
     * @param ifaContactList 接触履歴リスト
     * @return
     */
    private void setIfaContactSql004InfoList(String customerId, List<IfaContactCommonResponseDto> ifaContactList)
            throws Exception {

        // 問合せ接触履歴情報取得SQLのリクエストモデル
        IfaContactSql004RequestModel selSql004Req = new IfaContactSql004RequestModel();
        // 顧客コード
        selSql004Req.setCustomerId(customerId);

        // 問合せ接触履歴情報を取得する。
        DataList<IfaContactSql004ResponseModel> selSql004Res = dao.selectIfaContactSql004(selSql004Req);

        if (selSql004Res != null && CollectionUtils.isNotEmpty(selSql004Res.getDataList())) {

            // 接触履歴モデル
            IfaContactCommonResponseDto a004ContactListDto = null;

            for (IfaContactSql004ResponseModel sql004ResponseModel : selSql004Res.getDataList()) {

                // 接触履歴モデル
                a004ContactListDto = new IfaContactCommonResponseDto();

                // 大分類
                a004ContactListDto.setBigClass(BIG_CLS_IFA_CONTACT_INPUT);
                // 中分類
                a004ContactListDto.setMidClass(
                        getMidClassFor004(sql004ResponseModel.getToiawaseMei(), sql004ResponseModel.getToiawaseSMei()));
                // 記録日時
                a004ContactListDto.setRecordDate(sql004ResponseModel.getToiawaseNichiji());
                // ステータス
                a004ContactListDto.setStatus(TaiouSts.valueOfId(sql004ResponseModel.getTaiouSts()) != null
                        ? TaiouSts.valueOfId(sql004ResponseModel.getTaiouSts()).getLabel()
                        : StringUtil.EMPTY_STRING);
                // 内容
                a004ContactListDto.setContents(sql004ResponseModel.getToiawaseNaiyou());
                // 詳細("1" ※問合せ接触履歴受付詳細へのリンク)
                a004ContactListDto.setDetails(DETAILS_1);
                // 担当者名
                a004ContactListDto.setChargeName(sql004ResponseModel.getUserNm());
                // IFA問合せNO
                a004ContactListDto.setIfaToiawaseNo(sql004ResponseModel.getIfaToiawaseNo());
                // 参照元履歴区分("2"(問合せ履歴))
                a004ContactListDto.setReferenceKbn(IfaContactUtil.REFERENCE_KBN_2);
                // 回答IFA問合せNO
                a004ContactListDto.setAnsIfaToiawaseNo(sql004ResponseModel.getAnsIfaToiawaseNo());

                ifaContactList.add(a004ContactListDto);
            }
        }
    }

    /**
     * 書類請求接触履歴情報取得して設定する.
     * 
     * @param customerId           顧客コード
     * @param ifaContactList       接触履歴リスト
     * @return
     */
    private void setIfaContactSql005InfoList(String customerId, List<IfaContactCommonResponseDto> ifaContactList)
            throws Exception {

        // 書類請求接触履歴情報取得SQLのリクエストモデル
        IfaContactSql005RequestModel selSql005Req = new IfaContactSql005RequestModel();
        // 顧客コード
        selSql005Req.setCustomerId(customerId);

        // 書類請求接触履歴情報を取得する。
        DataList<IfaContactSql005ResponseModel> selSql005Res = dao.selectIfaContactSql005(selSql005Req);

        if (selSql005Res != null && CollectionUtils.isNotEmpty(selSql005Res.getDataList())) {

            // 接触履歴モデル
            IfaContactCommonResponseDto a005ContactListDto = null;

            for (IfaContactSql005ResponseModel sql005ResponseModel : selSql005Res.getDataList()) {

                // 接触履歴モデル
                a005ContactListDto = new IfaContactCommonResponseDto();

                // 大分類
                a005ContactListDto.setBigClass(BIG_CLS_PAPERS);
                // 中分類
                a005ContactListDto.setMidClass(sql005ResponseModel.getShoruiBunruiMei());
                // 記録日時
                a005ContactListDto.setRecordDate(sql005ResponseModel.getStsSeniNichiji());
                // ステータス
                a005ContactListDto.setStatus(HassouSts.valueOfId(sql005ResponseModel.getHassouSts()) != null
                        ? HassouSts.valueOfId(sql005ResponseModel.getHassouSts()).getLabel()
                        : null);
                // 内容
                a005ContactListDto.setContents(getContentsFor005(sql005ResponseModel));
                // 詳細("1" ※問合せ接触履歴受付詳細へのリンク)
                a005ContactListDto.setDetails(DETAILS_1);
                // 担当者名
                a005ContactListDto.setChargeName(sql005ResponseModel.getUserNm());
                // 参照元履歴区分("3"(書類請求当日履歴))
                a005ContactListDto.setReferenceKbn(IfaContactUtil.REFERENCE_KBN_3);
                ifaContactList.add(a005ContactListDto);
            }
        }
    }

    /**
     * 出金接触履歴情報取得して設定する.
     * 
     * @param customerId           顧客コード
     * @param ifaContactList       接触履歴リスト
     * @return
     */
    private void setIfaContactSql006InfoList(String customerId, List<IfaContactCommonResponseDto> ifaContactList)
            throws Exception {

        // 出金接触履歴情報取得SQLのリクエストモデル
        IfaContactSql006RequestModel selSql006Req = new IfaContactSql006RequestModel();
        // 顧客コード
        selSql006Req.setCustomerId(customerId);

        // 出金接触履歴情報を取得する。
        DataList<IfaContactSql006ResponseModel> selSql006Res = dao.selectIfaContactSql006(selSql006Req);

        if (selSql006Res != null && CollectionUtils.isNotEmpty(selSql006Res.getDataList())) {

            // 接触履歴モデル
            IfaContactCommonResponseDto a006ContactListDto = null;

            for (IfaContactSql006ResponseModel sql006ResponseModel : selSql006Res.getDataList()) {

                // 接触履歴モデル
                a006ContactListDto = new IfaContactCommonResponseDto();

                // 大分類: 入出金
                a006ContactListDto.setBigClass(BIG_CLS_NYUUSYUKKIN);
                // 中分類: 出金
                a006ContactListDto.setMidClass(MID_CLS_SHUKKIN);
                // 記録日時
                a006ContactListDto.setRecordDate(sql006ResponseModel.getTourokuNichiji());
                // ステータス
                a006ContactListDto.setStatus(IfaContactUtil.HALF_HYPHEN);
                // 内容
                a006ContactListDto.setContents(getContentsFor006(sql006ResponseModel));
                // 詳細("0"(履歴詳細無し))
                a006ContactListDto.setDetails(DETAILS_0);
                // 担当者名
                a006ContactListDto.setChargeName(sql006ResponseModel.getUserNm());

                ifaContactList.add(a006ContactListDto);
            }
        }
    }

    /**
     * 出金取消接触履歴情報取得して設定する.
     * 
     * @param customerId           顧客コード
     * @param ifaContactList       接触履歴リスト
     * @return
     */
    private void setIfaContactSql007InfoList(String customerId, List<IfaContactCommonResponseDto> ifaContactList)
            throws Exception {

        // 出金取消接触履歴情報取得SQLのリクエストモデル
        IfaContactSql007RequestModel selSql007Req = new IfaContactSql007RequestModel();
        // 顧客コード
        selSql007Req.setCustomerId(customerId);

        // 出金取消接触履歴情報を取得する。
        DataList<IfaContactSql007ResponseModel> selSql007Res = dao.selectIfaContactSql007(selSql007Req);

        if (selSql007Res != null && CollectionUtils.isNotEmpty(selSql007Res.getDataList())) {

            // 接触履歴モデル
            IfaContactCommonResponseDto a007ContactListDto = null;

            for (IfaContactSql007ResponseModel sql007ResponseModel : selSql007Res.getDataList()) {

                // 接触履歴モデル
                a007ContactListDto = new IfaContactCommonResponseDto();

                // 大分類
                a007ContactListDto.setBigClass(BIG_CLS_NYUUSYUKKIN);
                // 中分類
                a007ContactListDto.setMidClass(MID_CLS_SHUKKIN_CANCEL);
                // 記録日時
                a007ContactListDto.setRecordDate(sql007ResponseModel.getTourokuNichiji());
                // ステータス
                a007ContactListDto.setStatus(IfaContactUtil.HALF_HYPHEN);
                // 内容
                a007ContactListDto.setContents(getContentsFor007(sql007ResponseModel));
                // 詳細("0"(履歴詳細無し))
                a007ContactListDto.setDetails(DETAILS_0);
                // 担当者名
                a007ContactListDto.setChargeName(sql007ResponseModel.getUserNm());

                ifaContactList.add(a007ContactListDto);
            }
        }
    }

    /**
     * 国内注文見出（履歴）取得して設定する.
     * 
     * @param customerId           顧客コード
     * @param ifaContactList       接触履歴リスト
     * @return
     */
    private void setIfaContactSql008InfoList(String customerId, List<IfaContactCommonResponseDto> ifaContactList)
            throws Exception {

        // 国内注文接触履歴情報取得SQLのリクエストモデル
        IfaContactSql008RequestModel selSql008Req = new IfaContactSql008RequestModel();
        // 顧客コード
        selSql008Req.setCustomerId(customerId);

        // 国内注文接触履歴情報を取得する。
        DataList<IfaContactSql008ResponseModel> selSql008Res = dao.selectIfaContactSql008(selSql008Req);

        if (selSql008Res != null && CollectionUtils.isNotEmpty(selSql008Res.getDataList())) {

            // 接触履歴モデル
            IfaContactCommonResponseDto a008ContactListDto = null;

            for (IfaContactSql008ResponseModel sql008ResponseModel : selSql008Res.getDataList()) {

                // 接触履歴モデル
                a008ContactListDto = new IfaContactCommonResponseDto();

                // 大分類: 国内注文
                a008ContactListDto.setBigClass(BIG_CLS_DOMESTIC_ORDER);
                // 中分類: 注文受付
                a008ContactListDto.setMidClass(MID_CLS_TYUUMON_UKETSUKE);
                // 記録日時
                a008ContactListDto.setRecordDate(sql008ResponseModel.getCreateTime());
                // ステータス
                a008ContactListDto.setStatus(IfaContactUtil.HALF_HYPHEN);
                // 内容: 内容は詳細を参照
                a008ContactListDto.setContents(CONTENT_DETAIL_REF);
                // 詳細("1"(履歴詳細有り) ※接触履歴詳細画面へのリンク)
                a008ContactListDto.setDetails(DETAILS_1);
                // 担当者名
                a008ContactListDto.setChargeName(sql008ResponseModel.getUserNm());
                // 作成者
                a008ContactListDto.setCreateUser(sql008ResponseModel.getCreateUser());
                // 参照元履歴区分("4"(国内注文当日履歴))
                a008ContactListDto.setReferenceKbn(IfaContactUtil.REFERENCE_KBN_4);

                ifaContactList.add(a008ContactListDto);
            }
        }
    }

    /**
     * 外株委託注文見出（当日までの履歴）情報取得して設定する.
     * 
     * @param butenCode        部店コード
     * @param accountNumber    口座番号
     * @param ifaContactList   接触履歴リスト
     * @return
     */
    private void setIfaContactSql011InfoList(String butenCode, String accountNumber,
            List<IfaContactCommonResponseDto> ifaContactList) throws Exception {

        // 外株委託注文接触履歴情報取得SQLのリクエストモデル
        IfaContactSql011RequestModel selSql011Req = new IfaContactSql011RequestModel();
        // 部店コード
        selSql011Req.setButenCode(butenCode);
        // 口座番号
        selSql011Req.setAccountNumber(accountNumber);

        // 外株委託注文接触履歴情報を取得する。
        DataList<IfaContactSql011ResponseModel> selSql011Res = dao.selectIfaContactSql011(selSql011Req);

        if (selSql011Res != null && CollectionUtils.isNotEmpty(selSql011Res.getDataList())) {

            // 接触履歴モデル
            IfaContactCommonResponseDto a011ContactListDto = null;

            for (IfaContactSql011ResponseModel sql011ResponseModel : selSql011Res.getDataList()) {

                // 接触履歴モデル
                a011ContactListDto = new IfaContactCommonResponseDto();

                // 大分類
                a011ContactListDto.setBigClass(BIG_CLS_FOREIGN_CONSIGNMENT_ORDER);
                // 中分類
                a011ContactListDto.setMidClass(IfaContactUtil.HALF_HYPHEN);
                // 記録日時
                a011ContactListDto.setRecordDate(sql011ResponseModel.getCreateTime());
                // ステータス
                a011ContactListDto.setStatus(IfaContactUtil.HALF_HYPHEN);
                // 内容: 内容は詳細を参照
                a011ContactListDto.setContents(CONTENT_DETAIL_REF);
                // 詳細("1" ※接触履歴詳細画面へのリンク)
                a011ContactListDto.setDetails(DETAILS_1);
                // 担当者名
                a011ContactListDto.setChargeName(sql011ResponseModel.getUserNm());
                // 作成者
                a011ContactListDto.setCreateUser(sql011ResponseModel.getCreateUser());
                // 参照元履歴区分("7"(外株委託当日履歴))
                a011ContactListDto.setReferenceKbn(IfaContactUtil.REFERENCE_KBN_7);

                ifaContactList.add(a011ContactListDto);
            }
        }
    }

    /**
     * 外株店頭注文見出（当日までの履歴）情報取得して設定する.
     * 
     * @param butenCode        部店コード
     * @param accountNumber    口座番号
     * @param ifaContactList   接触履歴リスト
     * @return
     */
    private void setIfaContactSql012InfoList(String butenCode, String accountNumber,
            List<IfaContactCommonResponseDto> ifaContactList) throws Exception {

        // 外株店頭注文接触履歴情報取得SQLのリクエストモデル
        IfaContactSql012RequestModel selSql012Req = new IfaContactSql012RequestModel();
        // 部店コード
        selSql012Req.setButenCode(butenCode);
        // 口座番号
        selSql012Req.setAccountNumber(accountNumber);

        // 外株店頭注文接触履歴情報を取得する。
        DataList<IfaContactSql012ResponseModel> selSql012Res = dao.selectIfaContactSql012(selSql012Req);

        if (selSql012Res != null && CollectionUtils.isNotEmpty(selSql012Res.getDataList())) {

            // 接触履歴モデル
            IfaContactCommonResponseDto a012ContactListDto = null;

            for (IfaContactSql012ResponseModel sql012ResponseModel : selSql012Res.getDataList()) {

                // 接触履歴モデル
                a012ContactListDto = new IfaContactCommonResponseDto();

                // 大分類
                a012ContactListDto.setBigClass(BIG_CLS_COUNTER_ORDER);
                // 中分類
                a012ContactListDto.setMidClass(IfaContactUtil.HALF_HYPHEN);
                // 記録日時
                a012ContactListDto.setRecordDate(sql012ResponseModel.getCreateTime());
                // ステータス
                a012ContactListDto.setStatus(IfaContactUtil.HALF_HYPHEN);
                // 内容: 内容は詳細を参照
                a012ContactListDto.setContents(CONTENT_DETAIL_REF);
                // 詳細("1" ※接触履歴詳細画面へのリンク)
                a012ContactListDto.setDetails(DETAILS_1);
                // 担当者名
                a012ContactListDto.setChargeName(sql012ResponseModel.getUserNm());
                // 作成者
                a012ContactListDto.setCreateUser(sql012ResponseModel.getCreateUser());
                // 参照元履歴区分("8"(外株店頭当日履歴))
                a012ContactListDto.setReferenceKbn(IfaContactUtil.REFERENCE_KBN_8);

                ifaContactList.add(a012ContactListDto);
            }
        }
    }

    /**
     * 為替取引注文見出（当日までの履歴）情報取得して設定する.
     * 
     * @param butenCode        部店コード
     * @param accountNumber    口座番号
     * @param ifaContactList   接触履歴リスト
     * @return
     */
    private void setIfaContactSql013InfoList(String butenCode, String accountNumber,
            List<IfaContactCommonResponseDto> ifaContactList) throws Exception {

        // 為替取引注文接触履歴情報取得SQLのリクエストモデル
        IfaContactSql013RequestModel selSql013Req = new IfaContactSql013RequestModel();
        // 部店コード
        selSql013Req.setButenCode(butenCode);
        // 口座番号
        selSql013Req.setAccountNumber(accountNumber);

        // 為替取引注文接触履歴情報を取得する。
        DataList<IfaContactSql013ResponseModel> selSql013Res = dao.selectIfaContactSql013(selSql013Req);

        if (selSql013Res != null && CollectionUtils.isNotEmpty(selSql013Res.getDataList())) {

            // 接触履歴モデル
            IfaContactCommonResponseDto a013ContactListDto = null;

            for (IfaContactSql013ResponseModel sql013ResponseModel : selSql013Res.getDataList()) {

                // 接触履歴モデル
                a013ContactListDto = new IfaContactCommonResponseDto();

                // 大分類
                a013ContactListDto.setBigClass(BIG_CLS_EXCHANGE_ORDER);
                // 中分類
                a013ContactListDto.setMidClass(IfaContactUtil.HALF_HYPHEN);
                // 記録日時
                a013ContactListDto.setRecordDate(sql013ResponseModel.getCreateTime());
                // ステータス
                a013ContactListDto.setStatus(IfaContactUtil.HALF_HYPHEN);
                // 内容: 内容は詳細を参照
                a013ContactListDto.setContents(CONTENT_DETAIL_REF);
                // 詳細("1" ※接触履歴詳細画面へのリンク)
                a013ContactListDto.setDetails(DETAILS_1);
                // 担当者名
                a013ContactListDto.setChargeName(sql013ResponseModel.getUserNm());
                // 作成者
                a013ContactListDto.setCreateUser(sql013ResponseModel.getCreateUser());
                // 参照元履歴区分("9"(為替取引当日履歴))
                a013ContactListDto.setReferenceKbn(IfaContactUtil.REFERENCE_KBN_9);

                ifaContactList.add(a013ContactListDto);
            }
        }
    }

    /**
     * 国内株式現引現渡注文接触履歴情報取得して設定する.
     * 
     * @param customerId           顧客コード
     * @param ifaContactList       接触履歴リスト
     * @return
     */
    private void setIfaContactSql015InfoList(String customerId, List<IfaContactCommonResponseDto> ifaContactList) throws Exception {

        // 国内株式現引現渡注文接触履歴情報取得SQLのリクエストモデル
        IfaContactSql015RequestModel selSql015Req = new IfaContactSql015RequestModel();
        // 顧客コード
        selSql015Req.setCustomerId(customerId);

        // 国内株式現引現渡注文接触履歴情報を取得する。
        DataList<IfaContactSql015ResponseModel> selSql015Res = dao.selectIfaContactSql015(selSql015Req);

        if (selSql015Res != null && CollectionUtils.isNotEmpty(selSql015Res.getDataList())) {
            // 同一のIFA注文番号のレコード内(作成日時の降順でソート済)で以下の仕様で明細の表示/非表示を判断する

            // 接触履歴モデル
            IfaContactCommonResponseDto a015ContactListDto = null;

            // 同一のIFA注文番号の第1条データの番号
            int num = 0;

            List<IfaContactSql015ResponseModel> selSql015ResList = selSql015Res.getDataList();

            for (int i = 0; i < selSql015ResList.size(); i++) {

                // 接触履歴モデル
                a015ContactListDto = new IfaContactCommonResponseDto();

                // 現在のデータ
                IfaContactSql015ResponseModel nowData = selSql015ResList.get(i);
                // 同一のIFA注文番号の第1条データレコード
                IfaContactSql015ResponseModel firstData = selSql015ResList.get(num);
                // 戻り結果の設定用のモデル
                IfaContactSql015ResponseModel finalData = null;

                if (!firstData.getIfaOrderNo().equals(nowData.getIfaOrderNo())) {
                    // 次のデータセットの場合、新しい組データセットの「第1条データの番号」を設定
                    num = i;
                }

                if (i == num && IfaContactUtil.OrderStatus.TJO_ORDER_STATUS_2.equals(nowData.getOrderStatus())) {
                    // 該当レコードの1番目がA.注文状況＝'2'(取消)の場合、1番目のA.注文状況＝'2'(取消)の明細は表示
                    finalData = nowData;
                } else {
                    // 該当レコードの1番目がA.注文状況＝'2'(取消)以外の場合、レコードは表示しない
                    continue;
                }

                if (finalData != null) {
                    // 大分類
                    a015ContactListDto.setBigClass(BIG_CLS_DOMESTIC_ORDER);
                    // 中分類
                    a015ContactListDto.setMidClass(g_ifaContactUtil.getMidClass(finalData.getTradeKbn(),
                            finalData.getPaymentLimit(), finalData.getDailyCreditKbn(), finalData.getPaymentLimitDate(),
                            finalData.getDateKbn()));
                    // 記録日時
                    a015ContactListDto.setRecordDate(finalData.getCreateTime());
                    // ステータス
                    a015ContactListDto.setStatus(STATUS_ORDER_CANCELLATION);
                    // 内容
                    TJpOrderBaseModel tJpOrderBaseModel = new TJpOrderBaseModel();
                    BeanUtils.copyProperties(tJpOrderBaseModel, finalData);
                    a015ContactListDto.setContents(
                            g_ifaContactUtil.getContentsFor015(tJpOrderBaseModel, IfaContactUtil.IFA_CONTACT));
                    // 詳細("0"(履歴詳細無し))
                    a015ContactListDto.setDetails(DETAILS_0);
                    // 担当者名
                    a015ContactListDto.setChargeName(finalData.getUserNm());

                    // 接触履歴情報リストに接触履歴情報を追加する。
                    ifaContactList.add(a015ContactListDto);
                }
            }
        }
    }

    /**
     * 国内投信(一般)注文接触履歴情報取得して設定する.
     * 
     * @param customerId           顧客コード
     * @param jrIsaContractType    ジュニアISA契約区分
     * @param ifaContactList       接触履歴リスト
     * @return
     */
    private void setIfaContactSql016InfoList(String customerId, String jrIsaContractType,
            List<IfaContactCommonResponseDto> ifaContactList) throws Exception {

        // 国内投信(一般)注文接触履歴情報取得SQLのリクエストモデル
        IfaContactSql016RequestModel selSql016Req = new IfaContactSql016RequestModel();
        // 顧客コード
        selSql016Req.setCustomerId(customerId);

        // 国内投信(一般)注文接触履歴情報を取得する。
        DataList<IfaContactSql016ResponseModel> selSql016Res = dao.selectIfaContactSql016(selSql016Req);

        if (selSql016Res != null && CollectionUtils.isNotEmpty(selSql016Res.getDataList())) {
            // 同一のIFA注文番号のレコード内(作成日時の降順でソート済)で以下の仕様で明細の表示/非表示を判断する

            // 接触履歴モデル
            IfaContactCommonResponseDto a016ContactListDto = null;

            // 同一のIFA注文番号の第1条データの番号
            int num = 0;

            List<IfaContactSql016ResponseModel> selSql016ResList = selSql016Res.getDataList();

            for (int i = 0; i < selSql016ResList.size(); i++) {

                // 接触履歴モデル
                a016ContactListDto = new IfaContactCommonResponseDto();

                // 現在のデータ
                IfaContactSql016ResponseModel nowData = selSql016ResList.get(i);
                // 同一のIFA注文番号の第1条データレコード
                IfaContactSql016ResponseModel firstData = selSql016ResList.get(num);
                // 戻り結果の設定用のモデル
                IfaContactSql016ResponseModel finalData = null;

                if (!nowData.getIfaOrderNo().equals(firstData.getIfaOrderNo())) {
                    // 次のデータセットの場合、新しい組データセットの「第1条データの番号」を設定
                    num = i;
                }

                if (i == num && IfaContactUtil.OrderStatus.TJO_ORDER_STATUS_2.equals(nowData.getOrderStatus())) {
                    // 該当レコードの1番目がA.注文状況＝'2'(取消)の場合、1番目のA.注文状況＝'2'(取消)の明細は表示
                    finalData = nowData;
                } else {
                    // 該当レコードの1番目がA.注文状況＝'2'(取消)以外の場合、レコードは表示しない
                    continue;
                }

                if (finalData != null) {
                    // 大分類
                    a016ContactListDto.setBigClass(BIG_CLS_DOMESTIC_ORDER);
                    // 中分類
                    a016ContactListDto.setMidClass(g_ifaContactUtil.getSomeJtoTradeKbnNm(finalData.getTradeKbn()));
                    // 記録日時
                    a016ContactListDto.setRecordDate(finalData.getCreateTime());
                    // ステータス
                    a016ContactListDto.setStatus(STATUS_ORDER_CANCELLATION);
                    // 内容
                    JpToushinOrderBaseModel jpToushinOrderBaseModel = new JpToushinOrderBaseModel();
                    BeanUtils.copyProperties(jpToushinOrderBaseModel, finalData);
                    a016ContactListDto.setContents(g_ifaContactUtil.getContentsFor016(jpToushinOrderBaseModel,
                            jrIsaContractType, IfaContactUtil.IFA_CONTACT));
                    // 詳細("0"(履歴詳細無し))
                    a016ContactListDto.setDetails(DETAILS_0);
                    // 担当者名
                    a016ContactListDto.setChargeName(finalData.getUserNm());

                    // 接触履歴情報リストに接触履歴情報を追加する。
                    ifaContactList.add(a016ContactListDto);
                }
            }
        }
    }

    /**
     * 国内投信(累投)注文接触履歴情報取得して設定する.
     * 
     * @param customerId           顧客コード
     * @param jrIsaContractType    ジュニアISA契約区分
     * @param ifaContactList       接触履歴リスト
     * @return
     */
    private void setIfaContactSql017InfoList(String customerId, String jrIsaContractType,
            List<IfaContactCommonResponseDto> ifaContactList) throws Exception {

        // 国内投信(累投)注文接触履歴情報取得SQLのリクエストモデル
        IfaContactSql017RequestModel selSql017Req = new IfaContactSql017RequestModel();
        // 顧客コード
        selSql017Req.setCustomerId(customerId);

        // 国内投信(累投)注文接触履歴情報を取得する。
        DataList<IfaContactSql017ResponseModel> selSql017Res = dao.selectIfaContactSql017(selSql017Req);

        if (selSql017Res != null && CollectionUtils.isNotEmpty(selSql017Res.getDataList())) {
            // 同一のIFA注文番号のレコード内(作成日時の降順でソート済)で以下の仕様で明細の表示/非表示を判断する

            // 接触履歴モデル
            IfaContactCommonResponseDto a017ContactListDto = null;

            // 同一のIFA注文番号内で最大情報を取得する。
            Map<String, List<String>> maxInfoMap = g_ifaContactUtil.getJtoMaxInfoMap();

            // 同一のIFA注文番号の第1条データの番号
            int num = 0;

            List<IfaContactSql017ResponseModel> selSql017ResList = selSql017Res.getDataList();

            for (int i = 0; i < selSql017ResList.size(); i++) {

                // 接触履歴モデル
                a017ContactListDto = new IfaContactCommonResponseDto();

                // 現在のデータ
                IfaContactSql017ResponseModel nowData = selSql017ResList.get(i);
                // 同一のIFA注文番号の第1条データレコード
                IfaContactSql017ResponseModel firstData = selSql017ResList.get(num);
                // 戻り結果の設定用のモデル
                IfaContactSql017ResponseModel finalData = null;

                if (!nowData.getIfaOrderNo().equals(firstData.getIfaOrderNo())) {
                    // 次のデータセットの場合、新しい組データセットの「第1条データの番号」を設定
                    num = i;
                }

                if (i == num && IfaContactUtil.OrderStatus.TJO_ORDER_STATUS_2.equals(nowData.getOrderStatus())) {
                    // 該当レコードの1番目がA.注文状況＝'2'(取消)の場合、1番目のA.注文状況＝'2'(取消)の明細は表示
                    finalData = nowData;
                } else {
                    // 該当レコードの1番目がA.注文状況＝'2'(取消)以外の場合、レコードは表示しない
                    continue;
                }

                if (finalData != null) {
                    // 大分類
                    a017ContactListDto.setBigClass(BIG_CLS_DOMESTIC_ORDER);
                    // 中分類
                    a017ContactListDto.setMidClass(g_ifaContactUtil.getJtoTradeKbnNm(finalData.getTradeKbn()));
                    // 記録日時
                    a017ContactListDto.setRecordDate(finalData.getCreateTime());
                    // ステータス
                    a017ContactListDto.setStatus(STATUS_ORDER_CANCELLATION);
                    // 内容
                    JpToushinOrderBaseModel jpToushinOrderBaseModel = new JpToushinOrderBaseModel();
                    BeanUtils.copyProperties(jpToushinOrderBaseModel, finalData);
                    a017ContactListDto.setContents(g_ifaContactUtil.getContentsFor017(jpToushinOrderBaseModel,
                            jrIsaContractType, maxInfoMap.get(finalData.getIfaOrderNo()), IfaContactUtil.IFA_CONTACT));
                    // 詳細("0"(履歴詳細無し))
                    a017ContactListDto.setDetails(DETAILS_0);
                    // 担当者名
                    a017ContactListDto.setChargeName(finalData.getUserNm());

                    // 接触履歴情報リストに接触履歴情報を追加する。
                    ifaContactList.add(a017ContactListDto);
                }
            }
        }
    }

    /**
     * その他注文その他余力拘束取消の当日接触履歴情報取得して設定する.
     * 
     * @param customerId           顧客コード
     * @param jrIsaContractType    ジュニアISA契約区分
     * @param ifaContactList       接触履歴リスト
     * @return
     */
    private void setIfaContactSql018InfoList(String customerId, String jrIsaContractType,
            List<IfaContactCommonResponseDto> ifaContactList) throws Exception {

        // その他注文その他余力拘束取消の接触履歴情報取得SQLのリクエストモデル
        IfaContactSql018RequestModel selSql018Req = new IfaContactSql018RequestModel();
        // 顧客コード
        selSql018Req.setCustomerId(customerId);

        // その他注文その他余力拘束取消の接触履歴情報を取得する。
        DataList<IfaContactSql018ResponseModel> selSql018Res = dao.selectIfaContactSql018(selSql018Req);

        if (selSql018Res != null && CollectionUtils.isNotEmpty(selSql018Res.getDataList())) {
            // 同一のトランザクションIDのレコード内で、以下の仕様で明細の表示/非表示を判断する

            // 接触履歴モデル
            IfaContactCommonResponseDto a018ContactListDto = null;

            // 同一のトランザクションID内で最大情報を取得する
            Map<String, List<String>> maxInfoMap = g_ifaContactUtil.getTaoiMaxInfoMap();

            // 同一のトランザクションIDの第1条(枝番最大)データレコードの番号
            int num = 0;

            List<IfaContactSql018ResponseModel> selSql018ResList = selSql018Res.getDataList();

            for (int i = 0; i < selSql018ResList.size(); i++) {

                // 接触履歴モデル
                a018ContactListDto = new IfaContactCommonResponseDto();

                // 現在のデータ
                IfaContactSql018ResponseModel nowData = selSql018ResList.get(i);
                // 同一のトランザクションIDの第1条(枝番最大)データレコード
                IfaContactSql018ResponseModel firstData = selSql018ResList.get(num);
                // 戻り結果の設定用のモデル
                IfaContactSql018ResponseModel finalData = null;

                if (!firstData.getTranId().equals(nowData.getTranId())) {
                    // 次のデータセットの場合、新しい組データセットの「第1条データの番号」を設定
                    num = i;
                }

                if (i == num && IfaContactUtil.TorikeshiKbn.TORIKESHIKBN_1.equals(nowData.getTorikeshiKbn())) {
                    // 該当レコード内でA.枝番が最大であるレコードがA.取消区分＝'1'(取消済)の場合、1番目のA.取消区分＝'1'(取消済)は明細表示
                    finalData = nowData;
                } else {
                    // 該当レコード内でA.枝番が最大であるレコードがA.取消区分＝'1'(取消済)以外の場合、レコードは表示しない
                    continue;
                }

                if (finalData != null) {
                    // 大分類
                    a018ContactListDto.setBigClass(BIG_CLS_DOMESTIC_ORDER);
                    // 中分類
                    a018ContactListDto.setMidClass(IfaContactUtil.MID_CLASS);
                    // 記録日時
                    a018ContactListDto.setRecordDate(finalData.getTourokuNichiji());
                    // ステータス
                    a018ContactListDto.setStatus(STATUS_ORDER_CANCELLATION);
                    // 内容
                    TbAdditionalOrderIfaBaseModel tbAdditionalOrderIfaBaseModel = new TbAdditionalOrderIfaBaseModel();
                    BeanUtils.copyProperties(tbAdditionalOrderIfaBaseModel, finalData);
                    a018ContactListDto.setContents(g_ifaContactUtil.getContentsFor018(tbAdditionalOrderIfaBaseModel,
                            jrIsaContractType, maxInfoMap.get(finalData.getTranId()), IfaContactUtil.IFA_CONTACT));
                    // 詳細("0"(履歴詳細無し))
                    a018ContactListDto.setDetails(DETAILS_0);
                    // 担当者名
                    a018ContactListDto.setChargeName(finalData.getUserNm());

                    // 接触履歴情報リストに接触履歴情報を追加する。
                    ifaContactList.add(a018ContactListDto);
                }
            }
        }
    }

    /**
     * 内容を取得する.
     * 
     * @param ifaContactSql005ResponseModel 書類請求接触履歴情報
     * @return contents 内容
     */
    private String getContentsFor005(IfaContactSql005ResponseModel ifaContactSql005ResponseModel) throws Exception {

        // 内容
        String contents = StringUtil.EMPTY_STRING;

        // 1.目論見書(内容作成用)を取得する
        // 目論見書(内容作成用)
        String mokuronmishoNoDisp = StringUtil.EMPTY_STRING;
        // 目論見書No
        String mokuronmishoNo = ifaContactSql005ResponseModel.getMokuronmishoNo();
        if (!StringUtil.isNullOrEmpty(mokuronmishoNo)) {
            // 目論見書NoがNULLでない場合："目論見書No.:"||trim(A.目論見書No)
            mokuronmishoNoDisp = "目論見書No.:" + mokuronmishoNo;
        }

        // 書類名(内容作成用)
        String shoruimeiDisp = g_ifaContactUtil.getVal(ifaContactSql005ResponseModel.getShoruimei());

        // 内容(内容作成用)
        String naiyouDisp = ifaContactSql005ResponseModel.getNaiyou();

        // 備考
        String bikou = ifaContactSql005ResponseModel.getBikou();

        // 内容 = A.書類名 + "　" + A.内容 + "　" + A.備考 + "　" + 目論見書
        contents = shoruimeiDisp + g_ifaContactUtil.getDefaultVal(naiyouDisp)
                + g_ifaContactUtil.getDefaultVal(bikou) + g_ifaContactUtil.getDefaultVal(mokuronmishoNoDisp);

        return contents;
    }

    /**
     * 内容を取得する.
     * 
     * @param ifaContactSql006ResponseModel 出金接触履歴情報
     * @return contents 内容
     */
    private String getContentsFor006(IfaContactSql006ResponseModel ifaContactSql006ResponseModel) throws Exception {

        // 内容
        String contents = StringUtil.EMPTY_STRING;

        // 1.金額(内容作成用)を取得する
        // 金額(内容作成用)
        String kingakuDisp = StringUtil.EMPTY_STRING;
        // 金額
        String kingaku = ifaContactSql006ResponseModel.getKingaku();
        if (!StringUtil.isNullOrEmpty(kingaku)) {
            // 金額がNULLでない場合、'出金額:'||trim(to_char(金額,'999,999,999,990'))
            kingakuDisp = "出金額:" + kingaku;
        }

        // 2.出金予定日(内容作成用)を取得する
        String shukkinYoteibiDisp = getShukkinYoteibiDisp(ifaContactSql006ResponseModel.getShukkinYoteibi());

        // 3.計上日(内容作成用)を取得する
        // 計上日(内容作成用)
        String keijoubiDisp = StringUtil.EMPTY_STRING;
        // 計上日
        String keijoubi = ifaContactSql006ResponseModel.getKeijoubi();
        if (!StringUtil.isNullOrEmpty(keijoubi)) {
            // 計上日がNULLでない場合、'計上日:'||substr(rtrim(計上日),1,2)||'/'||substr(rtrim(計上日),3)
            keijoubiDisp = "計上日:" + keijoubi;
        }

        // 内容 = 金額+" "+出金予定日+" "+計上日+" "+"振替区分:銀行振込(送金)"
        contents = kingakuDisp + g_ifaContactUtil.getDefaultVal(shukkinYoteibiDisp)
                + g_ifaContactUtil.getDefaultVal(keijoubiDisp) + IfaContactUtil.FULL_SPACE + "振替区分:銀行振込(送金)";

        return contents;
    }

    /**
     * 内容を取得する.
     * 
     * @param ifaContactSql007ResponseModel 出金取消接触履歴情報
     * @return contents 内容
     */
    private String getContentsFor007(IfaContactSql007ResponseModel ifaContactSql007ResponseModel) throws Exception {

        // 内容
        String contents = StringUtil.EMPTY_STRING;

        // 1.出金予定日(内容作成用)を取得する
        String shukkinYoteibiDisp = getShukkinYoteibiDisp(ifaContactSql007ResponseModel.getShukkinYoteibi());

        // EC入出金番号
        String ecNyushukkinNo = g_ifaContactUtil.getVal(ifaContactSql007ResponseModel.getEcNyushukkinNo());

        // 内容 = A.EC入出金番号+" "+出金予定日+" "+"振替区分:銀行振込(送金)"
        contents = ecNyushukkinNo + g_ifaContactUtil.getDefaultVal(shukkinYoteibiDisp)
                + IfaContactUtil.FULL_SPACE + "振替区分:銀行振込(送金)";

        return contents;
    }

    /**
     * 中分類を取得する.(setIfaContactSql004InfoList)
     * 
     * @param toiawaseMei  大分類ー名称（中）
     * @param toiawaseSMei 大分類ー名称（小）
     * @return 中分類
     */
    private String getMidClassFor004(String toiawaseMei, String toiawaseSMei) throws Exception {

        // 中分類
        String midClass = StringUtil.EMPTY_STRING;

        if (StringUtil.isNullOrEmpty(toiawaseSMei)) {
            // ※A.大分類ー名称（小）がNULLの場合、A.大分類ー名称（中）のみ表示する
            midClass = toiawaseMei;
        } else {
            // 大分類ー名称（中）+'・'+大分類ー名称（小）
            midClass = toiawaseMei + "・" + toiawaseSMei;
        }

        return midClass;
    }

    /**
     * 出金予定日(内容作成用)を取得する.(getContentsFor006/getContentsFor007)
     * 
     * @param shukkinYoteibi 出金予定日
     * @return 出金予定日(内容作成用)
     */
    private String getShukkinYoteibiDisp(String shukkinYoteibi) throws Exception {

        // 出金予定日(内容作成用)
        String shukkinYoteibiDisp = StringUtil.EMPTY_STRING;

        if (!StringUtil.isNullOrEmpty(shukkinYoteibi)) {
            // 出金予定日がNULLでない場合、'出金予定日:'||to_char(出金予定日,'yyyy/mm/dd')
            shukkinYoteibiDisp = "出金予定日:" + shukkinYoteibi;
        }
        return shukkinYoteibiDisp;
    }

    /**
     * 編集後の接触履歴リストを取得する.
     * 
     * @param ifaContactList 接触履歴リスト
     * @param maxCount       最大件数
     * @return 接触履歴リスト(編集後)
     */
    private List<IfaContactCommonResponseDto> getIfaContactResList(
            List<IfaContactCommonResponseDto> ifaContactList) {

        List<IfaContactCommonResponseDto> ifaContactResList = new ArrayList<IfaContactCommonResponseDto>();

        if (ifaContactList != null && CollectionUtils.isNotEmpty(ifaContactList)) {
            // 1.結果リストをまとめて記録日時の降順にソートする
            List<IfaContactCommonResponseDto> ifaContactDescSortedList = ifaContactList.stream()
                    .sorted(Comparator.comparing(IfaContactCommonResponseDto::getRecordDate).reversed())
                    .collect(Collectors.toList());

            // 2.明細は最大100件まで表示する
            ifaContactResList = new ArrayList<IfaContactCommonResponseDto>(new ArrayList<IfaContactCommonResponseDto>(
                    ifaContactDescSortedList.subList(0, Math.min(ifaContactDescSortedList.size(), MAX_COUNT))));
        }
        return ifaContactResList;
    }

    /**
     * 初期表示では全ての大分類の履歴を取得、表示.
     * 
     * bigClassList 大分類リスト
     * @param[out] ccsApiErr CCS API ERROR
     * @return 全ての大分類の接触履歴リスト
     */
    private List<IfaContactCommonResponseDto> getAllBigClassIfaContactList(CcsApiErrUtil ccsApiErr) throws Exception {

        // 全ての大分類の接触履歴リスト
        List<IfaContactCommonResponseDto> ifaContactList = new ArrayList<IfaContactCommonResponseDto>();

        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        // 部店コード
        String butenCode = cc.getButenCode();
        // 口座番号
        String accountNumber = cc.getAccountNumber();
        // 顧客コード
        String customerId = cc.getCustomerCode();
        // ジュニアISA契約区分
        String jrIsaContractType = cc.getJrIsaContractType();

        // 国内株式現物注文履歴情報取得して設定する
        setIfaContactSql002InfoList(customerId, jrIsaContractType, ifaContactList);
        // 国内株式信用新規(返済)注文履歴情報取得して設定する
        setIfaContactSql003InfoList(customerId, jrIsaContractType, ifaContactList);
        // 国内株式現引現渡注文接触履歴情報取得して設定する
        setIfaContactSql015InfoList(customerId, ifaContactList);
        // 国内投信(一般)注文接触履歴情報取得して設定する
        setIfaContactSql016InfoList(customerId, jrIsaContractType, ifaContactList);
        // 国内投信(累投)注文接触履歴情報取得して設定する
        setIfaContactSql017InfoList(customerId, jrIsaContractType, ifaContactList);
        // その他注文その他余力拘束取消の当日接触履歴情報取得して設定する
        setIfaContactSql018InfoList(customerId, jrIsaContractType, ifaContactList);
        // 外株委託注文見出（当日までの履歴）情報取得して設定する
        setIfaContactSql011InfoList(butenCode, accountNumber, ifaContactList);
        // 外株店頭注文見出（当日までの履歴）情報取得して設定する
        setIfaContactSql012InfoList(butenCode, accountNumber, ifaContactList);
        // 為替取引注文見出（当日までの履歴）情報取得して設定する
        setIfaContactSql013InfoList(butenCode, accountNumber, ifaContactList);
        // 問合せ接触履歴情報取得して設定する
        setIfaContactSql004InfoList(customerId, ifaContactList);
        // 書類請求接触履歴情報取得して設定する
        setIfaContactSql005InfoList(customerId, ifaContactList);
        // 出金　履歴情報取得して設定する
        setIfaContactSql006InfoList(customerId, ifaContactList);
        // 出金取消　履歴情報取得して設定する
        setIfaContactSql007InfoList(customerId, ifaContactList);
        // 国内注文見出（履歴）情報取得して設定する
        setIfaContactSql008InfoList(customerId, ifaContactList);

        /* ------------------------------------------- ↓↓↓↓ CCS接触履歴を取得する ↓↓↓↓ ----------------------------------------------- */
        // ユーザ共通情報.CCSログイン用ID＝未設定(Null または空文字)のチェック
        ccsApiErr.fromCcsIdWarn();
        if (ccsApiErr.getErrLevel() != 0) {
            return ifaContactList;
        }
        // CCS API用統合リスト
        List<IfaContactCommonResponseDto> p_ccsAllLst = new ArrayList<IfaContactCommonResponseDto>();
        
        // ================================================画面.大分類が『国内注文』====================================================
        List<ShowSubContractHistoryIFAOut.Contact> p_rtnLst = new ArrayList<ShowSubContractHistoryIFAOut.Contact>();
        /* カテゴリコード＝"01"(信用・株式注文履歴)でAPI呼び出し    「画面.大分類が『国内注文』の場合のAPI処理です」 */
        List<ShowSubContractHistoryIFAOut.Contact> p_tmpLst = new ArrayList<ShowSubContractHistoryIFAOut.Contact>();
        p_tmpLst = getCcsApiOutList(customerId, CategoryCode.CC_01.getId(), ccsApiErr);
        // APIエラーの場合、CCS API呼び出しの処理を終了する。
        if (ccsApiErr.getErrLevel() != 0) {
            return ifaContactList;
        }
        p_rtnLst.addAll(p_tmpLst);

        /* カテゴリコード＝"02"(投信注文履歴)でAPI呼び出し    「画面.大分類が『国内注文』の場合のAPI処理です」 */
        p_tmpLst = new ArrayList<ShowSubContractHistoryIFAOut.Contact>();
        p_tmpLst = getCcsApiOutList(customerId, CategoryCode.CC_02.getId(), ccsApiErr);
        // APIエラーの場合、CCS API呼び出しの処理を終了する。
        if (ccsApiErr.getErrLevel() != 0) {
            return ifaContactList;
        }
        p_rtnLst.addAll(p_tmpLst);
        
        /* カテゴリコード＝"03"(CW注文履歴)でAPI呼び出し    「画面.大分類が『国内注文』の場合のAPI処理です」 */
        p_tmpLst = new ArrayList<ShowSubContractHistoryIFAOut.Contact>();
        p_tmpLst = getCcsApiOutList(customerId, CategoryCode.CC_03.getId(), ccsApiErr);
        // APIエラーの場合、エラーメッセージを表示し、次の処理へ
        if (ccsApiErr.getErrLevel() != 0) {
            return ifaContactList;
        }
        p_rtnLst.addAll(p_tmpLst);

        /* カテゴリコード＝"04"(その他注文履歴)でAPI呼び出し    「画面.大分類が『国内注文』の場合のAPI処理です」 */
        p_tmpLst = new ArrayList<ShowSubContractHistoryIFAOut.Contact>();
        p_tmpLst = getCcsApiOutList(customerId, CategoryCode.CC_04.getId(), ccsApiErr);
        // APIエラーの場合、エラーメッセージを表示し、次の処理へ
        if (ccsApiErr.getErrLevel() != 0) {
            return ifaContactList;
        }
        p_rtnLst.addAll(p_tmpLst);
        // 「注文」大分類のCCSAPI出力リストをフィルタリングする
        filterTyumonListForCcs(p_rtnLst);
        // 取得した項目を編集し、ActionパラメータCCS接触履歴リストに設定する
        setIfaContactCcsApiInfoList(BIG_CLASS_1, p_rtnLst, p_ccsAllLst);

        // ==============================================画面.大分類が『積立買付／定期売却』============================================
        p_rtnLst = new ArrayList<ShowSubContractHistoryIFAOut.Contact>();
        /* カテゴリコード＝"05"(積立買付履歴)でAPI呼び出し    「画面.大分類が『積立買付／定期売却』の場合のAPI処理です」 */
        p_tmpLst = new ArrayList<ShowSubContractHistoryIFAOut.Contact>();
        p_tmpLst = getCcsApiOutList(customerId, CategoryCode.CC_05.getId(), ccsApiErr);
        // APIエラーの場合、エラーメッセージを表示し、次の処理へ
        if (ccsApiErr.getErrLevel() != 0) {
            return ifaContactList;
        }
        p_rtnLst.addAll(p_tmpLst);

        /* カテゴリコード＝"11"(定期売却履歴)でAPI呼び出し    「画面.大分類が『積立買付／定期売却』の場合のAPI処理です」 */
        p_tmpLst = new ArrayList<ShowSubContractHistoryIFAOut.Contact>();
        p_tmpLst = getCcsApiOutList(customerId, CategoryCode.CC_11.getId(), ccsApiErr);
        // APIエラーの場合、エラーメッセージを表示し、次の処理へ
        if (ccsApiErr.getErrLevel() != 0) {
            return ifaContactList;
        }
        p_rtnLst.addAll(p_tmpLst);
        // 取得した項目を編集し、ActionパラメータCCS接触履歴リストに設定する
        setIfaContactCcsApiInfoList(BIG_CLASS_3, p_rtnLst, p_ccsAllLst);

        // ==============================================画面.大分類が『PWリセット』===================================================
        /* カテゴリコード＝"07"(PWリセット履歴)でAPI呼び出し    「画面.大分類が『PWリセット』の場合のAPI処理です」 */
        p_tmpLst = new ArrayList<ShowSubContractHistoryIFAOut.Contact>();
        p_tmpLst = getCcsApiOutList(customerId, CategoryCode.CC_07.getId(), ccsApiErr);
        // APIエラーの場合、エラーメッセージを表示し、次の処理へ
        if (ccsApiErr.getErrLevel() != 0) {
            return ifaContactList;
        }
        // 取得した項目を編集し、ActionパラメータCCS接触履歴リストに設定する
        setIfaContactCcsApiInfoList(BIG_CLASS_8, p_tmpLst, p_ccsAllLst);

        // ==============================================画面.大分類が『書類請求』===================================================
        /* カテゴリコード＝"08"(書類請求情報)でAPI呼び出し    「画面.大分類が『書類請求』の場合のAPI処理です」 */
        p_tmpLst = new ArrayList<ShowSubContractHistoryIFAOut.Contact>();
        p_tmpLst = getCcsApiOutList(customerId, CategoryCode.CC_08.getId(), ccsApiErr);
        // APIエラーの場合、エラーメッセージを表示し、次の処理へ
        if (ccsApiErr.getErrLevel() != 0) {
            return ifaContactList;
        }
        // 取得した項目を編集し、ActionパラメータCCS接触履歴リストに設定する
        setIfaContactCcsApiInfoList(BIG_CLASS_4, p_tmpLst, p_ccsAllLst);

        // ==============================================画面.大分類が『入出金』====================================================
        /* カテゴリコード＝"09"(入出金履歴)でAPI呼び出し    「画面.大分類が『入出金』の場合のAPI処理です」 */
        p_tmpLst = new ArrayList<ShowSubContractHistoryIFAOut.Contact>();
        p_tmpLst = getCcsApiOutList(customerId, CategoryCode.CC_09.getId(), ccsApiErr);
        // APIエラーの場合、エラーメッセージを表示し、次の処理へ
        if (ccsApiErr.getErrLevel() != 0) {
            return ifaContactList;
        }
        // 出金（出金履歴）のCCS接触履歴リストをフィルタリングする
        filterShukkinListForCcs(customerId, p_tmpLst);
        // 出金取消（出金履歴）のCCS接触履歴リストをフィルタリングする
        filterShukkinToriKeshiListForCcs(customerId, p_tmpLst);
        // 取得した項目を編集し、ActionパラメータCCS接触履歴リストに設定する
        setIfaContactCcsApiInfoList(BIG_CLASS_5, p_tmpLst, p_ccsAllLst);

        // ==============================================画面.大分類が『貸株』=======================================================
        /* カテゴリコード＝"10"(貸株履歴)でAPI呼び出し    「画面.大分類が『貸株』の場合のAPI処理です」 */
        p_tmpLst = new ArrayList<ShowSubContractHistoryIFAOut.Contact>();
        p_tmpLst = getCcsApiOutList(customerId, CategoryCode.CC_10.getId(), ccsApiErr);
        // APIエラーの場合、エラーメッセージを表示し、次の処理へ
        if (ccsApiErr.getErrLevel() != 0) {
            return ifaContactList;
        }
        // 取得した項目を編集し、ActionパラメータCCS接触履歴リストに設定する
        setIfaContactCcsApiInfoList(BIG_CLASS_7, p_tmpLst, p_ccsAllLst);

        // ==============================================画面.大分類が『ネーム変更』===================================================
        /* カテゴリコード＝"12"(ユーザーネーム変更履歴)でAPI呼び出し    「画面.大分類が『ネーム変更』の場合のAPI処理です」 */
        p_tmpLst = new ArrayList<ShowSubContractHistoryIFAOut.Contact>();
        p_tmpLst = getCcsApiOutList(customerId, CategoryCode.CC_12.getId(), ccsApiErr);
        // APIエラーの場合、エラーメッセージを表示し、次の処理へ
        if (ccsApiErr.getErrLevel() != 0) {
            return ifaContactList;
        }
        // 取得した項目を編集し、ActionパラメータCCS接触履歴リストに設定する
        setIfaContactCcsApiInfoList(BIG_CLASS_8, p_tmpLst, p_ccsAllLst);

        // ==============================================画面.大分類が『貸株振替』====================================================
        /* カテゴリコード＝"13"(貸株振替履歴)でAPI呼び出し    「画面.大分類が『貸株振替』の場合のAPI処理です」 */
        p_tmpLst = new ArrayList<ShowSubContractHistoryIFAOut.Contact>();
        p_tmpLst = getCcsApiOutList(customerId, CategoryCode.CC_13.getId(), ccsApiErr);
        // APIエラーの場合、エラーメッセージを表示し、次の処理へ
        if (ccsApiErr.getErrLevel() != 0) {
            return ifaContactList;
        }
        // 取得した項目を編集し、ActionパラメータCCS接触履歴リストに設定する
        setIfaContactCcsApiInfoList(BIG_CLASS_9, p_tmpLst, p_ccsAllLst);

        // 最後、CCSAPIの統合リストを、統合リストの後に追加します。
        ifaContactList.addAll(p_ccsAllLst);
        /* ------------------------------------------- ↑↑↑↑ CCS接触履歴を取得する ↑↑↑↑ ----------------------------------------------- */

        return ifaContactList;
    }

    /* ------------------- 以下は： CCS接触履歴用 プライベートメソッド ------------------- */
    /**
     * CCS APIを呼出し、CCS接触履歴リストの情報を取得する
     * 
     * @param x_customerId 顧客ID
     * @param x_categoryCode 大分類コード
     * @param ccsApiErr CCS API ERROR 
     * @throws Exception API呼び出しでエラーが発生した場合
     */
    private List<ShowSubContractHistoryIFAOut.Contact> getCcsApiOutList(String x_customerId, String x_categoryCode, CcsApiErrUtil ccsApiErr) throws Exception {
        // APIのIN引数をセットする
        ShowSubContractHistoryIFAReq p_req = new ShowSubContractHistoryIFAReq();
        ShowSubContractHistoryIFAIn p_in = new ShowSubContractHistoryIFAIn();
        p_in.setAccountId(StringUtil.fillLeft(x_customerId, '0', 9));                                   // 顧客ID(前ゼロ埋め)
        p_in.setCategoryCode(x_categoryCode);                                                           // 大分類
        p_in.setUserId(StringUtil.fillRight(IfaCommonUtil.getUserAccount().getCcsUserId(), ' ', 8));    // ユーザ共通情報.CCSログイン用ID(左詰め、後"△"埋め)
        p_req.setParameter(p_in);
        // APIを呼出し
        ShowSubContractHistoryIFAOut p_out = null;
        try {
            p_out = g_ccsApiService.calShowSubContractHistoryIFA(p_req).getCcsApiOut();
            ccsApiErr.fromApiOut(p_out);
        } catch (Exception e) {
            LOGGER.error("IfaContactServiceImpL.getCcsApiOutList(calShowSubContractHistoryIFA) error.", e);
            ccsApiErr.setErrLevel(-1);
            ccsApiErr.setErrId(StringUtil.EMPTY_STRING);
            ccsApiErr.setErrMsg(StringUtil.EMPTY_STRING);
        }
        if (ccsApiErr.getErrLevel() != 0) {
            return null;
        }
        return p_out.getContactList();
    }

    /**
     * CCS接触履歴リストの情報により、IfaContactCommonResponseDtoに変換してリストに追加します。
     * 
     * @param x_categoryCode 大分類コード
     * @param x_ccsApiList API OUTのCCS接触履歴リスト
     * @param x_ifaContactList 接触履歴詳細リスト
     */
    private void setIfaContactCcsApiInfoList(String x_categoryCode, List<ShowSubContractHistoryIFAOut.Contact> x_ccsApiList,
            List<IfaContactCommonResponseDto> x_ifaContactList) throws Exception {
        // 接触履歴データが存在する場合のみ処理
        Optional.ofNullable(x_ccsApiList)
            .filter(CollectionUtils::isNotEmpty)
            .ifPresent(p_list -> p_list.stream()
                .map(p_contact -> createIfaContactResponseDtoForCcs(p_contact, x_categoryCode))
                .forEach(x_ifaContactList::add));
    }

    /**
     * CCS接触履歴（CCS入力）用
     * CcsApi1Out.ContactをIfaContactCommonResponseDtoに変換します。
     * 
     * @param p_contact 接触履歴データ
     * @param categoryCode 大分類コード
     * @return 変換後のDTOオブジェクト
     */
    private IfaContactCommonResponseDto createIfaContactResponseDtoForCcs(ShowSubContractHistoryIFAOut.Contact x_contact, String x_categoryCode) {

        IfaContactCommonResponseDto p_resDto = new IfaContactCommonResponseDto();
        // 大分類設定
        p_resDto.setBigClass(setBigClassForCcs(x_categoryCode, x_contact));
        // 中分類設定
        p_resDto.setMidClass(setMidClassForCcs(x_categoryCode, x_contact));
        // 記録日時
        p_resDto.setRecordDate(x_contact.getRecordDate());
        // ステータス
        p_resDto.setStatus(x_contact.getOrderStatus());
        // 内容
        p_resDto.setContents(startsWithDateForCcs(x_contact.getContent(), null) ? CONTENT_DETAIL_REF : x_contact.getContent());
        // 詳細
        p_resDto.setDetails(setDetailsForCcs(x_categoryCode, x_contact.getShousaiKbn()));
        // 担当者名
        p_resDto.setChargeName(x_contact.getUserMei());
        // 参照元履歴区分
        p_resDto.setReferenceKbn(ReferenceKbn.REFERENCE_KBN_1.getId());
        // 枝番
        p_resDto.setEdaban(x_contact.getEdaban());
        // 詳細区分
        p_resDto.setShousaiKbn(x_contact.getShousaiKbn());
        return p_resDto;
    }

    /**
     * CCS接触履歴（CCS入力）用
     * 大分類を設定します。
     * 
     * @param x_categoryCode 大分類コード
     * @param x_contact 接触履歴データ
     * @return 設定された大分類
     */
    private String setBigClassForCcs(String x_categoryCode, ShowSubContractHistoryIFAOut.Contact x_contact) {
        switch (x_categoryCode) {
            // 画面.大分類 = '国内注文'の場合、'国内注文'
            case BIG_CLASS_1: return BIG_CLS_DOMESTIC_ORDER;
            // 画面.大分類 = '入出金'の場合、'入出金'
            case BIG_CLASS_5: return BIG_CLS_NYUUSYUKKIN;
            // 画面.大分類 = '積立買付／定期売却'の場合、'積立買付／定期売却'
            case BIG_CLASS_3: return BIG_CLS_ACCUMULATE;
            // 上記以外の場合、CCS接触履歴リスト.大分類
            default: return x_contact.getBigClass();
        }
    }

    /**
     * CCS接触履歴（CCS入力）用
     * 中分類を設定します。
     * 
     * @param x_categoryCode 大分類コード
     * @param x_contact 接触履歴データ
     * @return 設定された中分類
     */
    private String setMidClassForCcs(String x_categoryCode, ShowSubContractHistoryIFAOut.Contact x_contact) {
        switch (x_categoryCode) {
            // 画面.大分類 = '国内注文'の場合、リスト.内容の先頭文字が"YYYY/MM/DD-"の場合、CCS接触履歴リスト.大分類 以外の場合、CCS接触履歴リスト.中分類
            case BIG_CLASS_1: return startsWithDateForCcs(x_contact.getContent(), null) ? x_contact.getBigClass() : x_contact.getMidClass();
            // 画面.大分類 = '入出金'の場合、CCS接触履歴リスト.大分類
            case BIG_CLASS_5: return x_contact.getBigClass();
            // 上記以外の場合、CCS接触履歴リスト.中分類
            default: return x_contact.getMidClass();
        }
    }

    /**
     * CCS接触履歴（CCS入力）用
     * 詳細区分を設定します。
     * 
     * @param categoryCode 大分類コード
     * @param shousaiKbn 詳細区分コード
     * @return 設定された詳細区分
     */
    private String setDetailsForCcs(String x_categoryCode, String x_shousaiKbn) {
        // 大分類 = '書類請求'の場合、詳細区分 = '0'(リンクなし)
        if (BIG_CLASS_4.equals(x_categoryCode)) {
            return DETAILS_0;
        // '1'(貸株履歴へのリンク)、'4'(貸株振替)、'5'(貸株)の場合、詳細区分 = '1'(接触履歴受付詳細画面へのリンク)
        } else if (ShousaiKbn.SK_01.getId().equals(x_shousaiKbn) 
                || ShousaiKbn.SK_04.getId().equals(x_shousaiKbn) 
                || ShousaiKbn.SK_05.getId().equals(x_shousaiKbn)) {
            return DETAILS_1;
        // 以外の場合、詳細区分 = '0'(リンクなし)
        } else {
            return DETAILS_0;
        }
    }

    /**
     * CCS接触履歴（CCS入力）用<br>
     * 「注文」大分類のCCSAPI出力リストをフィルタリングする
     * 引数に渡されたリストを直接変更する。
     *
     * @param x_list フィルタリング対象のCCS接触履歴リスト
     * @throws Exception 例外が発生した場合
     */
    private void filterTyumonListForCcs(List<ShowSubContractHistoryIFAOut.Contact> x_list) throws Exception {
        // 引数x_listがnullまたは空の場合、処理を終了
        if (CollectionUtils.isEmpty(x_list)) {
            return;
        }
        // リスト内の要素をフィルタリングし、条件に合致しないものを削除
        x_list.removeIf(p_rec -> {
            String p_bigCls = p_rec.getBigClass();  // 大分類
            String p_midCls = p_rec.getMidClass();  // 中分類
            String p_cont = p_rec.getContent();     // 内容
            String p_recDt = p_rec.getRecordDate(); // 記録日時
            /* CCS接触履歴リスト.大分類＝'注文'の場合、以下のレコード以外を取得 */
            if (BigClass.BC_00.getId().equals(p_bigCls)) {
                // CCS接触履歴リスト.記録日時の時刻部分(HH:MM:DD)＝'23:59:59'
                return (isEndTimeForCcs(p_recDt));
            /* CCS接触履歴リスト.大分類＝'受付のみ','一部受付','注文受付'の場合、以下のレコード以外を取得 */
            } else if (BigClass.BC_01.getId().equals(p_bigCls) 
                    || BigClass.BC_02.getId().equals(p_bigCls)
                    || BigClass.BC_03.getId().equals(p_bigCls)) {
                // CCS接触履歴リスト.内容が'YYYY/MM/DD-n'の形式の時、n部分(枝番)＝'30000' または
                // CCS接触履歴リスト.記録日時の時刻部分(HH:MM:DD)＝'23:59:59'
                return ((p_cont != null && (startsWithDateForCcs(p_cont, CCS_API_OUT_EDABAN))
                        || isEndTimeForCcs(p_recDt)));
            }
            // その他は含む
            return false;
        });
    }

    /**
     * CCS接触履歴（CCS入力）用<br>
     * 出金（出金履歴）のCCS接触履歴リストをフィルタリングする
     * 引数に渡されたリストを直接変更する。
     * 
     * @param x_customerId 顧客ID
     * @param x_list フィルタリング対象のCCS接触履歴リスト
     * @throws Exception 例外が発生した場合
     */
    private void filterShukkinListForCcs(String x_customerId, List<ShowSubContractHistoryIFAOut.Contact> x_list) throws Exception {
        // 引数 CCS接触履歴リスト がnullまたは空の場合、処理を終了
        if (CollectionUtils.isEmpty(x_list)) {
            return;
        }
        // SQL019リクエスト用のデータを格納するリスト
        List<List<String>> p_sql019reqList = new ArrayList<>();
        // CCS接触履歴リストをループして、大分類が「出金」のレコードのみ抽出
        for (ShowSubContractHistoryIFAOut.Contact p_rec : x_list) {
            // 大分類が「出金」の場合に処理
            if (BigClass.BC_04.getId().equals(p_rec.getBigClass())) {
                // 内容と記録日時をリストに追加
                List<String> entry = new ArrayList<>();
                entry.add(p_rec.getContent());        // 内容
                entry.add(p_rec.getRecordDate());     // 記録日時
                p_sql019reqList.add(entry);
            }
        }
        if (CollectionUtils.isEmpty(p_sql019reqList)) {
            return;
        }
        // SQL019リクエストモデルを作成
        IfaContactSql019RequestModel p_selSql019Req = new IfaContactSql019RequestModel();
        // 顧客コードを設定
        p_selSql019Req.setKokyakuId(x_customerId);
        // CCSAPI接触履歴の戻り値（内容 + 記録日時のJSON形式）
        ObjectMapper p_objectMapper = new ObjectMapper();
        String p_json = p_objectMapper.writeValueAsString(p_sql019reqList);
        p_selSql019Req.setCcsApiRtnVal(p_json);
        // SQL019を実行し、結果を取得
        DataList<IfaContactSql019ResponseModel> p_selSql019Res = dao.selectIfaContactSql019(p_selSql019Req);
        // 結果が空の場合、処理を終了
        if (CollectionUtils.isEmpty(p_selSql019Res.getDataList())) {
            return;
        }
        // 結果から「内容:記録日時」のセットを作成
        Set<String> p_compare = p_selSql019Res.getDataList().stream()
                .map(item -> item.getContent() + ":" + item.getRecordDate())
                .collect(Collectors.toSet());
        // CCS接触履歴リストから、以下のレコード以外を取得
        // CCS接触履歴リスト.記録日時＝SQL019.記録日時 かつ CCS接触履歴リスト.内容＝SQL019.出金内容
        x_list.removeIf(contact -> p_compare.contains(contact.getContent() + ":" + contact.getRecordDate()));
    }

    /**
     * CCS接触履歴（CCS入力）用<br>
     * 出金取消（出金履歴）のCCS接触履歴リストをフィルタリングする
     * 引数に渡されたリストを直接変更する。
     * 
     * @param x_customerId 顧客ID
     * @param x_list フィルタリング対象のCCS接触履歴リスト
     * @throws Exception 例外が発生した場合
     */
    private void filterShukkinToriKeshiListForCcs(String x_customerId, List<ShowSubContractHistoryIFAOut.Contact> x_list) throws Exception {
        // 引数 CCS接触履歴リスト がnullまたは空の場合、処理を終了
        if (CollectionUtils.isEmpty(x_list)) {
            return;
        }
        // SQL020リクエスト用のデータを格納するリスト
        List<List<String>> p_sql020reqList = new ArrayList<>();
        // CCS接触履歴リストをループして、大分類が「出金」のレコードのみ抽出
        for (ShowSubContractHistoryIFAOut.Contact p_rec : x_list) {
            // 大分類が「出金取消」の場合に処理
            if (BigClass.BC_05.getId().equals(p_rec.getBigClass())) {
                // 内容と記録日時をリストに追加
                List<String> entry = new ArrayList<>();
                entry.add(p_rec.getContent());        // 内容
                entry.add(p_rec.getRecordDate());     // 記録日時
                p_sql020reqList.add(entry);
            }
        }
        if (CollectionUtils.isEmpty(p_sql020reqList)) {
            return;
        }
        // SQL020リクエストモデルを作成
        IfaContactSql020RequestModel p_selSql020Req = new IfaContactSql020RequestModel();
        // 顧客コードを設定
        p_selSql020Req.setKokyakuId(x_customerId);
        // CCSAPI接触履歴の戻り値（内容 + 記録日時のJSON形式）
        ObjectMapper p_objectMapper = new ObjectMapper();
        String p_json = p_objectMapper.writeValueAsString(p_sql020reqList);
        p_selSql020Req.setCcsApiRtnVal(p_json);
        // SQL020を実行し、結果を取得
        DataList<IfaContactSql020ResponseModel> p_selSql020Res = dao.selectIfaContactSql020(p_selSql020Req);
        // 結果が空の場合、処理を終了
        if (CollectionUtils.isEmpty(p_selSql020Res.getDataList())) {
            return;
        }
        // 結果から「内容:記録日時」のセットを作成
        Set<String> p_compare = p_selSql020Res.getDataList().stream()
                .map(item -> item.getContent() + ":" + item.getRecordDate())
                .collect(Collectors.toSet());
        // CCS接触履歴リストから、以下のレコード以外を取得
        // CCS接触履歴リスト.記録日時＝SQL020.記録日時 かつ CCS接触履歴リスト.内容＝SQL020.出金内容
        x_list.removeIf(contact -> p_compare.contains(contact.getContent() + ":" + contact.getRecordDate()));
    }

    /**
     * CCS接触履歴（CCS入力）用<br>
     * 「問合せ」大分類のCCSAPI出力リストをフィルタリングする
     * 引数に渡されたリストを直接変更する。
     *
     * @param x_list フィルタリング対象のCCS接触履歴リスト
     * @throws Exception 例外が発生した場合
     */
    private void filterToiawaseListForCcs(List<ShowSubContractHistoryIFAOut.Contact> x_list) throws Exception {
        // 引数x_listがnullまたは空の場合、処理を終了
        if (CollectionUtils.isEmpty(x_list)) {
            return;
        }
        // リスト内の要素をフィルタリングし、条件に合致しないものを削除
        x_list.removeIf(p_rec -> {
            String p_bigCls = p_rec.getBigClass();  // 大分類
            /* CCS接触履歴リスト.大分類＝'問合せ'のレコードは取得しない */
            if (BigClass.BC_06.getId().equals(p_bigCls)) {
                return true;
            }
            // その他は含む
            return false;
        });
    }

    /**
     * CCS接触履歴（CCS入力）用<br>
     * 文字列の先頭が "YYYY/MM/DD-" で始まり、サフィックスがあればその文字列も一致するかを判定する。
     * 
     * @param x_str    判定対象の文字列
     * @param suffix   サフィックス（例: "30000"）※null可
     * @return         先頭が "YYYY/MM/DD-" で始まり、サフィックスがあれば一致すれば true、それ以外は false
     * 
     */
    private boolean startsWithDateForCcs(String x_str, String x_suffix) {
        if (x_str == null) return false;
        // パターン生成
        String p_pattern = "^\\d{4}/\\d{2}/\\d{2}-";
        // サフィックスがある場合、追加
        if (!StringUtil.isNullOrEmpty(x_suffix)) {
            p_pattern += x_suffix;
        }
        p_pattern += ".*";
        return x_str.matches(p_pattern);
    }

    /**
     * CCS接触履歴（CCS入力）用<br>
     * 記録日時文字列に "23:59:59" が含まれているかを判定する。
     * @param x_strDate
     * @return
     */
    private boolean isEndTimeForCcs(String x_strDate) {
        return !StringUtil.isNullOrEmpty(x_strDate) && x_strDate.contains("23:59:59");
    }
}