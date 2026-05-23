package com.sbisec.helios.ap.brokerageMenu.ipoPo.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.DateUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct007;
import com.sbisec.helios.ap.bizcommon.component.Fct033;
import com.sbisec.helios.ap.bizcommon.model.InputFct007Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct033Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct007Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct033Dto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.IfaSubscriptInputConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql009RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql009ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql011RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA006RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.service.IfaSubscriptInputConfirmEtintraService;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.util.IfaSubscriptInputException;
import com.sbisec.helios.ap.common.annotation.dao.EtintraTransactional;
import com.sbisec.helios.ap.common.dao.SystemDateDao;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sbisec.pcenter.dto.yanap.AdditionalPlaceOrderIn;
import jp.co.sbisec.pcenter.dto.yanap.AdditionalPlaceOrderInData;
import jp.co.sbisec.pcenter.dto.yanap.AdditionalPlaceOrderOutData;


/**
 * 画面ID：SUB0204_02-04_2
 * 画面名：募集入力確認（ETINTRAトランザクション制御用）
 * 2024/04/15 新規作成
 *
 * @author SCSK濱田
 */
@Component
public class IfaSubscriptInputConfirmEtintraServiceImpL implements IfaSubscriptInputConfirmEtintraService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaSubscriptInputConfirmEtintraServiceImpL.class);

    @Autowired
    private IfaSubscriptInputConfirmDao dao;

    @Autowired
    private ApiWrapper apiWrapper;

    @Autowired
    private SystemDateDao systemDateDao;

    @Autowired
    private Fct007 fct007;

    @Autowired
    private Fct033 fct033;
    
    /** エラーメッセージID：注文失敗エラー */
    private static final String ERRORS_PROCESSINGFAILED = "errors.processingFailed";

    /** エラーメッセージ：注文 */
    private static final String MSG_ORDER = "注文";

    /** 余力拘束処理タイプ："1" 余力拘束 */
    private static final String ACTION_TYPE_ADDITIONAL_PLACE_ORDER = "1";

    /** 余力拘束処理タイプ："0" 余力拘束解除 */
    private static final String ACTION_TYPE_ADDITIONAL_PLACE_ORDER_CANCEL = "0";

    /** 預り区分.旧NISA */
    private static final String DEPOSIT_TYPE_NISA_OLD = "4";
    
    /** 預り区分.NISA（成長投資枠） */
    private static final String DEPOSIT_TYPE_NISA_GROWTH_INVESTMENT = "H";


    /** エラーメッセージ：余力拘束 */
    private static final String ADDITIONAL_PLACE_ORDER = "余力拘束";

    /** エラーメッセージ：余力拘束解除 */
    private static final String ADDITIONAL_PLACE_ORDER_CANCEL = "余力拘束解除";

    /** エラーメッセージID：余力拘束エラー */
    private static final String ERRORS_PROCESSING_FAILED = "errors.processingFailed";


    /** FCT007：カレンダー区分（証券営業日ベース）*/
    private static final String CALENDAR_TYPE_0 = "0";
    
    /** FCT007：翌営業日 */
    private static final Integer ONE_DAY_AFTER = 1;

    /** FCT033：戻り値　"1" 営業日 */
    private static final String BUSINESS_DAY = "1";
    
    /** FCT033：戻り値　"0" 非営業日 */
    private static final String NOT_BUSINESS_DAY = "0";

    
    /**
     * アクションID：A001（ETINTRAトランザクション制御用）
     * アクション名：注文（仲介業者新規）
     * Dto リクエスト：IfaSubscriptInputConfirmA001DtoRequest
     *
     * @param dtoReq リクエストボディ
     * @exception Exception システムエラー
     */
    @Override
    @EtintraTransactional
    public void orderPlacementBrokerA001Etintra(IfaSubscriptInputConfirmA001RequestDto dtoReq, ApiErrorUtil apiErrorUtil) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSubscriptInputConfirmEtintraServiceImpL.orderPlacementBrokerA001Etintra");
        }
        
        // ユーザアカウント情報取得
        UserAccount ua = IfaCommonUtil.getUserAccount();

        // 備考を変更する場合、関連テーブル（ブックビルディング受付テーブル）を更新する。
        if (!StringUtils.equals(dtoReq.getBbRemark(), dtoReq.getBbRemark2())) {
            // ブックビルディング受付テーブルの更新（SQL009,SQL011）
            updateBbAcceptTable(dtoReq.getButenCode(), dtoReq.getAccountNumber(), dtoReq.getBrandCode(),
                    dtoReq.getBookBuildingPresentationFrom(), dtoReq.getBbRemark(), ua.getUserId(), ua.getUserNm());

        }
        
        // 余力拘束を行う
        callAdditionalPlaceOrder(ACTION_TYPE_ADDITIONAL_PLACE_ORDER, dtoReq.getButenCode(),
                dtoReq.getAccountNumber(), dtoReq.getContractAmount(), ua.getUserId(), dtoReq.getDepositType(), "", apiErrorUtil);
    }

    /**
     * アクションID：A005（ETINTRAトランザクション制御用）
     * アクション名：訂正（仲介業者更新）
     * Dto リクエスト：IfaSubscriptInputConfirmA005RequestDto
     *
     * @param dtoReq リクエストボディ
     * @throws Exception SQLエラー、API002エラー
     */
    @Override
    @EtintraTransactional
    public void orderUpdateBrokerA005Etintra(IfaSubscriptInputConfirmA005RequestDto dtoReq, ApiErrorUtil apiErrorUtil) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSubscriptInputConfirmEtintraServiceImpL.orderUpdateBrokerA005Etintra");
        }
        
        // ユーザアカウント情報取得
        UserAccount ua = IfaCommonUtil.getUserAccount();

        // 備考を変更する場合、関連テーブル（ブックビルディング受付テーブル）を更新する。
        if (!StringUtils.equals(dtoReq.getBbRemark(), dtoReq.getBbRemark2())) {
            // ブックビルディング受付テーブルの更新（SQL009,SQL011）
            updateBbAcceptTable(dtoReq.getButenCode(), dtoReq.getAccountNumber(), dtoReq.getBrandCode(),
                    dtoReq.getBookBuildingPresentationFrom(), dtoReq.getBbRemark(), ua.getUserId(), ua.getUserNm());
        }
        
        // 余力拘束を行う
        callAdditionalPlaceOrder(ACTION_TYPE_ADDITIONAL_PLACE_ORDER, dtoReq.getButenCode(),
                dtoReq.getAccountNumber(), dtoReq.getContractAmount(), ua.getUserId(), dtoReq.getDepositType(), "", apiErrorUtil);
    }

    /**
     * アクションID：A006（ETINTRAトランザクション制御用）
     * アクション名：訂正（仲介業者訂正）
     * Dto リクエスト：IfaSubscriptInputConfirmA006RequestDto
     *
     * @param dtoReq リクエストボディ
     * @throws Exception SQLエラー
     */
    @Override
    @EtintraTransactional
    public void orderCorrectionBrokerA006Etintra(IfaSubscriptInputConfirmA006RequestDto dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSubscriptInputConfirmEtintraServiceImpL.orderCorrectionBrokerA006Etintra");
        }
        
        // ユーザアカウント情報取得
        UserAccount ua = IfaCommonUtil.getUserAccount();

        // 備考を変更する場合、関連テーブル（ブックビルディング受付テーブル）を更新する。
        if (!StringUtils.equals(dtoReq.getBbRemark(), dtoReq.getBbRemark2())) {
            // ブックビルディング受付テーブルの更新（SQL009,SQL011）
            updateBbAcceptTable(dtoReq.getButenCode(), dtoReq.getAccountNumber(), dtoReq.getBrandCode(),
                    dtoReq.getBookBuildingPresentationFrom(), dtoReq.getBbRemark(), ua.getUserId(), ua.getUserNm());
        }

    }


    // --------------------------------------------------------------
    // メソッド
    // --------------------------------------------------------------

    /**
     * ブックビルディング受付テーブルの更新<br/>
     * SQl009を実行し、セクションIDと銘柄コードを取得<br/>
     * SQL011を実行し、ブックビルディング受付テーブルを更新する。
     *
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @param brandCode 銘柄コード
     * @param bookBuildingPresentationFrom ブックビルディング申込期間（開始）
     * @param bbRemark 備考
     * @return SQL011の更新件数
     * @throws Exception システムエラー, SQL実行時のエラー
     */
    private int updateBbAcceptTable(String butenCode, String accountNumber, String brandCode,
            String bookBuildingPresentationFrom, String bbRemark, String userId, String userNm) throws Exception {
        
        // SQL009のリクエストを作成
        IfaSubscriptInputConfirmSql009RequestModel selSql009Req = new IfaSubscriptInputConfirmSql009RequestModel();
        selSql009Req.setButenCode(butenCode);
        selSql009Req.setAccountNumber(accountNumber);
        
        // SQL009を実行
        DataList<IfaSubscriptInputConfirmSql009ResponseModel> selSql009Res = dao
                .selectIfaSubscriptInputConfirmSql009(selSql009Req);
        
        // SQL011のリクエストを作成
        IfaSubscriptInputConfirmSql011RequestModel updSql011Req = new IfaSubscriptInputConfirmSql011RequestModel();
        updSql011Req.setBbRemark(bbRemark);
        updSql011Req.setUserId(userId);
        updSql011Req.setUserNm(userNm);
        updSql011Req.setSectionId(selSql009Res.get(0).getSectionId());
        updSql011Req.setBranchName(selSql009Res.get(0).getBranchName());
        updSql011Req.setBrandCode(brandCode);
        updSql011Req.setButenCode(butenCode);
        updSql011Req.setAccountNumber(accountNumber);
        updSql011Req.setBookBuildingPresentationFrom(bookBuildingPresentationFrom);
        
        int updSql011Res;
        
        try {
            // SQL011を実行
            // 異常時はエラーメッセージを出力
            updSql011Res = dao.updateIfaSubscriptInputConfirmSql011(updSql011Req);
            if (updSql011Res == 0) {
                throw new IfaSubscriptInputException(ERRORS_PROCESSINGFAILED, new String[] { MSG_ORDER });
            }
        } catch (Exception e) {
            LOGGER.error("Exception occured.");
            LOGGER.info("Exception occured.", e);
            throw new IfaSubscriptInputException(ERRORS_PROCESSINGFAILED, new String[] { MSG_ORDER });
        }
        
        return updSql011Res;
    }

    /**
     * 余力拘束・余力拘束解除を行う<br/>
     * API002 NRI_AdditionalPlaceOrder の呼び出し
     *
     * @param actionType 処理タイプ（"1" 余力拘束、"0" 余力拘束解除）
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @param contractAmount 約定金額
     * @param userId ユーザID
     * @param depositType 預り区分
     * @param recruitmentOrderDate 募集受注日時（処理タイプ "0" の時のみ）
     * @throws Exception API002実行時の例外
     */
    protected void callAdditionalPlaceOrder(String actionType, String butenCode, String accountNumber,
            String contractAmount, String userId, String depositType, String recruitmentOrderDate, ApiErrorUtil apiErrorUtil) throws Exception {
        
        // システム日付以降の直近営業日を取得
        Date systemDate = systemDateDao.getSystemDate();
        String nextBusinessDayFromSystemDate = getLastBusinessDay(systemDate);
        
        // API002のリクエストを作成
        AdditionalPlaceOrderInData api002InData = new AdditionalPlaceOrderInData();
        
        // トランザクションID
        api002InData.setTransactionId("");
        // 部店コード
        api002InData.setButenCd(butenCode);
        // 口座番号
        api002InData.setKozaNo(String.format("%7s", accountNumber).replace(' ', '0'));
        // 商品区分
        api002InData.setSecId("Z9");
        // 継投区分 固定値'△△'
        api002InData.setAccmulateId("  ");
        // 会社コード 固定値'△△△△△'
        api002InData.setCompanyCode("     ");
        // 新旧区分 固定値'△'
        api002InData.setNewOldId(" ");
        // 回数 固定値''
        api002InData.setSerNo("");
        // 号 固定値''
        api002InData.setSubCode("");
        // 商品名 固定値''
        api002InData.setBrandName("");
        // 約定予定日 システム日付以降の直近営業日
        api002InData.setTradeDate(nextBusinessDayFromSystemDate);
        // 受渡予定日 システム日付以降の直近営業日
        api002InData.setSettleDate(nextBusinessDayFromSystemDate);
        // 売買区分 固定値'K'
        api002InData.setBuySell("K");
        // 精算金額
        api002InData.setNetAmount(String.format("%15s", contractAmount).replace(' ', '0'));
        // 注文・出来区分 固定値'△'
        api002InData.setOrderStatus(" ");
        // 受渡方法 固定値'△'
        api002InData.setSettleCode(" ");
        // 数量
        api002InData.setQuantity("0");
        // 指成区分 固定値'△'
        api002InData.setSasinariKbn(" ");
        // 単価
        api002InData.setPrice("0");
        // 市場名 固定値'△'
        api002InData.setMarketName(" ");
        // 譲渡益税区分
        api002InData.setCgTaxId(" ");
        // 通貨
        api002InData.setCcy("JPY");
        // 為替レート
        api002InData.setExchangeRate("1");
        // クーポンレート
        api002InData.setCouponRate("0");
        // 受付経路区分
        api002InData.setCallcenterKbn("1");
        // ユーザーＩＤ（10桁以上の場合、左側10桁数を切り出す。）
        String editUserId;
        if (userId.length() >= 10) {
            editUserId = userId.substring(0, 10);
        } else {
            editUserId = userId;
        }
        api002InData.setUserId(editUserId);
        // 余力チェック区分 固定値'△'
        api002InData.setCheckId(" ");
        
        // 拘束区分 
        // リクエスト.預り区分の選択値が"4"（旧NISA）の場合、'2'を設定
        if (DEPOSIT_TYPE_NISA_OLD.equals(depositType)) {
            api002InData.setHoldKbn("2");
            
            // リクエスト.預り区分の選択値が"H"（新NISA「総合NISA(成長投資枠)」）の場合、'6'を設定する。
        }
        if (DEPOSIT_TYPE_NISA_GROWTH_INVESTMENT.equals(depositType)) {
            api002InData.setHoldKbn("6");
            
            // リクエスト.預り区分の選択値がNISA以外の場合、'1'を設定する。
        } else {
            api002InData.setHoldKbn("1");
        }
        
        // 拘束金額(ISA買付可能枠・総合NISA(成長投資枠/つみたて）買付可能枠）
        // リクエスト.預り区分の選択値がNISA（"4"または"H"）の場合、リクエスト.約定金額を設定する。
        if (DEPOSIT_TYPE_NISA_OLD.equals(depositType) || DEPOSIT_TYPE_NISA_GROWTH_INVESTMENT.equals(depositType)) {
            // NISAの場合、10桁に満たない場合は左側「0」埋め
            api002InData.setIsaBuyLimitHold(String.format("%10s", contractAmount).replace(' ', '0'));
            
            // リクエスト.預り区分の選択値がNISA以外の場合、'△△△△△△△△△△'を設定する。
        } else {
            api002InData.setIsaBuyLimitHold("          ");
        }
        
        // 指定口座区分
        api002InData.setAccountDesKbn(" ");
        
        // アクションによって設定値を変える項目
        // 余力拘束解除時（A007の時）
        if (ACTION_TYPE_ADDITIONAL_PLACE_ORDER_CANCEL.equals(actionType)) {
            
            // 募集受注日時から直近の営業日を取得
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date dateTypeRecruitmentOrderDate = sdf.parse(recruitmentOrderDate);
            String nextBusinessDayFromRecruitmentOrderDate = getLastBusinessDay(dateTypeRecruitmentOrderDate);
            // 取消区分 
            api002InData.setCxlStatus("1");
            // 受注日
            api002InData.setInputDate(nextBusinessDayFromRecruitmentOrderDate);
            // 受注時刻
            api002InData.setInputTime(DateUtil.format(dateTypeRecruitmentOrderDate, "HHmmss"));
            
            // 余力拘束時（A007以外の時）
        } else {
            
            // 取消区分
            api002InData.setCxlStatus(" ");
            // 受注日
            api002InData.setInputDate(nextBusinessDayFromSystemDate);
            // 受注時刻
            api002InData.setInputTime(DateUtil.format(systemDate, "HHmmss"));
        }
        
        AdditionalPlaceOrderIn api002Input = new AdditionalPlaceOrderIn();
        api002Input.setIndata(api002InData);
        
        // API002を呼び出し
        AdditionalPlaceOrderOutData api002OutData = apiWrapper.additionalPlaceOrder(api002Input);
        apiErrorUtil.checkApiResponse(api002OutData.getShubetu(), api002OutData.getCode(), api002OutData.getMessage());
        if (apiErrorUtil.isFatal()) {
            if (ACTION_TYPE_ADDITIONAL_PLACE_ORDER_CANCEL.equals(actionType)) {
                throw new IfaSubscriptInputException(ERRORS_PROCESSING_FAILED, new String[] { ADDITIONAL_PLACE_ORDER_CANCEL });
            } else {
                throw new IfaSubscriptInputException(ERRORS_PROCESSING_FAILED, new String[] { ADDITIONAL_PLACE_ORDER });
            }
        }

    }

    /**
     * 直近の営業日を取得
     *
     * @param date 基準日付
     * @return 直近の営業日（String YYYYMMDD）
     */
    protected String getLastBusinessDay(Date date) throws Exception {
        
        // 営業日チェック
        String businessDayCheckFlag = checkBusinessDay(date);
        
        // FCT033.営業日チェックフラグ = "1"（営業日）の場合：指定日を返却
        if (BUSINESS_DAY.equals(businessDayCheckFlag)) {
            return DateUtil.format(date, DateUtil.YYYYMMDD);
            
            // FCT033.営業日チェックフラグ = "0"（非営業日）の場合：翌営業日を取得して返却
        } else if (NOT_BUSINESS_DAY.equals(businessDayCheckFlag)) {
            // 募集受注日時以降の直近営業日 = FCT007.指定日
            return getNextBusinessDay(date);
        }
        
        return null;
    }
    
    /**
     * FCT007を呼び出して翌営業日を取得
     *
     * @param date 基準日付
     * @return 翌営業日
     */
    protected String getNextBusinessDay(Date date) throws Exception {
        
        InputFct007Dto inputFct007Dto = new InputFct007Dto();
        //基準日 設定元：   システム日付
        inputFct007Dto.setStandardDate(date);
        //カレンダー区分 設定元：   ０（証券営業日ベース）
        inputFct007Dto.setCalendarType(CALENDAR_TYPE_0);
        //日数  設定元：   1
        inputFct007Dto.setDay(ONE_DAY_AFTER);
        
        OutputFct007Dto outputFct007Dto;
        outputFct007Dto = fct007.getData(inputFct007Dto);

        return DateUtil.format(outputFct007Dto.getDesignatedDate(), DateUtil.YYYYMMDD);
    }
    
    /**
     * FCT033を呼び出して営業日チェック
     *
     * @param date 基準日付
     * @return 営業日チェックフラグ
     * @throws Exception FCT033実行時の例外
    */
    protected String checkBusinessDay(Date date) throws Exception {
        
        InputFct033Dto fct033InputDto = new InputFct033Dto();
        fct033InputDto.setDate(date);
        OutputFct033Dto fct033OutputDto = fct033.doCheck(fct033InputDto);
        return fct033OutputDto.getBusinessDayCheckFlag();
        
    }
    
}
